package com.leave.Martind;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class test_learn {
    public static void main(String[] args){
        File file = new File("Main.java");
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf('.')+1);
        if(suffix.equals("java")){
            System.out.println("success");
        }
    }
}
