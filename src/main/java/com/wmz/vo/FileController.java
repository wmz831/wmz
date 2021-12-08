package com.wmz.vo;

import com.wmz.utils.TypeValid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RequestMapping
@RestController
public class FileController {

    @Value("${uploadPath}")
    private String uploadPath;

    @Value("${videoPath}")
    private String videoPath;

    @Value("${imagePath}")
    private String imagePath;

    @Value("${documentPath}")
    private String documentPath;

    @Value("${audioPath}")
    private String audioPath;

//    @Value("${virtualPath}")
//    private String virtualPath;

    /**
     * 文件获取接口,
     * url地址格式：ip/action/uuid/fileName
     */
    @GetMapping("/getFile/{uuid}/{file:.+}")
    public void get(@PathVariable("file") String file,@PathVariable("uuid") String uuid, HttpServletResponse response){
        if(file!=null && !"".equals(file) && uuid!=null && !"".equals(uuid)){
            fileRead(file,uuid,response);
        }
    }

    /**
     * 文件上传接口
     * url地址格式：ip/action/type/uuid/fileName
     * @return
     */
    @PostMapping(value = "/saveFile/{uuid}")
    public boolean save(@RequestParam("file") MultipartFile file, @PathVariable("uuid") String uuid){
        if(file != null && !"".equals(file) && uuid!=null && !"".equals(uuid)){
            try{
                return fileWrite(file,uuid);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }


//    @GetMapping("/getFile/{uuid}/{file:.+}")
    public void fileRead(@PathVariable("file") String file,@PathVariable("uuid") String uuid, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET");
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,content-type");
        response.setHeader("Content-Type","application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=" + file);
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf8");
        String path = setPath(TypeValid.valid(file));//根据文件类型匹配地址
        //开始读取文件
        File target = new File(path + "/" + uuid + "/" + file);
        if(target.exists()){
            byte[] buff = new byte[1024 * 1024];
            InputStream is = null;
            OutputStream os = null;
            try {
                is = new FileInputStream(target);
                os = response.getOutputStream();
                int i = is.read(buff);
                while(i!=-1){
                    os.write(buff);
                    os.flush();
                    i = is.read(buff);
                }
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//    @PostMapping(value = "/saveFile/{uuid}")
    public boolean fileWrite(@RequestParam("file") MultipartFile file, @PathVariable("uuid") String uuid) throws Exception{
        //获取文件基本信息，判断文件类型
        System.out.println("FileMsg:" + file.getContentType());
        String fileName = file.getOriginalFilename();
        String path = setPath(TypeValid.valid(fileName));
        //新增uuid文件夹
        File filePath = new File(path + uuid);
        if(!filePath.exists()){
            filePath.mkdir();
        }
        //新增文件
        File addFile = new File(path + uuid + "/" + fileName);
        if(addFile.exists()){
            return false;
        }
        FileOutputStream fos = new FileOutputStream(addFile);
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        return true;
    }

    //0-其他 1-图片 2-视频 3-文档 4-音频
    private String setPath(int num){
        String path = "";
        switch (num){
            case 1:
                path = imagePath;
                break;
            case 2:
                path = videoPath;
                break;
            case 3:
                path = documentPath;
                break;
            case 4:
                path = audioPath;
                break;
            case 0:
                path = uploadPath;
                break;
        }
        return path;
    }

}
