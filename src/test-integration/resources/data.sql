DROP TABLE IF EXISTS `style`;
CREATE TABLE `style` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `style` (`name`)
);