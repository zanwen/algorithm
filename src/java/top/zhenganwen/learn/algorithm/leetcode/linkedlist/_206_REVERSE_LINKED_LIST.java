/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.linkedlist;

import top.zhenganwen.learn.algorithm.leetcode.commons.ListNode;
import org.junit.Test;

/**
 * @author zhenganwen
 * @date 2019/11/19:06
 * <p>
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class _206_REVERSE_LINKED_LIST {

    public ListNode reverseList(ListNode head) {
        return reverseListRecur(head);
    }

    public ListNode reverseListRecur(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecur(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseListNonRecur(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null, tmp;
        while (head != null) {
            tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    @Test
    public void testRecur() {
        System.out.println(ListNode.SAMPLE_LIST);
        ListNode newHead = reverseListRecur(ListNode.SAMPLE_LIST);
        System.out.println(newHead);
    }

    @Test
    public void testNonRecur() {
        System.out.println(ListNode.SAMPLE_LIST);
        ListNode newHead = reverseListNonRecur(ListNode.SAMPLE_LIST);
        System.out.println(newHead);
    }
}