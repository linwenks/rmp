/*
 Navicat Premium Data Transfer

 Source Server         : r
 Source Server Type    : MySQL
 Source Server Version : 100310
 Source Host           : localhost:3306
 Source Schema         : rmp

 Target Server Type    : MySQL
 Target Server Version : 100310
 File Encoding         : 65001

 Date: 31/01/2019 09:40:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `real_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '真实名称',
  `pinyin` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '拼音',
  `phone` bigint(11) NOT NULL COMMENT '手机号',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别（0:女 1:男）',
  `birthday` int(8) NULL DEFAULT NULL COMMENT '生日',
  `head_pic` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '头像',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '区域',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址',
  `vip` int(2) NOT NULL DEFAULT 0 COMMENT 'vip等级',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标签',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `is_delete`(`is_delete`, `user_id`, `phone`, `real_name`, `pinyin`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 基础' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES (84, 10, '测试1', 'cs1', 13965422548, 1, 19780110, 'http://47.94.5.205/img/head_pic/default.jpg', '重庆市江北区北城天街33号', NULL, 0, NULL, 0, 6, 20190110204312, 20190117172024);
INSERT INTO `t_customer` VALUES (85, 9, '1', '1', 13958327480, 0, 20180116, 'http://47.94.5.205/img/head_pic/default.jpg', '?????????', 'rr', 0, NULL, 0, 19, 20190110205325, 20190130101654);
INSERT INTO `t_customer` VALUES (86, 10, '测试2', 'cs2', 13854211457, 0, 19820313, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 6, 20190110205804, 20190117172040);
INSERT INTO `t_customer` VALUES (87, 10, '不测试3', 'bcs3', 17425477458, 1, 19910819, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 6, 20190110210319, 20190111185724);
INSERT INTO `t_customer` VALUES (88, 11, 'gg', 'gg', 13576567866, 1, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 0, 20190110211227, NULL);
INSERT INTO `t_customer` VALUES (89, 11, 'ff', 'ff', 13467656787, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 0, 20190110211347, NULL);
INSERT INTO `t_customer` VALUES (90, 11, 'hj', 'hj', 13454345456, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 0, 20190110211405, NULL);
INSERT INTO `t_customer` VALUES (91, 9, '??', '??', 13958327481, 0, 20190114, 'http://47.94.5.205/img/head_pic/default.jpg', '?????????', 'rr', 0, NULL, 0, 8, 20190110212504, 20190110213715);
INSERT INTO `t_customer` VALUES (92, 11, 'ff', 'ff', 13658321232, 0, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 0, 20190110212623, NULL);
INSERT INTO `t_customer` VALUES (93, 11, 'dd', 'dd', 13565434567, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 2, 20190110212732, 20190110220620);
INSERT INTO `t_customer` VALUES (94, 11, 'rr', 'rr', 13658324877, 1, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 9, 20190110213036, 20190130105223);
INSERT INTO `t_customer` VALUES (95, 11, 'ccc', 'ccc', 13658327483, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 3, 20190112203903, 20190124184441);
INSERT INTO `t_customer` VALUES (96, 11, '哈哈哈', 'hhh', 13658327489, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 0, 20190112215708, NULL);

-- ----------------------------
-- Table structure for t_customer_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_detail`;
CREATE TABLE `t_customer_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '备注',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_id`(`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 明细' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_customer_family
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_family`;
CREATE TABLE `t_customer_family`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `relationship` int(1) NOT NULL COMMENT '关系（1:父母、2:配偶、3:子女）',
  `real_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '真实姓名',
  `birthday` int(8) NULL DEFAULT NULL COMMENT '生日',
  `phone` bigint(11) NULL DEFAULT NULL COMMENT '手机',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '区域',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `is_delete`(`is_delete`, `customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 家庭' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_family
-- ----------------------------
INSERT INTO `t_customer_family` VALUES (49, 84, 1, '测试父', 19600515, 13254122541, '', NULL, 0, 0, 20190110204520, NULL);
INSERT INTO `t_customer_family` VALUES (50, 85, 1, '爸爸', 20180116, 15120000000, '', NULL, 0, 1, 20190110214248, 20190130101654);
INSERT INTO `t_customer_family` VALUES (51, 87, 1, '测试3', 19630107, 13854255412, '', NULL, 0, 0, 20190111185548, NULL);

-- ----------------------------
-- Table structure for t_customer_hobby
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_hobby`;
CREATE TABLE `t_customer_hobby`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `interest` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '兴趣（美食、旅游、美容美发、购物、按摩温泉、影视、运动、汽车、家居装饰、宠物、KTV、社交、养生、投资理财、营销、IT互联网、演出、外语学习、体验游戏、网络游戏）',
  `diet` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '饮食（1:川湘菜、2:江浙菜、3:粤菜、4:北方菜、5:日韩料理、6:西餐、7:东南亚菜、8:火锅、9:海鲜、10:素食、11:烧烤、12:甜点）',
  `taste` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '口味（1:甜、2:辣、3:酸、4:苦）',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 兴趣爱好' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_hobby
-- ----------------------------
INSERT INTO `t_customer_hobby` VALUES (14, 84, '1,3,6,10,13', '1,2,5,6', '2', 0, 0, 20190110204625, NULL);
INSERT INTO `t_customer_hobby` VALUES (15, 86, '2,11,14', '2', '2', 0, 0, 20190110205931, NULL);
INSERT INTO `t_customer_hobby` VALUES (16, 87, '1,2,7,11,14', '2,3', '2', 0, 0, 20190111185705, NULL);

-- ----------------------------
-- Table structure for t_customer_job
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_job`;
CREATE TABLE `t_customer_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `industry` int(3) NULL DEFAULT NULL COMMENT '行业（计算机硬软件、互联网/电子商务/网游、IT管理、通信、电子/电器/半导体、\r\n财务/审计/税务、金融/投资、银行/保险、工程/机械、能源/原材料、汽车及零配件制造、汽车销售服务、服装/纺织、轻工产品制造、食品生产、贸易、物流/仓储、生物/制药、化工、医院/医疗/护理、广告媒体、市场/营销、影视、编辑出版、艺术/设计、建筑与装潢、房地产开发、房地产销售与中介、物业、人力资源、咨询/顾问、律师/法务、教师/培训、科研、餐饮服务、酒店旅游、美容保健、百货零售、交通运输、家政/生活服务、政府/公务员、翻译、农林牧渔、印刷包装、运动健身、休闲娱乐、其他\r\n）',
  `company_name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公司名称',
  `department_name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门名称',
  `position` int(3) NULL DEFAULT NULL COMMENT '职业（工薪族、个体户、企业主、学生、公务员、自由职业、无业）',
  `office` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '职位',
  `phone` bigint(11) NULL DEFAULT NULL COMMENT '工作电话',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '区域',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `is_delete`(`is_delete`, `customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 工作' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_job
-- ----------------------------
INSERT INTO `t_customer_job` VALUES (56, 84, 2, NULL, NULL, 1, NULL, NULL, NULL, NULL, 0, 1, 20190110204312, 20190110204757);
INSERT INTO `t_customer_job` VALUES (57, 85, 6, 'rrr', NULL, 6, NULL, NULL, '??????????199?', 'rrrr', 0, 13, 20190110205410, 20190110213845);
INSERT INTO `t_customer_job` VALUES (58, 86, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 20190110205804, NULL);
INSERT INTO `t_customer_job` VALUES (59, 87, 3, '测试公司3', NULL, 2, 'CTO', NULL, NULL, NULL, 0, 0, 20190110210505, NULL);
INSERT INTO `t_customer_job` VALUES (60, 88, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 20190110211227, NULL);
INSERT INTO `t_customer_job` VALUES (61, 90, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 20190110211405, NULL);
INSERT INTO `t_customer_job` VALUES (62, 91, 6, 'rrr', NULL, 6, NULL, NULL, '??????????199?', 'rrrr', 0, 7, 20190110212522, 20190110213715);
INSERT INTO `t_customer_job` VALUES (63, 92, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 20190110212623, NULL);
INSERT INTO `t_customer_job` VALUES (64, 93, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 20190110212732, NULL);
INSERT INTO `t_customer_job` VALUES (65, 94, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 20190110213036, NULL);
INSERT INTO `t_customer_job` VALUES (66, 95, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 20190112203903, NULL);
INSERT INTO `t_customer_job` VALUES (67, 96, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 20190112215708, NULL);

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
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `customer_id`(`is_delete`, `customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 维护设置' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_customer_memorial_day
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_memorial_day`;
CREATE TABLE `t_customer_memorial_day`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `occur_type` int(1) NOT NULL COMMENT '发生类型（1:1次 2:每年 3:每月 4:每周）',
  `occur_date` int(8) NOT NULL COMMENT '发生日期',
  `advance_type` int(2) NOT NULL COMMENT '提前类型（1:1天 2:2天 3:3天 4:5天 5:1周 6:2周 7:1月）',
  `remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 86 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 纪念日' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_memorial_day
-- ----------------------------
INSERT INTO `t_customer_memorial_day` VALUES (74, 84, '测试1', 1, 20190114, 7, NULL, 0, 0, 20190110204608, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (75, 86, '测试2', 4, 7, 4, NULL, 0, 0, 20190110205917, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (76, 94, 'dd3', 1, 20190114, 3, NULL, 0, 1, 20190110213815, 20190110213914);
INSERT INTO `t_customer_memorial_day` VALUES (77, 94, 'dd', 1, 20190114, 1, NULL, 0, 0, 20190110213827, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (78, 94, 'dd2', 1, 20190114, 2, NULL, 0, 0, 20190110213852, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (79, 93, '测试提醒', 1, 20190110, 1, NULL, 0, 0, 20190110220620, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (80, 87, '特殊3', 4, 1, 3, NULL, 0, 0, 20190111185649, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (81, 86, '测试2a', 1, 20190121, 1, NULL, 0, 1, 20190117170902, 20190117171410);
INSERT INTO `t_customer_memorial_day` VALUES (82, 94, '测试11', 1, 20190124, 1, NULL, 0, 0, 20190124214903, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (83, 94, '测试11111', 1, 20190131, 1, NULL, 1, 1, 20190130102827, 20190130105011);
INSERT INTO `t_customer_memorial_day` VALUES (84, 94, '改红果果', 1, 20190202, 1, NULL, 0, 0, 20190130105048, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (85, 94, '皇皇巨著', 1, 20190131, 1, NULL, 0, 0, 20190130105223, NULL);

-- ----------------------------
-- Table structure for t_customer_problem
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_problem`;
CREATE TABLE `t_customer_problem`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `health` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '健康问题（1:心脏病 2:动脉硬化 3:高血压 4:高血脂 5:肠胃病 6:糖尿病 7:关节炎 8:肥胖症 9:胆结石 10:肾病 11:精神问题 12:脸部痘痕 13:五官瑕疵）',
  `life` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '生活问题（1:资金缺乏 2:寻找工作 3:事业发展 4:感情困扰 5:子女学习 6:法律问题 7:税务）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `customer_id`(`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 可能问题' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_problem
-- ----------------------------
INSERT INTO `t_customer_problem` VALUES (12, 84, '1,6,13', '2', NULL, 0, 0, 20190110204632, NULL);
INSERT INTO `t_customer_problem` VALUES (13, 86, '6,9,11', '2', NULL, 0, 0, 20190110205940, NULL);
INSERT INTO `t_customer_problem` VALUES (14, 87, '2,7,10', '1,2', NULL, 0, 0, 20190111185724, NULL);

-- ----------------------------
-- Table structure for t_customer_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_relation`;
CREATE TABLE `t_customer_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL,
  `relationship` int(2) NOT NULL DEFAULT 0 COMMENT '关系（0:其他 1:家人 2:亲戚 3:朋友 4:同学 5:同事 6:客户 7:熟人 8:陌生人）',
  `intimacy` int(2) NOT NULL DEFAULT 0 COMMENT '亲密（0:不详 1:很亲近 2:一般亲近 3:正常交往 4:点头之交）',
  `importance` int(2) NOT NULL DEFAULT 0 COMMENT '重要（0:不重要 1:重要 2:非常重要（vip））',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `customer_id`(`is_delete`, `customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_relation
-- ----------------------------
INSERT INTO `t_customer_relation` VALUES (21, 93, 4, 0, 0, 0, 0, 20190110212758, NULL);
INSERT INTO `t_customer_relation` VALUES (22, 87, 6, 1, 1, 0, 0, 20190111185433, NULL);
INSERT INTO `t_customer_relation` VALUES (23, 95, 0, 0, 1, 0, 2, 20190112203914, 20190124184441);
INSERT INTO `t_customer_relation` VALUES (24, 84, 1, 2, 0, 0, 0, 20190117172024, NULL);
INSERT INTO `t_customer_relation` VALUES (25, 86, 3, 3, 1, 0, 0, 20190117172040, NULL);
INSERT INTO `t_customer_relation` VALUES (26, 85, 4, 4, 2, 0, 2, 20190130101624, 20190130101639);

-- ----------------------------
-- Table structure for t_gift
-- ----------------------------
DROP TABLE IF EXISTS `t_gift`;
CREATE TABLE `t_gift`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `customer_phone` bigint(11) NOT NULL COMMENT '客户手机',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '类型（0:自动 1:手动）',
  `cause` int(1) NOT NULL COMMENT '事由（0:生日 1:家人生日 2:节日 3:特殊事件日子）',
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '赠言',
  `receive_state` int(1) NOT NULL DEFAULT 0 COMMENT '接收状态（0:未接收 1:已接收）',
  `receive_user_id` bigint(20) NULL DEFAULT NULL COMMENT '接收人用户ID（通过手机号关联）',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 礼品赠送' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_gift_category
-- ----------------------------
DROP TABLE IF EXISTS `t_gift_category`;
CREATE TABLE `t_gift_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '父ID',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `img` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `pid`(`pid`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '礼品 类别' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_gift_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_gift_detail`;
CREATE TABLE `t_gift_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `gift_id` bigint(20) NOT NULL COMMENT '用户 礼品赠送ID',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `sales_volume` int(11) NOT NULL COMMENT '销量',
  `price` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '价格',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 礼品赠送' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `goods_category_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '商品类别ID',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `tag` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标签',
  `content` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '内容',
  `img` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '图片',
  `stock` int(11) NOT NULL DEFAULT 0 COMMENT '库存',
  `sales_volume_true` int(11) NOT NULL DEFAULT 0 COMMENT '销量真',
  `sales_volume_false` int(11) NOT NULL DEFAULT 0 COMMENT '销量假',
  `price` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '价格',
  `cost_price` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '成本价格',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '状态（0:未上架 1:已上架）',
  `shelf_time` bigint(14) NULL DEFAULT NULL COMMENT '上架时间',
  `create_sys_user_id` bigint(20) NOT NULL COMMENT '创建系统用户ID',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `goods_category_id`(`goods_category_id`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品 类别' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_category`;
CREATE TABLE `t_goods_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '父ID',
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `img` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `pid`(`pid`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '商品 类别' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_phone_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_phone_msg`;
CREATE TABLE `t_phone_msg`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `phone` bigint(11) NOT NULL COMMENT '手机号',
  `code` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `type` int(1) NOT NULL COMMENT '类型（1：注册 2：找回密码）',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态（0:未使用 1:已使用）',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '手机短信' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_site_msg
-- ----------------------------
DROP TABLE IF EXISTS `t_site_msg`;
CREATE TABLE `t_site_msg`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '内容',
  `sys_user_id` bigint(20) NOT NULL COMMENT '系统用户ID',
  `type` int(1) NOT NULL DEFAULT 0 COMMENT '类型（0:个人 1:群发）',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '站内消息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_site_msg_detail
-- ----------------------------
DROP TABLE IF EXISTS `t_site_msg_detail`;
CREATE TABLE `t_site_msg_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `site_msg_id` bigint(20) NOT NULL COMMENT '站内消息ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `state` int(1) NOT NULL DEFAULT 0 COMMENT '状态（0:未读 1:已读）',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '站内消息 明细' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_sys_code
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_code`;
CREATE TABLE `t_sys_code`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pid` bigint(20) NOT NULL DEFAULT 0,
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '序号',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code_key`(`pid`, `key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 176 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_sys_code
-- ----------------------------
INSERT INTO `t_sys_code` VALUES (1, 'CUSTOMER', '客户', 0, 0, '', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (2, 'CUSTOMER_RELATION', '关系', 1, 0, '', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (3, 'CUSTOMER_RELATION_RELATIONSHIP', '关系', 2, 0, '', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (4, '0', '其他', 3, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (5, '1', '家人', 3, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (6, '2', '亲戚', 3, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (7, '3', '朋友', 3, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (8, '4', '同学', 3, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (9, '5', '同事', 3, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (10, '6', '客户', 3, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (11, '7', '熟人', 3, 7, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (12, '8', '陌生人', 3, 8, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (13, 'CUSTOMER_RELATION_INTIMACY', '亲密', 2, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (14, '0', '不详', 13, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (15, '1', '很亲近', 13, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (16, '2', '一般亲近', 13, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (17, '3', '正常交往', 13, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (18, '4', '点头之交', 13, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (19, 'CUSTOMER_RELATION_IMPORTANCE', '重要', 2, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (20, '0', '不重要', 19, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (21, '1', '重要', 19, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (22, '2', '非常重要（vip）', 19, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (23, 'CUSTOMER_PROBLEM', '问题', 1, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (24, 'CUSTOMER_PROBLEM_HEALTH', '健康', 23, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (25, '1', '心脏病', 24, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (26, '2', '动脉硬化', 24, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (27, '3', '高血压', 24, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (28, '4', '高血脂', 24, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (29, '5', '肠胃病', 24, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (30, '6', '糖尿病', 24, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (31, '7', '关节炎', 24, 7, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (32, '8', '肥胖症', 24, 8, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (33, '9', '胆结石', 24, 9, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (34, '10', '肾病', 24, 10, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (35, '11', '精神问题', 24, 11, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (36, '12', '脸部痘痕', 24, 12, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (37, '13', '五官瑕疵', 24, 13, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (38, 'CUSTOMER_PROBLEM_LIFE', '生活', 23, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (39, '1', '资金缺乏', 38, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (40, '2', '寻找工作', 38, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (41, '3', '事业发展', 38, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (42, '4', '感情困扰', 38, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (43, '5', '子女学习', 38, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (44, '6', '法律问题', 38, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (45, '7', '税务', 38, 7, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (46, 'CUSTOMER_HOBBY', '兴趣爱好', 1, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (47, 'CUSTOMER_HOBBY_INTEREST', '兴趣', 46, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (48, '1', '美食', 47, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (49, '2', '旅游', 47, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (50, '3', '美容美发', 47, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (51, '4', '购物', 47, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (52, '5', '按摩温泉', 47, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (53, '6', '影视', 47, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (54, '7', '运动', 47, 7, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (55, '8', '汽车', 47, 8, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (56, '9', '家居装饰', 47, 9, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (57, '10', '宠物', 47, 10, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (58, '11', 'KTV', 47, 11, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (59, '12', '社交', 47, 12, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (60, '13', '养生', 47, 13, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (61, '14', '投资理财', 47, 14, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (62, '15', '营销', 47, 15, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (63, '16', 'IT互联网', 47, 16, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (64, '17', '演出', 47, 17, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (65, '18', '外语学习', 47, 18, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (66, '19', '体验游戏', 47, 19, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (67, '20', '网络游戏', 47, 20, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (68, 'CUSTOMER_HOBBY_DIET', '饮食', 46, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (69, '1', '川湘菜', 68, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (70, '2', '江浙菜', 68, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (71, '3', '粤菜', 68, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (72, '4', '北方菜', 68, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (73, '5', '日韩料理', 68, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (74, '6', '西餐', 68, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (75, '7', '东南亚菜', 68, 7, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (76, '8', '火锅', 68, 8, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (77, '9', '海鲜', 68, 9, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (78, '10', '素食', 68, 10, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (79, '11', '烧烤', 68, 11, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (80, '12', '甜点', 68, 12, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (81, 'CUSTOMER_HOBBY_TASTE', '口味', 46, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (82, '1', '甜', 81, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (83, '2', '辣', 81, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (84, '3', '酸', 81, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (85, '4', '苦', 81, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (86, 'CUSTOMER_JOB', '工作', 1, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (87, 'CUSTOMER_JOB_INDUSTRY', '行业', 86, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (88, '1', '计算机硬软件', 87, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (89, '2', '互联网/电子商务/网游', 87, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (90, '3', 'IT管理', 87, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (91, '4', '通信', 87, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (92, '5', '电子/电器/半导体', 87, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (93, '6', '财务/审计/税务', 87, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (94, '7', '金融/投资', 87, 7, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (95, '8', '银行/保险', 87, 8, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (96, '9', '工程/机械', 87, 9, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (97, '10', '能源/原材料', 87, 10, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (98, '11', '汽车及零配件制造', 87, 11, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (99, '12', '汽车销售服务', 87, 12, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (100, '13', '服装/纺织', 87, 13, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (101, '14', '轻工产品制造', 87, 14, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (102, '15', '食品生产', 87, 15, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (103, '16', '贸易', 87, 16, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (104, '17', '物流/仓储', 87, 17, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (105, '18', '生物/制药', 87, 18, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (106, '19', '化工', 87, 19, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (107, '20', '医院/医疗/护理', 87, 20, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (108, '21', '广告媒体', 87, 21, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (109, '22', '市场/营销', 87, 22, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (110, '23', '影视', 87, 23, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (111, '24', '编辑出版', 87, 24, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (112, '25', '艺术/设计', 87, 25, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (113, '26', '建筑与装潢', 87, 26, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (114, '27', '房地产开发', 87, 27, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (115, '28', '房地产销售与中介', 87, 28, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (116, '29', '物业', 87, 29, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (117, '30', '人力资源', 87, 30, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (118, '31', '咨询/顾问', 87, 31, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (119, '32', '律师/法务', 87, 32, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (120, '33', '教师/培训', 87, 33, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (121, '34', '科研', 87, 34, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (122, '35', '餐饮服务', 87, 35, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (123, '36', '酒店旅游', 87, 36, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (124, '37', '美容保健', 87, 37, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (125, '38', '百货零售', 87, 38, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (126, '39', '交通运输', 87, 39, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (127, '40', '家政/生活服务', 87, 40, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (128, '41', '政府/公务员', 87, 41, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (129, '42', '翻译', 87, 42, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (130, '43', '农林牧渔', 87, 43, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (131, '44', '印刷包装', 87, 44, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (132, '45', '运动健身', 87, 45, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (133, '46', '休闲娱乐', 87, 46, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (134, '47', '其他', 87, 47, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (135, 'CUSTOMER_JOB_POSITION', '职业', 86, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (136, '1', '工薪族', 135, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (137, '2', '个体户', 135, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (138, '3', '企业主', 135, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (139, '4', '学生', 135, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (140, '5', '公务员', 135, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (141, '6', '自由职业', 135, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (142, '7', '无业', 135, 7, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (143, 'CUSTOMER_FAMILY', '家庭', 1, 0, '', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (144, 'CUSTOMER_FAMILY_RELATIONSHIP', '关系', 143, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (145, '1', '父亲', 144, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (146, '2', '母亲', 144, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (147, '3', '老公', 144, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (148, '4', '老婆', 144, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (149, '5', '儿子', 144, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (150, '6', '女儿', 144, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (151, 'CUSTOMER_MEMORIAL_DAY', '纪念日', 1, 0, '', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (152, 'CUSTOMER_MEMORIAL_DAY_OCCUR_TYPE', '发生类型', 151, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (153, '1', '1次', 152, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (154, '2', '每年', 152, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (155, '3', '每月', 152, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (157, '4', '每周', 152, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (158, 'CUSTOMER_MEMORIAL_DAY_ADVANCE_TYPE', '提前类型', 151, 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (159, '1', '1天', 158, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (160, '2', '2天', 158, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (161, '3', '3天', 158, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (162, '4', '4天', 158, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (163, '5', '5天', 158, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (164, '6', '6天', 158, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (165, '7', '7天', 158, 7, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (166, 'CUSTOMER_MAINTAIN', '维护设置', 1, 0, '', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (167, 'CUSTOMER_MAINTAIN_MAINTAIN', '维护', 166, 0, '', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (168, '0', '手动', 167, 0, '', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (169, '1', '自动', 167, 1, '', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (170, 'UPLOAD_TOP_PATH', '/opt/upload', 0, 0, '上传顶级路径', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (171, 'UPLOAD_USER_HEAD_PIC_PATH', '/user/head_pic', 170, 1, '上传用户头像路径', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (172, 'UPLOAD_USER_HEAD_PIC_PATH_TMP', '/tmp/user/head_pic', 170, 2, '上传用户头像路径临时', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (173, 'UPLOAD_CUSTOMER_HEAD_PIC_PATH', '/customer/head_pic', 170, 2, '上传客户头像路径', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (174, 'UPLOAD_CUSTOMER_HEAD_PIC_PATH_TMP', '/tmp/customer/head_pic', 170, 3, '上传客户头像路径临时', 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (175, 'IMG_DOMAIN', 'http://47.94.5.205', 0, 0, '图片域名', 0, 0, NULL, NULL);

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NULL DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `create_time` bigint(14) NULL DEFAULT NULL,
  `update_time` bigint(14) NULL DEFAULT NULL,
  `delete_time` bigint(14) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `create_time` bigint(14) NULL DEFAULT NULL,
  `update_time` bigint(14) NULL DEFAULT NULL,
  `delete_time` bigint(14) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_menu`;
CREATE TABLE `t_sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_role_id` bigint(20) NOT NULL,
  `sys_menu_id` bigint(20) NOT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `create_time` bigint(14) NULL DEFAULT NULL,
  `update_time` bigint(14) NULL DEFAULT NULL,
  `delete_time` bigint(14) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_role_id`(`sys_role_id`, `sys_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色菜单' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名称',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `real_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `create_time` bigint(14) NULL DEFAULT NULL,
  `update_time` bigint(14) NULL DEFAULT NULL,
  `delete_time` bigint(14) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sys_user_id` bigint(20) NOT NULL,
  `sys_role_id` bigint(20) NOT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  `create_time` bigint(14) NULL DEFAULT NULL,
  `update_time` bigint(14) NULL DEFAULT NULL,
  `delete_time` bigint(14) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_user_id`(`sys_user_id`, `sys_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `login_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录名称',
  `login_pwd` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录密码',
  `phone` bigint(11) NULL DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '真实姓名',
  `nick_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '昵称',
  `head_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别（0:女 1:男）',
  `birthday` int(8) NULL DEFAULT NULL COMMENT '生日',
  `pay_pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '支付密码',
  `account` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '账户余额',
  `last_login_time` bigint(14) NULL DEFAULT NULL COMMENT '上次登录时间',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '区域',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址',
  `wx_id` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '微信ID',
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标记',
  `status` int(1) NULL DEFAULT 0 COMMENT '状态（0:未注册 1:已注册）',
  `wx_session_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '微信sessionKey',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`login_name`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `wx_id`(`wx_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (9, '15123815032', '0', 15123815032, NULL, '遮不住的眼', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epz6CO3m7cUvNxIkibSMYO3LqyIIZrJLeA1wUSNcQKPkxCswIFficatmc1CeaCdjRUgHy7bUYJAefvg/132', NULL, NULL, NULL, 0.00, 20190130101550, NULL, NULL, 'oJUjE5D5EPE9HyLrsknXwwxjq5Po', 'd9d3792d2bd348658c321c9a47fda2e5', 1, 'QMPZ7/d2YUFKCnuYuugpkQ==', 0, 18, 20190110203901, 20190130101600);
INSERT INTO `t_user` VALUES (10, '13983100194', '0', 13983100194, NULL, '物随缘我', 'https://wx.qlogo.cn/mmopen/vi_32/Uj8UYOic8oKZRrvBYFMU418vt94CP310Q5r1k2VB4JOOlUjYPOeDjdTyVkO9nwesaJDNdAib6C2sQ8siarxibWNS4A/132', NULL, NULL, NULL, 0.00, 20190117163448, NULL, NULL, 'oJUjE5OAaBvVxkvgF8JSanMk20Y8', '8b1c0779f8804dedb6411576e8491adc', 1, 'OhoIT5QmSDeLaOWNn9eZvw==', 0, 51, 20190110204003, 20190124113506);
INSERT INTO `t_user` VALUES (11, '13658327483', '0', 13658327483, 'sdsdd', '蓝天', 'https://wx.qlogo.cn/mmopen/vi_32/icGYw95jPibIboG9OAoniboicLVM6W1SCHiaWsaNTe1pMCxicGAVKu9OyMiavJwicdLFgRwTPoEULORWXMkick6xL04ngFA/132', 0, NULL, NULL, 0.00, 20190117165102, NULL, NULL, 'oJUjE5JqZpGAJhkdodFeHq81NuaI', 'fcbe3ff96aad44d59eec666d4b3d357f', 1, 'Ph6KGgqyGFsTaFrWAZaAkA==', 0, 186, 20190110205347, 20190130192652);

-- ----------------------------
-- Table structure for t_user_hobby
-- ----------------------------
DROP TABLE IF EXISTS `t_user_hobby`;
CREATE TABLE `t_user_hobby`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `interest` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '兴趣（美食、旅游、美容美发、购物、按摩温泉、影视、运动、汽车、家居装饰、宠物、KTV、社交、养生、投资理财、营销、IT互联网、演出、外语学习、体验游戏、网络游戏）',
  `diet` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '饮食（1:川湘菜、2:江浙菜、3:粤菜、4:北方菜、5:日韩料理、6:西餐、7:东南亚菜、8:火锅、9:海鲜、10:素食、11:烧烤、12:甜点）',
  `taste` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '口味（1:甜、2:辣、3:酸、4:苦）',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 兴趣爱好' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user_job
-- ----------------------------
DROP TABLE IF EXISTS `t_user_job`;
CREATE TABLE `t_user_job`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户 ID',
  `industry` int(3) NULL DEFAULT NULL COMMENT '行业（计算机硬软件、互联网/电子商务/网游、IT管理、通信、电子/电器/半导体、\r\n财务/审计/税务、金融/投资、银行/保险、工程/机械、能源/原材料、汽车及零配件制造、汽车销售服务、服装/纺织、轻工产品制造、食品生产、贸易、物流/仓储、生物/制药、化工、医院/医疗/护理、广告媒体、市场/营销、影视、编辑出版、艺术/设计、建筑与装潢、房地产开发、房地产销售与中介、物业、人力资源、咨询/顾问、律师/法务、教师/培训、科研、餐饮服务、酒店旅游、美容保健、百货零售、交通运输、家政/生活服务、政府/公务员、翻译、农林牧渔、印刷包装、运动健身、休闲娱乐、其他\r\n）',
  `company_name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '公司名称',
  `department_name` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '部门名称',
  `position` int(3) NULL DEFAULT NULL COMMENT '职业（工薪族、个体户、企业主、学生、公务员、自由职业、无业）',
  `office` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '职位',
  `phone` bigint(11) NULL DEFAULT NULL COMMENT '工作电话',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '区域',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '地址',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `is_delete`(`is_delete`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 工作' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_job
-- ----------------------------
INSERT INTO `t_user_job` VALUES (11, 11, 3, NULL, NULL, 1, NULL, NULL, NULL, NULL, 0, 1, 20190112203035, 20190112203323);

-- ----------------------------
-- Table structure for t_user_maintain
-- ----------------------------
DROP TABLE IF EXISTS `t_user_maintain`;
CREATE TABLE `t_user_maintain`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '客户ID',
  `maintain` int(1) NOT NULL COMMENT '维护（0:手动 1:自动）',
  `frequency` int(1) NOT NULL COMMENT '频率',
  `count` int(3) NOT NULL COMMENT '次数',
  `budget` decimal(20, 2) NOT NULL DEFAULT 0.00 COMMENT '预算',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 维护设置' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user_remind
-- ----------------------------
DROP TABLE IF EXISTS `t_user_remind`;
CREATE TABLE `t_user_remind`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint(20) NOT NULL COMMENT '客户ID',
  `type` int(2) NOT NULL COMMENT '分类（1:纪念日 2:客户家庭生日 3:客户生日 4:用户生日）',
  `type_id` bigint(20) NOT NULL COMMENT '分类表ID',
  `advance_date` int(8) NOT NULL COMMENT '提前提醒日期',
  `advance_day` int(3) NOT NULL COMMENT '提前提醒天数',
  `remind_date` int(8) NOT NULL COMMENT '提醒日期',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `is_delete`(`is_delete`, `user_id`, `advance_date`, `type`, `type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 提醒' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_remind
-- ----------------------------
INSERT INTO `t_user_remind` VALUES (1, 11, 1, 85, 20190131, 0, 20190131, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (2, 10, 1, 75, 20190131, 3, 20190203, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (3, 11, 1, 84, 20190201, 1, 20190202, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (4, 10, 1, 75, 20190201, 2, 20190203, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (5, 10, 1, 80, 20190201, 3, 20190204, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (6, 11, 1, 84, 20190202, 0, 20190202, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (7, 10, 1, 75, 20190202, 1, 20190203, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (8, 10, 1, 80, 20190202, 2, 20190204, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (9, 10, 1, 75, 20190203, 0, 20190203, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (10, 10, 1, 80, 20190203, 1, 20190204, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (11, 10, 1, 80, 20190204, 0, 20190204, 0, 0, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
