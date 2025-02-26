package com.lmt.ecom.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 安全工具类
 */
public class SecurityUtil {

    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    public static String encrypt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * 密码是否匹配
     *
     * @param password
     * @param encodedPassword
     * @return
     */
    public static boolean matches(String password, String encodedPassword) {
        // 为了兼容现在有的密码加密，保留MD5加密
        if (new BCryptPasswordEncoder().matches(password, encodedPassword) || encodedPassword.equals(stringToMD5(password))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 原有MD5加密
     *
     * @param plainText
     * @return
     */
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

}
