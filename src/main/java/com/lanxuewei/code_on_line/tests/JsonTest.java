package com.lanxuewei.code_on_line.tests;

import com.alibaba.fastjson.JSON;
import com.lanxuewei.code_on_line.dao.entity.Case;
import com.lanxuewei.code_on_line.dao.entity.Tag;
import com.lanxuewei.code_on_line.model.CaseViewModel;
import com.lanxuewei.code_on_line.model.ProblemViewModel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * create by lanxuewei in 2018/5/10 15:34
 * description: Json 相关
 */
public class JsonTest {

    private static final Logger logger = LoggerFactory.getLogger(JsonTest.class);

    private static String TestCaseJsonStr = "{\"id\":1,\"input\":\"1 2\",\"output\":\"3\",\"status\":0}";

    /**
     * 将java bean转化为 json 字符串
     */
    @Test
    public void objToJsonStr() {
        Case testCase = new Case();
        testCase.setId(1L);
        testCase.setStatus((byte) 0);
        testCase.setInput("1 2");
        testCase.setOutput("3");
        String testCaseJosn = JSON.toJSONString(testCase);
        logger.info("testCaseJson = {}", testCaseJosn);
    }

    @Test
    public void jsonStrToObj() {
        Case testCase = JSON.parseObject(TestCaseJsonStr, Case.class);
        logger.info("testCase = {}", testCase);
    }

    @Test
    public void getProblemViewModelJson() {
        ProblemViewModel problemViewModel = new ProblemViewModel();
        problemViewModel.setName("A+B");
        problemViewModel.setAuthor("lanxuewei");
        Byte difficulty = 0;
        problemViewModel.setDifficulty(difficulty);
        problemViewModel.setDes("des");
        problemViewModel.setDesHtml("des by html");
        //tags
        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag();
        tag1.setId(55L);
        tags.add(tag1);
        Tag tag2 = new Tag();
        tag2.setId(56L);
        tags.add(tag2);
        problemViewModel.setTags(tags);
        //test cases
        List<CaseViewModel> caseViewModels = new ArrayList<>();
        CaseViewModel caseViewModel1 = new CaseViewModel();
        caseViewModel1.setInput("1 2");
        caseViewModel1.setOutput("3");
        caseViewModels.add(caseViewModel1);
        CaseViewModel caseViewModel2 = new CaseViewModel();
        caseViewModel2.setInput("2 3");
        caseViewModel2.setOutput("5");
        caseViewModels.add(caseViewModel2);
        problemViewModel.setCaseViewModels(caseViewModels);
        //to json
        String viewParamJson = JSON.toJSONString(problemViewModel);
        logger.info("viewParamJson = {}", viewParamJson);
    }
}
