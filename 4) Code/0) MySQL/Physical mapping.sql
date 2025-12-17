
/*
* Physical Mapping of the ROGEX Data Base
* Description: This physical mapping defines the structure of the ROGEX database
* in MySQL, including tables, columns, data types, primary keys, and foreign key
* s, also includes indexing strategies for performance optimization, some initial
* data insertion for testing purposes.
* Author: YmCris
* Date: 2025-12-8
* Conventions: snake_case for tables and columns; singular for table names
* See: diagram table and entity relationship diagram for reference.
*/

-- 1) Define the entities and their corresponding tables (Standardized)
/*********************************** SYSTEM ***********************************/
CREATE TABLE system_configuration (-- (1FN, 2FN, 3FN) Singleton
    global_commission_percentage DECIMAL(5,2) NOT NULL,
    description TEXT NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_system_configuration PRIMARY KEY (id)
);

CREATE TABLE system_administrator(-- (1FN, 2FN, 3FN)
    email VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_system_administrator PRIMARY KEY (email)
);

CREATE TABLE main_banner(-- (1FN, 2FN, 3FN)
    multimedia BLOB NOT NULL,
    is_image BOOLEAN NOT NULL,
    link VARCHAR(255) NOT NULL,
    CONSTRAINT pk_main_banner PRIMARY KEY (link)
);

/********************************* ENTERPRISE *********************************/
CREATE TABLE enterprise (-- (1FN, 2FN, 3FN)
    name VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    specific_commission DECIMAL(5,2),
    logo BLOB,
    cover BLOB,
    hidden_all_comments BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT pk_enterprise PRIMARY KEY (name) 
);
/*
CREATE TABLE enterprise_specific_commission(-- (1FN, 2FN, 3FN)
    specific_commission DECIMAL(5,2) NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    CONSTRAINT pk_enterprise_specific_commission PRIMARY KEY (enterprise_name),
    CONSTRAINT fk_enterprise_specific_commission_enterprise FOREIGN KEY (enterprise_name) REFERENCES enterprise(name)
);
*/

CREATE TABLE enterprise_user (-- (1FN, 2FN, 3FN)
    email VARCHAR(100) NOT NULL,
    name VARCHAR(150) NOT NULL,
    password VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    CONSTRAINT pk_enterprise_user PRIMARY KEY (email),
    CONSTRAINT fk_enterprise_user_enterprise FOREIGN KEY (enterprise_name) REFERENCES enterprise(name)
);

/********************************* VIDEOGAMES *********************************/
CREATE TABLE category ( -- (1FN, 2FN, 3FN)
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (name)
);

CREATE TABLE videogame (-- (1FN, 2FN, 3FN)
    title VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    minimum_requirements TEXT NOT NULL,
    age_rating ENUM('E', 'T', 'M') NOT NULL,
    release_date DATE NOT NULL,
    downloads INT NOT NULL DEFAULT 0,
    enterprise_name VARCHAR(150) NOT NULL,
    suspension_of_sale BOOLEAN NOT NULL DEFAULT FALSE,
    hidden_comments BOOLEAN NOT NULL DEFAULT FALSE,
    hidden BOOLEAN NOT NULL DEFAULT FALSE,-- Extra field to hide the game from the store
    CONSTRAINT pk_videogame PRIMARY KEY (title, enterprise_name),
    CONSTRAINT fk_videogame_enterprise FOREIGN KEY (enterprise_name) REFERENCES enterprise(name)
);
   
CREATE TABLE videogame_multimedia (-- (1FN, 2FN, 3FN)
    multimedia BLOB,
    is_image BOOLEAN NOT NULL DEFAULT TRUE,
    videogame_title VARCHAR(150) NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_videogame_multimedia PRIMARY KEY (id),
    CONSTRAINT fk_videogame_multimedia_videogame FOREIGN KEY (videogame_title, enterprise_name) REFERENCES videogame (title, enterprise_name)
);

CREATE TABLE videogame_category (-- ()
    category_name VARCHAR(50) NOT NULL,
    videogame_title VARCHAR(150) NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    CONSTRAINT pk_videogame_category PRIMARY KEY (category_name, videogame_title, enterprise_name),
    CONSTRAINT fk_videogame_category_category FOREIGN KEY (category_name) REFERENCES category(name),
    CONSTRAINT fk_videogame_category_videogame FOREIGN KEY (videogame_title, enterprise_name) REFERENCES videogame(title, enterprise_name)
);

CREATE TABLE videogame_comment ( -- (1FN, 2FN, )
    videogame_title VARCHAR(150) NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    user_email VARCHAR(50) NOT NULL,
    comment_text TEXT NOT NULL,
    comment_date DATETIME NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    parent_comment_id INT,
    CONSTRAINT pk_videogame_comment PRIMARY KEY (id),
    CONSTRAINT fk_videogame_comment_videogame FOREIGN KEY (videogame_title, enterprise_name) REFERENCES videogame(title, enterprise_name),
    CONSTRAINT fk_videogame_comment_user FOREIGN KEY (user_email) REFERENCES user(email)
);
/*
CREATE TABLE comment_comment_response(-- (1FN, 2FN, 3FN)???????? XXXX
    response_text TEXT NOT NULL,
    response_date DATETIME NOT NULL,
    videogame_title VARCHAR(150) NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    user_email VARCHAR(50) NOT NULL,
    original_comment_id TEXT NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_comment_comment_response PRIMARY KEY (id),
    CONSTRAINT fk_comment_comment_response_videogame FOREIGN KEY (videogame_title, enterprise_name) REFERENCES videogame(title, enterprise_name),
    CONSTRAINT fk_comment_comment_response_user FOREIGN KEY (user_email) REFERENCES user(email),
    CONSTRAINT fk_comment_comment_response_original_comment FOREIGN KEY (original_comment_id) REFERENCES videogame_comment(id)
);
*/
CREATE TABLE videogame_rating( -- (1FN, 2FN, 3FN)
    videogame_title VARCHAR(150) NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    user_email VARCHAR(50) NOT NULL,
    rating INT NOT NULL,
    CONSTRAINT pk_videogame_rating PRIMARY KEY (videogame_title, enterprise_name, user_email),
    CONSTRAINT fk_videogame_rating_videogame FOREIGN KEY (videogame_title, enterprise_name) REFERENCES videogame(title, enterprise_name),
    CONSTRAINT fk_videogame_rating_user FOREIGN KEY (user_email) REFERENCES user(email)
);

/************************************ USERS ***********************************/
CREATE TABLE user (-- (1FN, 2FN, 3FN)
    photo BLOB,
    nickname VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    country VARCHAR(100) NOT NULL,
    public_library BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_user PRIMARY KEY (email),
    CONSTRAINT uq_user_email UNIQUE (nickname)
);

CREATE TABLE message (-- (1FN, 2FN, 3FN)
    message_text TEXT NOT NULL,
    sent_date DATETIME NOT NULL,
    multimedia BLOB,
    sender_email VARCHAR(100) NOT NULL,
    receiver_email VARCHAR(100) NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_message PRIMARY KEY (id),
    CONSTRAINT fk_message_sender FOREIGN KEY (sender_email) REFERENCES user(email),
    CONSTRAINT fk_message_receiver FOREIGN KEY (receiver_email) REFERENCES user(email)
);

CREATE TABLE wallet (-- (1FN, 2FN, 3FN)
    banck ENUM('BANRURAL', 'BI', 'AZTECA','PROMERICA','G&T','BANTRAB', 'BAC') NOT NULL,
    user_email VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    fund DECIMAL(10,2) NOT NULL,
    CONSTRAINT pk_wallet PRIMARY KEY (name, banck),
    CONSTRAINT fk_wallet_user FOREIGN KEY (user_email) REFERENCES user(email)
);

CREATE TABLE wallet_transaction (-- (1FN, 2FN, )
    transaction_date DATETIME NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    wallet_name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    wallet_banck ENUM('BANRURAL', 'BI', 'AZTECA','PROMERICA','G&T','BANTRAB', 'BAC') NOT NULL,
    user_email VARCHAR(50) NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_wallet_transaction PRIMARY KEY (id),
    CONSTRAINT fk_wallet_transaction_wallet FOREIGN KEY (wallet_name, wallet_banck) REFERENCES wallet(name, banck),
    CONSTRAINT fk_wallet_transaction_user FOREIGN KEY (user_email) REFERENCES user(email)
);

CREATE TABLE sale ( -- Include commission details ( 1FN, )
    videogame_price DECIMAL(10,2) NOT NULL,
    sale_date DATETIME NOT NULL,
    commission_percentage DECIMAL(5,2) NOT NULL,
    profit DECIMAL(10,2) NOT NULL,
    user_email VARCHAR(50) NOT NULL,
    videogame_title VARCHAR(150) NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    CONSTRAINT pk_sale PRIMARY KEY (user_email, videogame_title, enterprise_name),
    CONSTRAINT fk_sale_user FOREIGN KEY (user_email) REFERENCES user(email),
    CONSTRAINT fk_sale_videogame FOREIGN KEY (videogame_title, enterprise_name) REFERENCES videogame(title, enterprise_name)
);

CREATE TABLE videogame_user (-- (1FN, 2FN, 3FN)
    instaled BOOLEAN NOT NULL DEFAULT FALSE,
    user_email VARCHAR(50) NOT NULL,
    videogame_title VARCHAR(150) NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    CONSTRAINT pk_videogame_user PRIMARY KEY (user_email, videogame_title, enterprise_name),
    CONSTRAINT fk_videogame_user_user FOREIGN KEY (user_email) REFERENCES user(email),
    CONSTRAINT fk_videogame_user_videogame FOREIGN KEY (videogame_title, enterprise_name) REFERENCES videogame(title, enterprise_name)
);

CREATE TABLE videogame_installation (-- (1FN, 2FN, 3FN)
    videogame_installation_date DATETIME NOT NULL,
    videogame_desinstallation_date DATETIME,
    user_email VARCHAR(50) NOT NULL,
    videogame_title VARCHAR(150) NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_videogame_installation PRIMARY KEY (id),
    CONSTRAINT fk_videogame_installation_user FOREIGN KEY (user_email) REFERENCES user(email),
    CONSTRAINT fk_videogame_installation_videogame FOREIGN KEY (videogame_title, enterprise_name) REFERENCES videogame(title, enterprise_name)
);

/******************************* FAMILY GROUPS ********************************/
CREATE TABLE family_group (-- (1FN, 2FN, 3FN)
    name VARCHAR(100) NOT NULL,
    members_limit INT NOT NULL DEFAULT 6,
    CONSTRAINT pk_family_group PRIMARY KEY (name)
);

CREATE TABLE group_member (-- (1FN, 2FN, 3FN)
    group_member_email VARCHAR(50) NOT NULL,
    family_group_name VARCHAR(100) NOT NULL,
    CONSTRAINT pk_group_member PRIMARY KEY (group_member_email, family_group_name),
    CONSTRAINT fk_group_member_family_group FOREIGN KEY (family_group_name) REFERENCES family_group(name),
    CONSTRAINT fk_group_member_user FOREIGN KEY (group_member_email) REFERENCES user(email)
);

CREATE TABLE videogame_loan (-- (1FN, ...)
    propietor_email VARCHAR(100) NOT NULL,
    lender_email VARCHAR(100) NOT NULL,
    videogame_title VARCHAR(150) NOT NULL,
    enterprise_name VARCHAR(150) NOT NULL,
    family_group_name VARCHAR(100) NOT NULL,
    loan_date DATETIME NOT NULL,
    return_date DATETIME,
    id INT NOT NULL AUTO_INCREMENT,
    CONSTRAINT pk_videogame_loan PRIMARY KEY (id),
    CONSTRAINT fk_videogame_loan_propietor FOREIGN KEY (propietor_email) REFERENCES user(email),
    CONSTRAINT fk_videogame_loan_lender FOREIGN KEY (lender_email) REFERENCES user(email),
    CONSTRAINT fk_videogame_loan_videogame FOREIGN KEY (videogame_title, enterprise_name) REFERENCES videogame(title, enterprise_name),
    CONSTRAINT fk_videogame_loan_family_group FOREIGN KEY (family_group_name) REFERENCES family_group(name)
);

-- 2) Adding initial data for testing purposes