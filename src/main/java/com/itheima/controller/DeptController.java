package com.itheima.controller;

import com.itheima.anno.LogOperation;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/depts")//提取公共路径

@RestController//底层封装了@controller和@ResponseBODY
public class DeptController {

    @Autowired
    private DeptService deptService;

    //@RequestMapping("/depts")
    @LogOperation
    @GetMapping
    public Result list(){
        System.out.println("查询全部部门数据");
        List<Dept> deptList=deptService.findAll();

        return Result.success(deptList);
    }


    /*方式二
    @DeleteMapping("/depts")
    public Result delete(@RequestParam(value="id",required=true) Integer deptId){
        System.out.println("根据ID删除部门："+ deptId);
        return Result.success();
    }
    @requestparam 要求参数必须传递，不传递会报错
     */
    @LogOperation
    @DeleteMapping
    public Result delete(Integer id){
        System.out.println("根据ID删除部门："+ id);
        deptService.deleteById(id);
        return Result.success();
    }
    @LogOperation
    @PostMapping
    public Result add(@RequestBody Dept dept){
        System.out.println("新增部门："+dept);
        deptService.add(dept);
        return Result.success();

    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer id){
        System.out.println("根据Id查询"+ id);
        Dept dept =deptService.getById(id);
        return  Result.success(dept);
    }
    //@pathvariable  路径参数
    @PutMapping
    public Result update(@RequestBody Dept dept){
        System.out.println("修改部门"+ dept);
        deptService.update(dept);
        return Result.success();
    }
}
