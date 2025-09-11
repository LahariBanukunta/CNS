import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class SimpleRSA {
    public static void main(String[] args) throws Exception {
        // Generate RSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        String text = "Hello RSA Encryption!";

        // Encrypt + print in one line
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(text.getBytes()));
        System.out.println("Encrypted (Base64): " + encrypted);

        // Decrypt + print in one line
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        System.out.println("Decrypted: " + new String(cipher.doFinal(Base64.getDecoder().decode(encrypted))));
    }
}
