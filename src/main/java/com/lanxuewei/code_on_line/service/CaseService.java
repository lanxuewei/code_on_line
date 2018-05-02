package com.lanxuewei.code_on_line.service;

import com.github.pagehelper.PageInfo;
import com.lanxuewei.code_on_line.dao.entity.Case;

import java.util.List;

/**
 * create by lanxuewei in 2018/4/21 19:04
 * description: Case 类的CURD操作
 */
public interface CaseService {

    //新增
    /**
     * add case
     * @param problemCase
     * @return true or false
     */
    boolean addCase(Case problemCase);

    //删除
    /**
     * delete case by id
     * @param id
     * @return true or false
     */
    boolean deleteCaseById(Long id);

    //查询
    /**
     * find case by id
     * @param id
     * @return true or false
     */
    Case findCaseById(Long id);
    /**
     * 查找所有用例
     * @return all case
     */
    PageInfo<Case> selectAll();
    /**
     * 查找总记录数
     * @return count all case
     */
    int selectCount();


    //修改
    /**
     * modify case by id
     * @param problemCase
     * @return true or false
     */
    boolean modifyCaseById(Case problemCase);
}
