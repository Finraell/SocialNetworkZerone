package com.skillbox.javapro21.service.serviceImpl;

import com.mailjet.client.errors.MailjetException;
import com.skillbox.javapro21.api.request.account.*;
import com.skillbox.javapro21.api.response.DataResponse;
import com.skillbox.javapro21.api.response.ListDataResponse;
import com.skillbox.javapro21.api.response.MessageOkContent;
import com.skillbox.javapro21.api.response.account.NotificationSettingData;
import com.skillbox.javapro21.config.MailjetSender;
import com.skillbox.javapro21.config.properties.ConfirmationUrl;
import com.skillbox.javapro21.config.security.JwtGenerator;
import com.skillbox.javapro21.domain.NotificationType;
import com.skillbox.javapro21.domain.Person;
import com.skillbox.javapro21.domain.enumeration.MessagesPermission;
import com.skillbox.javapro21.domain.enumeration.NotificationTypeStatus;
import com.skillbox.javapro21.domain.enumeration.UserType;
import com.skillbox.javapro21.exception.TokenConfirmationException;
import com.skillbox.javapro21.exception.UserExistException;
import com.skillbox.javapro21.repository.NotificationTypeRepository;
import com.skillbox.javapro21.repository.PersonRepository;
import com.skillbox.javapro21.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AccountServiceImpl extends AbstractMethodClass implements AccountService {
    private final PersonRepository personRepository;
    private final MailjetSender mailMessage;
    private final ConfirmationUrl confirmationUrl;
    private final JwtGenerator jwtGenerator;
    private final NotificationTypeRepository notificationTypeRepository;

    @Autowired
    public AccountServiceImpl(PersonRepository personRepository, MailjetSender mailMessage, ConfirmationUrl confirmationUrl, JwtGenerator jwtGenerator, NotificationTypeRepository notificationTypeRepository) {
        super(personRepository);
        this.personRepository = personRepository;
        this.mailMessage = mailMessage;
        this.confirmationUrl = confirmationUrl;
        this.jwtGenerator = jwtGenerator;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    //Todo: нужна ли проверка каптчи?
    public DataResponse<MessageOkContent> registration(RegisterRequest registerRequest) throws UserExistException, MailjetException {
        if (personRepository.findByEmail(registerRequest.getEmail()).isPresent())
            throw new UserExistException("Пользователь с таким логином существует");
        createNewPerson(registerRequest);
        mailMessageForRegistration(registerRequest);
        return getMessageOkResponse();
    }

    public String verifyRegistration(String email, String code) throws TokenConfirmationException {
        Person person = findPersonByEmail(email);
        if (person.getConfirmationCode().equals(code)) {
            person.setIsApproved(1)
                    .setUserType(UserType.USER)
                    .setMessagesPermission(MessagesPermission.ALL)
                    .setConfirmationCode("");
            personRepository.save(person);
        } else throw new TokenConfirmationException("Не верный confirmation code");
        return "Пользователь подтвержден";
    }

    public String recoveryPasswordMessage(RecoveryRequest recoveryRequest) throws MailjetException {
        String token = getToken();
        String text = confirmationUrl.getUrlForPasswordComplete() + "?email=" + recoveryRequest.getEmail() + "&code=" + token;
        confirmPersonAndSendEmail(recoveryRequest.getEmail(), text, token);
        return "Ссылка отправлена на почту";
    }

    private void mailMessageForRegistration(RegisterRequest registerRequest) throws MailjetException {
        String token = getToken();
        String text = confirmationUrl.getUrlForRegisterComplete() + "?email=" + registerRequest.getEmail() + "&code=" + token;
        confirmPersonAndSendEmail(registerRequest.getEmail(), text, token);
    }

    public String verifyRecovery(String email, String code) throws TokenConfirmationException {
        Person person = findPersonByEmail(email);
        if (person.getConfirmationCode().equals(code)) {
            return "Пользователь может приступить к изменению пароля";
        } else throw new TokenConfirmationException("Не верный confirmation code");
    }

    public String recoveryPassword(String email, String password) {
        Person person = findPersonByEmail(email);
        person.setPassword(password);
        personRepository.save(person);
        return "Пароль успешно изменен";
    }

    public DataResponse<MessageOkContent> changePassword(ChangePasswordRequest changePasswordRequest) {
        Person person = findPersonByEmail(jwtGenerator.getLoginFromToken(changePasswordRequest.getToken()));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        person.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
        personRepository.save(person);
        return getMessageOkResponse();
    }

    public DataResponse<MessageOkContent> changeEmail(ChangeEmailRequest changeEmailRequest, Principal principal) {
        Person person = findPersonByEmail(principal.getName());
        person.setEmail(changeEmailRequest.getEmail());
        return getMessageOkResponse();
    }

    public DataResponse<MessageOkContent> changeNotifications(ChangeNotificationsRequest changeNotificationsRequest, Principal principal) {
        Person person = findPersonByEmail(principal.getName());
        NotificationType notificationType = notificationTypeRepository.findNotificationTypeByPersonId(person.getId())
                .orElse(new NotificationType()
                        .setPost(true)
                        .setPostComment(true)
                        .setCommentComment(true)
                        .setFriendsRequest(true)
                        .setMessage(true));
        switch (changeNotificationsRequest.getNotificationTypeStatus()) {
            case POST -> notificationType.setPost(changeNotificationsRequest.isEnable());
            case POST_COMMENT -> notificationType.setPostComment(changeNotificationsRequest.isEnable());
            case COMMENT_COMMENT -> notificationType.setCommentComment(changeNotificationsRequest.isEnable());
            case FRIEND_REQUEST -> notificationType.setFriendsRequest(changeNotificationsRequest.isEnable());
            case MESSAGE -> notificationType.setMessage(changeNotificationsRequest.isEnable());
            case FRIEND_BIRTHDAY -> notificationType.setFriendsBirthday(changeNotificationsRequest.isEnable());
        }
        notificationTypeRepository.save(notificationType);
        return getMessageOkResponse();
    }

    public ListDataResponse<NotificationSettingData> getNotifications(Principal principal) {
        Person person = findPersonByEmail(principal.getName());
        NotificationType notificationType = notificationTypeRepository.findNotificationTypeByPersonId(person.getId())
                .orElse(new NotificationType()
                        .setPost(true)
                        .setPostComment(true)
                        .setCommentComment(true)
                        .setFriendsRequest(true)
                        .setMessage(true)
                        .setFriendsBirthday(true));
        ListDataResponse<NotificationSettingData> dataResponse = new ListDataResponse<>();
        dataResponse.setTimestamp(LocalDateTime.now());
        dataListNotification(notificationType);
        dataResponse.setData(dataListNotification(notificationType));
        return dataResponse;
    }

    /**
     * Заполнение новыми парраметрами настроек оповещения
     */
    private List<NotificationSettingData> dataListNotification(NotificationType notificationType) {
        List<NotificationSettingData> list = new ArrayList<>();
        list.add(new NotificationSettingData().setNotificationTypeStatus(NotificationTypeStatus.POST)
                .setEnable(notificationType.isPost()));
        list.add(new NotificationSettingData().setNotificationTypeStatus(NotificationTypeStatus.POST_COMMENT)
                .setEnable(notificationType.isPostComment()));
        list.add(new NotificationSettingData().setNotificationTypeStatus(NotificationTypeStatus.COMMENT_COMMENT)
                .setEnable(notificationType.isCommentComment()));
        list.add(new NotificationSettingData().setNotificationTypeStatus(NotificationTypeStatus.FRIEND_REQUEST)
                .setEnable(notificationType.isFriendsRequest()));
        list.add(new NotificationSettingData().setNotificationTypeStatus(NotificationTypeStatus.MESSAGE)
                .setEnable(notificationType.isMessage()));
        list.add(new NotificationSettingData().setNotificationTypeStatus(NotificationTypeStatus.FRIEND_BIRTHDAY)
                .setEnable(notificationType.isFriendsBirthday()));
        return list;
    }

    /**
     * Отправка на почту письма с токеном
     */
    private void confirmPersonAndSendEmail(String email, String text, String token) throws MailjetException {
        Person person = findPersonByEmail(email);
        person.setConfirmationCode(token);
        personRepository.save(person);
        mailMessage.send(email, text);
        log.info(String.valueOf(email));
        log.info(String.valueOf(text));
    }

    /**
     * Создание пользователя без верификации
     */
    private void createNewPerson(RegisterRequest registerRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        Person person = new Person()
                .setEmail(registerRequest.getEmail())
                .setFirstName(registerRequest.getFirstName())
                .setLastName(registerRequest.getLastName())
                .setConfirmationCode(registerRequest.getCode())
                .setIsApproved(0)
                .setPassword(passwordEncoder.encode(registerRequest.getPasswd1()))
                .setRegDate(LocalDateTime.now())
                .setLastOnlineTime(LocalDateTime.now())
                .setIsBlocked(0)
                .setMessagesPermission(MessagesPermission.NOBODY);
        personRepository.save(person);
        globalNotificationsSettings(person);
    }

    /**
     * Стартовые настройки оповещения
     */
    private void globalNotificationsSettings(Person person) {
        NotificationType notificationType = new NotificationType()
                .setPerson(person)
                .setPost(true)
                .setPostComment(true)
                .setCommentComment(true)
                .setFriendsRequest(true)
                .setMessage(true);
        notificationTypeRepository.save(notificationType);
    }
}
