INSERT INTO friendship_statuses (time, name, code)
VALUES ('2021-12-10 12:44:34', 'REQUEST', 11),
       ('2021-12-10 12:58:07', 'SUBSCRIBED', 1),
       ('2021-12-15 21:42:12', 'FRIEND', 1),
       ('2021-12-09 04:29:11', 'REQUEST', 3),
       ('2021-12-09 05:38:02', 'SUBSCRIBED', 10),
       ('2021-12-15 02:53:37', 'BLOCKED', 10),
       ('2021-12-10 11:52:19', 'REQUEST', 9),
       ('2021-12-15 03:01:00', 'FRIEND', 4),
       ('2021-12-15 03:38:52', 'REQUEST', 2),
       ('2021-12-15 03:13:59', 'SUBSCRIBED', 8),
       ('2021-12-15 13:59:36', 'FRIEND', 8),
       ('2021-12-15 04:38:52', 'REQUEST', 7),
       ('2021-12-15 13:29:13', 'DECLINED', 6),
       ('2021-12-15 04:38:52', 'REQUEST', 7),
       ('2021-12-15 21:49:01', 'FRIEND', 11),
       ('2021-12-09 04:38:52', 'REQUEST', 1),
       ('2021-12-09 06:25:16', 'FRIEND', 13),
       ('2021-12-09 12:25:13', 'BLOCKED', 13);

INSERT INTO notification_type(
    person_id, code, name)
    VALUES (1, 'false', 'POST'),
           (3, 'true', 'POST'),
           (1, 'false', 'POST_COMMENT'),
           (3, 'true', 'POST_COMMENT'),
           (3, 'false', 'COMMENT_COMMENT'),
           (1, 'true', 'COMMENT_COMMENT'),
           (3, 'false', 'FRIEND_REQUEST'),
           (1, 'true', 'FRIEND_REQUEST'),
           (3, 'false', 'MESSAGE'),
           (1, 'true', 'MESSAGE');



insert into friendship (status_id, src_person_id, dst_person_id)
values (1, 11, 1),
       (2, 1, 11),
       (3, 1, 11),
       (4, 10, 3),
       (5, 3, 10),
       (6, 3, 10),
       (7, 4, 9),
       (8, 9, 4),
       (9, 8, 2),
       (10, 2, 8),
       (11, 2, 8),
       (12, 6, 7),
       (13, 7, 6),
       (14, 11, 7),
       (15, 7, 11),
       (16, 13, 1),
       (17, 1, 13),
       (18, 1, 13);
