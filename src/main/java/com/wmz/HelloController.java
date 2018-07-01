package com.wmz;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Create by Wang Mingzhen om 2018/6/13
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/upTest")
    public boolean up(@RequestParam("file") MultipartFile file){
        System.out.println(file.isEmpty());
        System.out.println(file.getName());
        return true;
    }

}
