package org.openmall.mall.pms.sync.service.impl;

import cn.hutool.json.JSONObject;
import org.openmall.mall.pms.service.PmsProductService;
import org.openmall.mall.pms.sync.service.ProductSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSyncServiceImpl implements ProductSyncService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private PmsProductService productService;

    public int importProduct(){
        int count = 0;
        List<JSONObject> goods = mongoTemplate.findAll(JSONObject.class, "goodsinfos");
        for(JSONObject good:goods){

        }
        return count;
    }
}
