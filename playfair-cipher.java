import java.util.*;

public class Main {

    static char[][] matrix(String key) {
        key = key.toUpperCase().replaceAll("[J]", "I").replaceAll("[^A-Z]", "");
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for (char c : (key + "ABCDEFGHIKLMNOPQRSTUVWXYZ").toCharArray()) set.add(c);
        char[][] m = new char[5][5];
        Iterator<Character> it = set.iterator();
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                m[i][j] = it.next();
        return m;
    }

    static String prepare(String text) {
        text = text.toUpperCase().replaceAll("[J]", "I").replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';
            if (a == b) { sb.append(a).append('X'); i--; }
            else sb.append(a).append(b);
        }
        if (sb.length() % 2 != 0) sb.append('X');
        return sb.toString();
    }

    static int[] find(char c, char[][] m) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (m[i][j] == c) return new int[]{i, j};
        return null;
    }

    static String playfair(String text, String key, boolean encrypt) {
        char[][] m = matrix(key);
        text = prepare(text);
        StringBuilder out = new StringBuilder();
        int shift = encrypt ? 1 : -1;

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] p1 = find(a, m), p2 = find(b, m);
            if (p1[0] == p2[0])
                out.append(m[p1[0]][(p1[1] + shift + 5) % 5])
                   .append(m[p2[0]][(p2[1] + shift + 5) % 5]);
            else if (p1[1] == p2[1])
                out.append(m[(p1[0] + shift + 5) % 5][p1[1]])
                   .append(m[(p2[0] + shift + 5) % 5][p2[1]]);
            else
                out.append(m[p1[0]][p2[1]])
                   .append(m[p2[0]][p1[1]]);
        }
        return out.toString();
    }

    public static void main(String[] args) {
        String key = "MONARCHY";
        String plain = "HELLO WORLD";
        String encrypted = playfair(plain, key, true);
        String decrypted = playfair(encrypted, key, false);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}


Encrypted: CFSUPMVNMTBZ
Decrypted: HELXLOWORLDX
