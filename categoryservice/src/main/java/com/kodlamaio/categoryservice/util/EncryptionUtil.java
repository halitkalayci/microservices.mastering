package com.kodlamaio.categoryservice.util;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class EncryptionUtil {
    private static final String TRANSFORMATION="RSA/ECB/PKCS1Padding";

    public static String encrypt(String plainData, PublicKey publicKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(plainData.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData, PrivateKey privateKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = Base64.getDecoder().decode(encryptedData);
        return new String(cipher.doFinal(decryptedData));
    }
}
