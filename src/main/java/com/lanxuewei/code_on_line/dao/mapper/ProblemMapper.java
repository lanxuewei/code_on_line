package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.dao.entity.Problem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 对应 Problem 的CURD操作
 */
@Mapper
public interface ProblemMapper {

    //新增
    int insert(Problem record);
    int insertSelective(Problem record);

    //删除
    int deleteByPrimaryKey(Long id);

    //查找
    Problem selectByPrimaryKey(Long id);
    /**
     * 查找所有问题
     * @return all problem
     */
    List<Problem> selectAll();
    /**
     * 查找总记录数
     * @return count all problem
     */
    int selectCount();

    //更新
    int updateByPrimaryKeySelective(Problem record);
    int updateByPrimaryKey(Problem record);
}