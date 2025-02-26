package com.lmt.ecom.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GoogleORCTool {
    public static String ocr(String imgPath, String phpOCRPath) {
        String result = "";
        System.out.println(imgPath);
        ProcessBuilder p=new ProcessBuilder(
                "php",//php.exe是PHP解释器执行的exe文件
                phpOCRPath, //test.php是php源代码
                imgPath
        );//ProcessBuilder类和Process类属于java.lang包
        try {
            Process pp=p.start();
            InputStream is=pp.getInputStream();
            InputStreamReader isr=new InputStreamReader(is);
            char[] ch=new char[1024];
            int readLength = isr.read(ch);
            StringBuilder buffer = new StringBuilder();
            while (readLength != -1) {
                buffer.append(new String(ch, 0, readLength));
                readLength = isr.read(ch);
            }
            System.out.println("-----------phpOCRPath:" + phpOCRPath);
            System.out.println("-----------imgPath:" + imgPath);
            System.out.println(buffer);
            result = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
