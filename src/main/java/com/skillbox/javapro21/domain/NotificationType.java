package com.skillbox.javapro21.domain;

import com.skillbox.javapro21.domain.enumeration.NotificationTypeStatus;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "notification_type")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class NotificationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_status")
    private NotificationTypeStatus notificationStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NotificationType that = (NotificationType) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
