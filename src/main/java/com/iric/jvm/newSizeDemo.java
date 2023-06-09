package com.iric.jvm;

import cn.hutool.core.util.ByteUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试新生代配置
 * // 1. -Xmx20m -Xms20m -Xmn2m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
 * // 2.模拟老年代担保配置  -Xmx20m -Xms20m -Xmn2m -XX:NewRatio=2 -XX:+PrintGCDetails
 */
public class newSizeDemo {

    //模拟内存溢出
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            byte[] b = new byte[2 * 1024 * 1024];
            List<byte[]> list = Arrays.asList(b);
            result.addAll(list);
            Thread.sleep(200);
        }
        System.out.println("end");
    }

//    public static void main(String[] args) {
//        // byte[] b = null;
//        for (int i = 0; i < 30; i++) {
//            byte[]  b = new byte[3 * 1024 * 1024];
//        }
//    }
}
