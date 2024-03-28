package com.iric.map;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class LinkedHashMapExample {
    /**
     * @Author Yu.Xing
     * @Description LinkedHashMap有一个 removeEldestEntry(Map.Entry eldest)方法，通过覆盖这个方法，加入一定的条件，满足条件返回true。当put进新的值方法返回true时，便移除该map中最老的键和值。
     **/
    public static void main(String[] args) {
        final int MAX_S = 5;

        LinkedHashMap linkedHashMap = new LinkedHashMap() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_S;
            }
        };

        linkedHashMap.put("test1", "1");
        linkedHashMap.put("test2", "2");
        linkedHashMap.put("test3", "3");
        linkedHashMap.put("test4", "4");
        linkedHashMap.put("test5", "5");
        System.out.println(linkedHashMap);
        linkedHashMap.put("test6", "6");
        System.out.println(linkedHashMap);

        List<Long> longs = new ArrayList<>();
        longs.add(1L);
        longs.add(2L);
        log.info("longs:{}", longs);

    }
}
