DROP TABLE IF EXISTS ACCOUNT;
CREATE TABLE ACCOUNT
(
    ID          	VARCHAR(30)     generated by default as identity,
    LOGIN_ID       	VARCHAR(15)     NOT NULL,
    PASSWORD    	VARCHAR(100)    NOT NULL,
    NICK_NAME		VARCHAR(30)		NOT NULL,
    CREATED_AT		VARCHAR(30)		NOT NULL,
    CREATED_BY		VARCHAR(30)		NOT NULL,
    UPDATED_AT		VARCHAR(30)		NOT NULL,
    UPDATED_BY		VARCHAR(30)		NOT NULL,
    PRIMARY KEY (ID, LOGIN_ID)
);

DROP TABLE IF EXISTS SEARCH_HISTORY;
CREATE TABLE SEARCH_HISTORY
(
    SEARCH_WORD    	VARCHAR(50)     NOT NULL,
    SEARCH_COUNT    int(5)		    NOT NULL 	DEFAULT 0,
    CREATED_AT		VARCHAR(30)		NOT NULL,
    CREATED_BY		VARCHAR(30)		NOT NULL,
    UPDATED_AT		VARCHAR(30)		NOT NULL,
    UPDATED_BY		VARCHAR(30)		NOT NULL,
    PRIMARY KEY (SEARCH_WORD)
);

DROP TABLE IF EXISTS BOOKMARK_BLOG;
CREATE TABLE BOOKMARK_BLOG
(
	ID          	VARCHAR(30)     generated by default as identity,
    LOGIN_ID    	VARCHAR(15)     ,
    BOOKMARK_TITLE  VARCHAR(500)	NOT NULL,
    BOOKMARK_URL    VARCHAR(500)	NOT NULL,
    CREATED_AT		VARCHAR(30)		NOT NULL,
    CREATED_BY		VARCHAR(30)		NOT NULL,
    UPDATED_AT		VARCHAR(30)		NOT NULL,
    UPDATED_BY		VARCHAR(30)		NOT NULL,
    PRIMARY KEY (ID, LOGIN_ID)
);