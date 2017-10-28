package com.read;


import com.bean.Bean;
import com.sql.Shuju;
import com.sqlsession.GetShu;
import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.*;

public class Operatingtxt implements Operating{
    private GetShu getShu = GetShu.getShu();
    private Shuju shuju = getShu.getshuju();

    @SneakyThrows({FileNotFoundException.class,IOException.class})
    public void read() {
        String str = "";
        @Cleanup
        FileInputStream fis = new FileInputStream("src/main/resources/peizhi.txt");
        @Cleanup
        InputStreamReader isr = new InputStreamReader(fis);
        @Cleanup
        BufferedReader br = new BufferedReader(isr);
        while ((str = br.readLine()) != null) {
            add(str);
        }
    }

    @SneakyThrows(IOException.class)
    public void qingkong() {
        File f = new File("src/main/resources/peizhi.txt");
        @Cleanup  FileWriter fw = new FileWriter(f);
        fw.write("");
    }

    private void add(String str){
        String qufeng[] = str.split(":",2);
        if (shuju.select(qufeng[0])==null) {
            shuju.add(new Bean(qufeng[0], qufeng[1]));

        }
    }
}
