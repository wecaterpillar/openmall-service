package org.openmall.mall.common.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class PageInfo<T> extends Page<T> implements IPage<T> {

    public PageInfo(){
        super(1,10);
    }
    public PageInfo(long current, long size){
        super(current,size);
    }
    public PageInfo(List<T> list){
        super.setRecords(list);
    }

    public int getPageNum(){
        return (int)super.getCurrent();
    }

    public int getPageSize(){
        return (int)super.getSize();
    }

    public List<T> getList(){
        return super.getRecords();
    }

    @JsonIgnore
    public List<T> getRecords(){
        return super.getRecords();
    }

}
