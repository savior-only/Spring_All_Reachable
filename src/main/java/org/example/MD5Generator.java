package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class MD5Generator {
    public static String generateRandomMD5() {
        try {
            String randomString = UUID.randomUUID().toString();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(randomString.getBytes());
            byte[] digest = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}

