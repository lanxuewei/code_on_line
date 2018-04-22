package com.lanxuewei.code_on_line.controller;

import com.lanxuewei.code_on_line.model.ReturnValue;
import com.lanxuewei.code_on_line.service.TagService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * create by lanxuewei in 2018/4/22 08:43
 * description: tag controller
 */
@RestController
@RequestMapping(value = "/tag")
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagService tagService;

    /**
     * 新增标签  TODO
     * @param tagName 标签名
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation("add a tag")
    public ReturnValue addTag(@RequestParam String tagName) {
        return null;
    }

    /**
     * 删除标签 TODO
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation("delete a tag")
    public ReturnValue deleteTagById(@PathVariable Long id) {
        Assert.notNull(id, "tagId can not be empty");
        tagService.deleteTagById(id);
        return null;
    }

    /**
     * 获取所有标签
     * @return tags
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("get tag list")
    public ReturnValue getTagList() {
        // TODO
        return null;
    }


}
