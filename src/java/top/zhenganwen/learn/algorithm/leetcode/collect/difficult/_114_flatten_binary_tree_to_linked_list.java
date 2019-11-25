/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.collect.difficult;

import top.zhenganwen.learn.algorithm.leetcode.tree.TreeNode;

import java.util.Stack;

/**
 * @author zhenganwen
 * @date 2019/11/20 17:37
 */
public class _114_flatten_binary_tree_to_linked_list {
    private TreeNode root;

    /**
     * 解法一 </br>
     * 规律：扁平化后得到的链表节点顺序就是二叉树的先序遍历访问节点的顺序 </br>
     * 先序遍历，将上次访问的节点的right指向当前遍历节点 </br>
     * 执行用时 : 2 ms , 在所有 java 提交中击败了 23.89% 的用户 </br>
     * 内存消耗 : 35.8 MB , 在所有 java 提交中击败了 81.11% 的用户
     * 
     * @param root
     */
/*    public void flatten(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        TreeNode lastVisit = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
            if (lastVisit != null) {
                lastVisit.right = node;
                lastVisit.left = null;
            }
            lastVisit = node;
        }
    }*/

    /**
     * 解法二 </br>
     * 将左子树插入右子树之前，这样根节点就没有左子树了 </br>
     * 考虑根节点的右子树，同理消去右子树的左子树 </br>
     * 直到考虑的节点未null </br>
     * 执行用时 : 1 ms , 在所有 java 提交中击败了 89.10% 的用户 </br>
     * 内存消耗 : 35.6 MB , 在所有 java 提交中击败了 82.06% 的用户 </br>
     * </br>
     * 执行用时 : 0 ms , 在所有 java 提交中击败了 100.00% 的用户 </br>
     * 内存消耗 : 37 MB , 在所有 java 提交中击败了 77.48% 的用户
     * 
     * @param root
     */
/*    public void flatten(TreeNode root){
        if(root == null) return;
        while(root != null){
            TreeNode node = root.left;
            if (node == null){
                root = root.right;
                continue;
            }
            // link right child to the most right of left child, let right point left, let left point null
            while (node.right != null) {
                node = node.right;
            }
            node.right = root.right;
            root.right = root.left;
            root.left = null;

            root = root.right;
        }
    }*/

    /**
     * </br>
     * 解法三 </br>
     * 借助后续遍历 右-左-中，将当前访问节点的right指向上次访问的节点 </br>
     * ps：不要忘了更新记录上次访问节点的指针
     * </hr>
     * </br>
     * 执行用时 : 2 ms , 在所有 java 提交中击败了 23.89% 的用户 </br>
     * 内存消耗 : 38.4 MB , 在所有 java 提交中击败了 64.51% 的用户
     * 
     * @param root
     */
    public void flatten(TreeNode root){
        if(root == null) return;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        TreeNode lastVisit = null;
        while(!stack.isEmpty()){
            TreeNode node = stack.peek();
            if(isLeaf(node) || oneIsChildOfAnother(lastVisit, node)){
                stack.pop();
                node.right = lastVisit;
                node.left = null;
                lastVisit = node;
            }else{
                if(node.left != null) stack.push(node.left);
                if(node.right != null) stack.push(node.right);
            }
        }
    }

    public boolean isLeaf(TreeNode node){
        return node != null && node.left == null && node.right == null;
    }

    public boolean oneIsChildOfAnother(TreeNode one, TreeNode another){
        return one != null && (one == another.left || one == another.right);
    }



    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        new _114_flatten_binary_tree_to_linked_list().flatten(root);
    }
}
