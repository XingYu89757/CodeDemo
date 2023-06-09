package com.iric.transaction.controller;

import com.iric.transaction.model.Goods;
import com.iric.transaction.service.GoodService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private GoodService goodService;

    @PostMapping
    @Transactional(rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
    public void testSave() {
        Goods goods = new Goods();
        goods.setGoodsName("testGoodName1");
        goodService.saveGoods(goods);
        //测试回滚
        throw new RuntimeException("测试回滚");
    }

    @PostMapping("/test2")
    @Transactional(rollbackFor = RuntimeException.class, noRollbackFor = Exception.class)
    public void testSave2() {
        Goods goods = new Goods();
        goods.setGoodsName("testGoodName2");
        goodService.saveGoods(goods);
        //测试回滚
        throw new NullPointerException("测试回滚");
    }

    @PostMapping("/test3")
    @Transactional(rollbackFor = Exception.class)
    public void testSave3() {
        Goods goods = new Goods();
        goods.setGoodsName("testGoodName3");
        goodService.saveGoods(goods);
        //测试回滚
        throw new RuntimeException("测试回滚");
    }

}
