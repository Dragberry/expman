CREATE TABLE CUSTOMER (
	CUSTOMER_KEY BIGINT AUTO_INCREMENT NOT NULL,
	CUSTOMER_NAME VARCHAR(63),
	FIRST_NAME VARCHAR(63),
	LAST_NAME VARCHAR(63),
	BIRTHDATE DATE,
	EMAIL VARCHAR(255),
    ENABLED BIT,
	PASSWORD VARCHAR(80),
	PRIMARY KEY (CUSTOMER_KEY)
) ENGINE=INNODB;

CREATE TABLE ROLE (
	ROLE_KEY BIGINT AUTO_INCREMENT NOT NULL,
	ROLE_NAME VARCHAR(64),
	PRIMARY KEY (ROLE_KEY)
) ENGINE=INNODB;

CREATE TABLE CUSTOMER_ROLE (
	CUSTOMER_KEY BIGINT NOT NULL,
	ROLE_KEY BIGINT NOT NULL,
	CONSTRAINT FK_CUSTOMER_ROLE FOREIGN KEY (CUSTOMER_KEY) REFERENCES CUSTOMER (CUSTOMER_KEY),
	CONSTRAINT FK_ROLE_CUSTOMER FOREIGN KEY (ROLE_KEY) REFERENCES ROLE (ROLE_KEY)
) ENGINE=INNODB;

CREATE TABLE TRANSACTION_TYPE (
	TRANSACTION_TYPE_KEY BIGINT AUTO_INCREMENT NOT NULL,
	NAME VARCHAR(32) NOT NULL,
	CUSTOMER_KEY BIGINT NOT NULL,
	PARENT_KEY BIGINT,
	LEVEL TINYINT DEFAULT 0,
	PRIMARY KEY (TRANSACTION_TYPE_KEY),
	CONSTRAINT FK_TRANSACTION_TYPE_CUSTOMER FOREIGN KEY (CUSTOMER_KEY) REFERENCES CUSTOMER (CUSTOMER_KEY),
	CONSTRAINT FK_TRANSACTION_TYPE_PARENT FOREIGN KEY (PARENT_KEY) REFERENCES TRANSACTION_TYPE (TRANSACTION_TYPE_KEY)
) ENGINE=INNODB;

CREATE TABLE COUNTER_PARTY (
	COUNTER_PARTY_KEY BIGINT AUTO_INCREMENT NOT NULL,
	NAME VARCHAR(64) NOT NULL,
	CUSTOMER_KEY BIGINT NOT NULL,
	PHYSICAL BIT DEFAULT FALSE,
	PRIMARY KEY (COUNTER_PARTY_KEY),
	CONSTRAINT FK_COUNTER_PARTY_CUSTOMER FOREIGN KEY (CUSTOMER_KEY) REFERENCES CUSTOMER (CUSTOMER_KEY)
) ENGINE=INNODB;

CREATE TABLE ACCOUNT (
	ACCOUNT_KEY BIGINT AUTO_INCREMENT NOT NULL,
	NUMBER VARCHAR(64) NOT NULL,
	CURRENCY VARCHAR(3) NOT NULL,
	CUSTOMER_KEY BIGINT NOT NULL,
	TYPE INT NOT NUll,
	PRIMARY KEY (ACCOUNT_KEY),
	CONSTRAINT FK_ACCOUNT_CUSTOMER FOREIGN KEY (CUSTOMER_KEY) REFERENCES CUSTOMER (CUSTOMER_KEY)
) ENGINE=INNODB;

CREATE TABLE INSTRUCTION (
	INSTRUCTION_KEY BIGINT AUTO_INCREMENT NOT NULL,
	CLASSIFICATION VARCHAR(10) NOT NULL,
	CUSTOMER_KEY BIGINT NOT NULL,
	PRIMARY KEY (INSTRUCTION_KEY),
	CONSTRAINT FK_INSTRUCTION_CUSTOMER FOREIGN KEY (CUSTOMER_KEY) REFERENCES CUSTOMER (CUSTOMER_KEY)
) ENGINE=INNODB;

CREATE TABLE TRANSACTION (
	INSTRUCTION_KEY BIGINT AUTO_INCREMENT NOT NULL,
	CUSTOMER_KEY BIGINT NOT NULL,
	AMOUNT NUMERIC(19,2),
    PROCESSING_DATE DATETIME,
	TYPE VARCHAR(1) NOT NULL,
	CURRENCY VARCHAR(3),
	DESCRIPTION VARCHAR(255),
	ACCOUNT_KEY BIGINT NOT NULL,
	CUSTOMER_KEY BIGINT NOT NULL,
	TRANSACTION_TYPE_KEY BIGINT NOT NULL,
	COUNTER_PARTY_KEY BIGINT,
	PRIMARY KEY (TRANSACTION_KEY),
	CONSTRAINT FK_TRANSACTION_INSTRUCTION FOREIGN KEY (INSTRUCTION_KEY) REFERENCES CUSTOMER (INSTRUCTION_KEY),
	CONSTRAINT FK_TRANSACTION_ACCOUNT FOREIGN KEY (ACCOUNT_KEY) REFERENCES ACCOUNT (ACCOUNT_KEY),
	CONSTRAINT FK_TRANSACTION_TRANSACTION_TYPE FOREIGN KEY (TRANSACTION_TYPE_KEY) REFERENCES TRANSACTION_TYPE (TRANSACTION_TYPE_KEY),
	CONSTRAINT FK_COUNTER_PARTY_TYPE FOREIGN KEY (COUNTER_PARTY_KEY) REFERENCES COUNTER_PARTY (COUNTER_PARTY_KEY)
) ENGINE=INNODB;
