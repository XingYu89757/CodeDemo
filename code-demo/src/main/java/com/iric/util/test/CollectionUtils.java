package com.iric.util.test;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CollectionUtils {
    public static void main(String[] args) {
        List<LinkedHashMap<String, String>> listAllImport = new ArrayList<>();

        // 假设这里有一些初始化的代码

        // 添加一些示例数据
        LinkedHashMap<String, String> data1 = new LinkedHashMap<>();
        data1.put("0", "2024");
        data1.put("1", "基础工资");
        data1.put("2", "10001");
        data1.put("3", "事业三部（呼吸）长沙区");
        data1.put("4", "14479");
        data1.put("5", "全产品");
        data1.put("6", "QCP888");
        data1.put("7", "办公租赁");
        data1.put("8", "XM0001");
        data1.put("9", "10000");
        data1.put("10", "10000");
        data1.put("11", "10000");
        data1.put("12", "10000");
        data1.put("13", "10000");
        data1.put("14", "10000");
        data1.put("15", "10000");
        data1.put("16", "10000");
        data1.put("17", "10000");
        data1.put("18", "10000");
        data1.put("19", "10000");
        data1.put("20", "10000");

        listAllImport.add(data1);
        listAllImport.add(new LinkedHashMap<>());
        listAllImport.add(new LinkedHashMap<>());
        listAllImport.add(new LinkedHashMap<>());
        listAllImport.add(new LinkedHashMap<>());
        listAllImport.add(new LinkedHashMap<>());
        listAllImport.add(new LinkedHashMap<>());
        listAllImport.add(new LinkedHashMap<>());
        listAllImport.add(new LinkedHashMap<>());
        listAllImport.add(new LinkedHashMap<>());

        // 使用 Stream API 过滤掉空的 LinkedHashMap
        List<LinkedHashMap<String, String>> filteredList = listAllImport.stream()
                .filter(map -> !map.isEmpty()) // 过滤条件：非空的 LinkedHashMap
                .collect(Collectors.toList());

        // 输出过滤后的列表
        System.out.println(filteredList);
    }
}
