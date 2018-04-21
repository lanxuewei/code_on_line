package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Case;
import org.apache.ibatis.annotations.Mapper;

/**
 * 对应 Problem 的CURD操作
 */
@Mapper
public interface CaseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Case record);

    int insertSelective(Case record);

    Case selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Case record);

    int updateByPrimaryKey(Case record);
}