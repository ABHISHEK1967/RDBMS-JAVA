--
-- Table structure for table `ORDERS`
--

DROP TABLE IF EXISTS `ORDERS`;

CREATE TABLE `ORDERS` (`id` INT, 
`name` TEXT, 
`personId` INT,
PRIMARY KEY (`id`),
CONSTRAINT `idforeignKey` FOREIGN KEY (`personId`) REFERENCES `Person` (`id`));

