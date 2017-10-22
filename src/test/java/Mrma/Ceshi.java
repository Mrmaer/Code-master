package Mrma;

import com.read.Use;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ceshi {
    @Test
    public void cehi() {
        BeanFactory factory = new ClassPathXmlApplicationContext("Opera.xml");
        Use use = (Use) factory.getBean("useOperating");
        use.read();
    }
}
