package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.Case;

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
    public boolean addCase(Case problemCase);

    //删除
    /**
     * delete case by id
     * @param id
     * @return true or false
     */
    public boolean deleteCaseById(Long id);

    //查询
    /**
     * find case by id
     * @param id
     * @return true or false
     */
    public Case findCaseById(Long id);


    //修改
    /**
     * modify case by id
     * @param problemCase
     * @return true or false
     */
    public boolean modifyCaseById(Case problemCase);
}
