package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 对应 Tag 的CURD操作
 */
@Mapper
public interface TagMapper {

    //添加
    int insert(Tag record);
    int insertSelective(Tag record);

    //删除
    int deleteByPrimaryKey(Long id);

    //查找
    Tag selectByPrimaryKey(Long id);

    //修改
    int updateByPrimaryKeySelective(Tag record);
    int updateByPrimaryKey(Tag record);
}