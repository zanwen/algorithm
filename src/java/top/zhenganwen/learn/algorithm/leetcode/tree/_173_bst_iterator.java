/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

import java.util.LinkedList;

/**
 * @author zhenganwen
 * @date 2019/11/23 16:40
 */
public class _173_bst_iterator {

    /**
     * 执行用时 : 23 ms , 在所有 java 提交中击败了 98.07% 的用户 </br>
     * 内存消耗 : 49.6 MB , 在所有 java 提交中击败了 99.47% 的用户
     */
    class BSTIterator {

        LinkedList<TreeNode> q = new LinkedList();

        public BSTIterator(TreeNode root) {
            inorder(root);
        }

        private void inorder(TreeNode root) {
            if (root == null)
                return;
            inorder(root.left);
            q.offer(root);
            inorder(root.right);
        }

        /** @return the next smallest number */
        public int next() {
            return q.poll().val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !q.isEmpty();
        }
    }

    /**
     * 执行用时 : 26 ms , 在所有 java 提交中击败了 89.46% 的用户 </br>
     * 内存消耗 : 49.7 MB , 在所有 java 提交中击败了 99.47% 的用户
     */
    /*
     * class BSTIterator { LinkedList<TreeNode> q = new LinkedList(); public
     * BSTIterator(TreeNode root) { if (root == null) return; Stack<TreeNode> stack
     * = new Stack(); while (root != null) { stack.push(root); root = root.left; if
     * (root == null) { while (root == null) { if (stack.isEmpty()) break; root =
     * stack.pop(); q.offer(root); root = root.right; } } } }
     *//** @return the next smallest number */
    /*
     * public int next() { return q.poll().val; }
     *//** @return whether we have a next smallest number *//*
                                                             * public boolean hasNext() { return !q.isEmpty(); } }
                                                             */
}
