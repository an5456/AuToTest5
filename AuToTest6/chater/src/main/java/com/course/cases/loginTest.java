package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.logincase;
import com.course.util.configUrl;
import com.course.util.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

        //发送请求，获取结果
        String result=getResult(logincase);
        //断言
        Assert.assertEquals(logincase.getExpected(),result);
    }

    private String getResult(logincase logincase) throws IOException {

        HttpPost post=new HttpPost(TestConfig.loginUrl);
        JSONObject param=new JSONObject();
        param.put("userName",logincase.getUserName());
        param.put("passWord",logincase.getPassWord());

        post.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);

        result=EntityUtils.toString(response.getEntity(),"utf-8");

        TestConfig.store=TestConfig.defaultHttpClient.getCookieStore();

        return result;
    }


}
