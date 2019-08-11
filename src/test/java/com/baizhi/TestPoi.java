package com.baizhi;


import com.baizhi.entity.Cat;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = CmfzDyhApplication.class)
@RunWith(SpringRunner.class)
public class TestPoi {

    @Test
    public void test1(){

        //创建一个文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建表  指定表明
        HSSFSheet sheet = workbook.createSheet("用户表");
        //创建表格
        HSSFRow row = sheet.createRow(0);
        //创建单元格
        HSSFCell cell = row.createCell(0);
        //设置单元格的内容
        cell.setCellValue("第一个单元格内容");

        //导出表
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("F://text1.xls"));
           workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test2(){
        //创建对象
        Cat cat1 = new Cat("1", "小可爱", 2, new Date());
        Cat cat2 = new Cat("2", "小可人", 1, new Date());
        Cat cat3 = new Cat("3", "小爱人", 1, new Date());
        Cat cat4 = new Cat("4", "小可肥", 2, new Date());
        Cat cat5 = new Cat("5", "小可爱", 2, new Date());
        //将数据添加进集合
        List<Cat> cats = Arrays.asList(cat1, cat2, cat3, cat4, cat5);
        //创建一个文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个表
        HSSFSheet sheet = workbook.createSheet("宠物表");

        //创建标题
        HSSFRow row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("宠物表明细");

        //合并单元格
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 0, 0, 3);
        sheet.addMergedRegion(cellAddresses);

        // 设置单元格宽度
        sheet.setColumnWidth(3,20*256 );

        //设置字体样式
       /* HSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(Font.COLOR_RED);
        font.setItalic(true);
        font.setFontHeight((short)24);*/
        //font.setFontName("宋体");
        //设置字体样式
        Font font = workbook.createFont();
        font.setBold(true); //加粗
        font.setColor(Font.COLOR_RED);  //设置字体颜色
        font.setFontHeightInPoints((short) 24);  //设置字体大小
        font.setFontName("宋体");  //设置字体
        font.setItalic(true);  //设置斜体
        //创建样式对象
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setFont(font);
        //创建标题行
        HSSFRow row = sheet.createRow(1);
        row.setHeight((short) 900);
        String[] title = {"ID","名字","年龄","生日"};
        //处理单元格数据
        HSSFCell cell =null;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(cellStyle1);
        }


        //创建一个日期格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy年MM月dd日"));

        //处理行数据
        for (int i = 0; i < cats.size(); i++) {
            //创建行
            HSSFRow row1 = sheet.createRow(i + 2);
            //创建单元格
            HSSFCell cell1 = row1.createCell(0);
            cell1.setCellValue(cats.get(i).getId());//id
            row1.createCell(1).setCellValue(cats.get(i).getName());//name
            row1.createCell(2).setCellValue(cats.get(i).getAge());//age

            HSSFCell cell2 = row1.createCell(3);
            cell2.setCellValue(cats.get(i).getBirthday());
            cell2.setCellStyle(cellStyle);
            //row1.createCell(3).setCellValue(cats.get(i).getBirthday());//生日
        }
        //导出表
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("F://text1.xls"));
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导入表
    @Test
    public void test3(){
        try {
            //获取文档
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("F://text1.xls")));
            //获取工作簿
            HSSFSheet sheet = workbook.getSheet("宠物表");
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                //准备对象
                Cat cat = new Cat();
                //获取行
                HSSFRow row = sheet.getRow(i);
                //获取id
                cat.setId(row.getCell(0).getStringCellValue());
                //name
                cat.setName(row.getCell(1).getStringCellValue());
                //age
                double age = row.getCell(2).getNumericCellValue();
                cat.setAge((int)age);
                cat.setBirthday(row.getCell(3).getDateCellValue());
                System.out.println(cat);
            }
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}













