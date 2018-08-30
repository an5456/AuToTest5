package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.addusercase;
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


public class adduserTest {
    @Test(groups = "loginTest", description = "获取添加用户接口case")
    public void adduser() throws IOException, InterruptedException {
        SqlSession sqlSession = databaseUtil.sqlSession();
        addusercase addusercase = sqlSession.selectOne("adduser", 1);
        System.out.println(addusercase.toString());
        System.out.println(TestConfig.adduserUrl);

        //发送请求，获取结果
        String result=getResult(addusercase);

        Thread.sleep(3000);
        user user=sqlSession.selectOne("addusercase",addusercase);
        //判断结果
        Assert.assertEquals(addusercase.getExpected(),result);
    }

    private String getResult(addusercase addusercase) throws IOException {

        HttpPost post=new HttpPost(TestConfig.adduserUrl);
        JSONObject param=new JSONObject();
        param.put("userName",addusercase.getUserName());
        param.put("passWord",addusercase.getPassWord());
        param.put("age",addusercase.getAge());
        param.put("sex",addusercase.getSex());
        param.put("permission",addusercase.getPermission());
        param.put("isDelete",addusercase.getIsDelete());

        StringEntity entity=new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //设置请求头
        post.setHeader("content-type","application/json");

        //设置cookie
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);

        String result;

        HttpResponse response=TestConfig.defaultHttpClient.execute(post);

        result=EntityUtils.toString(response.getEntity(),"utf-8");

        System.out.println(result);


        return result;
    }

}
