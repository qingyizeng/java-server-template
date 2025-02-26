package com.lmt.ecom.portal.util;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.SetObjectAclRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmt.ecom.common.domain.CompressResult;
import com.lmt.ecom.common.domain.GoodsParam;
import com.lmt.ecom.common.domain.NodeJsCompress;
import com.luciad.imageio.webp.WebPWriteParam;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.json.JSONObject;

public class Utils {
    public static boolean isNotEmpty(String val) {
        if (null == val || "".equals(val)) {
            return false;
        } else {
            return true;
        }
    }

    public static double toDouble(Object obj) {
        String temp = toString(obj);
        double result = 0;
        if (temp != "") {
            try {
                result = Double.valueOf(temp);
            } catch (Exception ex) {
            }
        }
        return result;
    }

    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static String getNextTwoWeekSunday() {
        String twoDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date one = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(one);

        calendar.add(Calendar.WEEK_OF_YEAR, 3);

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day != 1) {
            int addDay = 8 - day;
            calendar.add(Calendar.DATE, addDay);
        }

        Date two = calendar.getTime();
        twoDate = sdf.format(two);

        return twoDate;
    }
    public static String formatDate(Date date, String format) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static boolean isEmpty(String val) {
        if (null == val || "".equals(val)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDate(String date) {
        Date temp = toDate(date, "yyyy-MM-dd");
        if (null != temp) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean checkEmail(String emial) {
        if (null == emial) {
            return false;
        }
        Pattern patten = Pattern
                .compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+");
        Matcher matcher = patten.matcher(emial);
        return matcher.find();
    }

    public static boolean checkPhone(String phone) {
        if (null == phone) {
            return false;
        }
        if (phone.length() != 10) {
            return false;
        }
        Pattern patten = Pattern
                .compile("(886)?09\\d{8}");
        Matcher matcher = patten.matcher(phone);
        return matcher.find();
    }

    public static String genRandCode() {
        long codeL = System.nanoTime();
        String codeStr = Long.toString(codeL);
        return codeStr.substring(codeStr.length() - 8, codeStr.length() - 2);
    }

    public static void main(String[] args) {



    }

    public static String toString(Object obj) {
        String result = "";
        if (null != obj) {
            result = obj.toString();
        }
        return result;
    }

    public static String expToStr(Object exp) {
        if (exp == null) {
            return "";
        }
        String strExp = toString(exp);
        String regEx = "[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher m = pattern.matcher(strExp);
        String strDate = m.replaceAll("").trim();
        String result = "";
        if (strDate.length() == 8) {
            result = strDate.substring(0, 4) + "-" + strDate.substring(4,6) + "-" + strDate.substring(6);
        }
        return result;
    }



    public static BigDecimal toBigDecimal(Object obj) {
        String result = "0";
        if (null != obj) {
            result = obj.toString();
            if (result.equals("")) {
                result = "0";
            }
        }

        return new BigDecimal(result);
    }

    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }

    public static Long toLong(Object obj) {
        String temp = toString(obj);
        Long result = 0L;
        if (temp != "") {
            try {
                result = Long.valueOf(temp);
            } catch (Exception ex) {
            }
        }
        return result;
    }
    public static boolean toBoolean(Object obj) {
        String temp = toString(obj);
        boolean result = false;
        if (temp != "") {
            try {
                result = Boolean.valueOf(temp);
            } catch (Exception ex) {
            }
        }
        return result;
    }

    public static int toInt(Object obj) {
        String temp = toString(obj);
        int result = 0;
        if (!temp.equals("")) {
            try {
                result = Integer.valueOf(temp);
            } catch (Exception ex) {
            }
        }
        return result;
    }


    public static String toStrDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String result = sdf.format(date);
        return result;
    }
    public static Date toDate(String strDate, String format) {
        Date result = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            result = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void downImg(String urlString)  throws Exception{
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流

        String filename = "/Users/jerry/Desktop/ossimg/" + urlString.substring(urlString.indexOf("com/") + 4);  //下载路径及下载图片名称
        File file = new File(filename);
        FileOutputStream os = new FileOutputStream(file, true);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

    /**
     * 文件上传
     *
     * @param file
     * @param targetPath
     * @return 0:文件完整路径  1:原始文件名  2:文件大小
     */
    public static List<String> upload(MultipartFile file, String targetPath, String baseURL) {
        List<String> result = new ArrayList<>();
        String resultFilePath = targetPath;
        String targetFileName = UUID.randomUUID().toString();

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        String fileFullPath = resultFilePath + "/" + targetFileName + suffixName;
        File dest = new File(fileFullPath);
        try {
            file.transferTo(dest);
            result.add(fileFullPath);
            result.add(fileName);
            result.add(String.valueOf(file.getSize()));
            result.add(baseURL + "/" + targetFileName + suffixName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存头像至AWS
     *
     * @param imgUrl
     * @param targetPath
     * @return
     */
    public static String uploadAvatar2AWS(String imgUrl, String targetPath) {
        String result = "";
        String targetFileName = UUID.randomUUID().toString();
        try {
            // 下载图片
            URL url = new URL(imgUrl);
            File file = new File(targetPath + "/" + targetFileName + ".jpg");
            FileUtils.copyURLToFile(url, file);

            // 判断文件大小
            if (file.length() > 512000) {
                // 对图片进行压缩
                compress2Webp(targetPath, targetFileName, file);
                //上传图片至AWS
                String awsKey = "upload/" + targetFileName + ".webp";
                result = awsUpload(awsKey, targetPath + "/" + targetFileName + ".webp");
            } else {
                //上传图片至AWS
                String awsKey = "upload/" + targetFileName + ".jpg";
                result = awsUpload(awsKey, targetPath + "/" + targetFileName + ".jpg");
            }
            // 删除文件
            if (file.exists()) {
                file.delete();
            }
            File webpFile = new File(targetPath + "/" + targetFileName + ".webp");
            if (webpFile.exists()) {
                webpFile.delete();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 上传文件
     *
     * @param file
     * @param targetPath
     * @return
     */
    public static Map<String, Object> uploadFile2AWS(MultipartFile file, String targetPath) {
        Map<String, Object> result = new HashMap<>();
        String targetFileName = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String fileFullPath = targetPath + "/" + targetFileName + suffixName;
        File dest = new File(fileFullPath);
        String awsURL = "";
        try {
            file.transferTo(dest);
            // 判断文件大小
            if (dest.length() > 512000) {
                // 对图片进行压缩
                compress2Webp(targetPath, targetFileName, dest);
                //上传图片至AWS
                String awsKey = "upload/" + targetFileName + ".webp";
                awsURL = awsUpload(awsKey, targetPath + "/" + targetFileName + ".webp");
            } else {
                //上传AWS
                String awsKey = "upload/" + targetFileName + suffixName;
                awsURL = awsUpload(awsKey, fileFullPath);
            }
            // 设置返回值
            result.put("fileName", fileName);
            result.put("awsUrl", awsURL);
            result.put("fileSize", file.getSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Webp文件压缩
     *
     * @param resultFilePath
     * @param fileName
     * @param file
     */
    private static void compress2Webp(String resultFilePath, String fileName, File file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            ImageWriter writer  = ImageIO.getImageWritersByMIMEType("image/webp").next();
            // 设置图片质量
            ImageWriteParam writeParam = writer.getDefaultWriteParam();
            writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            writeParam.setCompressionType(writeParam.getCompressionTypes()[WebPWriteParam.LOSSY_COMPRESSION]);
            writeParam.setCompressionQuality(0.1f);
            writer.setOutput(new FileImageOutputStream(new File(resultFilePath + "/" + fileName + ".webp")));
            writer.write(null, new IIOImage(bufferedImage, null, null), writeParam);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 上传文件至AmazonS3
     *
     * @param awsKey
     * @param filePath
     * @return
     */
    public static String awsUpload(String awsKey, String filePath) {
        String result = "https://d204imx2lso466.cloudfront.net/" + awsKey;
        Regions clientRegion = Regions.CA_CENTRAL_1;
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "");
        SetObjectAclRequest acl = new SetObjectAclRequest("", awsKey, CannedAccessControlList.PublicRead);
        // 调用AmazonS3接口上传
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(clientRegion).build();
        try {
            s3.putObject("", awsKey, new File(filePath));
            s3.setObjectAcl(acl);

        } catch (AmazonServiceException e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }


    public static String generateSeq() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedTime = currentTime.format(formatter);
        return formattedTime;
    }
}
