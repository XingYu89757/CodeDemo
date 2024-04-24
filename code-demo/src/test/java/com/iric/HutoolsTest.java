package com.iric;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HutoolsTest {
    public static void main(String[] args) {
        BigDecimal price = new BigDecimal("1.23");
        BigDecimal round = NumberUtil.round(NumberUtil.mul(price, 4), 8);
        System.out.println(round.stripTrailingZeros());

        BigDecimal rebate = new BigDecimal("80");
        System.out.println(rebate.stripTrailingZeros());
        System.out.println("=======================");
        BigDecimal value = new BigDecimal("81.00");
        BigDecimal result = value.divide(new BigDecimal("10"), 0, RoundingMode.DOWN);
        System.out.println("Result: " + result);


        BigDecimal value1 = new BigDecimal("80.00");
        BigDecimal value2 = new BigDecimal("81.00");

        if (value1.remainder(BigDecimal.TEN).compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("是整数");
        }
        System.out.println("Result for value1: " + convertBigDecimal(value1));
        System.out.println("Result for value2: " + convertBigDecimal(value2));
    }

    public static int convertBigDecimal(BigDecimal value) {
        if (value.scale() <= 0) { // 检查是否为整数
            return value.intValue();
        } else {
            BigDecimal integerValue = value.setScale(0, BigDecimal.ROUND_DOWN); // 将其转换为整数
            if (integerValue.compareTo(value) == 0) { // 如果转换后等于原始值，则返回整数
                return integerValue.intValue();
            } else {
                return value.intValue(); // 否则返回原始值
            }
        }
    }
}
