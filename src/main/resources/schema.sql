CREATE TABLE IF NOT EXISTS `Person` (
    `id`         INTEGER  PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `course` VARCHAR(50) NOT NULL,
    `age`        INTEGER  NOT NULL,     --note this line has a comma in the end
    `create_at` timestamp not null
    );