CREATE TABLE MEMBER (
                        memberId BIGINT NOT NULL AUTO_INCREMENT,
                        password VARCHAR(255) NOT NULL,
                        name VARCHAR(100) NOT NULL,
                        type VARCHAR(10) NOT NULL,
                        role INT NULL,
                        phone VARCHAR(11) NOT NULL,
                        email VARCHAR(100) NULL,
                        fileMaxSize DECIMAL(10,5) NOT NULL,
                        regId BIGINT NOT NULL,
                        regDt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        modiId BIGINT NULL,
                        modiDt DATETIME NULL,
                        PRIMARY KEY (memberId)
);

CREATE TABLE ALIM (
                      alimSeq BIGINT NOT NULL AUTO_INCREMENT,
                      memberId BIGINT NOT NULL,
                      title VARCHAR(255) NOT NULL,
                      sendDt VARCHAR(20) NOT NULL,
                      content VARCHAR(2000) NULL,
                      sendCd CHAR(1) NOT NULL DEFAULT 'N',
                      regId BIGINT NULL,
                      regDt DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
                      modiId BIGINT NULL,
                      modiDt DATETIME NULL,
                      PRIMARY KEY (alimSeq, memberId),
                      FOREIGN KEY (memberId) REFERENCES MEMBER(memberId)
);

CREATE TABLE SEND (
                      sendSeq BIGINT NOT NULL AUTO_INCREMENT,
                      alimSeq BIGINT NOT NULL,
                      memberId BIGINT NOT NULL,
                      sendStusMsg VARCHAR(2000) NOT NULL,
                      regId BIGINT NOT NULL,
                      regDt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      modiId BIGINT NULL,
                      modiDt DATETIME NULL,
                      PRIMARY KEY (sendSeq, alimSeq, memberId),
                      FOREIGN KEY (alimSeq) REFERENCES Alim(alimSeq),
                      FOREIGN KEY (memberId) REFERENCES Alim(memberId)
);

CREATE TABLE FILE (
                      fileSeq BIGINT NOT NULL AUTO_INCREMENT,
                      memberId BIGINT NOT NULL,
                      folderSeq BIGINT NOT NULL,
                      fileOrgNm VARCHAR(255) NOT NULL,
                      Field VARCHAR(255) NOT NULL,
                      path VARCHAR(255) NOT NULL,
                      fileSize DECIMAL(10,5) NOT NULL,
                      regId BIGINT NOT NULL,
                      regDt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      modiId BIGINT NULL,
                      modiDt DATETIME NULL,
                      PRIMARY KEY (fileSeq, memberId),
                      FOREIGN KEY (memberId) REFERENCES MEMBER(memberId)
);

CREATE TABLE FOLDER (
                        folderSeq BIGINT NOT NULL AUTO_INCREMENT,
                        fileSeq BIGINT NOT NULL,
                        memberId BIGINT NOT NULL,
                        folderNm VARCHAR(100) NOT NULL,
                        depthNo INT NOT NULL,
                        priorFolderSeq INT NULL,
                        regId BIGINT NOT NULL,
                        regDt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        modiId BIGINT NULL,
                        modiDt DATETIME NULL,
                        PRIMARY KEY (folderSeq, fileSeq, memberId),
                        FOREIGN KEY (fileSeq) REFERENCES File(fileSeq),
                        FOREIGN KEY (memberId) REFERENCES File(memberId)
);
