package com.wmz;

import com.wmz.fastDFS.MyQtFastStart;
import org.junit.Test;

/**
 * Create by Wang Mingzhen om 2018/7/13
 */
public class MyQtFastStartTest {

    @Test
    public void run(){
        String in = "D:\\迅雷下载\\火星救援\\The.Martian.2015.720p.BluRay.H264.AAC-RARBG\\The.Martian.2015.720p.BluRay.H264.AAC-RARBG.mp4";
        String out = "F:\\test\\1.mp4";
        try {
            MyQtFastStart.fastStart(in,out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}