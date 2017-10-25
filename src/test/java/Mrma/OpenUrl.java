package Mrma;

import com.openurl.Open;
import org.junit.Test;

public class OpenUrl {
    private static Open open = Open.getOpen();
    @Test
    public void open(){
       open.url("http://www.baidu.com");
    }
}
