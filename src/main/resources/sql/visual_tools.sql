# 连接信息表
CREATE TABLE `connect_info` (
  `id` int(11) NOT NULL COMMENT '自增ID',
  `name` varchar(255) DEFAULT NULL COMMENT '连接名称',
  `info` varchar(255) NOT NULL COMMENT '连接信息',
  `type` int(11) DEFAULT NULL COMMENT '连接类型，1：Zookeeper连接，2：redis连接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;