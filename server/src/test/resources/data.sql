ALTER TABLE image
    ALTER COLUMN data SET DATA TYPE LONGBLOB;
-- h2 DB는 MODIFY를 지원하지 않는다.

-- member 테이블에 데이터 삽입
INSERT INTO member (id, name, custom_id, pw, point, grade, birthday, gender, image_id)
VALUES (1, 'name1', 'customId1', 'password1', 0, 'BEGINNER', '2000-01-01', 'women', null);
-- thinking 테이블에 데이터 삽입
INSERT INTO thinking (id, writer_id, title, contents, date_time, is_premium, like_count, replies_count, view_count)
VALUES (1, 1, 'title1', 'content1', CAST('2024-01-01 00:00:00' AS TIMESTAMP), true, 9, 0, 0),
       (2, 1, 'title2', 'content2', CAST('2024-01-01 00:00:00' AS TIMESTAMP), true, 8, 0, 0),
       (3, 1, 'title3', 'content3', CAST('2024-01-01 00:00:00' AS TIMESTAMP), true, 7, 0, 0),
       (4, 1, 'title4', 'content4', CAST('2024-01-01 00:00:00' AS TIMESTAMP), true, 6, 0, 0),
       (5, 1, 'title5', 'content5', CAST('2024-01-01 00:00:00' AS TIMESTAMP), true, 5, 0, 0),
       (6, 1, 'title6', 'content6', CAST('2024-01-01 00:00:00' AS TIMESTAMP), true, 4, 0, 0),
       (7, 1, 'title7', 'content7', CAST('2024-01-01 00:00:00' AS TIMESTAMP), true, 3, 0, 0),
       (8, 1, 'title8', 'content8', CAST('2024-01-01 00:00:00' AS TIMESTAMP), true, 2, 0, 0),
       (9, 1, 'title9', 'content9', CAST('2024-01-01 00:00:00' AS TIMESTAMP), true, 1, 0, 0);

