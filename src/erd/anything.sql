CREATE TABLE `MEMBER` (
	`memberId`	number	NOT NULL	DEFAULT AUTO_INCREMENT,
	`password`	varchar(255)	NOT NULL,
	`name`	varchar(100)	NOT NULL,
	`type`	varchar(10)	NOT NULL,
	`role`	int(11)	NULL,
	`phone`	varchar(11)	NOT NULL,
	`email`	varchar(100)	NULL,
	`regId`	bigint(20)	NOT NULL,
	`regDt`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`modiId`	bigint(20)	NULL,
	`modiDt`	datetime	NULL,
	`fileMaxSize`	decimal(10,5)	NOT NULL
);

CREATE TABLE `Alim` (
	`alimSeq`	bigint(20)	NOT NULL	DEFAULT AUTO_INCREMENT,
	`memberId`	bigint(20)	NOT NULL,
	`title`	varchar(255)	NOT NULL,
	`sendDt`	varchar(20)	NOT NULL,
	`content`	varchar(2000)	NULL,
	`sendCd`	char(1)	NOT NULL	DEFAULT N,
	`regId`	bigint(20)	NULL,
	`regDt`	datetime	NULL	DEFAULT CURRENT_TIMESTAMP,
	`modiId`	bigint(20)	NULL,
	`modiDt`	datetime	NULL
);

CREATE TABLE `send` (
	`sendSeq`	bigint(20)	NOT NULL	DEFAULT AUTO_INCREMENT,
	`alimSeq`	bigint(20)	NOT NULL,
	`memberId`	bigint(20)	NOT NULL,
	`sendStusMsg`	varchar(2000)	NOT NULL,
	`regId`	bigint(20)	NOT NULL,
	`regDt`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`modiId`	bigint(20)	NULL,
	`modiDt`	datetime	NULL
);

CREATE TABLE `File` (
	`fileSeq`	bigint(20)	NOT NULL	DEFAULT AUTO_INCREMENT,
	`memberId`	bigint(20)	NOT NULL,
	`folderSeq`	bigint(20)	NOT NULL,
	`fileOrgNm`	varchar(255)	NOT NULL,
	`Field`	varchar(255)	NOT NULL,
	`path`	varchar(255)	NOT NULL,
	`fileSize`	decimal(10,5)	NOT NULL,
	`regId`	bigint(20)	NOT NULL,
	`regDt`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`modiId`	bigint(20)	NULL,
	`modiDt`	datetime	NULL
);

CREATE TABLE `folder` (
	`folderSeq`	bigint(20)	NOT NULL	DEFAULT AUTO_INCREMENT,
	`fileSeq`	bigint(20)	NOT NULL,
	`memberId`	bigint(20)	NOT NULL,
	`folderNm`	varchar(100)	NOT NULL,
	`depthNo`	int(11)	NOT NULL,
	`priorFolderSeq`	int(11)	NULL,
	`regId`	bigint(20)	NOT NULL,
	`regDt`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`modiId`	bigint(20)	NULL,
	`modiDt`	datetime	NULL
);

ALTER TABLE `MEMBER` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
	`memberId`
);

ALTER TABLE `Alim` ADD CONSTRAINT `PK_ALIM` PRIMARY KEY (
	`alimSeq`,
	`memberId`
);

ALTER TABLE `send` ADD CONSTRAINT `PK_SEND` PRIMARY KEY (
	`sendSeq`,
	`alimSeq`,
	`memberId`
);

ALTER TABLE `File` ADD CONSTRAINT `PK_FILE` PRIMARY KEY (
	`fileSeq`,
	`memberId`
);

ALTER TABLE `folder` ADD CONSTRAINT `PK_FOLDER` PRIMARY KEY (
	`folderSeq`,
	`fileSeq`,
	`memberId`
);

ALTER TABLE `Alim` ADD CONSTRAINT `FK_MEMBER_TO_Alim_1` FOREIGN KEY (
	`memberId`
)
REFERENCES `MEMBER` (
	`memberId`
);

ALTER TABLE `send` ADD CONSTRAINT `FK_Alim_TO_send_1` FOREIGN KEY (
	`alimSeq`
)
REFERENCES `Alim` (
	`alimSeq`
);

ALTER TABLE `send` ADD CONSTRAINT `FK_Alim_TO_send_2` FOREIGN KEY (
	`memberId`
)
REFERENCES `Alim` (
	`memberId`
);

ALTER TABLE `File` ADD CONSTRAINT `FK_MEMBER_TO_File_1` FOREIGN KEY (
	`memberId`
)
REFERENCES `MEMBER` (
	`memberId`
);

ALTER TABLE `folder` ADD CONSTRAINT `FK_File_TO_folder_1` FOREIGN KEY (
	`fileSeq`
)
REFERENCES `File` (
	`fileSeq`
);

ALTER TABLE `folder` ADD CONSTRAINT `FK_File_TO_folder_2` FOREIGN KEY (
	`memberId`
)
REFERENCES `File` (
	`memberId`
);

