package com.lanxuewei.code_on_line.judger;

// referenced ZOJ FAQ: http://acm.zju.edu.cn/onlinejudge/faq.do
public enum JudgeStatus {
    QUEUING,
    ACCEPTED,
    PRESENTATION_ERROR,
    WRONG_ANSWER,
    TIME_LIMIT_EXCEEDED,
    MEMORY_LIMIT_EXECEEDED,
    OUTPUT_LIMIT_EXCEEDED,
    NON_ZERO_EXIT_CODE,
    COMPILE_ERROR,
    SEGMENTATION_FAULT,
    FLOATING_POINT_ERROR,
    RUNTIME_ERROR,
}
