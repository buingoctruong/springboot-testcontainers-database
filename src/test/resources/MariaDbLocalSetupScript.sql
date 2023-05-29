
DROP TABLE IF EXISTS `celebrity`;

CREATE TABLE `celebrity` (
    `celebrityId` int(5) auto_increment PRIMARY KEY,
    `age` int(2),
    `name` varchar(255) not null
) ENGINE=InnoDB;

INSERT INTO celebrity(age, name) VALUES (51, "Elon Musk");
INSERT INTO celebrity(age, name) VALUES (39, "Mark Zuckerberg");
INSERT INTO celebrity(age, name) VALUES (59, "Jeff Bezos");
INSERT INTO celebrity(age, name) VALUES (67, "Bill Gates");
INSERT INTO celebrity(age, name) VALUES (92, "Warren Buffett");