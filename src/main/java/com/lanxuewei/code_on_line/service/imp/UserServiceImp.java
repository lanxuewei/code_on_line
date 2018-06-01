package com.lanxuewei.code_on_line.service.imp;

import com.github.pagehelper.PageHelper;
import com.lanxuewei.code_on_line.constant.ServiceConstant;
import com.lanxuewei.code_on_line.dao.entity.User;
import com.lanxuewei.code_on_line.dao.mapper.UserMapper;
import com.lanxuewei.code_on_line.dto.ProblemDto;
import com.lanxuewei.code_on_line.model.Page;
import com.lanxuewei.code_on_line.model.UserViewModel;
import com.lanxuewei.code_on_line.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 将UserViewModel转化为User
     * @param userViewModel
     * @return User
     */
    private User userViewModelToUser(UserViewModel userViewModel) {
        User user = new User();
        user.setUserName(userViewModel.getUsername());
        user.setPassword(userViewModel.getPassword());
        user.setRealName(userViewModel.getRealname());
        user.setSex(userViewModel.getSex());
        user.setImg(userViewModel.getImg());
        user.setDes(userViewModel.getDes());
        return user;
    }

    /**
     * 新增
     * @param vUser
     * @return
     */
    @Override
    public boolean addUser(UserViewModel vUser) {
        User user = userViewModelToUser(vUser);  //先将界面模型entity
        return userMapper.insertSelective(user) != 0;
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
     * 分页查找用户列表
     * @param pageNum
     * @param pageSize
     * @param status
     * @param keyword
     * @return
     */
    @Override
    public Page<User> getUserListByPage(Integer pageNum, Integer pageSize, Byte status, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectAllUsers(status, keyword);
        return new Page<>(users);
    }

    /**
     * 查询所有用户
     * @param status
     * @param keyword
     * @return
     */
    @Override
    public List<User> selectAllUsers(Byte status, String keyword) {
        return userMapper.selectAllUsers(status, keyword);
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

    /**
     * 更改用户状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public boolean changeUserStatus(Long id, Byte status) {
        return userMapper.updateUserStatus(id, status) != 0;
    }

    /**
     * 重置用户密码 todo 密码应该采用md5加密
     * @param id
     * @return
     */
    @Override
    public boolean resetUserPassword(Long id) {
        String password = "123456";
        return userMapper.updateUserPassword(id, password) != 0;
    }

    /**
     * 根据状态码统计用户数
     * @param status
     * @return
     */
    @Override
    public int countAllUsersByStatus(Byte status) {
        return userMapper.countAllUsersByStatus(status);
    }

    /**
     * 判断该用户是否为管理员
     * @param userId
     * @return
     */
    @Override
    public boolean isManager(Long userId) {
        Byte status = userMapper.selectStatusByUserId(userId);  // 通过 userId 查找对应的 status
        if (status == ServiceConstant.User.manager) {  // 表示管理员
            return true;
        } else {
            return false;                              // 学生身份
        }
    }
}
