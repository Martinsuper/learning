package com.leave.Martind;

import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        Excel_reader test = new Excel_reader();
//        Path excel = "C:\\Users\\Martin_working\\LeaveSystem.xlsx";
        String excel = "C:\\Users\\Martin_working\\Documents\\Tencent Files\\582791000\\FileRecv\\考勤\\考勤\\10003407.xls";
        String excel1 = "C:\\Users\\Martin_working\\Documents\\Tencent Files\\582791000\\FileRecv\\考勤\\考勤\\段路瑶.xlsx";
        ArrayList<ArrayList<String>> arr = test.reader(excel);


//        String persion = "2018"
        for (int i = 2; i < arr.size(); i++) {
            String mydate = arr.get(i).get(3);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date formatdate = format.parse(mydate);
            System.out.println(formatdate);
        }
    }
}
