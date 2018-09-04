/*
Navicat MySQL Data Transfer

Source Server         : 开发
Source Server Version : 80000
Source Host           : 127.0.0.1:3306
Source Database       : users

Target Server Type    : MYSQL
Target Server Version : 80000
File Encoding         : 65001

Date: 2018-09-04 13:12:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `year` int(10) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createPerson` varchar(100) DEFAULT NULL,
  `createPersonId` varchar(32) DEFAULT NULL,
  `lastModifyPerson` varchar(100) DEFAULT NULL,
  `lastModifyPersonId` varchar(32) DEFAULT NULL,
  `lastModifyTime` datetime DEFAULT NULL,
  `isdel` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('cd6f0a7b27784400b96dd801fbbc35f0', 'tch', '2018-09-04 12:06:19', '1', '2018-09-04 12:06:19', '', '', '', '', '2018-09-04 12:06:19', 'exist');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'user');
