package com.lanxuewei.code_on_line.model;

import com.lanxuewei.code_on_line.dao.entity.Tag;

import java.util.List;

/**
 * create by lanxuewei in 2018/5/10 14:49
 * description: problem相关界面模型,用于接收解析前端problem模型
 */
public class ProblemViewModel {

    /**
     * 问题名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 难易度
     */
    private Byte difficulty;

    /**
     * 描述
     */
    private String des;

    /**
     * 描述 html格式
     */
    private String desHtml;

    /**
     * 测试用例集
     */
    private List<CaseViewModel> caseViewModels;

    /**
     * 标签集
     */
    private List<Tag> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Byte getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Byte difficulty) {
        this.difficulty = difficulty;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDesHtml() {
        return desHtml;
    }

    public void setDesHtml(String desHtml) {
        this.desHtml = desHtml;
    }

    public List<CaseViewModel> getCaseViewModels() {
        return caseViewModels;
    }

    public void setCaseViewModels(List<CaseViewModel> caseViewModels) {
        this.caseViewModels = caseViewModels;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ProblemViewModel{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", difficulty=" + difficulty +
                ", des='" + des + '\'' +
                ", desHtml='" + desHtml + '\'' +
                ", caseViewModels=" + caseViewModels +
                ", tags=" + tags +
                '}';
    }
}
