package com.mit.pawin.util;

import com.mit.pawin.controller.AuthenticationController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AES {

    private static SecretKeySpec secretKey;
    private static byte[] key;

    private static final Logger log = LoggerFactory.getLogger(AES.class);

    public static void setKey(String myKey)
    {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ myKey={}}", myKey);

        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {

            log.info("Exception {}", e.getMessage());

        }
        catch (UnsupportedEncodingException e) {

            log.info("Exception {}", e.getMessage());

        }
    }

    public static String encrypt(String strToEncrypt, String secret)
    {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ strToEncrypt={}, secret={}}", strToEncrypt, secret);

        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            log.info("Exception {}", e.getMessage());

        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret)
    {

        String methodNameUsingClassInstance = new AuthenticationController() {}.getClass().getEnclosingMethod().getName();
        log.info("Calling  " + methodNameUsingClassInstance + "()" + "{ strToDecrypt={}, secret={}}", strToDecrypt, secret);

        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {

            log.info("Exception {}", e.getMessage());

        }
        return null;
    }
}
