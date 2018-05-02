package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.Tag;

/**
 * create by lanxuewei in 2018/4/21 19:15
 * description: Tag 类的CURD操作
 */
public interface TagService {

    //新增
    /**
     * add tag
     * @param tag
     * @return true or false
     */
    boolean addTag(Tag tag);

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

    //修改
    /**
     * modify tag by id
     * @param tag
     * @return true or false
     */
    boolean modifyTagById(Tag tag);
}
