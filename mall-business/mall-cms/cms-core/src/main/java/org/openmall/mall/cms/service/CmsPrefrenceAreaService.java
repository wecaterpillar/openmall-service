package org.openmall.mall.cms.service;

import org.openmall.mall.cms.model.CmsPrefrenceArea;
import org.openmall.mall.common.service.IBaseService;

import java.util.List;

/**
 * 优选专区Service
 */
public interface CmsPrefrenceAreaService extends IBaseService<CmsPrefrenceArea> {
    List<CmsPrefrenceArea> listAll();
}
