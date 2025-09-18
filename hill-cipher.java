import java.util.*;

public class Main {
    // Original encryption key
    static int[][] key = {{3, 3}, {2, 5}};
    
    // Precomputed inverse key (mod 26)
    static int[][] inverseKey = {{15, 17}, {20, 9}};

    static String process(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        if (text.length() % 2 != 0) text += "X";
        return text;
    }

    static String cipher(String text, boolean encrypt) {
        text = process(text);
        StringBuilder out = new StringBuilder();
        int[][] k = encrypt ? key : inverseKey;

        for (int i = 0; i < text.length(); i += 2) {
            int[] v = {text.charAt(i) - 'A', text.charAt(i + 1) - 'A'};
            for (int r = 0; r < 2; r++) {
                int val = k[r][0] * v[0] + k[r][1] * v[1];
                out.append((char) ((val % 26 + 26) % 26 + 'A'));
            }
        }
        return out.toString();
    }

    public static void main(String[] args) {
        String plain = "HELP";
        String encrypted = cipher(plain, true);
        String decrypted = cipher(encrypted, false);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
