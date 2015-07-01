/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : scud

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2015-06-25 17:52:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(4) NOT NULL AUTO_INCREMENT,
  `order_userid` int(4) DEFAULT NULL,
  `order_createtime` varchar(30) DEFAULT NULL,
  `order_completeflag` int(2) DEFAULT NULL,
  `order_token` varchar(30) DEFAULT NULL,
  `order_content` varchar(300) DEFAULT NULL,
  `order_acptusersex` int(2) DEFAULT NULL,
  `order_commission` double(10,0) DEFAULT NULL,
  `order_limittime` varchar(30) DEFAULT NULL,
  `order_serviceaddress` varchar(100) DEFAULT NULL,
  `order_callscope` varchar(10) DEFAULT NULL,
  `order_pictures` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `user_token` varchar(30) DEFAULT NULL,
  `reg_channel` varchar(20) DEFAULT NULL,
  `reg_date` varchar(20) DEFAULT NULL,
  `last_login_date` varchar(20) DEFAULT NULL,
  `last_login_ip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '蔡岷村', '123', '20150625103411466fi1po4m', 'android', '2015-06-25 10:34:11', '2015-06-25 10:34:11', '127.0.0.1');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userinfo_id` int(5) NOT NULL AUTO_INCREMENT,
  `user_id` int(5) DEFAULT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `user_realname` varchar(30) DEFAULT NULL,
  `user_idcardnum` varchar(20) DEFAULT NULL,
  `userinfo_email` varchar(20) DEFAULT NULL,
  `userinfo_sex` int(4) DEFAULT NULL,
  `userinfo_label` varchar(50) DEFAULT NULL,
  `userinfo_signature` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userinfo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
