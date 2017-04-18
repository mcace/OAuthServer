/*
Navicat MySQL Data Transfer

Source Server         : 本地数据库
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : oauth

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2017-04-18 10:55:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_application
-- ----------------------------
DROP TABLE IF EXISTS `app_application`;
CREATE TABLE `app_application` (
  `apply_id` int(8) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(20) NOT NULL COMMENT 'app名称',
  `app_host` varchar(75) NOT NULL COMMENT 'app所在域名',
  `app_owner` varchar(10) NOT NULL COMMENT 'app持有者',
  `apply_date` datetime NOT NULL COMMENT '申请接入日期',
  `apply_state` char(1) NOT NULL DEFAULT '2' COMMENT '申请状态：0：已拒绝，1：已通过，2：正在申请，3：重新认证',
  PRIMARY KEY (`apply_id`),
  UNIQUE KEY `app_name` (`app_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for app_info
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `app_id` int(8) NOT NULL AUTO_INCREMENT,
  `app_secret` varchar(30) NOT NULL COMMENT 'app密钥',
  `app_name` varchar(20) NOT NULL COMMENT 'app名称',
  `app_host` varchar(75) NOT NULL COMMENT 'app所在域名',
  `app_owner` varchar(10) NOT NULL COMMENT 'app持有者',
  `verify_host_code` varchar(16) NOT NULL COMMENT '验证网站域名码，类似微信',
  `access_date` datetime NOT NULL COMMENT '应用获取api调用权限日期',
  `expire_date` datetime NOT NULL COMMENT '应用api调用权限过期日',
  `refresh_date` datetime NOT NULL COMMENT '应用刷新调用api权限权限日期',
  PRIMARY KEY (`app_id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for authorization_code
-- ----------------------------
DROP TABLE IF EXISTS `authorization_code`;
CREATE TABLE `authorization_code` (
  `code_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_open_id` varchar(32) NOT NULL COMMENT '用户对应应用的唯一ID，可以提供给应用开发人员使用',
  `authorz_code` varchar(32) NOT NULL COMMENT '授权码',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `expire_date` datetime NOT NULL COMMENT '授权码过期日期',
  `scope` varchar(10) NOT NULL DEFAULT '' COMMENT '令牌适用范围',
  PRIMARY KEY (`code_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_token`;
CREATE TABLE `oauth_token` (
  `token_id` int(8) NOT NULL AUTO_INCREMENT,
  `user_open_id` varchar(32) NOT NULL COMMENT '用户对应应用的唯一ID，可以提供给应用开发人员使用',
  `access_token` varchar(30) NOT NULL COMMENT '准入令牌',
  `refresh_token` varchar(30) NOT NULL COMMENT '刷新令牌',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  `access_token_expire_date` datetime NOT NULL COMMENT 'access_token过期时间',
  `refresh_token_expire_date` datetime NOT NULL COMMENT 'refresh_token过期时间',
  `scope` varchar(10) NOT NULL DEFAULT '' COMMENT '令牌适用范围',
  `refresh_count` int(4) NOT NULL DEFAULT '0' COMMENT 'access_token刷新计数',
  `token_statues` char(1) NOT NULL DEFAULT '0' COMMENT '令牌状态：0：正常，1：被撤销',
  PRIMARY KEY (`token_id`),
  KEY `accessToken` (`access_token`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` char(50) NOT NULL COMMENT '用户名',
  `password` char(50) NOT NULL COMMENT '密码',
  `nickname` varchar(30) NOT NULL COMMENT '用户昵称',
  `reg_date` datetime NOT NULL COMMENT '注册时间',
  `status` varchar(1) NOT NULL DEFAULT '0' COMMENT '状态：0：正常，1：封禁',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_app_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_app_relation`;
CREATE TABLE `user_app_relation` (
  `user_open_id` varchar(32) NOT NULL COMMENT '用户对应应用的唯一ID，可以提供给应用开发人员使用',
  `user_id` int(8) NOT NULL COMMENT '用户ID',
  `app_id` int(8) NOT NULL COMMENT '应用ID',
  `create_date` datetime NOT NULL COMMENT '创建日期',
  PRIMARY KEY (`user_open_id`),
  KEY `userid-appid` (`user_id`,`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
