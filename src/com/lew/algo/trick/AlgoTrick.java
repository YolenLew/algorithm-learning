package com.lew.algo.trick;

/**
 * 算法小技巧
 *
 * @author Yolen
 * @date 2023/5/22
 */
public class AlgoTrick {
    /**
     * 两整数相除向上取整
     * <P> 原文链接：https://blog.csdn.net/gao_zhennan/article/details/121625485
     * <p> x / y + (x % y != 0 ? 1 : 0);
     * <p> (int)Math.ceil(x * 1.0 /y);
     *
     * @param x x
     * @param y y
     * @return 向上取整的商
     */
    public int getCeilQuotient(int x, int y) {
        return (x + y - 1) / y;
    }
}
