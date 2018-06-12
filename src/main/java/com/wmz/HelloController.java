package com.wmz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by Wang Mingzhen om 2018/6/13
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


//    public String index(){
//        return "index";
//    }

}
