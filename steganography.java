import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class SimpleSteganography {

    // Hide message in LSB of image pixels
    public static BufferedImage hideMessage(BufferedImage img, String msg) {
        msg += '\0'; // delimiter to mark end
        int msgIndex = 0, bitIndex = 7;
        outer: for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int bit = ((msg.charAt(msgIndex) >> bitIndex) & 1);
                rgb = (rgb & 0xFFFFFFFE) | bit; // set LSB of blue channel (or whole rgb here)
                img.setRGB(x, y, rgb);
                bitIndex--;
                if (bitIndex < 0) {
                    bitIndex = 7;
                    msgIndex++;
                    if (msgIndex == msg.length()) break outer;
                }
            }
        }
        return img;
    }

    // Retrieve hidden message from image LSBs
    public static String retrieveMessage(BufferedImage img) {
        StringBuilder msg = new StringBuilder();
        int bitIndex = 7;
        int currentChar = 0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int bit = rgb & 1;
                currentChar |= (bit << bitIndex);
                bitIndex--;
                if (bitIndex < 0) {
                    if (currentChar == 0) return msg.toString(); // end delimiter
                    msg.append((char) currentChar);
                    bitIndex = 7;
                    currentChar = 0;
                }
            }
        }
        return msg.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedImage img = ImageIO.read(new File("input.png"));

        // Hide
        BufferedImage encoded = hideMessage(img, "Hello Steganography!");
        ImageIO.write(encoded, "png", new File("encoded.png"));

        // Retrieve
        BufferedImage encodedImg = ImageIO.read(new File("encoded.png"));
        String secret = retrieveMessage(encodedImg);
        System.out.println("Hidden message: " + secret);
    }
}
