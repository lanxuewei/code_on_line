package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.ProblemTag;
import com.lanxuewei.code_on_line.dao.entity.ProblemTagKey;

/**
 * problemTag mapper todo test
 */
public interface ProblemTagMapper {

    //新增
    int insert(ProblemTag record);

    int insertSelective(ProblemTag record);

    //删除
    int deleteByPrimaryKey(ProblemTagKey key);

    //查找
    ProblemTag selectByPrimaryKey(ProblemTagKey key);

    //更新
    int updateByPrimaryKeySelective(ProblemTag record);

    int updateByPrimaryKey(ProblemTag record);
}