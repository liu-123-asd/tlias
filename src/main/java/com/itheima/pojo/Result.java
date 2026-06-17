package com.itheima.pojo;


import lombok.Data;

@Data
public class Result {
    private Integer code;//1成功  2失败
    private String msg; //错误信息
    private Object data; //数据

    public static Result  success(){
        Result result=new Result();
        result.code=1;
        result.msg="success";
        return result;
    }
    public static Result  success(Object object){
        Result result=new Result();
        result.code=1;
        result.msg="success";
        result.data= object;
        return result;
    }
    public static Result  error(String msg){
        Result result=new Result();
        result.code=1;
        result.msg= msg;
        return result;
    }


}
