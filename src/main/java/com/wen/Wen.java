package com.wen;

import com.bean.Bean;
import com.sql.Shuju;
import com.sqlsession.Getsql;
import org.apache.ibatis.session.SqlSession;

import java.io.*;

public class Wen {

    private static Wen wen;

    private Wen(){}
    public static Wen getWen(){
        if (wen == null)
            wen = new Wen();
        return wen;
    }

    public void read() {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            String str = "";
            StringBuilder str1 = new StringBuilder();
            fis = new FileInputStream("src/main/resources/peizhi.txt");// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
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

    private SqlSession getsql = Getsql.geSession();
    private Shuju shuju = getsql.getMapper(Shuju.class);
    private void add(String str){

        String qufeng[] = str.split(":",2);
        if (shuju.select(qufeng[0])==null) {
            shuju.add(new Bean(qufeng[0], qufeng[1]));

            getsql.commit();
        }
    }
}

