package com.kuukokawaii.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @Author 喵粮都输光了
 * @Date 2020/8/20 17:12
 * @Description 编码工具
 */
public class EncryptUtil {

    /**
     * 日志对象
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptUtil.class);

    /**
     * Base64 编码
     *
     * @param bytes 二进制数组
     * @return 返回编码后的字符串
     */
    public static String encodeBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Base64 解码
     *
     * @param str 字符串
     * @return 返回解码后的二进制数组
     */
    public static byte[] decodeBase64(String str) {
        return Base64.getDecoder().decode(str);
    }

    /**
     * 根据 UTF8 进行 Base64 编码
     *
     * @param str 字符串
     * @return 编码后的字符串
     */
    public static String encodeUtf8StringBase64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 根据 UTF8 进行 Base64 解码
     *
     * @param str 字符串
     * @return 解码后的字符串
     */
    public static String decodeUtf8StringBase64(String str) {
        return new String(Base64.getDecoder().decode(str), StandardCharsets.UTF_8);
    }

    /**
     * url 编码
     *
     * @param url url
     * @return url
     */
    public static String encodeUrl(String url) {
        String encoded = null;
        try {
            encoded = URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("不支持的编码格式：", e);
        }
        return encoded;
    }

    /**
     * url 解码
     *
     * @param url url
     * @return url
     */
    public static String decodeUrl(String url) {
        String decoded = null;
        try {
            decoded = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("不支持的编码格式：", e);
        }
        return decoded;
    }

}
