/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : scud

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2015-07-24 15:53:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `evaluate`
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `evaluate_id` int(4) NOT NULL AUTO_INCREMENT,
  `evaluate_usken` varchar(50) DEFAULT NULL,
  `evaluate_tousken` varchar(50) DEFAULT NULL,
  `evaluate_content` varchar(255) DEFAULT NULL,
  `evaluate_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`evaluate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluate
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123', '123', '20150625103411466fi1po4m', 'android', '2015-06-25 10:34:11', '2015-06-25 10:34:11', '127.0.0.1');
INSERT INTO `user` VALUES ('2', '2', '2', '20150625103411466fi1po4msds', 'android', '2015-06-25 10:34:11', '2015-06-25 10:34:11', '127.0.0.1');
INSERT INTO `user` VALUES ('3', '3', '3', '20150625103411466fi1po4msds1', null, null, null, null);
INSERT INTO `user` VALUES ('4', '4', '4', '20150625103411466fi1po4msds2', null, null, null, null);
INSERT INTO `user` VALUES ('5', '5', '5', '20150625103411466fi1po4msds3', null, null, null, null);
INSERT INTO `user` VALUES ('6', '6', '6', '20150625103411466fi1po4msds4', null, null, null, null);

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `userinfo_id` int(5) NOT NULL AUTO_INCREMENT,
  `user_realname` varchar(30) DEFAULT NULL,
  `user_idcardnum` varchar(20) DEFAULT NULL,
  `userinfo_email` varchar(20) DEFAULT NULL,
  `userinfo_sex` int(4) DEFAULT NULL,
  `userinfo_signature` varchar(100) DEFAULT NULL,
  `lbsid` double(20,0) DEFAULT NULL,
  `user_token` varchar(30) DEFAULT NULL,
  `pictures` varchar(50) DEFAULT NULL,
  `userinfo_job` varchar(300) DEFAULT NULL,
  `longitude` double(20,8) DEFAULT NULL,
  `latitude` double(20,8) DEFAULT NULL,
  `userinfo_introduction` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`userinfo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'scud', '1', '1', '1', '1', '1096921683', '20150625103411466fi1po4m', null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('2', 'scud', null, null, null, null, '1081691896', '20150625103411466fi1po4msds', null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('3', 'scud', null, null, null, null, '1081690142', '20150625103411466fi1po4msds1', null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('4', 'scud', null, null, null, null, '1081687241', '20150625103411466fi1po4msds2', null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('5', 'scud', null, null, null, null, '1081685664', '20150625103411466fi1po4msds3', null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('6', 'scud', null, null, null, null, '1081683840', '20150625103411466fi1po4msds4', null, null, null, null, null);
