package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Problem;
import com.lanxuewei.code_on_line.dto.ProblemCountDto;
import com.lanxuewei.code_on_line.dto.ProblemDto;
import com.lanxuewei.code_on_line.utils.CompareUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
     * count selectAll
     */
    @Test
    @Transactional
    public void countAndSelectAllTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ProblemMapper mapper = sqlSession.getMapper(ProblemMapper.class);
            // test
            // all problems
            Byte status = null;
            List<ProblemDto> allProblems = mapper.selectAll(status, null, null,null, null);
            int allCount = mapper.selectCount(status);
            Assert.assertEquals(allProblems.size(), allCount);
            // all normal problems
            status = 0;
            List<ProblemDto> allNormalProblems = mapper.selectAll(status, null, null,null, null);
            int allNormalCount = mapper.selectCount(status);
            Assert.assertEquals(allNormalProblems.size(), allNormalCount);
            // all deleted problems
            status = -1;
            List<ProblemDto> allDeletedProblems = mapper.selectAll(status, null, null,null, null);
            int allDeletedCount = mapper.selectCount(status);
            Assert.assertEquals(allDeletedProblems.size(), allDeletedCount);
            // all problem by keyword
            status = 0;
            String keyword = "te";
            List<ProblemDto> keyWordProblems = mapper.selectAll(status, keyword, null,null, null);
            for (ProblemDto item : keyWordProblems) {
                logger.info("name = {}", item.getName());
            }
            // all problem where problemId in {14,15}
            List<Long> resolvedProblemIds = new ArrayList<>();
            resolvedProblemIds.add(14L);
            resolvedProblemIds.add(15L);
            Byte resolve = 0;  // 表示id在集合中
            List<ProblemDto> allResolvedProblemIds = mapper.selectAll(status, null, null, resolve, resolvedProblemIds);
            logger.info("resolvedProblemIds = {}", allResolvedProblemIds);
            // all problem where problemId not in {14,15}
            resolve = -1;      // 表示id不在集合中
            List<ProblemDto> allNotResolvedProblemIds = mapper.selectAll(status, null, null, resolve, resolvedProblemIds);
            logger.info("allNotResolvedProblemIds = {}", allNotResolvedProblemIds);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 查询相应难易度对应问题数 test
     */
    @Test
    public void selectCountByDifficultyTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ProblemMapper mapper = sqlSession.getMapper(ProblemMapper.class);
            //test
            List<Map<String, Integer>> resultList = mapper.selectCountByDifficulty();
            //logger.info("resultList = {}", resultList);
            Map<Integer, Integer> map = transferCountMap(resultList);
            logger.info("map = {}", map);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * 将根据难度分组统计数据的list转化为map
     *   结果 map 为 {0->2, 1->3, 2->6} key为难度系数,即 0 1 2, value为问题数
     * @param list
     * @return
     */
    private Map<Integer, Integer> transferCountMap(List<Map<String, Integer>> list) {
        if (list != null) {
            Map<Integer, Integer> resultMap = new HashMap<>();
            for (Map<String, Integer> map : list) {
                logger.info("map = {}", map);
                //Integer count = map.get("difficulty");
                resultMap.put(map.get("difficulty"), map.get("count"));
            }
            return resultMap;
        }
        return null;
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
