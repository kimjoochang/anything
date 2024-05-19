CREATE TABLE ALIM (
                      ALIM_SEQ BIGINT NOT NULL AUTO_INCREMENT,
                      MEMBER_ID BIGINT NOT NULL,
                      TITLE VARCHAR(255) NOT NULL,
                      CONTENT VARCHAR(2000) NULL,
                      SEND_DAY VARCHAR(10) NOT NULL,
                      SEND_TIME VARCHAR(10) NOT NULL,
                      SEND_CD CHAR(1) NOT NULL DEFAULT 'N',
                      REG_ID BIGINT NOT NULL,
                      REG_DT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      MODI_ID BIGINT NULL,
                      MODI_DT DATETIME NULL,
                      PRIMARY KEY (ALIM_SEQ, MEMBER_ID),
                      FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER(MEMBER_ID)
);

CREATE TABLE NOTEPAD (
                         NOTEPAD_SEQ BIGINT NOT NULL AUTO_INCREMENT,
                         MEMBER_ID BIGINT NOT NULL,
                         TITLE VARCHAR(255) NOT NULL,
                         CONTENT VARCHAR(2000) NULL,
                         SEND_YN CHAR(1) NOT NULL,
                         SEND_DT DATETIME NOT NULL,
                         SEND_CD CHAR(1) NOT NULL DEFAULT 'N',
                         REG_ID BIGINT NOT NULL,
                         REG_DT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         MODI_ID BIGINT NULL,
                         MODI_DT DATETIME NULL,
                         PRIMARY KEY (NOTEPAD_SEQ, MEMBER_ID),
                         FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER(MEMBER_ID)
);

CREATE TABLE MEMBER (
                        MEMBER_ID BIGINT NOT NULL,
                        NICKNAME VARCHAR(100) NOT NULL,
                        TYPE VARCHAR(10) NOT NULL,
                        ROLE INT NULL,
                        EMAIL VARCHAR(100) NULL,
                        FILE_MAX_SIZE DECIMAL(10,5) NOT NULL,
                        ACCESS_TOKEN VARCHAR(100) NULL,
                        REFRESH_TOKEN VARCHAR(100) NULL,
                        REG_DT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        LOGIN_DT DATETIME NULL,
                        PRIMARY KEY (MEMBER_ID)
);

CREATE TABLE SEND (
                      SEND_SEQ BIGINT NOT NULL AUTO_INCREMENT,
                      CONTENT_TYPE VARCHAR(50) NOT NULL,
                      CONTENT_SEQ BIGINT NOT NULL,
                      MEMBER_ID BIGINT NOT NULL,
                      SEND_STUS_MSG VARCHAR(2000) NOT NULL,
                      REG_ID BIGINT NOT NULL,
                      REG_DT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      MODI_ID BIGINT NULL,
                      MODI_DT DATETIME NULL,
                      PRIMARY KEY (SEND_SEQ, CONTENT_TYPE, CONTENT_SEQ, MEMBER_ID),
                      FOREIGN KEY (MEMBER_ID) REFERENCES Alim(MEMBER_ID)
);

CREATE TABLE FILE (
                      FILE_SEQ BIGINT NOT NULL AUTO_INCREMENT,
                      MEMBER_ID BIGINT NOT NULL,
                      FOLDER_SEQ BIGINT NOT NULL,
                      FILE_ORG_NM VARCHAR(255) NOT NULL,
                      FIELD VARCHAR(255) NOT NULL,
                      PATH VARCHAR(255) NOT NULL,
                      FILE_SIZE DECIMAL(10,5) NOT NULL,
                      REG_ID BIGINT NOT NULL,
                      REG_DT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      MODI_ID BIGINT NULL,
                      MODI_DT DATETIME NULL,
                      PRIMARY KEY (FILE_SEQ, MEMBER_ID),
                      FOREIGN KEY (MEMBER_ID) REFERENCES MEMBER(MEMBER_ID)
);

CREATE TABLE FOLDER (
                        FOLDER_SEQ BIGINT NOT NULL AUTO_INCREMENT,
                        FILE_SEQ BIGINT NOT NULL,
                        MEMBER_ID BIGINT NOT NULL,
                        FOLDER_NM VARCHAR(100) NOT NULL,
                        DEPTH_NO INT NOT NULL,
                        PRIOR_FOLDER_SEQ INT NULL,
                        REG_ID BIGINT NOT NULL,
                        REG_DT DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        MODI_ID BIGINT NULL,
                        MODI_DT DATETIME NULL,
                        PRIMARY KEY (FOLDER_SEQ, FILE_SEQ, MEMBER_ID),
                        FOREIGN KEY (FILE_SEQ) REFERENCES File(FILE_SEQ),
                        FOREIGN KEY (MEMBER_ID) REFERENCES File(MEMBER_ID)
);
