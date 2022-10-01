package mtrx.Matrix;

import java.io.*;
import mtrx.Utility.Utils;

public class IO {
    /* --------------------------- File Input ------------------------- */
    /* List All Files Di "../test" */
    public static File[] getListDir() {
        /* ALGORITMA */
        File curDir = new File("../test");
        File[] listFiles = curDir.listFiles();
        return listFiles;
    }

    /* Output Ke Layar */
    public static void outputListDir() {
        /* KAMUS LOKAL */
        int i;

        /* ALGORITMA */
        File[] listFiles = getListDir();
        for (i = 0; i < listFiles.length; i ++) {
            Utils.println(String.format("[%d] %s", i + 1, listFiles[i].getName()));
        }
    }

    /* Read Nanyaknya Row Matrix */
    public static int readRow(String fileName) {
        /* KAMUS LOKAL */
        int count = 0;

        /* ALGORITMA */
        try {
            FileReader reader = new FileReader(String.format("../test/%s", fileName));
            BufferedReader bufferReader = new BufferedReader(reader);

            while(bufferReader.readLine() != null) {
                count += 1;
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /* Read Banyaknya Col Matrix */
    public static int readCol(String fileName) {
        /* KAMUS LOKAL */
        int count = 0;

        /* ALGORITMA */
        try {
            FileReader reader = new FileReader(String.format("../test/%s", fileName));
            BufferedReader bufferReader = new BufferedReader(reader);

            String line = bufferReader.readLine();
            String[] lines = line.split(" ");
            count = lines.length;
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /* Membaca Matrix Keseluruhan Dalam File */
    public static Matrix readMatrix(String fileName) {
        /* KAMUS LOKAL */
        int i;
        Matrix mRes = new Matrix(readRow(fileName), readCol(fileName));

        /* ALGORITMA */
        try {
            FileReader reader = new FileReader(String.format("../test/%s", fileName));
            BufferedReader bufferReader = new BufferedReader(reader);

            String line;
            int count = 0;
            while((line = bufferReader.readLine()) != null) {
                String[] lines = line.split(" ");
                for (i = 0; i < lines.length; i ++) {
                    double temp = Utils.eval(lines[i]);
                    mRes.setELMT(count, i, temp);
                }
                count += 1;
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mRes;
    }

    /* -------------------------- File Output ------------------------- */
    
    /* Menulis Matrix Ke File */
    public static void writeFileMatrix(String fileName, Matrix m) {
        /* KAMUS LOKAL */
        int i, j;

        /* ALGORITMA */
        try {
            FileWriter writer = new FileWriter(String.format("../test%s", fileName));

            for (i = 0; i < m.getRow(); i ++) {
                for (j = 0; j < m.getCol(); j ++) {
                    String temp = Utils.result(m.getELMT(i, j));
                    writer.write(temp);
                    writer.write(" ");
                }
                writer.write("\n");
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFileString(String fileNameOutput, String s) {
        try {
            FileWriter writer = new FileWriter(String.format("../test/%s.txt", fileNameOutput));
            writer.write(s);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
