DROP TABLE IF EXISTS comments;
CREATE TABLE comments (
     id SERIAL PRIMARY KEY, text VARCHAR(255) NOT NULL, post_id BIGINT NOT NULL
);
INSERT INTO comments (text, post_id) VALUES ('------Que dia lindo hoje!------', 1);
INSERT INTO comments (text, post_id) VALUES ('------Seja feliz agora mesmo!!----', 1);
INSERT INTO
comments (text, post_id)
VALUES ('-------Estudos de banco de dados múltiplos-------', 1);
INSERT INTO
comments (text, post_id)
VALUES ('-------A prática leva a perfeição!!!-----', 1);
