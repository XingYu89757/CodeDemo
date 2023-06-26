package com.iric.mvcParam.model;

import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    /**
     * 地址
     */

   @JsonAlias("ADDRESS")
    private String address;
}
