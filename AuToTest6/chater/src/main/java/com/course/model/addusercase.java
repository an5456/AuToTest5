package com.course.model;
import lombok.Data;

@Data
public class addusercase {
    private int id;
    private String userName;
    private String passWord;
    private int age;
    private String sex;
    private String permission;
    private String isDelete;
    private String expected;
}
