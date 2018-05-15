package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.UserProblem;
import com.lanxuewei.code_on_line.dao.entity.UserProblemKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * UserProblem mapper todo test
 */
@Mapper
public interface UserProblemMapper {

    //新增
    int insert(UserProblem record);

    int insertSelective(UserProblem record);

    //删除
    int deleteByPrimaryKey(UserProblemKey key);

    //查找
    UserProblem selectByPrimaryKey(UserProblemKey key);

    /**
     * 根据 userId 查询完成题目数
     * @param userId
     * @return
     */
    Integer countAllResolved(Long userId);

    /**
     * 查询已做题目集
     * @param userId
     * @return
     */
    List<Long> selectProblemIdByResolved(@Param("userId") Long userId);

    //更新
    int updateByPrimaryKeySelective(UserProblem record);

    //int updateByPrimaryKeyWithBLOBs(UserProblem record);

    int updateByPrimaryKey(UserProblem record);
}