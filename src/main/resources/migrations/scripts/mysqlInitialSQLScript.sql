/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : tzyc

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2014-12-19 23:39:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `basic_menu`
-- ----------------------------
DROP TABLE IF EXISTS `basic_menu`;
CREATE TABLE `basic_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `link_url` varchar(300) NOT NULL,
  `order_number` int(11) DEFAULT '999999',
  `remark` varchar(200) DEFAULT NULL,
  `full_permission` varchar(300) DEFAULT NULL,
  `read_permission` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `basic_menu_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `basic_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_menu
-- ----------------------------
INSERT INTO `basic_menu` VALUES ('1', 'root', null, '', '999999', null, null, null);
INSERT INTO `basic_menu` VALUES ('2', '菜单管理', '1', '', '2', '', '[]', null);
INSERT INTO `basic_menu` VALUES ('3', '开发人员管理', '1', '', '3', '', null, null);
INSERT INTO `basic_menu` VALUES ('4', '模板子系统', '1', '', '4', '', null, null);
INSERT INTO `basic_menu` VALUES ('5', '功能管理', '2', '', '1', '', '[]', null);
INSERT INTO `basic_menu` VALUES ('6', '菜单资源管理', '5', 'menu/manage', '2', '', null, null);
INSERT INTO `basic_menu` VALUES ('7', '角色管理', '5', 'role/manage', '3', '', null, null);
INSERT INTO `basic_menu` VALUES ('8', '用户管理', '5', 'user/manage', '4', '', null, null);
INSERT INTO `basic_menu` VALUES ('9', '组织机构管理', '5', 'organization/manage', '1', '', null, null);

-- ----------------------------
-- Table structure for `basic_organization`
-- ----------------------------
DROP TABLE IF EXISTS `basic_organization`;
CREATE TABLE `basic_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `order_number` int(11) DEFAULT '999999',
  `remark` varchar(200) DEFAULT NULL,
  `organization_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `basic_organization_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `basic_organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of basic_organization
-- ----------------------------
INSERT INTO `basic_organization` VALUES ('1', 'CMStudio', null, '999999', '组织架构备注', '1');
INSERT INTO `basic_organization` VALUES ('36', 'Charmyin', '1', '3', 'asdf', '1');

-- ----------------------------
-- Table structure for `shiro_role`
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role` (
  `name` varchar(50) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `organization_id` int(11) DEFAULT NULL,
  `permission` varchar(300) DEFAULT NULL,
  `menu` varchar(500) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `state` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`name`),
  KEY `organization_id` (`organization_id`),
  CONSTRAINT `shiro_role_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `basic_organization` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES ('developer', '开发者角色', '1', '[{\"permission\":\"menu:getallxx\",\"remark\":\"eeee\"}]', '1,2,5,9,6,7,8,3,4', '开发者角色', '1');

-- ----------------------------
-- Table structure for `shiro_user`
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `organization_id` int(11) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `passphrase` varchar(100) NOT NULL,
  `salt` varchar(100) NOT NULL,
  `state` tinyint(1) DEFAULT '1',
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `remark` varchar(200) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `cellphone` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_id` (`login_id`),
  UNIQUE KEY `email` (`email`),
  KEY `organization_id` (`organization_id`),
  CONSTRAINT `shiro_user_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `basic_organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
INSERT INTO `shiro_user` VALUES ('1', 'admin', 'admin', '1', 'bc5e08e6-3ffc-4e74-89a1-bb480342b2ce@default.com', 'wHcjhQc5KwW7zSO5OfQ6FRqvpcE2Zw6FkVHJRcnE57AoeaPaZ5DboqYbHGBjOKDE8Rl9+bclnF6lQ4y0D6GiaA==', 'qpOvViSVIY7XyYMpAsJHnQ==', '1', '2014-12-19 22:03:12', '', '1', '');

-- ----------------------------
-- Table structure for `shiro_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user_role`;
CREATE TABLE `shiro_user_role` (
  `user_id` int(11) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`role_name`),
  KEY `role_name` (`role_name`),
  CONSTRAINT `shiro_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `shiro_user` (`id`),
  CONSTRAINT `shiro_user_role_ibfk_2` FOREIGN KEY (`role_name`) REFERENCES `shiro_role` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shiro_user_role
-- ----------------------------
INSERT INTO `shiro_user_role` VALUES ('1', 'developer');

-- ----------------------------
-- Table structure for `tb_7s_evaluate_item`
-- ----------------------------
DROP TABLE IF EXISTS `tb_7s_evaluate_item`;
CREATE TABLE `tb_7s_evaluate_item` (
  `ID` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '评价项目编号',
  `NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '评价项目名称',
  `TYPE_ID` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '7S检查评价分类ID。关联TB_7S_EVALUATE_TYPE',
  `REMARK` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `CREATE_TIMESTAMP` bigint(20) DEFAULT NULL COMMENT '创建时间戳',
  `COID` int(11) DEFAULT NULL,
  `RECORD_STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标记。0：正常 ；1：删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_7s_evaluate_item
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_7s_evaluate_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_7s_evaluate_type`;
CREATE TABLE `tb_7s_evaluate_type` (
  `ID` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '主键',
  `CODE` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '评价分类编号',
  `NAME` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '评价分类名称',
  `REMARK` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  `CREATE_TIMESTAMP` bigint(20) DEFAULT NULL COMMENT '创建时间戳',
  `COID` int(11) DEFAULT NULL,
  `RECORD_STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '逻辑删除标记。0：正常 ；1：删除',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_7s_evaluate_type
-- ----------------------------
