/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.collect.classic;

import top.zhenganwen.learn.algorithm.commons.Timer;

/**
 * @author zhenganwen
 * @date 2019/11/18:22 https://leetcode-cn.com/problems/fibonacci-number/
 */
public class _509_FIBONACCI_NUMBER {

    /**
     * @param N 0 ≤ N ≤ 30
     * @return
     */
    public int fib(int N) {
        return nonRecursive(N);
    }

    /**
     * O(2^n)
     * 
     * @param n
     * @return
     */
    public static int recursive(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return recursive(n - 1) + recursive(n - 2);
    }

    /**
     * O(n)
     * 
     * @param n
     * @return
     */
    public static int nonRecursive(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int first = 0, second = 1;
        for (int i = 0; i < n - 1; i++) {
            second += first;
            first = second - first;
        }
        return second;
    }

    public static void main(String[] args) {
        int n = 50;
        Timer.execute(() -> nonRecursive(n));
        Timer.execute(() -> recursive(n));
    }

}
