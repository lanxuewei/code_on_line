package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.ProblemTag;
import com.lanxuewei.code_on_line.dao.entity.ProblemTagKey;
import com.lanxuewei.code_on_line.utils.CompareUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * create by lanxuewei in 2018/5/12 09:58
 * description: ProblemTagMapper test
 */
public class ProblemTagMapperTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ProblemTagMapper.class);

    /**
     * insert updateByPrimaryKey delete
     */
    @Override
    public void insertUpdateByPrimaryKeyDeleteByPrimaryKeyTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ProblemTagMapper mapper = sqlSession.getMapper(ProblemTagMapper.class);
            ProblemTag problemTag = (ProblemTag) getTestCase();
            //test
            //insert
            mapper.insert(problemTag);
            //selectByPrimaryKey
            ProblemTagKey problemTagKey = getProblemTagKey(problemTag);
            ProblemTag problemTagFromDatabase = mapper.selectByPrimaryKey(problemTagKey);
            Assert.assertTrue(compareTo(problemTag, problemTagFromDatabase));
            //updateByPrimaryKey
            problemTag = (ProblemTag) modifyTestCase(problemTag);
            mapper.updateByPrimaryKey(problemTag);
            problemTagFromDatabase = mapper.selectByPrimaryKey(problemTag);
            Assert.assertTrue(compareTo(problemTag, problemTagFromDatabase));
            //deleteByPrimaryKey
            mapper.deleteByPrimaryKey(problemTagKey);
            problemTagFromDatabase = mapper.selectByPrimaryKey(problemTagKey);
            Assert.assertNull(problemTagFromDatabase);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * insertSelective updateByPrimaryKey
     */
    @Override
    public void insertSelectiveUpdateByPrimaryKeySelectiveTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ProblemTagMapper mapper = sqlSession.getMapper(ProblemTagMapper.class);
            ProblemTag problemTag = (ProblemTag) getTestCase();
            //test
            //insert
            mapper.insertSelective(problemTag);
            ProblemTagKey problemTagKey = getProblemTagKey(problemTag);
            ProblemTag problemTagFromDatabase = mapper.selectByPrimaryKey(problemTagKey);
            Assert.assertTrue(compareTo(problemTag, problemTagFromDatabase));
            //updateByPrimaryKey
            problemTag = (ProblemTag) modifyTestCase(problemTag);
            mapper.updateByPrimaryKeySelective(problemTag);
            problemTagFromDatabase = mapper.selectByPrimaryKey(problemTagKey);
            Assert.assertTrue(compareTo(problemTag, problemTagFromDatabase));
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Override
    public Object getTestCase() {
        ProblemTag problemTag = new ProblemTag();
        problemTag.setProblemId(1L);
        problemTag.setTagId(2L);
        Byte status = 1;
        problemTag.setStatus(status);
        return problemTag;
    }

    /**
     * get ProblemTagKey entity
     * @return
     */
    public ProblemTagKey getProblemTagKey(ProblemTag problemTag) {
        ProblemTagKey problemTagKey = new ProblemTagKey();
        problemTagKey.setProblemId(problemTag.getProblemId());
        problemTagKey.setTagId(problemTag.getTagId());
        return problemTagKey;
    }

    @Override
    public Object modifyTestCase(Object obj) {
        if (obj instanceof ProblemTag) {
            ProblemTag problemTag = (ProblemTag) obj;
            Byte status = -1;
            problemTag.setStatus(status);
            return problemTag;
        }
        return null;
    }

    @Override
    public boolean compareTo(Object obj, Object src) {
        Set<String> propertiesSet = new HashSet<>();
        propertiesSet.add("createTime");  //设置忽略比较属性值
        propertiesSet.add("updateTime");
        return CompareUtil.isPropertiesEquals(obj, src, propertiesSet);  //判断两个对象属性值是否完全一致
    }
}
