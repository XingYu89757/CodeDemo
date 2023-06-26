package com.iric;

import cn.hutool.core.collection.ListUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class TestCompletable {


    public static CompletableFuture<List<String>> getWearIdByCompletable(List<String> wearUserIds) {
        return CompletableFuture.supplyAsync(() -> {
            //获取当前线程
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + wearUserIds.toString());
            System.out.println(wearUserIds);
            // return diseasesRiskDataMapper.getDiseasesRiskDataByWearUserId(wearUserIds);
            return wearUserIds;
        });
    }

    //    多线程处理
    public static void main(String[] args) {


        List<String> strings = Arrays.asList("1", "2", "3", "4", "5", "6", "7");

        List<List<String>> lists = ListUtil.split(strings, 2);

        List<CompletableFuture<List<String>>> wearIdFuture = lists.stream()
                .map(item -> getWearIdByCompletable(item))
                .collect(Collectors.toList());

        CompletableFuture<Void> all = CompletableFuture.allOf(wearIdFuture.toArray(new CompletableFuture[wearIdFuture.size()]));
        all.join();

        List<String> collect = wearIdFuture.stream().map(CompletableFuture::join)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        System.out.println(collect);


//        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
//        List<Integer> list2 = Arrays.asList(6, 7, 8, 9, 10);
//
//        // 将每个List的处理逻辑包装成一个CompletableFuture对象
//        CompletableFuture<List<String>> future1 = CompletableFuture.supplyAsync(() -> processList(list1));
//        CompletableFuture<List<String>> future2 = CompletableFuture.supplyAsync(() -> processList(list2));
//
//        // 将所有CompletableFuture对象组合成一个新的CompletableFuture对象
//        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2);
//
//        // 等待所有子任务完成并获取结果
//        allFutures.join();
//
//        // 将所有子任务的结果合并成一个List
//        List<String> result = Stream.of(future1, future2)
//                .map(CompletableFuture::join)
//                .flatMap(List::stream)
//                .collect(Collectors.toList());

    }
}
