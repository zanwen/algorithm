/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.commons;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

/**
 * @author zhenganwen
 * @date 2019/11/18:42
 */
public class Timer {

    public static void execute(Callable callable){
        LocalDateTime begin = LocalDateTime.now();
        System.out.println(String.format("开始时间: %s", begin));
        Object result = null;
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LocalDateTime end = LocalDateTime.now();
        System.out.println(String.format("结束时间: %s, 执行结果: %s", end, result));
        System.out.println(String.format("总耗时: %ds", (end.getSecond() - begin.getSecond())));
        System.out.println();
    }
}
