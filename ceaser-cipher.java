import java.util.Scanner;
public class Main
{
    public static String caesar(String text, int shift, boolean encrypt) {
        if (!encrypt) shift = 26 - shift;
        StringBuilder sb = new StringBuilder();
        text = text.toUpperCase();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = 'A';
                sb.append((char)((c - base + shift) % 26 + base));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter shift: ");
        int shift = sc.nextInt();
        sc.nextLine();
        System.out.print("Encryption: ");
        String result = caesar(text, shift, true);
        System.out.println(result);
        
        System.out.print("Decryption: ");
        result = caesar(result, shift, false);
        System.out.println(result);
        
    }


}

output:Enter shift: 3
Encryption: KHOOR
Decryption: HELLO
