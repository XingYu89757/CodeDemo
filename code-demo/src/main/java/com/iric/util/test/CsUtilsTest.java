package com.iric.util.test;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharsetUtil;
import com.iric.transaction.model.Goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CsUtilsTest {
    public static void main(String[] args) {

        List<List<String>> goodsList = new ArrayList<>();

        ArrayList<String> objects = new ArrayList<>();
        objects.add("1");
        objects.add("2");
        objects.add("3");

        goodsList.add(objects);

        String filePath = "D:\\test.csv";
        CsvWriter writer = CsvUtil.getWriter(filePath, CharsetUtil.CHARSET_UTF_8);
        writer.write(goodsList);
    }



}
