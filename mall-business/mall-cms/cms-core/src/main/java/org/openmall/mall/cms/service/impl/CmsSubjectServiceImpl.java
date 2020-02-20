package org.openmall.mall.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import org.openmall.mall.cms.mapper.CmsSubjectCategoryMapper;
import org.openmall.mall.cms.mapper.CmsSubjectMapper;
import org.openmall.mall.cms.model.CmsSubject;
import org.openmall.mall.cms.model.CmsSubjectCategory;
import org.openmall.mall.cms.model.CmsSubjectExample;
import org.openmall.mall.cms.service.CmsSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 商品专题Service实现类
 */
@Service
public class CmsSubjectServiceImpl extends ServiceImpl<CmsSubjectMapper,CmsSubject> implements CmsSubjectService {
    @Autowired
    private CmsSubjectMapper subjectMapper;

    @Resource
    private CmsSubjectCategoryMapper subjectCategoryMapper;

    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.selectByExample(new CmsSubjectExample());
    }

    @Override
    public List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        CmsSubjectExample example = new CmsSubjectExample();
        CmsSubjectExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andTitleLike("%" + keyword + "%");
        }
        return subjectMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public boolean saves(CmsSubject entity) {
        entity.setCreateTime(new Date());
        subjectMapper.insert(entity);
        CmsSubjectCategory category = subjectCategoryMapper.selectById(entity.getCategoryId());
        category.setSubjectCount(category.getSubjectCount()==null?0:category.getSubjectCount() + 1);
        subjectCategoryMapper.updateById(category);
        return true;
    }

    @Override
    public int updateRecommendStatus(Long ids, Integer recommendStatus) {
        CmsSubject record = new CmsSubject();
        record.setRecommendStatus(recommendStatus);
        return subjectMapper.update(record, new QueryWrapper<CmsSubject>().eq("id", ids));
    }

    @Override
    public int updateShowStatus(Long ids, Integer showStatus) {
        CmsSubject record = new CmsSubject();
        record.setShowStatus(showStatus);
        return subjectMapper.update(record, new QueryWrapper<CmsSubject>().eq("id", ids));
    }
}
