package com.sqlsession;

import com.sql.Shuju;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class Getsql {

    private static SqlSessionFactory getsql;
    static {
        try {
            Reader reader = Resources.getResourceAsReader("MyBatis.xml");
            getsql = new SqlSessionFactoryBuilder().build(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSession geSession(){
        return getsql.openSession();
    }


}
