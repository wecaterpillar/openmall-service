package org.openmall.mall.search.dto;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * TODO 价格区间待增加
 */
@lombok.Data
public class AggVO {
    private List<Long> categories = Lists.newArrayList();
    private List<String> brands = Lists.newArrayList();
    private List<AttrGroup> attrs = Lists.newArrayList();
    private Long totalNum;

    @lombok.Data
    public static class AttrGroup{
        private String attrName;
        private List<String> attrValues = Lists.newArrayList();
    }
}
