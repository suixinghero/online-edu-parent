package com.online.edu;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author xujin
 * @createtime 2020-04-28 18:10
 * @description
 */

public class PoiDemoTest {
    @Test
    public void testWriteExcel2007Version() throws IOException {
        // 创建新的Excel 工作簿
        Workbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值 Sheet1
        //Sheet sheet = workbook.createSheet();
        // 如要新建一名为"会员登录统计"的工作表，其语句为：
        Sheet sheet = workbook.createSheet("会员登录统计");
        // 创建行（row 1）
        Row row1 = sheet.createRow(0);
        // 创建单元格（col 1-1）
        Cell cell11 = row1.createCell(0);
        cell11.setCellValue("今日人数");
        // 创建单元格（col 1-2）
        Cell cell12 = row1.createCell(1);
        cell12.setCellValue(666);
        // 创建行（row 2）
        Row row2 = sheet.createRow(1);
        // 创建单元格（col 2-1）
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        //创建单元格（第三列）
        Cell cell22 = row2.createCell(1);
        String dateTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(dateTime);
        // 新建一输出文件流（注意：要先创建文件夹）
        FileOutputStream out = new FileOutputStream("C:\\Users\\lenovo\\Desktop\\excel2007version.xlsx");
        // 把相应的Excel 工作簿存盘
        workbook.write(out);
        // 操作结束，关闭文件
        out.close();
        System.out.println("文件生成成功");
    }

    @Test
    public void testRead2007ExcelVersion() throws IOException {
        InputStream is = new FileInputStream("C:\\Users\\lenovo\\Desktop\\excel2007version.xlsx");
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        // 读取第一行第一列
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        // 输出单元内容
        System.out.println(cell.getStringCellValue());
        // 操作结束，关闭文件
        is.close();
    }

}
