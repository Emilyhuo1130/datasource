package com.other.md5;

import java.security.MessageDigest;

public class EncodeMD5Test {
	/**
	 * MD5加密 测试
	 * **/
//	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
//	    "e", "f" };
	private final static String[] hexDigits = { "f", "e", "d", "c", "b", "a", "6", "7", "8", "9", "5", "4", "3", "2",
	    "1", "0" };
	public static void main(String[] args) {
		String ss="12345678";
		System.out.println(md5Encode(ss.getBytes()));

	}
	public static String md5Encode(byte[] b) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(b));
        } catch (Exception ex) {
            resultString = null;
        }
        return resultString;
    }
	public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
//670b14728ad9902aecba32e22fa4f6bd
//6710bb1437258a2d949092a8ec2ba1321e2c2fda46f69bd2

}
