package com.marc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * Encode Input String
 * User: marc
 * Date: 2016/2/3
 */
public class Encode {
    public static Logger log = LoggerFactory.getLogger(Encode.class);

    /**
     * @param str        input string
     * @param encodeType hash function(md5、md2.....
     * @return result string
     */
    public static String encode(String str, String encodeType) {
        String result = "";
        try {
            //set encode type
            MessageDigest md = MessageDigest.getInstance(encodeType);
            md.update(str.getBytes());
            StringBuffer sb = new StringBuffer();
            byte byteData[] = md.digest();

            //Convert to 128 bytes（32 Hex）
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 0x100, 16).substring(1));
            }
            result = sb.toString().toUpperCase();
        } catch (Exception e) {
            log.error("Encode.encode exception：Input string=" + str + " Encode type=" + encodeType, e);
        }
        return result;
    }

    /**
     * @param str        input string
     * @param encodeType hash function(md5、md2.....
     * @param salt       salt string
     * @return result string
     */
    public static String encode(String str, String encodeType, String salt) {
        str = str + salt;
        return encode(encode(str, encodeType), encodeType);
    }
}
