package com.horadrim.talrasha.site.utils;

import org.apache.commons.codec.binary.Base64;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Administrator on 2014/10/31.
 */
public final class SecretkeyUtil {

    public static String genericAuthToken(){
         return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateRandomNumberCode(int codeLength) {
        String sRand = "";
        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {
            sRand += random.nextInt(10);
        }
        return sRand;
    }
    //byte 数组与 int 的相互转换
    public static int byteArrayToInt(byte[] b) {
        return   b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[] {
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    public static int base64StrTOint(String str){
        return byteArrayToInt(Base64.decodeBase64(str.getBytes()));
    }
}
