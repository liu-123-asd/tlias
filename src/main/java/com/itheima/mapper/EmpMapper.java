package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

/*
    @Select("select  count(*) from emp e left join dept d on e.dept_id = d.id")
    public  Long count();


    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc  limit #{start},#{pageSize}")//使用spingboot官方注解，只要标准变量名一样，不再使用param注解
    public List<Emp> list(Integer start,Integer pageSize);
*/

   // @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id=d.id order by e.update_time desc")
    //public List<Emp> list();
//pagehelper相当于一个拦截器，只会拦截一次即拦截附进第一条sql语句进行改造处理
    List<Emp> list(EmpQueryParam empQueryParam);


    /**
     * 新增员工数据
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")  //主键返回·
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    List<Map<String, Object>> countEmpJobData();

    List<Map<String,Object>> countEmpGenderData();

    /**
     * 根据用户名和密码查询员工信息
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);


    @Select("SELECT  *  from emp")
    List<Emp> findall();
}