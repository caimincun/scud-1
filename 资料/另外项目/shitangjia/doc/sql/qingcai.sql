/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : qingcai

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2015-06-13 16:58:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qc_canteen
-- ----------------------------
DROP TABLE IF EXISTS `qc_canteen`;
CREATE TABLE `qc_canteen` (
  `id` int(11) NOT NULL auto_increment,
  `canteen_name` varchar(50) NOT NULL COMMENT '食堂名称',
  `canteen_leader` varchar(20) NOT NULL COMMENT '食堂负责人',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_canteen
-- ----------------------------
INSERT INTO `qc_canteen` VALUES ('1', '清菜食堂', 'test');

-- ----------------------------
-- Table structure for qc_canteenauthority
-- ----------------------------
DROP TABLE IF EXISTS `qc_canteenauthority`;
CREATE TABLE `qc_canteenauthority` (
  `id` int(11) NOT NULL auto_increment,
  `authority_name` varchar(100) NOT NULL,
  `parent_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_canteenauthority
-- ----------------------------
INSERT INTO `qc_canteenauthority` VALUES ('1', '菜单管理', '0');
INSERT INTO `qc_canteenauthority` VALUES ('2', '订单管理', '0');
INSERT INTO `qc_canteenauthority` VALUES ('3', '取餐', '0');
INSERT INTO `qc_canteenauthority` VALUES ('4', '财务管理', '0');
INSERT INTO `qc_canteenauthority` VALUES ('5', '充值', '4');
INSERT INTO `qc_canteenauthority` VALUES ('6', '消费记录', '4');
INSERT INTO `qc_canteenauthority` VALUES ('7', '用户管理', '0');

-- ----------------------------
-- Table structure for qc_canteendept
-- ----------------------------
DROP TABLE IF EXISTS `qc_canteendept`;
CREATE TABLE `qc_canteendept` (
  `id` int(11) NOT NULL auto_increment,
  `dept_name` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_canteendept
-- ----------------------------
INSERT INTO `qc_canteendept` VALUES ('1', '总经理部门');
INSERT INTO `qc_canteendept` VALUES ('2', '餐厅部门');
INSERT INTO `qc_canteendept` VALUES ('3', '财务部门');
INSERT INTO `qc_canteendept` VALUES ('4', '普通员工部门');

-- ----------------------------
-- Table structure for qc_canteenuser
-- ----------------------------
DROP TABLE IF EXISTS `qc_canteenuser`;
CREATE TABLE `qc_canteenuser` (
  `id` int(11) NOT NULL auto_increment,
  `dept_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_canteenuser
-- ----------------------------
INSERT INTO `qc_canteenuser` VALUES ('1', '1', '蔡岷村', '123');
INSERT INTO `qc_canteenuser` VALUES ('2', '2', '王浩', '123');
INSERT INTO `qc_canteenuser` VALUES ('3', '3', '牛哥', '123');
INSERT INTO `qc_canteenuser` VALUES ('4', '4', '牛姐', '123');

-- ----------------------------
-- Table structure for qc_collection
-- ----------------------------
DROP TABLE IF EXISTS `qc_collection`;
CREATE TABLE `qc_collection` (
  `id` int(11) NOT NULL auto_increment,
  `product_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK1A9CD6EB7136F109` (`product_id`),
  KEY `FK1A9CD6EBF8C0768B` (`user_id`),
  CONSTRAINT `FK1A9CD6EB7136F109` FOREIGN KEY (`product_id`) REFERENCES `qc_product` (`id`),
  CONSTRAINT `FK1A9CD6EBF8C0768B` FOREIGN KEY (`user_id`) REFERENCES `qc_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_collection
-- ----------------------------

-- ----------------------------
-- Table structure for qc_consume_history
-- ----------------------------
DROP TABLE IF EXISTS `qc_consume_history`;
CREATE TABLE `qc_consume_history` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `balance` decimal(8,2) NOT NULL default '0.00' COMMENT '消费金额',
  `consume_type` int(11) NOT NULL default '0' COMMENT '消费类型',
  `consume_time` datetime NOT NULL COMMENT '消费时间',
  `target_id` int(11) NOT NULL default '0',
  `c_day` int(11) default NULL,
  `c_month` int(11) default NULL,
  `c_year` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_consume_history
-- ----------------------------
INSERT INTO `qc_consume_history` VALUES ('1', '3', '24.00', '2', '2015-06-05 17:46:06', '23', '5', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('2', '4', '122.00', '2', '2015-06-05 18:10:35', '28', '5', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('3', '4', '226.00', '2', '2015-06-06 13:09:02', '29', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('4', '4', '184.00', '2', '2015-06-06 14:16:27', '32', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('5', '4', '142.00', '2', '2015-06-06 14:23:26', '33', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('6', '4', '142.00', '2', '2015-06-06 14:23:43', '33', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('7', '4', '142.00', '2', '2015-06-06 14:23:47', '33', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('8', '4', '142.00', '2', '2015-06-06 14:23:53', '33', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('9', '4', '109.00', '2', '2015-06-06 14:42:28', '35', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('10', '4', '109.00', '2', '2015-06-06 14:42:34', '35', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('11', '4', '109.00', '2', '2015-06-06 14:42:38', '35', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('12', '4', '109.00', '2', '2015-06-06 14:42:43', '35', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('13', '4', '109.00', '2', '2015-06-06 14:42:48', '35', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('14', '4', '109.00', '2', '2015-06-06 14:42:48', '35', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('15', '4', '36.00', '2', '2015-06-06 14:46:43', '38', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('16', '4', '151.00', '2', '2015-06-06 15:39:40', '42', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('17', '4', '88.00', '2', '2015-06-06 15:51:24', '45', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('18', '4', '124.00', '2', '2015-06-06 16:11:11', '51', '6', '6', '2015');
INSERT INTO `qc_consume_history` VALUES ('19', '4', '124.00', '2', '2015-06-09 12:26:08', '54', '9', '6', '2015');

-- ----------------------------
-- Table structure for qc_deptandauthority
-- ----------------------------
DROP TABLE IF EXISTS `qc_deptandauthority`;
CREATE TABLE `qc_deptandauthority` (
  `id` int(11) NOT NULL auto_increment,
  `authorty_id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_deptandauthority
-- ----------------------------
INSERT INTO `qc_deptandauthority` VALUES ('1', '1', '1');
INSERT INTO `qc_deptandauthority` VALUES ('2', '2', '1');
INSERT INTO `qc_deptandauthority` VALUES ('3', '3', '1');
INSERT INTO `qc_deptandauthority` VALUES ('4', '4', '1');
INSERT INTO `qc_deptandauthority` VALUES ('5', '5', '1');
INSERT INTO `qc_deptandauthority` VALUES ('6', '6', '1');
INSERT INTO `qc_deptandauthority` VALUES ('7', '7', '1');
INSERT INTO `qc_deptandauthority` VALUES ('8', '1', '2');
INSERT INTO `qc_deptandauthority` VALUES ('9', '2', '2');
INSERT INTO `qc_deptandauthority` VALUES ('10', '4', '3');
INSERT INTO `qc_deptandauthority` VALUES ('11', '5', '3');
INSERT INTO `qc_deptandauthority` VALUES ('12', '6', '3');
INSERT INTO `qc_deptandauthority` VALUES ('13', '3', '4');

-- ----------------------------
-- Table structure for qc_order
-- ----------------------------
DROP TABLE IF EXISTS `qc_order`;
CREATE TABLE `qc_order` (
  `id` int(11) NOT NULL auto_increment,
  `order_num` varchar(20) NOT NULL COMMENT '订单号',
  `total_price` decimal(8,2) NOT NULL default '0.00' COMMENT '总价',
  `order_date` datetime NOT NULL COMMENT '订单状态',
  `status` int(1) NOT NULL default '0' COMMENT '订单状态 0 未取餐 1 已取餐 2 转赠 3 撤销',
  `is_payed` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `eat_date` datetime default NULL,
  `eat_time` int(11) default NULL,
  `delete` int(1) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKF7DBA2E1F8C0768B` (`user_id`),
  CONSTRAINT `FKF7DBA2E1F8C0768B` FOREIGN KEY (`user_id`) REFERENCES `qc_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_order
-- ----------------------------
INSERT INTO `qc_order` VALUES ('1', '1433241395080', '0.00', '2015-06-02 18:36:35', '0', '0', '1', null, null, '0');
INSERT INTO `qc_order` VALUES ('2', '14332986523794731', '0.00', '2015-06-03 10:30:52', '0', '1', '1', null, null, '0');
INSERT INTO `qc_order` VALUES ('3', '14332986795653957', '0.00', '2015-06-03 10:31:19', '0', '1', '1', null, null, '0');
INSERT INTO `qc_order` VALUES ('4', '14332988657465317', '0.00', '2015-06-03 10:34:25', '0', '1', '1', null, null, '0');
INSERT INTO `qc_order` VALUES ('7', '14333074919831044', '50.00', '2015-06-03 12:58:11', '0', '0', '1', null, null, '0');
INSERT INTO `qc_order` VALUES ('8', '14333152461808106', '225.00', '2015-06-03 15:07:26', '0', '0', '3', null, null, '0');
INSERT INTO `qc_order` VALUES ('9', '14334078680945894', '393.00', '2015-06-04 16:51:08', '0', '0', '4', '2015-06-17 08:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('10', '14334084315948220', '199.00', '2015-06-04 17:00:31', '0', '0', '6', '2015-06-04 08:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('11', '14334734834671887', '229.00', '2015-06-05 11:04:43', '0', '0', '9', '2015-06-10 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('12', '14334741282337667', '372.00', '2015-06-05 11:15:28', '0', '0', '10', '2015-06-10 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('13', '14334921294686062', '310.00', '2015-06-05 16:15:29', '0', '0', '11', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('14', '14334923378016712', '48.00', '2015-06-05 16:18:57', '0', '0', '11', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('15', '14334925500135370', '259.00', '2015-06-05 16:22:30', '0', '0', '12', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('16', '14334936125876315', '76.00', '2015-06-05 16:40:12', '0', '0', '3', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('17', '14334943648446237', '232.00', '2015-06-05 16:52:44', '0', '0', '3', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('18', '14334953757768924', '192.00', '2015-06-05 17:09:35', '0', '0', '3', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('19', '14334958149474727', '342.00', '2015-06-05 17:16:54', '0', '0', '3', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('20', '14334963334220073', '417.00', '2015-06-05 17:25:33', '0', '0', '3', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('21', '14334966974341654', '256.00', '2015-06-05 17:31:37', '0', '0', '3', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('22', '14334971879106612', '277.00', '2015-06-05 17:39:47', '0', '0', '3', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('23', '14334974750340418', '24.00', '2015-06-05 17:44:35', '0', '0', '3', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('24', '14334976201086106', '93.00', '2015-06-05 17:47:00', '0', '0', '3', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('25', '14334979346063119', '155.00', '2015-06-05 17:52:14', '0', '0', '3', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('26', '14334983742835302', '64.00', '2015-06-05 17:59:34', '0', '0', '3', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('27', '14334986329792801', '108.00', '2015-06-05 18:03:52', '0', '0', '4', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('28', '14334989665838170', '122.00', '2015-06-05 18:09:26', '0', '0', '4', null, '3', '0');
INSERT INTO `qc_order` VALUES ('29', '14335672544274979', '226.00', '2015-06-06 13:07:34', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('30', '14335702286336703', '84.00', '2015-06-06 13:57:08', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('31', '14335708744170362', '156.00', '2015-06-06 14:07:54', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('32', '14335712638518720', '184.00', '2015-06-06 14:14:23', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('33', '14335717390861018', '142.00', '2015-06-06 14:22:19', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('34', '14335722970624137', '88.00', '2015-06-06 14:31:37', '0', '0', '4', null, '2', '0');
INSERT INTO `qc_order` VALUES ('35', '14335728841565200', '109.00', '2015-06-06 14:41:24', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('36', '14335730283825075', '241.00', '2015-06-06 14:43:48', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('37', '14335731389612633', '60.00', '2015-06-06 14:45:38', '0', '0', '4', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('38', '14335731989145209', '36.00', '2015-06-06 14:46:38', '0', '0', '4', null, '1', '0');
INSERT INTO `qc_order` VALUES ('39', '14335732383094770', '84.00', '2015-06-06 14:47:18', '0', '0', '4', null, '1', '0');
INSERT INTO `qc_order` VALUES ('40', '14335758995646394', '253.00', '2015-06-06 15:31:39', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('41', '14335761547275776', '228.00', '2015-06-06 15:35:54', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('42', '14335762943511771', '151.00', '2015-06-06 15:38:14', '0', '0', '4', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('43', '14335768181241483', '253.00', '2015-06-06 15:46:58', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('44', '14335769136786303', '57.00', '2015-06-06 15:48:33', '0', '0', '4', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('45', '14335770712668833', '88.00', '2015-06-06 15:51:11', '0', '0', '4', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('46', '14335771947734466', '94.00', '2015-06-06 15:53:14', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('47', '14335772653224603', '204.00', '2015-06-06 15:54:25', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('48', '14335773834288509', '118.00', '2015-06-06 15:56:23', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('49', '14335775008612617', '60.00', '2015-06-06 15:58:20', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('50', '14335775948183926', '84.00', '2015-06-06 15:59:54', '0', '0', '4', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('51', '14335780675306483', '124.00', '2015-06-06 16:07:47', '0', '0', '4', '2015-06-07 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('52', '14335781445070261', '60.00', '2015-06-06 16:09:04', '0', '0', '4', '2015-06-06 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('53', '14337396008609212', '179.00', '2015-06-08 13:00:00', '0', '0', '4', '2015-06-09 00:00:00', '2', '0');
INSERT INTO `qc_order` VALUES ('54', '14338239529644758', '124.00', '2015-06-09 12:25:52', '0', '0', '4', '2015-06-10 00:00:00', '2', '0');

-- ----------------------------
-- Table structure for qc_order_item
-- ----------------------------
DROP TABLE IF EXISTS `qc_order_item`;
CREATE TABLE `qc_order_item` (
  `id` int(11) NOT NULL auto_increment,
  `count` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FKAF7835B1E15D68F5` (`order_id`),
  KEY `FKAF7835B17093E695` (`product_id`),
  CONSTRAINT `FKAF7835B17093E695` FOREIGN KEY (`product_id`) REFERENCES `qc_product` (`id`),
  CONSTRAINT `FKAF7835B1E15D68F5` FOREIGN KEY (`order_id`) REFERENCES `qc_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_order_item
-- ----------------------------
INSERT INTO `qc_order_item` VALUES ('1', '2', '7', '1');
INSERT INTO `qc_order_item` VALUES ('2', '2', '7', '4');
INSERT INTO `qc_order_item` VALUES ('3', '4', '8', '1');
INSERT INTO `qc_order_item` VALUES ('4', '4', '8', '2');
INSERT INTO `qc_order_item` VALUES ('5', '5', '8', '3');
INSERT INTO `qc_order_item` VALUES ('6', '4', '9', '1');
INSERT INTO `qc_order_item` VALUES ('7', '6', '9', '2');
INSERT INTO `qc_order_item` VALUES ('8', '8', '9', '3');
INSERT INTO `qc_order_item` VALUES ('9', '5', '9', '4');
INSERT INTO `qc_order_item` VALUES ('10', '1', '10', '1');
INSERT INTO `qc_order_item` VALUES ('11', '4', '10', '2');
INSERT INTO `qc_order_item` VALUES ('12', '4', '10', '3');
INSERT INTO `qc_order_item` VALUES ('13', '3', '10', '4');
INSERT INTO `qc_order_item` VALUES ('14', '3', '11', '1');
INSERT INTO `qc_order_item` VALUES ('15', '6', '11', '2');
INSERT INTO `qc_order_item` VALUES ('16', '5', '11', '3');
INSERT INTO `qc_order_item` VALUES ('17', '4', '12', '1');
INSERT INTO `qc_order_item` VALUES ('18', '5', '12', '2');
INSERT INTO `qc_order_item` VALUES ('19', '4', '12', '3');
INSERT INTO `qc_order_item` VALUES ('20', '6', '12', '5');
INSERT INTO `qc_order_item` VALUES ('21', '3', '12', '6');
INSERT INTO `qc_order_item` VALUES ('22', '6', '13', '1');
INSERT INTO `qc_order_item` VALUES ('23', '8', '13', '2');
INSERT INTO `qc_order_item` VALUES ('24', '6', '13', '3');
INSERT INTO `qc_order_item` VALUES ('25', '2', '14', '1');
INSERT INTO `qc_order_item` VALUES ('26', '3', '14', '2');
INSERT INTO `qc_order_item` VALUES ('27', '4', '15', '1');
INSERT INTO `qc_order_item` VALUES ('28', '5', '15', '2');
INSERT INTO `qc_order_item` VALUES ('29', '5', '15', '3');
INSERT INTO `qc_order_item` VALUES ('30', '2', '15', '4');
INSERT INTO `qc_order_item` VALUES ('31', '3', '16', '1');
INSERT INTO `qc_order_item` VALUES ('32', '5', '16', '2');
INSERT INTO `qc_order_item` VALUES ('33', '4', '17', '1');
INSERT INTO `qc_order_item` VALUES ('34', '4', '17', '2');
INSERT INTO `qc_order_item` VALUES ('35', '3', '17', '3');
INSERT INTO `qc_order_item` VALUES ('36', '5', '17', '4');
INSERT INTO `qc_order_item` VALUES ('37', '3', '18', '1');
INSERT INTO `qc_order_item` VALUES ('38', '5', '18', '2');
INSERT INTO `qc_order_item` VALUES ('39', '4', '18', '3');
INSERT INTO `qc_order_item` VALUES ('40', '5', '19', '1');
INSERT INTO `qc_order_item` VALUES ('41', '7', '19', '2');
INSERT INTO `qc_order_item` VALUES ('42', '6', '19', '3');
INSERT INTO `qc_order_item` VALUES ('43', '4', '19', '4');
INSERT INTO `qc_order_item` VALUES ('44', '8', '20', '1');
INSERT INTO `qc_order_item` VALUES ('45', '7', '20', '2');
INSERT INTO `qc_order_item` VALUES ('46', '6', '20', '3');
INSERT INTO `qc_order_item` VALUES ('47', '7', '20', '4');
INSERT INTO `qc_order_item` VALUES ('48', '7', '21', '1');
INSERT INTO `qc_order_item` VALUES ('49', '7', '21', '2');
INSERT INTO `qc_order_item` VALUES ('50', '4', '21', '3');
INSERT INTO `qc_order_item` VALUES ('51', '4', '22', '1');
INSERT INTO `qc_order_item` VALUES ('52', '4', '22', '2');
INSERT INTO `qc_order_item` VALUES ('53', '5', '22', '3');
INSERT INTO `qc_order_item` VALUES ('54', '4', '22', '4');
INSERT INTO `qc_order_item` VALUES ('55', '2', '23', '1');
INSERT INTO `qc_order_item` VALUES ('56', '4', '24', '1');
INSERT INTO `qc_order_item` VALUES ('57', '2', '24', '2');
INSERT INTO `qc_order_item` VALUES ('58', '1', '24', '3');
INSERT INTO `qc_order_item` VALUES ('59', '3', '25', '1');
INSERT INTO `qc_order_item` VALUES ('60', '4', '25', '2');
INSERT INTO `qc_order_item` VALUES ('61', '3', '25', '3');
INSERT INTO `qc_order_item` VALUES ('62', '2', '26', '1');
INSERT INTO `qc_order_item` VALUES ('63', '5', '26', '2');
INSERT INTO `qc_order_item` VALUES ('64', '5', '27', '1');
INSERT INTO `qc_order_item` VALUES ('65', '6', '27', '2');
INSERT INTO `qc_order_item` VALUES ('66', '8', '28', '1');
INSERT INTO `qc_order_item` VALUES ('67', '2', '28', '4');
INSERT INTO `qc_order_item` VALUES ('68', '5', '29', '1');
INSERT INTO `qc_order_item` VALUES ('69', '5', '29', '2');
INSERT INTO `qc_order_item` VALUES ('70', '3', '29', '3');
INSERT INTO `qc_order_item` VALUES ('71', '3', '29', '4');
INSERT INTO `qc_order_item` VALUES ('72', '5', '30', '1');
INSERT INTO `qc_order_item` VALUES ('73', '3', '30', '2');
INSERT INTO `qc_order_item` VALUES ('74', '6', '31', '1');
INSERT INTO `qc_order_item` VALUES ('75', '4', '31', '2');
INSERT INTO `qc_order_item` VALUES ('76', '4', '31', '4');
INSERT INTO `qc_order_item` VALUES ('77', '3', '32', '1');
INSERT INTO `qc_order_item` VALUES ('78', '4', '32', '2');
INSERT INTO `qc_order_item` VALUES ('79', '4', '32', '3');
INSERT INTO `qc_order_item` VALUES ('80', '7', '33', '1');
INSERT INTO `qc_order_item` VALUES ('81', '2', '33', '2');
INSERT INTO `qc_order_item` VALUES ('82', '1', '33', '3');
INSERT INTO `qc_order_item` VALUES ('83', '1', '33', '4');
INSERT INTO `qc_order_item` VALUES ('84', '4', '34', '1');
INSERT INTO `qc_order_item` VALUES ('85', '5', '34', '2');
INSERT INTO `qc_order_item` VALUES ('86', '4', '35', '1');
INSERT INTO `qc_order_item` VALUES ('87', '4', '35', '2');
INSERT INTO `qc_order_item` VALUES ('88', '1', '35', '3');
INSERT INTO `qc_order_item` VALUES ('89', '1', '36', '1');
INSERT INTO `qc_order_item` VALUES ('90', '4', '36', '2');
INSERT INTO `qc_order_item` VALUES ('91', '5', '36', '3');
INSERT INTO `qc_order_item` VALUES ('92', '4', '36', '4');
INSERT INTO `qc_order_item` VALUES ('93', '5', '37', '1');
INSERT INTO `qc_order_item` VALUES ('94', '3', '38', '1');
INSERT INTO `qc_order_item` VALUES ('95', '7', '39', '1');
INSERT INTO `qc_order_item` VALUES ('96', '2', '40', '1');
INSERT INTO `qc_order_item` VALUES ('97', '4', '40', '2');
INSERT INTO `qc_order_item` VALUES ('98', '5', '40', '3');
INSERT INTO `qc_order_item` VALUES ('99', '4', '40', '4');
INSERT INTO `qc_order_item` VALUES ('100', '1', '41', '1');
INSERT INTO `qc_order_item` VALUES ('101', '4', '41', '2');
INSERT INTO `qc_order_item` VALUES ('102', '5', '41', '3');
INSERT INTO `qc_order_item` VALUES ('103', '3', '41', '4');
INSERT INTO `qc_order_item` VALUES ('104', '2', '42', '1');
INSERT INTO `qc_order_item` VALUES ('105', '5', '42', '2');
INSERT INTO `qc_order_item` VALUES ('106', '3', '42', '3');
INSERT INTO `qc_order_item` VALUES ('107', '2', '43', '1');
INSERT INTO `qc_order_item` VALUES ('108', '4', '43', '2');
INSERT INTO `qc_order_item` VALUES ('109', '5', '43', '3');
INSERT INTO `qc_order_item` VALUES ('110', '4', '43', '4');
INSERT INTO `qc_order_item` VALUES ('111', '1', '44', '1');
INSERT INTO `qc_order_item` VALUES ('112', '2', '44', '2');
INSERT INTO `qc_order_item` VALUES ('113', '1', '44', '3');
INSERT INTO `qc_order_item` VALUES ('114', '4', '45', '1');
INSERT INTO `qc_order_item` VALUES ('115', '5', '45', '2');
INSERT INTO `qc_order_item` VALUES ('116', '3', '46', '1');
INSERT INTO `qc_order_item` VALUES ('117', '2', '46', '3');
INSERT INTO `qc_order_item` VALUES ('118', '4', '47', '1');
INSERT INTO `qc_order_item` VALUES ('119', '5', '47', '2');
INSERT INTO `qc_order_item` VALUES ('120', '4', '47', '3');
INSERT INTO `qc_order_item` VALUES ('121', '3', '48', '1');
INSERT INTO `qc_order_item` VALUES ('122', '3', '48', '2');
INSERT INTO `qc_order_item` VALUES ('123', '2', '48', '3');
INSERT INTO `qc_order_item` VALUES ('124', '3', '49', '1');
INSERT INTO `qc_order_item` VALUES ('125', '3', '49', '2');
INSERT INTO `qc_order_item` VALUES ('126', '7', '50', '1');
INSERT INTO `qc_order_item` VALUES ('127', '7', '51', '1');
INSERT INTO `qc_order_item` VALUES ('128', '5', '51', '2');
INSERT INTO `qc_order_item` VALUES ('129', '5', '52', '1');
INSERT INTO `qc_order_item` VALUES ('130', '3', '53', '1');
INSERT INTO `qc_order_item` VALUES ('131', '7', '53', '2');
INSERT INTO `qc_order_item` VALUES ('132', '3', '53', '3');
INSERT INTO `qc_order_item` VALUES ('133', '5', '54', '1');
INSERT INTO `qc_order_item` VALUES ('134', '8', '54', '2');

-- ----------------------------
-- Table structure for qc_order_product
-- ----------------------------
DROP TABLE IF EXISTS `qc_order_product`;
CREATE TABLE `qc_order_product` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY  (`order_id`,`product_id`),
  KEY `FK55CA5917093E695` (`product_id`),
  KEY `FK55CA591E15D68F5` (`order_id`),
  CONSTRAINT `FK55CA5917093E695` FOREIGN KEY (`product_id`) REFERENCES `qc_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK55CA591E15D68F5` FOREIGN KEY (`order_id`) REFERENCES `qc_order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_order_product
-- ----------------------------

-- ----------------------------
-- Table structure for qc_product
-- ----------------------------
DROP TABLE IF EXISTS `qc_product`;
CREATE TABLE `qc_product` (
  `id` int(11) NOT NULL auto_increment,
  `img` varchar(100) default NULL COMMENT '菜谱图片',
  `name` varchar(20) NOT NULL default '0.00' COMMENT '菜名',
  `price` decimal(7,2) NOT NULL,
  `description` varchar(255) default NULL COMMENT '菜谱描述',
  `canteen_id` int(11) NOT NULL COMMENT '绑定餐厅id',
  `c_day` int(11) NOT NULL,
  `c_month` int(11) NOT NULL,
  `c_year` int(11) NOT NULL,
  `created_time` date NOT NULL,
  `time_node` int(11) NOT NULL,
  `product_category_id` int(11) NOT NULL,
  `product_category_name` varchar(20) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `cookbook_canteen_id` (`canteen_id`),
  KEY `FKA4FF4FC2AE71B268` (`product_category_id`),
  CONSTRAINT `cookbook_canteen_id` FOREIGN KEY (`canteen_id`) REFERENCES `qc_canteen` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKA4FF4FC2AE71B268` FOREIGN KEY (`product_category_id`) REFERENCES `qc_product_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_product
-- ----------------------------
INSERT INTO `qc_product` VALUES ('1', null, '鱼香肉丝', '12.00', '美味', '1', '8', '6', '2015', '2015-06-08', '1', '1', '汤菜');
INSERT INTO `qc_product` VALUES ('2', null, '麻婆豆腐', '8.00', '好吃', '1', '9', '6', '2015', '2015-06-09', '2', '1', '汤菜');
INSERT INTO `qc_product` VALUES ('3', null, '川大神鸭', '29.00', '美容养颜', '1', '11', '6', '2015', '2015-06-11', '3', '1', '汤菜');
INSERT INTO `qc_product` VALUES ('4', null, '回锅肉', '13.00', '好吃', '1', '10', '6', '2015', '2015-06-10', '2', '1', '汤菜');
INSERT INTO `qc_product` VALUES ('5', null, '麻辣香锅', '25.00', '香辣', '1', '10', '6', '2015', '2015-06-10', '2', '1', '汤菜');
INSERT INTO `qc_product` VALUES ('6', null, '土豆丝', '6.00', '营养', '1', '12', '6', '2015', '2015-06-12', '3', '1', '汤菜');
INSERT INTO `qc_product` VALUES ('7', null, '番茄炒蛋', '10.00', '好吃', '1', '12', '6', '2015', '2015-06-12', '2', '1', '汤菜');

-- ----------------------------
-- Table structure for qc_product_category
-- ----------------------------
DROP TABLE IF EXISTS `qc_product_category`;
CREATE TABLE `qc_product_category` (
  `id` int(11) NOT NULL auto_increment,
  `categroy_name` varchar(20) NOT NULL,
  `canteen_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK5BD938DB2F84D329` (`canteen_id`),
  CONSTRAINT `FK5BD938DB2F84D329` FOREIGN KEY (`canteen_id`) REFERENCES `qc_canteen` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_product_category
-- ----------------------------
INSERT INTO `qc_product_category` VALUES ('1', '汤类', '1');

-- ----------------------------
-- Table structure for qc_question
-- ----------------------------
DROP TABLE IF EXISTS `qc_question`;
CREATE TABLE `qc_question` (
  `id` int(11) NOT NULL auto_increment,
  `question_type` varchar(50) default NULL,
  `title` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_question
-- ----------------------------
INSERT INTO `qc_question` VALUES ('13', '1', '您光顾食堂的频率是');
INSERT INTO `qc_question` VALUES ('14', '1', '您觉得食堂饭菜口味怎么样？');
INSERT INTO `qc_question` VALUES ('15', '1', '结合当前物价和您的生活经验，以成本价计算，您认为员工餐一餐至少需要多少钱才能吃得比较满意？');
INSERT INTO `qc_question` VALUES ('16', '1', '您觉得食堂打的饭菜份量如何?');
INSERT INTO `qc_question` VALUES ('17', '1', '您对食堂每餐供应的免费汤味道满意吗？');
INSERT INTO `qc_question` VALUES ('18', '1', '您认为食堂的菜品更新情况如何?');
INSERT INTO `qc_question` VALUES ('19', '1', '您对食堂环境、餐具的卫生消毒情况感觉如何？');
INSERT INTO `qc_question` VALUES ('20', '1', '您认为食堂工作人员的工作态度是？');
INSERT INTO `qc_question` VALUES ('21', '1', '您在食堂用餐碰到过排好久的队，轮到自己时没有想吃的菜的情况吗？');
INSERT INTO `qc_question` VALUES ('22', '1', '您通常的候餐时间是？');
INSERT INTO `qc_question` VALUES ('23', '1', '您更倾向的取餐方式是？');
INSERT INTO `qc_question` VALUES ('24', '1', '您是否愿意通过微信公众号或者APP订餐？');
INSERT INTO `qc_question` VALUES ('25', '1', '您是否愿意与同事拼餐？');
INSERT INTO `qc_question` VALUES ('26', '0', '您目前最关注下面哪些问题？');
INSERT INTO `qc_question` VALUES ('27', '0', '您主要采用的就餐方式有？');
INSERT INTO `qc_question` VALUES ('28', '0', '您希望增加何种口味？');
INSERT INTO `qc_question` VALUES ('29', '0', '您可以接受的支付方式有？');
INSERT INTO `qc_question` VALUES ('30', '0', '您愿意通过哪种方式与食堂进行互动？');
INSERT INTO `qc_question` VALUES ('31', '0', '您愿意在哪些方面参与食堂的管理？');
INSERT INTO `qc_question` VALUES ('32', '0', '如果食堂提供更多增值服务，您可能会尝试哪些服务？');

-- ----------------------------
-- Table structure for qc_questionuser
-- ----------------------------
DROP TABLE IF EXISTS `qc_questionuser`;
CREATE TABLE `qc_questionuser` (
  `id` int(11) NOT NULL auto_increment,
  `age` int(11) default NULL,
  `sex` int(1) NOT NULL,
  `name` varchar(50) default NULL,
  `award` int(1) NOT NULL,
  `ipaddr` varchar(255) NOT NULL,
  `iscanjia` int(1) NOT NULL,
  `phonenumber` varchar(20) default NULL,
  `isconfirm` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_questionuser
-- ----------------------------
INSERT INTO `qc_questionuser` VALUES ('49', '12', '0', null, '0', '192.168.1.1', '1', null, '0');
INSERT INTO `qc_questionuser` VALUES ('50', '32', '1', null, '0', '192.168.1.2', '1', null, '0');
INSERT INTO `qc_questionuser` VALUES ('51', '41', '0', null, '0', '192.168.1.3', '1', null, '0');
INSERT INTO `qc_questionuser` VALUES ('53', '12', '1', null, '0', '192.168.1.4', '1', null, '0');
INSERT INTO `qc_questionuser` VALUES ('54', '14', '1', null, '0', '192.168.1.5', '0', null, '0');
INSERT INTO `qc_questionuser` VALUES ('55', '12', '1', null, '0', '192.168.1.6', '0', null, '0');
INSERT INTO `qc_questionuser` VALUES ('56', '12', '1', null, '0', '192.168.1.7', '1', null, '0');
INSERT INTO `qc_questionuser` VALUES ('57', '12', '0', null, '0', '192.168.1.8', '1', null, '0');
INSERT INTO `qc_questionuser` VALUES ('58', '51', '1', null, '0', '192.168.1.10', '1', null, '0');
INSERT INTO `qc_questionuser` VALUES ('59', '58', '1', null, '3', '192.168.1.11', '1', null, '0');
INSERT INTO `qc_questionuser` VALUES ('60', '12', '1', null, '2', '192.168.1.122', '1', null, '1');

-- ----------------------------
-- Table structure for qc_question_answers
-- ----------------------------
DROP TABLE IF EXISTS `qc_question_answers`;
CREATE TABLE `qc_question_answers` (
  `id` int(11) NOT NULL auto_increment,
  `value` varchar(100) default NULL,
  `question_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK29494FA93CF6E815` (`question_id`),
  CONSTRAINT `FK29494FA93CF6E815` FOREIGN KEY (`question_id`) REFERENCES `qc_question` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_question_answers
-- ----------------------------
INSERT INTO `qc_question_answers` VALUES ('32', '0--4次/周', '13');
INSERT INTO `qc_question_answers` VALUES ('33', '5--10次/周', '13');
INSERT INTO `qc_question_answers` VALUES ('34', '11次以上', '13');
INSERT INTO `qc_question_answers` VALUES ('35', '还行，偶尔味道是可以的', '14');
INSERT INTO `qc_question_answers` VALUES ('36', '很好啊，比我知道的其他公司都好', '14');
INSERT INTO `qc_question_answers` VALUES ('37', '有待提高，没吃过这么难吃的食堂', '14');
INSERT INTO `qc_question_answers` VALUES ('38', '一般般，和其他公司的食堂没什么区别', '14');
INSERT INTO `qc_question_answers` VALUES ('39', '午餐13-14元', '15');
INSERT INTO `qc_question_answers` VALUES ('40', '午餐8-10元', '15');
INSERT INTO `qc_question_answers` VALUES ('41', '午餐11-12元', '15');
INSERT INTO `qc_question_answers` VALUES ('42', '午餐15元以上', '15');
INSERT INTO `qc_question_answers` VALUES ('43', '不够', '16');
INSERT INTO `qc_question_answers` VALUES ('44', '还可以', '16');
INSERT INTO `qc_question_answers` VALUES ('45', '太少', '16');
INSERT INTO `qc_question_answers` VALUES ('46', '太多', '16');
INSERT INTO `qc_question_answers` VALUES ('47', '不错，挺好喝的', '17');
INSERT INTO `qc_question_answers` VALUES ('48', '味道不行，哪里叫汤', '17');
INSERT INTO `qc_question_answers` VALUES ('49', '一般般，吃饭噎到有得救就好', '17');
INSERT INTO `qc_question_answers` VALUES ('50', '还行吧，偶尔味道是可以的', '17');
INSERT INTO `qc_question_answers` VALUES ('51', '很死板', '18');
INSERT INTO `qc_question_answers` VALUES ('52', '经常更新', '18');
INSERT INTO `qc_question_answers` VALUES ('53', '好像没什么变化', '18');
INSERT INTO `qc_question_answers` VALUES ('54', '还算可以', '18');
INSERT INTO `qc_question_answers` VALUES ('55', '饿了就吃饭，没注意过', '19');
INSERT INTO `qc_question_answers` VALUES ('56', '挺好的，挺干净卫生的', '19');
INSERT INTO `qc_question_answers` VALUES ('57', '没感觉有做过清洁消毒，感觉很不干净', '19');
INSERT INTO `qc_question_answers` VALUES ('58', '一般般，餐盘消毒觉得做的还可以', '19');
INSERT INTO `qc_question_answers` VALUES ('59', '很敬业', '20');
INSERT INTO `qc_question_answers` VALUES ('60', '比较敬业', '20');
INSERT INTO `qc_question_answers` VALUES ('61', '能说真话吗', '20');
INSERT INTO `qc_question_answers` VALUES ('62', '消极怠工', '20');
INSERT INTO `qc_question_answers` VALUES ('63', '没有', '21');
INSERT INTO `qc_question_answers` VALUES ('64', '经常有', '21');
INSERT INTO `qc_question_answers` VALUES ('65', '偶尔有', '21');
INSERT INTO `qc_question_answers` VALUES ('66', '10分钟以内', '22');
INSERT INTO `qc_question_answers` VALUES ('67', '7分钟以内', '22');
INSERT INTO `qc_question_answers` VALUES ('68', '3分钟以内', '22');
INSERT INTO `qc_question_answers` VALUES ('69', '等到花儿都谢了', '22');
INSERT INTO `qc_question_answers` VALUES ('70', '能送上门不', '23');
INSERT INTO `qc_question_answers` VALUES ('71', '现点现舀', '23');
INSERT INTO `qc_question_answers` VALUES ('72', '自助取餐', '23');
INSERT INTO `qc_question_answers` VALUES ('73', '预订取餐', '23');
INSERT INTO `qc_question_answers` VALUES ('74', '不愿意', '24');
INSERT INTO `qc_question_answers` VALUES ('75', '如果不费事，愿意尝试', '24');
INSERT INTO `qc_question_answers` VALUES ('76', '愿意', '24');
INSERT INTO `qc_question_answers` VALUES ('77', '如果能省钱，愿意尝试', '24');
INSERT INTO `qc_question_answers` VALUES ('78', '不愿意', '25');
INSERT INTO `qc_question_answers` VALUES ('79', ' 那要看谁了', '25');
INSERT INTO `qc_question_answers` VALUES ('80', '愿意', '25');
INSERT INTO `qc_question_answers` VALUES ('81', '食堂的环境', '26');
INSERT INTO `qc_question_answers` VALUES ('82', '饭菜的价格', '26');
INSERT INTO `qc_question_answers` VALUES ('83', '食堂的餐具卫生', '26');
INSERT INTO `qc_question_answers` VALUES ('84', '工作人员服务态度', '26');
INSERT INTO `qc_question_answers` VALUES ('85', '看心情，想到哪吃就到哪吃', '27');
INSERT INTO `qc_question_answers` VALUES ('86', '外卖', '27');
INSERT INTO `qc_question_answers` VALUES ('87', '自己带饭', '27');
INSERT INTO `qc_question_answers` VALUES ('88', '食堂', '27');
INSERT INTO `qc_question_answers` VALUES ('89', '酸甜', '28');
INSERT INTO `qc_question_answers` VALUES ('90', '咸香', '28');
INSERT INTO `qc_question_answers` VALUES ('91', '豉香', '28');
INSERT INTO `qc_question_answers` VALUES ('92', '麻辣', '28');
INSERT INTO `qc_question_answers` VALUES ('93', '手机扫码支付', '29');
INSERT INTO `qc_question_answers` VALUES ('94', '指纹支付', '29');
INSERT INTO `qc_question_answers` VALUES ('95', 'IC卡支付', '29');
INSERT INTO `qc_question_answers` VALUES ('96', '现金支付', '29');
INSERT INTO `qc_question_answers` VALUES ('97', '现场座谈会', '30');
INSERT INTO `qc_question_answers` VALUES ('98', '没空', '30');
INSERT INTO `qc_question_answers` VALUES ('99', '食堂APP', '30');
INSERT INTO `qc_question_answers` VALUES ('100', '微信公众号', '30');
INSERT INTO `qc_question_answers` VALUES ('101', '菜谱制定', '31');
INSERT INTO `qc_question_answers` VALUES ('102', '真的没时间', '31');
INSERT INTO `qc_question_answers` VALUES ('103', '菜品点评', '31');
INSERT INTO `qc_question_answers` VALUES ('104', '食材采购', '31');
INSERT INTO `qc_question_answers` VALUES ('105', '真烦，说了没时间的', '32');
INSERT INTO `qc_question_answers` VALUES ('106', '送餐上门', '32');
INSERT INTO `qc_question_answers` VALUES ('107', '食材代加工', '32');
INSERT INTO `qc_question_answers` VALUES ('108', '食材销售', '32');

-- ----------------------------
-- Table structure for qc_question_result
-- ----------------------------
DROP TABLE IF EXISTS `qc_question_result`;
CREATE TABLE `qc_question_result` (
  `id` int(11) NOT NULL auto_increment,
  `answers_id` int(11) default NULL,
  `question_id` int(11) default NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_question_result
-- ----------------------------
INSERT INTO `qc_question_result` VALUES ('148', '33', '13', '49');
INSERT INTO `qc_question_result` VALUES ('149', '36', '14', '49');
INSERT INTO `qc_question_result` VALUES ('150', '41', '15', '49');
INSERT INTO `qc_question_result` VALUES ('151', '43', '16', '49');
INSERT INTO `qc_question_result` VALUES ('152', '48', '17', '49');
INSERT INTO `qc_question_result` VALUES ('153', '52', '18', '49');
INSERT INTO `qc_question_result` VALUES ('154', '56', '19', '49');
INSERT INTO `qc_question_result` VALUES ('155', '59', '20', '49');
INSERT INTO `qc_question_result` VALUES ('156', '63', '21', '49');
INSERT INTO `qc_question_result` VALUES ('157', '66', '22', '49');
INSERT INTO `qc_question_result` VALUES ('158', '70', '23', '49');
INSERT INTO `qc_question_result` VALUES ('159', '75', '24', '49');
INSERT INTO `qc_question_result` VALUES ('160', '80', '25', '49');
INSERT INTO `qc_question_result` VALUES ('161', '81', '26', '49');
INSERT INTO `qc_question_result` VALUES ('162', '86', '27', '49');
INSERT INTO `qc_question_result` VALUES ('163', '87', '27', '49');
INSERT INTO `qc_question_result` VALUES ('164', '90', '28', '49');
INSERT INTO `qc_question_result` VALUES ('165', '91', '28', '49');
INSERT INTO `qc_question_result` VALUES ('166', '95', '29', '49');
INSERT INTO `qc_question_result` VALUES ('167', '97', '30', '49');
INSERT INTO `qc_question_result` VALUES ('168', '103', '31', '49');
INSERT INTO `qc_question_result` VALUES ('169', '106', '32', '49');
INSERT INTO `qc_question_result` VALUES ('170', '33', '13', '50');
INSERT INTO `qc_question_result` VALUES ('171', '36', '14', '50');
INSERT INTO `qc_question_result` VALUES ('172', '39', '15', '50');
INSERT INTO `qc_question_result` VALUES ('173', '43', '16', '50');
INSERT INTO `qc_question_result` VALUES ('174', '47', '17', '50');
INSERT INTO `qc_question_result` VALUES ('175', '51', '18', '50');
INSERT INTO `qc_question_result` VALUES ('176', '55', '19', '50');
INSERT INTO `qc_question_result` VALUES ('177', '59', '20', '50');
INSERT INTO `qc_question_result` VALUES ('178', '65', '21', '50');
INSERT INTO `qc_question_result` VALUES ('179', '69', '22', '50');
INSERT INTO `qc_question_result` VALUES ('180', '72', '23', '50');
INSERT INTO `qc_question_result` VALUES ('181', '76', '24', '50');
INSERT INTO `qc_question_result` VALUES ('182', '78', '25', '50');
INSERT INTO `qc_question_result` VALUES ('183', '82', '26', '50');
INSERT INTO `qc_question_result` VALUES ('184', '86', '27', '50');
INSERT INTO `qc_question_result` VALUES ('185', '87', '27', '50');
INSERT INTO `qc_question_result` VALUES ('186', '89', '28', '50');
INSERT INTO `qc_question_result` VALUES ('187', '93', '29', '50');
INSERT INTO `qc_question_result` VALUES ('188', '96', '29', '50');
INSERT INTO `qc_question_result` VALUES ('189', '99', '30', '50');
INSERT INTO `qc_question_result` VALUES ('190', '102', '31', '50');
INSERT INTO `qc_question_result` VALUES ('191', '104', '31', '50');
INSERT INTO `qc_question_result` VALUES ('192', '106', '32', '50');
INSERT INTO `qc_question_result` VALUES ('193', '107', '32', '50');
INSERT INTO `qc_question_result` VALUES ('194', '33', '13', '51');
INSERT INTO `qc_question_result` VALUES ('195', '37', '14', '51');
INSERT INTO `qc_question_result` VALUES ('196', '40', '15', '51');
INSERT INTO `qc_question_result` VALUES ('197', '44', '16', '51');
INSERT INTO `qc_question_result` VALUES ('198', '47', '17', '51');
INSERT INTO `qc_question_result` VALUES ('199', '51', '18', '51');
INSERT INTO `qc_question_result` VALUES ('200', '56', '19', '51');
INSERT INTO `qc_question_result` VALUES ('201', '60', '20', '51');
INSERT INTO `qc_question_result` VALUES ('202', '64', '21', '51');
INSERT INTO `qc_question_result` VALUES ('203', '68', '22', '51');
INSERT INTO `qc_question_result` VALUES ('204', '72', '23', '51');
INSERT INTO `qc_question_result` VALUES ('205', '76', '24', '51');
INSERT INTO `qc_question_result` VALUES ('206', '78', '25', '51');
INSERT INTO `qc_question_result` VALUES ('207', '83', '26', '51');
INSERT INTO `qc_question_result` VALUES ('208', '87', '27', '51');
INSERT INTO `qc_question_result` VALUES ('209', '91', '28', '51');
INSERT INTO `qc_question_result` VALUES ('210', '93', '29', '51');
INSERT INTO `qc_question_result` VALUES ('211', '95', '29', '51');
INSERT INTO `qc_question_result` VALUES ('212', '100', '30', '51');
INSERT INTO `qc_question_result` VALUES ('213', '103', '31', '51');
INSERT INTO `qc_question_result` VALUES ('214', '105', '32', '51');
INSERT INTO `qc_question_result` VALUES ('215', '33', '13', '53');
INSERT INTO `qc_question_result` VALUES ('216', '37', '14', '53');
INSERT INTO `qc_question_result` VALUES ('217', '41', '15', '53');
INSERT INTO `qc_question_result` VALUES ('218', '46', '16', '53');
INSERT INTO `qc_question_result` VALUES ('219', '49', '17', '53');
INSERT INTO `qc_question_result` VALUES ('220', '51', '18', '53');
INSERT INTO `qc_question_result` VALUES ('221', '55', '19', '53');
INSERT INTO `qc_question_result` VALUES ('222', '59', '20', '53');
INSERT INTO `qc_question_result` VALUES ('223', '65', '21', '53');
INSERT INTO `qc_question_result` VALUES ('224', '67', '22', '53');
INSERT INTO `qc_question_result` VALUES ('225', '70', '23', '53');
INSERT INTO `qc_question_result` VALUES ('226', '74', '24', '53');
INSERT INTO `qc_question_result` VALUES ('227', '78', '25', '53');
INSERT INTO `qc_question_result` VALUES ('228', '81', '26', '53');
INSERT INTO `qc_question_result` VALUES ('229', '88', '27', '53');
INSERT INTO `qc_question_result` VALUES ('230', '90', '28', '53');
INSERT INTO `qc_question_result` VALUES ('231', '93', '29', '53');
INSERT INTO `qc_question_result` VALUES ('232', '97', '30', '53');
INSERT INTO `qc_question_result` VALUES ('233', '104', '31', '53');
INSERT INTO `qc_question_result` VALUES ('234', '106', '32', '53');
INSERT INTO `qc_question_result` VALUES ('235', '32', '13', '56');
INSERT INTO `qc_question_result` VALUES ('236', '38', '14', '56');
INSERT INTO `qc_question_result` VALUES ('237', '42', '15', '56');
INSERT INTO `qc_question_result` VALUES ('238', '45', '16', '56');
INSERT INTO `qc_question_result` VALUES ('239', '49', '17', '56');
INSERT INTO `qc_question_result` VALUES ('240', '52', '18', '56');
INSERT INTO `qc_question_result` VALUES ('241', '55', '19', '56');
INSERT INTO `qc_question_result` VALUES ('242', '62', '20', '56');
INSERT INTO `qc_question_result` VALUES ('243', '65', '21', '56');
INSERT INTO `qc_question_result` VALUES ('244', '67', '22', '56');
INSERT INTO `qc_question_result` VALUES ('245', '72', '23', '56');
INSERT INTO `qc_question_result` VALUES ('246', '76', '24', '56');
INSERT INTO `qc_question_result` VALUES ('247', '79', '25', '56');
INSERT INTO `qc_question_result` VALUES ('248', '82', '26', '56');
INSERT INTO `qc_question_result` VALUES ('249', '83', '26', '56');
INSERT INTO `qc_question_result` VALUES ('250', '85', '27', '56');
INSERT INTO `qc_question_result` VALUES ('251', '88', '27', '56');
INSERT INTO `qc_question_result` VALUES ('252', '89', '28', '56');
INSERT INTO `qc_question_result` VALUES ('253', '90', '28', '56');
INSERT INTO `qc_question_result` VALUES ('254', '93', '29', '56');
INSERT INTO `qc_question_result` VALUES ('255', '94', '29', '56');
INSERT INTO `qc_question_result` VALUES ('256', '97', '30', '56');
INSERT INTO `qc_question_result` VALUES ('257', '98', '30', '56');
INSERT INTO `qc_question_result` VALUES ('258', '101', '31', '56');
INSERT INTO `qc_question_result` VALUES ('259', '104', '31', '56');
INSERT INTO `qc_question_result` VALUES ('260', '106', '32', '56');
INSERT INTO `qc_question_result` VALUES ('261', '32', '13', '57');
INSERT INTO `qc_question_result` VALUES ('262', '36', '14', '57');
INSERT INTO `qc_question_result` VALUES ('263', '39', '15', '57');
INSERT INTO `qc_question_result` VALUES ('264', '44', '16', '57');
INSERT INTO `qc_question_result` VALUES ('265', '48', '17', '57');
INSERT INTO `qc_question_result` VALUES ('266', '54', '18', '57');
INSERT INTO `qc_question_result` VALUES ('267', '56', '19', '57');
INSERT INTO `qc_question_result` VALUES ('268', '60', '20', '57');
INSERT INTO `qc_question_result` VALUES ('269', '65', '21', '57');
INSERT INTO `qc_question_result` VALUES ('270', '69', '22', '57');
INSERT INTO `qc_question_result` VALUES ('271', '70', '23', '57');
INSERT INTO `qc_question_result` VALUES ('272', '74', '24', '57');
INSERT INTO `qc_question_result` VALUES ('273', '78', '25', '57');
INSERT INTO `qc_question_result` VALUES ('274', '81', '26', '57');
INSERT INTO `qc_question_result` VALUES ('275', '83', '26', '57');
INSERT INTO `qc_question_result` VALUES ('276', '86', '27', '57');
INSERT INTO `qc_question_result` VALUES ('277', '90', '28', '57');
INSERT INTO `qc_question_result` VALUES ('278', '93', '29', '57');
INSERT INTO `qc_question_result` VALUES ('279', '97', '30', '57');
INSERT INTO `qc_question_result` VALUES ('280', '100', '30', '57');
INSERT INTO `qc_question_result` VALUES ('281', '102', '31', '57');
INSERT INTO `qc_question_result` VALUES ('282', '105', '32', '57');
INSERT INTO `qc_question_result` VALUES ('283', '33', '13', '58');
INSERT INTO `qc_question_result` VALUES ('284', '36', '14', '58');
INSERT INTO `qc_question_result` VALUES ('285', '39', '15', '58');
INSERT INTO `qc_question_result` VALUES ('286', '43', '16', '58');
INSERT INTO `qc_question_result` VALUES ('287', '47', '17', '58');
INSERT INTO `qc_question_result` VALUES ('288', '54', '18', '58');
INSERT INTO `qc_question_result` VALUES ('289', '57', '19', '58');
INSERT INTO `qc_question_result` VALUES ('290', '60', '20', '58');
INSERT INTO `qc_question_result` VALUES ('291', '63', '21', '58');
INSERT INTO `qc_question_result` VALUES ('292', '66', '22', '58');
INSERT INTO `qc_question_result` VALUES ('293', '70', '23', '58');
INSERT INTO `qc_question_result` VALUES ('294', '74', '24', '58');
INSERT INTO `qc_question_result` VALUES ('295', '78', '25', '58');
INSERT INTO `qc_question_result` VALUES ('296', '81', '26', '58');
INSERT INTO `qc_question_result` VALUES ('297', '83', '26', '58');
INSERT INTO `qc_question_result` VALUES ('298', '87', '27', '58');
INSERT INTO `qc_question_result` VALUES ('299', '92', '28', '58');
INSERT INTO `qc_question_result` VALUES ('300', '95', '29', '58');
INSERT INTO `qc_question_result` VALUES ('301', '99', '30', '58');
INSERT INTO `qc_question_result` VALUES ('302', '103', '31', '58');
INSERT INTO `qc_question_result` VALUES ('303', '106', '32', '58');
INSERT INTO `qc_question_result` VALUES ('304', '33', '13', '59');
INSERT INTO `qc_question_result` VALUES ('305', '38', '14', '59');
INSERT INTO `qc_question_result` VALUES ('306', '42', '15', '59');
INSERT INTO `qc_question_result` VALUES ('307', '45', '16', '59');
INSERT INTO `qc_question_result` VALUES ('308', '48', '17', '59');
INSERT INTO `qc_question_result` VALUES ('309', '53', '18', '59');
INSERT INTO `qc_question_result` VALUES ('310', '56', '19', '59');
INSERT INTO `qc_question_result` VALUES ('311', '60', '20', '59');
INSERT INTO `qc_question_result` VALUES ('312', '65', '21', '59');
INSERT INTO `qc_question_result` VALUES ('313', '69', '22', '59');
INSERT INTO `qc_question_result` VALUES ('314', '71', '23', '59');
INSERT INTO `qc_question_result` VALUES ('315', '77', '24', '59');
INSERT INTO `qc_question_result` VALUES ('316', '79', '25', '59');
INSERT INTO `qc_question_result` VALUES ('317', '81', '26', '59');
INSERT INTO `qc_question_result` VALUES ('318', '84', '26', '59');
INSERT INTO `qc_question_result` VALUES ('319', '85', '27', '59');
INSERT INTO `qc_question_result` VALUES ('320', '90', '28', '59');
INSERT INTO `qc_question_result` VALUES ('321', '93', '29', '59');
INSERT INTO `qc_question_result` VALUES ('322', '96', '29', '59');
INSERT INTO `qc_question_result` VALUES ('323', '97', '30', '59');
INSERT INTO `qc_question_result` VALUES ('324', '100', '30', '59');
INSERT INTO `qc_question_result` VALUES ('325', '103', '31', '59');
INSERT INTO `qc_question_result` VALUES ('326', '105', '32', '59');
INSERT INTO `qc_question_result` VALUES ('327', '107', '32', '59');
INSERT INTO `qc_question_result` VALUES ('328', '33', '13', '60');
INSERT INTO `qc_question_result` VALUES ('329', '37', '14', '60');
INSERT INTO `qc_question_result` VALUES ('330', '40', '15', '60');
INSERT INTO `qc_question_result` VALUES ('331', '46', '16', '60');
INSERT INTO `qc_question_result` VALUES ('332', '48', '17', '60');
INSERT INTO `qc_question_result` VALUES ('333', '51', '18', '60');
INSERT INTO `qc_question_result` VALUES ('334', '55', '19', '60');
INSERT INTO `qc_question_result` VALUES ('335', '62', '20', '60');
INSERT INTO `qc_question_result` VALUES ('336', '64', '21', '60');
INSERT INTO `qc_question_result` VALUES ('337', '66', '22', '60');
INSERT INTO `qc_question_result` VALUES ('338', '70', '23', '60');
INSERT INTO `qc_question_result` VALUES ('339', '77', '24', '60');
INSERT INTO `qc_question_result` VALUES ('340', '80', '25', '60');
INSERT INTO `qc_question_result` VALUES ('341', '81', '26', '60');
INSERT INTO `qc_question_result` VALUES ('342', '82', '26', '60');
INSERT INTO `qc_question_result` VALUES ('343', '85', '27', '60');
INSERT INTO `qc_question_result` VALUES ('344', '86', '27', '60');
INSERT INTO `qc_question_result` VALUES ('345', '89', '28', '60');
INSERT INTO `qc_question_result` VALUES ('346', '93', '29', '60');
INSERT INTO `qc_question_result` VALUES ('347', '97', '30', '60');
INSERT INTO `qc_question_result` VALUES ('348', '100', '30', '60');
INSERT INTO `qc_question_result` VALUES ('349', '104', '31', '60');
INSERT INTO `qc_question_result` VALUES ('350', '106', '32', '60');

-- ----------------------------
-- Table structure for qc_tixian
-- ----------------------------
DROP TABLE IF EXISTS `qc_tixian`;
CREATE TABLE `qc_tixian` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) NOT NULL,
  `balance` decimal(8,2) NOT NULL default '0.00' COMMENT '提现金额',
  `tixian_time` datetime NOT NULL,
  `realName` varchar(20) NOT NULL,
  `account` varchar(30) NOT NULL COMMENT '提现账户',
  `account_type` int(11) NOT NULL COMMENT '账户类型',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_tixian
-- ----------------------------

-- ----------------------------
-- Table structure for qc_user
-- ----------------------------
DROP TABLE IF EXISTS `qc_user`;
CREATE TABLE `qc_user` (
  `id` int(11) NOT NULL auto_increment,
  `nickname` varchar(50) NOT NULL COMMENT '用户昵称',
  `phone` varchar(11) NOT NULL COMMENT '用户电话',
  `openid` varchar(50) NOT NULL COMMENT '用户绑定微信openid',
  `canteen_id` int(11) NOT NULL COMMENT '用户绑定餐厅id',
  `qingcai_balance` decimal(8,2) NOT NULL default '0.00' COMMENT '清菜账户余额',
  `canteen_balance` decimal(8,2) NOT NULL default '0.00' COMMENT '食堂余额',
  `pay_password` varchar(100) default NULL,
  `is_without_pwd` int(1) NOT NULL default '0' COMMENT '是否小额免支付密码，0 不是  1 是',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qc_user
-- ----------------------------
INSERT INTO `qc_user` VALUES ('1', '罗忠涛', '13112345678', 'oBpoJuEOXqA03jvCpTvyKxCYsee4', '1', '1000.00', '1000.00', '123456', '0');
INSERT INTO `qc_user` VALUES ('3', '王浩', '13651764386', '12345', '1', '976.00', '1000.00', '', '0');
INSERT INTO `qc_user` VALUES ('4', 'wanghao', '13573113203', '12345', '1', '788.00', '876.00', 'e10adc3949ba59abbe56e057f20f883e', '0');
INSERT INTO `qc_user` VALUES ('5', '小明', '13573113204', '12345', '1', '1000.00', '1000.00', null, '0');
INSERT INTO `qc_user` VALUES ('6', '小明', '13573113204', '12345', '1', '0.00', '0.00', null, '0');
INSERT INTO `qc_user` VALUES ('7', '清菜', '13573113205', '12345', '1', '0.00', '0.00', null, '0');
INSERT INTO `qc_user` VALUES ('8', '小红', '13573113206', '12345', '1', '0.00', '0.00', null, '0');
INSERT INTO `qc_user` VALUES ('9', 'tianwang', '13573113288', '12345', '1', '0.00', '0.00', null, '0');
INSERT INTO `qc_user` VALUES ('10', 'dd', '13573113255', '12345', '1', '0.00', '0.00', null, '0');
INSERT INTO `qc_user` VALUES ('11', '小明', '13573113207', '12345', '1', '0.00', '0.00', null, '0');
INSERT INTO `qc_user` VALUES ('12', 'luo', '13573113248', '12345', '1', '0.00', '0.00', null, '0');
