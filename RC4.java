import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class RC4Builtin {
    public static void main(String[] args) throws Exception {
        String keyStr = "SecretKey", msg = "Hello RC4!";
        SecretKey key = new SecretKeySpec(keyStr.getBytes(), "RC4");

        Cipher enc = Cipher.getInstance("RC4");
        enc.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = enc.doFinal(msg.getBytes());
        System.out.println("E: " + Base64.getEncoder().encodeToString(encrypted));

        Cipher dec = Cipher.getInstance("RC4");
        dec.init(Cipher.DECRYPT_MODE, key);
        System.out.println("D: " + new String(dec.doFinal(encrypted)));
    }
}
