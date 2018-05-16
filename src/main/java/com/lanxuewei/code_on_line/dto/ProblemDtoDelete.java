//package com.lanxuewei.code_on_line.dto;
//
//import com.lanxuewei.code_on_line.dao.entity.Problem;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * create by lanxuewei in 2018/5/15 20:04
// * description: problem dto todo 废弃
// */
//public class ProblemDto {
//
//    /**
//     * 是否通过
//     */
//    private boolean isResolved;
//
//    /**
//     * 题目信息
//     */
//    private Problem problem;
//
//    public ProblemDto(boolean isResolved, Problem problem) {
//        this.isResolved = isResolved;
//        this.problem = problem;
//    }
//
//    /**
//     * 完成题目 id 以及所有问题集构建 ProblemDto list
//     *
//     * @param allResolvedProblemIds 完成题目id集
//     * @param problems 问题集
//     */
//    public static List<ProblemDto> getProblemDtoList(List<Long> allResolvedProblemIds, List<Problem> problems) {
//        List<ProblemDto> problemDtos = new ArrayList<>();  // 返回结果集
//
//        if (problems != null) {                     // 问题不为空才进行操作,否则直接返回null
//            if (allResolvedProblemIds == null) {    // 完成题目集为空 表示都未完成
//                for (Problem problem : problems) {  // 直接构造 ProblemDto isResolve=false
//                    ProblemDto problemDto = new ProblemDto(false, problem);
//                    problemDtos.add(problemDto);    // 加入结果集
//                }
//            } else {
//                for (Problem problem : problems) {  // 遍历问题集
//                    if (allResolvedProblemIds.contains(problem.getId())) {  // 如果该问题已做
//                        ProblemDto problemDto = new ProblemDto(true, problem);
//                        problemDtos.add(problemDto);
//                    }
//                }
//
//            }
//            return problemDtos;  // 返回结果集
//        }
//        return null;
//    }
//
//    public boolean isResolved() {
//        return isResolved;
//    }
//
//    public void setResolved(boolean resolved) {
//        isResolved = resolved;
//    }
//
//    public Problem getProblem() {
//        return problem;
//    }
//
//    public void setProblem(Problem problem) {
//        this.problem = problem;
//    }
//
//    @Override
//    public String toString() {
//        return "ProblemDto{" +
//                "isResolved=" + isResolved +
//                ", problem=" + problem +
//                '}';
//    }
//}
