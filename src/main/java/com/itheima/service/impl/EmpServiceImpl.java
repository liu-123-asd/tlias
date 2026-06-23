package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import com.itheima.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    /*
        @Override
        public PageResult<Emp> page(Integer page, Integer pageSize) {
            Long total=empMapper.count();

            Integer start=(page-1)*pageSize;
            List<Emp> rows = empMapper.list(start,pageSize);
            return  new PageResult<Emp> (total,rows);
        }

     */
/*
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Emp> empList=empMapper.list();
        Page<Emp> p =(Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }
*/
    @Override
    public PageResult page(EmpQueryParam empQueryParam) {
        //1. 设置PageHelper分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3. 封装分页结果
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult(p.getTotal(), p.getResult());
    }


    @Transactional(rollbackFor = {Exception.class})//事务管理
    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(expr -> {
                expr.setEmpId(emp.getId());//新增时 emp 插入数据库前，emp.getId() 还没有值；插入后才会生成自增主键。前端新增页面，履历表单不知道这条员工最终 ID，所有 exprList 里 empId=null必须后端统一用持久化后的 empId 批量塞给每一条履历，否则入库时外键为空，直接报错。
//也就是说直接用前端的数据会直接报错，因为在前端周期中还没有响应。
            });
            empExprMapper.insertBatch(exprList);


        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);

        empExprMapper.deleteByEmpIds(ids);


    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);


    }

    @Transactional
    @Override
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2. 根据员工ID删除员工的工作经历信息 【删除老的】
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //3. 新增员工的工作经历数据 【新增新的】
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            //1. 生成JWT令牌
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());

            String jwt = JwtUtils.generateJwt(dataMap);
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
            return loginInfo;
        }
        return null;
    }

    @Override
    public List<Emp> findall() {
       return empMapper.findall();
    }

}
