package com.lanxuewei.code_on_line.dao.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;

/**
 * Created by lanxuewei in 11:15 2018/3/9
 * 单元测试类父类模板
 */

public class BaseTest {

    public static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public final static void setUp() throws Exception {
        // create an SqlSessionFactory
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();
    }

    /**
     * insert updateByPrimaryKey selectByPrimaryKey deleteByPrimaryKey test
     */
    @Test
    @Transactional
    public void insertUpdateByPrimaryKeyDeleteByPrimaryKeyTest(){}

    /**
     * insertSelective updateByPrimaryKeySelective selectByPrimaryKey test
     */
    @Test
    @Transactional
    public void insertSelectiveUpdateByPrimaryKeySelectiveTest(){}

    /**
     * get testCase
     */
    public Object getTestCase(){
        return null;
    }

    /**
     * modifyTestCase
     */
    public Object modifyTestCase(Object obj){
        return obj;
    }

    /**
     * 对象属性对比
     */
    public boolean compareTo(Object obj, Object src){
        return false;
    }

    /**
     * print compare message
     */
    public final void compareMessagePrint(Object obj, Object src){
        System.out.println("obj = " + obj.toString());
        System.out.println("src = " + src.toString());
        System.out.println("compareTo: " + compareTo(obj,src));
    }
}
