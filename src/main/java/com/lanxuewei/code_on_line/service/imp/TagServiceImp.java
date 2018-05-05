package com.lanxuewei.code_on_line.service.imp;

import com.lanxuewei.code_on_line.dao.entity.Tag;
import com.lanxuewei.code_on_line.dao.mapper.TagMapper;
import com.lanxuewei.code_on_line.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by lanxuewei in 2018/4/21 19:19
 * description: Tag 相关业务实现
 */
@Service
public class TagServiceImp implements TagService{

    private static final Logger logger = LoggerFactory.getLogger(TagServiceImp.class);

    @Autowired
    private TagMapper tagMapper;

    @Override
    public boolean addTag(Tag tag) {
        return tagMapper.insert(tag) != 0;
    }

    @Override
    public boolean deleteTagById(Long id) {
        return tagMapper.deleteByPrimaryKey(id) != 0;
    }

    @Override
    public Tag findTagById(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tag> selectAll() {
        return tagMapper.selectAll();
    }

    @Override
    public int selectCount() {
        return tagMapper.selectCount();
    }

    @Override
    public boolean modifyTagById(Tag tag) {
        return tagMapper.updateByPrimaryKey(tag) != 0;
    }
}
