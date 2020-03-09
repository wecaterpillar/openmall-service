package org.openmall.mall.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.openmall.mall.sms.mapper.SmsCouponProductRelationMapper;
import org.openmall.mall.sms.model.SmsCouponProductRelation;
import org.openmall.mall.sms.service.SmsCouponProductRelationService;
import org.springframework.stereotype.Service;

@Service
public class SmsCouponProductRelationServiceImpl
        extends ServiceImpl<SmsCouponProductRelationMapper, SmsCouponProductRelation>
        implements SmsCouponProductRelationService {
}
