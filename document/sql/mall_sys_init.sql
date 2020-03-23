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

INSERT INTO `ums_role` VALUES ('1', '商品管理员', '只能查看及操作商品', '0', '2020-02-03 16:50:37', '1', '0');
INSERT INTO `ums_role` VALUES ('2', '订单管理员', '只能查看及操作订单', '0', '2018-09-30 15:53:45', '1', '0');
INSERT INTO `ums_role` VALUES ('5', '超级管理员', '拥有所有查看和操作功能', '0', '2020-02-02 15:11:05', '1', '0');

-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------



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
--  Records of `sys_permission`
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission`
VALUES ('1', '0', '商品管理', null, null, '0', '/pms', '1', '2018-09-29 16:15:14', '1'),
       ('2', '1', '商品列表', 'pms:product:read', null, '1', 'product', '1', '2018-09-29 16:17:01', '0'),
       ('3', '1', '添加商品', 'pms:product:create', null, '1', 'addProduct', '1', '2018-09-29 16:18:51', '0'),
       ('4', '1', '商品分类', 'pms:productCategory:read', null, '1', 'productCate', '1', '2018-09-29 16:23:07', '0'),
       ('5', '1', '商品类型', 'pms:productAttributeCategory:read', null, '1', 'productAttr', '1', '2018-09-29 16:24:43',
        '0'),
       ('6', '1', '品牌管理', 'pms:brand:read', null, '1', 'brand', '1', '2018-09-29 16:25:45', '0'),
       ('7', '2', '编辑商品', 'pms:product:update', null, '2', 'updateProduct', '1', '2018-09-29 16:34:23', '0'),
       ('8', '2', '商品回收站', 'pms:product:delete', null, '2', 'productRecycle', '1', '2018-09-29 16:38:33', '0'),
       ('9', '4', '添加商品分类', 'pms:productCategory:create', null, '2', 'addProductCate', '1', '2018-09-29 16:43:23', '0'),
       ('10', '4', '修改商品分类', 'pms:productCategory:update', null, '2', 'updateProductCate', '1', '2018-09-29 16:43:55',
        '0'),
       ('11', '4', '删除商品分类', 'pms:productCategory:delete', null, '2', '/pms/productAttr/delete', '1',
        '2018-09-29 16:44:38', '0'),
       ('12', '5', '添加商品类型', 'pms:productAttribute:create', null, '2', 'addProductAttr', '1', '2018-09-29 16:45:25',
        '0'),
       ('13', '5', '修改商品类型', 'pms:productAttribute:update', null, '2', 'updateProductAttr', '1', '2018-09-29 16:48:08',
        '0'),
       ('14', '5', '删除商品类型', 'pms:productAttribute:delete', null, '2', '/pms/productAttr/delete', '1',
        '2018-09-29 16:48:44', '0'),
       ('15', '6', '添加品牌', 'pms:brand:create', null, '2', 'addBrand', '1', '2018-09-29 16:49:34', '0'),
       ('16', '6', '修改品牌', 'pms:brand:update', null, '2', 'updateBrand', '1', '2018-09-29 16:50:55', '0'),
       ('17', '6', '删除品牌', 'pms:brand:delete', null, '2', '/pms/brand/delete', '1', '2018-09-29 16:50:59', '0'),
       ('18', '0', '首页', null, null, '0', '/home', '1', '2018-09-29 16:51:57', '0'),
       ('19', '0', '系统管理', null, null, '0', '/sys', '1', '2018-09-29 16:15:14', '6'),
       ('20', '19', '角色管理', 'sys:role:read', null, '1', 'role', '1', '2018-09-29 16:18:51', '1'),
       ('21', '19', '菜单管理', 'sys:permission:read', null, '1', 'permission', '1', '2018-09-29 16:23:07', '2'),
       ('22', '19', '用户管理', 'sys:admin:read', null, '1', 'admin', '1', '2018-09-29 16:17:01', '0'),
       ('23', '0', '订单管理', null, null, '0', '/oms', '1', '2018-09-29 16:15:14', '2'),
       ('24', '23', '发货列表', 'oms:deliverOrderList:read', null, '1', 'deliverOrderList', '1', '2018-09-29 16:18:51',
        '1'),
       ('25', '23', '订单设置', 'oms:orderSetting:read', null, '1', 'orderSetting', '1', '2018-09-29 16:23:07', '3'),
       ('26', '30', '订单详情', 'oms:order:read', null, '2', 'orderDetail', '1', '2018-09-29 16:23:07', '0'),
       ('27', '23', '退货申请处理', 'oms:orderReturnApply:read', null, '1', 'returnApply', '1', '2018-09-29 16:23:07',
        '2'),
       ('28', '23', '退货原因设置', 'oms:orderReturnReason:read', null, '1', 'returnReason', '1', '2018-09-29 16:23:07',
        '4'),
       ('29', '27', '退货原因详情', 'oms:orderReturnApply:read', null, '2', 'returnApplyDetail', '1',
        '2018-09-29 16:23:07', '0'),
       ('30', '23', '订单列表', 'oms:order:read', null, '1', 'order', '1', '2018-09-29 16:17:01', '0'),
       ('31', '0', '营销管理', null, null, '0', '/sms', '1', '2018-09-29 16:15:14', '4'),
       ('32', '31', '秒杀活动列表', 'marking:SmsFlashPromotion:read', null, '1', 'flash', '2', '2018-09-29 16:17:01', '0'),
       ('36', '31', '优惠券列表', 'marking:SmsCoupon:read', null, '1', 'coupon', '1', '2018-09-29 16:31:07', '0'),
       ('37', '36', '优惠券领取详情', 'marking:SmsCouponHistory:read', null, '2', 'couponHistory', '1', '2018-09-29 16:31:07',
        '0'),
       ('38', '31', '品牌推荐', 'marking:SmsHomeBrand:read', null, '1', 'homeBrand', '1', '2018-09-29 16:31:07', '5'),
       ('39', '31', '新品推荐', 'marking:SmsHomeNewProduct:read', null, '1', 'homeNew', '1', '2018-09-29 16:31:07', '4'),
       ('40', '31', '人气推荐', 'marking:SmsHomeRecommendProduct:read', null, '1', 'homeHot', '1', '2018-09-29 16:31:07',
        '3'),
       ('41', '31', '专题推荐', 'marking:SmsHomeRecommendSubject:read', null, '1', 'homeSubject', '1',
        '2018-09-29 16:31:07', '2'),
       ('42', '31', '广告列表', 'marking:SmsHomeAdvertise:read', null, '1', 'homeAdvertise', '1', '2018-09-29 16:31:07',
        '1'),
       ('43', '0', '会员管理', null, null, '0', '/ums', '1', '2018-09-29 16:15:14', '3'),
       ('44', '43', '会员列表', 'ums:member:read', null, '1', 'member', '1', '2018-09-29 16:18:51', '0'),
       ('45', '19', '代码生成', 'sys:gen:read', null, '1', 'gen', '1', '2018-09-29 16:17:01', '0'),
       ('46', '18', 'HOME', 'sys:gen:read', null, '1', '', '1', '2018-09-29 16:17:01', '0'),
       ('47', '0', '内容管理', null, null, '0', '/cms', '1', '2018-09-29 16:15:14', '5'),
       ('48', '47', '帮助列表', 'cms:help:read', null, '1', 'help', '1', '2018-09-29 16:18:51', '6'),
       ('49', '48', '添加帮助', 'cms:help:create', null, '2', 'addHelp', '1', '2018-09-29 16:18:51', '0'),
       ('50', '47', '专题列表', 'cms:subject:read', null, '1', 'subject', '1', '2018-09-29 16:18:51', '0'),
       ('51', '48', '修改帮助', 'cms:help:update', null, '2', 'updateHelp', '1', '2018-09-29 16:18:51', '0'),
       ('52', '48', '删除帮助', 'cms:help:delete', null, '2', 'deleteHelp', '1', '2018-09-29 16:18:51', '0'),
       ('53', '47', '帮助分类列表', 'cms:helpCategory:read', null, '1', 'helpCategory', '1', '2018-09-29 16:18:51', '7'),
       ('54', '53', '添加帮助分类', 'cms:helpCategory:create', null, '2', 'addHelpCategory', '1', '2018-09-29 16:18:51', '0'),
       ('55', '53', '修改帮助分类', 'cms:helpCategory:update', null, '2', 'updateHelpCategory', '1', '2018-09-29 16:18:51',
        '0'),
       ('56', '53', '删除帮助分类', 'cms:helpCategory:delete', null, '2', 'deleteHelpCategory', '1', '2018-09-29 16:18:51',
        '0'),
       ('57', '50', '添加专题', 'cms:subject:create', null, '2', 'addSubject', '1', '2018-09-29 16:18:51', '0'),
       ('58', '50', '修改专题', 'cms:subject:update', null, '2', 'updateSubject', '1', '2018-09-29 16:18:51', '0'),
       ('59', '50', '删除专题', 'cms:subject:delete', null, '2', 'deleteSubject', '1', '2018-09-29 16:18:51', '0'),
       ('60', '47', '用户举报列表', 'cms:memberReport:read', null, '1', 'memberReport', '1', '2018-09-29 16:18:51', '8'),
       ('61', '60', '添加用户举报', 'cms:memberReport:create', null, '2', 'addMemberReport', '1', '2018-09-29 16:18:51', '0'),
       ('62', '60', '修改用户举报', 'cms:memberReport:update', null, '2', 'updateMemberReport', '1', '2018-09-29 16:18:51',
        '0'),
       ('63', '60', '删除用户举报', 'cms:memberReport:delete', null, '2', 'deleteMemberReport', '1', '2018-09-29 16:18:51',
        '0'),
       ('64', '47', '优选专区列表', 'cms:prefrenceArea:read', null, '1', 'prefrenceArea', '1', '2018-09-29 16:18:51', '9'),
       ('65', '64', '添加优选专区', 'cms:prefrenceArea:create', null, '2', 'addPrefrenceArea', '1', '2018-09-29 16:18:51',
        '0'),
       ('66', '64', '修改优选专区', 'cms:prefrenceArea:update', null, '2', 'updatePrefrenceArea', '1', '2018-09-29 16:18:51',
        '0'),
       ('67', '64', '删除优选专区', 'cms:prefrenceArea:delete', null, '2', 'deletePrefrenceArea', '1', '2018-09-29 16:18:51',
        '0'),
       ('68', '47', '专题评论列表', 'cms:subjectComment:read', null, '1', 'subjectComment', '1', '2018-09-29 16:18:51', '2'),
       ('71', '68', '删除专题评论', 'cms:subjectComment:delete', null, '2', 'deleteSubjectComment', '1',
        '2018-09-29 16:18:51', '0'),
       ('72', '47', '专题分类列表', 'cms:subjectCategory:read', null, '1', 'subjectCategory', '1', '2018-09-29 16:18:51',
        '1'),
       ('73', '72', '添加专题分类', 'cms:subjectCategory:create', null, '2', 'addSubjectCategory', '1', '2018-09-29 16:18:51',
        '0'),
       ('74', '72', '修改专题分类', 'cms:subjectCategory:update', null, '2', 'updateSubjectCategory', '1',
        '2018-09-29 16:18:51', '0'),
       ('75', '72', '删除专题分类', 'cms:subjectCategory:delete', null, '2', 'deleteSubjectCategory', '1',
        '2018-09-29 16:18:51', '0'),
       ('76', '47', '话题列表', 'cms:topic:read', null, '1', 'topic', '1', '2018-09-29 16:18:51', '3'),
       ('77', '76', '添加话题', 'cms:topic:create', null, '2', 'addTopic', '1', '2018-09-29 16:18:51', '0'),
       ('78', '76', '修改话题', 'cms:topic:update', null, '2', 'updateTopic', '1', '2018-09-29 16:18:51', '0'),
       ('79', '76', '删除话题', 'cms:topic:delete', null, '2', 'deleteTopic', '1', '2018-09-29 16:18:51', '0'),
       ('80', '47', '话题分类表列表', 'cms:topicCategory:read', null, '1', 'topicCategory', '1', '2018-09-29 16:18:51', '4'),
       ('81', '80', '添加话题分类表', 'cms:topicCategory:create', null, '2', 'addTopicCategory', '1', '2018-09-29 16:18:51',
        '0'),
       ('82', '80', '修改话题分类表', 'cms:topicCategory:update', null, '2', 'updateTopicCategory', '1', '2018-09-29 16:18:51',
        '0'),
       ('83', '80', '删除话题分类表', 'cms:topicCategory:delete', null, '2', 'deleteTopicCategory', '1', '2018-09-29 16:18:51',
        '0'),
       ('84', '47', '话题评论列表', 'cms:topicComment:read', null, '1', 'topicComment', '1', '2018-09-29 16:18:51', '5'),
       ('87', '84', '删除专题评论', 'cms:topicComment:delete', null, '2', 'deleteTopicComment', '1', '2018-09-29 16:18:51',
        '0'),
       ('88', '19', '区域列表', 'sys:area:read', null, '1', 'area', '1', '2018-09-29 16:18:51', '3'),
       ('89', '88', '添加区域', 'sys:area:create', null, '2', 'addArea', '1', '2018-09-29 16:18:51', '0'),
       ('90', '88', '修改区域', 'sys:area:update', null, '2', 'updateArea', '1', '2018-09-29 16:18:51', '0'),
       ('91', '88', '删除区域', 'sys:area:delete', null, '2', 'deleteArea', '1', '2018-09-29 16:18:51', '0'),
       ('92', '31', '拼团列表', 'marking:group:read', null, '1', 'group', '1', '2018-09-29 16:18:51', '0'),
       ('93', '92', '添加拼团', 'marking:group:create', null, '2', 'addGroup', '1', '2018-09-29 16:18:51', '0'),
       ('94', '92', '修改拼团', 'marking:group:update', null, '2', 'updateGroup', '1', '2018-09-29 16:18:51', '0'),
       ('95', '92', '删除拼团', 'marking:group:delete', null, '2', 'deleteGroup', '1', '2018-09-29 16:18:51', '0'),
       ('100', '43', '会员等级表列表', 'ums:memberLevel:read', null, '1', 'memberLevel', '1', '2018-09-29 16:18:51', '0'),
       ('101', '100', '添加会员等级表', 'ums:memberLevel:create', null, '2', 'addMemberLevel', '1', '2018-09-29 16:18:51',
        '0'),
       ('102', '100', '修改会员等级表', 'ums:memberLevel:update', null, '2', 'updateMemberLevel', '1',
        '2018-09-29 16:18:51', '0'),
       ('103', '100', '删除会员等级表', 'ums:memberLevel:delete', null, '2', 'deleteMemberLevel', '1',
        '2018-09-29 16:18:51', '0'),
       ('104', '19', '前台日志列表', 'sys:webLog:read', null, '1', 'webLog', '1', '2018-09-29 16:18:51', '4'),
       ('105', '19', '后台日志列表', 'sys:adminLog:read', null, '1', 'adminLog', '1', '2018-09-29 16:18:51', '5'),
       ('106', '42', '添加广告', 'marking:homeAdvertise:create', null, '2', 'addHomeAdvertise', '1',
        '2018-09-29 16:18:51', '0'),
       ('107', '42', '修改广告', 'marking:homeAdvertise:update', null, '2', 'updateHomeAdvertise', '1',
        '2018-09-29 16:18:51', '0'),
       ('108', '31', '红包列表', 'marking:redPacket:read', null, '1', 'redPacket', '1', '2018-09-29 16:18:51', '1'),
       ('109', '108', '添加红包', 'marking:redPacket:create', null, '2', 'addRedPacket', '1', '2018-09-29 16:18:51',
        '0'),
       ('110', '108', '修改红包', 'marking:redPacket:update', null, '2', 'updateRedPacket', '1', '2018-09-29 16:18:51',
        '0'),
       ('111', '108', '删除红包', 'marking:redPacket:delete', null, '2', 'deleteRedPacket', '1', '2018-09-29 16:18:51',
        '0'),
       ('112', '20', '添加角色', 'sys:role:create', null, '2', 'addRole', '1', '2018-09-29 16:18:51', '1'),
       ('113', '20', '修改角色', 'sys:role:update', null, '2', 'updateRole', '1', '2018-09-29 16:18:51', '1'),
       ('114', '20', '删除角色', 'sys:role:delete', null, '2', 'deleteRole', '1', '2018-09-29 16:18:51', '1'),
       ('115', '22', '添加用户', 'sys:admin:create', null, '2', 'addAdmin', '1', '2018-09-29 16:18:51', '1'),
       ('116', '22', '修改用户', 'sys:admin:update', null, '2', 'updateAdmin', '1', '2018-09-29 16:18:51', '1'),
       ('117', '22', '删除用户', 'sys:admin:delete', null, '2', 'deleteAdmin', '1', '2018-09-29 16:18:51', '1'),
       ('118', '21', '添加菜单', 'sys:permission:create', null, '2', 'addPermission', '1', '2018-09-29 16:18:51', '1'),
       ('119', '21', '修改菜单', 'sys:permission:update', null, '2', 'updatePermission', '1', '2018-09-29 16:18:51',
        '1'),
       ('120', '21', '删除菜单', 'sys:permission:delete', null, '2', 'deletePermission', '1', '2018-09-29 16:18:51',
        '1');
COMMIT;


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



-- ----------------------------
-- Records of ums_menu
-- ----------------------------
INSERT INTO `ums_menu` VALUES ('1', '0', '2020-02-02 14:50:36', '商品', '0', '0', 'pms', 'product', '0');
INSERT INTO `ums_menu` VALUES ('2', '1', '2020-02-02 14:51:50', '商品列表', '1', '0', 'product', 'product-list', '0');
INSERT INTO `ums_menu` VALUES ('3', '1', '2020-02-02 14:52:44', '添加商品', '1', '0', 'addProduct', 'product-add', '0');
INSERT INTO `ums_menu` VALUES ('4', '1', '2020-02-02 14:53:51', '商品分类', '1', '0', 'productCate', 'product-cate', '0');
INSERT INTO `ums_menu` VALUES ('5', '1', '2020-02-02 14:54:51', '商品类型', '1', '0', 'productAttr', 'product-attr', '0');
INSERT INTO `ums_menu` VALUES ('6', '1', '2020-02-02 14:56:29', '品牌管理', '1', '0', 'brand', 'product-brand', '0');
INSERT INTO `ums_menu` VALUES ('7', '0', '2020-02-02 16:54:07', '订单', '0', '0', 'oms', 'order', '0');
INSERT INTO `ums_menu` VALUES ('8', '7', '2020-02-02 16:55:18', '订单列表', '1', '0', 'order', 'product-list', '0');
INSERT INTO `ums_menu` VALUES ('9', '7', '2020-02-02 16:56:46', '订单设置', '1', '0', 'orderSetting', 'order-setting', '0');
INSERT INTO `ums_menu` VALUES ('10', '7', '2020-02-02 16:57:39', '退货申请处理', '1', '0', 'returnApply', 'order-return', '0');
INSERT INTO `ums_menu` VALUES ('11', '7', '2020-02-02 16:59:40', '退货原因设置', '1', '0', 'returnReason', 'order-return-reason', '0');
INSERT INTO `ums_menu` VALUES ('12', '0', '2020-02-04 16:18:00', '营销', '0', '0', 'sms', 'sms', '0');
INSERT INTO `ums_menu` VALUES ('13', '12', '2020-02-04 16:19:22', '秒杀活动列表', '1', '0', 'flash', 'sms-flash', '0');
INSERT INTO `ums_menu` VALUES ('14', '12', '2020-02-04 16:20:16', '优惠券列表', '1', '0', 'coupon', 'sms-coupon', '0');
INSERT INTO `ums_menu` VALUES ('16', '12', '2020-02-07 16:22:38', '品牌推荐', '1', '0', 'homeBrand', 'product-brand', '0');
INSERT INTO `ums_menu` VALUES ('17', '12', '2020-02-07 16:23:14', '新品推荐', '1', '0', 'homeNew', 'sms-new', '0');
INSERT INTO `ums_menu` VALUES ('18', '12', '2020-02-07 16:26:38', '人气推荐', '1', '0', 'homeHot', 'sms-hot', '0');
INSERT INTO `ums_menu` VALUES ('19', '12', '2020-02-07 16:28:16', '专题推荐', '1', '0', 'homeSubject', 'sms-subject', '0');
INSERT INTO `ums_menu` VALUES ('20', '12', '2020-02-07 16:28:42', '广告列表', '1', '0', 'homeAdvertise', 'sms-ad', '0');
INSERT INTO `ums_menu` VALUES ('21', '0', '2020-02-07 16:29:13', '权限', '0', '0', 'sys', 'ums', '0');
INSERT INTO `ums_menu` VALUES ('22', '21', '2020-02-07 16:29:51', '用户列表', '1', '0', 'admin', 'ums-admin', '0');
INSERT INTO `ums_menu` VALUES ('23', '21', '2020-02-07 16:30:13', '角色列表', '1', '0', 'role', 'ums-role', '0');
INSERT INTO `ums_menu` VALUES ('24', '21', '2020-02-07 16:30:53', '菜单列表', '1', '0', 'menu', 'ums-menu', '0');
INSERT INTO `ums_menu` VALUES ('25', '21', '2020-02-07 16:31:13', '资源列表', '1', '0', 'resource', 'ums-resource', '0');


-- ----------------------------
-- Records of ums_resource
-- ----------------------------
INSERT INTO `ums_resource` VALUES ('1', '2020-02-04 17:04:55', '商品品牌管理', '/brand/**', null, '1');
INSERT INTO `ums_resource` VALUES ('2', '2020-02-04 17:05:35', '商品属性分类管理', '/productAttribute/**', null, '1');
INSERT INTO `ums_resource` VALUES ('3', '2020-02-04 17:06:13', '商品属性管理', '/productAttribute/**', null, '1');
INSERT INTO `ums_resource` VALUES ('4', '2020-02-04 17:07:15', '商品分类管理', '/productCategory/**', null, '1');
INSERT INTO `ums_resource` VALUES ('5', '2020-02-04 17:09:16', '商品管理', '/product/**', null, '1');
INSERT INTO `ums_resource` VALUES ('6', '2020-02-04 17:09:53', '商品库存管理', '/sku/**', null, '1');
INSERT INTO `ums_resource` VALUES ('8', '2020-02-05 14:43:37', '订单管理', '/order/**', '', '2');
INSERT INTO `ums_resource` VALUES ('9', '2020-02-05 14:44:22', ' 订单退货申请管理', '/returnApply/**', '', '2');
INSERT INTO `ums_resource` VALUES ('10', '2020-02-05 14:45:08', '退货原因管理', '/returnReason/**', '', '2');
INSERT INTO `ums_resource` VALUES ('11', '2020-02-05 14:45:43', '订单设置管理', '/orderSetting/**', '', '2');
INSERT INTO `ums_resource` VALUES ('12', '2020-02-05 14:46:23', '收货地址管理', '/companyAddress/**', '', '2');
INSERT INTO `ums_resource` VALUES ('13', '2020-02-07 16:37:22', '优惠券管理', '/coupon/**', '', '3');
INSERT INTO `ums_resource` VALUES ('14', '2020-02-07 16:37:59', '优惠券领取记录管理', '/couponHistory/**', '', '3');
INSERT INTO `ums_resource` VALUES ('15', '2020-02-07 16:38:28', '限时购活动管理', '/flash/**', '', '3');
INSERT INTO `ums_resource` VALUES ('16', '2020-02-07 16:38:59', '限时购商品关系管理', '/flashProductRelation/**', '', '3');
INSERT INTO `ums_resource` VALUES ('17', '2020-02-07 16:39:22', '限时购场次管理', '/flashSession/**', '', '3');
INSERT INTO `ums_resource` VALUES ('18', '2020-02-07 16:40:07', '首页轮播广告管理', '/home/advertise/**', '', '3');
INSERT INTO `ums_resource` VALUES ('19', '2020-02-07 16:40:34', '首页品牌管理', '/home/brand/**', '', '3');
INSERT INTO `ums_resource` VALUES ('20', '2020-02-07 16:41:06', '首页新品管理', '/home/newProduct/**', '', '3');
INSERT INTO `ums_resource` VALUES ('21', '2020-02-07 16:42:16', '首页人气推荐管理', '/home/recommendProduct/**', '', '3');
INSERT INTO `ums_resource` VALUES ('22', '2020-02-07 16:42:48', '首页专题推荐管理', '/home/recommendSubject/**', '', '3');
INSERT INTO `ums_resource` VALUES ('23', '2020-02-07 16:44:56', ' 商品优选管理', '/prefrenceArea/**', '', '5');
INSERT INTO `ums_resource` VALUES ('24', '2020-02-07 16:45:39', '商品专题管理', '/subject/**', '', '5');
INSERT INTO `ums_resource` VALUES ('25', '2020-02-07 16:47:34', '后台用户管理', '/admin/**', '', '4');
INSERT INTO `ums_resource` VALUES ('26', '2020-02-07 16:48:24', '后台用户角色管理', '/role/**', '', '4');
INSERT INTO `ums_resource` VALUES ('27', '2020-02-07 16:48:48', '后台菜单管理', '/menu/**', '', '4');
INSERT INTO `ums_resource` VALUES ('28', '2020-02-07 16:49:18', '后台资源分类管理', '/resourceCategory/**', '', '4');
INSERT INTO `ums_resource` VALUES ('29', '2020-02-07 16:49:45', '后台资源管理', '/resource/**', '', '4');

-- ----------------------------
-- Records of ums_resource_category
-- ----------------------------
INSERT INTO `ums_resource_category` VALUES ('1', '2020-02-05 10:21:44', '商品模块', '0');
INSERT INTO `ums_resource_category` VALUES ('2', '2020-02-05 10:22:34', '订单模块', '0');
INSERT INTO `ums_resource_category` VALUES ('3', '2020-02-05 10:22:48', '营销模块', '0');
INSERT INTO `ums_resource_category` VALUES ('4', '2020-02-05 10:23:04', '权限模块', '0');
INSERT INTO `ums_resource_category` VALUES ('5', '2020-02-07 16:34:27', '内容模块', '0');
INSERT INTO `ums_resource_category` VALUES ('6', '2020-02-07 16:35:49', '其他模块', '0');

-- ----------------------------
-- Records of ums_role_menu_relation
-- ----------------------------
INSERT INTO `ums_role_menu_relation` VALUES ('33', '1', '1');
INSERT INTO `ums_role_menu_relation` VALUES ('34', '1', '2');
INSERT INTO `ums_role_menu_relation` VALUES ('35', '1', '3');
INSERT INTO `ums_role_menu_relation` VALUES ('36', '1', '4');
INSERT INTO `ums_role_menu_relation` VALUES ('37', '1', '5');
INSERT INTO `ums_role_menu_relation` VALUES ('38', '1', '6');
INSERT INTO `ums_role_menu_relation` VALUES ('53', '2', '7');
INSERT INTO `ums_role_menu_relation` VALUES ('54', '2', '8');
INSERT INTO `ums_role_menu_relation` VALUES ('55', '2', '9');
INSERT INTO `ums_role_menu_relation` VALUES ('56', '2', '10');
INSERT INTO `ums_role_menu_relation` VALUES ('57', '2', '11');
INSERT INTO `ums_role_menu_relation` VALUES ('72', '5', '1');
INSERT INTO `ums_role_menu_relation` VALUES ('73', '5', '2');
INSERT INTO `ums_role_menu_relation` VALUES ('74', '5', '3');
INSERT INTO `ums_role_menu_relation` VALUES ('75', '5', '4');
INSERT INTO `ums_role_menu_relation` VALUES ('76', '5', '5');
INSERT INTO `ums_role_menu_relation` VALUES ('77', '5', '6');
INSERT INTO `ums_role_menu_relation` VALUES ('78', '5', '7');
INSERT INTO `ums_role_menu_relation` VALUES ('79', '5', '8');
INSERT INTO `ums_role_menu_relation` VALUES ('80', '5', '9');
INSERT INTO `ums_role_menu_relation` VALUES ('81', '5', '10');
INSERT INTO `ums_role_menu_relation` VALUES ('82', '5', '11');
INSERT INTO `ums_role_menu_relation` VALUES ('83', '5', '12');
INSERT INTO `ums_role_menu_relation` VALUES ('84', '5', '13');
INSERT INTO `ums_role_menu_relation` VALUES ('85', '5', '14');
INSERT INTO `ums_role_menu_relation` VALUES ('86', '5', '16');
INSERT INTO `ums_role_menu_relation` VALUES ('87', '5', '17');
INSERT INTO `ums_role_menu_relation` VALUES ('88', '5', '18');
INSERT INTO `ums_role_menu_relation` VALUES ('89', '5', '19');
INSERT INTO `ums_role_menu_relation` VALUES ('90', '5', '20');
INSERT INTO `ums_role_menu_relation` VALUES ('91', '5', '21');
INSERT INTO `ums_role_menu_relation` VALUES ('92', '5', '22');
INSERT INTO `ums_role_menu_relation` VALUES ('93', '5', '23');
INSERT INTO `ums_role_menu_relation` VALUES ('94', '5', '24');
INSERT INTO `ums_role_menu_relation` VALUES ('95', '5', '25');


-- ----------------------------
-- Records of ums_role_resource_relation
-- ----------------------------
INSERT INTO `ums_role_resource_relation` VALUES ('103', '2', '8');
INSERT INTO `ums_role_resource_relation` VALUES ('104', '2', '9');
INSERT INTO `ums_role_resource_relation` VALUES ('105', '2', '10');
INSERT INTO `ums_role_resource_relation` VALUES ('106', '2', '11');
INSERT INTO `ums_role_resource_relation` VALUES ('107', '2', '12');
INSERT INTO `ums_role_resource_relation` VALUES ('142', '5', '1');
INSERT INTO `ums_role_resource_relation` VALUES ('143', '5', '2');
INSERT INTO `ums_role_resource_relation` VALUES ('144', '5', '3');
INSERT INTO `ums_role_resource_relation` VALUES ('145', '5', '4');
INSERT INTO `ums_role_resource_relation` VALUES ('146', '5', '5');
INSERT INTO `ums_role_resource_relation` VALUES ('147', '5', '6');
INSERT INTO `ums_role_resource_relation` VALUES ('148', '5', '8');
INSERT INTO `ums_role_resource_relation` VALUES ('149', '5', '9');
INSERT INTO `ums_role_resource_relation` VALUES ('150', '5', '10');
INSERT INTO `ums_role_resource_relation` VALUES ('151', '5', '11');
INSERT INTO `ums_role_resource_relation` VALUES ('152', '5', '12');
INSERT INTO `ums_role_resource_relation` VALUES ('153', '5', '13');
INSERT INTO `ums_role_resource_relation` VALUES ('154', '5', '14');
INSERT INTO `ums_role_resource_relation` VALUES ('155', '5', '15');
INSERT INTO `ums_role_resource_relation` VALUES ('156', '5', '16');
INSERT INTO `ums_role_resource_relation` VALUES ('157', '5', '17');
INSERT INTO `ums_role_resource_relation` VALUES ('158', '5', '18');
INSERT INTO `ums_role_resource_relation` VALUES ('159', '5', '19');
INSERT INTO `ums_role_resource_relation` VALUES ('160', '5', '20');
INSERT INTO `ums_role_resource_relation` VALUES ('161', '5', '21');
INSERT INTO `ums_role_resource_relation` VALUES ('162', '5', '22');
INSERT INTO `ums_role_resource_relation` VALUES ('163', '5', '23');
INSERT INTO `ums_role_resource_relation` VALUES ('164', '5', '24');
INSERT INTO `ums_role_resource_relation` VALUES ('165', '5', '25');
INSERT INTO `ums_role_resource_relation` VALUES ('166', '5', '26');
INSERT INTO `ums_role_resource_relation` VALUES ('167', '5', '27');
INSERT INTO `ums_role_resource_relation` VALUES ('168', '5', '28');
INSERT INTO `ums_role_resource_relation` VALUES ('169', '5', '29');
INSERT INTO `ums_role_resource_relation` VALUES ('170', '1', '1');
INSERT INTO `ums_role_resource_relation` VALUES ('171', '1', '2');
INSERT INTO `ums_role_resource_relation` VALUES ('172', '1', '3');
INSERT INTO `ums_role_resource_relation` VALUES ('173', '1', '4');
INSERT INTO `ums_role_resource_relation` VALUES ('174', '1', '5');
INSERT INTO `ums_role_resource_relation` VALUES ('175', '1', '6');
INSERT INTO `ums_role_resource_relation` VALUES ('176', '1', '23');
INSERT INTO `ums_role_resource_relation` VALUES ('177', '1', '24');
