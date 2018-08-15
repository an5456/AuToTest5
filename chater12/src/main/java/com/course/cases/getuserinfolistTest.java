package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.getuserinfolistcase;
import com.course.util.databaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class getuserinfolistTest {
    @Test(groups = "loginTest",description = "获取用户列表信息接口")
    public void getuserinfolist() throws IOException {
        SqlSession sqlSession= databaseUtil.sqlSession();
        getuserinfolistcase getuserinfolistcase=sqlSession.selectOne("getuserlist",1);
        System.out.println(getuserinfolistcase.toString());
        System.out.println(TestConfig.getuserinfolistUrl);
    }
}
