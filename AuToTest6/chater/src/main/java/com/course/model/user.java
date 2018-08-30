package com.course.model;

public class user {
    private String id;
    private String userName;
    private String passWord;
    private String sex;
    private int age;
    private String isDelete;
    private String permission;
    @Override
    public String toString(){
        return (
                "{id:"+id+","+
                "userName:"+userName+","+
                "passWord:"+passWord+","+
                "sex:"+sex+","+
                "age:"+age+","+
                "isDelete:"+isDelete+","+
                "permission:"+permission+"}"

        );
    }
}

