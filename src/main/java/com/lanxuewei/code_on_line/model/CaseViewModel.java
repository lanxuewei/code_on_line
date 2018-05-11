package com.lanxuewei.code_on_line.model;

/**
 * create by lanxuewei in 2018/5/10 14:54
 * description: 测试用例视图模型
 */
public class CaseViewModel {

    /**
     * 输入
     */
    private String input;

    /**
     * 输出
     */
    private String output;

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

    @Override
    public String toString() {
        return "CaseViewModel{" +
                "input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
