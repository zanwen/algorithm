/*
 * Copyright 2019 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package top.zhenganwen.learn.algorithm.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/submissions/
 * @author zhenganwen
 * @date 2019/11/19 17:59
 */
public class _559_MAXIMUM_DEPTH_OF_N_ARY_TREE {

    /**
     * 执行用时 : 3 ms , 在所有 java 提交中击败了 35.27% 的用户 内存消耗 : 39.5 MB , 在所有 java 提交中击败了
     * 100.00% 的用户
     * 
     * @param root
     * @return
     */
    public int maxDepth(Node root) {
        if (root == null)
            return 0;
        LinkedList<Node> q = new LinkedList();
        q.offer(root);
        q.offer(null);
        int level = 0;
        while (!q.isEmpty()) {
            while (!q.isEmpty() && q.peek() != null) {
                Node node = q.poll();
                List<Node> children = node.children;
                for (int i = 0; i < children.size(); i++) {
                    Node child = children.get(i);
                    if (child != null)
                        q.offer(child);
                }
            }
            level++;
            q.poll();
            if (!q.isEmpty())
                q.offer(null);
        }
        return level;
    }
}
