package com.iric.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class IntToBitUtils {
    /**
     * 整理筛选条件
     *
     * @param bizCodes 业务码
     * @return 条件集合
     */
    public static Integer sumIntToBit(List<Integer> bizCodes) {
        if (CollectionUtils.isEmpty(bizCodes)) {
            return 0;
        }

        int result = 0;
        for (Integer feature : bizCodes) {
            result = result | feature;
        }

        return result;
    }

    /**
     * 拆分二进制条件
     */
    public static Set<Integer> splitBitToIntSet(Integer bizCode) {
        Set<Integer> result = new HashSet<>();

        if (Objects.isNull(bizCode) || bizCode == 0) {
            result.add(0);
            return result;
        }

        String[] bitStr = Integer.toBinaryString(bizCode).split("");
        int length = bitStr.length;
        for (int i = 0; i < length; i++) {
            int i1 = Integer.parseInt(bitStr[i]);
            result.add(i1 << (length - i - 1));
        }

        return result;
    }

    public static void main(String[] args) {
        Set<Integer> integers = splitBitToIntSet(15);
        System.out.println(integers);
    }
}
