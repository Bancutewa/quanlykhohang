-- ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'bancutewa10304';
-- FLUSH PRIVILEGES;

CREATE DATABASE inventory_management;
USE inventory_management;

CREATE TABLE users (
    ID INT(11) NOT NULL AUTO_INCREMENT,
    USER_NAME VARCHAR(50) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) DEFAULT NULL,
    NAME VARCHAR(100) NOT NULL,
    ACTIVE_FLAG INT(1) NOT NULL DEFAULT '1',
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID)
);

CREATE TABLE role (
    ID INT(11) NOT NULL AUTO_INCREMENT,
    ROLE_NAME VARCHAR(50) NOT NULL,
    DESCRIPTION VARCHAR(50) NOT NULL,
    ACTIVE_FLAG INT(1) NOT NULL DEFAULT 1,
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID)
);

CREATE TABLE user_role (
    ID INT(11) NOT NULL AUTO_INCREMENT,
    USER_ID INT(11) NOT NULL,
    ROLE_ID INT(11) NOT NULL,
    ACTIVE_FLAG INT(1) NOT NULL DEFAULT 1,
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID),
    FOREIGN KEY (USER_ID) REFERENCES users(ID) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (ROLE_ID) REFERENCES role(ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE menu (
    ID INT(11) NOT NULL AUTO_INCREMENT,
    PARENT_ID INT(11),
    URL VARCHAR(100) NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    ORDER_INDEX INT(1) NOT NULL DEFAULT '0',
    ACTIVE_FLAG INT(1) NOT NULL DEFAULT '1',
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID),
    FOREIGN KEY (PARENT_ID) REFERENCES menu(ID) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE auth (
    ID INT(11) NOT NULL AUTO_INCREMENT,
    ROLE_ID INT(11) NOT NULL,
    MENU_ID INT(11) NOT NULL,
    PERMISSION INT(1) NOT NULL DEFAULT '1',
    ACTIVE_FLAG INT(1) NOT NULL DEFAULT 1,
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID),
    FOREIGN KEY (ROLE_ID) REFERENCES role(ID) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (MENU_ID) REFERENCES menu(ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE category (
    ID INT(11) NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    CODE VARCHAR(50) NOT NULL,
    DESCRIPTION TEXT,
    ACTIVE_FLAG INT(1) NOT NULL DEFAULT '1',
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID)
);

CREATE TABLE product_info (
    ID INT(11) NOT NULL AUTO_INCREMENT,
    CATE_ID INT(11) NOT NULL,
    CODE VARCHAR(50) NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    DESCRIPTION TEXT,
    IMG_URL VARCHAR(200) NOT NULL,
    ACTIVE_FLAG INT(1) NOT NULL DEFAULT '1',
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID),
    FOREIGN KEY (CATE_ID) REFERENCES category(ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE product_in_stock (
    ID INT(11) NOT NULL AUTO_INCREMENT,
    PRODUCT_ID INT(11) NOT NULL,
    QTY INT(11) NOT NULL,
    ACTIVE_FLAG INT(1) NOT NULL DEFAULT '1',
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES product_info(ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE history (
    ID INT(11) NOT NULL AUTO_INCREMENT,
    ACTION_NAME VARCHAR(100) NOT NULL,
    TYPE INT(1) NOT NULL,
    PRODUCT_ID INT(11) NOT NULL,
    QTY INT(11) NOT NULL,
    PRICE DECIMAL(15,2) NOT NULL,
    ACTIVE_FLAG INT(1) NOT NULL DEFAULT '1',
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES product_info(ID) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE invoice (
    ID INT(11) NOT NULL AUTO_INCREMENT,
    CODE VARCHAR(50) NOT NULL,
    TYPE INT(1) NOT NULL,
    PRODUCT_ID INT(11) NOT NULL,
    QTY INT(11) NOT NULL,
    PRICE DECIMAL(15,2) NOT NULL,
    ACTIVE_FLAG INT(1) NOT NULL DEFAULT '1',
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(ID),
    FOREIGN KEY (PRODUCT_ID) REFERENCES product_info(ID) ON DELETE RESTRICT ON UPDATE CASCADE
);