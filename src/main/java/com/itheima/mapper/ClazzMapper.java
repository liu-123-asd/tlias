package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void add(Clazz clazz);

    Clazz getclazzById(Integer id);

    // 根据班级id，查询关联的学生总数
    Long countByClazzId(Integer clazzId);

    void update(Clazz clazz);

    List<Clazz> findAll();
}
