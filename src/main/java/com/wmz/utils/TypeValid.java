package com.wmz.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TypeValid {

//    private static String uploadPath;
//
//    private static String videoPath;
//
//    private static String imagePath;
//
//    private static String documentPath;
//
//    private static String audioPath;

    //模拟外系统路径
//    @Value("${virtualPath}")
//    private static String virtualPath;

    /**
     * 功能：根据文件名称判断类型
     * @return 0-其他 1-图片 2-视频 3-文档 4-音频
     */
    public static int valid(String fileName){

        if (fileName == null) {
            return 0;
        } else {
            // 获取文件后缀名并转化为写，用于后续比较
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();

            // 图片类型数组1
            String[] img = { "bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
                    "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf" };
            for (int i = 0; i < img.length; i++) {
                if (img[i].equals(fileType)) {
                    return 1;
                }
            }

            // 视频类型数组2
            String[] video = { "mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "flv", "rmvb", "rm", "webm" };
            for (int i = 0; i < video.length; i++) {
                if (video[i].equals(fileType)) {
                    return 2;
                }
            }

            // 创建文档类型数组3
            String[] document = { "txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt" };
            for (int i = 0; i < document.length; i++) {
                if (document[i].equals(fileType)) {
                    return 3;
                }
            }

            // 创建音乐类型数组4
            String[] music = { "mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
                    "m4a", "vqf" };
            for (int i = 0; i < music.length; i++) {
                if (music[i].equals(fileType)) {
                    return 4;
                }
            }
        }
        return 0;
    }
}
