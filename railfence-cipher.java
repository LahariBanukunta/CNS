public class RailFenceCipher {
    static String encrypt(String text, int rails) {
        StringBuilder[] rows = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) rows[i] = new StringBuilder();

        for (int i = 0, r = 0, dir = 1; i < text.length(); i++) {
            rows[r].append(text.charAt(i));
            if (r == 0) dir = 1;
            else if (r == rails - 1) dir = -1;
            r += dir;
        }
        return String.join("", rows);
    }

    static String decrypt(String text, int rails) {
        int len = text.length();
        boolean[] pattern = new boolean[len];
        int r = 0, dir = 1, idx = 0;

        // mark zigzag pattern
        for (int i = 0; i < len; i++) {
            pattern[i] = (r == 0 || r == rails - 1);
            if (r == 0) dir = 1;
            else if (r == rails - 1) dir = -1;
            r += dir;
        }

        // fill chars row-wise
        char[] res = new char[len];
        for (int row = 0; row < rails; row++) {
            r = 0; dir = 1;
            for (int i = 0; i < len; i++) {
                if (r == row) res[i] = text.charAt(idx++);
                if (r == 0) dir = 1;
                else if (r == rails - 1) dir = -1;
                r += dir;
            }
        }
        return new String(res);
    }

    public static void main(String[] args) {
        String plain = "HELLO_WORLD";
        int rails = 3;
        String enc = encrypt(plain, rails);
        String dec = decrypt(enc, rails);
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}

Encrypted: HOREL_OLLWD
Decrypted: HELLO_WORLD
