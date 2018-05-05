package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.dao.entity.Tag;
import com.lanxuewei.code_on_line.utils.CompareUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * create by lanxuewei in 2018/4/21 15:07
 * description: TagMapper test
 */
public class TagMapperTest extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(TagMapper.class);

    /**
     * insert updateByPrimaryKey selectByPrimaryKey deleteByPrimaryKey test
     */
    @Override
    public void insertUpdateByPrimaryKeyDeleteSelectByPrimaryKeyTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            TagMapper mapper = sqlSession.getMapper(TagMapper.class);
            Tag tag = (Tag) getTestCase();
            //test
            //insert selectByPrimaryKey
            Byte status = 2;  //insert 方法不允许status为空
            tag.setStatus(status);
            mapper.insert(tag);
            Tag tagFromDatabase = mapper.selectByPrimaryKey(tag.getId());
            Assert.assertTrue(compareTo(tag, tagFromDatabase));
            //updateByPrimaryKey
            modifyTestCase(tag);  //modify test case
            mapper.updateByPrimaryKey(tag);
            tagFromDatabase = mapper.selectByPrimaryKey(tag.getId());
            Assert.assertTrue(compareTo(tag, tagFromDatabase));
            //deleteByPrimaryKey
            mapper.deleteByPrimaryKey(tag.getId());
            tagFromDatabase = mapper.selectByPrimaryKey(tag.getId());
            Assert.assertNull(tagFromDatabase);
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
            TagMapper mapper = sqlSession.getMapper(TagMapper.class);
            Tag tag = (Tag) getTestCase();
            //test
            //insertSelective
            mapper.insertSelective(tag);
            Tag tagFromDatabase = mapper.selectByPrimaryKey(tag.getId());
            Assert.assertTrue(compareTo(tag, tagFromDatabase));
            //updateByPrimaryKeySelective
            modifyTestCase(tag);
            mapper.updateByPrimaryKeySelective(tag);
            tagFromDatabase = mapper.selectByPrimaryKey(tag.getId());
            Assert.assertTrue(compareTo(tag, tagFromDatabase));
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
            TagMapper mapper = sqlSession.getMapper(TagMapper.class);
            Tag tag = (Tag) getTestCase();
            //test insert
            mapper.insert(tag);
            tag.setName("test1");
            mapper.insert(tag);
            tag.setName("test2");
            mapper.insert(tag);
            //selectAll
            List<Tag> tags = mapper.selectAll();
            logger.debug("tags = {}", tags);
            //selectCout
            int count = mapper.selectCount();
            logger.debug("count = {}", count);
            Assert.assertEquals(tags.size(), count);
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    /**
     * get test case
     * @return tag
     */
    @Override
    public Object getTestCase() {
        Tag tag = new Tag();
        tag.setName("database");
        Byte status = 0;  //默认为0
        tag.setStatus(status);
        return tag;
    }

    /**
     * modify test case
     * @param obj
     * @return tag
     */
    @Override
    public Object modifyTestCase(Object obj) {
        Tag tag = (Tag) obj;
        tag.setName("array");
        return tag;
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
        propertiesSet.add("createTime");  //设置忽略比较属性值
        propertiesSet.add("updateTime");
        return CompareUtil.isPropertiesEquals(obj, src, propertiesSet);  //判断两个tag属性值是否完全一致
    }
}
