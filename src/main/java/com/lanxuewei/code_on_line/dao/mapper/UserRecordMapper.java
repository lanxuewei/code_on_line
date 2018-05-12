package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.UserRecord;

/**
 * UserRecord mapper todo test
 */
public interface UserRecordMapper {

    //新增
    int insert(UserRecord record);

    int insertSelective(UserRecord record);

    //删除
    int deleteByPrimaryKey(Long id);

    //查找
    UserRecord selectByPrimaryKey(Long id);

    //更新
    int updateByPrimaryKeySelective(UserRecord record);

    int updateByPrimaryKey(UserRecord record);
}