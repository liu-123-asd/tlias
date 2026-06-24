package com.itheima.service;

import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.OperateLogQueryParam;

public interface OperateLogService {
    PageResult<OperateLog> page(OperateLogQueryParam param);
}