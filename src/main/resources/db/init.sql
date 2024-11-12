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
    family_role VARCHAR(255),
    profile_image_name TEXT,
    calorie_goal INT DEFAULT 0,

    is_admin BOOLEAN DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (family_id) REFERENCES families (id)
);

CREATE TABLE houseworks
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    family_id INT NOT NULL,
    register_user_id INT NOT NULL,
    assigned_user_id INT NOT NULL,

    name     VARCHAR(255) NOT NULL,
    description TEXT,
    color VARCHAR(50) DEFAULT "#000000",

    start_at DATETIME NOT NULL,
    due_at  DATETIME NOT NULL,
    done_at DATETIME COMMENT "if null, not done",

    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (family_id) REFERENCES families (id),
    FOREIGN KEY (register_user_id) REFERENCES users (id),
    FOREIGN KEY (assigned_user_id) REFERENCES users (id)
);

CREATE TABLE daily_workouts
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    user_id  INT NOT NULL,
    calorie_burned  INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
)