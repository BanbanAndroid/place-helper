/*
Navicat MySQL Data Transfer

Source Server         : wisteria
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : weibopage

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2015-12-28 21:21:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for enc_url
-- ----------------------------
DROP TABLE IF EXISTS `enc_url`;
CREATE TABLE `enc_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encUrl` varchar(64) DEFAULT NULL,
  `place_page_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `place_page_id` (`place_page_id`) USING BTREE,
  CONSTRAINT `enc_url_ibfk_1` FOREIGN KEY (`place_page_id`) REFERENCES `place_page` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for fans
-- ----------------------------
DROP TABLE IF EXISTS `fans`;
CREATE TABLE `fans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fan_info_json` mediumtext,
  `fan_id` varchar(127) DEFAULT NULL,
  `follower_id` varchar(127) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fans_ibfk_1` (`follower_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for follower
-- ----------------------------
DROP TABLE IF EXISTS `follower`;
CREATE TABLE `follower` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `follower_json` mediumtext,
  `follower_id` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `fan_id` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `follower_ibfk_1` (`fan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for mblog_json
-- ----------------------------
DROP TABLE IF EXISTS `mblog_json`;
CREATE TABLE `mblog_json` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mblog_json_text` mediumtext CHARACTER SET utf8mb4,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `place_url` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `mid` varchar(127) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `place_url` (`place_url`(191)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=33194 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for personal_weibo_json
-- ----------------------------
DROP TABLE IF EXISTS `personal_weibo_json`;
CREATE TABLE `personal_weibo_json` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mblog_json` mediumtext,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mid` varchar(127) DEFAULT NULL,
  `page_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for place_page
-- ----------------------------
DROP TABLE IF EXISTS `place_page`;
CREATE TABLE `place_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `place_url` varchar(64) DEFAULT NULL,
  `place_page` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for place_poiid
-- ----------------------------
DROP TABLE IF EXISTS `place_poiid`;
CREATE TABLE `place_poiid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poiId` varchar(256) DEFAULT NULL,
  `poisId` int(11) DEFAULT NULL,
  `placeName` varchar(128) DEFAULT NULL,
  `country` varchar(64) DEFAULT NULL,
  `province` varchar(64) DEFAULT NULL,
  `city` varchar(64) DEFAULT NULL,
  `county` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `poisId` (`poisId`),
  CONSTRAINT `place_poiid_ibfk_1` FOREIGN KEY (`poisId`) REFERENCES `place_pois` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for place_pois
-- ----------------------------
DROP TABLE IF EXISTS `place_pois`;
CREATE TABLE `place_pois` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placeName` varchar(64) DEFAULT NULL,
  `placePois` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for weibo_content
-- ----------------------------
DROP TABLE IF EXISTS `weibo_content`;
CREATE TABLE `weibo_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placeUrl` varchar(1024) NOT NULL,
  `createTime` varchar(255) CHARACTER SET utf8 NOT NULL,
  `userNickname` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `userHomeUrl` varchar(1024) NOT NULL,
  `createSource` varchar(255) DEFAULT NULL,
  `postText` varchar(255) DEFAULT NULL,
  `weiboUrl` varchar(1024) NOT NULL,
  `pictureNum` int(11) DEFAULT NULL,
  `isVerified` double DEFAULT NULL,
  `verifiedReason` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
