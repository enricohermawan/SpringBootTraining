package com.example.demo.utils;

import java.security.MessageDigest;

public class MD5Hash {
    public static String md5(String input) {
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : messageDigest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
}
