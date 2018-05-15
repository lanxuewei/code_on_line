package com.lanxuewei.code_on_line.service.imp;

import com.github.pagehelper.PageHelper;
import com.lanxuewei.code_on_line.constant.ServiceConstant;
import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.dao.mapper.CaseMapper;
import com.lanxuewei.code_on_line.model.Page;
import com.lanxuewei.code_on_line.service.CaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by lanxuewei in 2018/4/21 19:10
 * description: Case 相关业务实现
 */
@Service
public class CaseServiceImp implements CaseService{

    private static final Logger logger = LoggerFactory.getLogger(CaseServiceImp.class);

    @Autowired
    private CaseMapper caseMapper;

    @Override
    public boolean addCase(Case problemCase) {
        return caseMapper.insert(problemCase) != 0;
    }

    @Override
    public boolean deleteCaseById(Long id) {
        return caseMapper.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public Case findCaseById(Long id) {
        return caseMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据状态码查找所有测试用例(状态划分 0:正常 -1:已删除 null:所有)
     * @return
     */
    @Override
    public List<Case> selectAll(Byte status) {
        return caseMapper.selectAll(status);
    }

    /**
     * 分页查找
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return
     */
    @Override
    public Page<Case> selectByPage(Integer pageNum, Integer pageSize, Byte status) {
        PageHelper.startPage(pageNum, pageSize);  //分页查询
        List<Case> cases = caseMapper.selectAll(status);
        return new Page<>(cases);
    }

    /**
     * 根据状态码统计测试用例数量
     * @return
     */
    @Override
    public int selectCount(Byte status) {
        return caseMapper.selectCount(status);
    }

    @Override
    public boolean modifyCaseById(Case problemCase) {
        return caseMapper.updateByPrimaryKey(problemCase) != 0;
    }
}
