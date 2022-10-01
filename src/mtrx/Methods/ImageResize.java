package mtrx.Methods;

import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import mtrx.Matrix.*;
import mtrx.Utility.Utils;
import java.io.*;

public class ImageResize {
    public static BufferedImage scale(BufferedImage src, int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
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

    public static void processImage(File file, String outputName) throws IOException {
        // Reading the image
    
        
        BufferedImage img = ImageIO.read(file);

        int[][] red = new int[img.getWidth()][img.getHeight()];
        int[][] green = new int[img.getWidth()][img.getHeight()];
        int[][] blue = new int[img.getWidth()][img.getHeight()];
        int[][] alpha = new int[img.getWidth()][img.getHeight()];

        // RGB -> Matrix
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                // Pixel integer from RGB
                int pixel = img.getRGB(x, y);
                int a = (pixel >>> 24) & 0xff;

                // Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                // RGB Values
                red[x][y] = color.getRed();
                green[x][y] = color.getGreen();
                blue[x][y] = color.getBlue();
                alpha[x][y] = a;
            }
        }

        /* Fungsi Bicubic Untuk red[][] green[][] blue[][] */
        Matrix mRed = new Matrix(img.getWidth(), img.getHeight());
        Matrix mGreen = new Matrix(img.getWidth(), img.getHeight());
        Matrix mBlue = new Matrix(img.getWidth(), img.getHeight());
        Matrix mAlpha = new Matrix(img.getWidth(), img.getHeight());

        mRed.copyELMT(red);
        mGreen.copyELMT(green);
        mBlue.copyELMT(blue);
        mAlpha.copyELMT(alpha);

        int[][] newRed = ImageScaler.DoubleScale(mRed).getIntMatrix(0, 255);
        int[][] newGreen = ImageScaler.DoubleScale(mGreen).getIntMatrix(0, 255);
        int[][] newBlue = ImageScaler.DoubleScale(mBlue).getIntMatrix(0, 255);
        int[][] newAlpha = ImageScaler.DoubleScale(mAlpha).getIntMatrix(0, 255);

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
                int a = newAlpha[x][y];
                // Creating new Color object
                color = new Color(r, g, b, a);
                // Setting new Color object to the image
                img.setRGB(x, y, color.getRGB());
            

            }
        }
        // Saving the modified image
        file = new File(System.getProperty("user.dir") + "/src/mtrx/test/" + outputName);
        ImageIO.write(img, "png", file);
        System.out.println("Done...");
    }

    public static void driver() throws IOException {

        String fileName = new String();
        String outputName = new String();
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader readInput = new BufferedReader(streamReader);

        File file, outFile;

        do
        {
            Utils.println("Masukkan nama file yang ingin diproses : ");
            try {
                fileName = readInput.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = new File(System.getProperty("user.dir") + "/src/mtrx/test/" + fileName);

            if (!(file.exists() && !file.isDirectory()))
            {
                Utils.println("File yang ingin diproses tidak ada. Mohon masukkan nama file yang benar.");
            }
        }
        while(!(file.exists() && !file.isDirectory()));

        boolean ignore = false;
        do
        {
            Utils.println("Masukkan nama file keluaran : ");
            try {
                outputName = readInput.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            outFile = new File(System.getProperty("user.dir") + "/src/mtrx/test/" + outputName);

            if ((outFile.exists() && !outFile.isDirectory()))
            {
                Utils.println("Nama file sudah ada pada folder tujuan. Apakah Anda ingin mengganti file tersebut?");
                Utils.println("(0 : tidak, 1 : ya)");

                int selectInput = Utils.select(0, 1);
                
                if (selectInput == 1)
                {
                    ignore = true;
                }
            }
        }
        while((outFile.exists() && !outFile.isDirectory()) && !ignore);
        
        processImage(file, outputName);
    }
    public static void main(String args[]) throws IOException {

        driver();
        // Reading the image
        // Utils.println(System.getProperty("user.dir"));
        // File file = new File(System.getProperty("user.dir") + "/src/mtrx/test/test.png");
        
        // BufferedImage img = ImageIO.read(file);

        // int[][] red = new int[img.getWidth()][img.getHeight()];
        // int[][] green = new int[img.getWidth()][img.getHeight()];
        // int[][] blue = new int[img.getWidth()][img.getHeight()];
        // int[][] alpha = new int[img.getWidth()][img.getHeight()];

        // // RGB -> Matrix
        // for (int y = 0; y < img.getHeight(); y++) {
        //     for (int x = 0; x < img.getWidth(); x++) {
        //         // Pixel integer from RGB
        //         int pixel = img.getRGB(x, y);
        //         int a = (pixel >>> 24) & 0xff;

        //         // Creating a Color object from pixel value
        //         Color color = new Color(pixel, true);
        //         // RGB Values
        //         red[x][y] = color.getRed();
        //         green[x][y] = color.getGreen();
        //         blue[x][y] = color.getBlue();
        //         alpha[x][y] = a;
        //     }
        // }

        // /* Fungsi Bicubic Untuk red[][] green[][] blue[][] */
        // Matrix mRed = new Matrix(img.getWidth(), img.getHeight());
        // Matrix mGreen = new Matrix(img.getWidth(), img.getHeight());
        // Matrix mBlue = new Matrix(img.getWidth(), img.getHeight());
        // Matrix mAlpha = new Matrix(img.getWidth(), img.getHeight());

        // mRed.copyELMT(red);
        // mGreen.copyELMT(green);
        // mBlue.copyELMT(blue);
        // mAlpha.copyELMT(alpha);

        // int[][] newRed = ImageScaler.DoubleScale(mRed).getIntMatrix(0, 255);
        // int[][] newGreen = ImageScaler.DoubleScale(mGreen).getIntMatrix(0, 255);
        // int[][] newBlue = ImageScaler.DoubleScale(mBlue).getIntMatrix(0, 255);
        // int[][] newAlpha = ImageScaler.DoubleScale(mAlpha).getIntMatrix(0, 255);

        // img = scale(img, 2 * img.getWidth(), 2 * img.getHeight());

        // // Matrix -> RGB (Udah 2x scaled)
        // for (int y = 0; y < img.getHeight(); y++) {
        //     for (int x = 0; x < img.getWidth(); x++) {
        //         // Retrieving contents of a pixel
        //         int pixel = img.getRGB(x, y);
        //         // Creating a Color object from pixel value
        //         Color color = new Color(pixel, true);
        //         // Retrieving the R G B values
        //         int r = newRed[x][y];
        //         int g = newGreen[x][y];
        //         int b = newBlue[x][y];
        //         int a = newAlpha[x][y];
        //         // Creating new Color object
        //         color = new Color(r, g, b, a);
        //         // Setting new Color object to the image
        //         img.setRGB(x, y, color.getRGB());
            

        //     }
        // }
        // // Saving the modified image
        // file = new File(System.getProperty("user.dir") + "/src/mtrx/test/test2x.png");
        // ImageIO.write(img, "png", file);
        // System.out.println("Done...");
    }
}
