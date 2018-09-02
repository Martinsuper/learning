package com.leave.Martind;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

import static org.apache.poi.ss.usermodel.Cell.*;

public class Excel_reader {

    public ArrayList<ArrayList<String>> reader(String path) throws IOException {
        File file = new File(path);
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
//        System.out.println();
        if(suffix.equals("xls")){
            return xls_reader(path);
        }else if(suffix.equals("xlsx")){
           return xlsx_reader(path);
        }else {
            System.out.println("error");
            return null;
        }

    }

    private ArrayList<ArrayList<String>> xls_reader(String xls_path) throws IOException {
        InputStream inputStream = new FileInputStream(new File(xls_path));
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        HSSFSheet hssfSheet = null;
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        for (int i = 0; i < hssfWorkbook.getNumberOfSheets(); i++) {
            hssfSheet = hssfWorkbook.getSheetAt(i);
            for (int j = 0; j < hssfSheet.getLastRowNum() + 1; j++) {
                ArrayList<String> arrayList = new ArrayList<>();
                HSSFRow row = hssfSheet.getRow(j);
                if (row != null) {
                    for (int k = 0; k < row.getLastCellNum(); k++) {
//                        System.out.println(row.getCell(k));
                        arrayList.add(getValue(row.getCell(k)));
                    }
                }
                arrayLists.add(arrayList);
            }

        }
        return arrayLists;
    }

    private ArrayList<ArrayList<String>> xlsx_reader(String xlsx_path) throws IOException {
        InputStream inputStream = new FileInputStream(new File(xlsx_path));
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
        XSSFSheet xssfSheet = null;
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        for (int i = 0; i < xssfWorkbook.getNumberOfSheets();i++){
            xssfSheet = xssfWorkbook.getSheetAt(i);
            for (int j=0; j< xssfSheet.getLastRowNum()+1;j++){
                ArrayList<String> arrayList = new ArrayList<>();
                XSSFRow row = xssfSheet.getRow(j);
                if(row!=null){
                    for(int k =0;k<row.getLastCellNum();k++){
                        arrayList.add(getValue(row.getCell(k)));
                    }
                }
                arrayLists.add(arrayList);
            }
        }
        return arrayLists;
    }


    //    public ArrayList<ArrayList<String>> xlsx_reader(String xlsx_path, int... args) throws IOException {
//
//        InputStream inputStream = new FileInputStream(new File(xlsx_path));
//        XSSFWorkbook xssfWorkbook = new XSSFWorkbook((inputStream));
//        if (xssfWorkbook == null) {
//            System.out.println("哇！没有读取到文件，你再找找看路径对不对~");
//            return null;
//        }
//
//        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
//        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
//            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
//            if (xssfSheet == null) {
//                continue;
//            }
//            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
//                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
//                if (xssfRow == null) continue;
//                ArrayList<String> arrayList = new ArrayList<>();
//                for (int columnNum = 0; columnNum < args.length; columnNum++) {
//                    XSSFCell xssfCell = xssfRow.getCell(args[columnNum]);
//                    arrayList.add(getValue(xssfCell));
//                }
//            arrayLists.add(arrayList);
//            }
//        }
//        return arrayLists;
//
//    }
//
    private static String getValue(XSSFCell xssfCell) {

        int cellType = xssfCell.getCellType();
        switch (cellType) {
            case CELL_TYPE_NUMERIC:
//                System.out.println(getType(xssfCell.));
                return String.valueOf((int) xssfCell.getNumericCellValue());
            case CELL_TYPE_STRING:
                return String.valueOf(xssfCell.getStringCellValue());
            case CELL_TYPE_BOOLEAN:
                return String.valueOf(xssfCell.getBooleanCellValue());
            default:
                return "";
        }
    }

    private static String getValue(HSSFCell hssfCell) {

        int cellType = hssfCell.getCellType();
        switch (cellType) {
            case CELL_TYPE_NUMERIC:
                return String.valueOf((int) hssfCell.getNumericCellValue());
            case CELL_TYPE_STRING:
                return String.valueOf(hssfCell.getStringCellValue());
            case CELL_TYPE_BOOLEAN:
                return String.valueOf(hssfCell.getBooleanCellValue());
            default:
                return " ";
        }
    }


}