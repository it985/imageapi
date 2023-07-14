package com.random.image.controller;

/**
 * @author zx
 * @date 2022/8/22 17:02
 */
import java.io.*;
import java.util.ArrayList;

public class ReadFiledata {
    public static ArrayList<String> txt2String(File file) {
        StringBuilder result = new StringBuilder();
        ArrayList<String> readList = new ArrayList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
                result.append(System.lineSeparator() + s);
                readList.add(System.lineSeparator() + s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readList;
    }

}