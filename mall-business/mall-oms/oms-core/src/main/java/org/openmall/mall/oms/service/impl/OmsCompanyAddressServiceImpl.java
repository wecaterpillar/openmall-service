package org.openmall.mall.oms.service.impl;

import org.openmall.mall.oms.mapper.OmsCompanyAddressMapper;
import org.openmall.mall.oms.model.OmsCompanyAddress;
import org.openmall.mall.oms.model.OmsCompanyAddressExample;
import org.openmall.mall.oms.service.OmsCompanyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址管理Service实现类
 */
@Service
public class OmsCompanyAddressServiceImpl implements OmsCompanyAddressService {
    @Autowired
    private OmsCompanyAddressMapper companyAddressMapper;
    @Override
    public List<OmsCompanyAddress> list() {
        return companyAddressMapper.selectByExample(new OmsCompanyAddressExample());
    }
}
