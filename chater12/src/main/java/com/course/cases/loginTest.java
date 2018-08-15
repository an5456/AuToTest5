package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.logincase;
import com.course.util.configUrl;
import com.course.util.databaseUtil;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class loginTest {
    @BeforeTest(groups = "loginTest",description = "配置、获取请求URL")
    public void beforeTest(){
        TestConfig.loginUrl= configUrl.getUrl(InterfaceName.LOGINURL);
        TestConfig.adduserUrl=configUrl.getUrl(InterfaceName.ADDUSERURL);
        TestConfig.getuserinfolistUrl=configUrl.getUrl(InterfaceName.GETUSERINFOLISTURL);
        TestConfig.getuserinfoUrl=configUrl.getUrl(InterfaceName.GETUSERINFOURL);
        TestConfig.updateuserinfoUrl=configUrl.getUrl(InterfaceName.UPDATEUSERINFOURL);

        TestConfig.defaultHttpClient=new DefaultHttpClient();
    }
    @Test(groups = "loginTest",description = "获取登录接口case")
    public void loginTest() throws IOException {
        SqlSession sqlSession= databaseUtil.sqlSession();
        logincase logincase=sqlSession.selectOne("logincase",1);
        System.out.println(logincase.toString());
        System.out.println(TestConfig.loginUrl);
    }
}
