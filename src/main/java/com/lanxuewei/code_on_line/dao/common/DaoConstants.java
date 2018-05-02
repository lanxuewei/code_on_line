package com.lanxuewei.code_on_line.dao.common;

/**
 * create by lanxuewei in 2018/4/21 14:48
 * description: dao层相关常量
 */
public interface DaoConstants {

    interface User{
        /** user 表中 sex 字段 */
        Byte Female = 0;  //女
        Byte Male = 1;    //男

        /** user 表中 status 字段 */
        Byte TeacherOrManager = 0;  //老师或管理员
        Byte Student = 1;           //学生
    }

    interface Problem{
        /** problem 表中 difficult 字段*/
        Byte easy = 0;              //简单
        Byte medium = 1;            //中等
        Byte difficult = 2;         //难
    }

}
