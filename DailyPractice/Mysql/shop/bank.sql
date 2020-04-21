
# 银行转账

# 创建数据库
CREATE DATABASE bank;
SHOW CREATE TABLE bankAccount;
#创建用户表
CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `uId` VARCHAR(18) DEFAULT NULL COMMENT '身份证号',
  `uName` VARCHAR(20) DEFAULT NULL COMMENT '姓名',
  `age` INT(5) DEFAULT NULL COMMENT '年龄',
  `gender` INT(1) DEFAULT '0' COMMENT '性别 0男  1女',
  `address` VARCHAR(100) DEFAULT NULL COMMENT '家庭住址',
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

# 创建银行账户表
CREATE TABLE `bankAccount` (
 CREATE TABLE `bankAccount` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` VARCHAR(18) DEFAULT NULL COMMENT '用户名',
  `password` VARCHAR(18) DEFAULT NULL COMMENT '密码',
  `uid` VARCHAR(18) DEFAULT NULL COMMENT '身份证号',
  `balance` DOUBLE(20,2) DEFAULT NULL COMMENT '余额',
  `phone` VARCHAR(11) DEFAULT NULL COMMENT '手机号',
  `f_u` INT(11) DEFAULT NULL COMMENT 'user表外键',
  PRIMARY KEY (`id`),
  KEY `f_user` (`f_u`),
  CONSTRAINT `f_user` FOREIGN KEY (`f_u`) REFERENCES `user` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8

130223199210052630
130181199308226721



// 从 白银杰 1账户  向张岱 1账户转账1000元
UPDATE bankAccount SET balance= balance -? WHERE (uid=? AND `password` = ?) OR (phone=? AND `password` = ?);





