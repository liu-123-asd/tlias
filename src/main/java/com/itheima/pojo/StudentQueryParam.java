package com.itheima.pojo;

import lombok.Data;

@Data
public class StudentQueryParam {

    private Integer page=1;
    private Integer pageSize=10;
    private String name; //班级姓名
    private  Integer degree;
    private Integer clazzId;

}
