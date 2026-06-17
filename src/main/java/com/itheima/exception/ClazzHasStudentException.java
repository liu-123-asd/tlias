package com.itheima.exception;

/**
 * 班级存在关联学生，禁止删除 自定义异常
 */
public class ClazzHasStudentException extends RuntimeException {
    // 接收提示信息
    public ClazzHasStudentException(String message) {
        super(message);
    }
}