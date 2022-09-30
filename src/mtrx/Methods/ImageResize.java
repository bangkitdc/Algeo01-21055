package mtrx.Methods;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import mtrx.Matrix.*;
import mtrx.Utility.Utils;

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

        File file = new File("D:/JavaProject/Tubes Algeo/Algeo01-21055/src/mtrx/test/test2x.png");
        
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

        /* Fungsi Bicubic Untuk red[][] green[][] blue[][] */
        Matrix mRed = new Matrix(img.getWidth(), img.getHeight());
        Matrix mGreen = new Matrix(img.getWidth(), img.getHeight());
        Matrix mBlue = new Matrix(img.getWidth(), img.getHeight());

        mRed.copyELMT(red);
        mGreen.copyELMT(green);
        mBlue.copyELMT(blue);

        int[][] newRed = ImageScaler.DoubleScale(mRed).getIntMatrix(0, 255);
        int[][] newGreen = ImageScaler.DoubleScale(mGreen).getIntMatrix(0, 255);
        int[][] newBlue = ImageScaler.DoubleScale(mBlue).getIntMatrix(0, 255);

        img = scale(img, 2 * img.getWidth(), 2 * img.getHeight());

        // Matrix -> RGB (Udah 2x scaled)
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                // Retrieving contents of a pixel
                int pixel = img.getRGB(x, y);
                // Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                // Retrieving the R G B values
                int r = newRed[x][y];
                int g = newGreen[x][y];
                int b = newBlue[x][y];

                // Creating new Color object
                color = new Color(r, g, b);
                // Setting new Color object to the image
                img.setRGB(x, y, color.getRGB());

            }
        }
        // Saving the modified image
        file = new File("D:/JavaProject/Tubes Algeo/Algeo01-21055/src/mtrx/test/test2x.png");
        ImageIO.write(img, "png", file);
        System.out.println("Done...");
    }
}
