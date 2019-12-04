# 时间复杂度

## 从小到大排序

`O(1) -> O(n) -> O(logn) -> O(nlogn) -> O(n^2) -> O(n^3) -> O(2^n)`

> 时间复杂度越大, 即随着数据规模的增大, 算法所需时间的增长率就越大

## 所有对数均可看作logn

如`log2(n)`和`log5(n)` : `log2(n) = log5(2) * log2(n)`, 可以省去常数项系数`log5(2)`, 也即`log2(n) <=> log5(n)`; 从函数图像角度来看, 随n的变大, 两函数增长率变大的幅度处于同一个级别。

# 小技巧

1.  如果递归的过程中有很多重复计算, 考虑将依赖的计算先算出来并做缓存
2.  避免使用`HashMap/HashTree/HashSet`做缓存, 而使用数组等简单数据结构
3.  尽量使用贴近题目规则的数据结构辅助解题, 如约瑟夫环问题可使用循环链表
4.  对于解题过程中感觉无从下手的, 子数据集计算过程如出一辙的, 可考虑采用递归进行分而治之, 如题目`括号的分数`
5.  避免使用`*, /, %, 乘方, 开方`, 而多使用`^, &, >>`
    
    1.  `n % m` => `n - (m > n ? 0 : m) `(`n >= 0, m > 0, n < 2m` `)

# 树

## 二叉树的性质

1.  非空二叉树的第i层的节点数最多为`2^(i-1)`
2.  高为h的非空二叉树节点数最多为`2^(h-1)`
3.  节点的子节点个数称为该节点的度, 树的度为树中节点的度的最大值, 二叉树是度为2的树
4.  节点的高度是该节点到叶子节点的最长路径经过的节点数, 包括他自己和叶子节点; 节点的深度是该节点到根节点路径中经过的节点数, 包括他自己和根节点; 树的高度就是树的深度
5.  二叉树的边数之和`T`等于
    1.  从节点的度的角度来看, 就是`T = 度为1的节点数*1 + 度为2的节点*2`, 即`T = n1 + 2 * n2`
    2.  从每条边都会指向一个节点来看, 只有根节点没有被指向, 因此为`T = 节点数 - 1`, 即`T = n0 + n1 + n2 - 1`
    3.  综上有`n0 = n2 + 1`, 使用满二叉树举例可快速验证
6.  真二叉树: 只有度为0和2的节点; 满二叉树: 在真二叉树的条件下, 度为0的节点都是叶子节点; 完全二叉树: 只有最后两层包含叶子节点, 且最后一层中的叶子节点从左至右依次排列

## 完全二叉树的性质

1.  完全二叉树: 度为1的节点树要么为1个要么为0个; 节点数相同的不同二叉树中完全二叉树的高度最小
2.  若完全二叉树的高度为`h`, 则有
    1.  前`h-1`层为满二叉树, 最后一层至少有1个节点, 因此节点数`n >= [2^(h-1) - 1] + 1`, 即`n >= 2^(h-1)`
    2.  高为`h`的二叉树节点数最多为`2^h - 1`, 因此总结点数`n < 2^h - 1`
    3.  综上有: `2^(h-1) <= n < 2^h`, 即`h-1 <= log2(n) < h`, 由于`h`只能取整, 因此`h = floor(log2(n)) + 1`, `floor`为向下取整, `ceiling`为向上取整; 向上向下取整与四舍五入无关, 仅看小数位数是否为零, `ceiling(4.0) = 4`, `ceiling(4.1
    ) = ceiling
    (4.8
    ) = 5`.
3.  若按从上到下, 从左到右的顺序对节点从1开始进行编号, 那么对于某个节点`i`有:
    1.  `i = 1`, 根节点
    2.  `i > 1`, 他的父节点编号为`floor(i/2)`
    3.  `2i <= n`, 他的左子节点编号为`2i`; `2i > n`, 则无左子节点; `2i + 1 <= n `, 他的左子节点编号为`2i + 1`; `2i + 1 > n`, 则无左子节点; 若从`0`开始编号, 对应将`n`换成`n - 1`即可
4.  已知完全二叉树节点个数`n`, 求叶子节点个数`n0`
    1.  `n = n0 + n1 + n2`
    2.  `n0 = n2 + 1`
    3.  综上, 有`n = 2 * n0 + n1 - 1`, 由于完全二叉树度为1的节点数只可能为0或1, 由此题解
5.  上述`7.iv`中为了避免使用`%2`以判断`n`的奇偶性, 可做如下优化
    1.  当`n`为偶数时, `n1 = 1`, `n0 = n/2`
    2.  当`n`为奇数时, `n1 = 0`, `n0 = (n + 1) / 2 = n/2 + 1/2`
    3.  假设有一个函数`fn`可以综上以上两种情况, `n0 = fn(n)`, 则`fn`应该为`floor`; 因此书写代码时, 以`java`为例, `if-else + %2`可优化为`n0 = (n + 1) >> 1`, 因为`java`中的运算浮点转整型时默认向下取整. 
    4. 总结: `n0 = floor( (n+1)/2 )`, `n1+n2 = floor( n/2 )`

## 二叉树的遍历

1.  先序遍历：拿到根节点就直接访问，然后将其右、左子节点依次入栈
2.  中序遍历：要遍历完左子树才能遍历根节点，借助一个遍历指针初始指向根节点，循环入栈遍历节点并将指针指向左孩子，直到左孩子为空则弹出栈顶节点进行访问，然后将指针指向该节点的右孩子（如果为空，则继续弹出栈顶节点，直到右孩子不为空或栈为空），进入下一次循环
3.  后序遍历：根节点要最后访问，将根节点入栈，栈非空循环，瞥一眼栈顶节点，如果栈顶节点是叶子节点或上次访问的节点是该节点的子节点，则弹出栈顶节点并进行访问，否则依序将其非空的右、左子节点入栈
4.  层序遍历：可借助队列，将根节点入队，队不空循环，出队一个节点进行访问，依序将其不空的左、右子节点入队；如果要求分别每一层有哪些节点，则可借助两个队列或插入null值标识
5.  翻转二叉树：将二叉树中每个节点的左右子节点交换位置，这相当于对每个节点进行访问操作（交换左右指针的值）
6.  前驱节点
    1.  如果存在左子树，前驱节点为左子树的最右节点
    2.  如果没有左子树，则到祖先节点中找：遍历指针沿着父节点依次向上，直到遍历节点是父节点的右子树，那么该父节点就是前驱节点；直到遍历节点没有父节点了，则前驱节点不存在
    3.  同理可推后继节点的查找

## 二叉搜索树（Binary Search Tree, BST）

删除节点，注意不能破环BST的性质

1.  删除度为0的节点（叶子节点），直接解除其父节点指向其的指针（可通过比较两节点的大小来判定是左指针还是右指针）；如果该节点是根节点，则直接将根节点指针置空即可
2.  删除度为1的节点
    1.  节点为根节点，根节点指针指向根节点的孩子节点，并将孩子节点的父指针指向null
    2.  节点不为根节点，将其父节点指向其的指针指向其孩子节点，并将其孩子节点的父指针指向其父节点
3.  删除度为2的节点，将其前驱或后继节点的元素覆盖该节点的，然后再删除其前驱或后继节点，注意其前驱或后继节点的度只有可能是0或1（因为是左子树中最右的节点或右子树总最左的节点）
4.  判断bst节点是否失衡，根据其左右子树的高度计算平衡因子即可

## 多路平衡搜索树——B树

1. 每个节点，所有子树高度相同

2. m阶B树表示节点的子节点个数最多为m，2-3树就是3阶B树，2-3-4树就是4阶B树，以此类推

3. 节点的元素个数限制：
   -   根节点：1 <= x <= m - 1
   -   非根节点：（⌈m/2⌉ - 1） <= x <= （m - 1）；

4. 节点的子节点个数限制：
   -   2 <= x <= m
   -   ⌈m/2⌉ <= x <= m

5. 添加元素：新添加的元素必定会添加到叶子节点中

6. **上溢：当向某个节点添加元素导致该节点元素个数超出限制**。此时需将该节点中间元素（从左到右第 m>>1 个）沿父指针向上移动，并将左右两边的元素作为该元素的左右子节点

   ![Snipaste_2019-11-26_16-11-06.png](http://ww1.sinaimg.cn/large/006zweohgy1g9bic6xp2ej306s0cmjrs.jpg)

   > 这是唯一一种能让B树长高的情况，即添加元素时，上溢传播到了根节点

7. 删除元素

   1.  当删除叶子节点中的元素时，直接移除即可
   2.  当删除非叶子节点中的元素时，先将其前驱/后继元素覆盖该元素，然后再删除其前驱/后继元素
   3.  某元素的前驱或后继元素必定在叶子节点中
   4.  真正的删除必定发生在叶子节点中

8. 下溢：

   1. 下溢：**删除某元素后，该元素所在节点元素个数变为（ ⌊m/2⌋ -2）**

   2. 如果下溢节点有元素个数为（⌊m/2⌋）或以上的同层相邻节点，可从该节点“借”一个元素

      1. 将下溢节点A和满足条件的相邻节点B中间的父节点元素复制一份添加到A中

      2. 将B中的最大元素（如果B在A的左边）或最小元素（如果B在A的右边）覆盖父节点元素

      3. 删除B中的最大/最小元素

         <img src="http://ww1.sinaimg.cn/large/006zweohgy1g9bh9qf452j30eo0btwfn.jpg" alt="Snipaste_2019-11-26_15-34-02.png" style="zoom: 67%;" />

   3.  如果下溢节点的同层相邻节点元素小于或等于（⌊m/2⌋-1），这时没办法借了，则合并下溢节点和任意一个相邻节点，并将两者之间的父节点元素也扯下来

       ![Snipaste_2019-11-26_15-38-51.png](http://ww1.sinaimg.cn/large/006zweohgy1g9bhejrcv3j30u5052dgt.jpg)

       1.  合并之后的节点元素个数为（ ⌊m/2⌋ + ⌊m/2⌋ -2），不超过（m-1）
       2.  此操作可能会导致父节点下溢，下溢现象可能沿父节点向上传播

   >  如果下溢调整时，扯下一个父节点元素之后导致父节点没有元素了，此时树的高度减一
   
9. 依序向4阶B树（2-3-4树）中添加1~22这22个元素步骤图：

   ![10-52-49.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dkhr9bfrj314n0lu40s.jpg)

   ![10-58-15.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dkj7hsqcj31ac0n8mzm.jpg)

   ![10-58-43.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dkjmtewlj30j00gdq3t.jpg)

10. 依序删除22~1

    ![11-41-02.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dlrymgn0j30zh0k6dhe.jpg)

    ![11-41-10.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dlryiq22j31300kata2.jpg)

    ![11-41-17.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dlt270dlj31490mh408.jpg)

## 红黑树

### 红黑树的性质

![11-52-26.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dm3jsogzj30qf06rdik.jpg)

上图中的树并不是一棵红黑色，因为绿色路径上只有两个黑色节点，而其他红色路径有3个黑色节点，不符合性质5

### 红黑树 VS 2-3-4树

![12-39-46.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dngs8m86j30qf0btwi5.jpg)

当B树叶子节点元素分别为：红黑红、黑红、红黑、黑。（**将红黑树当做2-3-4树来看，只有黑色节点才能独立成为一个B树节点，且其左右红色子节点可以融入该B树节点中**）

![14-30-03.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dr5aq4nvj30px096myz.jpg)

### 添加元素

#### 染成红色添加到叶子节点中

![14-30-24.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dr5as961j30p7091dhw.jpg)

新元素的定位逻辑与BST一致，需要注意的是添加之后的调整逻辑。

#### 分情况讨论

![14-30-37.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dr5b7htfj30pp0c6q6b.jpg)

#### 1、添加第一个元素

将节点染成黑色

#### 2、添加的节点作为黑色节点的子节点

染成红色添加即可，无需调整

![15-05-50.png](http://ww1.sinaimg.cn/large/006zweohgy1g9drox9t90j30hk054755.jpg)

#### 3、添加的节点作为红色节点的子节点，但无需上溢

##### LL/RR，单旋

![14-30-57.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dr5bh6rlj30q80bgdig.jpg)

##### RL/LR，双旋

![14-31-07.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dr5bg9epj30qg0b8goa.jpg)

#### 4、添加的节点作为红色的子节点，且需上溢

##### LL上溢

![14-45-03.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dr5arrikj30qb0cagoo.jpg)

##### RR上溢

![14-45-18.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dr5aqgk8j30qf0c776y.jpg)

##### LR上溢

![14-45-28.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dr5aybd6j30qa0buwh5.jpg)

##### RL上溢

![14-45-54.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dr5bik71j30qd0bv417.jpg)

#### 代码实现

> github: https://github.com/zanwen/algorithm/blob/master/src/java/top/zhenganwen/learn/algorithm/datastructure/tree/RBTree.java 

##### 二叉搜索树

```java
package top.zhenganwen.learn.algorithm.datastructure.tree;

import top.zhenganwen.learn.algorithm.commons.printer.BinaryTreeInfo;
import top.zhenganwen.learn.algorithm.commons.printer.BinaryTrees;

import java.util.*;

import static java.util.Objects.isNull;

/**
 * @author zhenganwen
 * @date 2019/11/6 17:48
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {

    protected Node<E>       root;

    private int           size;

    protected Comparator<E> comparator;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        nonNullCheck(element);

        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }

        Node<E> parent = root, cur = root;
        int compare = 0;
        while (cur != null) {
            parent = cur;
            compare = compare(element, cur.element);
            cur = compare > 0 ? cur.right : compare < 0 ? cur.left : cur;
            if (cur == parent) {
                cur.element = element;
                return;
            }
        }
        Node<E> node = createNode(element, parent);
        if (compare > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        size++;
        afterAdd(node);
    }

    protected void afterAdd(Node<E> node) {

    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null)
            return;

        size--;
        if (hasTwoChild(node)) {
            // the node's degree is 2, use node's predecessor/successor's element
            // cover the node, and then delete the predecessor/successor
            Node<E> successor = Objects.requireNonNull(successor(node));
            node.element = successor.element;
            node = successor;
        }

        // reach here, the degree of the node is possible only 0 or 1
        // that is to say, the node only has one child
        Node replacement = node.left == null ? node.right : node.left;
        if (replacement != null) {
            // the node's degree is 1
            replacement.parent = node.parent;
            if (isRoot(node)) {
                root = replacement;
            } else if (compare(node.element, node.parent.element) >= 0) {
                node.parent.right = replacement;
            } else {
                node.parent.left = replacement;
            }
        } else {
            // the node is leaf node
            if (isRoot(node)) {
                root = null;
            } else if (compare(node.element, node.parent.element) >= 0) {
                node.parent.right = null;
            } else {
                node.parent.left = null;
            }
        }
        afterRemove(node);
    }

    protected void afterRemove(Node<E> node) {
        // let auto-balance bst overwrite the method to balance the tree
    }

    private boolean isRoot(Node<E> node) {
        return node.parent == null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public Node node(E element) {
        Node<E> node = root;
        while (node != null) {
            int compare = compare(element, node.element);
            if (compare == 0)
                return node;
            else if (compare > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    private Node predecessor(Node<E> node) {
        if (node.left != null) {
            node = node.left;
            while (node.right != null) {
                node = node.right;
            }
            return node;
        } else {
            Node parent = node.parent;
            while (parent != null) {
                if (node == parent.right) {
                    return parent;
                } else {
                    node = parent;
                    parent = node.parent;
                }
            }
            return null;
        }
    }

    private Node successor(Node<E> node) {
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            Node parent = node.parent;
            while (parent != null) {
                if (node == parent.left) {
                    return parent;
                } else {
                    node = parent;
                    parent = node.parent;
                }
            }
            return null;
        }
    }

    private int compare(E insert, E current) {
        if (comparator != null) {
            return Objects.compare(insert, current, comparator);
        }
        return ((Comparable<E>) insert).compareTo(current);
    }

    private void nonNullCheck(E element) {
        if (isNull(element)) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }

    protected static class Node<E> {
        E       element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        Node(E element, Node<E> parent) {
            this(element);
            this.parent = parent;
        }

        Node(E element) {
            this.element = element;
        }

        boolean isLeftChildOf(Node node) {
            return this == node.left;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    private static boolean oneIsChildOfAnother(Node one, Node another) {
        return one != null && (one == another.left || one == another.right);
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

}
```

##### 自平衡的二叉搜索树

```java
package top.zhenganwen.learn.algorithm.datastructure.tree;

import java.util.Comparator;

/**
 * @author zhenganwen
 * @date 2019/11/28/028 15:53
 */
public class BBST<E> extends BinarySearchTree<E> {

    public BBST() {

    }

    public BBST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    protected void rotateLeft(Node<E> node) {
        Node<E> child = node.right;
        // rotate left
        node.right = child.left;
        child.left = node;
        afterRotate(node, child);
    }

    protected void afterRotate(Node<E> node, Node<E> child) {
        // link parent
        child.parent = node.parent;
        if (node.parent == null)
            root = child;
        else if (node.isLeftChildOf(node.parent))
            node.parent.left = child;
        else
            node.parent.right = child;
        node.parent = child;
        if (node == child.right && node.left != null)
            node.left.parent = node;
        if (node == child.left && node.right != null)
            node.right.parent = node;
    }

    protected void rotateRight(Node<E> node) {
        Node<E> child = node.left;
        // rotate right
        node.left = child.right;
        child.right = node;
        afterRotate(node, child);
    }

    /**
     *
     * LL
     *
     * inorder traversal: a b c d e f g
     *                     |
     *              _______f______
     *             |             |
     *         ____d____         g                  ____d____
     *        |        |              ===>         |        |
     *       _b_       e                          _b_      _f_
     *      |  |                                 |  |     |  |
     *      a  c                                 a  c     e  g
     *
     *
     * RR
     *
     * inorder traversal: a b c d e f g
     *            |
     *        ____b____
     *       |        |
     *       a    ____d____                        ____d____
     *           |        |          ===>         |        |
     *           c       _f_                     _b_      _f_
     *                  |  |                    |  |     |  |
     *                  e  g                    a  c     e  g
     *
     * LR
     *
     * inorder traversal: a b c d e f g
     *                  |
     *            ______f_____
     *           |           |
     *        ___b___        g                  ____d____
     *       |      |             ===>         |        |
     *       a     _d_                        _b_      _f_
     *            |  |                       |  |     |  |
     *            c  e                       a  c     e  g
     *
     *
     * RL
     *
     * inorder traversal: a b c d e f g
     *             |
     *       ______b______
     *      |            |
     *      a         ___f___                  ____d____
     *               |      |    ===>         |        |
     *              _d_     g                _b_      _f_
     *             |  |                     |  |     |  |
     *             c  e                     a  c     e  g
     *
     *
     * @param r the root node of the child tree
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     */
    protected void rotate(
            Node<E> r,
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g
    ) {
        // d -> new root of the child tree
        d.parent = r.parent;
        if (r.parent == null)
            root = d;
        else if (r.isLeftChildOf(r.parent))
            r.parent.left = d;
        else
            r.parent.right = d;

        // a-b-c
        b.left = a;
        b.right = c;
        if (a != null)
            a.parent = b;
        if (c != null)
            c.parent = b;


        // e-f-g
        f.left = e;
        f.right = g;
        if (e != null)
            e.parent = f;
        if (g != null)
            g.parent = f;


        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}
```

##### 红黑树

```java
package top.zhenganwen.learn.algorithm.datastructure.tree;

import top.zhenganwen.learn.algorithm.commons.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author zhenganwen
 * @date 2019/11/28/028 15:52
 */
public class RBTree<E> extends BBST<E> {

    private static boolean RED   = false;
    private static boolean BLACK = true;

    public RBTree() {

    }

    public RBTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    protected void afterAdd(Node<E> node) {
        // the insert node is root node or node overflows to top
        if (node.parent == null) {
            darken(node);
            return;
        }
        // the node is leaf node
        RBNode<E> parent = (RBNode<E>) node.parent;
        // 1. black parent
        if (parent.color == BLACK) {
            // redden it -> default red color
            return;
        }
        // 2. red parent, and grand must exist
        RBNode<E> uncle = sibling(parent);
        RBNode<E> grand = (RBNode<E>) parent.parent;
        if (isRed(uncle)) {
            // 2.1 overflow
            darken(parent);
            darken(uncle);
            redden(grand);
            afterAdd(grand);
            return;
        }
        // 2.2 uncle is null or black
        if (parent.isLeftChildOf(grand)) {
            if (node.isLeftChildOf(parent)) {
                // LL
                darken(parent);
                redden(grand);
                rotateRight(grand);
            } else {
                // LR
                darken(node);
                redden(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChildOf(parent)) {
                // RL
                darken(node);
                redden(grand);
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                // RR
                redden(grand);
                darken(parent);
                rotateLeft(grand);
            }
        }
    }

    private RBNode<E> color(Node<E> node, boolean color) {
        RBNode<E> n = (RBNode<E>) node;
        n.color = color;
        return n;
    }

    private RBNode redden(Node<E> node) {
        return color(node, RED);
    }

    private RBNode darken(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean isRed(Node<E> node) {
        return node != null && ((RBNode<E>) node).color == RED;
    }

    private RBNode<E> sibling(Node<E> node) {
        if (node.parent == null) {
            return null;
        }
        if (node.isLeftChildOf(node.parent)) {
            return (RBNode<E>) node.parent.right;
        } else {
            return (RBNode<E>) node.parent.left;
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private class RBNode<E> extends Node<E> {
        boolean color = RED;

        RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String s = "";
            if (color == RED) {
                s += "R_";
            }
            return s + element + "(" + (parent == null ? "null" : parent.element) + ")";
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                89, 90, 40, 21, 81, 58, 79, 85, 98, 12, 15, 91, 96, 69, 18, 66, 47, 43, 82
        };
        RBTree<Integer> rbt = new RBTree<>();
        for (Integer i : arr) {
            System.out.println("【" + i + "】");
            rbt.add(i);
            BinaryTrees.println(rbt);
            System.out.println("=================================================");
        }
    }
}
```

### 删除元素

#### 前提

![14-46-19.png](http://ww1.sinaimg.cn/large/006zweohgy1g9dr5aqc2sj30i706rmy5.jpg)

#### 1、删除红色节点

![11-17-32.png](http://ww1.sinaimg.cn/large/006zweohgy1g9kiudzy4yj30ht07pwfn.jpg)

#### 2、删除黑色节点

![11-17-39.png](http://ww1.sinaimg.cn/large/006zweohgy1g9kiue0hx0j30qj08dtaz.jpg)

#### 2.1、删除有一个红色孩子的黑色节点

![11-17-45.png](http://ww1.sinaimg.cn/large/006zweohgy1g9kiue37z1j30nc0b476h.jpg)

#### 2.2、删除没有红色孩子的黑色节点

##### 2.2.1、被删除节点的兄弟节点为黑色

![11-17-52.png](http://ww1.sinaimg.cn/large/006zweohgy1g9kiue430ej30q70dzae6.jpg)

---

![11-18-37.png](http://ww1.sinaimg.cn/large/006zweohgy1g9kiue20q1j30pw0dptbt.jpg)



##### 2.2.2、被删除节点的兄弟节点为红色

![11-18-43.png](http://ww1.sinaimg.cn/large/006zweohgy1g9kiue2lv2j30ny0dc41w.jpg)

#### 代码实现

##### BST

```java
package top.zhenganwen.learn.algorithm.datastructure.tree;

import top.zhenganwen.learn.algorithm.commons.printer.BinaryTreeInfo;
import top.zhenganwen.learn.algorithm.commons.printer.BinaryTrees;

import java.util.*;

import static java.util.Objects.isNull;

/**
 * @author zhenganwen
 * @date 2019/11/6 17:48
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {

    protected Node<E>       root;

    private int           size;

    protected Comparator<E> comparator;

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        nonNullCheck(element);

        if (root == null) {
            root = createNode(element, null);
            size++;
            afterAdd(root);
            return;
        }

        Node<E> parent = root, cur = root;
        int compare = 0;
        while (cur != null) {
            parent = cur;
            compare = compare(element, cur.element);
            cur = compare > 0 ? cur.right : compare < 0 ? cur.left : cur;
            if (cur == parent) {
                cur.element = element;
                return;
            }
        }
        Node<E> node = createNode(element, parent);
        if (compare > 0) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        size++;
        afterAdd(node);
    }

    protected void afterAdd(Node<E> node) {

    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null)
            return;

        size--;
        if (hasTwoChild(node)) {
            // the node's degree is 2, use node's predecessor/successor's element
            // cover the node, and then delete the predecessor/successor
            Node<E> successor = Objects.requireNonNull(successor(node));
            node.element = successor.element;
            node = successor;
        }

        // reach here, the degree of the node is only possible 0 or 1
        // that is to say, the node has no more than one child
        Node replacement = node.left == null ? node.right : node.left;
        if (replacement != null) {
            // the node's degree is 1
            replacement.parent = node.parent;
            if (isRoot(node)) {
                root = replacement;
            } else if (compare(node.element, node.parent.element) >= 0) {
                node.parent.right = replacement;
            } else {
                node.parent.left = replacement;
            }
            afterRemove(node, replacement);
        } else {
            // the node is leaf node
            if (isRoot(node)) {
                root = null;
            } else if (compare(node.element, node.parent.element) >= 0) {
                node.parent.right = null;
            } else {
                node.parent.left = null;
            }
            afterRemove(node, null);
        }
    }

    protected void afterRemove(Node<E> node, Node<E> replacement) {
        // let auto-balance bst overwrite the method to rebalance the tree
    }

    private boolean isRoot(Node<E> node) {
        return node.parent == null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public Node node(E element) {
        Node<E> node = root;
        while (node != null) {
            int compare = compare(element, node.element);
            if (compare == 0)
                return node;
            else if (compare > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    private Node predecessor(Node<E> node) {
        if (node.left != null) {
            node = node.left;
            while (node.right != null) {
                node = node.right;
            }
            return node;
        } else {
            Node parent = node.parent;
            while (parent != null) {
                if (node == parent.right) {
                    return parent;
                } else {
                    node = parent;
                    parent = node.parent;
                }
            }
            return null;
        }
    }

    private Node successor(Node<E> node) {
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            Node parent = node.parent;
            while (parent != null) {
                if (node == parent.left) {
                    return parent;
                } else {
                    node = parent;
                    parent = node.parent;
                }
            }
            return null;
        }
    }

    private int compare(E insert, E current) {
        if (comparator != null) {
            return Objects.compare(insert, current, comparator);
        }
        return ((Comparable<E>) insert).compareTo(current);
    }

    private void nonNullCheck(E element) {
        if (isNull(element)) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return node;
    }

    protected static class Node<E> {
        E       element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        Node(E element, Node<E> parent) {
            this(element);
            this.parent = parent;
        }

        Node(E element) {
            this.element = element;
        }

        boolean isLeftChildOf(Node node) {
            return this == node.left;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    public static void preOrderUnRecur(Node root) {
        emptyTreeCheck(root);
        Stack<Node> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            stringBuilder.append(node.element).append(" ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println(stringBuilder.toString());
    }

    private static void emptyTreeCheck(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("empty tree");
        }
    }

    public static void inOrderUnRecur(Node root) {
        emptyTreeCheck(root);
        StringBuilder sb = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
            while (root == null) {
                if (stack.isEmpty()) {
                    break;
                } else {
                    Node node = stack.pop();
                    sb.append(node.element).append(" ");
                    root = node.right;
                }
            }
        }
        System.out.println(sb.toString());
    }

    private static void postOrderUnRecur(Node root) {
        emptyTreeCheck(root);
        StringBuilder stringBuilder = new StringBuilder();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node lastAccess = null;
        while (!stack.isEmpty()) {
            Node node = stack.peek();
            // 当来到的节点node是叶子节点或上次访问的节点是其子节点时,需要进行访问
            if (isLeaf(node) || oneIsChildOfAnother(lastAccess, node)) {
                stack.pop();
                stringBuilder.append(node.element).append(" ");
                lastAccess = node;
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        System.out.println(stringBuilder.toString());
    }

    public static void levelOrder(Node root) {
        emptyTreeCheck(root);
        StringBuilder stringBuilder = new StringBuilder();
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            stringBuilder.append(node.element).append(" ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println(stringBuilder.toString());
    }

    private static boolean oneIsChildOfAnother(Node one, Node another) {
        return one != null && (one == another.left || one == another.right);
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    public static int heightUnRecur(Node root) {
        if (root == null) {
            return 0;
        }
        Stack<Node> s1 = new Stack<>(), s2 = new Stack<>();
        int height = 0;
        s1.push(root);
        while (!s1.isEmpty()) {
            while (!s1.isEmpty()) {
                Node node = s1.pop();
                if (node.left != null) {
                    s2.push(node.left);
                }
                if (node.right != null) {
                    s2.push(node.right);
                }
            }
            height++;
            Stack tmp = s1;
            s1 = s2;
            s2 = tmp;
        }
        return height;
    }

    public static boolean isCompleteBinaryTreeUnRecur(Node root) {
        if (root == null) {
            return true;
        }
        boolean leafMode = false;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.pollFirst();
            if (leafMode) {
                if (!isLeaf(node)) {
                    return false;
                }
                continue;
            }
            if (hasTwoChild(node)) {
                queue.addLast(node.left);
                queue.addLast(node.right);
            } else if (node.left == null && node.right != null) {
                return false;
            } else {
                leafMode = true;
                if (node.left != null) {
                    queue.addLast(node.left);
                }
            }
        }
        return true;
    }

    private static boolean hasTwoChild(Node node) {
        return node != null && node.left != null && node.right != null;
    }

    public static void main(String[] args) {
        int[] arr = { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(Integer::compareTo);
        for (int i : arr) {
            bst.add(i);
        }
        BinaryTrees.println(bst);

        // remove node that degree is 0
//        bst.remove(1);
//        bst.remove(3);
//        bst.remove(12);
//        BinaryTrees.println(bst);

        // remove node that degree is 1
//        bst.remove(11);
//        BinaryTrees.println(bst);

        // remove node that degree is 2
//        bst.remove(4);
//        bst.remove(9);
//        BinaryTrees.println(bst);

        // remove root
        bst.remove(7);
        BinaryTrees.println(bst);


//        Node root = new Node(1);
//        root.left = new Node(2);
//        root.right = new Node(3);
//        root.left.left = new Node(4);
//        root.left.right = new Node(5);
//        root.right.left = new Node(6);
//        root.right.right = new Node(7);
//        root.left.left.left = new Node(8);
//        root.left.right.left = new Node(9);
//        root.left.right.left.left = new Node(10);

//        preOrderUnRecur(root);
//        inOrderUnRecur(root);
//        postOrderUnRecur(root);
//        System.out.println(height(root));
//        System.out.println(heightUnRecur(root));
//        System.out.println(isCompleteBinaryTreeUnRecur(root));
//        levelOrder(root);

    }

}
```

##### BBST

```java
package top.zhenganwen.learn.algorithm.datastructure.tree;

import java.util.Comparator;

/**
 * @author zhenganwen
 * @date 2019/11/28/028 15:53
 */
public class BBST<E> extends BinarySearchTree<E> {

    public BBST() {

    }

    public BBST(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    protected void rotateLeft(Node<E> node) {
        Node<E> child = node.right;
        // rotate left
        node.right = child.left;
        child.left = node;
        afterRotate(node, child);
    }

    protected void afterRotate(Node<E> node, Node<E> child) {
        // link parent
        child.parent = node.parent;
        if (node.parent == null)
            root = child;
        else if (node.isLeftChildOf(node.parent))
            node.parent.left = child;
        else
            node.parent.right = child;
        node.parent = child;
        if (node == child.right && node.left != null)
            node.left.parent = node;
        if (node == child.left && node.right != null)
            node.right.parent = node;
    }

    protected void rotateRight(Node<E> node) {
        Node<E> child = node.left;
        // rotate right
        node.left = child.right;
        child.right = node;
        afterRotate(node, child);
    }

    /**
     *
     * LL
     *
     * inorder traversal: a b c d e f g
     *                     |
     *              _______f______
     *             |             |
     *         ____d____         g                  ____d____
     *        |        |              ===>         |        |
     *       _b_       e                          _b_      _f_
     *      |  |                                 |  |     |  |
     *      a  c                                 a  c     e  g
     *
     *
     * RR
     *
     * inorder traversal: a b c d e f g
     *            |
     *        ____b____
     *       |        |
     *       a    ____d____                        ____d____
     *           |        |          ===>         |        |
     *           c       _f_                     _b_      _f_
     *                  |  |                    |  |     |  |
     *                  e  g                    a  c     e  g
     *
     * LR
     *
     * inorder traversal: a b c d e f g
     *                  |
     *            ______f_____
     *           |           |
     *        ___b___        g                  ____d____
     *       |      |             ===>         |        |
     *       a     _d_                        _b_      _f_
     *            |  |                       |  |     |  |
     *            c  e                       a  c     e  g
     *
     *
     * RL
     *
     * inorder traversal: a b c d e f g
     *             |
     *       ______b______
     *      |            |
     *      a         ___f___                  ____d____
     *               |      |    ===>         |        |
     *              _d_     g                _b_      _f_
     *             |  |                     |  |     |  |
     *             c  e                     a  c     e  g
     *
     *
     * @param r the root node of the child tree
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     */
    protected void rotate(
            Node<E> r,
            Node<E> a, Node<E> b, Node<E> c,
            Node<E> d,
            Node<E> e, Node<E> f, Node<E> g
    ) {
        // d -> new root of the child tree
        d.parent = r.parent;
        if (r.parent == null)
            root = d;
        else if (r.isLeftChildOf(r.parent))
            r.parent.left = d;
        else
            r.parent.right = d;

        // a-b-c
        b.left = a;
        b.right = c;
        if (a != null)
            a.parent = b;
        if (c != null)
            c.parent = b;


        // e-f-g
        f.left = e;
        f.right = g;
        if (e != null)
            e.parent = f;
        if (g != null)
            g.parent = f;


        // b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}
```

##### AVLTree

```java
package top.zhenganwen.learn.algorithm.datastructure.tree;

import top.zhenganwen.learn.algorithm.commons.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author zhenganwen
 * @date 2019/11/25 13:46
 */
public class AVLTree<E> extends BBST<E> {

    public AVLTree() {

    }

    public AVLTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * just need once rebalance
     *
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
                break;
            }
        }
    }

    /**
     * remove the {@code node}, maybe cause the LL or RR situation generating,
     * this depends on the height of right child's left height when remove left child's node
     * and the height of left child's right height when remove right child's node.
     * what's more, this time rebalance maybe cause the ancestor's unbalance.
     * <p>
     * maybe need O(logn) times rebalance. see red-black tree {@link RBTree}
     *
     * @param node
     */
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                rebalance(node);
            }
        }
    }

    /**
     * see {@link this#rebalance)}
     * 平衡方案一：左旋右旋分开来做
     *
     * @param node
     */
    private void rebalance2(Node<E> node) {
        AVLNode grand = (AVLNode) node;
        AVLNode parent = getTallerChild(grand);
        AVLNode child = getTallerChild(parent);
        if (parent == grand.left) {
            if (child == parent.left) {
                // LL rotate right
                rotateRight(grand);
            } else {
                // LR rotate left first and then rotate right
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (child == parent.right) {
                // RR rotate left
                rotateLeft(grand);
            } else {
                // RL rotate right first and then rotate left
                rotateRight(parent);
                rotateLeft(grand);
            }
        }
    }

    /**
     * see {@link this#rebalance2}
     * 平衡方案二：从四种变换中抽离出通用的逻辑
     *
     * @param node
     */
    private void rebalance(Node<E> node) {
        AVLNode grand = (AVLNode) node;
        AVLNode parent = getTallerChild(grand);
        AVLNode child = getTallerChild(parent);
        if (parent == grand.left) {
            if (child == parent.left) {
                /*
                  LL
                           _______f______
                          |             |
                      ____d____         g
                     |        |
                    _b_       e
                   |  |
                   a  c

                   f -> grand, d -> parent, b -> child
                 */
                rotate(grand,
                        cast(child.left), child, cast(child.right),
                        parent,
                        cast(parent.right), grand, cast(grand.right));
            } else {
                /*
                  LR
                       ______f_____
                      |           |
                   ___b___        g
                  |      |
                  a     _d_
                       |  |
                       c  e

                  f -> grand, b -> parent, d -> child
                 */
                rotate(grand,
                        cast(parent.left), parent, cast(child.left),
                        child,
                        cast(child.right), grand, cast(grand.right));
            }
        } else {
            if (child == parent.right) {
                /*
                  RR
                   ____b____
                  |        |
                  a    ____d____
                      |        |
                      c       _f_
                             |  |
                             e  g

                  b -> grand, d -> parent, f -> child
                 */
                rotate(grand,
                        cast(grand.left), grand, cast(parent.left),
                        parent,
                        cast(child.left), child, cast(child.right));

            } else {
                /*
                  RL
                   ______b______
                  |            |
                  a         ___f___
                           |      |
                          _d_     g
                         |  |
                         c  e

                  b -> grand, f -> parent, d -> child
                 */
                rotate(grand,
                        cast(grand.left), grand, cast(child.left),
                        child,
                        cast(child.right), parent, cast(parent.right));
            }
        }
    }

    @Override
    protected void afterRotate(Node<E> node, Node<E> child) {
        super.afterRotate(node, child);
        ((AVLNode) node).updateHeight();
        ((AVLNode) child).updateHeight();
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        ((AVLNode) b).updateHeight();
        ((AVLNode) f).updateHeight();
        ((AVLNode) d).updateHeight();
    }

    private AVLNode cast(Node node) {
        return (AVLNode) node;
    }

    private AVLNode getTallerChild(AVLNode node) {
        int r = node.getRightHeight();
        int l = node.getLeftHeight();
        return (AVLNode) (r > l ? node.right : node.left);
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode) node).updateHeight();
    }

    protected boolean isBalanced(Node<E> node) {
        return ((AVLNode) node).isBalanced();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode(element, parent);
    }

    protected static class AVLNode extends Node {
        int height = 1;

        AVLNode(Object element, Node parent) {
            super(element, parent);
        }

        void updateHeight() {
            int r = getRightHeight();
            int l = getLeftHeight();
            height = 1 + Math.max(r, l);
        }

        int getLeftHeight() {
            return left == null ? 0 : ((AVLNode) left).height;
        }

        int getRightHeight() {
            return right == null ? 0 : ((AVLNode) right).height;
        }

        int balanceFactor() {
            int r = getRightHeight();
            int l = getLeftHeight();
            return Math.abs(r - l);
        }

        boolean isBalanced() {
            return balanceFactor() <= 1;
        }

        @Override
        public String toString() {
            return element.toString() + "(" + (parent == null ? "null" : parent.element) + ")";
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        for (int i = 0; i < 50; i++) {
            tree.add(i);
        }
        BinaryTrees.println(tree);

    }
}
```

##### RBTree

```java
package top.zhenganwen.learn.algorithm.datastructure.tree;

import top.zhenganwen.learn.algorithm.commons.printer.BinaryTrees;

import java.util.Comparator;
import java.util.Optional;

/**
 * @author zhenganwen
 * @date 2019/11/28/028 15:52
 */
public class RBTree<E> extends BBST<E> {

    private static boolean RED   = false;
    private static boolean BLACK = true;

    public RBTree() {

    }

    public RBTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * adjustment after bst's insertion.
     * </hr>
     * the node must be leaf node, and we can regard it as insert a element into a 2-3-4 b-tree.</br>
     * the 2-3-4 b-tree's leaf node must but only have one black node. </br>
     * so the b-tree's leaf node can have four situations below:
     * <ul>
     *     <li>one black node with two red children. R<-B->R </li>
     *     <li>one black node with one red left child. R<-B- </li>
     *     <li>one black node with one red right child. -B->R </li>
     *     <li>one black node. -B- </li>
     * </ul>
     *
     * 1. the insert node is added into the left of right of the black node.
     *
     *          B-      => R<-B- / -B->R
     *          <-B-    => R<-B->R
     *          B->R    => R<-B->R
     *
     *         insert into directly with bst's insertion logic
     *
     * 2. the insert node is added into the left of right of the red node. after insertion, the overflow not occurs
     *
     *               R<-B-    =>      R<-B        R<-B
     *                                 \         /
     *                                  R       R
     *
     *               -B->R    =>      -B->R       -B->R
     *                                  /              \
     *                                 R                R
     *
     *          after insertion of bst, we need rotate to let the mid node become the black node
     *
     * 3. the insert node is added into the left of right of the red node. and then, the overflow occurs
     *
     *              R<-B->R  =>      R<-B->R         R<-B->R         R<-B->R          R<-B->R
     *                             /                        \         \                   /
     *                            R                          R         R                 R
     *
     *          let the left and right become two independent b-tree node(color it to black), and then
     *          color itself to red to become a insertion node added its parent b-tree node
     * @param node
     */
    @Override
    protected void afterAdd(Node<E> node) {
        // the insert node is root node or node overflows to top
        if (node.parent == null) {
            darken(node);
            return;
        }
        // the node is leaf node
        RBNode<E> parent = (RBNode<E>) node.parent;
        // 1. black parent
        if (parent.color == BLACK) {
            // redden it -> default red color
            return;
        }
        // 2. red parent, and grand must exist
        RBNode<E> uncle = sibling(parent);
        RBNode<E> grand = (RBNode<E>) parent.parent;
        if (isRed(uncle)) {
            // 2.1 overflow
            darken(parent);
            darken(uncle);
            redden(grand);
            afterAdd(grand);
            return;
        }
        // 2.2 uncle is null or black
        if (parent.isLeftChildOf(grand)) {
            if (node.isLeftChildOf(parent)) {
                // LL
                darken(parent);
                redden(grand);
            } else {
                // LR
                darken(node);
                redden(grand);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChildOf(parent)) {
                // RL
                darken(node);
                redden(grand);
                rotateRight(parent);
            } else {
                // RR
                redden(grand);
                darken(parent);
            }
            rotateLeft(grand);
        }
    }

    /**
     * 首先，真正被删除的bst节点肯定存在于该红黑树等价的4阶B树的叶子节点中：
     *
     *      a. R_1 <- B_2 -> R_3
     *
     *      b. R_1 <- B_2
     *
     *      c. B_2 -> R_3
     *
     *      d. B_2
     *
     * 1. 如果被删除的节点是红节点，如上例 a,b,c 的 R_1 和 R_3，那么在bst的删除逻辑之后不需要额外的修复操作
     *
     * 2. 如果被删除的节点是黑色节点，如上例 b,c 中的 B_2
     *
     *      2.1 首先不要囊括 a 中的 B_2，这种情况我们会删除其后继节点 R_3
     *
     *      2.2 如果删除 b，c 中的 B_2， bst的删除逻辑是 用其孩子节点 R_1/R_3 替换它，这时我们为了保证等价性
     *      （B树节点中必须有且仅有一个黑色节点），需要将该红色孩子节点染成黑色
     *
     * 3. 如果被删除的节点没有红色孩子节点（即替换节点为null）
     *
     *      3.1 如果被删除节点的兄弟节点是黑色节点
     *
     *          3.1.1 如果【兄弟节点有红色孩子节点可以借】，则通过旋转操作修复红黑树
     *
     *                如果兄弟和其红色孩子节点的相对方位是 LL 或 RR，则对父节点进行 右旋 或 左旋，
     *                并将旋转后的中间节点【继承父节点的颜色】、【中间节点的两个孩子染成黑色】
     *
     *                e.g: delete B_4
     *
     *                          R_3                        R_2
     *                         /   \     =>               /   \
     *                R_1 <- B_2   B_4                 R_1     B_3
     *
     *                如果兄弟和其红色孩子节点的相对方位是 LR 或 RL，则先将其转变为 LL 或 RR 的情况后
     *                再复用上述的处理逻辑
     *
     *                e.g: delete B_5
     *
     *                     R_4                           R_4                      R_3
     *                   /     \        =>             /     \      =>          /     \
     *                B_2->R_3  B_5             B_2->R_3     B_5              B_2     B_4
     *
     *          3.1.2 如果兄弟节点没有红色孩子可以借，则考虑4阶B树的【下溢】，等价修复红黑树
     *
     *                【将父节点拉下来】与当前节点和兄弟节点合并成一个新的4阶B树节点（实际做法是将父节点染黑，兄弟节点染红）
     *                考虑【下溢】有向上传播性，我们将父节点作为删除后节点递归执行修复逻辑
     *
     *                e.g: delete B_8
     *                
     *                           B_5                            B_5                           
     *                         /    \                         /    \                             
     *                     B_3       B_7        =>        B_3       \        =>           B_3   <-  B_5    
     *                   /    \     /   \               /    \       \                  /    \       \   
     *                B_2    B_4   B_6  B_8          B_2    B_4  R_6<-B_7            B_2    B_4  R_6<-B_7
     *
     *                        B_8的兄弟节点B_6没有红色孩子节点          B_7的兄弟节点B_3没有红色孩子节点              下溢到了根节点，终止
     *         
     *      3.2 如果被删除节点的兄弟节点是红色节点
     *          
     *          根据红黑色的性质，等价的4阶B树中，被删除节点和兄弟节点并不处于同一层中
     *          （兄弟节点和父节点位于一个B树节点中，被删除节点位于该B树节点的下一层的B树节点中）
     *
     *          那么兄弟节点肯定有两个黑色孩子节点，与被删除节点位于同一层，可以通过旋转转换成 3.1
     *
     *
     *          e.g: delete B_7
     *
     *              R_4 <- B_6                  B_4 -> R_6
     *            /    \      \      =>       /      /    \
     *          B_3    B_5    B_7           B_3    B_5    B_7
     *
     *               通过对R_6右旋，B_7的兄弟节点由红色的R_4转换成了黑色的B_5，此后可复用 3.1 的逻辑
     *
     *
     *
     * @param node
     * @param replacement
     */
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        // 1. 如果被删除节点是红色节点
        if (isRed(node)) {
            return;
        }
        // 2. 如果替代被删除节点的是红色节点
        if (isRed(replacement)) {
            darken(replacement);
            return;
        }
        if (node.parent == null) {  // 3.1.2 中的下溢传播到根节点终止
            return;
        }

        Node<E> parent = node.parent;
        boolean left = parent.left == null || parent.left == node; // 当前被删除节点是否是左子节点 或 上溢传播至的节点是否是左子节点
        RBNode<E> sibling = (RBNode<E>) (left ? parent.right : parent.left);

        // 3.2 如果兄弟节点是红色节点，则旋转父节点，转变为 3.1
        if (isRed(sibling)) {
            if (left) rotateRight(parent);
            else rotateLeft(parent);
            afterRemove(node, null);
        // 3.1 兄弟节点是黑色节点
        } else if (hasRedChild(sibling)) {
            // 3.1.1 兄弟节点有红色孩子可以借，将旋转后的中间节点继承父节点颜色，两边节点染黑
            darken(parent); // 父节点不会成为中间节点，直接提前染黑
            if (sibling.isLeftChildOf(parent)) {
                if (isRed(sibling.left)) {
                    // LL 兄弟节点将成为中间节点
                    if (isRed(parent)) {
                        redden(sibling);
                    }
                    darken(sibling.left);
                } else {
                    // LR 兄弟节点的孩子将成为中间节点
                    if (isRed(parent)) {
                        redden(sibling.right);
                    }
                    darken(sibling);
                    rotateLeft(sibling); // 调整 LR 为 LL
                }
                // 到这里肯定是 LL
                rotateRight(parent);
            } else {
                // 与上述对称
                if (isRed(sibling.left)) {
                    // RL
                    if (isRed(parent)) {
                        redden(sibling.left);
                    }
                    darken(sibling);
                    rotateRight(sibling);
                } else {
                    // RR
                    if (isRed(parent)) {
                        redden(sibling);
                    }
                    darken(sibling.right);
                }
                rotateLeft(parent);
            }
        } else {
            // 3.1.2 兄弟节点没有红色孩子可以借，父节点染黑、兄弟节点染红，下溢传播（如果拉下来的父节点是黑色）
            boolean parentColor = ((RBNode<E>) parent).color;
            darken(parent);
            redden(sibling);
            if (parentColor == BLACK) {
                afterRemove(parent, null);
            }
        }
    }

    private boolean hasRedChild(RBNode<E> rbNode) {
        return rbNode != null && (isRed(rbNode.left) || isRed(rbNode.right));
    }

    private RBNode<E> color(Node<E> node, boolean color) {
        RBNode<E> n = (RBNode<E>) node;
        n.color = color;
        return n;
    }

    private RBNode redden(Node<E> node) {
        return color(node, RED);
    }

    private RBNode darken(Node<E> node) {
        return color(node, BLACK);
    }

    /**
     * if the {@code node} is null or its color is black, it will return false
     * @param node
     * @return
     */
    private boolean isRed(Node<E> node) {
        return node != null && ((RBNode<E>) node).color == RED;
    }

    private boolean isBlack(Node<E> node) {
        // node is leaf's children or non-null black node
        return node == null || ((RBNode<E>) node).color == BLACK;
    }

    private RBNode<E> sibling(Node<E> node) {
        if (node.parent == null) {
            return null;
        }
        if (node.isLeftChildOf(node.parent)) {
            return (RBNode<E>) node.parent.right;
        } else {
            return (RBNode<E>) node.parent.left;
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private class RBNode<E> extends Node<E> {
        boolean color = RED;

        RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Optional.ofNullable(left).ifPresent(p -> sb.append(p.element).append("-"));
            if (color == RED) {
                sb.append("R_");
            }
            sb.append(element);
            Optional.ofNullable(right).ifPresent(p -> sb.append("-").append(p.element));
            Optional.ofNullable(parent).ifPresent(p -> sb.append("(").append(p.element).append(")"));
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{
                89, 90, 40, 21, 81, 58, 79, 85, 98, 12, 15, 91, 96, 69, 18, 66, 47, 43, 82
        };
        RBTree<Integer> rbt = new RBTree<>();
        for (Integer i : arr) {
            rbt.add(i);
//            System.out.println("add 【" + i + "】");
//            BinaryTrees.println(rbt);
//            System.out.println("=================================================");
        }
        BinaryTrees.println(rbt);
        System.out.println("=================================================");

        for (Integer i : arr) {
            rbt.remove(i);
            System.out.println("remove 【" + i + "】");
            BinaryTrees.println(rbt);
            System.out.println("=================================================");
        }
    }
}
```

### 为何平衡

> 红黑树的5条性质能够保证其等价于一棵4阶B树

![11-29-59.png](http://ww1.sinaimg.cn/large/006zweohgy1g9kj60931gj30k40cvtbl.jpg)

## 性能对比

### AVL树

- 平衡标准比较严格：每个左右子树的高度差不超过1

- 最大高度是 1.44 ∗ log 2 n + 2 − 1.328 （100W个节点，AVL树最大树高28）

- 搜索、添加、删除都是 O(logn) 复杂度，其中添加仅需 O(1) 次旋转调整、**删除最多需要 O(logn) 次旋转调整**

  ### 红黑树

- 平衡标准比较宽松：没有一条路径会大于其他路径的2倍

- 最大高度是 2 ∗ log 2 (n + 1) （ 100W个节点，红黑树最大树高40）

- 搜索、添加、删除都是 O(logn) 复杂度，其中**添加、删除都仅需 O(1) 次旋转调整**

> 据统计，红黑树删除节点发生上溢传播的次数不会超过3次，因此可认为旋转调整复杂度为O(1)

### 技术选型

- 搜索的次数远远大于插入和删除，选择AVL树；搜索、插入、删除次数几乎差不多，选择红黑树

- 相对于AVL树来说，红黑树牺牲了部分平衡性以换取插入/删除操作时少量的旋转操作，整体来说性能要优于AVL树

- **红黑树的平均统计性能优于AVL树，实际应用中更多选择使用红黑树**