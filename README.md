# 时间复杂度

## 从小到大排序

`O(1) -> O(n) -> O(logn) -> O(nlogn) -> O(n^2) -> O(n^3) -> O(2^n)`

> 时间复杂度越大, 即随着数据规模的增大, 算法所需时间的增长率就越大

## 所有对数均可看作logn

如`log2(n)`和`log5(n)` : `log2(n) = log5(2) * log2(n)`, 可以省去常数项系数`log5(2)`, 也即`log2(n) <=> log5(n)`; 从函数图像角度来看, 随n的变大, 两函数增长率变大的幅度处于同一个级别。

# 小技巧

[Tips.md](./src/top/zhenganwen/learn/algorithm/Tips.md)