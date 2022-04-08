--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;

CREATE TABLE `courses` (`courseId` INT, 
`courseName` TEXT, 
`id` INT,
PRIMARY KEY (`courseId`),
CONSTRAINT `idforeignKey` FOREIGN KEY (`id`) REFERENCES `students` (`id`));

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` VALUES (1, 'physics', 1), (2, 'chemistry', 1), (3, 'maths', 1), (4, 'biology', 1);