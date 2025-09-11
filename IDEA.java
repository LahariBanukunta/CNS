import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

public class IDEAWithBC {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        String keyString = "0123456789ABCDEF"; // 16 bytes = 128 bits
        byte[] keyBytes = keyString.getBytes();

        SecretKeySpec key = new SecretKeySpec(keyBytes, "IDEA");

        String plaintext = "Hello IDEA with BC!";

        Cipher cipher = Cipher.getInstance("IDEA/ECB/PKCS5Padding", "BC");

        // Encrypt
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(plaintext.getBytes());
        System.out.println("Encrypted (Base64): " + Base64.getEncoder().encodeToString(encrypted));

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encrypted);
        System.out.println("Decrypted: " + new String(decrypted));
    }
}
