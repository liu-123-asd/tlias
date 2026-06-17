package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.ClassOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String,Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Integer>> getStudentDegreeData() {

        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClassOption getStudentCountData() {
        // 1. 直接查Map集合，不自定义ClazzCount
        List<Map<String, Object>> list = studentMapper.countStudentByClazz();

        // 提取班级名称数组
        List<Object> clazzList = list.stream()
                .map(dataMap -> dataMap.get("clazzName"))
                .toList();

        // 提取对应人数数组
        List<Object> dataList = list.stream()
                .map(dataMap -> dataMap.get("count"))
                .toList();
        return new ClassOption(clazzList, dataList);
    }
}
