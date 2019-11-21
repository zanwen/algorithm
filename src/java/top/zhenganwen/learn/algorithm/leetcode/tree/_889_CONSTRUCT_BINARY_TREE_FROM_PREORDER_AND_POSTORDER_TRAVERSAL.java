/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

/**
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/submissions/
 * @author zhenganwen
 * @date 2019/11/21 14:54
 */
public class _889_CONSTRUCT_BINARY_TREE_FROM_PREORDER_AND_POSTORDER_TRAVERSAL {

    /**
     * </br>
     * 解析思路：写出两种遍历，发现规律——前序遍历根节点后一个就是左子节点，后续遍历根节点前一个就是右子节点，
     * 由此两者可得到左右子节点信息，从而分离左右子树节点在数组中的范围 </br>
     * 注意：难点在于边界判定——没有左子树或没有右子树的情况；且前序遍历、后序遍历组合是无法判断缺少的是两者中的哪一个
     * </hr>
     * </br>
     * 执行用时 : 1 ms , 在所有 java 提交中击败了 100.00% 的用户 </br>
     * 内存消耗 : 37.2 MB , 在所有 java 提交中击败了 85.48% 的用户
     * 
     * @param pre
     * @param post
     * @return
     */
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if(pre == null || post == null
                || pre.length == 0 || pre.length != post.length) return null;
        int end = pre.length -1;
        return build(pre, 0, end, post, 0, end);
    }

    private TreeNode build(int[] pre, int i, int j, int[] post, int m, int n){
        if(i == j) return new TreeNode(pre[i]);

        TreeNode root = new TreeNode(pre[i]);
        int left = pre[i + 1], right = post[n - 1];
        if(left == right){
            root.left = build(pre, i + 1, j, post, m, n - 1);
            return root;
        }

        int k = index(pre, right), q = index(post, left);
        if(k == -1 || q == -1) throw new RuntimeException("invalid input");
        root.left = build(pre, i + 1, k - 1, post, m, q);
        root.right = build(pre, k, j, post, q + 1, n - 1);
        return root;
    }

    private int index(int[] arr, int val){
        for(int i = 0 ; i < arr.length ; i++) // asume the val is unrepeatable
            if(arr[i] == val) return i;
        return -1;
    }
}