package com.iric.mvcParam.model;

import com.baomidou.mybatisplus.generator.config.INameConvert;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yu.xing
 */
@Data
public class Person implements Serializable {
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
