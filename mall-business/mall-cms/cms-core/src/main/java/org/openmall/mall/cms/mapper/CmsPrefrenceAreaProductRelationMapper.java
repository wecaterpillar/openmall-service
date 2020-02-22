package org.openmall.mall.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.openmall.mall.cms.model.CmsPrefrenceAreaProductRelation;
import org.openmall.mall.cms.model.CmsPrefrenceAreaProductRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsPrefrenceAreaProductRelationMapper extends BaseMapper<CmsPrefrenceAreaProductRelation> {
    long countByExample(CmsPrefrenceAreaProductRelationExample example);

    int deleteByExample(CmsPrefrenceAreaProductRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsPrefrenceAreaProductRelation record);

    int insertSelective(CmsPrefrenceAreaProductRelation record);

    List<CmsPrefrenceAreaProductRelation> selectByExample(CmsPrefrenceAreaProductRelationExample example);

    CmsPrefrenceAreaProductRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CmsPrefrenceAreaProductRelation record, @Param("example") CmsPrefrenceAreaProductRelationExample example);

    int updateByExample(@Param("record") CmsPrefrenceAreaProductRelation record, @Param("example") CmsPrefrenceAreaProductRelationExample example);

    int updateByPrimaryKeySelective(CmsPrefrenceAreaProductRelation record);

    int updateByPrimaryKey(CmsPrefrenceAreaProductRelation record);
}
