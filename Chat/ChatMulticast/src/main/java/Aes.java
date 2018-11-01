
import criptografia.AESCript;
import criptografia.CriptAES;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Aes {

    /**
     * Turns array of bytes into string
     *
     * @param buf Array of bytes to convert to hex string
     * @return Generated hex string
     */
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

    public static void main(String[] args) throws Exception {
        
        String chave = "qqqqwwwweeeerrrr";
        String message = "teste 1";

        String cript = CriptAES.encrypt(message, chave);
        String origin = CriptAES.decrypt(cript, chave);

        System.out.println("cript: " + cript);

        System.out.println("origin: " + origin);

    }
}
