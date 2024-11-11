DROP DATABASE IF EXISTS ssafy_housework;
CREATE DATABASE ssafy_housework;

USE ssafy_housework;

CREATE TABLE families
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE users
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    family_id INT NOT NULL,
    name      VARCHAR(50) NOT NULL,
    email     VARCHAR(50) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    profile_image_url TEXT,
    is_admin BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (family_id) REFERENCES families (id)
);

CREATE TABLE family_members
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    user_id   INT          NOT NULL,
    family_id INT          NOT NULL,
    role      VARCHAR(50) NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (family_id) REFERENCES families (id)
);

CREATE TABLE housework_categories
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    family_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE houseworks
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    family_id INT NOT NULL,
    housework_category_id INT COMMENT "nullable if not specified",
    name     VARCHAR(255) NOT NULL,
    calorie_amount INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (family_id) REFERENCES families (id),
    FOREIGN KEY (housework_category_id) REFERENCES housework_categories (id)
);

CREATE TABLE tasks
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    family_id        INT      NOT NULL,
    housework_id     INT      NOT NULL,
    name             VARCHAR(255),
    description      TEXT,
    due_at           DATETIME NOT NULL,
    done_at   DATETIME COMMENT "if null, not done",
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (family_id) REFERENCES families (id),
    FOREIGN KEY (housework_id) REFERENCES houseworks (id)
);

CREATE TABLE assignments
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    family_member_id   INT NOT NULL,
    task_id   INT NOT NULL,
    calorie_burned INT NOT NULL COMMENT "FROM housework",
    is_active BOOLEAN DEFAULT TRUE COMMENT "if false, soft deleted",
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES tasks (id),
    FOREIGN KEY (family_member_id) REFERENCES families (id)
)