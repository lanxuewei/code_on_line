package com.lanxuewei.code_on_line.dto;

import com.lanxuewei.code_on_line.dao.entity.Case;

/**
 * create by lanxuewei in 2018/5/18 21:11
 * description: 用于返回未必通过的测试用例
 */
public class CaseDto {

    /**
     * 输入
     */
    private String input;

    /**
     * 输出
     */
    private String output;

    public CaseDto(Case problemCase) {
        this.input = problemCase.getInput();
        this.output = problemCase.getOutput();
    }

    public CaseDto(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
