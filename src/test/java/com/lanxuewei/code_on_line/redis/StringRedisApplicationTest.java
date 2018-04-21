package com.lanxuewei.code_on_line.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * create by lanxuewei in 2018/4/14 15:33
 * description: redis 整合测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = com.lanxuewei.code_on_line.CodeOnLineApplication.class)
public class StringRedisApplicationTest {

    private static final Logger logger = LoggerFactory.getLogger(StringRedisApplicationTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void redisTest() {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("myKey", "spring boot!");
        String testKey = stringStringValueOperations.get("testKey");
        logger.info("mykey ------> {}", testKey);
    }
}
