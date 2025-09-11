public class RailFenceCipher {
    static String encrypt(String text, int rails) {
        StringBuilder[] fence = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) fence[i] = new StringBuilder();

        int rail = 0, dir = 1;
        for (char c : text.toCharArray()) {
            fence[rail].append(c);
            rail += dir;
            if (rail == 0 || rail == rails - 1) dir *= -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : fence) result.append(row);
        return result.toString();
    }

    static String decrypt(String text, int rails) {
        int len = text.length();
        boolean[][] mark = new boolean[rails][len];

        int rail = 0, dir = 1;
        for (int i = 0; i < len; i++) {
            mark[rail][i] = true;
            rail += dir;
            if (rail == 0 || rail == rails - 1) dir *= -1;
        }

        char[][] railMatrix = new char[rails][len];
        int index = 0;
        for (int i = 0; i < rails; i++)
            for (int j = 0; j < len; j++)
                if (mark[i][j])
                    railMatrix[i][j] = text.charAt(index++);

        StringBuilder result = new StringBuilder();
        rail = 0; dir = 1;
        for (int i = 0; i < len; i++) {
            result.append(railMatrix[rail][i]);
            rail += dir;
            if (rail == 0 || rail == rails - 1) dir *= -1;
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String plain = "HELLO_WORLD";
        int depth = 3;
        String encrypted = encrypt(plain, depth);
        String decrypted = decrypt(encrypted, depth);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
Encrypted: HOREL_OLLWD
Decrypted: HELLO_WORLD
