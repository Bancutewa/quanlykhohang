ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'bancutewa10304';
FLUSH PRIVILEGES;
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
                      PRIMARY KEY(ID)
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
INSERT INTO users (USER_NAME, PASSWORD, EMAIL, NAME)
VALUES ('admin', 'admin', 'admin', 'admin');

INSERT INTO menu (parent_id, url, name, order_index) VALUES
                                                         (0, '/product', 'Sản phẩm', 1),
                                                         (0, '/stock', 'Kho', 2),
                                                         (0, '/management', 'Quản lý', 3),
                                                         (1, '/product-info/list', 'Danh sách sản phẩm', 2),
                                                         (1, '/category/list', 'Danh sách category', 1),
                                                         (1, '/category/edit', 'Sửa', -1),
                                                         (1, '/category/view', 'Xem', -1),
                                                         (1, '/category/add', 'Thêm mới', -1),
                                                         (1, '/category/save', 'Lưu', -1),
                                                         (1, '/category/delete', 'Xoá', -1),
                                                         (2, '/goods-recept/list', 'Danh sách nhập kho', 1),
                                                         (2, '/goods-issue/list', 'Danh sách xuất kho', 2),
                                                         (2, '/product-in-stock/list', 'Sản phẩm trong kho', 3),
                                                         (2, '/history', 'Lịch sử kho', 4),
                                                         (3, '/user/list', 'Danh sách user', 1),
                                                         (3, '/menu/list', 'Danh sách menu', 1),
                                                         (3, '/role/list', 'Danh sách quyển', 1);

insert into role(ROLE_NAME, DESCRIPTION) value 	('admin', 'Admin of system'),
    ('staff','Staff of system');
INSERT INTO auth (ROLE_ID, MENU_ID, PERMISSION) VALUES (1,1,1), (1,2,1), (1,3,1),(1,4,1), (1,5,1), (1,6,1), (1,7,1), (1,8,1), (1,9,1), (1,10,1), (1,11,1), (1,12,1), (1,13,1), (1,14,1), (1,15,1), (1,16,1), (1,17,1);

insert into user_role(id, user_id, role_id) values (1,1,1);