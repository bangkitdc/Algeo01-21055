package mtrx.Methods;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import mtrx.Matrix.*;
import java.io.*;

public class ImageResize {

    final static double oneThird = ((double) 1) / ((double) 3);

    public static Matrix DoubleScale(Matrix m){
        // PREKONDISI : jumlah elemen matriks >= 16
        // KAMUS LOKAL

        Matrix mRes, bicubicInterpolationRes;
        int currentRow, currentCol, i, j, x, y, xLowerMid, yLowerMid, deltaX, deltaY, countInterpolateX, countInterpolateY, currentRowRecord, currentColRecord;
        double interpolateIdxX, interpolateIdxY, upperLimit, lowerLimit;
        boolean midX, midY;

        // ALGORITMA

        if(m.getRow() < 4 || m.getCol() < 4) {
            return m;
        }

        mRes = new Matrix(m.getRow() * 2, m.getCol() * 2);
        // hanya elemen pada lowerMid dan lowerMid+1 yang akan disisipi dua baris atau dua kolom hasil interpolasi secara berurutan
        // dengan x dan y bukan bilangan bulat
        xLowerMid = m.getRow() % 2 == 0? (m.getRow() - 1) : (m.getRow() - 2);
        yLowerMid = m.getCol() % 2 == 0? (m.getCol() - 1) : (m.getCol() - 2);

        currentRow = 0;

        for(i = 0; i < m.getRow() - 3; i++) {

            currentRowRecord = currentRow;
        
            // decide how many row to insert
            // midX tells that if this iteration will insert 2 rows of interpolation results

            if (i == 0) {
                deltaX = 4;

                midX = ((currentRow + deltaX - 1 == xLowerMid)? true : false);
                deltaX += ((currentRow + deltaX - 1 == xLowerMid)? 2 : 1);
            
            }

            else {
                deltaX = ((currentRow == xLowerMid)? 3 : 2);
                midX = ((currentRow == xLowerMid)? true : false);
            }

            if (i + 3 == m.getLastRow())
            {
                deltaX += 2;
            }

            currentCol = 0;

            for (j = 0; j < m.getCol() - 3; j++)
            {
                currentColRecord = currentCol;

                // midY tells that if this iteration will insert 2 columns of interpolation results
                if (j == 0) {
                    deltaY = 4;
    
                    midY = ((currentCol + deltaY - 1 == yLowerMid)? true : false);
                    deltaY += ((currentCol + deltaY - 1 == yLowerMid)? 2 : 1);
                
                }
    
                else {
                    deltaY = ((currentCol == yLowerMid)? 3 : 2);
                    midY = ((currentCol == yLowerMid)? true : false);
                }


                if (j + 3 == m.getLastCol())
                {
                    deltaY += 2;
                }

                bicubicInterpolationRes = BicubicInterpolation.Bicubic(m.getSubMatrix(i, i+3, j, j+3));

                currentRow = currentRowRecord;
                countInterpolateX = 0;

                if (i == 0)
                {
                    interpolateIdxX = -1;
                }

                else 
                {
                    interpolateIdxX = (currentRow == xLowerMid)? (oneThird) : 0.5;
                }

                for (x = 0; x < deltaX; x++)
                {
                    currentCol = currentColRecord;
                    countInterpolateY = 0;

                    if (j == 0){
                        interpolateIdxY = -1;
                    }

                    else {
                        interpolateIdxY = (currentCol == yLowerMid)? (oneThird) : 0.5;
                    }
  
                    for (y = 0; y < deltaY; y++)
                    {

                        // x is horizontal in cartecius (becomes column) and y becomes row
                        mRes.setELMT(currentRow, currentCol, 
                                        BicubicInterpolation.getBicubicInterpolation(bicubicInterpolationRes, 
                                            interpolateIdxY, interpolateIdxX));

                        countInterpolateY += 1;
                        currentCol++;

                        if (midY) {
                            // Jika iterasi saat ini mencakup lowerMid
                            if (j == 0)
                            {
                                upperLimit = 5;
                            }

                            else {
                                upperLimit = 2;
                            }

                            lowerLimit = upperLimit - 2;

                            if (countInterpolateY == upperLimit)
                            {
                                interpolateIdxY = 1;
                            }

                            else if (countInterpolateY > upperLimit)
                            {
                                interpolateIdxY += 0.5;
                            }

                            // countInterpolateY < upperLimit
                            else {
                                interpolateIdxY += ((countInterpolateY < lowerLimit)? 0.5 : oneThird);

                            }
                            
                        }

                        else {
                            interpolateIdxY += 0.5;
                        }
                    
                    
                    }

                    countInterpolateX += 1;
                    currentRow++;
            
                    if (midX) {
                        // Jika iterasi saat ini mencakup lowerMid
                        if (i == 0)
                        {
                            upperLimit = 5;
                        }

                        else {
                            upperLimit = 2;
                        }

                        lowerLimit = upperLimit - 2;

                        if (countInterpolateX == upperLimit)
                        {
                            interpolateIdxX = 1;
                        }

                        else if (countInterpolateX > upperLimit)
                        {
                            interpolateIdxX += 0.5;
                        }

                        // countInterpolateX < upperLimit
                        else {
                            interpolateIdxX += ((countInterpolateX < lowerLimit)? 0.5 : oneThird);

                        }             
                    }

                    else {
                        interpolateIdxX += 0.5;
                    }
                }            
            }
            
        }

        return mRes;
    }

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

    public static void processImage(File file, String outputName, String relativePath) throws IOException {
        // Reading the image
    
        System.out.println("Please Wait...");

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

        int[][] newRed = DoubleScale(mRed).getIntMatrix(0, 255);
        int[][] newGreen = DoubleScale(mGreen).getIntMatrix(0, 255);
        int[][] newBlue = DoubleScale(mBlue).getIntMatrix(0, 255);
        int[][] newAlpha = DoubleScale(mAlpha).getIntMatrix(0, 255);

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
        file = new File(relativePath + outputName);
        ImageIO.write(img, "png", file);

        System.out.println("Done...");

        System.out.println("File output disimpin di path : " + file.getCanonicalPath());
    }

    public static void inputImage(String relativePath) throws IOException {

        File file = IO.getFile(relativePath, ".png");
        String outputName = IO.inputNewFileName(relativePath, ".png");
        
        processImage(file, outputName, relativePath);
    }
}
