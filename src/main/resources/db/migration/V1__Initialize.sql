-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books`  (
  `id` int(255) NOT NULL AUTO_INCREMENT COMMENT '书籍编号',
  `book_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书籍名称',
  `book_quantity` int(255) NULL DEFAULT NULL COMMENT '书籍数量',
  `press` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出版社',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`id`) USING BTREE
);


-- ----------------------------
-- Records of books
-- ----------------------------
INSERT INTO `books` VALUES (1, 'JAVAEE入门', -1269, '机械工业出版社', 50.00);
INSERT INTO `books` VALUES (2, '大学英语', 1000, '机械工业出版社', 30.50);
INSERT INTO `books` VALUES (3, '高等数学', 1000, '机械工业出版社', 30.50);
INSERT INTO `books` VALUES (4, 'C++程序设计', 1000, '电子工业出版社', 50.20);
INSERT INTO `books` VALUES (5, '大学物理', 1000, '复旦大学出版社', 30.50);
INSERT INTO `books` VALUES (6, 'Android开发', 500, '电子工业出版社', 80.20);
INSERT INTO `books` VALUES (7, 'C语言从入门到精通', 1000, '人民邮电出版社', 20.50);
INSERT INTO `books` VALUES (8, '算法分析与设计', 1000, '清华大学出版社', 24.80);
INSERT INTO `books` VALUES (9, '马克思原理概论', 300, '清华大学出版社', 30.60);
INSERT INTO `books` VALUES (10, '深入理解java虚拟机', 1000, '机械工业出版社', 80.90);
INSERT INTO `books` VALUES (11, '软件工程', 200, '清华大学出版社', 40.50);
INSERT INTO `books` VALUES (12, 'python', 200, '人民出版社', 23.00);
INSERT INTO `books` VALUES (13, '高等数学', 200, '北京大学出版社', 20.50);
INSERT INTO `books` VALUES (14, '数据库概论', 200, '机械工业出版社', 20.00);
INSERT INTO `books` VALUES (21, 'java从入门到精通', 500, '北京大学出版社', 40.20);



-- ----------------------------
-- Table structure for approval
-- ----------------------------
DROP TABLE IF EXISTS `approval`;
CREATE TABLE `approval`  (
  `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号\r\n',
  `book_id` int(255) NULL DEFAULT NULL COMMENT '书目编号',
  `teacher_id` int(255) UNSIGNED NULL DEFAULT NULL COMMENT '教师id',
  `quantity` int(255) NULL DEFAULT NULL COMMENT '订购数量',
  `toclass` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订购班级',
  `pass`  int(3) UNSIGNED NULL DEFAULT 2 COMMENT '是否通过',
  PRIMARY KEY (`id`) USING BTREE
);

-- ----------------------------
-- Records of approval
-- ----------------------------
INSERT INTO `approval` VALUES (7, 1, 10003, 50, '17070143', 3);
INSERT INTO `approval` VALUES (8, 11, 10003, 50, '17070143', 3);
INSERT INTO `approval` VALUES (9, 1, 10003, 50, '17070144', 3);
INSERT INTO `approval` VALUES (10, 11, 10003, 50, '17070144', 3);
INSERT INTO `approval` VALUES (11, 12, 10003, 50, '17070144', 1);
INSERT INTO `approval` VALUES (12, 2, 10007, 50, '17070144', 1);
INSERT INTO `approval` VALUES (13, 10, 10003, 20, '17070143', 1);
INSERT INTO `approval` VALUES (14, 11, 10003, 20, '17070047', 3);
INSERT INTO `approval` VALUES (15, 13, 10003, 20, '2', 3);
INSERT INTO `approval` VALUES (16, 11, 10003, 50, '17070143', 3);
INSERT INTO `approval` VALUES (17, 11, 10003, 50, '17070143', 1);
INSERT INTO `approval` VALUES (19, 1, 10011, 1221, '17140341', 1);
INSERT INTO `approval` VALUES (20, 1, 10013, 4, '1', 2);
INSERT INTO `approval` VALUES (21, 3, 10011, 45, '12', 2);
INSERT INTO `approval` VALUES (22, 2, 10011, 12, '12', 2);


-- ----------------------------
-- Table structure for classleader
-- ----------------------------
DROP TABLE IF EXISTS `classleader`;
CREATE TABLE `classleader`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电子邮箱',
  `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `classid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级号',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '院系',
  PRIMARY KEY (`id`) USING BTREE
);

-- ----------------------------
-- Records of classleader
-- ----------------------------
INSERT INTO `classleader` VALUES ('张三', '123', '123@qq.com', 5, '17070143', '大数据学院s');


-- ----------------------------
-- Table structure for grant
-- ----------------------------
DROP TABLE IF EXISTS `grant`;
CREATE TABLE `grant`  (
  `order_id` int(255) UNSIGNED NOT NULL COMMENT '订单号（外键）',
  `grant_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发放时间',
  `grant_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发放地点',
  PRIMARY KEY (`order_id`) USING BTREE
);

-- ----------------------------
-- Records of grant
-- ----------------------------
INSERT INTO `grant` VALUES (7, '2019-12-1 9:00', '11201H');
INSERT INTO `grant` VALUES (8, '2019-11-17 ', '11505');
INSERT INTO `grant` VALUES (9, '2019-1-1 8:00', '文灜5');
INSERT INTO `grant` VALUES (10, '2019-1-1 8:00', '主楼');
INSERT INTO `grant` VALUES (14, '2019-1-1', '主楼');
INSERT INTO `grant` VALUES (15, '2019-12-12 9:00', '主楼');
INSERT INTO `grant` VALUES (16, '2019-12-12 19:00', '主楼');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电子邮件',
  `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  PRIMARY KEY (`id`) USING BTREE
);

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('admin', '123', '1707004732@st.nuc.edu.cn', 5);
INSERT INTO `manager` VALUES ('admin2', '123', '1707004732@st.nuc.edu.cn', 6);
INSERT INTO `manager` VALUES ('admin3', '123', '123@xxx.com', 7);
INSERT INTO `manager` VALUES ('admin4', '123', '10129465@qq.com', 11);
INSERT INTO `manager` VALUES ('小杜', '123', '15364968962@163.com', 24);
INSERT INTO `manager` VALUES ('张劭航', '123', '568@qq.com', 25);
INSERT INTO `manager` VALUES ('1707004243', '123', '123@qq.com', 26);
INSERT INTO `manager` VALUES ('admin6', '123', '1707004732@st.nuc.edu.cn', 27);
INSERT INTO `manager` VALUES ('123admin', 'admin', '', 37);
INSERT INTO `manager` VALUES ('李相', 'dxdx5211314', '', 38);
INSERT INTO `manager` VALUES ('root', '123', '67883@qq.com', 39);
INSERT INTO `manager` VALUES ('1707014311', '1707014311', '2985694296@qq.com', 40);
INSERT INTO `manager` VALUES ('1714034111', '1714034111', '2985694296@qq.com', 41);
INSERT INTO `manager` VALUES ('111111', '123456', '123456@qq.com', 47);
INSERT INTO `manager` VALUES ('111', '123456', '123456@qq.com', 48);
INSERT INTO `manager` VALUES ('123', '123456', '123456@qq.com', 49);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` int(255) UNSIGNED NOT NULL AUTO_INCREMENT,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE

);

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('张健华', '123', '666@QQ.com', 10002, '大数据学院');
INSERT INTO `teacher` VALUES ('李玲', '123', '666@QQ.com', 10003, '大数据学院');
INSERT INTO `teacher` VALUES ('周海英', '123', '666@QQ.com', 10004, '大数据学院');
INSERT INTO `teacher` VALUES ('况立群', '123', '666@QQ.com', 10005, '大数据学院');
INSERT INTO `teacher` VALUES ('梁志健', '123', '123@qq.com', 10007, '大数据学院');
INSERT INTO `teacher` VALUES ('小杜', '123', '15364968962@163.com', 10009, '大数据学院');
INSERT INTO `teacher` VALUES ('1123546', '111', '11@qq.com', 10010, NULL);
INSERT INTO `teacher` VALUES ('1707014312', '1707014312', '1014416989@qq.com', 10011, 'saf');
INSERT INTO `teacher` VALUES ('123456', '147852', '123456@qq.com', 10012, NULL);
INSERT INTO `teacher` VALUES ('111111', '123456', '123456@qq.com', 10013, NULL);
