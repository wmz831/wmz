package com.wmz.demo;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

public class KeyWordTest {

    @Test
    public void test() {
        KeyWord a = new KeyWord();
        KeyWord b = new KeyWord();
        System.out.println("a: " + a.num1 + "  b: " + b.num1);
        a.num1 = 6;
        System.out.println("a: " + a.num1 + "  b: " + b.num1);
        b.num1 = 7;
        System.out.println("a: " + a.num1 + "  b: " + b.num1);
        KeyWord c = new SubKeyWord();
        SubKeyWord d = new SubKeyWord();
        System.out.println(JSON.toJSONString(a));
        System.out.println(JSON.toJSONString(b));

        System.out.println(JSON.toJSONString(c));
        System.out.println(JSON.toJSONString(d));
    }

}
