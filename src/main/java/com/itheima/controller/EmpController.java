package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 员工管理
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

/*
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "1")Integer pageSize){
        log.info("page:{},pageSize:{}",page,pageSize);
        PageResult<Emp> pageResult=empService.page(page,pageSize);
        return  Result.success(pageResult);
    }
*/
@GetMapping
public Result page(EmpQueryParam empQueryParam) {
    log.info("查询请求参数： {}", empQueryParam);
    PageResult pageResult = empService.page(empQueryParam);
    return Result.success(pageResult);

}
@PostMapping
public Result save(@RequestBody Emp emp) {
    log.info("新增",emp);
    empService.save(emp);
    return Result.success();

}
/*@DeleteMapping
public Result delete(Integer[] ids) {
    log.info("删除：{}" ,Arrays.toString(ids));
    return  Result.success();
}

 */
@DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
    log.info("删除：{}" ,ids);
    empService.delete(ids);
    return  Result.success();
}


@GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
    log.info("根据Id查询员工信息：{}",id);
    Emp emp=empService.getInfo(id);
    return Result.success(emp);

}


    /**
     * 更新员工信息
     */
@PutMapping
public Result update(@RequestBody Emp emp){
    log.info("修改员工信息, {}", emp);
    empService.update(emp);
    return Result.success();
    }
}