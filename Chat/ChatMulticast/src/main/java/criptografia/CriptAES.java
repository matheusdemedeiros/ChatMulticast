
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author aluno
 */
public class CriptAES {

    private static byte[] keyValue;

    /**
     * Encrypt a string with AES algorithm.
     *
     * @param data is a string
     * @return the encrypted string
     */
    public static String encrypt(String data) {
        Key key;
        try {
            key = generateKey();

            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encVal);
        } catch (Exception ex) {
            Logger.getLogger(CriptAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Decrypt a string with AES algorithm.
     *
     * @param encryptedData is a string
     * @return the decrypted string
     */
    public static String decrypt(String encryptedData) {
        Key key;
        try {
            key = generateKey();

            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = Base64.getMimeDecoder().decode(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            return new String(decValue, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            Logger.getLogger(CriptAES.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Generate a new encryption key.
     */
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, "AES");
    }

    public static void setChave(String key) {
        if (key.length() < 16) {
            while (key.length() < 16) {
                key = key.concat("X");
            }
        }
        CriptAES.keyValue = key.getBytes(StandardCharsets.UTF_8);
    }

    public static String getChave() {
        return new String(keyValue, StandardCharsets.UTF_8);
    }

}
