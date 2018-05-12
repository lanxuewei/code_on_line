package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.UserProblem;
import com.lanxuewei.code_on_line.dao.entity.UserProblemKey;

/**
 * UserProblem mapper todo test
 */
public interface UserProblemMapper {

    //新增
    int insert(UserProblem record);

    int insertSelective(UserProblem record);

    //删除
    int deleteByPrimaryKey(UserProblemKey key);

    //查找
    UserProblem selectByPrimaryKey(UserProblemKey key);

    //更新
    int updateByPrimaryKeySelective(UserProblem record);

    int updateByPrimaryKeyWithBLOBs(UserProblem record);

    int updateByPrimaryKey(UserProblem record);
}