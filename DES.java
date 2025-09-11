import javax.crypto.*;
import java.util.Base64;

public class Main {
    static String enc(String text, SecretKey key) throws Exception {
        Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(c.doFinal(text.getBytes()));
    }

    static String dec(String text, SecretKey key) throws Exception {
        Cipher c = Cipher.getInstance("DES/ECB/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, key);
        return new String(c.doFinal(Base64.getDecoder().decode(text)));
    }

    public static void main(String[] a) throws Exception {
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        String msg = "Hello DES!";
        String e = enc(msg, key);
        System.out.println("Encrypted: " + e);
        System.out.println("Decrypted: " + dec(e, key));
    }
}
