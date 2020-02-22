package org.openmall.mall.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.openmall.mall.cms.mapper.CmsMemberReportMapper;
import org.openmall.mall.cms.model.CmsMemberReport;
import org.openmall.mall.cms.service.CmsMemberReportService;
import org.springframework.stereotype.Service;

@Service
public class CmsMemberReportServiceImpl
        extends ServiceImpl<CmsMemberReportMapper, CmsMemberReport>
        implements CmsMemberReportService {
}
