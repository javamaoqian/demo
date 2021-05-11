package com.example.demo.reflection;

/**
 * @Author: mq
 * @Date: 2020/11/20 09:40
 */
public class Bird {
    private String name;

    private void sing() {
        System.out.println("0000000");
    }

    protected void sing(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
       int rs= Integer.numberOfLeadingZeros(16) | (1 << (16 - 1));
        System.out.println(Integer.toBinaryString(rs));
        System.out.println((rs<<16)+2);
        System.out.println(Integer.toBinaryString((rs<<16)+2));
        System.out.println(((1<<15)<<16)+2);
        System.out.println(1<<31);
    }
}
