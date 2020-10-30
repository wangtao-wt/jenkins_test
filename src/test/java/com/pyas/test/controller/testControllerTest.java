package com.pyas.test.controller;


import org.junit.jupiter.api.Test;

import java.util.Stack;

public class testControllerTest {

    @Test
    public void test1() {
        int last = getValue(6);
        System.out.println(last);
    }

    //栈方式实现
    public int getValue1(int n) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(1);
        int k = 2;
        while (k < n) {
            int f1 = stack.pop();//后进先出 第2个数
            int f2 = stack.pop();//先进后出 第个数
            int index = f1 + f2;
            System.out.println(index);
            stack.push(f1); //第2个数转换为第一个数
            stack.push(index); //设置第2个数
            k++;
        }
        return stack.peek();//获取上面一个元素
    }

    //使用动态规划的思想
    public int getValue2(int n) {
        int f1 = 1;
        int f2 = 1;
        int f3 = 0;
        if (n < 3) {
            return 1;
        } else {
            for (int i = 0; i < n-2; i++) {
                f3 =f1 +f2;//计算第n个的value
                f1 = f2; //替换为下一次计算n做准备
                f2 = f3;
            }
            return f3;
        }
    }


    //使用递归方式调用 建立一个函数，用于计算数列中的每一项
    public  int getValue(int num) {
        //是否是第一个数和第二个数
        if(num == 1 || num == 2) {
            return 1;
        }else {
            //循环调用本函数
            return getValue(num - 2) + getValue(num - 1);
        }
    }


}