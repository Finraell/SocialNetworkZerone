CREATE TABLE dialogs
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title      VARCHAR(255),
    is_blocked INTEGER,
    CONSTRAINT pk_dialogs PRIMARY KEY (id)
);

ALTER TABLE messages ADD COLUMN dialog_id INTEGER;

ALTER TABLE messages
    ADD CONSTRAINT FK_MESSAGES_ON_DIALOG FOREIGN KEY (dialog_id) REFERENCES dialogs (id) ON DELETE CASCADE;

CREATE TABLE person2dialog
(
    id         INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    person_id  BIGINT,
    dialog_id  INTEGER,
    last_check TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_person2dialog PRIMARY KEY (id)
);

ALTER TABLE person2dialog
    ADD CONSTRAINT FK_PERSON2DIALOG_ON_DIALOG FOREIGN KEY (dialog_id) REFERENCES dialogs (id);

ALTER TABLE person2dialog
    ADD CONSTRAINT FK_PERSON2DIALOG_ON_PERSON FOREIGN KEY (person_id) REFERENCES persons (id);

INSERT INTO persons (id, first_name, last_name, reg_date, birth_date, email, phone, password, photo, about, town, country,
                     confirmation_code, is_approved, messages_permission, user_type, last_online_time, is_blocked)
VALUES (777, 'Иван', 'Зуккель', '2021-12-12 17:30:03', '1993-09-22', 'briz.zukkel@gmail.com', '+7 (999) 111-0000', '$2a$12$y0xCXLylGH5/mCiaNlkZcu87zsXoflsGq29rGzVGCdtCr1tZoSIWe',
        '',
        '', 'Новороссийск', 'Россия',
        '1111', 1, 'ALL', 'ADMIN', '2021-12-16 16:57:08', 0),
       (666, 'Иван', 'Пуккель', '2021-12-12 17:30:03', '1993-09-22', 'ivan_zykkel@mail.ru', '+7 (999) 111-0000', '$2a$12$y0xCXLylGH5/mCiaNlkZcu87zsXoflsGq29rGzVGCdtCr1tZoSIWe',
        '',
        '', 'Новороссийск', 'Россия',
        '1111', 1, 'ALL', 'ADMIN', '2021-12-16 16:57:08', 0);

INSERT INTO dialogs (id, title, is_blocked)
VALUES (777, 'Дружбаны', 0);

INSERT INTO person2dialog (id, person_id, dialog_id, last_check)
VALUES (777, 777, 777, '2021-12-16 16:57:08'),
       (778, 666, 777, '2021-12-16 16:57:08');

INSERT INTO messages (id, dialog_id, time, author_id, recipient_id, message_text, read_status)
VALUES (777, 777, '2021-12-18 00:59:21', 777, 666,
        'are you buba',
        'SENT'),
       (778, 777, '2021-12-18 09:54:15', 666, 777,
        'no, i am biba',
        'SENT')

