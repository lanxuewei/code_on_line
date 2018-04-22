package com.lanxuewei.code_on_line.service.imp;

import com.lanxuewei.code_on_line.dao.entity.User;
import com.lanxuewei.code_on_line.dao.mapper.UserMapper;
import com.lanxuewei.code_on_line.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by lanxuewei in 2018/4/15 21:32
 * description: User 相关业务实现
 */
@Service
public class UserServiceImp implements UserService{

    private static Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        return false;
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @Override
    public boolean deleteUserByUserId(Long userId) {
        return false;
    }

    /**
     * 查找
     * @param userName
     * @param status
     * @return
     */
    @Override
    public User findByUserName(String userName, Byte status) {
        return userMapper.selectByUserName(userName, status);
    }

    @Override
    public User findByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 修改
     * @param userName
     * @return
     */
    @Override
    public boolean modifyUserByUserName(String userName) {
        return false;
    }
}
