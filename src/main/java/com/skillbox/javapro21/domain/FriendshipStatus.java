package com.skillbox.javapro21.domain;

import com.skillbox.javapro21.config.PostgreSQLEnumType;
import com.skillbox.javapro21.domain.enumeration.FriendshipStatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Accessors(chain = true)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@Table(name = "friendship_statuses")
public class FriendshipStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "code")
    private long code;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "name")
    private FriendshipStatusType friendshipStatusType;

//    @JsonIgnoreProperties(value = {"friendshipStatus", "srcPerson", "dstPerson"}, allowSetters = true)
//    @OneToOne(mappedBy = "friendshipStatus")
//    private Friendship friendship;

}
