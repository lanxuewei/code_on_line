package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.dao.entity.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * @param status 状态码(状态划分 0:正常 -1:已删除 null:所有)
     * @param keyword 关键字(为空时不参与查询)
     * @return all problem
     */
    List<Problem> selectAll(@Param("status") Byte status, @Param("keyword") String keyword);
    /**
     * 查找总记录数(状态划分 0:正常 -1:已删除 null:所有)
     * @return count all problem
     */
    Integer selectCount(@Param("status") Byte status);
    /**
     * 查询相应难易度对应问题数
     * @return
     */
    List<Map<String, Integer>> selectCountByDifficulty();

    //更新
    int updateByPrimaryKeySelective(Problem record);
    int updateByPrimaryKey(Problem record);
}