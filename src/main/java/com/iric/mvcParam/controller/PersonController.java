package com.iric.mvcParam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author yu.xing
 */
@Slf4j
@RestController
@RequestMapping("api/person")
public class PersonController {

    /**
     *GET http://localhost:8080/api/person/getPerson?id=1
     */
    @GetMapping("/getPerson")
    public String getPerson(@RequestParam(value = "id",required = false,defaultValue = "2") Integer id) {
        log.info("id:{}", id);
        return "";
    }

    /**
     * POST http://localhost:8080/api/person/postPerson
     * Content-Type: application/x-www-form-urlencoded
     *
     * id=1
     */
    @PostMapping("/postPerson")
    public String postPerson(@RequestParam("id") String id) {
        log.info("id:{}", id);
        return "";
    }

    /**
     *POST http://localhost:8080/api/person/postPersonMap
     * Content-Type: application/x-www-form-urlencoded
     *
     * id=1
     */
    @PostMapping("/postPersonMap")
    public String postPersonMap(@RequestParam HashMap<String,String> param) {
        log.info("param:{}",param.toString());
        return "";
    }

    /**
     *可以通过两种方式传递list
     * 第一种传递到param中：
     * POST http://localhost:8080/api/person/postPersonList?param=1,2,3,4
     *
     * 第二种传递到form表单中：
     * POST http://localhost:8080/api/person/postPersonList
     * Content-Type: application/x-www-form-urlencoded
     *
     * param=1,2,3,4,5
     */
    @PostMapping("/postPersonList")
    public String postPersonList(@RequestParam List<String> param) {
        log.info("param:{}",param);
        return "";
    }

}
