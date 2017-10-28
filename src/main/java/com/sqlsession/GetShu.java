package com.sqlsession;

import com.sql.Shuju;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetShu {
    private GetShu(){}
    private static GetShu getShu;
    public static GetShu getShu(){
        if (getShu ==null)
            getShu = new GetShu();
        return getShu;
    }

    public Shuju getshuju() {
        ApplicationContext context = new ClassPathXmlApplicationContext("peizhi.xml");
        Shuju shuju = context.getBean(Shuju.class);
        return shuju;
    }


}
