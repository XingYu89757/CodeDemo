package com.iric.jdk8_demo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.iric.abstractDemo.Cat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * stream排序demo
 * List<类> rankList = new ArrayList<>(); 表明某个集合
 * <p>
 * //返回 对象集合以类属性一升序排序
 * <p>
 * rankList.stream().sorted(Comparator.comparing(类::属性一));
 * <p>
 * //返回 对象集合以类属性一降序排序 注意两种写法
 * <p>
 * rankList.stream().sorted(Comparator.comparing(类::属性一).reversed()); //先以属性一升序,而后对结果集进行属性一降序
 * <p>
 * rankList.stream().sorted(Comparator.comparing(类::属性一, Comparator.reverseOrder())); //以属性一降序
 * <p>
 * //返回 对象集合以类属性一升序 属性二升序
 * <p>
 * rankList.stream().sorted(Comparator.comparing(类::属性一).thenComparing(类::属性二));
 * <p>
 * //返回 对象集合以类属性一降序 属性二升序 注意两种写法
 * <p>
 * rankList.stream().sorted(Comparator.comparing(类::属性一).reversed().thenComparing(类::属性二));//先以属性一升序,升序结果进行属性一降序,再进行属性二升序
 * <p>
 * rankList.stream().sorted(Comparator.comparing(类::属性一,Comparator.reverseOrder()).thenComparing(类::属性二));//先以属性一降序,再进行属性二升序
 * <p>
 * //返回 对象集合以类属性一降序 属性二降序 注意两种写法
 * <p>
 * rankList.stream().sorted(Comparator.comparing(类::属性一).reversed().thenComparing(类::属性二,Comparator.reverseOrder()));//先以属性一升序,升序结果进行属性一降序,再进行属性二降序
 * <p>
 * rankList.stream().sorted(Comparator.comparing(类::属性一,Comparator.reverseOrder()).thenComparing(类::属性二,Comparator.reverseOrder()));//先以属性一降序,再进行属性二降序
 * <p>
 * //返回 对象集合以类属性一升序 属性二降序 注意两种写法
 * <p>
 * rankList.stream().sorted(Comparator.comparing(类::属性一).reversed().thenComparing(类::属性二).reversed());//先以属性一升序,升序结果进行属性一降序,再进行属性二升序,结果进行属性一降序属性二降序
 * <p>
 * rankList.stream().sorted(Comparator.comparing(类::属性一).thenComparing(类::属性二,Comparator.reverseOrder()));//先以属性一升序,再进行属性二降序
 */
@Data
@Slf4j
public class StreamSortDemo {

    public static void main(String[] args) {

        Cart cart = new Cart();
        cart.setName("tom");
        cart.setStatus(1);
        cart.setCreateTime(DateUtil.lastMonth());

        Cart cart1 = new Cart();
        cart1.setName("jerry");
        cart1.setStatus(2);
        cart1.setCreateTime(DateUtil.lastWeek());

        DateTime yesterday = DateUtil.yesterday();
        Cart cart2 = new Cart();
        cart2.setName("jack");
        cart2.setStatus(1);
        cart2.setCreateTime(yesterday);

        Cart cart3 = new Cart();
        cart3.setName("lily");
        cart3.setStatus(2);
        cart3.setCreateTime(yesterday);

        ArrayList<Cart> carts = new ArrayList<>();
        carts.add(cart);
        carts.add(cart1);
        carts.add(cart2);
        carts.add(cart3);

        // 状态>创建时间
        List<Cart> collect = carts.stream().sorted(Comparator.comparing(Cart::getStatus).thenComparing(Cart::getCreateTime, Comparator.reverseOrder())).collect(Collectors.toList());
        log.info("排序后内容为：{}", collect);

    }

    @Data
    static class Cart {
        String name;
        int status;
        Date createTime;
    }
}
