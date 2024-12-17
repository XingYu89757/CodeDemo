package com.iric.mvcParam.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.iric.mvcParam.model.Person;
import com.iric.util.DeepCopyUtil;
import com.iric.util.PojoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author yu.xing
 */
@Slf4j
@RestController
@RequestMapping("api/person")
public class PersonController {


    /**
     * GET http://localhost:8080/api/person/getPersonById
     */
    @GetMapping("/getPersonById")
    public String getPersonById(Integer id) {
        log.info("id:{}", id);
        return "";
    }

    /**
     * GET http://localhost:8080/api/person/getPerson?id=1
     */
    @GetMapping("/getPerson")
    public String getPerson(@RequestParam(value = "id", required = false, defaultValue = "2") Integer id) {
        log.info("id:{}", id);
        return "";
    }

    /**
     * POST http://localhost:8080/api/person/postPerson
     * Content-Type: application/x-www-form-urlencoded
     * <p>
     * id=1
     */
    @PostMapping("/postPerson")
    public String postPerson(Person person) {
        log.info("person:{}", person);
        return "";
    }

    /**
     * POST http://localhost:8080/api/person/postPersonMap
     * Content-Type: application/x-www-form-urlencoded
     * <p>
     * id=1
     */
    @PostMapping("/postPersonMap")
    public String postPersonMap(@RequestParam HashMap<String, String> param) {
        log.info("param:{}", param.toString());
        return "";
    }

    /**
     * 可以通过两种方式传递list
     * 第一种传递到param中：
     * POST http://localhost:8080/api/person/postPersonList?param=1,2,3,4
     * <p>
     * 第二种传递到form表单中：
     * POST http://localhost:8080/api/person/postPersonList
     * Content-Type: application/x-www-form-urlencoded
     * <p>
     * param=1,2,3,4,5
     */
    @PostMapping("/postPersonList")
    public String postPersonList(@RequestParam List<String> param) {
        log.info("param:{}", param);
        return "";
    }

    /**
     * HttpEntity 和@RequestBody使用相同，多了一个header
     *
     * @param entity
     */
    @PostMapping("/accounts")
    public void handle(HttpEntity<Person> entity) {
        log.info("请求头部为：{}，请求体为：{}", entity.getHeaders(), entity.getBody());
    }

    /**
     * ### test postPersonByJson
     * POST http://localhost:8080/api/person/postPersonByJson
     * Content-Type: application/json
     * <p>
     * {
     * "id": 1,
     * "name": "tom",
     * "age": 12,
     * "ADDRESS": "beijing"
     * }
     */
    @PostMapping("/postPersonByJson")
    public Person postPersonByJson(@RequestBody Person person) {
        log.info("person:{}", person);
        return person;
    }

    /**
     * 接收header参数
     */
    @GetMapping("/getPersonHeader")
    public void getPersonHeader(@RequestHeader("Accept-Encoding") String encoding,
                                @RequestHeader("Keep-Alive") long keepAlive) {
        log.info("Accept-Encoding:{},Keep-Alive:{}", encoding, keepAlive);
    }

    /**
     * map接收header参数
     * 使用map接收key值会变成小写
     */
    @GetMapping("/getPersonHeaderByMap")
    public void getPersonHeader(@RequestHeader Map<String, String> param) {
        param.forEach((key, value) -> {
            log.info("key:{},value:{}", key, value);
        });
        log.info("Accept-Encoding:{},Keep-Alive:{}", param.get("accept-encoding"), param.get("keep-alive"));
    }

    /**
     * 获取url中的参数
     */
    @GetMapping("/{id}")
    public void getPersonById(@PathVariable String id) {
        log.info("id:{}", id);
    }

    /**
     * 通过正则表达式获取url中name，version，ext等信息
     */
    @GetMapping("/express/{name:[a-z-]+}-{version:\\d\\.\\d\\.\\d}{ext:\\.[a-z]+}")
    public void handle(@PathVariable String name, @PathVariable String version, @PathVariable String ext) {
        log.info("name：{},version:{},ext:{}", name, version, ext);
    }


    @PostMapping("/postPersonByModel/{id}")
    public void postPersonByModel(@ModelAttribute Person person) {
        log.info("person:{}", person);
    }

    @GetMapping("/getPersonByModel/{id}")
    public void getPersonByModel(@ModelAttribute Person person) {
        log.info("person:{}", person);
    }

    @ModelAttribute
    public void userInfo(ModelMap modelMap, HttpServletRequest request) {
        log.info("执行modelAttribute方法");
        //模拟通过token获取userId的过程
        String id = request.getHeader("token").toString();
        modelMap.addAttribute("userId", id);
    }

    @PostMapping("/testModelAttribute")
    public void testModelAttribute(@ModelAttribute("userId") String userId, Person person) {
        log.info("userId:{}", userId);
    }

    public static void main(String[] args) {
        Person tom = new Person();
        tom.setName("tom");
        tom.setId(123);
        tom.setAge(12);
        Person jerry = new Person();
        jerry.setName("jerry");
        jerry.setId(1234);
        jerry.setAge(123);
        List<Person> personList = new ArrayList<>();

        personList.add(tom);
        personList.add(jerry);
        log.info("personList:{}",personList);
        // List<Person> map = PojoUtils.map(personList, Person.class);
        List<Person> newPersonList = new ArrayList<>();
        List<Person> personList1 = DeepCopyUtil.deepCopy(personList);
        Collections.copy(personList, newPersonList);
        // List<Person> map = ObjectUtil.clone(personList);
        Person person = personList.get(0);
        log.info("log1:{}",person);
        person.setName("jack");
        log.info("personList:{}",personList);
        log.info("newPersonList:{}",personList1);


    }

}
