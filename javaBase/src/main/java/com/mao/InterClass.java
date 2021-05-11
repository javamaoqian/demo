package com.mao;

/**
 * @Author: mq
 * @Date: 2021/5/10 19:12
 * netty的channel&unsafe 的关系
 * unsafe是channel里面的一个内部类 所以unsafe能访问channel里面的属性和方法
 */
public class InterClass {
    private String outName="haha";

    public static void main(String[] args) {
        System.out.println(new InterClass().getInter().getOutName());
    }
    private Inter getInter(){
        return new Inter();
    }

    public class Inter{
        public String getOutName(){
            return outName;
        }
    }
}
