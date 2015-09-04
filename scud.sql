/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : scud

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2015-08-30 22:40:26
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
-- Table structure for `orderanduser`
-- ----------------------------
DROP TABLE IF EXISTS `orderanduser`;
CREATE TABLE `orderanduser` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `user_token` varchar(50) DEFAULT NULL,
  `order_token` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderanduser
-- ----------------------------
INSERT INTO `orderanduser` VALUES ('1', 'userToken201508122133546077264', 'orderToken20150730231256853arpq');

-- ----------------------------
-- Table structure for `skill`
-- ----------------------------
DROP TABLE IF EXISTS `skill`;
CREATE TABLE `skill` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `skill_sort` varchar(50) DEFAULT NULL,
  `skill_token` varchar(50) DEFAULT NULL,
  `user_token` varchar(50) DEFAULT NULL,
  `skill_title` varchar(100) DEFAULT NULL,
  `skill_money` double(10,4) DEFAULT NULL,
  `skill_content` varchar(300) DEFAULT NULL,
  `skill_picture` varchar(300) DEFAULT NULL,
  `trade_flag` int(2) DEFAULT NULL,
  `skill_remark` varchar(300) DEFAULT NULL,
  `skill_unit` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of skill
-- ----------------------------
INSERT INTO `skill` VALUES ('1', '按摩', 'skillToken20150822164542131328w', 'userToken201508122133546077264', '按摩', '7.6000', 'ddddd', null, '1', '1day', '元/场');
INSERT INTO `skill` VALUES ('2', '聊天', 'skillToken20150822182109462sqh0', 'userToken201508122133546077264', '聊天', '789.0000', 'bjjjj', null, '1', 'bbjj', '元/场');
INSERT INTO `skill` VALUES ('3', '跑步', 'skillToken201508221821388178fbb', 'userToken201508122133546077264', '跑步', '555.0000', 'fighting', null, '1', 'bbhh', '元/小时');
INSERT INTO `skill` VALUES ('4', '家政', 'skillToken201508222029414850swb', 'userToken201508122133546077264', '家政', '5556.0000', '涂抹', null, '2', '痛苦咯', '元/小时');

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123', '123', '20150625103411466fi1po4m', 'android', '2015-06-25 10:34:11', '2015-06-25 10:34:11', '127.0.0.1');
INSERT INTO `user` VALUES ('2', '2', '2', '20150625103411466fi1po4msds', 'android', '2015-06-25 10:34:11', '2015-06-25 10:34:11', '127.0.0.1');
INSERT INTO `user` VALUES ('3', '3', '3', '20150625103411466fi1po4msds1', null, null, null, null);
INSERT INTO `user` VALUES ('4', '4', '4', '20150625103411466fi1po4msds2', null, null, null, null);
INSERT INTO `user` VALUES ('5', '5', '5', '20150625103411466fi1po4msds3', null, null, null, null);
INSERT INTO `user` VALUES ('6', '6', '6', '20150625103411466fi1po4msds4', null, null, null, null);
INSERT INTO `user` VALUES ('9', '15984755735', 'baiqiuping', 'userToken201507242155066738262', 'android', '2015-07-24 09:55:06', '2015-07-24 09:55:06', null);
INSERT INTO `user` VALUES ('10', '18381090832', '111111', 'userToken201507261310486387207', 'android', '2015-07-26 01:10:48', '2015-07-26 01:10:48', null);
INSERT INTO `user` VALUES ('11', '7', '7', 'userToken201507261310486387206', null, null, null, null);
INSERT INTO `user` VALUES ('12', '18728120022', '123', 'userToken201507302312556501512', 'android', '2015-07-30 11:12:55', '2015-07-30 11:12:55', null);
INSERT INTO `user` VALUES ('13', '123123', 'baiqiuping', 'userToken201508122133546077264', 'android', '2015-08-12 09:33:54', '2015-08-12 09:33:54', null);
INSERT INTO `user` VALUES ('14', '18381090833', '111111', 'userToken201508301650136515849', 'android', '2015-08-30 04:50:13', '2015-08-30 04:50:13', null);

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
  `user_token` varchar(50) DEFAULT NULL,
  `userinfo_picture` varchar(50) DEFAULT NULL,
  `userinfo_job` varchar(300) DEFAULT NULL,
  `longitude` double(20,8) DEFAULT NULL,
  `latitude` double(20,8) DEFAULT NULL,
  `userinfo_introduction` varchar(300) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  PRIMARY KEY (`userinfo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'scud', '5110021992', '1019557621@qq.com', '1', '喜欢吃饭，睡觉打豆豆', '1096921683', '20150625103411466fi1po4m', '/upload/20150812211350630x65k', '程序猿', null, null, '我很帅', '22');
INSERT INTO `userinfo` VALUES ('2', 'scud', '5110021992', '1019557621@qq.com', '0', '喜欢吃饭，睡觉打豌豆', '1081691896', '20150625103411466fi1po4msds', '/upload/20150812211350630x65k', '工程师', null, null, '我很阳光', '22');
INSERT INTO `userinfo` VALUES ('3', 'scud', '5110021992', '1019557621@qq.com', '1', '喜欢吃饭，睡觉打豇豆', '1081690142', '20150625103411466fi1po4msds1', '/upload/20150812211350630x65k', '架构师', null, null, '我很张扬', '22');
INSERT INTO `userinfo` VALUES ('4', 'scud', '5110021992', '1019557621@qq.com', '0', '喜欢吃红薯', '1081687241', '20150625103411466fi1po4msds2', '/upload/20150812211350630x65k', '模特', null, null, '我很漂亮', '21');
INSERT INTO `userinfo` VALUES ('5', 'scud', '5110021992', '1019557621@qq.com', '1', '喜欢吃玉米', '1081685664', '20150625103411466fi1po4msds3', '/upload/20150812211350630x65k', '腿模', null, null, '我很可爱', '22');
INSERT INTO `userinfo` VALUES ('6', 'scud', '5110021992', '1019557621@qq.com', '0', '喜欢吃土豆', '1081683840', '20150625103411466fi1po4msds4', '/upload/20150812211350630x65k', '胸模', null, null, '喜欢帅哥', '18');
INSERT INTO `userinfo` VALUES ('8', 'scud', '5110021992', '1019557621@qq.com', '1', '喜欢吃红薯', '1099628509', 'userToken201507242155066738262', '/upload/20150812211350630x65k', '健身教练', null, null, '我很漂亮', '27');
INSERT INTO `userinfo` VALUES ('9', 'scud', '511002199258695', '1019557621@qq.com', '0', '爱帅哥', '1107025768', 'userToken201507261310486387207', '/upload/20150814205806712kllh', '程序员', '0.00000000', '0.00000000', '量力量力斯拉', '0');
INSERT INTO `userinfo` VALUES ('10', 'scud', '5110021992', '1019557621@qq.com', '0', '爱美女', '1108190179', 'userToken201507261310486387206', '/upload/20150812211350630x65k', '八路', null, null, '我很漂亮', '23');
INSERT INTO `userinfo` VALUES ('11', 'scud', null, null, null, null, '1131185598', 'userToken201507302312556501512', '/upload/20150812211350630x65k', null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('12', 'scud', null, null, null, null, '1201925534', 'userToken201508122133546077264', null, null, null, null, null, null);
INSERT INTO `userinfo` VALUES ('13', 'scud', null, null, null, null, '1315559846', 'userToken201508301650136515849', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `userorder`
-- ----------------------------
DROP TABLE IF EXISTS `userorder`;
CREATE TABLE `userorder` (
  `order_id` int(4) NOT NULL AUTO_INCREMENT,
  `order_completeflag` tinyint(2) DEFAULT NULL,
  `order_token` varchar(50) DEFAULT NULL,
  `order_content` varchar(300) DEFAULT NULL,
  `order_limittime` varchar(30) DEFAULT NULL,
  `order_serviceaddress` varchar(100) DEFAULT NULL,
  `order_callscope` varchar(10) DEFAULT NULL,
  `order_money` double(10,2) DEFAULT NULL,
  `order_usertoken` varchar(50) DEFAULT NULL,
  `order_title` varchar(100) DEFAULT NULL,
  `order_starttime` varchar(30) DEFAULT NULL,
  `aptuser_num` int(4) unsigned zerofill DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `order_acptusken` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userorder
-- ----------------------------
INSERT INTO `userorder` VALUES ('16', '0', 'orderToken201507302122394004go7', '需要按摩放松一下', '1438262557755', '当前地址', '按摩', '15.00', '20150625103411466fi1po4msds2', '按摩', '1438262557755', '0001', null, null);
INSERT INTO `userorder` VALUES ('17', '0', 'orderToken20150730212749327klxh', '洗脚小妹上门服务', '1438262867756', '当前地址', '洗脚', '15.00', '20150625103411466fi1po4m', '洗脚', '1438262867756', '0000', null, null);
INSERT INTO `userorder` VALUES ('18', '0', 'orderToken20150730212821246h4cp', '求美女，陪饭局', '1438262899694', '当前地址', '美女', '15.00', '20150625103411466fi1po4msds1', '饭局', '1438262899694', '0000', null, null);
INSERT INTO `userorder` VALUES ('19', '0', 'orderToken20150730213231575pycp', '下午出门，需要一个美容师', '1438263150046', '当前地址', '美女', '15.00', '20150625103411466fi1po4msds3', '美容师', '1438263150046', '0000', null, null);
INSERT INTO `userorder` VALUES ('20', '0', 'orderToken20150730231256853arpq', '求聊天', '1438269173141', '当前地址', '陪聊', '15.00', '20150625103411466fi1po4msds4', '陪聊', '1438269173141', '0000', null, null);
INSERT INTO `userorder` VALUES ('21', '0', 'orderToken201507302318496766me4', '需要大厨上门做饭', '1438269525939', '当前地址', '厨师', '15.00', 'userToken201507302312556501512', '需要厨师', '1438269525939', '0000', null, null);
INSERT INTO `userorder` VALUES ('22', '0', 'orderToken20150812215252969ktu3', '啦啦啦', '2015-8-12 01 00', '他陌陌', '啊啊啊', '5566.00', 'userToken201508122133546077264', '啊啊啊', null, '0000', null, null);
INSERT INTO `userorder` VALUES ('23', '0', 'orderToken201508122236337674dti', '咯陌', '2015-8-12 04 00', '了分', '呃呃呃', '333.00', 'userToken201508122133546077264', '呃呃呃', null, '0000', null, null);
INSERT INTO `userorder` VALUES ('24', '0', 'orderToken20150822181748389k6i8', 'rgg', '2015-8-29 07 30', 'cgvgh', '2', '111.00', 'userToken201508122133546077264', 'ggg', null, '0000', null, null);
INSERT INTO `userorder` VALUES ('25', '0', 'orderToken20150822202907071izcn', '好纠结', '2015-8-30 07 40', '轰隆隆', '3', '8555.00', 'userToken201508122133546077264', '啦啦啦啦', null, '0000', null, null);
INSERT INTO `userorder` VALUES ('26', '0', 'orderToken20150825205826496qz2p', '嗨喽，你好', '2015-8-25 03 00', '这', '0', '1.00', 'userToken201508122133546077264', '聊天你好', null, '0000', null, null);
