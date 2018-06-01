package com.lanxuewei.code_on_line.service;

import com.lanxuewei.code_on_line.dao.entity.User;
import com.lanxuewei.code_on_line.dao.entity.UserRecord;
import com.lanxuewei.code_on_line.model.Page;
import com.lanxuewei.code_on_line.model.UserViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by lanxuewei in 2018/4/15 21:06
 * description: User 类的CURD操作
 */
@Service
public interface UserService {

    /**
     * 将UserViewModel转化为User
     * @param userViewModel
     * @return User
     */
    /*User UserViewModelToUser(UserViewModel userViewModel);*/

    //add
    /**
     * 添加用户
     * @param vUser
     * @return 操作是否成功
     */
    boolean addUser(UserViewModel vUser);

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

    /**
     * 分页查找用户列表
     * @param pageNum
     * @param pageSize
     * @param status
     * @param keyword
     * @return
     */
    Page<User> getUserListByPage(Integer pageNum,
                                 Integer pageSize,
                                 Byte status,
                                 String keyword);

    /**
     * 查询所有用户
     * @param status
     * @param keyword
     * @return
     */
    List<User> selectAllUsers(Byte status, String keyword);

    /**
     * 根据问题id以及用户id查找做题记录
     * @param userId
     * @param problemId
     * @return
     */
    List<UserRecord> findUserRecords(Long userId, Long problemId);


    //modify
    /**
     * 通过用户名修改用户信息
     * @param userName 用户名
     * @return 操作是否成功
     */
    boolean modifyUserByUserName(String userName);

    /**
     * 更改用户状态
     * @param id
     * @param status
     * @return
     */
    boolean changeUserStatus(Long id, Byte status);

    /**
     * 重置用户密码为123456
     * @param id
     * @return
     */
    boolean resetUserPassword(Long id);

    /**
     * 根据状态码统计用户数量
     * @param status
     * @return
     */
    int countAllUsersByStatus(Byte status);

    /**
     * 判断该用户是否为管理员
     * @param userId
     * @return
     */
    public boolean isManager(Long userId);
}
