/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author matheus
 */
public class AESCript {

    private KeyGenerator kgen;
    private SecretKey skey;
    private byte[] raw;
    private SecretKeySpec skeySpec;
    private Cipher cipher;
    private byte[] encrypted;
    private byte[] original;
    private String originalString;
    private String key;

    public AESCript(String key) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.key = key;
        this.kgen = KeyGenerator.getInstance(this.key);
        this.kgen.init(256);

        this.skey = this.kgen.generateKey();

        this.raw = this.skey.getEncoded();
        this.skeySpec = new SecretKeySpec(this.raw, this.key);

        // Instantiate the cipher
        this.cipher = Cipher.getInstance(this.key);
    }

    public void encrypt(String message) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.ENCRYPT_MODE, this.skeySpec);
        this.encrypted = this.cipher.doFinal(message.getBytes());
        System.out.println("encrypted string: " + asHex(this.encrypted));
        //return asHex(this.encrypted);

    }

    public void decrypt(String message) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        this.cipher.init(Cipher.DECRYPT_MODE, this.skeySpec);
        this.original = this.cipher.doFinal(this.encrypted);
        this.originalString = new String(this.original);
        System.out.println("Original string: " + originalString);
    }

    public static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10) {
                strbuf.append("0");
            }

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

}
