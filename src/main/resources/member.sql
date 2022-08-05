DROP TABLE IF EXISTS MEMBER;
CREATE TABLE MEMBER
(
    ID          	VARCHAR(255)    PRIMARY KEY,
    NAME        	VARCHAR(255)    NOT NULL,
    PASSWORD    	VARCHAR(255)    NOT NULL,
    MEMBER_TYPE		VARCHAR(30)		NOT NULL,
    CREATED_AT		VARCHAR(30)		NOT NULL,
    CREATED_BY		VARCHAR(30)		NOT NULL,
    UPDATED_AT		VARCHAR(30)		NOT NULL,
    UPDATED_BY		VARCHAR(30)		NOT NULL
);