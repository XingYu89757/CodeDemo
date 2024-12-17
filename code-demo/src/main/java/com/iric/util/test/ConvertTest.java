package com.iric.util.test;

import cn.hutool.core.convert.Convert;

import java.util.Objects;

public class ConvertTest {
    public static void main(String[] args) {
        String a = "鄂尔多斯市中心医院（内蒙古自治区超声影像研究所）";
        String b = "鄂尔多斯市中心医院(内蒙古自治区超声影像研究所)";


        //结果为："１２３４５６７８９"
        String sbc = Convert.toSBC(a);
        String sbc1 = Convert.toSBC(b);
        System.out.println(a);
        System.out.println(sbc);

        if (!Objects.equals(a, b)) {
            System.out.println("a和b不相同");
        }
        if (!Objects.equals(sbc, sbc1)) {
            System.out.println("sbc和sbc1不相同");
        }
    }
}
