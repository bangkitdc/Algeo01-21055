package mtrx.Methods;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageResize {
    public static BufferedImage scale(BufferedImage src, int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        int x, y;
        int ww = src.getWidth();
        int hh = src.getHeight();
        int[] ys = new int[h];
        for (y = 0; y < h; y++)
            ys[y] = y * hh / h;
        for (x = 0; x < w; x++) {
            int newX = x * ww / w;
            for (y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
                img.setRGB(x, y, col);
            }
        }
        return img;
    }

    public static void main(String args[]) throws IOException {
        // Reading the image
        File file = new File("../test/butterfly.png");
        BufferedImage img = ImageIO.read(file);

        int[][] red = new int[img.getWidth()][img.getHeight()];
        int[][] green = new int[img.getWidth()][img.getHeight()];
        int[][] blue = new int[img.getWidth()][img.getHeight()];

        // RGB -> Matrix
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                // Pixel integer from RGB
                int pixel = img.getRGB(x, y);
                // Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                // RGB Values
                red[x][y] = color.getRed();
                green[x][y] = color.getGreen();
                blue[x][y] = color.getBlue();
            }
        }
        img = scale(img, 2 * img.getWidth(), 2 * img.getHeight());

        /* Fungsi Bicubic Untuk red[][] green[][] blue[][] */

        // Matrix -> RGB (Udah 2x scaled)
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                // Retrieving contents of a pixel
                int pixel = img.getRGB(x, y);
                // Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                // Retrieving the R G B values
                int r = red[x][y];
                int g = green[x][y];
                int b = blue[x][y];

                // Creating new Color object
                color = new Color(r, g, b);
                // Setting new Color object to the image
                img.setRGB(x, y, color.getRGB());

            }
        }
        // Saving the modified image
        file = new File("../test/butterfly2X.png");
        ImageIO.write(img, "png", file);
        System.out.println("Done...");
    }
}
