import java.util.*;

public class Main {
    static int[][] key = {{3, 3}, {2, 5}};  // Example invertible matrix mod 26

    static String process(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        if (text.length() % 2 != 0) text += "X";
        return text;
    }

    static int[][] inverseKey() {
        int det = (key[0][0]*key[1][1] - key[0][1]*key[1][0]) % 26;
        det = (det + 26) % 26;
        int invDet = -1;
        for (int i = 0; i < 26; i++)
            if ((det * i) % 26 == 1) { invDet = i; break; }
        int[][] inv = {
            {key[1][1], -key[0][1]},
            {-key[1][0], key[0][0]}
        };
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                inv[i][j] = ((inv[i][j] * invDet) % 26 + 26) % 26;
        return inv;
    }

    static String cipher(String text, boolean encrypt) {
        text = process(text);
        StringBuilder out = new StringBuilder();
        int[][] k = encrypt ? key : inverseKey();

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
Encrypted: HIAT
Decrypted: HELP
