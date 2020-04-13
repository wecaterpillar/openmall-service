package org.openmall.mall.common.api;

//import com.github.pagehelper.PageInfo;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 分页数据封装类
 */
public class CommonPage<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    public static <T> CommonPage<T> restPage(PageInfo<T> pageInfo) {
        return restPage(pageInfo, null);
    }

    /**
     * 将PageHelper分页后的list转为分页信息
     *
     * @deprecated replace by restPage(Page<T> pageInfo, List<T> list)
     */
//    public static <T> CommonPage<T> restPage(List<T> list) {
//        return restPage(null, list);
//    }

    /**
     * 转换PageInfo
     */
    public static <T> CommonPage<T> restPage(PageInfo<T> pageInfo, List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        if (pageInfo == null) {
            pageInfo = new PageInfo<>();
        }
        if (list != null && !list.isEmpty()) {
            result.setList(list);
            pageInfo.setRecords(list);
        } else {
            result.setList(pageInfo.getList());
        }

        if (!pageInfo.getList().isEmpty() &&
                pageInfo.getTotal() == 0) {
            try {
                com.github.pagehelper.Page<Object> ghPage = com.github.pagehelper.PageHelper.getLocalPage();
                if (ghPage != null) {
                    pageInfo.setCurrent(ghPage.getPageNum());
                    pageInfo.setSize(ghPage.getPageSize());
                    pageInfo.setTotal(ghPage.getTotal());
                    pageInfo.setPages(ghPage.getPages());
                }
            } catch (Exception e) {
            }
        }
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotalPage((int) pageInfo.getPages());
        result.setTotal(pageInfo.getTotal());
        if (result.getTotal() == 0) {
            if(pageInfo.getRecords().size()<pageInfo.getPageSize()){
                result.setTotalPage(pageInfo.getPageNum());
            }else {
                result.setTotalPage(pageInfo.getPageNum()+1);
            }
            result.setTotal((long)pageInfo.getPageSize()*result.getTotalPage());
        }
        return result;
    }

    /**
     * 将SpringData分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(pageInfo.getTotalPages());
        result.setPageNum(pageInfo.getNumber());
        result.setPageSize(pageInfo.getSize());
        result.setTotal(pageInfo.getTotalElements());
        result.setList(pageInfo.getContent());
        return result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
