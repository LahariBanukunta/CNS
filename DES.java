import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Main {

    // Encrypt plaintext with DES key
    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    // Decrypt ciphertext with DES key
    public static String decrypt(String cipherText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }

    public static void main(String[] args) throws Exception {
        // Generate a DES key
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        SecretKey secretKey = keyGen.generateKey();

        String message = "Hello DES!";
        System.out.println("Original: " + message);

        String encrypted = encrypt(message, secretKey);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, secretKey);
        System.out.println("Decrypted: " + decrypted);
    }
}
Original: Hello DES!
Encrypted: ZX3chxmt28VsRqiygqUxCg==
Decrypted: Hello DES!
