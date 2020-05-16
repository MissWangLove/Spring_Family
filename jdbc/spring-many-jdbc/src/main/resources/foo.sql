/*
 Navicat Premium Data Transfer

 Source Server         : mariaDB
 Source Server Type    : MariaDB
 Source Server Version : 100412
 Source Host           : localhost:3306
 Source Schema         : spring_family

 Target Server Type    : MariaDB
 Target Server Version : 100412
 File Encoding         : 65001

 Date: 16/05/2020 12:29:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for foo
-- ----------------------------
DROP TABLE IF EXISTS `foo`;
CREATE TABLE `foo`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of foo
-- ----------------------------
INSERT INTO `foo` VALUES (1, 'aaa');
INSERT INTO `foo` VALUES (2, 'bbb');

SET FOREIGN_KEY_CHECKS = 1;
