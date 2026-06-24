package com.itheima.controller;

import com.itheima.anno.LogOperation;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.OperateLogQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;

    // 查询日志列表【重点：查询分页不加@LogOperation，不会记录日志、不会超长报错】
    @GetMapping("/page")
    public Result page(OperateLogQueryParam param) {
        log.info("日志分页查询请求参数：{}", param);
        PageResult<OperateLog> pageResult = operateLogService.page(param);
        return Result.success(pageResult);
    }
}