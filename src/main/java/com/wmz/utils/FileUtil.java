package com.wmz.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {



    /**
     * 文件读取，同步非阻塞
     */
    public static boolean read(File srcFile, File dstFile) throws Exception {
        FileInputStream fis= new FileInputStream(srcFile);
        FileOutputStream fos =  new FileOutputStream(dstFile);
        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannl = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(true){
            buffer.clear();
            if(readChannel.read(buffer) == -1){
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
    public static boolean write(){

        return false;
    }


}
