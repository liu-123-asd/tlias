package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentQueryParam studentQueryParam);

    void deleteById(Integer id);

    void insert(Student student);

    Student getById(Integer id);

    void update(Student student);

    void deleteBatch(@Param("ids") List<Integer> ids);

    void updateViolation(Integer id, Integer score);

    List<Map<String, Integer>> countStudentDegreeData();

    List<Map<String, Object>> countStudentByClazz();
}
