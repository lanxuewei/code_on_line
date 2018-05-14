package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    /**
     * 查找所有标签(状态划分 0:正常 -1:已删除 null:所有)
     * @return all tag
     */
    List<Tag> selectAll(@Param("status") Byte status);
    /**
     * 查找总记录数(状态划分 0:正常 -1:已删除 null:所有)
     * @return count all problem
     */
    int selectCount(@Param("status") Byte status);

    //修改
    int updateByPrimaryKeySelective(Tag record);
    int updateByPrimaryKey(Tag record);
}