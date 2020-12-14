package com.example.demo.reflection;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mq
 * @Date: 2020/11/20 09:42
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Class b = Bird.class;
        Method s = b.getDeclaredMethod("sing", String.class);
        s.invoke(new Bird(), "hahha");


        Map<String, Integer> a = new HashMap<>();
        int c = a.get("1");
        System.out.println(c);
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }

        return 0;
    }
}
