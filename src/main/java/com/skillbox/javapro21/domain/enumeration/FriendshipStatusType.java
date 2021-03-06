package com.skillbox.javapro21.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum FriendshipStatusType {
    REQUEST("Запрос на добавление в друзья"),
    FRIEND("Друзья"),
    BLOCKED("Пользователь в черном списке"),
    DECLINED("Запрос на добавление в друзья отклонен"),
    SUBSCRIBED("Подписан"),
    WASBLOCKED("Заблокирован пользователем"),
    INTERLOCKED("Взаимная блокировка");

    private final String name;
}
