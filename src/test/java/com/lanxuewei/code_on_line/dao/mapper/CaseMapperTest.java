package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.utils.CompareUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create by lanxuewei in 2018/4/21 16:19
 * description: CaseMapper test
 */
public class CaseMapperTest extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(CaseMapperTest.class);

    /**
     * insert updateByPrimaryKey selectByPrimaryKey deleteByPrimaryKey test
     */
    @Override
    public void insertUpdateByPrimaryKeyDeleteByPrimaryKeyTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
            Case problemCase = (Case) getTestCase();
            //test
            //insert
            mapper.insert(problemCase);
            Case problemCaseFromDatabase = mapper.selectByPrimaryKey(problemCase.getId());
            Assert.assertTrue(compareTo(problemCase, problemCaseFromDatabase));
            //updateByPrimaryKey
            problemCase = (Case) modifyTestCase(problemCase);
            mapper.updateByPrimaryKey(problemCase);
            problemCaseFromDatabase = mapper.selectByPrimaryKey(problemCase.getId());
            Assert.assertTrue(compareTo(problemCase, problemCaseFromDatabase));
            //deleteByPrimaryKey
            mapper.deleteByPrimaryKey(problemCase.getId());
            problemCaseFromDatabase = mapper.selectByPrimaryKey(problemCase.getId());
            Assert.assertNull(problemCaseFromDatabase);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 返回更新数条数 test
     */
    @Test
    public void addTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
            Case problemCase = (Case) getTestCase();
            //insert
            int result1 = mapper.insert(problemCase);
            logger.debug("result1 = " + result1);
            //update
            problemCase.setId(problemCase.getId() + 1);
            int result2 = mapper.updateByPrimaryKey(problemCase);
            logger.debug("result2 = " + result2);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * insertSelective updateByPrimaryKeySelective test
     */
    @Override
    public void insertSelectiveUpdateByPrimaryKeySelectiveTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
            Case problemCase = (Case) getTestCase();
            //test
            //insert
            problemCase.setStatus(null);
            mapper.insertSelective(problemCase);
            Byte status = 0;  //为空时数据库默认值为0
            problemCase.setStatus(status);
            Case problemCaseFromDatabase = mapper.selectByPrimaryKey(problemCase.getId());
            Assert.assertTrue(compareTo(problemCase, problemCaseFromDatabase));
            //updateByPrimaryKeySelective
            problemCase = (Case) modifyTestCase(problemCase);
            mapper.updateByPrimaryKeySelective(problemCase);
            problemCaseFromDatabase = mapper.selectByPrimaryKey(problemCase.getId());
            /**
             * 比较非空更新属性值
             */
            Assert.assertEquals(problemCase.getId(), problemCaseFromDatabase.getId());
            Assert.assertEquals(problemCase.getInput(), problemCaseFromDatabase.getInput());
            Assert.assertEquals(problemCase.getOutput(), problemCaseFromDatabase.getOutput());
        } finally {
            sqlSession.close();
        }
    }

    /**
     * selectAll selectCount
     */
    @Test
    @Transactional
    public void selectCountAndSelectAllTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
            //select all cases
            Byte status = null;
            List<Case> cases = mapper.selectAll(status);
            logger.info("cases = {}", cases);
            int count = mapper.selectCount(status);
            logger.info("count = {}", count);
            Assert.assertEquals(cases.size(), count);
            //select all Normal cases
            status = 0;
            List<Case> allNormalCases = mapper.selectAll(status);
            logger.info("allNormalCase = {}", allNormalCases);
            int allNormalCount = mapper.selectCount(status);
            logger.info("allNormalCount = {}", allNormalCount);
            Assert.assertEquals(allNormalCases.size(), allNormalCount);
            //select all deleted cases
            status = -1;
            List<Case> allDeletedCases = mapper.selectAll(status);
            logger.info("allDeletedCase = {}", allDeletedCases);
            int allDeleteCount = mapper.selectCount(status);
            logger.info("allDeletedCount = {}", allDeleteCount);
            Assert.assertEquals(allDeletedCases.size(), allDeleteCount);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * selectAllByProblemId test
     */
    @Test
    public void selectAllByProblemIdTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            CaseMapper mapper = sqlSession.getMapper(CaseMapper.class);
            // test
            Long problemId = 14L;
            Byte status = 0;
            List<Case> cases = mapper.selectAllByProblemId(problemId, status);
            logger.info("cases = {}", cases);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * get test case
     * @return case
     */
    @Override
    public Object getTestCase() {
        Case problemCase = new Case();
        problemCase.setProblemId(1L);
        problemCase.setInput("1 2");
        problemCase.setOutput("3");
        Byte status = 1;
        problemCase.setStatus(status);
        return problemCase;
    }

    /**
     * modify test case
     * @param obj
     * @return case
     */
    @Override
    public Object modifyTestCase(Object obj) {
        Case problemCase = (Case) obj;
        problemCase.setProblemId(2L);
        problemCase.setInput("1 4");
        problemCase.setOutput("5");
        Byte status = 3;
        problemCase.setStatus(status);
        return problemCase;
    }

    /**
     * compare two obj
     * @param obj obj1
     * @param src obj2
     * @return true or false
     */
    @Override
    public boolean compareTo(Object obj, Object src) {
        Set<String> propertiesSet = new HashSet<>();
        propertiesSet.add("createTime");
        propertiesSet.add("updateTime");
        return CompareUtil.isPropertiesEquals(obj, src, propertiesSet);
    }
}
