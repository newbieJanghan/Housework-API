DROP DATABASE IF EXISTS ssafy_housework;
CREATE DATABASE ssafy_housework;

USE ssafy_housework;

CREATE TABLE users
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    family_id INT,
    name      VARCHAR(50) NOT NULL,
    email     VARCHAR(50) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    profile_image_url TEXT,
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
    role      VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (family_id) REFERENCES families (id)
);

CREATE TABLE houseworks
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    category VARCHAR(255) NULL
);

CREATE TABLE tasks
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    family_id        INT      NOT NULL,
    housework_id     INT      COMMENT "not specified",
    name             VARCHAR(255),
    description      TEXT,
    due_at           DATETIME NOT NULL,
    FOREIGN KEY (family_id) REFERENCES families (id),
    FOREIGN KEY (housework_id) REFERENCES houseworks (id)
);

CREATE TABLE assignments
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    task_id   INT NOT NULL,
    family_member_id   INT COMMENT "not assigned",
    done_at   DATETIME COMMENT "not done",
    FOREIGN KEY (task_id) REFERENCES tasks (id),
    FOREIGN KEY (family_member_id) REFERENCES families (id)
)