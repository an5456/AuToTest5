package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.getuserinfocase;
import com.course.model.user;
import com.course.util.databaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class getuserinfoTest {
    @Test(groups = "loginTest",description = "添加用户信息接口测试")
    public void getuserinfo() throws IOException, InterruptedException {
        SqlSession sqlSession= databaseUtil.sqlSession();
        getuserinfocase getuserinfocase=sqlSession.selectOne("getuserinfo1",1);
        System.out.println(getuserinfocase.toString());
        System.out.println(TestConfig.getuserinfoUrl);
        //发送请求，获取结果
        JSONArray resultJson=getResultJson(getuserinfocase);
        Thread.sleep(5000);
        user user=sqlSession.selectOne(getuserinfocase.getExpected(),getuserinfocase);
        List userlist=new ArrayList();
        userlist.add(user);
        JSONArray userListArray=new JSONArray(userlist);
        Assert.assertEquals(userListArray,resultJson);

    }

    private JSONArray getResultJson(getuserinfocase getuserinfocase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.getuserinfoUrl);
        JSONObject param=new JSONObject();
        param.put("id",getuserinfocase.getUserId());

        post.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);

        result=EntityUtils.toString(response.getEntity(),"utf-8");

        List resultArray= Arrays.asList(result);

        JSONArray result1=new JSONArray(resultArray);
        return result1;
    }
}
