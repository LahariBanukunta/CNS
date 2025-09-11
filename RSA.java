import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class SimpleRSA {

    public static void main(String[] args) throws Exception {
        // Generate RSA key pair (2048 bits)
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String plaintext = "Hello RSA Encryption!";

        // Encrypt with public key
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = encryptCipher.doFinal(plaintext.getBytes());

        String encryptedBase64 = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted (Base64): " + encryptedBase64);

        // Decrypt with private key
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);

        String decrypted = new String(decryptedBytes);
        System.out.println("Decrypted: " + decrypted);
    }
}
