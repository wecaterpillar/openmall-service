package org.openmall.mall.cms.service;

import org.openmall.mall.cms.model.CmsHelp;
import org.openmall.mall.common.service.IBaseService;

public interface CmsHelpService extends IBaseService<CmsHelp> {

    boolean saves(CmsHelp entity);
}
