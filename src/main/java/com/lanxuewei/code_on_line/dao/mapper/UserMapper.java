package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 对应 User 的CURD操作
 */
@Mapper
public interface UserMapper {

    //增加
    int insert(User record);
    int insertSelective(User record);

    //删除
    int deleteByPrimaryKey(Long id);

    //查找
    /**
     * 通过id查找User
     * @param id id
     * @return User
     */
    User selectByPrimaryKey(Long id);
    /**
     * 通过用户名查找对应User
     * @param userName 用户名
     * @return User
     */
    User selectByUserName(String userName);

    //修改
    int updateByPrimaryKey(User record);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKeyWithBLOBs(User record);
}