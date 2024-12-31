DROP TABLE IF EXISTS posts;
CREATE TABLE posts (
    id SERIAL PRIMARY KEY, text VARCHAR(255) NOT NULL
);
INSERT INTO
   posts (text)
VALUES (
    'Múltiplos DBs com Spring Boot'
 );