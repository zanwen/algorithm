/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-bst/submissions/
 * @author zhenganwen
 * @date 2019/11/22 15:51
 */
public class _450_delete_node_in_a_bst {

    /**
     * </br>
     * 根据被删除节点度进行分类讨论
     * <ul>
     * <li>当度为0时，将其父节点指向其的指针置空即可</li>
     * <li>当度为1时，将其父节点指向其的指针指向其唯一的一个孩子即可</li>
     * <li>当度为2时，可将其前驱/后继节点的元素覆盖其元素，然后删除其只有一个孩子的前驱/后继节点，要注意覆盖后会导致bst存在两个相同元素的节点</li>
     * </ul>
     * </br>
     * OJ
     * <ul>
     * <li>执行用时 : 1 ms , 在所有 java 提交中击败了 45.87% 的用户 内存消耗 : 39.3 MB , 在所有 java 提交中击败了
     * 95.43% 的用户</li>
     * <li>执行用时 : 0 ms , 在所有 java 提交中击败了 100.00% 的用户 内存消耗 : 38.7 MB , 在所有 java
     * 提交中击败了 95.94% 的用户</li>
     * </ul>
     * 
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        TreeNode node = node(root, key);
        if(node == null) return root;

        if(hasTwoChild(node)){
            TreeNode successor = node.right;
            while(successor.left != null){
                successor = successor.left;
            }
            node.val = successor.val;
            node = successor;
        }

        TreeNode parent = parent(root, node);
        TreeNode child = node.left == null ? node.right : node.left;
        if(parent == null){
            root = child;
            return root;
        }
        if(node.val >= parent.val) parent.right = child;
        else parent.left = child;
        return root;
    }

    private boolean hasTwoChild(TreeNode node){
        return node != null && node.left != null && node.right != null;
    }

    private TreeNode parent(TreeNode root, TreeNode node){
        TreeNode parent = null;
        while(root != null){
            if(node == root) return parent;
            parent = root;
            if(node.val >= root.val) root = root.right;
            else root = root.left;
        }
        return null;
    }

    private TreeNode node(TreeNode root, int val){
        while(root != null){
            if(root.val == val) return root;
            if(val > root.val) root = root.right;
            else root = root.left;

        }
        return null;
    }
}