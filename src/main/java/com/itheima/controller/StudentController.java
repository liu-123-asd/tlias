package com.itheima.controller;


import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result getStudents(StudentQueryParam studentQueryParam) {
        PageResult pageResult=studentService.page(studentQueryParam);
        return Result.success(pageResult);

    }
    // 4.2 删除学生
    // 4.2 批量删除学生
    // 4.2 批量删除学生
    @DeleteMapping
    public Result deleteBatch(@RequestParam List<Integer> ids){
        studentService.deleteBatch(ids);
        return Result.success();
    }
    // 4.3 新增学生
    @PostMapping
    public Result add(@RequestBody Student student){
        studentService.add(student);
        return Result.success();
    }

    // 4.4 根据id查询学生
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Student student = studentService.getById(id);
        return Result.success(student);
    }


    // 4.5 修改学生
    @PutMapping
    public Result update(@RequestBody Student student){
        studentService.update(student);
        return Result.success();
    }

    // 4.6 违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id, @PathVariable Integer score) {
        studentService.violationAdd(id, score);
        return Result.success();
    }
}




