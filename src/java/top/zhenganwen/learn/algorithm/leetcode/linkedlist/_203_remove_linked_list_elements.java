/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.linkedlist;

import top.zhenganwen.learn.algorithm.commons.ListNode;
import org.junit.Test;

/**
 * @author zhenganwen
 * @date 2019/11/110:51
 *       https://leetcode-cn.com/problems/remove-linked-list-elements/
 */
public class _203_remove_linked_list_elements {

    /**
     * 删除链表中等于给定值 val 的所有节点。 输入: 1->2->6->3->4->5->6, val = 6 输出: 1->2->3->4->5
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        while (head.val == val) {
            head = head.next;
            if (head == null) {
                return null;
            }
        }
        ListNode tmp = head;
        while (tmp.next != null) {
            if (tmp.next.val == val) {
                tmp.next = tmp.next.next;
            } else {
                tmp = tmp.next;
            }
        }
        return head;
    }

    @Test
    public void test() {
        System.out.println(removeElements(ListNode.SAMPLE_LIST, 3));
    }
}
