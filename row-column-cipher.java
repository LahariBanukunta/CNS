public class RowColumnCipher {
    static String encrypt(String text, int row, int col) {
        text = text.replaceAll("[^A-Z]", "").toUpperCase();
        while (text.length() < row * col) text += "X";

        char[][] grid = new char[row][col];
        int k = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                grid[i][j] = text.charAt(k++);

        StringBuilder out = new StringBuilder();
        for (int j = 0; j < col; j++)
            for (int i = 0; i < row; i++)
                out.append(grid[i][j]);
        return out.toString();
    }

    static String decrypt(String text, int row, int col) {
        char[][] grid = new char[row][col];
        int k = 0;
        for (int j = 0; j < col; j++)
            for (int i = 0; i < row; i++)
                grid[i][j] = text.charAt(k++);

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                out.append(grid[i][j]);
        return out.toString();
    }

    public static void main(String[] args) {
        String plain = "HELLOWORLD";
        int row = 2, col = 5;
        String encrypted = encrypt(plain, row, col);
        String decrypted = decrypt(encrypted, row, col);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}

Encrypted: HWEOLRLLOD
Decrypted: HELLOWORLD

