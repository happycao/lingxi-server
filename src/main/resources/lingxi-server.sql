/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost
Source Database       : lingxi-server

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-05-16 20:22:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_app_version
-- ----------------------------
DROP TABLE IF EXISTS `t_app_version`;
CREATE TABLE `t_app_version` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '版本id',
  `version_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '版本名称',
  `version_code` int(11) DEFAULT NULL COMMENT '版本号',
  `update_flag` int(1) NOT NULL DEFAULT '1' COMMENT '更新标识，1正常、2强制',
  `update_info` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '更新内容',
  `apk_name` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'apk完整名称',
  `apk_url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'apk下载路径',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='APP版本';

-- ----------------------------
-- Table structure for t_feed
-- ----------------------------
DROP TABLE IF EXISTS `t_feed`;
CREATE TABLE `t_feed` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '动态id',
  `user_id` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '用户id',
  `feed_info` varchar(255) NOT NULL COMMENT '动态内容',
  `view_num` int(8) NOT NULL DEFAULT '0' COMMENT '查看数',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '数据状态1可用0不可用-1用户删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='动态表';

-- ----------------------------
-- Table structure for t_feed_action
-- ----------------------------
DROP TABLE IF EXISTS `t_feed_action`;
CREATE TABLE `t_feed_action` (
  `id` varchar(32) NOT NULL COMMENT '操作id',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '操作类型，0点赞1收藏',
  `feed_id` varchar(32) NOT NULL COMMENT '动态id',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='动态点赞';

-- ----------------------------
-- Table structure for t_feed_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_feed_comment`;
CREATE TABLE `t_feed_comment` (
  `id` varchar(32) NOT NULL COMMENT '评论id',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '类型，0评论1回复',
  `feed_id` varchar(32) NOT NULL COMMENT '动态id',
  `comment_id` varchar(32) DEFAULT NULL COMMENT '被评论id',
  `user_id` varchar(32) NOT NULL COMMENT '评论者id',
  `to_user_id` varchar(32) NOT NULL COMMENT '被评论者id',
  `comment_info` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT '评论详情',
  `is_look` tinyint(1) DEFAULT '0' COMMENT '是否已经查看，0未查看1已查看',
  `state` int(1) DEFAULT '1' COMMENT '数据状态1可用0不可用-1用户删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动态评论回复';

-- ----------------------------
-- Table structure for t_feed_photo
-- ----------------------------
DROP TABLE IF EXISTS `t_feed_photo`;
CREATE TABLE `t_feed_photo` (
  `id` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '照片id',
  `feed_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '动态id',
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '照片url',
  `state` int(11) DEFAULT '1' COMMENT '可用状态1可用0不可用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='动态相册表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL COMMENT '用户id',
  `uid` int(11) NOT NULL COMMENT '用户编号',
  `username` varchar(25) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `six` int(1) NOT NULL DEFAULT '-1' COMMENT '性别，-1未知，0女1男',
  `qq` varchar(13) DEFAULT NULL COMMENT 'QQ号',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像路径',
  `signature` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '个性签名',
  `im_token` varchar(100) DEFAULT NULL COMMENT '融云token',
  `login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
