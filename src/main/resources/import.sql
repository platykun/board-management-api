-- ユーザ
CREATE TABLE IF NOT EXISTS USER ( ID INT NOT NULL AUTO_INCREMENT, NAME VARCHAR(20) NOT NULL , AUTHORITY INT NOT NULL);
-- パスワード
CREATE TABLE IF NOT EXISTS PASSWORD ( USER_ID INT NOT NULL,PASSWORD VARCHAR(20) NOT NULL );

-- ルーム
DROP TABLE ROOM;
CREATE TABLE IF NOT EXISTS ROOM ( ID INT NOT NULL AUTO_INCREMENT,ROOM_NAME VARCHAR(20) NOT NULL, BOARD_TITLE VARCHAR(20) NOT NULL, PLACE_NAME VARCHAR(20), PLAYER INT NOT NULL, REMARK VARCHAR(20) );

-- 参加
CREATE TABLE IF NOT EXISTS JOIN_ROOM (USER_ID INT NOT NULL, ROOM_ID INT NOT NULL, OWNER BOOLEAN, PRIMARY KEY(USER_ID, ROOM_ID));

-- 結果
CREATE TABLE IF NOT EXISTS RESULT ( USER_ID INT NOT NULL, ROOM_ID INT NOT NULL, RANK INT NOT NULL, SCORE VARCHAR(20), COMMENT VARCHAR(20), PRIMARY KEY(USER_ID, ROOM_ID));
-- ボードゲーム
CREATE TABLE IF NOT EXISTS BOARD_GAME( TITLE VARCHAR(20) NOT NULL, PLAYER INT NOT NULL, OVERVIEW VARCHAR(50));
-- 場所
CREATE TABLE IF NOT EXISTS PLACE (NAME VARCHAR(20), URL VARCHAR(50));

INSERT INTO USER (ID, NAME, AUTHORITY) VALUES (0, 'user001', 0);
INSERT INTO USER (ID, NAME, AUTHORITY) VALUES (1, 'user002', 1);
INSERT INTO USER (ID, NAME, AUTHORITY) VALUES (2, 'user003', 1);
INSERT INTO PASSWORD (USER_ID, PASSWORD) VALUES (0, 'password');
INSERT INTO PASSWORD (USER_ID, PASSWORD) VALUES (1, 'password');
INSERT INTO PASSWORD (USER_ID, PASSWORD) VALUES (2, 'password');
INSERT INTO ROOM (ID, ROOM_NAME, BOARD_TITLE, PLACE_NAME, PLAYER, REMARK) VALUES (0, 'a部屋1', 'ボードゲーム0', 'ボドゲショップ1', 2, null);
INSERT INTO ROOM (ID, ROOM_NAME, BOARD_TITLE, PLACE_NAME, PLAYER, REMARK) VALUES (1, 'a部屋2', 'ボードゲーム0', 'ボドゲショップ1', 2, 'biko');
INSERT INTO ROOM (ID, ROOM_NAME, BOARD_TITLE, PLACE_NAME, PLAYER, REMARK) VALUES (2, 'bc部屋1', 'ボードゲーム0', 'ボドゲショップ1', 3, 'hoge');
INSERT INTO ROOM (ID, ROOM_NAME, BOARD_TITLE, PLACE_NAME, PLAYER, REMARK) VALUES (3, 'bc部屋2', 'ボードゲーム0', 'ボドゲショップ1', 4, 'fuga');
INSERT INTO ROOM (ID, ROOM_NAME, BOARD_TITLE, PLACE_NAME, PLAYER, REMARK) VALUES (4, 'bd部屋3', 'ボードゲーム0', 'ボドゲショップ1', 5, 'piyo');
INSERT INTO JOIN_ROOM (USER_ID, ROOM_ID, OWNER) VALUES (0, 0, true);
INSERT INTO JOIN_ROOM (USER_ID, ROOM_ID, OWNER) VALUES (0, 1, false);
INSERT INTO RESULT (USER_ID, ROOM_ID, RANK, SCORE, COMMENT) VALUES (0, 0, 1, 'スコア100', '');
INSERT INTO RESULT (USER_ID, ROOM_ID, RANK, SCORE, COMMENT) VALUES (1, 0, 1, 'スコア50', '');
INSERT INTO BOARD_GAME (TITLE, PLAYER, OVERVIEW) VALUES ('ボードゲーム0', 2, '概要0');
INSERT INTO BOARD_GAME (TITLE, PLAYER, OVERVIEW) VALUES ('ボードゲーム1', 3, '概要1');
INSERT INTO BOARD_GAME (TITLE, PLAYER, OVERVIEW) VALUES ('ボードゲーム2', 4, '概要2');
INSERT INTO PLACE (NAME) VALUES ('ボドゲショップ1');