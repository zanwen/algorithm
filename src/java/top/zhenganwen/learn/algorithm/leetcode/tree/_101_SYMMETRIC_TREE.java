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
 * https://leetcode-cn.com/problems/symmetric-tree/
 * 
 * @author zhenganwen
 * @date 2019/11/21 16:13
 */
public class _101_SYMMETRIC_TREE {

    /**
     * </br>
     * 可以借助两个队列遍历时分别按照 根-左-右、根-右-左 的顺序入队，再遍历访问时对当前节点进行比较
     * </hr>
     * 执行用时 : 1 ms , 在所有 java 提交中击败了 87.84% 的用户 </br>
     * 内存消耗 : 35.5 MB , 在所有 java 提交中击败了 80.54% 的用户
     * 
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        LinkedList<TreeNode> q1 = new LinkedList(), q2 = new LinkedList();
        q1.offer(root);
        q2.offer(root);
        while (!q1.isEmpty()) {
            TreeNode n1 = q1.poll(), n2 = q2.poll();
            if (!nodeEquals(n1, n2))
                return false;
            if (n1 != null) {
                q1.offer(n1.left);
                q1.offer(n1.right);
            }
            if (n2 != null) {
                q2.offer(n2.right);
                q2.offer(n2.left);
            }
        }
        return true;
    }

    public boolean nodeEquals(TreeNode n1, TreeNode n2) {
        return (n1 == null && n2 == null) || (n1 != null && n2 != null && n1.val == n2.val);
    }

    /**
     * </br>
     * 一棵树是中心轴对称的，那么该树的左子树和右子树就是镜像的 </br>
     * 那么问题就可以转化为可递归的过程：判断两个树是否关于轴对称
     * <ul>
     * <li>左子节点</li>
     * <li>左子节点的左子树和右子节点的右子树是<span>中心轴对称的</span></li>
     * <li>左子节点的右子树和右子节点的左子树是<span>中心轴对称的</span></li>
     * </ul>
     * </hr>
     * 执行用时 : 1 ms , 在所有 java 提交中击败了 87.84% 的用户 </br>
     * 内存消耗 : 38.2 MB , 在所有 java 提交中击败了 60.95% 的用户
     * 
     * @param root
     * @return
     */
    /*
     * public boolean isSymmetric(TreeNode root) { if(root == null) return true;
     * return isMirror(root.left, root.right); } private boolean isMirror(TreeNode
     * r1, TreeNode r2){ if(r1 == null && r2 == null) return true; if((r1 != null &&
     * r2 == null) || (r1 == null && r2 != null) || r1.val != r2.val) return false;
     * return isMirror(r1.left, r2.right) && isMirror(r1.right, r2.left); }
     */

    /**
     * </br>
     * 镜像意味着左子树的 根-左-右 遍历序列 和右子树的 跟-右-左 遍历序列 相同 (以先序遍历为例) </br>
     * 注意：考虑左右有一个为空的情况，这种情况是先序遍历无法区分开的，可以使用null进行填充
     * </hr>
     * 执行用时 : 3 ms , 在所有 java 提交中击败了 8.26% 的用户 </br>
     * 内存消耗 : 36 MB , 在所有 java 提交中击败了 79.43% 的用户
     * 
     * @param root
     * @return
     */
    /*
     * public boolean isSymmetric(TreeNode root) { if(root == null || (root.left ==
     * null && root.right == null)) return true; if(nodeNotEquals(root.left,
     * root.right)) return false; Stack<TreeNode> s1 = new Stack();
     * LinkedList<TreeNode> q = new LinkedList(); s1.push(root.left);
     * while(!s1.isEmpty()){ TreeNode node = s1.pop(); q.offer(node); if(node !=
     * null){ s1.push(node.right); s1.push(node.left); } } s1.push(root.right);
     * while(!s1.isEmpty()){ TreeNode node = s1.pop(); if(q.isEmpty() ||
     * nodeNotEquals(node, q.poll())) return false; if(node != null){
     * s1.push(node.left); s1.push(node.right); } } return q.isEmpty(); }
     */

    private boolean nodeNotEquals(TreeNode n1, TreeNode n2) {
        if (n1 == null) {
            return n2 != null;
        } else {
            return n2 == null || n1.val != n2.val;
        }
    }
}
