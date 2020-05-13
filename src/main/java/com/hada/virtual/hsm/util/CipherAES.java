package com.hada.virtual.hsm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

public class CipherAES {
    private static final Logger log = LoggerFactory.getLogger(CipherAES.class);

    private static final String secretKey = "cms_secretKey";
    private static final String salt = "cms_salt";
    private static CipherAES instance;
    private Cipher cipherEncrypt;
    private Cipher cipherDecrypt;

    private CipherAES() throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec iv_spec = new IvParameterSpec(iv);

        cipherEncrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherEncrypt.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv_spec);
        log.info("Initialized CipherEncrypt");

        cipherDecrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, iv_spec);
        log.info("Initialized CipherDecrypt");
    }

    public static CipherAES getInstance() {
        try {
            if (instance == null)
                instance = new CipherAES();
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String encrypt(String strToEncrypt) {
        try {
            return Base64.getEncoder().encodeToString(getInstance().getCipherEncrypt().doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String strToDecrypt) {
        try {
            return new String(getInstance().getCipherDecrypt().doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cipher getCipherEncrypt() {
        return cipherEncrypt;
    }

    public Cipher getCipherDecrypt() {
        return cipherDecrypt;
    }

}
