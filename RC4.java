public class RC4 {

    private byte[] S = new byte[256];
    private int i = 0, j = 0;

    // Key-scheduling algorithm (KSA)
    public void init(byte[] key) {
        for (int i = 0; i < 256; i++)
            S[i] = (byte) i;
        int j = 0;
        for (int i = 0; i < 256; i++) {
            j = (j + (S[i] & 0xFF) + (key[i % key.length] & 0xFF)) & 0xFF;
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
        }
        this.i = 0;
        this.j = 0;
    }

    // Pseudo-random generation algorithm (PRGA)
    public byte[] process(byte[] data) {
        byte[] output = new byte[data.length];
        for (int k = 0; k < data.length; k++) {
            i = (i + 1) & 0xFF;
            j = (j + (S[i] & 0xFF)) & 0xFF;
            byte temp = S[i];
            S[i] = S[j];
            S[j] = temp;
            int t = ((S[i] & 0xFF) + (S[j] & 0xFF)) & 0xFF;
            byte keyStreamByte = S[t];
            output[k] = (byte) (data[k] ^ keyStreamByte);
        }
        return output;
    }

    // Example usage
    public static void main(String[] args) {
        String key = "SecretKey";
        String plaintext = "Hello RC4!";

        RC4 rc4 = new RC4();
        rc4.init(key.getBytes());

        byte[] encrypted = rc4.process(plaintext.getBytes());
        System.out.print("Encrypted (hex): ");
        for (byte b : encrypted) System.out.printf("%02X ", b);

        // To decrypt, re-initialize RC4 with the same key and process ciphertext
        RC4 rc4Decrypt = new RC4();
        rc4Decrypt.init(key.getBytes());
        byte[] decrypted = rc4Decrypt.process(encrypted);

        System.out.println("\nDecrypted: " + new String(decrypted));
    }
}
