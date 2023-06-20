package com.iric.mvcParam.model;

import com.baomidou.mybatisplus.generator.config.INameConvert;
import lombok.Data;

/**
 * @author yu.xing
 */
@Data
public class Person {
    /**
     * id
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
}
