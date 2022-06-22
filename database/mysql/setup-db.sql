-- Database creation

CREATE DATABASE IF NOT EXISTS user_db;
CREATE DATABASE IF NOT EXISTS drone_db;

GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;

-- User Management tables

USE user_db;

CREATE TABLE personal_info (
    id VARCHAR(255) NOT NULL,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(64) NOT NULL,
    birth_date DATE,
    phone_number VARCHAR(32),
    address VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE account (
    id INT AUTO_INCREMENT NOT NULL,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    status VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE account ADD CONSTRAINT FK_PersonalInfoAccount FOREIGN KEY (email) REFERENCES personal_info(id);
ALTER TABLE account ADD CONSTRAINT UC_AccountUsername UNIQUE(username);

-- Drone Management tables

USE drone_db;

CREATE TABLE drone (
    id INT AUTO_INCREMENT NOT NULL,
    model VARCHAR(64) NOT NULL,
    color VARCHAR(64) NOT NULL,
    status VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE model (
    id VARCHAR(255) NOT NULL,
    weight INT NOT NULL, 
    height INT NOT NULL,
    width INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE drone ADD CONSTRAINT FK_DroneModel FOREIGN KEY (model) REFERENCES model(id);
ALTER TABLE model ADD CONSTRAINT weight_greater_than_zero CHECK (weight > 0);
ALTER TABLE model ADD CONSTRAINT height_greater_than_zero CHECK (height > 0);
ALTER TABLE model ADD CONSTRAINT width_greater_than_zero CHECK (width > 0);

-- Setup User Management data

USE user_db;

INSERT INTO personal_info (first_name, last_name, birth_date, id, phone_number, address) 
    VALUES("Waverly", "Jones", '1970-02-06', "waverly.jones@email.com", "1747542670", "196 Bay Street Sykesville, MD 21784");
INSERT INTO personal_info (first_name, last_name, birth_date, id, phone_number, address)
    VALUES("Merritt", "Mclean", '1979-12-24', "merritt.mclean@email.com", "0867789093", "422 South Division St. Mchenry, IL 60050");
INSERT INTO personal_info (first_name, last_name, birth_date, id, phone_number, address)
    VALUES("Budd", "Heath", '1983-01-31', "budd.heath@email.com", "5532001455", "21 South Cypress Dr. South Ozone Park, NY 11420");
INSERT INTO personal_info (first_name, last_name, birth_date, id, phone_number, address)
    VALUES("Dana", "Harris", '1984-04-04', "dana.harris@email.com", "7353801376", "275 Pilgrim Ave. Billerica, MA 01821");
INSERT INTO personal_info (first_name, last_name, birth_date, id, phone_number, address)
    VALUES("Nellie", "Miles", '1988-03-12', "nellie.miles@email.com", "7430601668", "8305 Ridgeview Street Camas, WA 98607");

INSERT INTO account (email, username, password, status) VALUES("waverly.jones@email.com", "wjones", "wjones", "ACTIVE");
INSERT INTO account (email, username, password, status) VALUES("merritt.mclean@email.com", "mmclean", "mmclean", "ACTIVE");
INSERT INTO account (email, username, password, status) VALUES("budd.heath@email.com", "bheath", "bheath", "ACTIVE");
INSERT INTO account (email, username, password, status) VALUES("dana.harris@email.com", "dharris", "dharris", "ACTIVE");
INSERT INTO account (email, username, password, status) VALUES("nellie.miles@email.com", "nmiles", "nmiles", "ACTIVE");

-- Setup Drone Management data

USE drone_db;

INSERT INTO model (id, weight, height, width) VALUES("DJI Air 2S", 1500, 35, 40);
INSERT INTO model (id, weight, height, width) VALUES("DJI Mini 2", 2000, 40, 38);
INSERT INTO model (id, weight, height, width) VALUES("DJI Mavic 2 Pro", 2200, 40, 40);
INSERT INTO model (id, weight, height, width) VALUES("Parrot Anafi", 1200, 25, 30);
INSERT INTO model (id, weight, height, width) VALUES("3DR Solo", 1700, 35, 45);

INSERT INTO drone (model, color, status) VALUES("DJI Air 2S", "WHITE", "IDLE");
INSERT INTO drone (model, color, status) VALUES("DJI Air 2S", "BLUE", "IDLE");
INSERT INTO drone (model, color, status) VALUES("DJI Mini 2", "GREEN", "IDLE");
INSERT INTO drone (model, color, status) VALUES("DJI Mini 2", "RED", "IDLE");
INSERT INTO drone (model, color, status) VALUES("DJI Mavic 2 Pro", "ORANGE", "IDLE");
INSERT INTO drone (model, color, status) VALUES("DJI Mavic 2 Pro", "YELLOW", "IDLE");
INSERT INTO drone (model, color, status) VALUES("Parrot Anafi", "PURPLE", "IDLE");
INSERT INTO drone (model, color, status) VALUES("Parrot Anafi", "BLACK", "IDLE");
INSERT INTO drone (model, color, status) VALUES("3DR Solo", "GREY", "IDLE");
INSERT INTO drone (model, color, status) VALUES("3DR Solo", "GREY", "IDLE");