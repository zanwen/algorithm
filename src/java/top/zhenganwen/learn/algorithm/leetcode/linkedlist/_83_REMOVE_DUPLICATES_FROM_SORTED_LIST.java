/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.linkedlist;

import top.zhenganwen.learn.algorithm.commons.ListNode;

/**
 * @author zhenganwen
 * @date 2019/11/111:32
 *       https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class _83_REMOVE_DUPLICATES_FROM_SORTED_LIST {

    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     * 
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode t = head;
        while (t.next != null) {
            if (t.val == t.next.val) {
                t.next = t.next.next;
            } else {
                t = t.next;
            }
        }
        return head;
    }
}
