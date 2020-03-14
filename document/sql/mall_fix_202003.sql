

-- update 20200314
-- pms_sku_stock增加字段sp_data
alter table pms_sku_stock
add column `sp_data`         varchar(500)   DEFAULT NULL COMMENT '商品销售属性，json格式';

-- 删除字段sp1,sp2,sp3
