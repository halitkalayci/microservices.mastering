package com.kodlamaio.categoryservice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class KeyUtil
{
    private static final String ALGORITHM = "RSA";
    public static KeyPair loadKeyPair(String path) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        File privateKeyFile = new File(path+"/private.key");
        File publicKeyFile = new File(path+"/public.key");

        if(!privateKeyFile.exists() || !publicKeyFile.exists())
        {
            KeyPair keyPair = generateNewPair();
            saveKeyPair(path, keyPair);
            return keyPair;
        }

        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

        byte[] privateKeyString = Base64.getDecoder().decode(new String(readFile(privateKeyFile)));
        byte[] publicKeyString = Base64.getDecoder().decode(new String(readFile(publicKeyFile)));

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyString);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyString);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        return new KeyPair(publicKey, privateKey);
    }

    public static KeyPair generateNewPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static void saveKeyPair(String path, KeyPair keyPair) throws IOException {
        File directory = new File(path);
        if(!directory.exists())
            directory.mkdir();

        File privateKeyFile = new File(path + "/private.key");

        privateKeyFile.createNewFile();

        File publicKeyFile = new File(path+"/public.key");

        publicKeyFile.createNewFile();

        try(FileOutputStream fs = new FileOutputStream(privateKeyFile))
        {
            fs.write(Base64.getEncoder().encode(keyPair.getPrivate().getEncoded()));
        }

        try(FileOutputStream fs = new FileOutputStream(publicKeyFile))
        {
            fs.write(Base64.getEncoder().encode(keyPair.getPublic().getEncoded()));
        }
    }

    private static byte[] readFile(File file) throws IOException
    {
        try(FileInputStream fs = new FileInputStream(file))
        {
            return fs.readAllBytes();
        }
    }
}
