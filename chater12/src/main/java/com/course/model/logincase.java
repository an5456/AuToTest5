package com.course.model;

import lombok.Data;

@Data
public class logincase {
    private int id;
    private String userName;
    private String passWord;
    private String expected;
}
