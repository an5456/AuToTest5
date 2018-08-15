package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.getuserinfocase;
import com.course.util.databaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class getuserinfoTest {
    @Test(groups = "loginTest",description = "添加用户信息接口测试")
    public void getuserinfo() throws IOException {
        SqlSession sqlSession= databaseUtil.sqlSession();
        getuserinfocase getuserinfocase=sqlSession.selectOne("getuserinfo",1);
        System.out.println(getuserinfocase.toString());
        System.out.println(TestConfig.getuserinfoUrl);
    }
}
