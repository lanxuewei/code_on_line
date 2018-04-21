package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.utils.CompareUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
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
    public void insertUpdateByPrimaryKeyDeleteSelectByPrimaryKeyTest() {
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
     * get test case
     * @return case
     */
    @Override
    public Object getTestCase() {
        Case problemCase = new Case();
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
