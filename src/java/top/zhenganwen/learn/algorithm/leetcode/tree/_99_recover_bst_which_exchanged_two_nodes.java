/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * @author zhenganwen
 * @date 2019/11/23 17:25
 */
public class _99_recover_bst_which_exchanged_two_nodes {

    /**
     * </br>
     * Morris遍历是一种空间复杂度为O(1)的二叉树遍历方式 </br>
     * 遍历指针p从root开始，非空循环 </br>
     * 如果p.left == null，那么就访问p，并将p右移 </br>
     * 否则，获取p的前驱节点pre，如果pre.left==null，就将其指向p，并将p左移；如果pre.left=p，说明p的左子树已经遍历完了，这时需要访问p，并将Morris辅助指针pre.right恢复为null，最后将p右移同样的逻辑遍历右子树
     * </br>
     * 执行用时 : 3 ms , 在所有 java 提交中击败了 83.99% 的用户 </br>
     * 内存消耗 : 38.6 MB , 在所有 java 提交中击败了 98.80% 的用户
     * 
     * @param root
     */
    public void recoverTree(TreeNode root) {
        if (root == null)
            return;
        TreeNode p = root, last = null, n1 = null, n2 = null;
        while (p != null) {
            if (p.left != null) {
                TreeNode pre = predecessor(p);
                if (pre.right == null) {
                    pre.right = p;
                    p = p.left;
                } else {
                    if (last != null && last.val > p.val) {
                        if (n1 == null)
                            n1 = last;
                        n2 = p;
                    }
                    last = p;
                    pre.right = null;
                    p = p.right;
                }
            } else {
                if (last != null && last.val > p.val) {
                    if (n1 == null)
                        n1 = last;
                    n2 = p;
                }
                last = p;
                p = p.right;
            }
        }

        if (n1 != null && n2 != null) {
            n1.val ^= n2.val;
            n2.val ^= n1.val;
            n1.val ^= n2.val;
        }
    }

    private TreeNode predecessor(TreeNode node) {
        if (node.left == null)
            return null;
        TreeNode p = node.left;
        while (p.right != null && p.right != node)
            p = p.right;
        return p;
    }

    /**
     * space: O(n) </br>
     * 第一个问题节点是中序遍历中第一次遇到的逆序的两个节点的前一个 </br>
     * 第二个问题节点是中序遍历中第二次遇到的逆序的两个节点的后一个 </br>
     * 注意：这里有个特殊情况，即交换的两个节点可能是中序遍历中相邻的两个节点 </br>
     * 执行用时 : 5 ms , 在所有 java 提交中击败了 45.28% 的用户 </br>
     * 内存消耗 : 37.4 MB , 在所有 java 提交中击败了 98.80% 的用户
     * 
     * @param root
     */
    /*
     * public void recoverTree(TreeNode root) { if (root == null) return;
     * Stack<TreeNode> stack = new Stack(); TreeNode last = null, n1 = null, n2 =
     * null; while (root != null) { stack.push(root); root = root.left; if (root ==
     * null) { while (root == null) { if (stack.isEmpty()) break; root =
     * stack.pop(); if (last != null && last.val > root.val) { if (n1 == null) { n1
     * = last; n2 = root; } else { n2 = root; } } last = root; root = root.right; }
     * } } if (n1 != null && n2 != null) { n1.val ^= n2.val; n2.val ^= n1.val;
     * n1.val ^= n2.val; } }
     */
}
