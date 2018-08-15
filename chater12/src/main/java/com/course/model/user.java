package com.course.model;

import lombok.Data;

@Data
public class user {
    private int id;
    private String userName;
    private String passWord;
    private String sex;
    private int age;
    private String permission;
    private String isDelete;
    public String toString(){
        return (
                "{id:"+id+"," +
                "userName:"+userName+"," +
                "passWord:"+passWord+"," +
                "sex:"+sex+"," +
                "age:"+age+"," +
                "permission:"+permission+"," +
                "isDelete:"+isDelete+"}"
        );
    }
}
