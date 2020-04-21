
# 创建数据库
SHOW CREATE DATABASE ajax;
CREATE DATABASE `ajax` ;

# 创建user表
SHOW CREATE TABLE `user`;
CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` VARCHAR(30) DEFAULT NULL COMMENT '用户名',
  `password` VARCHAR(20) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8

# 向user表中添加数据
INSERT INTO `user`(username,`password`) VALUES 
	("zhangsan","123456"),
	("lisi","lisi123"),
	("wangwu","wangwu123"),
	("sunliu","sunliu123"),
	("zhengqi","zhengqi123"),
	("ergouzi","ergouzi123"),
	("goudan","goudan123");






