package com.iric.test;

import cn.hutool.core.util.ObjectUtil;

public class IntAndLongTest {
    public static void main(String[] args) {
        int code = 60008;
        long errorCode = 60008L;

        if (ObjectUtil.equal(Integer.toUnsignedLong(code),errorCode)){
            System.out.println("code相等");
        }
    }
}
