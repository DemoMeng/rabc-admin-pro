/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50560
 Source Host           : localhost:3306
 Source Schema         : hlw_manage_admin

 Target Server Type    : MySQL
 Target Server Version : 50560
 File Encoding         : 65001

 Date: 20/07/2020 10:25:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for global_config
-- ----------------------------
DROP TABLE IF EXISTS `global_config`;
CREATE TABLE `global_config` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '归类名称',
  `level_belong` int(8) NOT NULL COMMENT '1:第一级  2：第二级  3：第三级',
  `num` bigint(20) NOT NULL COMMENT '数量\n',
  `by_role_id` bigint(20) DEFAULT NULL COMMENT '归属审核角色id',
  `type` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '区别标识 （接口赠送：API  合同赠送 ：CONTRACT）',
  `status` int(1) NOT NULL COMMENT '启用状态 （ 0 ：启用  1：未启用）',
  `create_time` datetime DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of global_config
-- ----------------------------
BEGIN;
INSERT INTO `global_config` VALUES (1, '接口赠送审核设置', 1, 3, 3, 'API', 0, '2020-07-13 15:38:07', 1, '2020-07-13 15:38:07', 1);
INSERT INTO `global_config` VALUES (2, '接口赠送审核设置', 2, 6, 2, 'API', 0, '2020-07-13 15:38:07', 1, '2020-07-13 15:38:07', 1);
INSERT INTO `global_config` VALUES (3, '接口赠送审核设置', 3, 99, 1, 'API', 1, '2020-07-13 15:38:07', 1, '2020-07-13 15:46:08', 1);
INSERT INTO `global_config` VALUES (4, '合同份额赠送审核设置', 1, 3, 3, 'CONTRACT', 0, '2020-07-13 15:38:07', 1, '2020-07-13 15:38:07', 1);
INSERT INTO `global_config` VALUES (5, '合同份额赠送审核设置', 2, 50, 1, 'CONTRACT', 0, '2020-07-13 15:38:07', 1, '2020-07-13 15:45:51', 1);
INSERT INTO `global_config` VALUES (6, '合同份额赠送审核设置', 3, 102, 1, 'CONTRACT', 0, '2020-07-13 15:38:07', 1, '2020-07-13 15:46:08', 1);
COMMIT;

-- ----------------------------
-- Table structure for hlw_manage_log
-- ----------------------------
DROP TABLE IF EXISTS `hlw_manage_log`;
CREATE TABLE `hlw_manage_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `ip` varchar(100) DEFAULT NULL COMMENT '操作IP地址',
  `request_params` longtext COMMENT '请求参数',
  `response_data` longtext COMMENT '返回数据',
  `request_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '请求接口地址',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '操作方式',
  `description` varchar(255) DEFAULT NULL COMMENT '操作名称',
  `result` varchar(255) DEFAULT NULL COMMENT '操作结果',
  `operator` bigint(20) DEFAULT NULL COMMENT '操作人id',
  `operator_name` varchar(20) DEFAULT NULL COMMENT '操作人姓名',
  `operator_role` varchar(255) DEFAULT NULL COMMENT '操作人角色\n',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `operate_account` varchar(255) DEFAULT NULL COMMENT '操作账号\n',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `index_for_id` (`id`) USING BTREE,
  KEY `index_for_operate_name` (`operator_name`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of hlw_manage_log
-- ----------------------------
BEGIN;
INSERT INTO `hlw_manage_log` VALUES (13, '127.0.0.1', NULL, NULL, NULL, NULL, '份额赠送-订单审核', '份额赠送已处于订单审核列表中【订单id:3】', 1, '蒙大大大拿', '超级管理员,普通管理员,部门管理员', '2020-07-18 09:49:20', '13128507506');
COMMIT;

-- ----------------------------
-- Table structure for hlw_manage_menu
-- ----------------------------
DROP TABLE IF EXISTS `hlw_manage_menu`;
CREATE TABLE `hlw_manage_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` bigint(20) NOT NULL COMMENT '父级id',
  `belong_type` int(1) NOT NULL COMMENT '归属类别 1：一级菜单 2：二级菜单 3：三级菜单 4：tab链  5：按钮',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `sort` int(8) NOT NULL DEFAULT '1' COMMENT '排序',
  `href` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '链接',
  `route` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '前端路由',
  `permission` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '权限',
  `icon` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图标',
  `create_by` bigint(20) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记 0：正常 1：已删除',
  PRIMARY KEY (`id`),
  KEY `index_for_parent_id` (`parent_id`) USING BTREE,
  KEY `index_for_href` (`href`) USING BTREE,
  KEY `index_for_name` (`name`) USING BTREE,
  KEY `index_for_belong_type` (`belong_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='菜单表';

-- ----------------------------
-- Records of hlw_manage_menu
-- ----------------------------
BEGIN;
INSERT INTO `hlw_manage_menu` VALUES (1, 0, 1, 'XXX', 1, '#', 'haha', '*', '333', 1, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (2, 0, 1, 'XXX开放平台', 21, '#', '', '*', '/images/log', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (13, 16, 2, '日志中心', 1, NULL, 'logcenter', 'system:log', NULL, 1, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (14, 16, 2, '成员管理', 1, NULL, 'membermanage', 'system:admin', NULL, 1, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (15, 16, 2, '角色管理', 1, NULL, 'rolemanage', 'system:role', NULL, 1, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (16, 0, 1, '管理设置', 2, '#', 'settings', '*', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (28, 1, 2, '订单管理', 1, NULL, 'order', 'manage:order', 'md-reorder', 14, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (30, 1, 2, '用户管理', 1, NULL, 'usermanages', 'manage:user', 'ios-people', 14, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (31, 1, 2, '套餐定价', 1, NULL, 'packagepricing', 'manage:combination-price', 'md-cash', 14, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (32, 1, 2, '企业审核', 1, NULL, 'companyverify', 'manage:audit', 'ios-construct', 14, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (33, 1, 2, '经销商管理', 1, NULL, 'agentmanage', 'manage:agent', 'md-contacts', 14, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (34, 30, 3, '企业用户', 1, NULL, 'major', 'manage:user:company', 'company', 1, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (35, 30, 3, '个人用户', 1, NULL, 'basepersonal', 'manage:user:personal', 'user', 1, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (36, 30, 3, '未认证用户', 1, NULL, 'uncertified', 'manage:user:un-auth', NULL, 14, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (40, 2, 2, '企业审核', 1, NULL, 'accountVerify', 'open:audit', 'ios-key', 14, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (41, 2, 2, '客户管理', 1, NULL, 'customerManage', 'open:customer', 'md-contact', 14, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (42, 2, 2, '订单管理', 1, NULL, 'orderMange', 'open:order', 'md-reorder', 14, '2020-07-09 06:32:46', 15, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (43, 2, 2, '经销商管理', 1, NULL, 'agentManageapi', 'open:agent', 'md-people', 14, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (46, 16, 2, '菜单管理', 1, NULL, 'menumanage', 'system:menu', NULL, 14, '2020-07-09 06:32:46', 14, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (47, 28, 4, '企业订单记录', 1, NULL, NULL, 'manage:order:company', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '订单管理企业订单记录列表', 0);
INSERT INTO `hlw_manage_menu` VALUES (48, 28, 4, '个人订单记录', 1, NULL, NULL, 'manage:order:personal', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '订单管理个人订单记录列表', 0);
INSERT INTO `hlw_manage_menu` VALUES (49, 34, 4, '专业版用户', 1, NULL, NULL, 'manage:user:company:prod', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户专业版列表', 0);
INSERT INTO `hlw_manage_menu` VALUES (50, 34, 4, '基础版用户', 1, NULL, NULL, 'manage:user:company:base', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户基础版列表', 0);
INSERT INTO `hlw_manage_menu` VALUES (51, 49, 5, '创建账号', 1, NULL, 'zycj', 'manage:user:company:prod:add', '', 1, '2020-07-09 06:32:46', 2, '2020-07-18 09:04:11', '用户管理企业用户专业版创建账号', 0);
INSERT INTO `hlw_manage_menu` VALUES (52, 49, 5, '账号充值', 1, NULL, NULL, 'manage:user:company:prod:charge', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户专业版创账号充值', 0);
INSERT INTO `hlw_manage_menu` VALUES (53, 49, 5, '份额赠送', 1, NULL, NULL, 'manage:user:company:prod:free', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户专业版创份额赠送', 0);
INSERT INTO `hlw_manage_menu` VALUES (54, 49, 5, '用户改名', 1, NULL, NULL, 'manage:user:company:prod:rename', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户专业版创用户改名', 0);
INSERT INTO `hlw_manage_menu` VALUES (55, 49, 5, '用户资料', 1, NULL, NULL, 'manage:user:company:prod:detail', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户专业版创用户资料', 0);
INSERT INTO `hlw_manage_menu` VALUES (56, 49, 5, '成为经销商', 1, NULL, NULL, 'manage:user:company:prod:be-agent', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户专业版创成为经销商', 0);
INSERT INTO `hlw_manage_menu` VALUES (57, 50, 5, '创建账号', 1, NULL, NULL, 'manage:user:company:base:add', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户基础版创建账号', 1);
INSERT INTO `hlw_manage_menu` VALUES (58, 50, 5, '账号充值', 1, NULL, NULL, 'manage:user:company:base:charge', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户基础版账号充值', 0);
INSERT INTO `hlw_manage_menu` VALUES (59, 50, 5, '份额赠送', 1, NULL, NULL, 'manage:user:company:base:free', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户基础版份额赠送', 0);
INSERT INTO `hlw_manage_menu` VALUES (60, 50, 5, '用户改名', 1, NULL, NULL, 'manage:user:company:base:rename', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户基础版用户改名', 0);
INSERT INTO `hlw_manage_menu` VALUES (61, 50, 5, '用户资料', 1, NULL, NULL, 'manage:user:company:base:detail', '', 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理企业用户基础版用户资料', 0);
INSERT INTO `hlw_manage_menu` VALUES (62, 35, 5, '账号充值', 1, NULL, NULL, 'manage:user:personal:charge', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理个人用户账号充值', 0);
INSERT INTO `hlw_manage_menu` VALUES (63, 35, 5, '份额赠送', 1, NULL, NULL, 'manage:user:personal:free', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理个人用户份额赠送', 0);
INSERT INTO `hlw_manage_menu` VALUES (64, 35, 5, '用户改名', 1, NULL, NULL, 'manage:user:personal:rename', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', '用户管理个人用户用户改名', 0);
INSERT INTO `hlw_manage_menu` VALUES (65, 35, 5, '列表', 1, NULL, NULL, 'manage:user:personal:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (66, 36, 5, '列表', 1, NULL, NULL, 'manage:user:un-auth:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (67, 31, 5, '列表', 1, NULL, NULL, 'manage:combination-price:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (68, 31, 5, '编辑', 1, NULL, NULL, 'manage:combination-price:touch', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (69, 31, 5, '删除', 1, NULL, NULL, 'manage:combination-price:remove', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (70, 31, 5, '添加', 1, NULL, NULL, 'manage:combination-price:add', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 1);
INSERT INTO `hlw_manage_menu` VALUES (71, 32, 4, '账号审核', 1, NULL, NULL, 'manage:audit:account', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (72, 32, 4, '签证审核', 1, NULL, NULL, 'manage:audit:signature', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (73, 71, 5, '账号审核', 1, NULL, NULL, 'manage:audit:account:audit', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (74, 72, 5, '签章审核', 1, NULL, NULL, 'manage:audit:signature:audit', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (75, 33, 4, '一级经销商', 1, NULL, NULL, 'manage:agent:first', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (76, 33, 4, '二级经销商', 1, NULL, NULL, 'manage:agent:second', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (77, 33, 4, '三级经销商', 1, NULL, NULL, 'manage:agent:third', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (78, 75, 5, '创建账号', 1, NULL, NULL, 'manage:agent:first:add', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (79, 75, 5, '账号充值', 1, NULL, NULL, 'manage:agent:first:charge', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (80, 75, 5, '份额赠送', 1, NULL, NULL, 'manage:agent:first:free', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (81, 75, 5, '用户资料', 1, NULL, NULL, 'manage:agent:first:detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (82, 2, 2, 'API管理', 1, NULL, NULL, 'open:api', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (83, 82, 4, '已上架接口', 1, NULL, NULL, 'open:api:on-sale', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (84, 82, 4, '未上架接口', 1, NULL, NULL, 'open:api:for-sale', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (85, 84, 5, '编辑', 1, NULL, NULL, 'open:api:for-sale:edit', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (86, 84, 5, '上架', 1, NULL, NULL, 'open:api:for-sale:on', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (87, 84, 5, '删除', 1, NULL, NULL, 'open:api:for-sale:remove', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (88, 83, 5, '下架', 1, NULL, NULL, 'open:api:on-sale:off', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (89, 84, 5, '查看', 1, NULL, NULL, 'open:api:for-sale:detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (90, 83, 5, '查看', 1, NULL, NULL, 'open:api:on-sale:detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (91, 40, 4, '账号审核', 1, NULL, NULL, 'open:audit:account', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (92, 40, 4, '订单审核', 1, NULL, NULL, 'open:audit:order', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (93, 91, 5, '审核', 1, NULL, NULL, 'open:audit:account:audit', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (94, 92, 5, '通过', 1, NULL, NULL, 'open:audit:order:pass', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (95, 41, 5, '创建账号', 1, NULL, NULL, 'open:customer:add', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (96, 41, 5, '导出', 1, NULL, NULL, 'open:customer:export', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (97, 41, 5, '查看', 1, NULL, NULL, 'open:customer:api-detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (98, 41, 5, '接口充值', 1, NULL, NULL, 'open:customer:charge', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (99, 41, 5, '份额赠送', 1, NULL, NULL, 'open:customer:free', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (100, 41, 5, '客户资料', 1, NULL, NULL, 'open:customer:detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (101, 41, 5, '成为经销商', 1, NULL, NULL, 'open:customer:be-agent', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (102, 42, 5, '列表', 1, NULL, NULL, 'open:order:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (103, 43, 4, '一级经销商', 1, NULL, NULL, 'open:agent:first', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (104, 43, 4, '二级经销商', 1, NULL, NULL, 'open:agent:second', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (105, 43, 4, '三级经销商', 1, NULL, NULL, 'open:agent:third', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (106, 103, 5, '创建账号', 1, NULL, NULL, 'open:agent:first:add', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (107, 103, 5, '导出', 1, NULL, NULL, 'open:agent:first:export', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (108, 103, 5, '查看', 1, NULL, NULL, 'open:agent:first:api-detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (109, 103, 5, '接口充值', 1, NULL, NULL, 'open:agent:first:charge', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (110, 103, 5, '份额赠送', 1, NULL, NULL, 'open:agent:first:free', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (111, 103, 5, '客户资料', 1, NULL, NULL, 'open:agent:first:detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (112, 13, 5, '列表', 1, NULL, NULL, 'system:log:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (113, 14, 5, '列表', 1, NULL, NULL, 'system:admin:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (114, 14, 5, '禁用', 1, NULL, NULL, 'system:admin:abandon', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (115, 14, 5, '编辑', 1, NULL, NULL, 'system:admin:edit', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (116, 14, 5, '删除', 1, NULL, NULL, 'system:admin:remove', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (117, 15, 5, '列表', 1, NULL, NULL, 'system:role:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (118, 15, 5, '编辑', 1, NULL, NULL, 'system:role:edit', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (119, 15, 5, '删除', 1, NULL, NULL, 'system:role:remove', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (120, 15, 5, '添加角色', 1, NULL, NULL, 'system:role:add', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (121, 46, 5, '列表', 1, NULL, NULL, 'system:menu:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (122, 46, 5, '添加菜单', 1, NULL, NULL, 'system:menu:add', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (123, 46, 5, '编辑', 1, NULL, NULL, 'system:menu:edit', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (124, 46, 5, '删除', 1, NULL, NULL, 'system:menu:remove', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (125, 47, 5, '列表', 1, NULL, NULL, 'manage:order:company:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (126, 48, 5, '列表', 1, NULL, NULL, 'manage:order:personal:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (127, 49, 5, '列表', 1, NULL, NULL, 'manage:user:company:prod:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (128, 50, 5, '列表', 1, NULL, NULL, 'manage:user:company:base:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (129, 71, 5, '列表', 1, NULL, NULL, 'manage:audit:account:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (130, 72, 5, '列表', 1, NULL, NULL, 'manage:audit:signature:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (131, 75, 5, '查看', 1, NULL, NULL, 'manage:agent:first:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (132, 91, 5, '列表', 1, NULL, NULL, 'open:audit:account:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (133, 92, 5, '列表', 1, NULL, NULL, 'open:audit:order:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (134, 41, 5, '列表', 1, NULL, NULL, 'open:customer:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (135, 103, 5, '列表', 1, NULL, NULL, 'open:agent:first:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (136, 83, 5, '列表', 1, NULL, NULL, 'open:api:on-sale:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (137, 84, 5, '列表', 1, NULL, NULL, 'open:api:for-sale:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (138, 32, 4, '订单审核', 1, NULL, NULL, 'manage:audit:order', NULL, 1, '2020-07-13 13:47:19', 1, '2020-07-13 13:47:25', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (139, 138, 5, '列表', 1, NULL, NULL, 'manage:audit:order:list', NULL, 1, '2020-07-13 13:48:11', 1, '2020-07-13 13:48:17', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (140, 138, 5, '通过', 1, NULL, NULL, 'manage:audit:order:pass', NULL, 1, '2020-07-13 13:48:59', 1, '2020-07-13 13:49:03', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (141, 138, 5, '不通过', 1, NULL, NULL, 'manage:audit:order:re-back', NULL, 1, '2020-07-13 13:49:30', 1, '2020-07-13 13:49:35', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (142, 92, 5, '打回', 1, NULL, NULL, 'open:audit:order:re-back', NULL, 1, '2020-07-14 16:03:45', 1, '2020-07-14 16:03:52', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (143, 103, 5, '查看下级客户', 1, NULL, NULL, 'open:agent:first:child', NULL, 1, '2020-07-17 07:24:48', 1, '2020-07-17 07:24:48', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (144, 104, 5, '导出', 1, NULL, NULL, 'open:agent:second:export', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (145, 104, 5, '查看', 1, NULL, NULL, 'open:agent:second:api-detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (146, 104, 5, '接口充值', 1, NULL, NULL, 'open:agent:second:charge', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (147, 104, 5, '份额赠送', 1, NULL, NULL, 'open:agent:second:free', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (148, 104, 5, '客户资料', 1, NULL, NULL, 'open:agent:second:detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (149, 104, 5, '列表', 1, NULL, NULL, 'open:agent:second:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (150, 104, 5, '查看下级客户', 1, NULL, NULL, 'open:agent:second:child', NULL, 1, '2020-07-17 07:24:48', 1, '2020-07-17 07:24:48', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (151, 104, 5, '创建账号', 1, NULL, NULL, 'open:agent:second:add', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (152, 105, 5, '创建账号', 1, NULL, NULL, 'open:agent:third:add', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (153, 105, 5, '导出', 1, NULL, NULL, 'open:agent:third:export', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (154, 105, 5, '查看', 1, NULL, NULL, 'open:agent:third:api-detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (155, 105, 5, '接口充值', 1, NULL, NULL, 'open:agent:third:charge', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (156, 105, 5, '份额赠送', 1, NULL, NULL, 'open:agent:third:free', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (157, 105, 5, '客户资料', 1, NULL, NULL, 'open:agent:third:detail', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (158, 105, 5, '列表', 1, NULL, NULL, 'open:agent:third:list', NULL, 1, '2020-07-09 06:32:46', 1, '2020-07-09 06:33:11', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (159, 105, 5, '查看下级客户', 1, NULL, NULL, 'open:agent:third:child', NULL, 1, '2020-07-17 07:24:48', 1, '2020-07-17 07:24:48', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (160, 2, 2, '服务动态配置', 1, NULL, NULL, 'open:company-service', NULL, 1, '2020-07-17 10:57:10', 1, '2020-07-17 10:57:19', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (161, 84, 5, '新增', 1, NULL, NULL, 'open:api:for-sale:add', NULL, 1, '2020-07-17 10:26:50', 1, '2020-07-17 10:26:44', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (162, 160, 5, '列表', 1, NULL, NULL, 'open:company-service:list', NULL, 1, '2020-07-17 07:24:48', 1, '2020-07-17 07:24:48', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (163, 160, 5, '编辑', 1, NULL, NULL, 'open:company-service:edit', NULL, 1, '2020-07-17 07:24:48', 1, '2020-07-17 07:24:48', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (164, 2, 2, '服务策略管理', 1, NULL, NULL, 'open:service-strategy', NULL, 1, '2020-07-17 07:24:48', 1, '2020-07-17 07:24:48', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (165, 164, 5, '列表', 1, NULL, NULL, 'open:service-strategy:list', NULL, 1, '2020-07-17 07:24:48', 1, '2020-07-17 07:24:48', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (166, 164, 5, '编辑', 1, NULL, NULL, 'open:service-strategy:edit', NULL, 1, '2020-07-17 07:24:48', 1, '2020-07-17 07:24:48', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (167, 164, 5, '新增', 1, NULL, NULL, 'open:service-strategy:add', NULL, 1, '2020-07-17 07:24:48', 1, '2020-07-17 07:24:48', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (168, 16, 2, '份额审核设置', 1, NULL, NULL, 'system:audit-config', NULL, 1, '2020-07-17 17:20:23', 1, '2020-07-17 17:22:29', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (169, 168, 5, '列表', 1, NULL, NULL, 'system:audit-config:list', NULL, 1, '2020-07-17 09:26:10', 1, '2020-07-17 09:26:10', NULL, 0);
INSERT INTO `hlw_manage_menu` VALUES (170, 168, 5, '编辑', 1, NULL, NULL, 'system:audit-config:edit', NULL, 1, '2020-07-17 09:26:10', 1, '2020-07-17 09:26:10', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for hlw_manage_role
-- ----------------------------
DROP TABLE IF EXISTS `hlw_manage_role`;
CREATE TABLE `hlw_manage_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `no` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `role_type` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色类型',
  `useable` int(1) DEFAULT NULL COMMENT '是否可用  0:可用 1不可用',
  `create_by` bigint(20) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记  0:正常  1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

-- ----------------------------
-- Records of hlw_manage_role
-- ----------------------------
BEGIN;
INSERT INTO `hlw_manage_role` VALUES (1, '1', '超级管理员', 'SA', 0, 1, '2013-05-27 08:00:00', 2, '2020-07-17 17:28:02', NULL, 0);
INSERT INTO `hlw_manage_role` VALUES (2, '2', '普通管理员', 'CR', 0, 1, '2013-05-27 08:00:00', 15, '2020-06-17 16:26:55', NULL, 0);
INSERT INTO `hlw_manage_role` VALUES (3, '3', '部门管理员', 'CR', 0, 1, '2013-05-27 08:00:00', 15, '2020-06-23 16:39:47', NULL, 0);
INSERT INTO `hlw_manage_role` VALUES (4, '4', '行政', 'CR', 0, 1, '2013-05-27 08:00:00', 1, '2020-06-09 16:59:15', NULL, 0);
INSERT INTO `hlw_manage_role` VALUES (5, '5', '人事', 'CR', 0, 1, '2013-05-27 08:00:00', 1, '2020-06-09 16:59:10', NULL, 0);
INSERT INTO `hlw_manage_role` VALUES (6, '6', '财务', 'CR', 0, 1, '2013-05-27 08:00:00', 18, '2020-06-20 10:51:24', NULL, 0);
INSERT INTO `hlw_manage_role` VALUES (7, '7', '助理', 'CR', 0, 1, '2013-05-27 08:00:00', 1, '2020-06-09 16:58:48', NULL, 0);
INSERT INTO `hlw_manage_role` VALUES (8, '11', '运营管理员', 'CR', 0, 18, '2020-06-17 14:29:26', 1, '2020-06-17 14:32:49', NULL, 0);
INSERT INTO `hlw_manage_role` VALUES (9, 'js', '产品技术中心', 'CR', 0, 27, '2020-07-03 14:38:09', 27, '2020-07-03 14:38:09', NULL, 0);
INSERT INTO `hlw_manage_role` VALUES (10, 'developer', '开发者', 'CR', 0, 1, '2020-07-08 16:44:16', 1, '2020-07-08 16:44:23', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for hlw_manage_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `hlw_manage_role_menu`;
CREATE TABLE `hlw_manage_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `index_for_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of hlw_manage_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `hlw_manage_role_menu` VALUES (1, 1);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 2);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 13);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 14);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 15);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 16);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 28);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 30);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 31);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 32);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 33);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 34);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 35);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 36);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 40);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 41);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 42);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 43);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 46);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 47);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 48);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 49);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 50);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 51);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 52);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 53);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 54);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 55);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 56);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 58);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 59);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 60);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 61);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 62);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 63);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 64);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 65);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 66);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 67);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 68);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 69);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 71);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 72);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 73);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 74);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 75);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 76);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 77);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 78);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 79);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 80);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 81);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 82);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 83);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 84);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 85);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 86);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 87);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 88);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 89);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 90);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 91);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 92);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 93);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 94);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 95);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 96);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 97);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 98);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 99);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 100);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 101);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 102);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 103);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 104);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 105);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 106);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 107);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 108);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 109);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 110);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 111);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 112);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 113);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 114);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 115);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 116);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 117);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 118);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 119);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 120);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 121);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 122);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 123);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 124);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 125);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 126);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 127);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 128);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 129);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 130);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 131);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 132);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 133);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 134);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 135);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 136);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 137);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 138);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 139);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 140);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 141);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 142);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 143);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 144);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 145);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 146);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 147);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 148);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 149);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 150);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 151);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 152);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 153);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 154);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 155);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 156);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 157);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 158);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 159);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 160);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 161);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 162);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 163);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 164);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 165);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 166);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 167);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 168);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 169);
INSERT INTO `hlw_manage_role_menu` VALUES (1, 170);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 13);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 16);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 30);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 31);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 34);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 35);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 40);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 49);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 50);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 62);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 63);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 64);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 65);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 91);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 92);
INSERT INTO `hlw_manage_role_menu` VALUES (2, 112);
INSERT INTO `hlw_manage_role_menu` VALUES (3, 14);
INSERT INTO `hlw_manage_role_menu` VALUES (3, 113);
INSERT INTO `hlw_manage_role_menu` VALUES (3, 114);
INSERT INTO `hlw_manage_role_menu` VALUES (3, 115);
INSERT INTO `hlw_manage_role_menu` VALUES (3, 116);
COMMIT;

-- ----------------------------
-- Table structure for hlw_manage_user
-- ----------------------------
DROP TABLE IF EXISTS `hlw_manage_user`;
CREATE TABLE `hlw_manage_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '登录名',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '盐',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `user_type` int(1) DEFAULT NULL COMMENT '用户类型',
  `area` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地区',
  `login_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `enable` int(1) DEFAULT NULL COMMENT '是否启用 0：启用 1：不启用',
  `login_flag` int(1) DEFAULT NULL COMMENT '是否可登录  0：可  ，1：不可',
  `create_by` bigint(20) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(1) NOT NULL DEFAULT '0' COMMENT '删除标记 0：正常 ，1：已删除',
  PRIMARY KEY (`id`),
  KEY `index_for_login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of hlw_manage_user
-- ----------------------------
BEGIN;
INSERT INTO `hlw_manage_user` VALUES (1, '13128507506', 'f60e1961fb788d2bbd99088cad4bbc00', '6d67dd76f51346779d8457fc0e39a36b', '蒙大大大拿', NULL, '局域网', '127.0.0.1', '2020-07-18 10:41:49', 0, 0, 1, '2020-04-24 15:48:10', 18, '2020-06-29 10:40:26', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for hlw_manage_user_role
-- ----------------------------
DROP TABLE IF EXISTS `hlw_manage_user_role`;
CREATE TABLE `hlw_manage_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `index_for_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户角色关联表';

-- ----------------------------
-- Records of hlw_manage_user_role
-- ----------------------------
BEGIN;
INSERT INTO `hlw_manage_user_role` VALUES (1, 1);
INSERT INTO `hlw_manage_user_role` VALUES (1, 2);
INSERT INTO `hlw_manage_user_role` VALUES (1, 3);
INSERT INTO `hlw_manage_user_role` VALUES (2, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
