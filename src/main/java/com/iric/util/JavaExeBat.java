package com.iric.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.Scanner;

public class JavaExeBat {
    public static void main(String[] args) throws IOException {
//        String[] cmd = {"cmd", "/C", "notepad.exe"};
//        //执行命令
//        System.out.println("start");
//        Runtime.getRuntime().exec(cmd);
//        System.out.println("end");

        // testBat();
        //getDir();
        autoDown();
    }

    //查看内存使用情况
    public static void getMemory() {
        System.out.println("total-memory:" + Runtime.getRuntime().totalMemory() / 1024 / 1024);
        System.out.println("max-memory:" + Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println("free-memory:" + Runtime.getRuntime().freeMemory() / 1024 / 1024);
    }

    //查看文件夹内容
    public static void getDir() {
        Process process;
        //需要指定参数一：命令位置；参数二：/c表示先执行第一个参数；参数三：你的命令。
        String[] cmd = {"cmd", "/C", "cd / && dir "};
        try {
            //执行命令
            process = Runtime.getRuntime().exec(cmd);
            //取得命令结果的输出流
            InputStream inputStream = process.getInputStream();
            //用一个读输出流类去读
            InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("GBK"));
            //用缓冲器读行
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            //结果输出流
            //直到读完为止
            System.out.println("输出");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            //错误使出流
            System.out.println("错误");
            //输出流
            InputStream errorStream = process.getErrorStream();
            InputStreamReader error = new InputStreamReader(errorStream, Charset.forName("GBK"));
            BufferedReader erbr = new BufferedReader(error);
            line = null;
            while ((line = erbr.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //执行本地脚本
    public static void testBat() {
        Process process;
        //本地脚本地址
        String cmd = "D:\\11\\testBat.bat";
        try {
            //执行命令
            process = Runtime.getRuntime().exec(cmd);
            //取得命令结果的输出流
            InputStream inputStream = process.getInputStream();
            //用一个读输出流类去读
            InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("GBK"));
            //用缓冲器读行
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            //知道读完为止
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //自动关机
    public static void autoDown() throws IOException {
        System.out.println("欢迎来到自动关机程序");
        //用于创建接收用户输入的变量input
        Scanner input = new Scanner(System.in);
        System.out.println("请输入电脑关机倒计时时间（s）");
        //获取用户输入信息
        String text = input.nextLine();
        //关机指令shutdown -s -t 时间
        Runtime.getRuntime().exec("shutdown -s -t " + text);
        System.out.println("你的电脑将在" + text + "s后关机");
        System.out.println("如想取消关机请输入1");
        //用于创建接收用户输入的变量input1
        Scanner input1 = new Scanner(System.in);
        //获取用户输入信息
        String text1 = input1.nextLine();
        //将String（字符串）型text1转换为int整型b
        int b = Integer.parseInt(text1);
        //判定b是否等于1
        if (b == 1) {
            //取消关机指令
            Runtime.getRuntime().exec("shutdown -a");
            System.out.println("关机已经取消");
        }
    }

}
