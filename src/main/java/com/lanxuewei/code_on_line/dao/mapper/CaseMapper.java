package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Case;
import org.apache.ibatis.annotations.Mapper;

/**
 * 对应 Problem 的CURD操作
 */
@Mapper
public interface CaseMapper {

    //新增
    int insert(Case record);
    int insertSelective(Case record);

    //删除
    int deleteByPrimaryKey(Long id);

    //查找
    Case selectByPrimaryKey(Long id);

    //更新
    int updateByPrimaryKeySelective(Case record);
    int updateByPrimaryKey(Case record);
}