package com.leave.Martind;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Excel_reader test = new Excel_reader();
        ArrayList<ArrayList<String>> arr = test.xlsx_reader("C:\\Users\\Martin_working\\Downloads\\LeaveName.xlsx",0,1,2);

        for(int i=0; i<arr.size(); i++){
            ArrayList<String> row = arr.get(i);
            for (int j=0; j<row.size(); j++){
                System.out.println(row.get(j)+" ");
            }
            System.out.println(" ");
        }

    }
}
