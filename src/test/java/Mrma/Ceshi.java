package Mrma;


import com.read.Use;
import com.sql.Shuju;
import com.sqlsession.GetShu;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ceshi {

    @Test
    public void cehi() {
        ApplicationContext factory = new ClassPathXmlApplicationContext("peizhi.xml");
        Use use = (Use) factory.getBean("useOperating");
        use.read();
    }

    @Test
    public void say(){
        Say say = new Say("11");
        System.out.println(say.toString());

    }

    @Test
    public void textMybatisSpring(){
        GetShu getShu = GetShu.getShu();
        Shuju shuju = getShu.getshuju();
        System.out.println(shuju.select("112"));
    }
}
