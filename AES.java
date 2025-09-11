import javax.crypto.*;
import java.util.Base64;

public class SimpleAES {
    static String enc(String text, SecretKey key) throws Exception {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(c.doFinal(text.getBytes()));
    }

    static String dec(String text, SecretKey key) throws Exception {
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, key);
        return new String(c.doFinal(Base64.getDecoder().decode(text)));
    }

    public static void main(String[] a) throws Exception {
        SecretKey key = KeyGenerator.getInstance("AES").generateKey();
        String msg = "Hello AES!";
        String e = enc(msg, key);
        System.out.println("Encrypted: " + e);
        System.out.println("Decrypted: " + dec(e, key));
    }
}
