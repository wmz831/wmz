package com.wmz;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Create by Wang Mingzhen om 2018/6/13
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/up")
    public boolean up(@RequestParam("file") MultipartFile file){
        System.out.println(file.isEmpty());
        System.out.println(file.getName());
        return true;
    }

//    public String index(){
//        return "index";
//    }

}
