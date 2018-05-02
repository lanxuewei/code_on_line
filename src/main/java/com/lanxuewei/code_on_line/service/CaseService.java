package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.model.Page;

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
    List<Case> selectAll();
    /**
     * 查找总记录数
     * @return count all case
     */
    int selectCount();
    /**
     * 分页查找case
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页后数据集以及分页信息
     */
    Page<Case> selectByPage(Integer pageNum, Integer pageSize);

    //修改
    /**
     * modify case by id
     * @param problemCase
     * @return true or false
     */
    boolean modifyCaseById(Case problemCase);
}
