/*
 Navicat Premium Data Transfer

 Source Server         : mariaDB
 Source Server Type    : MariaDB
 Source Server Version : 100412
 Source Host           : localhost:3306
 Source Schema         : spring_family_test

 Target Server Type    : MariaDB
 Target Server Version : 100412
 File Encoding         : 65001

 Date: 16/05/2020 12:29:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bar
-- ----------------------------
DROP TABLE IF EXISTS `bar`;
CREATE TABLE `bar`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bar
-- ----------------------------
INSERT INTO `bar` VALUES (1, 'ccc');

SET FOREIGN_KEY_CHECKS = 1;
