package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.ProblemThroughRate;
import com.lanxuewei.code_on_line.dao.entity.UserProblem;
import com.lanxuewei.code_on_line.dao.entity.UserProblemKey;
import com.lanxuewei.code_on_line.utils.CompareUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;

/**
 * create by lanxuewei in 2018/5/13 16:13
 * description: UserProblemMapper test todo
 */
public class UserProblemMapperTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(UserProblemMapper.class);

    /**
     * insert updateByPrimaryKey deleteByPrimaryKey test
     */
    @Override
    public void insertUpdateByPrimaryKeyDeleteByPrimaryKeyTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserProblemMapper mapper = sqlSession.getMapper(UserProblemMapper.class);
            UserProblem userProblem = (UserProblem) getTestCase();
            // test
            // insert
            mapper.insert(userProblem);
            UserProblemKey userProblemKey = getUserProblemKey(userProblem);
            UserProblem userProblemFromDatabase = mapper.selectByPrimaryKey(userProblemKey);
            Assert.assertTrue(compareTo(userProblem, userProblemFromDatabase));
            // updateByPrimaryKey
            userProblem = (UserProblem) modifyTestCase(userProblem);
            mapper.updateByPrimaryKey(userProblem);
            userProblemFromDatabase = mapper.selectByPrimaryKey(userProblemKey);
            Assert.assertTrue(compareTo(userProblem, userProblemFromDatabase));
            //deleteByPrimaryKey
            mapper.deleteByPrimaryKey(userProblemKey);
            userProblemFromDatabase = mapper.selectByPrimaryKey(userProblemKey);
            Assert.assertNull(userProblemFromDatabase);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * insertSelective updateByPrimaryKeySelective test
     */
    @Override
    public void insertSelectiveUpdateByPrimaryKeySelectiveTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserProblemMapper mapper = sqlSession.getMapper(UserProblemMapper.class);
            UserProblem userProblem = (UserProblem) getTestCase();
            //userProblem.setFail(null);
            userProblem.setStatus(null);
            // test
            // insertSelective
            mapper.insertSelective(userProblem);
            UserProblemKey userProblemKey = getUserProblemKey(userProblem);
            UserProblem userProblemFromDatabase = mapper.selectByPrimaryKey(userProblemKey);
            Byte status = 0;
            userProblem.setStatus(status);
            Assert.assertTrue(compareTo(userProblem, userProblemFromDatabase));
            // updateByPrimaryKeySelective
            userProblem = (UserProblem) modifyTestCase(userProblem);
            mapper.updateByPrimaryKeySelective(userProblem);
            userProblemFromDatabase = mapper.selectByPrimaryKey(userProblemKey);
            Assert.assertTrue(compareTo(userProblem, userProblemFromDatabase));
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * countAllResolved test
     */
    @Test
    public void countAllResolvedTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserProblemMapper mapper = sqlSession.getMapper(UserProblemMapper.class);
            UserProblem userProblem = (UserProblem) getTestCase();
            Byte status = 0;
            userProblem.setStatus(status);
            mapper.insertSelective(userProblem);
            userProblem.setProblemId(100000L);
            mapper.insertSelective(userProblem);
            userProblem.setProblemId(300000L);
            mapper.insertSelective(userProblem);
            Integer count = mapper.countAllResolved(userProblem.getUserId());
            logger.info("count = {}", count);
            Integer insertCount = 3;
            Assert.assertEquals(insertCount, count);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * selectProblemIdByResolved test
     */
    @Test
    public void selectProblemIdByResolvedTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserProblemMapper mapper = sqlSession.getMapper(UserProblemMapper.class);
            // test
            Long userId = 3L;
            List<Long> resolvedProblmeIds = mapper.selectProblemIdByResolved(userId);
            logger.info("resolvedProblemIds = {}", resolvedProblmeIds);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * selectProblemThroughRate test
     */
    @Test
    public void selectProblemThroughRateTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserProblemMapper userProblemMapper = sqlSession.getMapper(UserProblemMapper.class);
            // test
            List<Long> problemIds = new ArrayList<>();
            List<ProblemThroughRate> list = userProblemMapper.selectProblemThroughRate(problemIds);
            logger.info("list = {}", list);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Override
    public Object getTestCase() {
        UserProblem userProblem = new UserProblem();
        userProblem.setUserId(100L);
        userProblem.setProblemId(100L);
        userProblem.setLastSubmit("lastSubmit");
        userProblem.setSubmit(1);
        userProblem.setSuccess(1);
        userProblem.setFail(0);
        Byte status = 1;
        userProblem.setStatus(status);
        return userProblem;
    }

    @Override
    public Object modifyTestCase(Object obj) {
        UserProblem userProblem = (UserProblem) obj;
        userProblem.setSubmit(2);
        userProblem.setFail(1);
        Byte status = -1;
        userProblem.setStatus(status);
        return userProblem;
    }

    /**
     * get UserProblemKey
     * @param userProblem
     * @return
     */
    private UserProblemKey getUserProblemKey(UserProblem userProblem) {
        UserProblemKey userProblemKey = new UserProblemKey();
        if (userProblem != null) {
            userProblemKey.setProblemId(userProblem.getProblemId());
            userProblemKey.setUserId(userProblem.getUserId());
        }
        return userProblemKey;
    }

    @Override
    public boolean compareTo(Object obj, Object src) {
        if (obj instanceof UserProblem && src instanceof UserProblem) {
            Set<String> propertiesSet = new HashSet<>();
            propertiesSet.add("createTime");  //设置忽略比较属性值
            propertiesSet.add("updateTime");
            return CompareUtil.isPropertiesEquals(obj, src, propertiesSet);  //判断两个tag属性值是否完全一致
        }
        return false;
    }
}
