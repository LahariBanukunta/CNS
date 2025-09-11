import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class AES_CBC_Simple {
    public static void main(String[] args) throws Exception {
        SecretKey key = KeyGenerator.getInstance("AES").generateKey(); // Generate random AES key
        IvParameterSpec iv = new IvParameterSpec(new byte[16]); // 16-byte zero IV
        String text = "Hello CBC Mode AES!";

        // Encrypt & print in one line
        Cipher encCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        encCipher.init(Cipher.ENCRYPT_MODE, key, iv);
        String encrypted = Base64.getEncoder().encodeToString(encCipher.doFinal(text.getBytes()));
        System.out.println("Encrypted: " + encrypted);

        // Decrypt & print in one line
        Cipher decCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        decCipher.init(Cipher.DECRYPT_MODE, key, iv);
        System.out.println("Decrypted: " + new String(decCipher.doFinal(Base64.getDecoder().decode(encrypted))));
    }
}
