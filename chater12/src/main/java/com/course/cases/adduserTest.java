package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.addusercase;
import com.course.util.databaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class adduserTest {
    @Test(groups = "loginTest",description = "获取添加用户接口case")
    public void adduser() throws IOException {
        SqlSession sqlSession= databaseUtil.sqlSession();
        addusercase addusercase=sqlSession.selectOne("adduser",1);
        System.out.println(addusercase.toString());
        System.out.println(TestConfig.adduserUrl);
    }
}
