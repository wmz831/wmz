package com.wmz.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {


    /**
     * 文件读取，同步非阻塞
     */
    public static boolean read(File srcFile, File dstFile) throws Exception {
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(dstFile);
        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannl = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            if (readChannel.read(buffer) == -1) {
                break;
            }
            buffer.flip();
            writeChannl.write(buffer);
        }
        fis.close();
        fos.close();
        return true;
    }

    /**
     * 文件写入，同步非阻塞
     */
    public static boolean write() {

        return false;
    }

    public static boolean txtChange(String srcFile, String dstFile,
                                    String srcStr, String dstStr,
                                    String talkStart, String talkEnd) {
        Boolean inner = false;
        BufferedReader br = null;
        try {
            br = Files.newBufferedReader(Paths.get(srcFile));
            char c;
            do {
                c = (char) br.read();
                String tmp = String.valueOf(c);
                if (tmp.equals(talkStart)) {
                    inner = true;
                }
                if (inner && tmp.equals(talkEnd)) {
                    inner = false;
                }
                if (!inner && tmp.equals(srcStr)) {
                    tmp = dstStr;
                }
                System.out.print(tmp);
            } while (c != -1);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FileUtil.txtChange("G:/txt/change/todo.txt", "",
                "我", "c", "」", "「");
    }

}
