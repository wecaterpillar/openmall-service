

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin`
VALUES ('1', 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', null, '测试账号', null,
        '2018-09-29 13:55:30', '2018-09-29 13:55:39', '1');
INSERT INTO `ums_admin`
VALUES ('3', 'admin', '$2a$10$TJkwVdlpbHKnV45.nBxbgeFHmQRmyWlshg94lFu2rKxVtT2OMniDO',
        'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20190129/170157_yIl3_1767531.jpg', 'admin@163.com',
        '系统管理员', '系统管理员', '2018-10-08 13:32:47', '2019-03-20 15:38:50', '1');


-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role`
VALUES ('1', '商品管理员', '商品管理员', '0', '2018-09-30 15:46:11', '1', '0');
INSERT INTO `ums_role`
VALUES ('2', '商品分类管理员', '商品分类管理员', '0', '2018-09-30 15:53:45', '1', '0');
INSERT INTO `ums_role`
VALUES ('3', '商品类型管理员', '商品类型管理员', '0', '2018-09-30 15:53:56', '1', '0');
INSERT INTO `ums_role`
VALUES ('4', '品牌管理员', '品牌管理员', '0', '2018-09-30 15:54:12', '1', '0');


-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------
INSERT INTO `ums_admin_role_relation`
VALUES ('13', '3', '1');
INSERT INTO `ums_admin_role_relation`
VALUES ('15', '3', '2');
INSERT INTO `ums_admin_role_relation`
VALUES ('16', '3', '4');


-- ----------------------------
-- Records of ums_permission
-- ----------------------------
INSERT INTO `ums_permission`
VALUES ('1', '0', '商品', null, null, '0', null, '1', '2018-09-29 16:15:14', '0');
INSERT INTO `ums_permission`
VALUES ('2', '1', '商品列表', 'pms:product:read', null, '1', '/pms/product/index', '1', '2018-09-29 16:17:01', '0');
INSERT INTO `ums_permission`
VALUES ('3', '1', '添加商品', 'pms:product:create', null, '1', '/pms/product/add', '1', '2018-09-29 16:18:51', '0');
INSERT INTO `ums_permission`
VALUES ('4', '1', '商品分类', 'pms:productCategory:read', null, '1', '/pms/productCate/index', '1', '2018-09-29 16:23:07',
        '0');
INSERT INTO `ums_permission`
VALUES ('5', '1', '商品类型', 'pms:productAttribute:read', null, '1', '/pms/productAttr/index', '1', '2018-09-29 16:24:43',
        '0');
INSERT INTO `ums_permission`
VALUES ('6', '1', '品牌管理', 'pms:brand:read', null, '1', '/pms/brand/index', '1', '2018-09-29 16:25:45', '0');
INSERT INTO `ums_permission`
VALUES ('7', '2', '编辑商品', 'pms:product:update', null, '2', '/pms/product/updateProduct', '1', '2018-09-29 16:34:23',
        '0');
INSERT INTO `ums_permission`
VALUES ('8', '2', '删除商品', 'pms:product:delete', null, '2', '/pms/product/delete', '1', '2018-09-29 16:38:33', '0');
INSERT INTO `ums_permission`
VALUES ('9', '4', '添加商品分类', 'pms:productCategory:create', null, '2', '/pms/productCate/create', '1',
        '2018-09-29 16:43:23', '0');
INSERT INTO `ums_permission`
VALUES ('10', '4', '修改商品分类', 'pms:productCategory:update', null, '2', '/pms/productCate/update', '1',
        '2018-09-29 16:43:55', '0');
INSERT INTO `ums_permission`
VALUES ('11', '4', '删除商品分类', 'pms:productCategory:delete', null, '2', '/pms/productAttr/delete', '1',
        '2018-09-29 16:44:38', '0');
INSERT INTO `ums_permission`
VALUES ('12', '5', '添加商品类型', 'pms:productAttribute:create', null, '2', '/pms/productAttr/create', '1',
        '2018-09-29 16:45:25', '0');
INSERT INTO `ums_permission`
VALUES ('13', '5', '修改商品类型', 'pms:productAttribute:update', null, '2', '/pms/productAttr/update', '1',
        '2018-09-29 16:48:08', '0');
INSERT INTO `ums_permission`
VALUES ('14', '5', '删除商品类型', 'pms:productAttribute:delete', null, '2', '/pms/productAttr/delete', '1',
        '2018-09-29 16:48:44', '0');
INSERT INTO `ums_permission`
VALUES ('15', '6', '添加品牌', 'pms:brand:create', null, '2', '/pms/brand/add', '1', '2018-09-29 16:49:34', '0');
INSERT INTO `ums_permission`
VALUES ('16', '6', '修改品牌', 'pms:brand:update', null, '2', '/pms/brand/update', '1', '2018-09-29 16:50:55', '0');
INSERT INTO `ums_permission`
VALUES ('17', '6', '删除品牌', 'pms:brand:delete', null, '2', '/pms/brand/delete', '1', '2018-09-29 16:50:59', '0');
INSERT INTO `ums_permission`
VALUES ('18', '0', '首页', null, null, '0', null, '1', '2018-09-29 16:51:57', '0');


-- ----------------------------
-- Records of ums_role_permission_relation
-- ----------------------------
INSERT INTO `ums_role_permission_relation`
VALUES ('1', '1', '1');
INSERT INTO `ums_role_permission_relation`
VALUES ('2', '1', '2');
INSERT INTO `ums_role_permission_relation`
VALUES ('3', '1', '3');
INSERT INTO `ums_role_permission_relation`
VALUES ('4', '1', '7');
INSERT INTO `ums_role_permission_relation`
VALUES ('5', '1', '8');
INSERT INTO `ums_role_permission_relation`
VALUES ('6', '2', '4');
INSERT INTO `ums_role_permission_relation`
VALUES ('7', '2', '9');
INSERT INTO `ums_role_permission_relation`
VALUES ('8', '2', '10');
INSERT INTO `ums_role_permission_relation`
VALUES ('9', '2', '11');
INSERT INTO `ums_role_permission_relation`
VALUES ('10', '3', '5');
INSERT INTO `ums_role_permission_relation`
VALUES ('11', '3', '12');
INSERT INTO `ums_role_permission_relation`
VALUES ('12', '3', '13');
INSERT INTO `ums_role_permission_relation`
VALUES ('13', '3', '14');
INSERT INTO `ums_role_permission_relation`
VALUES ('14', '4', '6');
INSERT INTO `ums_role_permission_relation`
VALUES ('15', '4', '15');
INSERT INTO `ums_role_permission_relation`
VALUES ('16', '4', '16');
INSERT INTO `ums_role_permission_relation`
VALUES ('17', '4', '17');
