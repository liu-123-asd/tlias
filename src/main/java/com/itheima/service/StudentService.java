package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult page(StudentQueryParam studentQueryParam);

    void deleteById(Integer id);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void deleteBatch(List<Integer> ids);

    void violationAdd(Integer id, Integer score);
}
