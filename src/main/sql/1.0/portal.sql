/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : portal

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-09-04 18:04:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL COMMENT '邮箱',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `nick_name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `gender` tinyint(4) NOT NULL COMMENT '性别 0：保密；1：男；2：女',
  `update_time` timestamp NULL DEFAULT '2012-05-08 11:53:35',
  `create_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  `status` int(11) NOT NULL COMMENT '状态：0 停用；0x1 启用；0x2：禁止登录（密码错误次数过多，一定时间内禁止登录）',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `last_login_ip` varchar(18) DEFAULT NULL COMMENT '最后登录Ip',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  `office_phone` varchar(18) DEFAULT NULL COMMENT '办公电话',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_t_base_user_email` (`email`),
  UNIQUE KEY `idx_t_base_nick_name` (`nick_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台管理用户';

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES ('0', 'admin@admin.com', 'fd8922228b92a18e611567b3195126ac', '系统管理员', null, '1', '2012-09-04 16:51:12', '2012-08-03 17:56:47', '1', null, '127.0.0.1', '2012-09-04 16:51:12', null, '');
INSERT INTO `t_admin_user` VALUES ('2', 'test@test.com', '90c523f5826301fb1e4ddc6a573fbaa4', '测试', '测试', '2', '2012-08-07 15:37:13', '2012-08-07 15:35:53', '0', '测试', null, null, '13764546654', '13764546654');

-- ----------------------------
-- Table structure for `t_banner_ad`
-- ----------------------------
DROP TABLE IF EXISTS `t_banner_ad`;
CREATE TABLE `t_banner_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `image` varchar(400) NOT NULL COMMENT '图片',
  `thumb` varchar(400) NOT NULL COMMENT '缩略图',
  `href` varchar(400) NOT NULL COMMENT '跳转链接',
  `display_index` int(11) DEFAULT '0',
  `status` int(11) NOT NULL COMMENT '状态：0 停用；1 启用；',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  `update_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_banner_ad
-- ----------------------------
INSERT INTO `t_banner_ad` VALUES ('1', 'aaaa', 'http://www.carit.com.cn/UploadFile/Link/2011091415024098.jpg', 'http://www.carit.com.cn/UploadFile/Link/2011091415024098.jpg', 'http://www.carit.com.cn/UploadFile/Link/2011091415024098.jpg', '1', '1', null, '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_banner_ad` VALUES ('2', 'bbbb', 'http://www.carit.com.cn/UploadFile/Link/2011091415024898.jpg', 'http://www.carit.com.cn/UploadFile/Link/2011091415024898.jpg', 'http://www.carit.com.cn/UploadFile/Link/2011091415024898.jpg', '2', '1', null, '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_banner_ad` VALUES ('3', 'cccc', 'http://www.carit.com.cn/UploadFile/Link/2011091415025710.jpg', 'http://www.carit.com.cn/UploadFile/Link/2011091415025710.jpg', 'http://www.carit.com.cn/UploadFile/Link/2011091415025710.jpg', '3', '1', null, '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_banner_ad` VALUES ('4', 'dddd', 'http://www.carit.com.cn/UploadFile/Link/2011091415075109.jpg', 'http://www.carit.com.cn/UploadFile/Link/2011091415075109.jpg', 'http://www.carit.com.cn/UploadFile/Link/2011091415075109.jpg', '4', '1', null, '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_banner_ad` VALUES ('6', 'eeee', 'http://www.carit.com.cn/UploadFile/Link/2011091415080021.jpg', 'http://www.carit.com.cn/UploadFile/Link/2011091415080021.jpg', 'http://www.carit.com.cn/UploadFile/Link/2011091415080021.jpg', '5', '1', null, '2012-05-08 11:53:35', '2012-05-08 11:53:35');

-- ----------------------------
-- Table structure for `t_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `t_catalog`;
CREATE TABLE `t_catalog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catalog_code` varchar(100) NOT NULL COMMENT ' 类别代码（具体名称在国际化文件中配置）',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `display_index` int(11) NOT NULL COMMENT '显示顺序',
  `status` tinyint(4) NOT NULL COMMENT '状态：0 停用， 1 启用',
  `create_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  `update_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_t_catalog_catalog_code` (`catalog_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_catalog
-- ----------------------------
INSERT INTO `t_catalog` VALUES ('1', 'universal', '通用机', '1', '1', '2012-08-10 10:15:03', '2012-08-10 11:02:18');
INSERT INTO `t_catalog` VALUES ('2', 'audi', '奥迪系列', '2', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('3', 'bmw', '宝马系列', '3', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('4', 'heveolet', '雪佛莱系列', '4', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('5', 'chrtsler', '克莱斯勒系列', '5', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('6', 'ciroen', '雪铁龙系列', '6', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('7', 'ford', '福特系列', '7', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('8', 'honda', '本田系列', '8', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('9', 'hyundai', '现代系列', '9', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('10', 'kia', '起亚系列', '10', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('11', 'mazda', '马自达系列', '11', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('12', 'mercedes.benz', '奔驰系列', '12', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('13', 'mitsubishi', '三菱系列', '13', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('14', 'opel', '欧宝系列', '14', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('15', 'peugeot', '标致系列', '15', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('16', 'skoda', '斯柯达系列', '16', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('17', 'suzuki', '铃木系列', '17', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('18', 'toyota', '丰田系列', '18', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('19', 'nissan', '日产系列', '19', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_catalog` VALUES ('20', 'volkswagen', '大众系列', '20', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');

-- ----------------------------
-- Table structure for `t_demo_video`
-- ----------------------------
DROP TABLE IF EXISTS `t_demo_video`;
CREATE TABLE `t_demo_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `url` varchar(200) NOT NULL,
  `status` int(11) NOT NULL COMMENT '状态：0 停用；1 启用；',
  `create_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  `update_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_demo_video
-- ----------------------------
INSERT INTO `t_demo_video` VALUES ('3', '在线演示视频', 'http://localhost/attachment/flash/46fe07cc586354a883cb065353f1ec60.1.flv', '1', '2012-09-04 16:31:52', '2012-09-04 16:31:52');

-- ----------------------------
-- Table structure for `t_global_address`
-- ----------------------------
DROP TABLE IF EXISTS `t_global_address`;
CREATE TABLE `t_global_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(5) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `fax` varchar(50) DEFAULT NULL,
  `postalcode` varchar(15) DEFAULT NULL,
  `status` int(11) NOT NULL COMMENT '状态：0 停用；1 启用；',
  `create_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  `update_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_global_address
-- ----------------------------
INSERT INTO `t_global_address` VALUES ('1', 'cn', '深圳市南山区华侨城创意工业园东北B-03栋6楼', '0755-33309198  0755-33309188', '0755-33309173', '518112', '1', '2012-09-03 16:54:17', '2012-09-03 17:22:35');
INSERT INTO `t_global_address` VALUES ('4', 'en', '6/F, B-3 BUilding, OCT-LOFT(East Industry Area), Nanshan District, Shenzhen', '0755-33309198  0755-33309188', '0755-33309173', '518112', '1', '2012-09-03 16:57:53', '2012-09-03 17:25:28');

-- ----------------------------
-- Table structure for `t_media_gallery`
-- ----------------------------
DROP TABLE IF EXISTS `t_media_gallery`;
CREATE TABLE `t_media_gallery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(200) NOT NULL COMMENT '媒体路径（相当路径或绝对路径）',
  `name` varchar(100) NOT NULL,
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '0 图片；1 视频(flv)',
  `status` int(11) NOT NULL COMMENT '状态：0 停用；1 启用；',
  `create_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  `update_time` timestamp NULL DEFAULT '2012-05-08 11:53:35',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_t_media_gallery_url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='媒体库：图片、视频、Flash';

-- ----------------------------
-- Records of t_media_gallery
-- ----------------------------
INSERT INTO `t_media_gallery` VALUES ('1', 'media/37e65f755ba8aa005e5102234c0b5d09.r.jpg', '测试图片', '测试图片', '0', '1', '2012-08-08 15:30:01', '2012-08-08 17:56:28');
INSERT INTO `t_media_gallery` VALUES ('2', 'http://www.carit.com.cn/UploadFile/Link/2011091415024098.jpg', '测试2', '测试', '0', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_media_gallery` VALUES ('3', 'http://www.carit.com.cn/UploadFile/Link/2011091415024898.jpg', 'ddd', '点点滴滴', '0', '1', '2012-08-28 15:44:06', '2012-08-28 15:44:06');
INSERT INTO `t_media_gallery` VALUES ('4', 'http://www.carit.com.cn/UploadFile/Link/2011091415025710.jpg', 'dddddd', 'dddddddddddd', '0', '1', '2012-08-28 16:23:30', '2012-08-28 16:23:30');
INSERT INTO `t_media_gallery` VALUES ('5', 'http://www.carit.com.cn/UploadFile/Link/2011091415075109.jpg', '1234', '1234', '0', '1', '2012-08-28 16:24:36', '2012-08-28 16:24:36');
INSERT INTO `t_media_gallery` VALUES ('6', 'http://localhost/attachment/images/5dba52e98e432154750cba888bab1641.8.jpg', 'ddddddddd', '', '0', '1', '2012-09-04 10:41:26', '2012-09-04 10:41:26');
INSERT INTO `t_media_gallery` VALUES ('7', 'http://localhost/attachment/flash/46fe07cc586354a883cb065353f1ec60.1.flv', '在线演示视频', '在线演示视频', '1', '1', '2012-09-04 16:30:50', '2012-09-04 16:30:50');

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL COMMENT '菜单代码',
  `url` varchar(100) DEFAULT NULL COMMENT '链接',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单',
  `level` int(11) NOT NULL,
  `display_index` int(11) NOT NULL COMMENT '排序',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int(11) NOT NULL COMMENT '状态：0 停用；1 启用；',
  `create_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  `update_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_t_menu_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', 'home', '/', null, '1', '1', '网站首页', '1', '2012-08-04 11:38:09', '2012-08-04 12:07:03');
INSERT INTO `t_menu` VALUES ('2', 'about_us', '/about_us', null, '1', '2', '关于我们', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_menu` VALUES ('3', 'news', '/news_list', null, '1', '3', '新闻动态', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_menu` VALUES ('4', 'products', '/products', null, '1', '4', '产品介绍', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_menu` VALUES ('5', 'online_demo', '/online_demo', null, '1', '5', '在线展示', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_menu` VALUES ('6', 'global', '/global', null, '1', '6', 'Global CARIT', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_menu` VALUES ('7', 'contact_us', '/contact_us', null, '1', '7', '联系我们', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_menu` VALUES ('8', 'bbs', 'http://localhost/bbs', null, '1', '8', '互动论坛', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');

-- ----------------------------
-- Table structure for `t_news`
-- ----------------------------
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE `t_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '类别：0 公司新闻；1 业界动态',
  `language` varchar(5) NOT NULL COMMENT '语言类别',
  `title` varchar(100) NOT NULL,
  `content` varchar(5000) DEFAULT NULL,
  `status` int(11) NOT NULL COMMENT '状态：0 停用；1 启用；',
  `create_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  `update_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  PRIMARY KEY (`id`),
  KEY `idx_t_news_language` (`language`),
  KEY `idx_t_news_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_news
-- ----------------------------
INSERT INTO `t_news` VALUES ('1', '0', 'en', 'CompanyNews Test', '<span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span><span>This is a English news of the company</span>', '1', '2012-05-08 11:53:35', '2012-08-08 09:40:11');
INSERT INTO `t_news` VALUES ('2', '1', 'en', 'Industry News test', 'Industry News test', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_news` VALUES ('3', '0', 'cn', '公司新闻', '测试', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_news` VALUES ('4', '1', 'cn', '行业新闻', '测试', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_news` VALUES ('5', '0', 'cn', '测试1', '<span>测试</span><span>测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试&nbsp;测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试&nbsp;测试测试&nbsp;测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试&nbsp;测试测试&nbsp;测试测试&nbsp;测试测试&nbsp;测试 测试测试&nbsp;测试 测试测试&nbsp;测试测试测试测试测试&nbsp;测试 测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试&nbsp;测试测试&nbsp;测试测试&nbsp;测试 测试测试&nbsp;测试 测试测试&nbsp;测试 测试 测试测试测试测试&nbsp;测试测试&nbsp;测试测试&nbsp;测试测试&nbsp;测试 测试测试&nbsp;测试 测试测试&nbsp;测试 测试测试&nbsp;测试 测试 测试测试&nbsp;测试 测试 测试测试&nbsp;测试 测试 测试 测试测试&nbsp;测试 测试 测试 测试 测试测试&nbsp;测试 测试 测试 测试 测试 测试 测试测试&nbsp;测试 测试 测试 测试 测试 测试 测试 测试测试&nbsp;测试 测试 测试 测试测试&nbsp;测试 测试测试&nbsp;测试 测试测试&nbsp;测试 测试测试&nbsp;测试 测试测试&nbsp;测试 测试 测试测试&nbsp;测试 测试 测试 测试测试&nbsp;测试 测试 测试 测试 测试 测试测试&nbsp;测试 测试 测试 测试 测试测试测试测试测试测试测试测试测试&nbsp;测试测试&nbsp;测试 测试测试&nbsp;测试 测试测试&nbsp;测试 测试测试&nbsp;测试 测试 测试测试&nbsp;测试 测试 测试测试&nbsp;测试 测试 测试 测试测试&nbsp;测试 测试 测试 测试 测试测试&nbsp;测试 测试 测试 测试 测试 测试</span><span></span>', '1', '2012-08-08 09:43:56', '2012-08-08 10:10:12');

-- ----------------------------
-- Table structure for `t_product_release`
-- ----------------------------
DROP TABLE IF EXISTS `t_product_release`;
CREATE TABLE `t_product_release` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catalog_id` int(11) NOT NULL COMMENT '类别Id',
  `language` varchar(5) NOT NULL COMMENT '语言类别',
  `title` varchar(100) NOT NULL,
  `content` varchar(5000) DEFAULT NULL,
  `top` int(11) NOT NULL DEFAULT '0' COMMENT '是否置顶',
  `picture` varchar(1500) NOT NULL COMMENT '产品图片',
  `main_pic` varchar(200) NOT NULL,
  `thumb` varchar(200) NOT NULL COMMENT '缩略图',
  `status` int(11) NOT NULL COMMENT '状态：0 停用；1 启用；',
  `create_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  `update_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_t_product_release_title` (`title`),
  KEY `idx_t_product_release_catalog_id` (`catalog_id`),
  KEY `idx_t_product_release_language` (`language`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_release
-- ----------------------------
INSERT INTO `t_product_release` VALUES ('1', '1', 'en', '6.2〃Universal', '6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal 6.2〃Universal', '0', 'media/37e65f755ba8aa005e5102234c0b5d09.r.jpg;media/37e65f755ba8aa005e5102234c0b5d09.jpg;media/37e65f755ba8aa005e5102234c0b5d09.r.jpg;media/37e65f755ba8aa005e5102234c0b5d09.jpg;media/37e65f755ba8aa005e5102234c0b5d09.r.jpg;media/37e65f755ba8aa005e5102234c0b5d09.jpg', '', 'http://www.carit.com.cn/UploadFile/Content/2012061409481876.jpg', '1', '2012-05-08 11:53:35', '2012-09-03 17:24:14');
INSERT INTO `t_product_release` VALUES ('2', '1', 'cn', '6.2 寸通用机', '6.2 寸通用机', '1', 'http://www.carit.com.cn/UploadFile/Content/2012061409481876.jpg', '', 'http://www.carit.com.cn/UploadFile/Content/2012061409481876.jpg', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_product_release` VALUES ('3', '4', 'en', 'CHRTSLER', 'CHRTSLER', '1', 'http://www.carit.com.cn/UploadFile/Content/2012021609290721.JPG', '', 'http://www.carit.com.cn/UploadFile/Content/2012021609290721.JPG', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_product_release` VALUES ('4', '4', 'cn', '雪佛兰', '雪佛兰', '1', 'http://www.carit.com.cn/UploadFile/Content/2012021609290721.JPG', '', 'http://www.carit.com.cn/UploadFile/Content/2012021609290721.JPG', '1', '2012-05-08 11:53:35', '2012-05-08 11:53:35');
INSERT INTO `t_product_release` VALUES ('5', '2', 'en', '测试', '测试', '0', 'media/37e65f755ba8aa005e5102234c0b5d09.jpg;media/37e65f755ba8aa005e5102234c0b5d09.r.jpg;media/37e65f755ba8aa005e5102234c0b5d09.jpg;media/37e65f755ba8aa005e5102234c0b5d09.r.jpg;media/37e65f755ba8aa005e5102234c0b5d09.r.jpg;media/37e65f755ba8aa005e5102234c0b5d09.r.jpg', '', 'media/37e65f755ba8aa005e5102234c0b5d09.r.jpg', '1', '2012-08-28 13:57:07', '2012-08-28 14:01:42');

-- ----------------------------
-- Table structure for `t_support_language`
-- ----------------------------
DROP TABLE IF EXISTS `t_support_language`;
CREATE TABLE `t_support_language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iso_code` varchar(2) NOT NULL COMMENT '两位语言ISO代码',
  `name` varchar(20) NOT NULL COMMENT '后台显示名称',
  `status` int(11) NOT NULL COMMENT '状态：0 停用；1 启用；',
  `create_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  `update_time` timestamp NOT NULL DEFAULT '2012-05-08 11:53:35',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_t_support_language_iso_code` (`iso_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_support_language
-- ----------------------------
INSERT INTO `t_support_language` VALUES ('1', 'cn', '简体中文', '1', '2012-08-07 12:22:15', '2012-08-07 12:22:15');
INSERT INTO `t_support_language` VALUES ('2', 'en', '英语', '1', '2012-08-07 12:25:24', '2012-08-07 12:25:24');
