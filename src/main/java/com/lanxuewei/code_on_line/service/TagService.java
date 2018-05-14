package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.dao.entity.Tag;

import java.util.List;

/**
 * create by lanxuewei in 2018/4/21 19:15
 * description: Tag 类的CURD操作
 */
public interface TagService {

    //新增
    /**
     * add tag
     * @param tagName
     * @return true or false
     */
    boolean addTag(String tagName);

    //删除
    /**
     * delete tag by id
     * @param id
     * @return true or false
     */
    boolean deleteTagById(Long id);

    //查询
    /**
     * find tag by id
     * @param id
     * @return tag
     */
    Tag findTagById(Long id);
    /**
     * 查找所有标签(状态划分 0:正常 -1:已删除 null:所有)
     * @return all tags
     */
    List<Tag> selectAll(Byte status);
    /**
     * 查找总记录数(状态划分 0:正常 -1:已删除 null:所有)
     * @return count all tags
     */
    int selectCount(Byte status);

    //修改
    /**
     * modify tag by id
     * @param tag
     * @return true or false
     */
    boolean modifyTagById(Tag tag);
}
