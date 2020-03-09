package org.openmall.mall.portal.pms.service;

import org.openmall.mall.pms.dto.PmsProductQueryParam;
import org.openmall.mall.pms.dto.PmsProductResult;
import org.openmall.mall.pms.model.PmsProduct;

import java.util.List;

/**
 * 商品查询
 */
public interface PortalPmsProductService {
    List<PmsProduct> list(String keyword);
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, String sortField, Integer pageSize, Integer pageNum);
    PmsProductResult getProductById(Long id);
}
