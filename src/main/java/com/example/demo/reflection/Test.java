package com.example.demo.reflection;

import org.apache.commons.lang.time.DateUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author: mq
 * @Date: 2020/11/20 09:42
 */
public class Test {
    public static void main(String[] args) throws Exception {
        Set<String> s =new HashSet<>();
        s.add("123");
        s.add("12345");
        s.add("12343");
        System.out.println(s.contains(Integer.valueOf(123).toString()));
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
