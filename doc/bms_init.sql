drop database if exists bms;
create database if not exists bms DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

use bms;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          bigint primary key                       NOT NULL AUTO_INCREMENT,
    `username`    varchar(64)                              NOT NULL COMMENT '用户登录名',
    `password`    varchar(64)                              NOT NULL COMMENT '密码',
    `icon`        varchar(512) DEFAULT NULL COMMENT '头像',
    `email`       varchar(128) DEFAULT NULL COMMENT '邮箱',
    `nick_name`   varchar(256) DEFAULT NULL COMMENT '昵称',
    `note`        varchar(512) DEFAULT NULL COMMENT '备注信息',
    `create_time` timestamp    default current_timestamp() NOT NULL COMMENT '创建时间',
    `login_time`  timestamp    DEFAULT NULL COMMENT '最后登录时间',
    `disable`     int(1)       DEFAULT 1                   NOT NULL COMMENT '帐号启用状态: 0->启用;1->禁用',
    unique index udx_user(username)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4 COMMENT ='后台用户表';

INSERT INTO `user` VALUES (1, 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', null, '测试账号', null, '2018-09-29 13:55:30', '2018-09-29 13:55:39', 0);
INSERT INTO `user` VALUES (3, 'admin', '$2a$10$.E1FokumK5GIXWgKlg.Hc.i/0/2.qdAwYFL1zc5QHdyzpXOr38RZO', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/170157_yIl3_1767531.jpg', 'admin@163.com', '系统管理员', '系统管理员', '2018-10-08 13:32:47', '2019-03-20 15:38:50', 0);


DROP TABLE IF EXISTS `user_log`;
CREATE TABLE `user_log`
(
    `id`          bigint primary key                       NOT NULL AUTO_INCREMENT,
    `user_id`     bigint                                   NOT NULL,
    `create_time` timestamp    default current_timestamp() NOT NULL,
    `ip`          varchar(64)  DEFAULT NULL,
    `address`     varchar(128) DEFAULT NULL,
    `user_agent`  varchar(256) DEFAULT NULL COMMENT '浏览器登录类型',
    index idx_user (user_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='后台用户登录日志表';



DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`
(
    `id`          bigint primary key                       NOT NULL AUTO_INCREMENT,
    `name`        varchar(128)                             NOT NULL COMMENT '名称',
    `description` varchar(512) DEFAULT ''                  NOT NULL COMMENT '描述',
    `admin_count` int          DEFAULT 0                   NOT NULL COMMENT '后台用户数量',
    `create_time` timestamp    default current_timestamp() NOT NULL COMMENT '创建时间',
    `disable`     int(1)       DEFAULT 0                   NOT NULL COMMENT '启用状态: 0->启用;1->禁用',
    `sort`        int          DEFAULT 0                   NOT NULL,
    unique key uk_name(`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4 COMMENT ='后台用户角色表';

INSERT INTO `ums_role` VALUES (1, '商品管理员', '商品管理员', 0, '2018-09-30 15:46:11', 0, 0);
INSERT INTO `ums_role` VALUES (2, '商品分类管理员', '商品分类管理员', 0, '2018-09-30 15:53:45', 0, 0);
INSERT INTO `ums_role` VALUES (3, '商品类型管理员', '商品类型管理员', 0, '2018-09-30 15:53:56', 0, 0);
INSERT INTO `ums_role` VALUES (4, '品牌管理员', '品牌管理员', 0, '2018-09-30 15:54:12', 0, 0);



DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`          bigint primary key                       NOT NULL AUTO_INCREMENT,
    `pid`         bigint       DEFAULT NULL COMMENT '父级权限id',
    `name`        varchar(128) DEFAULT NULL COMMENT '名称',
    `value`       varchar(256) DEFAULT NULL COMMENT '权限值',
    `icon`        varchar(512) DEFAULT NULL COMMENT '图标',
    `type`        int(1)       DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
    `uri`         varchar(256) DEFAULT NULL COMMENT '前端资源路径',
    `disable`     int(1)       DEFAULT 0                   NOT NULL COMMENT '启用状态: 0->启用;1->禁用',
    `create_time` timestamp    default current_timestamp() NOT NULL COMMENT '创建时间',
    `sort`        int          DEFAULT 0                   NOT NULL COMMENT '排序',
    index idx_pid (pid)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8mb4 COMMENT ='后台用户权限表';

INSERT INTO `permission` VALUES (1, 0, '商品', null, null, 0, null, 0, '2018-09-29 16:15:14', 0);
INSERT INTO `permission` VALUES (2, 1, '商品列表', 'pms:product:read', null, 1, '/pms/product/index', 0, '2018-09-29 16:17:01', 0);
INSERT INTO `permission` VALUES (3, 1, '添加商品', 'pms:product:create', null, 1, '/pms/product/add', 0, '2018-09-29 16:18:51', 0);
INSERT INTO `permission` VALUES (4, 1, '商品分类', 'pms:productCategory:read', null, 1, '/pms/productCate/index', 0, '2018-09-29 16:23:07', 0);
INSERT INTO `permission` VALUES (5, 1, '商品类型', 'pms:productAttribute:read', null, 1, '/pms/productAttr/index', 0, '2018-09-29 16:24:43', 0);
INSERT INTO `permission` VALUES (6, 1, '品牌管理', 'pms:brand:read', null, 1, '/pms/brand/index', 0, '2018-09-29 16:25:45', 0);
INSERT INTO `permission` VALUES (7, 2, '编辑商品', 'pms:product:update', null, 2, '/pms/product/updateProduct', 0, '2018-09-29 16:34:23', 0);
INSERT INTO `permission` VALUES (8, 2, '删除商品', 'pms:product:delete', null, 2, '/pms/product/delete', 0, '2018-09-29 16:38:33', 0);
INSERT INTO `permission` VALUES (9, 4, '添加商品分类', 'pms:productCategory:create', null, 2, '/pms/productCate/create', 0, '2018-09-29 16:43:23', 0);
INSERT INTO `permission` VALUES (10, 4, '修改商品分类', 'pms:productCategory:update', null, 2, '/pms/productCate/update', 0, '2018-09-29 16:43:55', 0);
INSERT INTO `permission` VALUES (11, 4, '删除商品分类', 'pms:productCategory:delete', null, 2, '/pms/productAttr/delete', 0, '2018-09-29 16:44:38', 0);
INSERT INTO `permission` VALUES (12, 5, '添加商品类型', 'pms:productAttribute:create', null, 2, '/pms/productAttr/create', 0, '2018-09-29 16:45:25', 0);
INSERT INTO `permission` VALUES (13, 5, '修改商品类型', 'pms:productAttribute:update', null, 2, '/pms/productAttr/update', 0, '2018-09-29 16:48:08', 0);
INSERT INTO `permission` VALUES (14, 5, '删除商品类型', 'pms:productAttribute:delete', null, 2, '/pms/productAttr/delete', 0, '2018-09-29 16:48:44', 0);
INSERT INTO `permission` VALUES (15, 6, '添加品牌', 'pms:brand:create', null, 2, '/pms/brand/add', 0, '2018-09-29 16:49:34', 0);
INSERT INTO `permission` VALUES (16, 6, '修改品牌', 'pms:brand:update', null, 2, '/pms/brand/update', 0, '2018-09-29 16:50:55', 0);
INSERT INTO `permission` VALUES (17, 6, '删除品牌', 'pms:brand:delete', null, 2, '/pms/brand/delete', 0, '2018-09-29 16:50:59', 0);
INSERT INTO `permission` VALUES (18, 0, '首页', null, null, 0, null, 0, '2018-09-29 16:51:57', 0);



DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`      bigint primary key NOT NULL AUTO_INCREMENT,
    `user_id` bigint             NOT NULL,
    `role_id` bigint             NOT NULL
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4 COMMENT ='后台用户和角色关系表';

INSERT INTO `user_role` VALUES (1, 3, 1);
INSERT INTO `user_role` VALUES (2, 3, 2);
INSERT INTO `user_role` VALUES (3, 3, 4);


DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE `user_permission`
(
    `id`            bigint primary key NOT NULL AUTO_INCREMENT,
    `user_id`       bigint             NOT NULL,
    `permission_id` bigint             NOT NULL,
    `type`          int(1) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='后台用户和权限关系表(除角色中定义的权限以外的加减权限)';


DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `id`            bigint primary key NOT NULL AUTO_INCREMENT,
    `role_id`       bigint             NOT NULL,
    `permission_id` bigint             NOT NULL
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARSET = utf8mb4 COMMENT ='后台用户角色和权限关系表';

INSERT INTO `role_permission` VALUES (1, 1, 1);
INSERT INTO `role_permission` VALUES (2, 1, 2);
INSERT INTO `role_permission` VALUES (3, 1, 3);
INSERT INTO `role_permission` VALUES (4, 1, 7);
INSERT INTO `role_permission` VALUES (5, 1, 8);
INSERT INTO `role_permission` VALUES (6, 2, 4);
INSERT INTO `role_permission` VALUES (7, 2, 9);
INSERT INTO `role_permission` VALUES (8, 2, 10);
INSERT INTO `role_permission` VALUES (9, 2, 11);
INSERT INTO `role_permission` VALUES (10, 3, 5);
INSERT INTO `role_permission` VALUES (11, 3, 12);
INSERT INTO `role_permission` VALUES (12, 3, 13);
INSERT INTO `role_permission` VALUES (13, 3, 14);
INSERT INTO `role_permission` VALUES (14, 4, 6);
INSERT INTO `role_permission` VALUES (15, 4, 15);
INSERT INTO `role_permission` VALUES (16, 4, 16);
INSERT INTO `role_permission` VALUES (17, 4, 17);

