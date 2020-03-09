package org.openmall.mall.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.openmall.mall.sms.mapper.SmsFlashPromotionLogMapper;
import org.openmall.mall.sms.model.SmsFlashPromotionLog;
import org.openmall.mall.sms.service.SmsFlashPromotionLogService;
import org.springframework.stereotype.Service;

@Service
public class SmsFlashPromotionLogServiceImpl
        extends ServiceImpl<SmsFlashPromotionLogMapper, SmsFlashPromotionLog>
        implements SmsFlashPromotionLogService {
}
