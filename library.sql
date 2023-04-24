/*
 Navicat MySQL Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : library

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 06/04/2023 13:18:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `adminType` int(11) NULL DEFAULT NULL COMMENT '管理员类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '12345', 1);
INSERT INTO `admin` VALUES (2, 'yx5411', '12345', 0);
INSERT INTO `admin` VALUES (4, 'xy1221', '12345', 0);

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
--  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书名称',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书名称',
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
--  `publish` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `publish` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `isbn` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍编号',
--  `introduction` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `introduction` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '语言',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `publish_date` date NULL DEFAULT NULL COMMENT '出版时间',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '书籍类型',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态：0未借出，1已借出',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图书信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_info
-- ----------------------------
/*
INSERT INTO `book_info` VALUES (1, '西游记', '施耐庵', '机械工业出版社', '100011', '师徒四人去西天取真经', '中文', 42, '2020-03-20', 1, 0);
INSERT INTO `book_info` VALUES (2, '三国演义', '罗贯中', '清华大学出版社', '100012', '东汉末年分三国。。。', '中文', 48, '2018-03-30', 1, 1);
INSERT INTO `book_info` VALUES (3, '红楼梦', '曹雪芹', '机械工业出版社', '100013', '宝玉与众多男女之间故事', '中文', 39, '2019-04-04', 1, 1);
INSERT INTO `book_info` VALUES (4, '资本论', '马克思', '原子能出版社', '100014', '马克思的剩余价值理论', '英文', 42, '2019-04-05', 5, 0);
INSERT INTO `book_info` VALUES (5, '经济学原理', '马歇尔', '机械工业出版社', '100015', '西方经济学界公认为划时代的著作', '英文', 45, '2020-04-06', 5, 0);
INSERT INTO `book_info` VALUES (6, '大变局下的中国法治', '李卫东', '北京大学出版社', '100016', '十大经典法学著作', '中文', 42, '2015-04-05', 4, 1);
*/

INSERT INTO `book_info` VALUES (1, 'journey to the west', 'shy naih-an', 'China Machine Press', '100011', 'teacher and pupil four people go to the buddhist paradise to take true through', 'Chinese', 42, '2020-03-20', 1, 0);
INSERT INTO `book_info` VALUES (2, 'Romance of The Three Kingdoms', 'Luo Guanzhong', 'Tsinghua University Press', '100012', 'The end of the Eastern Han Dynasty divided into Three Kingdoms...', 'Chinese', 48, '2018-03-30', 1, 1);
INSERT INTO `book_info` VALUES (3, 'Dream of Red Mansions', 'Cao Xueqin', 'China Machine Press', '100013', 'Stories between Baoyu and Many Men and Women', 'Chinese', 39, '2019-04-04', 1, 1);
INSERT INTO `book_info` VALUES (4, 'das kapital', 'Marx', 'atomic press', '100014', "Marx's theory of surplus value", 'English', 42, '2019-04-05', 5, 0);
INSERT INTO `book_info` VALUES (5, 'Principles of Economics ',' Marshall ', 'China Machine Press ', '100015',' A Book Recognized as epoch-making in Western economics', 'English', 45, '2020-04-06', 5, 0);
INSERT INTO `book_info` VALUES (6, 'under the changing of Chinese rule of law', 'wei-dong li', 'Beijing university press', '100016', 'top ten classic works of law', 'Chinese', 42, '2015-04-05', 4, 1);
-- ----------------------------
-- Table structure for lend_list
-- ----------------------------
DROP TABLE IF EXISTS `lend_list`;
CREATE TABLE `lend_list`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `bookId` int(11) NULL DEFAULT NULL COMMENT '图书id',
  `readerId` int(11) NULL DEFAULT NULL COMMENT '读者id',
  `lendDate` datetime(0) NULL DEFAULT NULL COMMENT '借书时间',
  `backDate` datetime(0) NULL DEFAULT NULL COMMENT '还书时间',
  `backType` int(11) NULL DEFAULT NULL,
  `exceptRemarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '借阅记录（谁在何时借走了什么书，并且有没有归还，归还时间多少）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lend_list
-- ----------------------------
INSERT INTO `lend_list` VALUES (15, 1, 1, '2023-04-04 10:07:31', '2023-04-04 21:09:27', 0, NULL);
INSERT INTO `lend_list` VALUES (36, 2, 2, '2023-04-04 21:48:17', '2023-04-04 21:50:00', 2, NULL);
INSERT INTO `lend_list` VALUES (37, 3, 3, '2023-04-04 21:50:22', '2023-04-04 21:50:32', 3, NULL);
INSERT INTO `lend_list` VALUES (38, 5, 1, '2023-04-05 21:35:35', '2023-04-05 21:35:47', 1, NULL);
INSERT INTO `lend_list` VALUES (39, 6, 3, '2023-04-05 21:42:35', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `topic` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `author` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人',
  `createDate` datetime(0) NULL DEFAULT NULL COMMENT '公告发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, 'Announcement internal Test 1','This is the first content test', 'admin', '2023-04-01 21:35:53');
INSERT INTO `notice` VALUES (2, 'Announcement internal Test 2','This is the second content test', 'admin', '2023-04-02 22:38:03');
INSERT INTO `notice` VALUES (3, 'Announcement internal Test 3','This is the third content test', 'yx5411', '2023-04-03 06:47:54');
INSERT INTO `notice` VALUES (4, 'Announcement internal Test 4','This is the fourth content test', 'yx5411', '2023-04-04 06:48:01');
INSERT INTO `notice` VALUES (5, 'Announcement internal Test 5','This is the fifth content test', 'xy1221', '2023-04-04 06:49:03');
INSERT INTO `notice` VALUES (6, 'Announcement internal Test 6','This is the sixth content test', 'yx5411', '2023-04-05 07:48:04');

-- ----------------------------
-- Table structure for reader_info
-- ----------------------------
DROP TABLE IF EXISTS `reader_info`;
CREATE TABLE `reader_info`(
                              `id`           int(11)                                                  NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `username`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '用户名',
                              `password`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '密码',
                              `realName`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '真实姓名',
                              -- `sex`          varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '性别',
                              `sex`          varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '性别',
                              `birthday`     date                                                     NULL DEFAULT NULL COMMENT '出生日期',
                              `address`      varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '籍贯',
                              `tel`          varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '电话',
                              `email`        varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '邮箱',
                              `registerDate` datetime(0)                                              NULL DEFAULT NULL COMMENT '注册日期',
                              `readerNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '读者编号',
                              `avatar`       varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '读者信息（包括登录账号密码等）'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reader_info
-- ----------------------------
INSERT INTO `reader_info`
VALUES (1, 'zhangsan', '12345', 'zhangsan', 'male', '2001-04-01', 'Nanchang, Jiangxi', '13767134834', 'yu123@163.com','2023-04-02 13:18:59', '8120116041', '');
INSERT INTO `reader_info`
VALUES (2, 'mary', '12345', 'mary', 'female', '2004-04-01', 'Wuhan, Hubei', '15270839599', 'yx123@163.com','2023-03-06 07:57:56', '8120116044', '');
INSERT INTO `reader_info`
VALUES (3, 'cindy', '12345', 'cindy', 'female', '2010-04-04', 'Beijing Haidian District', '13834141133', 'zs1314@163.com','2023-04-04 13:36:42', '8120116042', '');

/*
VALUES (1, 'zhangsan', '12345', '彭于晏', '男', '2001-04-01', '江西南昌', '13767134834', 'yu123@163.com','2023-04-02 13:18:59', '8120116041', '');
VALUES (2, 'mary', '12345', '陈恋', '女', '2004-04-01', '湖北武汉', '15270839599', 'yx123@163.com','2023-03-06 07:57:56', '8120116044', '');
VALUES (3, 'cindy', '12345', '辛帝', '女', '2010-04-04', '北京海淀', '13834141133', 'zs1314@163.com','2023-04-04 13:36:42', '8120116042', '');
*/
-- ----------------------------
-- Table structure for type_info
-- ----------------------------
DROP TABLE IF EXISTS `type_info`;
CREATE TABLE `type_info`
(
  `id`      int(11)                                                NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图书分类名称',
  -- `remarks` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '备注',
  `remarks` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图书类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type_info
-- ----------------------------
/*
INSERT INTO `type_info` VALUES (1, '文学类', '陶冶情操');
INSERT INTO `type_info` VALUES (2, '历史类', '了解历史文化');
INSERT INTO `type_info` VALUES (3, '工学类', '造火箭');
INSERT INTO `type_info` VALUES (4, '法学类', '学习法律，打官司');
INSERT INTO `type_info` VALUES (5, '经济学类', '搞经济');
INSERT INTO `type_info` VALUES (6, '统计学类', '学习统计，天下无敌');
*/

INSERT INTO `type_info` VALUES (1, 'Literature','cultivate sentiment');
INSERT INTO `type_info` VALUES (2, 'History','understanding history and culture');
INSERT INTO `type_info` VALUES (3, 'Engineering','rocket building');
INSERT INTO `type_info` VALUES (4, 'Law','study law, litigate');
INSERT INTO `type_info` VALUES (5, 'Economics','doing economics');
INSERT INTO `type_info` VALUES (6, 'Statistics','Study statistics, the world is invincible');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for worker_info
-- ----------------------------
DROP TABLE IF EXISTS `worker_info`;
CREATE TABLE `worker_info`(
                              `id`           int                                                NOT NULL AUTO_INCREMENT COMMENT 'id',
                              `username`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '用户名',
                              `password`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '密码',
                              `realName`     varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '真实姓名',
                              -- `sex`          varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '性别',
                              `sex`          varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci    NULL DEFAULT NULL COMMENT '性别',
                              `birthday`     date                                                     NULL DEFAULT NULL COMMENT '出生日期',
                              `address`      varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '籍贯',
                              `tel`          varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '电话',
                              `email`        varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '邮箱',
                              `registerDate` datetime(0)                                              NULL DEFAULT NULL COMMENT '注册日期',
                              `workerNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT '工号',
                              `avatar`       varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
                              `status`       int                                                NOT NULL COMMENT '是否在职',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '图书馆工作人员信息（包括工号密码等）'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of worker_info
-- 发现一个bug，电话号码整重了，随便换了一下
-- ----------------------------
INSERT INTO `worker_info`
VALUES (1, 'worker001', '12345', 'zhangsan', 'male', '2001-04-02', "Xi'an, Shaanxi", '13701234567', 'w001@163.com', '2023-04-01 12:34:26', '20009200001', '', '1');
INSERT INTO `worker_info`
VALUES (2, 'worker002', '12345', 'lisi', 'female', '2004-04-01', 'Wuhan, Hubei', '15212345678', 'w002@163.com', '2023-03-06 07:57:56', '20009200002', '', '1');
INSERT INTO `worker_info`
VALUES (3, 'worker003', '12345', 'wangwu', 'female', '2010-04-04', 'Beijing Haidian District', '13823456789', 'w003@163.com', '2023-04-04 13:36:42', '20009200003', '', '0');
/*
VALUES (1, 'worker001', '12345', '张三', '男', '2001-04-02', '陕西西安', '13767134834', 'w001@163.com', '2023-04-01 12:34:26', '20009200001', '', '1');
VALUES (2, 'worker002', '12345', '李四', '女', '2004-04-01', '湖北武汉', '15270839599', 'w002@163.com', '2023-03-06 07:57:56', '20009200002', '', '1');
VALUES (3, 'worker003', '12345', '王五', '女', '2010-04-04', '北京海淀', '13834141133', 'w003@163.com', '2023-04-04 13:36:42', '20009200003', '', '0');
*/