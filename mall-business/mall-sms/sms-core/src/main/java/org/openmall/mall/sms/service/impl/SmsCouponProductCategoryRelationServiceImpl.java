package org.openmall.mall.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.openmall.mall.sms.mapper.SmsCouponProductCategoryRelationMapper;
import org.openmall.mall.sms.model.SmsCouponProductCategoryRelation;
import org.openmall.mall.sms.service.SmsCouponProductCategoryRelationService;
import org.springframework.stereotype.Service;

@Service
public class SmsCouponProductCategoryRelationServiceImpl
        extends ServiceImpl<SmsCouponProductCategoryRelationMapper, SmsCouponProductCategoryRelation>
        implements SmsCouponProductCategoryRelationService {
}
