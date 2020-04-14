package org.openmall.mall.portal.home.domain;

import org.openmall.mall.cms.model.CmsSubject;
import org.openmall.mall.pms.dto.PmsProductCategoryWithChildrenItem;
import org.openmall.mall.pms.model.PmsProductCategory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页导航分类信息
 */
@lombok.Data
public class NavProductCategory extends PmsProductCategoryWithChildrenItem {

    //subCategory(styles), PmsProductCategoryWithChildrenItem.children

    //subjects(category)
    private List<CmsSubject> subjectList = new ArrayList<>();

    //tag(collects)
    private List<String> tagList = new ArrayList<>();


    public NavProductCategory(PmsProductCategory category){
        BeanUtils.copyProperties(category, this);
    }
}
