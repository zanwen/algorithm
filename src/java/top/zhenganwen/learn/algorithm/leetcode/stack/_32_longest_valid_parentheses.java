/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.stack;

/**
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 *
 * @author zhenganwen
 * @date 2019/11/28/028 9:47
 */
public class _32_longest_valid_parentheses {

    /**
     * 暴力法：O(n^2) </br>
     * 以每个字符为子串首字符，向后遍历所有字符，在遍历途中，遇到'('就将lefts自增，否则自减。
     * 如果某次修改后lefts为0，则更新一下最长有效子串长度；否则如果lefts小于0，则停止此次以该字符为首字符的最长有效子串的求解。 </br>
     * 执行用时 : 338 ms , 在所有 java 提交中击败了 5.06% 的用户 </br>
     * 内存消耗 : 37.3 MB , 在所有 java 提交中击败了 83.21% 的用户
     * 
     * @param s
     * @return
     */
    /*public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int max = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int lefts = 0;
            if (s.charAt(i) == ')')
                continue;
            else
                lefts = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == '(')
                    lefts++;
                else
                    lefts--;
                if (lefts == 0) {
                    max = Math.max(max, j - i + 1);
                } else if (lefts < 0) {
                    break;
                }
            }
        }
        return max;
    }*/

    /**
     * 借助栈和参照下标法：</br>
     * space: O(n) </br>
     * time: O(n) </br>
     * 开始时参照下标是-1，将参照下标入栈，开始遍历字符串。如果遇到'('，则将该字符的下标入栈；
     * 否则弹出栈顶下标，记录当前下标和栈顶下标（此时栈顶下标必定是参照下标）的差值并更新到{@code max}。
     * 如果弹出栈顶下标之后发现栈为空，则弹出的肯定是参照下标，此时应该将当前字符')'的下标入栈，
     * 作为新的参照下标（这意味着，由于该')'的加入，导致参照下标之后的字符到该')'之前的字符
     * 组成的有效子串的有效性被打破了。后续有效子串的长度应该以该')'所在下标作为参照)。</br>
     * </hr>
     * 执行用时 : 8 ms , 在所有 java 提交中击败了 45.59% 的用户 </br>
     * 内存消耗 : 37.8 MB , 在所有 java 提交中击败了 80.96% 的用户 </br>
     *
     * @param s
     * @return
     */
  /*  public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) return 0;
        int max = 0;
        Stack<Integer> stack = new Stack();
        // reference index
        stack.push(-1);
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.empty()){
                    // update reference index
                    stack.push(i);
                }else{
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }*/

    /**
     * 创建两个计数器lefts和rights，分别记录遍历遇到的'('和')'的次数。</br>
     * 从左到右遍历，每遍历一个后就比较lefts和rights，如果发现rights>lefts，则说明该')'的加入破坏了有效性，
     * 于是终止本次有效子串的计算，将lefts和rights置为0；如果rights=lefts，则说明当前有效子串的长度延长了，
     * 长度为rights*2，更新到{@code max}中。</br>
     * 值得注意的是，仅仅从左到右遍历一遍是不够的，因为我们忽略了从左到右遍历过程中对lefts>rights的判断，如果
     * 整个遍历过程中lefts一直是大于rights的，那么即使存在有效子串，我们也没有记录下来。</br>
     * 此时，我们反向遍历一下，即再从右到左遍历一遍，就能弥补对这种情况的处理，即lefts=rights时记录有效长度rights*2，
     * lefts>rights时，两者重置为0 </br>
     * </hr>
     * 执行用时 : 2 ms , 在所有 java 提交中击败了 99.39% 的用户 </br>
     * 内存消耗 : 36.8 MB , 在所有 java 提交中击败了 86.51% 的用户 </br>
     * 
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) return 0;
        int max = 0, lefts = 0, rights = 0;
        for(int i = 0 ; i < s.length() ; i++){
            if(s.charAt(i) == '(') lefts++;
            else rights++;
            if(rights > lefts) lefts = rights = 0;
            else if(lefts == rights) max = Math.max(max, rights*2);
        }
        lefts = rights = 0;
        for(int i = s.length() - 1 ; i >= 0 ; i--){
            if(s.charAt(i) == '(') lefts++;
            else rights++;
            if(lefts > rights) lefts = rights = 0;
            else if(lefts == rights) max = Math.max(max, rights*2);
        }
        return max;
    }
}
