--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;

CREATE TABLE `students` (`id` INT, 
`name` TEXT, 
`email` TEXT,
PRIMARY KEY (`id`));

--
-- Dumping data for table `students`
--

INSERT INTO `students` VALUES (1, 'kannan', 'smiley@gmail.com'), (2, 'abhi', 'smiley@gmail.com'), (3, 'karthik', 'karthik@gmail.com'), (1, 'abhi', 'abc@gmail.com');