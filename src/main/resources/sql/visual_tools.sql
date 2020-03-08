CREATE DATABASE visual_tools charset utf8;

# 连接信息表
CREATE TABLE `connect_info` (
  `id` int(11) NOT NULL COMMENT '自增ID',
  `name` varchar(255) DEFAULT NULL COMMENT '连接名称',
  `info` varchar(255) NOT NULL COMMENT '连接信息',
  `type` int(11) DEFAULT NULL COMMENT '连接类型，1：Zookeeper连接，2：redis连接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

# 操作日志表
CREATE TABLE `operator_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(11) DEFAULT NULL COMMENT '日志类型',
  `title` varchar(255) DEFAULT NULL COMMENT '日志标题',
  `method` varchar(255) DEFAULT NULL COMMENT '方法名称',
  `content` varchar(1024) DEFAULT NULL COMMENT '日志内容',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `result` varchar(255) DEFAULT NULL COMMENT '返回结果',
  `params` varchar(1024) DEFAULT NULL COMMENT '请求参数',
  `exception` varchar(1024) DEFAULT NULL COMMENT '异常信息',
  `create_time` datetime DEFAULT NULL COMMENT '操作时间',
  `cost_time` int(11) DEFAULT NULL COMMENT '执行耗时，单位毫秒',
  `user_name` varchar(255) DEFAULT NULL COMMENT '操作用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=259 DEFAULT CHARSET=utf8;