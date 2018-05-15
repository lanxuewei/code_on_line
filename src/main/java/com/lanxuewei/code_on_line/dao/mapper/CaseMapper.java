package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Case;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    /**
     * 查找所有用例(状态划分 0:正常 -1:已删除 null:所有)
     * @return all case
     */
    List<Case> selectAll(@Param("status") Byte status);
    /**
     * 查找总记录数(状态划分 0:正常 -1:已删除 null:所有)
     * @return count all case
     */
    int selectCount(@Param("status") Byte status);

    //更新
    int updateByPrimaryKeySelective(Case record);
    int updateByPrimaryKey(Case record);
}