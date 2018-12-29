/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.7.124
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 192.168.7.124:3306
 Source Schema         : rmp

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 29/12/2018 15:39:26
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
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 基础' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES (1, 1, 'aa', 'aa', 15111111116, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 4, 20181027183019, 20181027204213);
INSERT INTO `t_customer` VALUES (2, 1, 'ss', 'ss', 15111111112, 1, 20100101, '/img/head_pic/default.jpg', '321200', 'ttt', 0, NULL, 0, 31, 20181027183458, 20181228094130);
INSERT INTO `t_customer` VALUES (4, 1, 'ttt', 'ttt', 15111111111, 0, 20100101, '/xxx/pic.jpg', 'aaa', 'aaaaaaaaaaaaaa', 0, NULL, 0, 5, 20181028134438, 20181222193743);
INSERT INTO `t_customer` VALUES (5, 1, 'ttt', 'ttt', 15111111113, 0, 20100101, '/xxx/pic.jpg', '321200', 'aaaaaaaaaaaaaa', 0, NULL, 0, 0, 20181028135135, NULL);
INSERT INTO `t_customer` VALUES (6, 1, 'ss', 'ss', 15111111115, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 0, 20181119105500, NULL);
INSERT INTO `t_customer` VALUES (7, 2, 'ss', 'ss', 15111111111, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 0, 20181124174000, NULL);
INSERT INTO `t_customer` VALUES (8, 1, 'ddd', 'ddd', 13658327488, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 0, 20181125010915, NULL);
INSERT INTO `t_customer` VALUES (10, 3, 'dsaf', 'dsaf', 13112121212, 1, 20150901, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, 'gg', 0, NULL, 1, 4, 20181126162732, 20181227162314);
INSERT INTO `t_customer` VALUES (12, 3, '幽云', 'yy', 13658327455, 1, NULL, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 10, 20181126164844, 20181221115302);
INSERT INTO `t_customer` VALUES (13, 3, 'ttt', 'ttt', 15111111111, 0, 20100101, '/xxx/pic.jpg', '321200', 'aaaaaaaaaaaaaa', 0, NULL, 1, 2, 20181126194313, 20181227162258);
INSERT INTO `t_customer` VALUES (14, 3, 'ttt', 'ttt', 15111111133, 0, 20100101, '/xxx/pic.jpg', '321200', 'asdfassdf;fm;aslsdf', 0, NULL, 1, 5, 20181126194347, 20181227162256);
INSERT INTO `t_customer` VALUES (15, 3, 'ddd', 'ddd', 15111111122, 1, 19700101, 'http://47.94.5.205/xxx/pic.jpg', '321200', 'aaaaaaaaaaaaaa', 0, NULL, 1, 2, 20181127165307, 20181227162308);
INSERT INTO `t_customer` VALUES (16, 3, 'ttt', 'ttt', 15111111134, 1, 20150901, 'http://47.94.5.205/xxx/pic.jpg', '321200', 'aaaaaaaaaaaaaa', 0, NULL, 1, 9, 20181127194855, 20181221115947);
INSERT INTO `t_customer` VALUES (17, 3, '张三', 'zs', 15111111156, 1, 20100101, 'http://47.94.5.205/xxx/pic.jpg', '321200', 'aaaaaaaaaaaaaa', 0, NULL, 1, 4, 20181127195020, 20181221115132);
INSERT INTO `t_customer` VALUES (18, 3, '王5', 'w5', 13666666666, 0, NULL, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, 'qerqwer', 0, NULL, 1, 3, 20181127213216, 20181227162318);
INSERT INTO `t_customer` VALUES (19, 3, '王5233', 'w5233', 13658327484, 0, 20221001, 'http://47.94.5.205/img/head_pic/default.jpg', '重庆市渝北区胜利路129号金港国际广场沃尔玛1楼', '1-1室', 0, NULL, 1, 79, 20181127225007, 20181227162320);
INSERT INTO `t_customer` VALUES (20, 3, '??...', '??...', 13958327483, 1, 20161101, 'http://47.94.5.205/img/head_pic/default.jpg', '重庆市南岸区花园路', 'rr', 0, NULL, 1, 10, 20181207163521, 20181227162327);
INSERT INTO `t_customer` VALUES (21, 3, '新增客户', 'xzkh', 13101315226, 1, 20170901, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, '辅向路8号', 0, NULL, 1, 8, 20181208004436, 20181227162323);
INSERT INTO `t_customer` VALUES (22, 3, 'gg', 'gg', 13658328477, 0, 20151001, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 2, 20181208173623, 20181221223455);
INSERT INTO `t_customer` VALUES (23, 3, '啊哈哈', 'ahh', 13658327422, 0, 20160901, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, '大大大', 0, NULL, 1, 5, 20181209114432, 20181221115253);
INSERT INTO `t_customer` VALUES (24, 3, '不单单', 'bdd', 13658627455, 0, NULL, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 5, 20181209124241, 20181226213301);
INSERT INTO `t_customer` VALUES (25, 3, '太热', 'tr', 13852145236, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 3, 20181209132025, 20181221115941);
INSERT INTO `t_customer` VALUES (26, 5, '哈哈', 'hh', 17521455875, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 0, 20181218115602, NULL);
INSERT INTO `t_customer` VALUES (27, 3, '阿达', 'ad', 13658328782, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 1, 20181218144956, 20181221115954);
INSERT INTO `t_customer` VALUES (28, 3, 'ddd', 'ddd', 13658327483, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 3, 20181221115422, 20181227162306);
INSERT INTO `t_customer` VALUES (29, 3, 'ff', 'ff', 13658327866, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 1, 20181221162847, 20181227162316);
INSERT INTO `t_customer` VALUES (30, 5, '看看', 'kk', 13587411254, 1, 19671221, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 11, 20181221163837, 20181227141551);
INSERT INTO `t_customer` VALUES (31, 3, '新增测试', 'xzcs', 13658325477, 0, 20181222, 'http://47.94.5.205/img/head_pic/default.jpg', '重庆市渝北区义学路10号', NULL, 0, NULL, 1, 2, 20181221164220, 20181227162322);
INSERT INTO `t_customer` VALUES (32, 3, 'lrbfly', 'lrbfly', 13658328744, 1, 20181224, 'http://47.94.5.205/img/head_pic/default.jpg', '重庆市南岸区南城大道199号南岸文化艺术中心4/5楼', NULL, 0, NULL, 1, 4, 20181223121813, 20181227162325);
INSERT INTO `t_customer` VALUES (33, 3, '四十四', 'sss', 13658658788, 0, NULL, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 6, 20181223134854, 20181227162253);
INSERT INTO `t_customer` VALUES (34, 3, '多的', 'dd', 13658324565, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 1, 20181223164047, 20181227162303);
INSERT INTO `t_customer` VALUES (35, 3, 'ddd', 'ddd', 13658328766, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 1, 20181227133135, 20181227162305);
INSERT INTO `t_customer` VALUES (36, 3, 'ddd', 'ddd', 15656565658, 1, 20181228, 'http://47.94.5.205/img/head_pic/default.jpg', '重庆市渝北区两路街道金航路', 'hhh', 0, NULL, 1, 2, 20181227133832, 20181227162302);
INSERT INTO `t_customer` VALUES (37, 3, 'dddd', 'dddd', 13658959865, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 1, 20181227134026, 20181227162310);
INSERT INTO `t_customer` VALUES (38, 3, 'dsadds', 'dsadds', 13589574854, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 1, 20181227134117, 20181227162312);
INSERT INTO `t_customer` VALUES (39, 3, 'rrr', 'rrr', 15623212312, 0, NULL, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 2, 20181227134246, 20181227162031);
INSERT INTO `t_customer` VALUES (40, 5, '测试2', 'cs2', 13521455785, 1, 19721227, 'http://47.94.5.205/img/head_pic/default.jpg', '重庆市江北区图书馆(金源路64号附2号)', NULL, 0, NULL, 0, 4, 20181227141624, 20181227150124);
INSERT INTO `t_customer` VALUES (41, 5, '测试3', 'cs3', 17412545214, 0, 19951227, 'http://47.94.5.205/img/head_pic/default.jpg', '重庆市江北区图书馆(金源路64号附2号)', NULL, 0, NULL, 0, 1, 20181227142336, 20181227142443);
INSERT INTO `t_customer` VALUES (42, 5, '测试4', 'cs4', 13854168547, 1, 19961227, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 2, 20181227142615, 20181227145955);
INSERT INTO `t_customer` VALUES (43, 3, 'assdf', 'assdf', 13212121232, 0, NULL, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 3, 20181227144303, 20181227162251);
INSERT INTO `t_customer` VALUES (44, 3, 'dd', 'dd', 13254545454, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 1, 20181227150453, 20181227162300);
INSERT INTO `t_customer` VALUES (45, 5, '测试5', 'cs5', 15225478542, 1, NULL, 'http://47.94.5.205/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 5, 20181227150546, 20181227150937);
INSERT INTO `t_customer` VALUES (46, 3, 'sa', 'sa', 13658328755, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 1, 20181227162349, 20181227162354);
INSERT INTO `t_customer` VALUES (47, 3, 'asdf', 'asdf', 13654879587, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 1, 20181227162432, 20181227162437);
INSERT INTO `t_customer` VALUES (48, 3, '的风神股份', 'dfsgf', 13589874874, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 1, 1, 20181227162901, 20181227162907);
INSERT INTO `t_customer` VALUES (49, 3, '测试a1', 'csa1', 13658327483, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 88, 20181227210707, 20181229132024);
INSERT INTO `t_customer` VALUES (50, 3, 'dd', 'dd', 13658328783, NULL, NULL, '/img/head_pic/default.jpg', NULL, NULL, 0, NULL, 0, 1, 20181229140607, 20181229140639);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 明细' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 家庭' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_family
-- ----------------------------
INSERT INTO `t_customer_family` VALUES (23, 19, 2, 'llddd', 20181008, 13658328788, '重庆市渝北区两路街道金航路', 'dddd', 1, 2, 20181221124041, 20181227162320);
INSERT INTO `t_customer_family` VALUES (24, 28, 2, 'ddddsf', 20180808, 13658328566, '重庆市渝北区义学路10号', 'ddd', 1, 1, 20181221163527, 20181227162306);
INSERT INTO `t_customer_family` VALUES (25, 30, 0, '顾及', 20180808, 13541255412, '金源路(重庆市江北区)', NULL, 0, 1, 20181221164153, 20181221165421);
INSERT INTO `t_customer_family` VALUES (26, 10, 0, '000', 20180808, 13658888888, '重庆市南岸区南城大道199号', NULL, 1, 0, 20181221231132, 20181227162314);
INSERT INTO `t_customer_family` VALUES (27, 14, 0, 'sss', 20180808, 13658658777, '重庆市南岸区花园路', 'ccc', 1, 1, 20181222202723, 20181227162256);
INSERT INTO `t_customer_family` VALUES (28, 2, 2, 'xxx', 20100102, 15111111111, '???????', 'ttt', 0, 1, 20181222203812, 20181222204052);
INSERT INTO `t_customer_family` VALUES (29, 20, 2, '1212', 20180908, 13658585878, '重庆市南岸区花园路', '测试', 1, 1, 20181223121602, 20181227162327);
INSERT INTO `t_customer_family` VALUES (30, 30, 2, '巨卡', 19760808, 13983522145, '', NULL, 0, 0, 20181227141454, NULL);
INSERT INTO `t_customer_family` VALUES (31, 45, 0, '父测试5', 19920808, 13854211452, '重庆市江北区图书馆(金源路64号附2号)', NULL, 0, 0, 20181227150937, NULL);
INSERT INTO `t_customer_family` VALUES (32, 49, 0, '大时代', 20180808, 13658659888, '重庆市渝北区义学路10号', '阿斯顿发生的', 0, 0, 20181228103208, NULL);
INSERT INTO `t_customer_family` VALUES (33, 49, 1, 'sa', 20180808, 13658328577, '重庆市渝北区义学路10号', 'dd', 0, 0, 20181229130101, NULL);
INSERT INTO `t_customer_family` VALUES (34, 49, 2, 'dsf', 20180808, 13658657488, '', NULL, 0, 0, 20181229130120, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 兴趣爱好' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_hobby
-- ----------------------------
INSERT INTO `t_customer_hobby` VALUES (1, 2, '2', '1,3', '4', 0, 3, 20181027212631, 20181206225003);
INSERT INTO `t_customer_hobby` VALUES (2, 19, '15,18,19', '3,6,11', '2,3', 1, 4, 20181207234248, 20181227162320);
INSERT INTO `t_customer_hobby` VALUES (3, 21, '18', '6,10', '2', 1, 0, 20181208014256, 20181227162323);
INSERT INTO `t_customer_hobby` VALUES (4, 30, '1,3,5,6,7,9,12,14,15', '1,3', '1,3', 0, 1, 20181221164353, 20181227141525);
INSERT INTO `t_customer_hobby` VALUES (5, 24, '3,11,18,19', '3,7,11', '1,2,3,4', 1, 0, 20181226213210, 20181226213301);
INSERT INTO `t_customer_hobby` VALUES (6, 43, '7,15', '7', '2,3', 1, 0, 20181227144311, 20181227162251);
INSERT INTO `t_customer_hobby` VALUES (7, 45, '1,6,9,10,15,19', '2,6,9', '1,2', 0, 0, 20181227150825, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 工作' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_job
-- ----------------------------
INSERT INTO `t_customer_job` VALUES (1, 2, 2, 'aaaa', 'bbb', 3, NULL, 15111111111, '321200', 'aaaaaaaaaaaaaa', 0, 3, 20181028134438, 20181028135348);
INSERT INTO `t_customer_job` VALUES (2, 5, 2, 'aaaa', 'bbb', 3, NULL, 15111111111, '321200', 'aaaaaaaaaaaaaa', 0, 0, 20181028135135, NULL);
INSERT INTO `t_customer_job` VALUES (3, 13, 2, 'aaaa', 'bbb', 3, NULL, 15111111111, '321200', 'aaaaaaaaaaaaaa', 1, 0, 20181127095208, 20181227162258);
INSERT INTO `t_customer_job` VALUES (6, 14, 2, 'aaaa', 'bbb', 3, NULL, 15111111121, '321200', '拉撒盗号翻江搅海', 1, 0, 20181127102921, 20181227162256);
INSERT INTO `t_customer_job` VALUES (7, 15, 2, 'aaaa', 'bbb', 3, NULL, 15111111111, '321200', 'aaaaaaaaaaaaaa', 1, 1, 20181127165307, 20181227162308);
INSERT INTO `t_customer_job` VALUES (8, 16, 2, 'aaaa', 'bbb', 3, NULL, 15111111111, '321200', 'aaaaaaaaaaaaaa', 1, 3, 20181127194855, 20181221115947);
INSERT INTO `t_customer_job` VALUES (9, 17, 2, 'aaaa', 'bbb', 3, NULL, 15111111111, '321200', 'aaaaaaaaaaaaaa', 1, 3, 20181127195020, 20181221115132);
INSERT INTO `t_customer_job` VALUES (10, 12, NULL, NULL, NULL, 2, NULL, NULL, NULL, NULL, 1, 6, 20181127204733, 20181221115302);
INSERT INTO `t_customer_job` VALUES (11, 18, 2, 'qwerqwer', NULL, 2, NULL, NULL, NULL, 'qerqewrqwerqwer', 1, 1, 20181127223449, 20181227162318);
INSERT INTO `t_customer_job` VALUES (12, 19, 3, 'ifm科技', NULL, 3, NULL, NULL, '重庆市渝北区金航路1号', '测试测试测试测试', 1, 17, 20181127225154, 20181227162320);
INSERT INTO `t_customer_job` VALUES (13, 21, 2, '蓝天可以', NULL, 3, NULL, NULL, NULL, 'dddd', 1, 0, 20181208004732, 20181227162323);
INSERT INTO `t_customer_job` VALUES (14, 20, 6, 'rrr', NULL, 6, NULL, NULL, '重庆市南岸区南城大道199号南岸文化艺术中心4/5楼', 'rrrr', 1, 5, 20181208165748, 20181227162327);
INSERT INTO `t_customer_job` VALUES (15, 10, 3, 'fff', NULL, 2, NULL, NULL, NULL, 'ffff', 1, 0, 20181208173427, 20181227162314);
INSERT INTO `t_customer_job` VALUES (16, 22, 2, NULL, NULL, 2, NULL, NULL, NULL, NULL, 1, 0, 20181208173712, 20181221223455);
INSERT INTO `t_customer_job` VALUES (17, 23, 5, NULL, NULL, 4, NULL, NULL, NULL, NULL, 1, 0, 20181209114613, 20181221115253);
INSERT INTO `t_customer_job` VALUES (18, 24, 36, '好久', NULL, 5, NULL, NULL, NULL, NULL, 1, 0, 20181209124350, 20181226213301);
INSERT INTO `t_customer_job` VALUES (19, 31, 2, NULL, NULL, 2, NULL, NULL, NULL, NULL, 1, 0, 20181221165032, 20181227162322);
INSERT INTO `t_customer_job` VALUES (20, 30, 2, '还好还好哈', NULL, 2, NULL, NULL, NULL, NULL, 0, 1, 20181221165339, 20181227141321);
INSERT INTO `t_customer_job` VALUES (21, 4, NULL, 'aaaa', 'bbb', NULL, NULL, 15111111111, NULL, 'aaaaaaaaaaaaaa', 0, 1, 20181222193519, 20181222193743);
INSERT INTO `t_customer_job` VALUES (22, 32, 2, NULL, NULL, 2, NULL, NULL, NULL, '', 1, 2, 20181223121855, 20181227162325);
INSERT INTO `t_customer_job` VALUES (23, 36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 0, 20181227134217, 20181227162302);
INSERT INTO `t_customer_job` VALUES (24, 40, 3, NULL, NULL, 2, NULL, NULL, NULL, NULL, 0, 1, 20181227141653, 20181227141837);
INSERT INTO `t_customer_job` VALUES (25, 41, 4, NULL, NULL, 5, NULL, NULL, NULL, NULL, 0, 0, 20181227142442, NULL);
INSERT INTO `t_customer_job` VALUES (26, 42, 2, '看看公司', NULL, 3, NULL, NULL, NULL, NULL, 0, 1, 20181227142712, 20181227145955);
INSERT INTO `t_customer_job` VALUES (27, 39, 2, NULL, NULL, 2, NULL, NULL, NULL, NULL, 1, 0, 20181227143734, 20181227162031);
INSERT INTO `t_customer_job` VALUES (28, 33, 4, NULL, NULL, 1, NULL, NULL, NULL, NULL, 1, 3, 20181227143809, 20181227162253);
INSERT INTO `t_customer_job` VALUES (29, 43, 2, NULL, NULL, 2, NULL, NULL, NULL, NULL, 1, 0, 20181227144337, 20181227162251);
INSERT INTO `t_customer_job` VALUES (30, 45, 4, NULL, NULL, 4, NULL, NULL, NULL, NULL, 0, 0, 20181227150617, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 纪念日' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_memorial_day
-- ----------------------------
INSERT INTO `t_customer_memorial_day` VALUES (1, 2, 'ttttt2', 1, 20181030, 1, NULL, 0, 1, 20181028192857, 20181028193057);
INSERT INTO `t_customer_memorial_day` VALUES (2, 2, 'ttttt2', 1, 20181030, 1, NULL, 0, 0, 20181028193023, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (3, 19, 'dd', 1, 20181030, 6, NULL, 1, 1, 20181128164226, 20181220112115);
INSERT INTO `t_customer_memorial_day` VALUES (4, 19, '冠军日', 1, 20181030, 5, NULL, 1, 1, 20181128164349, 20181129002423);
INSERT INTO `t_customer_memorial_day` VALUES (5, 16, '对对对', 1, 20181030, 2, NULL, 1, 0, 20181129100140, 20181221115947);
INSERT INTO `t_customer_memorial_day` VALUES (6, 21, 'fafds', 2, 20181030, 2, NULL, 1, 0, 20181208013054, 20181227162323);
INSERT INTO `t_customer_memorial_day` VALUES (7, 19, 'gaagagf', 2, 20150901, 2, NULL, 1, 1, 20181208013412, 20181220112118);
INSERT INTO `t_customer_memorial_day` VALUES (8, 21, 'gggf', 2, 20150901, 2, NULL, 1, 0, 20181208014232, 20181227162323);
INSERT INTO `t_customer_memorial_day` VALUES (9, 23, '大大大', 3, 20160901, 4, NULL, 1, 0, 20181209114812, 20181221115253);
INSERT INTO `t_customer_memorial_day` VALUES (10, 19, 'd', 2, 20050901, 5, NULL, 1, 0, 20181220112244, 20181227162320);
INSERT INTO `t_customer_memorial_day` VALUES (11, 19, '尴尬', 3, 20050901, 3, NULL, 1, 1, 20181220112416, 20181220112612);
INSERT INTO `t_customer_memorial_day` VALUES (12, 19, '订单', 2, 20150901, 3, NULL, 1, 0, 20181220112720, 20181227162320);
INSERT INTO `t_customer_memorial_day` VALUES (13, 30, '测试1', 1, 20170901, 1, NULL, 0, 0, 20181221164311, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (14, 2, 'ttttt', 2, 20181030, 1, NULL, 0, 0, 20181222200027, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (15, 33, 'eeee', 2, 1224, 2, NULL, 1, 0, 20181223205115, 20181227162253);
INSERT INTO `t_customer_memorial_day` VALUES (16, 49, 'dd', 1, 20050402, 2, NULL, 1, 1, 20181227211009, 20181227214927);
INSERT INTO `t_customer_memorial_day` VALUES (17, 49, 'dd', 1, 20181227, 1, NULL, 1, 1, 20181227212859, 20181227214925);
INSERT INTO `t_customer_memorial_day` VALUES (18, 49, 'ddd', 2, 1010, 1, NULL, 1, 1, 20181227213026, 20181227214923);
INSERT INTO `t_customer_memorial_day` VALUES (19, 49, 'ddd', 2, 202, 1, NULL, 1, 1, 20181227214346, 20181227214921);
INSERT INTO `t_customer_memorial_day` VALUES (20, 49, 'ddd', 2, 101, 1, NULL, 1, 1, 20181227214505, 20181227214919);
INSERT INTO `t_customer_memorial_day` VALUES (21, 49, 'dfdf', 2, 101, 1, NULL, 1, 1, 20181227214731, 20181227214917);
INSERT INTO `t_customer_memorial_day` VALUES (22, 49, 'df', 2, 101, 1, NULL, 1, 1, 20181227214947, 20181228102015);
INSERT INTO `t_customer_memorial_day` VALUES (23, 49, 'dfd', 2, 202, 1, NULL, 1, 1, 20181227215006, 20181228102013);
INSERT INTO `t_customer_memorial_day` VALUES (24, 49, 'ddd', 2, 1010, 1, NULL, 1, 1, 20181227215910, 20181228102012);
INSERT INTO `t_customer_memorial_day` VALUES (25, 49, 'ff', 2, 210, 1, NULL, 1, 1, 20181227220348, 20181228102010);
INSERT INTO `t_customer_memorial_day` VALUES (26, 49, 'jyfg', 2, 404, 1, NULL, 1, 1, 20181227235759, 20181228102008);
INSERT INTO `t_customer_memorial_day` VALUES (27, 49, 'hsg', 3, 3, 1, NULL, 1, 1, 20181228000632, 20181228102006);
INSERT INTO `t_customer_memorial_day` VALUES (28, 49, 'fdj', 3, 31, 1, NULL, 1, 1, 20181228000714, 20181228102005);
INSERT INTO `t_customer_memorial_day` VALUES (29, 49, 'bzx', 4, 3, 1, NULL, 1, 1, 20181228000957, 20181228102003);
INSERT INTO `t_customer_memorial_day` VALUES (30, 2, 'ttttt', 2, 203, 1, NULL, 0, 0, 20181228092754, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (31, 2, 'ddd', 2, 1010, 1, NULL, 0, 0, 20181228092838, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (32, 2, 'ddd', 2, 1030, 1, NULL, 0, 0, 20181228092907, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (33, 2, 'ddd', 2, 1001, 1, NULL, 0, 0, 20181228092916, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (34, 2, 'ddd', 2, 1001, 1, NULL, 0, 0, 20181228093827, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (35, 2, 'ddd', 2, 101, 1, NULL, 0, 0, 20181228094028, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (36, 2, 'ddd', 2, 101, 1, NULL, 0, 0, 20181228094130, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (37, 49, '儿童节', 2, 101, 1, NULL, 1, 1, 20181228094820, 20181228102001);
INSERT INTO `t_customer_memorial_day` VALUES (38, 49, '生日歌', 2, 1006, 1, NULL, 1, 1, 20181228094950, 20181228102000);
INSERT INTO `t_customer_memorial_day` VALUES (39, 49, 'ad沙发上的', 4, 4, 3, NULL, 1, 1, 20181228095043, 20181228101958);
INSERT INTO `t_customer_memorial_day` VALUES (40, 49, '鼎折覆餗', 3, 3, 1, NULL, 1, 1, 20181228095127, 20181228101956);
INSERT INTO `t_customer_memorial_day` VALUES (41, 49, '测试节日', 1, 20181228, 1, NULL, 0, 1, 20181228102612, 20181228111521);
INSERT INTO `t_customer_memorial_day` VALUES (42, 49, '到底', 1, 20181229, 1, NULL, 0, 0, 20181228102627, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (43, 49, 'esr', 2, 203, 1, NULL, 0, 1, 20181228113219, 20181228115200);
INSERT INTO `t_customer_memorial_day` VALUES (44, 49, 'ddd', 1, 20181229, 3, NULL, 0, 3, 20181228121822, 20181229110025);
INSERT INTO `t_customer_memorial_day` VALUES (45, 49, 'dddss', 2, 131, 1, NULL, 0, 9, 20181228121848, 20181229132024);
INSERT INTO `t_customer_memorial_day` VALUES (46, 49, 'dsss', 2, 1004, 1, NULL, 0, 0, 20181228145659, NULL);
INSERT INTO `t_customer_memorial_day` VALUES (47, 49, 'adfa', 2, 630, 3, NULL, 0, 4, 20181228151639, 20181229105909);
INSERT INTO `t_customer_memorial_day` VALUES (48, 49, '是否', 2, 228, 3, NULL, 0, 13, 20181228154135, 20181229111533);
INSERT INTO `t_customer_memorial_day` VALUES (49, 49, '打的费', 3, 2, 1, NULL, 0, 2, 20181228160033, 20181229105800);
INSERT INTO `t_customer_memorial_day` VALUES (50, 49, 'asdfadd', 4, 4, 3, NULL, 0, 2, 20181228160919, 20181229131910);
INSERT INTO `t_customer_memorial_day` VALUES (51, 49, 'asdd愿意', 4, 2, 2, NULL, 0, 1, 20181229111659, 20181229131938);
INSERT INTO `t_customer_memorial_day` VALUES (52, 49, 'af', 1, 20181229, 1, NULL, 1, 1, 20181229125748, 20181229131926);

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 可能问题' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_problem
-- ----------------------------
INSERT INTO `t_customer_problem` VALUES (1, 2, '2', '1,3', 'xxxxxxxxxTTT', 0, 4, 20181027220102, 20181206225425);
INSERT INTO `t_customer_problem` VALUES (2, 19, '2,6', '2', NULL, 1, 3, 20181208002821, 20181227162320);
INSERT INTO `t_customer_problem` VALUES (3, 21, '2,6,10', '2,3', NULL, 1, 0, 20181208014313, 20181227162323);
INSERT INTO `t_customer_problem` VALUES (4, 30, '1,6', '1', NULL, 0, 1, 20181221164430, 20181227141551);
INSERT INTO `t_customer_problem` VALUES (5, 24, '6,7,8,10,12', '3,7', NULL, 1, 0, 20181226213240, 20181226213301);
INSERT INTO `t_customer_problem` VALUES (6, 45, '1,2,9,13', '1,2', NULL, 0, 0, 20181227150831, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '客户 关系' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_customer_relation
-- ----------------------------
INSERT INTO `t_customer_relation` VALUES (1, 2, 0, 0, 0, 0, 1, 20181027210812, 20181027210836);
INSERT INTO `t_customer_relation` VALUES (2, 19, 1, 1, 1, 0, 11, 20181128095934, 20181220104005);
INSERT INTO `t_customer_relation` VALUES (3, 12, 2, 2, 0, 0, 1, 20181128225603, 20181128225634);
INSERT INTO `t_customer_relation` VALUES (4, 16, 5, 1, 1, 0, 0, 20181129095922, NULL);
INSERT INTO `t_customer_relation` VALUES (5, 14, 6, 1, 1, 0, 0, 20181207223859, NULL);
INSERT INTO `t_customer_relation` VALUES (6, 21, 5, 1, 2, 0, 0, 20181208004933, NULL);
INSERT INTO `t_customer_relation` VALUES (7, 20, 6, 1, 1, 0, 0, 20181209104556, NULL);
INSERT INTO `t_customer_relation` VALUES (8, 23, 0, 1, 1, 0, 0, 20181209114635, NULL);
INSERT INTO `t_customer_relation` VALUES (9, 10, 2, 2, 1, 0, 0, 20181221151628, NULL);
INSERT INTO `t_customer_relation` VALUES (10, 30, 1, 1, 1, 0, 0, 20181221164112, NULL);
INSERT INTO `t_customer_relation` VALUES (11, 24, 5, 2, 1, 0, 0, 20181226213154, NULL);
INSERT INTO `t_customer_relation` VALUES (12, 40, 2, 3, 1, 0, 1, 20181227141938, 20181227150124);
INSERT INTO `t_customer_relation` VALUES (13, 45, 3, 2, 1, 0, 0, 20181227150632, NULL);
INSERT INTO `t_customer_relation` VALUES (14, 50, 8, 4, 2, 0, 0, 20181229140639, NULL);

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
-- Records of t_phone_msg
-- ----------------------------
INSERT INTO `t_phone_msg` VALUES (1, 15123815032, '666666', 1, '666666', 1, 0, 2, 20181021174100, 20181021174853);
INSERT INTO `t_phone_msg` VALUES (2, 15123815032, '777777', 2, '666666', 1, 0, 3, 20181021181100, 1540116915);
INSERT INTO `t_phone_msg` VALUES (3, 15123815000, '623694', 1, '623694', 0, 0, 0, 20181021182045, NULL);
INSERT INTO `t_phone_msg` VALUES (4, 15123815000, '289984', 1, '289984', 0, 0, 0, 20181021182152, NULL);
INSERT INTO `t_phone_msg` VALUES (5, 15123815032, '382336', 2, '382336', 0, 0, 0, 20181021182251, NULL);
INSERT INTO `t_phone_msg` VALUES (6, 15123815000, '538612', 1, '538612', 0, 0, 0, 20181105130834, NULL);
INSERT INTO `t_phone_msg` VALUES (7, 15123815000, '996774', 1, '996774', 0, 0, 0, 20181105145921, NULL);
INSERT INTO `t_phone_msg` VALUES (8, 15123815000, '205931', 1, '205931', 0, 0, 0, 20181105150036, NULL);
INSERT INTO `t_phone_msg` VALUES (9, 15123815000, '068424', 1, '068424', 0, 0, 0, 20181105151052, NULL);
INSERT INTO `t_phone_msg` VALUES (10, 15123815000, '284295', 1, '284295', 0, 0, 0, 20181105151519, NULL);
INSERT INTO `t_phone_msg` VALUES (11, 15123815000, '425152', 1, '425152', 0, 0, 0, 20181105153123, NULL);
INSERT INTO `t_phone_msg` VALUES (12, 15123815000, '149024', 1, '149024', 0, 0, 0, 20181105153243, NULL);
INSERT INTO `t_phone_msg` VALUES (13, 15123815000, '758388', 1, '758388', 0, 0, 0, 20181105153350, NULL);
INSERT INTO `t_phone_msg` VALUES (14, 15123815000, '128831', 1, '128831', 0, 0, 0, 20181105153456, NULL);
INSERT INTO `t_phone_msg` VALUES (15, 15123815000, '454301', 1, '454301', 0, 0, 0, 20181105153603, NULL);
INSERT INTO `t_phone_msg` VALUES (16, 15123815000, '016740', 1, '016740', 0, 0, 0, 20181105154127, NULL);
INSERT INTO `t_phone_msg` VALUES (17, 15123815000, '622251', 1, '622251', 0, 0, 0, 20181105154401, NULL);
INSERT INTO `t_phone_msg` VALUES (18, 15123815000, '322474', 1, '322474', 0, 0, 0, 20181105154511, NULL);
INSERT INTO `t_phone_msg` VALUES (19, 15123815000, '860460', 1, '860460', 0, 0, 0, 20181105160722, NULL);
INSERT INTO `t_phone_msg` VALUES (20, 15123815000, '353582', 1, '353582', 0, 0, 0, 20181105161400, NULL);
INSERT INTO `t_phone_msg` VALUES (21, 15123815000, '278921', 1, '278921', 0, 0, 0, 20181105163223, NULL);
INSERT INTO `t_phone_msg` VALUES (22, 15123815000, '552302', 1, '552302', 0, 0, 0, 20181105164158, NULL);
INSERT INTO `t_phone_msg` VALUES (23, 15123815000, '452371', 1, '452371', 0, 0, 0, 20181106155115, NULL);
INSERT INTO `t_phone_msg` VALUES (24, 15123815000, '953658', 1, '953658', 0, 0, 0, 20181126143727, NULL);
INSERT INTO `t_phone_msg` VALUES (25, 13658327483, '313817', 1, '313817', 0, 0, 0, 20181126143727, NULL);
INSERT INTO `t_phone_msg` VALUES (26, 13658327483, '657866', 1, '657866', 0, 0, 0, 20181126143727, NULL);
INSERT INTO `t_phone_msg` VALUES (28, 15123815033, '767505', 1, '767505', 0, 0, 0, 20181126143727, NULL);
INSERT INTO `t_phone_msg` VALUES (35, 17353133429, '997302', 1, '997302', 1, 0, 1, 20181126152102, 20181126152128);
INSERT INTO `t_phone_msg` VALUES (36, 15320500581, '511883', 1, '511883', 0, 0, 0, 20181130212240, NULL);
INSERT INTO `t_phone_msg` VALUES (37, 13558327483, '339933', 1, '339933', 0, 0, 0, 20181130212522, NULL);
INSERT INTO `t_phone_msg` VALUES (38, 13658327483, '368137', 1, '368137', 0, 0, 0, 20181130212644, NULL);
INSERT INTO `t_phone_msg` VALUES (39, 17356565656, '633607', 1, '633607', 0, 0, 0, 20181130214841, NULL);
INSERT INTO `t_phone_msg` VALUES (40, 13521212121, '363135', 1, '363135', 0, 0, 0, 20181208203829, NULL);

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
INSERT INTO `t_sys_code` VALUES (162, '4', '5天', 158, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (163, '5', '1周', 158, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (164, '6', '2周', 158, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `t_sys_code` VALUES (165, '7', '1月', 158, 7, NULL, 0, 0, NULL, NULL);
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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '15123815032', '7c4a8d09ca3762af61e59520943dc26494f8941b', 15123815032, 'ttt', 'ttt', '/xxx/pic.jpg', 0, 20100101, NULL, 0.00, 20181021175641, 'fffff', 'aaaaaaaaaaaaaa', 'wx_id', '2661f2cac9754c98873aa9ce431b8012', 1, NULL, 0, 15, 20181021155201, 20181213230911);
INSERT INTO `t_user` VALUES (3, '13658327483', '7c4a8d09ca3762af61e59520943dc26494f8941b', 13658327483, NULL, '蓝天', 'https://wx.qlogo.cn/mmopen/vi_32/icGYw95jPibIboG9OAoniboicLVM6W1SCHiaWsaNTe1pMCxicGAVKu9OyMiavJwicdLFgRwTPoEULORWXMkick6xL04ngFA/132', NULL, NULL, NULL, 0.00, 20181221154537, '333', '444', 'oJUjE5JqZpGAJhkdodFeHq81NuaI', 'c19c430d09f94defa5f7848d4bc25d57', 1, 'iK9DatACA/m0kG7GIZ3ohg==', 0, 3263, 20181126150058, 20181229142127);
INSERT INTO `t_user` VALUES (4, 'oJUjE5D5EPE9HyLrsknXwwxjq5Po', '0', NULL, 'aad', '遮不住的眼', 'https://wx.qlogo.cn/mmopen/vi_32/icGYw95jPibIboG9OAoniboicLVM6W1SCHiaWsaNTe1pMCxicGAVKu9OyMiavJwicdLFgRwTPoEULORWXMkick6xL04ngFA/132', 0, 20180901, NULL, 0.00, NULL, '333', '444', 'oJUjE5D5EPE9HyLrsknXwwxjq5Po', 'b43e079cc01c463d83d7ca47aca3e4f4', 0, 'TX24K7Xf/vSpQZwoZVygUQ==', 0, 14, 20181201204219, 20181229142200);
INSERT INTO `t_user` VALUES (5, '13983100194', '0', 13983100194, NULL, '物随缘我', 'https://wx.qlogo.cn/mmopen/vi_32/Uj8UYOic8oKZRrvBYFMU418vt94CP310Q5r1k2VB4JOOlUjYPOeDjdTyVkO9nwesaJDNdAib6C2sQ8siarxibWNS4A/132', NULL, NULL, NULL, 0.00, 20181218115031, NULL, NULL, 'oJUjE5OAaBvVxkvgF8JSanMk20Y8', '921cda5e87644addaaa8b8eaf8387383', 1, 'C/X+lOWwvikA4Z/BsJrrPA==', 0, 27, 20181218114939, 20181227151821);

-- ----------------------------
-- Table structure for t_user_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_user_customer`;
CREATE TABLE `t_user_customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `is_delete` int(1) NOT NULL DEFAULT 0 COMMENT '是否删除（0:未删除 1:已删除）',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '版本号',
  `create_time` bigint(14) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(14) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id`(`user_id`, `customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 客户 关联' ROW_FORMAT = Compact;

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 兴趣爱好' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_hobby
-- ----------------------------
INSERT INTO `t_user_hobby` VALUES (1, 1, '1', '1,3', '4', 0, 0, 20181206225706, NULL);
INSERT INTO `t_user_hobby` VALUES (2, 3, '6,14,18', '4,6,7', '2,3', 0, 2, 20181208231443, 20181223213255);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 工作' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_job
-- ----------------------------
INSERT INTO `t_user_job` VALUES (1, 4, 2, 'asdf', NULL, 2, NULL, NULL, NULL, 'adfad', 0, 4, 20181208223635, 20181229142200);
INSERT INTO `t_user_job` VALUES (3, 1, 1, '33', NULL, NULL, NULL, NULL, NULL, NULL, 0, 2, 20181211204554, 20181213230911);

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户 提醒' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_remind
-- ----------------------------
INSERT INTO `t_user_remind` VALUES (1, 1, 3, 4, 20181229, 3, 20190101, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (2, 1, 3, 2, 20181229, 3, 20190101, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (3, 1, 3, 5, 20181229, 3, 20190101, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (4, 1, 4, 1, 20181229, 3, 20190101, 0, 0, NULL, NULL);
INSERT INTO `t_user_remind` VALUES (5, 1, 2, 28, 20181229, 4, 20190102, 0, 0, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
