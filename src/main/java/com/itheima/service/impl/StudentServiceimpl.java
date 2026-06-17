package com.itheima.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.*;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceimpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult page(StudentQueryParam studentQueryParam) {

            //1. 设置PageHelper分页参数
            PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
            //2. 执行查询
            List<Student> studentList = studentMapper.list(studentQueryParam);
            //3. 封装分页结果
            Page<Student> p = (Page<Student>)studentList;
            return new PageResult(p.getTotal(), p.getResult());

    }

    @Override
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    @Override
    public void add(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);

    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        studentMapper.deleteBatch(ids);
    }

    @Override
    public void violationAdd(Integer id, Integer score) {
        // 1. 查询学生
        Student student = studentMapper.getById(id);

        // 2. Short类型运算，强转Short
        short newCount = (short) (student.getViolationCount() + 1);
        student.setViolationCount(newCount);

        short newScore = (short) (student.getViolationScore() + score);
        student.setViolationScore(newScore);

        // 3. Service层手动设置更新时间
        student.setUpdateTime(LocalDateTime.now());

        // 4. 调用通用update更新
        studentMapper.update(student);
    }
}
