/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.forkjoin;

import java.util.Stack;

/**
 * @author zhenganwen
 * @date 2019/11/510:48
 *
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 *  () 得 1 分。
 *  AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 *  (A)得 2 * A 分，其中 A 是平衡括号字符串。
 *
 * 示例 1：
 *
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 *
 * 输入： "(())"
 * 输出： 2
 * 示例 3：
 *
 * 输入： "()()"
 * 输出： 2
 * 示例 4：
 *
 * 输入： "( ( ) ( ( ) ) )"
 * 输出： 6
 *
 *  提示： S 是平衡括号字符串，且只含有 ( 和 ) 。 2 <= S.length <= 50
 *
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/score-of-parentheses
 *      * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处
 */
public class _856_SCORE_OF_PARENTHESES {

    /**
     *
     *
     * ( ( ) ( ( ) ) )
     * 0 1 2 3 4 5 6 7
     *
     * @param S
     * @return
     */
    public int scoreOfParentheses(String S) {
//        return division(S, 0, S.length());
        return solveByStack(S);
    }

    /**
     * let's imagine we are the dot and need move from the left to right of {@code s} step by step,
     * as if the {@code s} is "(()(()))", we will be beginning from ".(()(()))" and current score is
     * 0; when we move front, we arrive "(.()(()))" and current score is still 0; repeat the step,
     * we will arrive "((.)(()))" and current score is 0; now we record the score of every step into
     * a stack, the stack will like this [0,0,0]; continue, we arrive "(().(()))", we come across a
     * right parentheses, so we pop the current score and double it and then plus it to last score,
     * but there is a special situation that the current score is 0 which we should plus 1 to last
     * score directly.
     * take the "(()(()))" for example, the process like:
     * 1. dot -> ".(()(()))"    score stack -> [0]
     * 2. dot -> "(.()(()))"    score stack -> [0, 0]
     * 3. dot -> "((.)(()))"    score stack -> [0, 0, 0]
     * 4. dot -> "(().(()))"    score stack -> [0, 0]   pop 0
     *                                                  pop is 0, so plus 1 to last -> [0, 1]
     * 5. dot -> "(()(.()))"    score stack -> [0, 1, 0]
     * 6. dot -> "(()((.)))"    score stack -> [0, 1, 0, 0]
     * 7. dot -> "(()(().))"    score stack -> [0, 1, 0]    pop 0
     *                                                      pop is 0, so plus 1 to last -> [0, 1, 1]
     * 8. dot -> "(()(()).)"    score stack -> [0, 1]   pop 1
     *                                                  pop isn't 0, so double it and plus it to last -> [0, 3]
     * 9. dot -> "(()(()))."    score stack -> [0]  pop 3
     *                                              pop isn't 0, so double it and plus it to last -> [6]
     *
     * complexity
     *      time:   O(N)    iterates N times
     *      space:  O(N)    stack will store N/2+1 numbers when the string is like "(((...)))"
     * @param s
     * @return
     */
    public int solveByStack(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                // c -> ')'
                Integer current = stack.pop();
                Integer last = stack.pop();
                stack.push(last + Math.max(current * 2, 1));
            }
        }
        return stack.pop();
    }

    /**
     * time complexity: O(N^2)
     *      (((...))) n pair of parentheses, need division  n/2 times, and need iterates n/2 times averagely for each division
     *
     * space complexity: O(N)
     *      each division need store s.length()/2 characters averagely, and the var e.g start, end, ans, bal can be ignored
     * @param s
     * @param start
     * @param end
     * @return
     */
    public int division(String s, int start, int end) {
        int ans = 0, bal = 0;
        for (int i = start; i < end; i++) {
            bal += s.charAt(i) == '(' ? 1 : -1;
            if (bal == 0) {
                if (i == start + 1) {
                    ans++;
                } else {
                    ans += 2 * (division(s, start + 1, i));
                }
                start = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new _856_SCORE_OF_PARENTHESES().scoreOfParentheses("(()(()))"));
        System.out.println(new _856_SCORE_OF_PARENTHESES().scoreOfParentheses("()"));
        System.out.println(new _856_SCORE_OF_PARENTHESES().scoreOfParentheses("()()"));
        System.out.println(new _856_SCORE_OF_PARENTHESES().scoreOfParentheses("(())()"));
    }
}
