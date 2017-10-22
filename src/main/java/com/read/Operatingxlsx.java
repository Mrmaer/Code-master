package com.read;

import com.bean.Bean;
import com.sql.Shuju;
import com.sqlsession.Getsql;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Operatingxlsx implements Operating {
    private SqlSession getsql = Getsql.geSession();
    private Shuju shuju = getsql.getMapper(Shuju.class);
    public void read() {
        InputStream is = null;
        try {

            is = new FileInputStream("src/main/resources/peizhi.xlsx");
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);

            for (XSSFSheet xssfSheet:xssfWorkbook){
                if (xssfSheet == null)
                    continue;
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

    public void qingkong() {

        XSSFWorkbook workbook = null;
        workbook = new XSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        String fileDir = "src/main/resources/peizhi.xlsx";
        String sheetName = "sheet1";
        Sheet sheet1 = workbook.createSheet(sheetName);
        //新建文件
        FileOutputStream out = null;
        try {
            //添加表头
            Row row = workbook.getSheet(sheetName).createRow(0);    //创建第一行
            String titleRow[] = {"name","address"};
            for(int i = 0;i < titleRow.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(titleRow[i]);
            }
            out = new FileOutputStream(fileDir);
            workbook.write(out);
        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
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
