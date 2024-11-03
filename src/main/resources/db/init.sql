DROP
DATABASE IF EXISTS ssafy_housework;
CREATE
DATABASE ssafy_housework;

USE
ssafy_housework;

CREATE TABLE users
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    family_id INT,
    FOREIGN KEY (family_id) REFERENCES families (id)
);

CREATE TABLE families
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE family_members
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    user_id   INT          NOT NULL,
    family_id INT          NOT NULL,
    role      VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (family_id) REFERENCES families (id)
);

CREATE TABLE houseworks
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL
);

CREATE TABLE tasks
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    family_id        INT      NOT NULL,
    family_member_id INT,
    housework_id     INT      NOT NULL,
    due_at           DATETIME NOT NULL,
    done_at          DATETIME,
    FOREIGN KEY (family_id) REFERENCES families (id),
    FOREIGN KEY (family_member_id) REFERENCES family_members (id),
    FOREIGN KEY (housework_id) REFERENCES houseworks (id)
);