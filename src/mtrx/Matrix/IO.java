package mtrx.Matrix;

import java.io.*;
import mtrx.Utility.Utils;


public class IO {
    /* --------------------------- File Input ------------------------- */
    /* List All Files Di "../test" */
    public static File[] getListDir() {
        /* ALGORITMA */
        File curDir = new File("../test/txt");
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
            FileReader reader = new FileReader(String.format("../test/txt/%s", fileName));
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
            FileReader reader = new FileReader(String.format("../test/txt/%s", fileName));
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
        // file = new File(System.getProperty("user.dir") + "/src/mtrx/test/" + outputName);

            FileReader reader = new FileReader(String.format("../test/txt/%s", fileName));
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

    public static File getFile(String relativePath) {
        String fileName = new String();
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader readInput = new BufferedReader(streamReader);

        File file;

        do
        {
            Utils.println("Masukkan nama file yang ingin diproses : ");
            try {
                fileName = readInput.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            file = new File(relativePath + fileName);

            if (!(file.exists() && !file.isDirectory()))
            {
                Utils.println("File yang ingin diproses tidak ada. Mohon masukkan nama file yang benar.");
            }
        }

        while(!(file.exists() && !file.isDirectory()));

        return file;
    }



    /* -------------------------- File Output ------------------------- */
    
    /* Menulis Matrix Ke File */
    public static void writeFileMatrix(String fileName, Matrix m) {
        /* KAMUS LOKAL */
        int i, j;

        /* ALGORITMA */
        try {
            FileWriter writer = new FileWriter(String.format("../test/txt/%s", fileName));

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
            FileWriter writer = new FileWriter(String.format("../test/txt/%s.txt", fileNameOutput));
            writer.write(s);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // check file output
    public static String inputNewFileName(String relativePath) throws IOException{

        String outputName = new String();
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader readInput = new BufferedReader(streamReader);

        File outFile;
        boolean ignore = false;
        do
        {
            Utils.println("Masukkan nama file keluaran : ");
            try {
                outputName = readInput.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            outFile = new File(relativePath + outputName);

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

        return outputName;
    }
}
