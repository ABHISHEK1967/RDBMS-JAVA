--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (`id` INT, 
`name` TEXT, 
`email` TEXT,
PRIMARY KEY (`id`));

--
-- Dumping data for table `student`
--

INSERT INTO `student` VALUES (2, 'arjun', 'aml@gmail.com');