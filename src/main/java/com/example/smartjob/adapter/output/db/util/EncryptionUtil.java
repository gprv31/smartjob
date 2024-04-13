package com.example.smartjob.adapter.output.db.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class EncryptionUtil {
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String ALGORITHM_SECRET_KEY = "DESede";
    private static final String TRANSFORMATION = "/ECB/PKCS5Padding";


    public static String encrypt(String text) {
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            KeySpec keySpec = new PBEKeySpec("password".toCharArray(), "salt".getBytes(), 65536, 192);
            SecretKey key = new SecretKeySpec(secretKeyFactory.generateSecret(keySpec).getEncoded(), ALGORITHM_SECRET_KEY);

            Cipher cipher = Cipher.getInstance(key.getAlgorithm() + TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedData = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Arrays.toString(encryptedData);
        } catch (Exception e) {
            return null;
        }

    }

    public static String decrypt(String text) {
        try {

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            KeySpec keySpec = new PBEKeySpec("password".toCharArray(), "salt".getBytes(), 65536, 192);
            SecretKey key = new SecretKeySpec(secretKeyFactory.generateSecret(keySpec).getEncoded(), ALGORITHM_SECRET_KEY);

            Cipher cipher = Cipher.getInstance(key.getAlgorithm() + TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, key);

            // Notice this
            byte[] decryptedData = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return Arrays.toString(decryptedData);

        } catch (Exception e) {
            return null;
        }
    }
}
