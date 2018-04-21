package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.User;
import org.springframework.stereotype.Service;

/**
 * create by lanxuewei in 2018/4/15 21:06
 * description: User 类的CURD操作
 */
@Service
public interface UserService {

    //新增
    /**
     * 添加用户
     * @param user
     * @return 操作是否成功
     */
    public boolean addUser(User user);


    //删除
    /**
     * 删除用户(TODO 暂时不考虑实现)
     * @param userId
     * @return 操作是否成功
     */
    public boolean deleteUserByUserId(Long userId);


    //查找
    /**
     * 通过用户名查找用户信息
     * @param userName
     * @return 查找到的用户信息
     */
    public User findByUserName(String userName);
    public User findByUserId(Long userId);


    //修改
    /**
     * 通过用户名修改用户信息
     * @param userName
     * @return 操作是否成功
     */
    public boolean modifyUserByUserName(String userName);
}
