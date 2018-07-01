package com.wmz;

import com.wmz.utils.TypeValid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 模拟路径的接口
 */
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
    public boolean save(@RequestParam MultipartFile file){
        System.out.println("FileMsg:" + file.getContentType());
        String fileName = file.getOriginalFilename();
        //新增uuid文件夹
        File filePath = new File(virtualPath);
        if(!filePath.exists()){
            filePath.mkdir();
        }
        //新增文件
        File addFile = new File(virtualPath + "/" + fileName);
        if(addFile.exists()){
            return false;
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(addFile);
            fos.write(file.getBytes());
            fos.flush();
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
