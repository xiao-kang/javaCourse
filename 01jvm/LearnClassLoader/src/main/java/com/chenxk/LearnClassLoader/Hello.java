package com.chenxk.LearnClassLoader;

/**
 * @author chenxiaokang
 * @date 2021/5/9
 */
public class Hello {
    public static void main(String[] args) {
        int result=0;
        for (int i = 0;i<10;i++){
            result=i*i+11;
            result=result-i;
            result=result/3;
            System.out.println("第"+i+"次是，"+result);
        }
    }

}
