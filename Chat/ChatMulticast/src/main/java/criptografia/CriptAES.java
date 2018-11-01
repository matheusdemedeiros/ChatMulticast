
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author aluno
 */
public class CriptAES {

    private static SecretKeySpec chaveSecreta;
    private static byte[] chave;

    public static void setKey(String minhaChave) {
        MessageDigest sha = null;
        try {
            CriptAES.chave = minhaChave.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            CriptAES.chave = sha.digest(CriptAES.chave);
            CriptAES.chave = Arrays.copyOf(CriptAES.chave, 16);
            chaveSecreta = new SecretKeySpec(CriptAES.chave, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String mensagem, String chave) {
        try {
            setKey(chave);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, chaveSecreta);
            return Base64.getEncoder().encodeToString(cipher.doFinal(mensagem.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Erro durante a encriptação: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String mensagem, String chave) {
        try {
            setKey(chave);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, chaveSecreta);
            String retorno = new String(cipher.doFinal(Base64.getDecoder().decode(mensagem)));
            System.out.println("retorno " + retorno);
            return retorno;
        } catch (Exception e) {
            System.out.println("Erro durante a desencriptação: " + e.toString());
        }
        return null;
    }
}
