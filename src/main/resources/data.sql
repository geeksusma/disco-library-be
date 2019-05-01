DROP TABLE IF EXISTS `style`;
CREATE TABLE `style` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `style` (`name`)
);

INSERT INTO `style` (`id`, `name`) values (1, 'rock');
INSERT INTO `style` (`id`, `name`) values (2, 'heavy');
INSERT INTO `style` (`id`, `name`) values (3, 'punk');
INSERT INTO `style` (`id`, `name`) values (4, 'hardcore');