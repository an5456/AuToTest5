package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.getuserinfolistcase;
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
import java.util.Arrays;
import java.util.List;

public class getuserinfolistTest {
    @Test(groups = "loginTest",description = "获取用户列表信息接口")
    public void getuserinfolist() throws IOException, InterruptedException {
        SqlSession sqlSession= databaseUtil.sqlSession();
        getuserinfolistcase getuserinfolistcase=sqlSession.selectOne("getuserlist2",1);
        System.out.println(getuserinfolistcase.toString());
        System.out.println(TestConfig.getuserinfolistUrl);


        //发送请求，获取结果
        JSONArray resultJson=getJsonResult(getuserinfolistcase);
        System.out.println("预期:"+resultJson.toString());
        Thread.sleep(3000);
        List<user> userList=sqlSession.selectList(getuserinfolistcase.getExpected(),getuserinfolistcase);
        for (user u:userList){
            System.out.println("获取的user："+u.toString());
        }
        JSONArray userListJson=new JSONArray(userList);
        Assert.assertEquals(userListJson.length(),resultJson.length());


        for (int i=0;i<resultJson.length();i++){
            JSONObject actual=(JSONObject)userListJson.get(i);
            JSONObject expect=(JSONObject)resultJson.get(i);
            Assert.assertEquals(actual.toString(),expect.toString());
        }


    }

    private JSONArray getJsonResult(getuserinfolistcase getuserinfolistcase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.getuserinfolistUrl);

        JSONObject param=new JSONObject();
        param.put("userName",getuserinfolistcase.getUserName());
        param.put("age",getuserinfolistcase.getAge());
        param.put("sex",getuserinfolistcase.getSex());

        post.setHeader("content-type","application/json");

        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        String result;
        HttpResponse response=TestConfig.defaultHttpClient.execute(post);
        result=EntityUtils.toString(response.getEntity(),"utf-8");
        List resultArray= Arrays.asList(result);
        JSONArray resultJson=new JSONArray(resultArray);
        return resultJson;
    }
}
