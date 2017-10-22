package com.read;


import com.bean.Bean;
import com.sql.Shuju;
import com.sqlsession.Getsql;
import org.apache.ibatis.session.SqlSession;

import java.io.*;

public class Operatingtxt implements Operating{
    private SqlSession getsql = Getsql.geSession();
    private Shuju shuju = getsql.getMapper(Shuju.class);

    public void read() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {

            String str = "";
            fis = new FileInputStream("src/main/resources/peizhi.txt");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {

                add(str);
            }

        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void qingkong() {
        System.out.println("111");
        File f = new File("src/main/resources/peizhi.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            fw.write("");
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }


    private void add(String str){
        String qufeng[] = str.split(":",2);
        if (shuju.select(qufeng[0])==null) {
            shuju.add(new Bean(qufeng[0], qufeng[1]));
            getsql.commit();
        }
    }
}
