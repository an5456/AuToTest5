package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.updateuserinfocase;
import com.course.model.user;
import com.course.util.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class updateuserinfoTest {
    @Test(groups = "loginTest",description = "更新用户信息接口")
    public void updateuser() throws IOException, InterruptedException {
        SqlSession sqlSession= databaseUtil.sqlSession();
        updateuserinfocase updateuserinfocase=sqlSession.selectOne("updatauser",1);
        System.out.println(updateuserinfocase.toString());
        System.out.println(TestConfig.updateuserinfoUrl);

        int result=getResult(updateuserinfocase);
        Thread.sleep(3000);

        user user=sqlSession.selectOne(updateuserinfocase.getExpected(),updateuserinfocase);
        Assert.assertNotNull(user);
        Assert.assertNotNull(result);

    }

    private int getResult(updateuserinfocase updateuserinfocase) throws IOException {

        HttpPost post=new HttpPost(TestConfig.updateuserinfoUrl);
        JSONObject param=new JSONObject();
        param.put("id",updateuserinfocase.getUserId());
        param.put("userName",updateuserinfocase.getUserName());
        param.put("age",updateuserinfocase.getAge());
        param.put("sex",updateuserinfocase.getSex());
        param.put("peimission",updateuserinfocase.getPermission());
        param.put("isDelete",updateuserinfocase.getIsDelete());

        post.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;

        HttpResponse response=TestConfig.defaultHttpClient.execute(post);

        result=EntityUtils.toString(response.getEntity(),"utf-8");

        return Integer.parseInt(result);


    }
}
