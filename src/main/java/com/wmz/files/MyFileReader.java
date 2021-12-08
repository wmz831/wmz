package com.wmz.files;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @Author wangmingzhen
 * @Date 21/11/18 11:09
 */
public class MyFileReader {

    public static void main(String[] args) {
        MyFileReader.j8();
    }

    public static void j8(){
        Scanner sc = new Scanner(System.in);
        String str;
        do{
            str =  sc.nextLine();
            System.out.println(str);
        }while (!"bye".equals(str));
    }

    public void j5(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        try {
            do {
                str =  br.readLine();
                System.out.println(str);
            } while (!"bye".equals(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
