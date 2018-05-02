package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.User;
import org.springframework.stereotype.Service;

/**
 * create by lanxuewei in 2018/4/15 21:06
 * description: User 类的CURD操作
 */
@Service
public interface UserService {

    //add
    /**
     * 添加用户
     * @param user
     * @return 操作是否成功
     */
    boolean addUser(User user);


    //delete
    /**
     * 删除用户(TODO 暂时不考虑实现)
     * @param userId
     * @return 操作是否成功
     */
    boolean deleteUserByUserId(Long userId);


    //find
    /**
     * 通过用户名以及status查找用户信息
     * @param userName 用户名
     * @param status 身份码
     * @return 查找到的用户信息
     */
    User findByUserName(String userName, Byte status);
    User findByUserId(Long userId);


    //modify
    /**
     * 通过用户名修改用户信息
     * @param userName 用户名
     * @return 操作是否成功
     */
    boolean modifyUserByUserName(String userName);
}
