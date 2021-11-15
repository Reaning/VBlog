package cn.lu.vblog.provider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * com.example.vblog.provider
 *
 * @author lkxBruce
 * @date 2021/11/9 20:46
 * @email lkxbruce@gmail.com
 * @project VBlog
 */

public class ValueProvider {

    private static final char[] hexDigits = "0123456789ABCDEF".toCharArray();

    public static String stringMD5(String input) {
        try {
            MessageDigest messageDigest =MessageDigest.getInstance("MD5");
            byte[] inputByteArray = input.getBytes();
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String byteArrayToHex(byte[] byteArray) {
        char[] resultCharArray =new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        return new String(resultCharArray);
    }
}
