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

    @Override
    public List<Case> selectAll() {
        return caseMapper.selectAll();
    }

    @Override
    public Page<Case> selectByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);  //分页查询
        List<Case> cases = caseMapper.selectAll();
        return new Page<>(cases);
    }

    @Override
    public int selectCount() {
        return caseMapper.selectCount();
    }

    @Override
    public boolean modifyCaseById(Case problemCase) {
        return caseMapper.updateByPrimaryKey(problemCase) != 0;
    }
}
