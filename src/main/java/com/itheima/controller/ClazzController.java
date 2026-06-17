package com.itheima.controller;


import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result  getClazzs(ClazzQueryParam clazzQueryParam){
        PageResult pageResult=clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }
    @GetMapping("/list")
    public Result  getAllClazzs(){
        List<Clazz> clazz =clazzService.findAll();
        return Result.success(clazz);
    }
    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable("id") Integer id){
        clazzService.delete(id);
        return Result.success();
    }


    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz){
        clazzService.add(clazz);
        return Result.success();

    }
    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable("id") Integer id){
        Clazz clazz=clazzService.getclazzById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){
        clazzService.update(clazz);
        return Result.success();
    }





}
