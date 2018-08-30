package com.course.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.Reader;

public class databaseUtil {
    public static SqlSession sqlSession() throws IOException {
        Reader reader= Resources.getResourceAsReader("databaseConfig.xml");
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        return sqlSession;
    }
}
