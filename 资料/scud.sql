/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : scud

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2015-07-05 19:17:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(50) DEFAULT NULL,
  `user_token` varchar(50) DEFAULT NULL,
  `reg_channel` varchar(50) DEFAULT NULL,
  `reg_date` varchar(50) DEFAULT NULL,
  `last_login_date` varchar(50) DEFAULT NULL,
  `last_login_ip` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('39', '321233225', '20150705190928181se87gyf', 'android', '2015-07-05 07:09:28', '2015-07-05 07:09:28', '192.168.1.108', '111');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userinfo_id` int(4) NOT NULL AUTO_INCREMENT,
  `user_token` varchar(50) DEFAULT NULL,
  `user_realname` varchar(50) DEFAULT NULL,
  `userid_idcardnum` varchar(50) DEFAULT NULL,
  `userinfo_signatur` varchar(100) DEFAULT NULL,
  `userinfo_job` varchar(100) DEFAULT NULL,
  `pictures` varchar(100) DEFAULT NULL,
  `userinfo_email` varchar(50) DEFAULT NULL,
  `userinfo_sex` int(4) DEFAULT NULL,
  PRIMARY KEY (`userinfo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('2', '20150705190928181se87gyf', '张三', '大家好，我是小白，我是一个程序员！', 'cral', '老师', null, 'aaaa@qq.com', '1');
