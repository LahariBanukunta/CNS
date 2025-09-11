import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class SimpleRC4 {
    public static void main(String[] args) throws Exception {
        String key = "SecretKey";
        String text = "Hello RC4!";

        SecretKeySpec rc4Key = new SecretKeySpec(key.getBytes(), "RC4");
        Cipher cipher = Cipher.getInstance("RC4");

        // Encrypt in one line
        cipher.init(Cipher.ENCRYPT_MODE, rc4Key);
        System.out.println("Encrypted: " +
                Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes())));

        // Decrypt in one line
        cipher.init(Cipher.DECRYPT_MODE, rc4Key);
        System.out.println("Decrypted: " +
                new String(cipher.doFinal(Base64.getDecoder().decode(
                        Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes())))
                )));
    }
}
