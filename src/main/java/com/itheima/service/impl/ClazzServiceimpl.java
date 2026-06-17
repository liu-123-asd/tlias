package com.itheima.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.exception.ClazzHasStudentException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceimpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult page(ClazzQueryParam clazzQueryParam) {

            //1. 设置PageHelper分页参数
            PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
            //2. 执行查询
            List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
            //3. 封装分页结果
            Page<Clazz> p = (Page<Clazz>)clazzList;
            //4.赋值status
        LocalDate now = LocalDate.now();
        for (Clazz clazz : clazzList) {
            LocalDate beginDate = clazz.getBeginDate();
            LocalDate endDate = clazz.getEndDate();
            if (now.isAfter(endDate)) {
                clazz.setStatus("已结课");
            } else if (now.isBefore(beginDate)) {
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        }
            return new PageResult(p.getTotal(), p.getResult());


    }

    @Override
    public void delete(Integer id) {
        // 查询该班级下学生数量
        Long count = clazzMapper.countByClazzId(id);
        if(count > 0){
            // 抛出自定义异常，信息写死需求文案
            throw new ClazzHasStudentException("对不起, 该班级下有学生, 不能直接删除");
        }
        // 原有删除逻辑不变
        clazzMapper.deleteById(id);
    }

    @Override
    public void add(Clazz clazz) {
        clazzMapper.add(clazz);
    }

    @Override
    public Clazz getclazzById(Integer id) {
        return clazzMapper.getclazzById(id);


    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return  clazzMapper.findAll();
    }
}
