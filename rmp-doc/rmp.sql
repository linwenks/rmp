/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : localhost:3306
 Source Schema         : rmp

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 07/10/2018 20:47:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录名称',
  `phone` bigint(11) NOT NULL COMMENT '手机号',
  `sex` int(1) DEFAULT NULL COMMENT '性别（0:女 1:男）',
  `birthday` int(8) DEFAULT NULL COMMENT '生日',
  `address` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `head_pic` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '头像',
  `custermer_area_id` int(11) DEFAULT NULL COMMENT '所在区域ID',
  `vip` int(2) NOT NULL DEFAULT 0 COMMENT 'vip等级',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`, `phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 基础' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_customer_family
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_family`;
CREATE TABLE `t_customer_family`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `relationship` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '关系',
  `real_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '真实姓名',
  `birthday` int(8) DEFAULT NULL COMMENT '生日',
  `phone` bigint(11) DEFAULT NULL COMMENT '手机',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 家庭' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_customer_hobby
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_hobby`;
CREATE TABLE `t_customer_hobby`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `type` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类',
  `content` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_id`(`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 兴趣爱好' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_customer_job
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_job`;
CREATE TABLE `t_customer_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `industry_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '行业名称',
  `position_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '职位名称',
  `company_name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '公司名称',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_id`(`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 工作' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_customer_maintain
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_maintain`;
CREATE TABLE `t_customer_maintain`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `maintain` int(1) NOT NULL COMMENT '维护（0:手动 1:自动）',
  `frequency` int(1) NOT NULL COMMENT '频率',
  `count` int(3) NOT NULL COMMENT '次数',
  `budget` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '预算',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_id`(`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 维护设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_customer_memorial_day
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_memorial_day`;
CREATE TABLE `t_customer_memorial_day`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `memorial_day` bigint(8) DEFAULT NULL COMMENT '日期',
  `remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 纪念日' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_customer_problem
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_problem`;
CREATE TABLE `t_customer_problem`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `type` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类',
  `content` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_id`(`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 可能问题' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_customer_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_relation`;
CREATE TABLE `t_customer_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL,
  `relationship` int(2) NOT NULL DEFAULT 0 COMMENT '关系（0其他1家人2亲戚3朋友4同学5同事6客户7熟人8陌生人）',
  `intimacy` int(2) NOT NULL DEFAULT 0 COMMENT '亲密（0不详1很亲近2一般亲近3正常交往4点头之交）',
  `importance` int(2) NOT NULL DEFAULT 0 COMMENT '重要（0不重要1重要2非常重要（vip））',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_id`(`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_gift
-- ----------------------------
DROP TABLE IF EXISTS `t_gift`;
CREATE TABLE `t_gift`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `gift_category_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '礼品类别ID',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `content` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '内容',
  `img` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图片',
  `price` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '价格',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `gift_category_id`(`gift_category_id`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '礼品 类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_gift_category
-- ----------------------------
DROP TABLE IF EXISTS `t_gift_category`;
CREATE TABLE `t_gift_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '父ID',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `img` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `pid`(`pid`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '礼品 类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `create_time` bigint(14) DEFAULT NULL,
  `update_time` bigint(14) DEFAULT NULL,
  `delete_time` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `create_time` bigint(14) DEFAULT NULL,
  `update_time` bigint(14) DEFAULT NULL,
  `delete_time` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_role_id` bigint(20) NOT NULL,
  `sys_menu_id` bigint(20) NOT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `create_time` bigint(14) DEFAULT NULL,
  `update_time` bigint(14) DEFAULT NULL,
  `delete_time` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_role_id`(`sys_role_id`, `sys_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `real_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `create_time` bigint(14) DEFAULT NULL,
  `update_time` bigint(14) DEFAULT NULL,
  `delete_time` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_user_id` bigint(20) NOT NULL,
  `sys_role_id` bigint(20) NOT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `create_time` bigint(14) DEFAULT NULL,
  `update_time` bigint(14) DEFAULT NULL,
  `delete_time` bigint(14) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_user_id`(`sys_user_id`, `sys_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `login_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录名称',
  `login_pwd` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录密码',
  `phone` bigint(11) NOT NULL COMMENT '手机号',
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '昵称',
  `head_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '头像',
  `pay_pwd` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '支付密码',
  `account` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '账户余额',
  `last_login_time` bigint(14) DEFAULT NULL COMMENT '上次登录时间',
  `current_area_id` int(11) DEFAULT NULL COMMENT '当前区域ID',
  `wx_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信ID',
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标记',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`login_name`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `wx_id`(`wx_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_user_customer`;
CREATE TABLE `t_user_customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`, `customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 客户 关联' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
