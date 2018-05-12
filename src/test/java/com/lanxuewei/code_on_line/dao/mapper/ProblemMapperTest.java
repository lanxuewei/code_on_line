package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.utils.CompareUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * create by lanxuewei in 2018/4/21 15:05
 * description: ProblemMapper test
 */
public class ProblemMapperTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ProblemMapper.class);

    /**
     * insert updateByPrimaryKey selectByPrimaryKey deleteByPrimaryKey test
     */
    @Override
    public void insertUpdateByPrimaryKeyDeleteByPrimaryKeyTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ProblemMapper mapper = sqlSession.getMapper(ProblemMapper.class);
            Problem problem = (Problem) getTestCase();
            //test
            //insert
            mapper.insert(problem);
            Problem problemFromDatabase = mapper.selectByPrimaryKey(problem.getId());
            Assert.assertTrue(compareTo(problem, problemFromDatabase));
            //update
            problem = (Problem) modifyTestCase(problem);
            mapper.updateByPrimaryKey(problem);
            problemFromDatabase = mapper.selectByPrimaryKey(problem.getId());
            Assert.assertTrue(compareTo(problem, problemFromDatabase));
            //delete
            mapper.deleteByPrimaryKey(problem.getId());
            problemFromDatabase = mapper.selectByPrimaryKey(problem.getId());
            Assert.assertNull(problemFromDatabase);
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
            ProblemMapper mapper = sqlSession.getMapper(ProblemMapper.class);
            Problem problem = (Problem) getTestCase();
            //test
            //insertSelect
            mapper.insertSelective(problem);
            Problem problemFromDatabase = mapper.selectByPrimaryKey(problem.getId());
            Assert.assertTrue(compareTo(problem, problemFromDatabase));
            //updateSelective
            problem = (Problem) modifyTestCase(problem);
            mapper.updateByPrimaryKeySelective(problem);
            problemFromDatabase = mapper.selectByPrimaryKey(problem.getId());
            Assert.assertTrue(compareTo(problem, problemFromDatabase));
        } finally {
            sqlSession.close();
        }
    }

    /**
     * get test case
     *
     * @return test case
     */
    @Override
    public Object getTestCase() {
        Problem problem = new Problem();
        problem.setName("test");
        problem.setAuthor("lanxuewei");
        problem.setDes("the problem is test");
        problem.setDesHtml("the problem is test html");
        Byte difficulty = 1;
        problem.setDifficulty(difficulty);
        problem.setFail(0);
        Byte status = 1;
        problem.setStatus(status);
        problem.setSubmit(1);
        problem.setSuccess(1);
        return problem;
    }

    /**
     * modify test case
     *
     * @param obj
     * @return problem
     */
    @Override
    public Object modifyTestCase(Object obj) {
        Problem problem = (Problem) obj;
        problem.setName("test1");
        problem.setSuccess(5);
        problem.setFail(2);
        problem.setSubmit(7);
        Byte status = 0;
        problem.setStatus(status);
        return problem;
    }

    /**
     * compare two obj
     *
     * @param obj obj1
     * @param src obj2
     * @return true or false
     */
    @Override
    public boolean compareTo(Object obj, Object src) {
        Set<String> properties = new HashSet<>();
        properties.add("createTime");
        properties.add("updateTime");
        return CompareUtil.isPropertiesEquals(obj, src, properties);
    }
}
