package com.wmz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RequestMapping
@RestController
public class testController {

    @Value("${virtualPath}")
    private String virtualPath;

    @GetMapping("/download/{file:.+}")
    public void test(@PathVariable("file") String file,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=" + file);
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf8");
        File target = new File(virtualPath + file);
        if(target.exists()){
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;
            try{
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(new File(virtualPath + file)));
                int i = bis.read(buff);
                while(i!=-1){
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
                bis.close();
                os.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    @PostMapping("/update")
    public @ResponseBody boolean save(@RequestParam MultipartFile file, String uuid){
        return true;
    }

}
