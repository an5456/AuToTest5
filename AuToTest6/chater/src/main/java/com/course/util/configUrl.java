package com.course.util;

import com.course.model.InterfaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class configUrl {
    private static ResourceBundle bundle=ResourceBundle.getBundle("application",Locale.CHINA);
    public static String getUrl(InterfaceName name){
        String address=bundle.getString("test.url");
        String uri="";
        String testUrl;
        if(name==InterfaceName.ADDUSERURL){
            uri=bundle.getString("addUser.uri");
        }
        if(name==InterfaceName.GETUSERINFOLISTURL){
            uri=bundle.getString("getUserList.uri");
        }
        if(name==InterfaceName.GETUSERINFOURL){
            uri=bundle.getString("getUserInfo.uri");
        }
        if(name==InterfaceName.LOGINURL){
            uri=bundle.getString("login.uri");
        }
        if(name==InterfaceName.UPDATEUSERINFOURL){
            uri=bundle.getString("updateUserInfo.uri");
        }
        testUrl=address+uri;
        return testUrl;
    }
}

