package com.lanxuewei.code_on_line.dao.mapper;

import com.lanxuewei.code_on_line.dao.entity.ProblemThroughRate;
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

    /**
     * 计算对应题目的通过率
     * @param problemIds 题目id集
     * @return
     */
    List<ProblemThroughRate> selectProblemThroughRate(@Param("problemIds") List<Long> problemIds);

    /**
     * 查找该题提交次数
     * @param problemId
     * @param status
     * @return
     */
    Integer selectDoneCountByProblemId(@Param("problemId") Long problemId, @Param("status") Byte status);

    /**
     * 查找该问题成功次数
     * @param status
     * @param problemId
     * @return
     */
    Integer selectSuccessCountByProblemId(@Param("problemId") Long problemId, @Param("status") Byte status);

    /**
     * 查找最后一次用户提交的该问题代码
     * @param userId
     * @param problemId
     * @return
     */
    String selectLastSubmitCode(@Param("userId") Long userId, @Param("problemId") Long problemId);

    /**
     * 更新用户-题目记录 即成功或者失败+1 提交次数加1
     * @param userId
     * @param problemId
     * @param isSuccess 是否成功(0:表示成功 -1:表示失败)
     * @return
     */
    int updateSubmitsByIds(@Param("userId") Long userId, @Param("problemId") Long problemId, @Param("lastSubmit") String lastSubmit, @Param("isSuccess") Byte isSuccess);

    //更新
    int updateByPrimaryKeySelective(UserProblem record);

    //int updateByPrimaryKeyWithBLOBs(UserProblem record);

    int updateByPrimaryKey(UserProblem record);
}