package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.common.DaoConstants;
import com.lanxuewei.code_on_line.dao.entity.User;
import com.lanxuewei.code_on_line.utils.CompareUtil;
import com.lanxuewei.code_on_line.utils.Md5Util;
import com.lanxuewei.code_on_line.utils.StringUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * create by lanxuewei in 2018/3/31
 * UserMapper test
 */
public class UserMapperTest extends BaseTest{
    private static Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

    @Test
    @Transactional
    public void insertSelectiveSelectByPrimaryKeyTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User userCase = (User) getTestCase();
            userMapper.insertSelective(userCase);  //insert
            logger.info("userId = {}", userCase.getId());
            User userFromDatabase = userMapper.selectByPrimaryKey(userCase.getId());  //select
            logger.info("user = {}", userFromDatabase);
            Assert.assertTrue(CompareUtil.isPropertiesEquals(userCase, userFromDatabase));
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    @Transactional
    public void selectByUserNameTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = (User) getTestCase();
            userMapper.insertSelective(user);  //insert
            User userFromDatabase = userMapper.selectByUserName(user.getUserName());
            Assert.assertTrue(CompareUtil.isPropertiesEquals(user, userFromDatabase));
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Override
    public Object getTestCase() {
        User user = new User();
        user.setUserName(StringUtil.createStringByRandom(5));
        user.setPassword(Md5Util.getMd5(StringUtil.createStringByRandom(8)));
        user.setDes("这个人很懒，什么都没说！");
        user.setImg("D:/img/img1");
        user.setRealName("lanxuewei");
        user.setSex(DaoConstants.Male);
        user.setStatus(DaoConstants.Student);
        return user;
    }

    @Override
    public Object modifyTestCase(Object obj) {
        return super.modifyTestCase(obj);
    }

    @Override
    public boolean compareTo(Object obj, Object src) {
        return super.compareTo(obj, src);
    }
}
