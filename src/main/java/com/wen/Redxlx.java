package com.wen;

import com.bean.Bean;
import com.sql.Shuju;
import com.sqlsession.Getsql;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Redxlx {
    private static Redxlx redxlx;

    private Redxlx(){}
    public static Redxlx getRedxlx(){
        if (redxlx == null)
            redxlx = new Redxlx();
        return redxlx;
    }


    private SqlSession getsql = Getsql.geSession();
    private Shuju shuju = getsql.getMapper(Shuju.class);


    public void readxlsx() {
        InputStream is = null;
        try {
            is = new FileInputStream("src/main/resources/peizhi.xlsx");
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

            for (XSSFSheet xssfSheet:xssfWorkbook){
                if (xssfSheet == null)
                    continue;
//                System.out.println(xssfSheet.getLastRowNum());
                for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                    XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                    List<String> strings = new ArrayList<String>();
                    String name = xssfRow.getCell(0).toString();
                    String addrees = xssfRow.getCell(1).toString();
                    strings.add(name);
                    strings.add(addrees);
                   add(strings);
                }
            }

            } catch(FileNotFoundException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();

            }finally{
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


    }


    private void add(List<String> list){
        if (shuju.select(list.get(0))==null) {
            Bean bean = new Bean(list.get(0),list.get(1));
            shuju.add(bean);
            getsql.commit();
        }
    }
}
