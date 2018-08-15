package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.updateuserinfocase;
import com.course.util.databaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class updateuserinfoTest {
    @Test(groups = "loginTest",description = "更新用户信息接口")
    public void updateuser() throws IOException {
        SqlSession sqlSession= databaseUtil.sqlSession();
        updateuserinfocase updateuserinfocase=sqlSession.selectOne("updatauser",1);
        System.out.println(updateuserinfocase.toString());
        System.out.println(TestConfig.updateuserinfoUrl);
    }
}
