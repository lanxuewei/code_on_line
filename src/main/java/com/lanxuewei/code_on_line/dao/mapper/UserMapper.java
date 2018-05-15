package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 通过用户名以及身份码查找对应User
     * @param userName 用户名
     * @param status 身份码
     * @return User
     */
    User selectByUserName(@Param("userName") String userName, @Param("status") Byte status);
    /**
     * 根据 userId 查找对应的 status(用于身份判断)
     * @param id
     * @return
     */
    Byte selectStatusByUserId(@Param("id") Long id);

    //修改
    int updateByPrimaryKey(User record);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKeyWithBLOBs(User record);
}