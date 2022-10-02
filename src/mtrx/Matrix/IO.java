package mtrx.Matrix;

import java.io.*;
import mtrx.Utility.Utils;

public class IO {
    /* --------------------------- File Input ------------------------- */
    /* List All Files in relativePath */
    public static File[] getListDir(String relativePath) {
        /* ALGORITMA */
        File curDir = new File(relativePath);
        File[] listFiles = curDir.listFiles();
        return listFiles;
    }

    /* Output Ke Layar */
    public static void outputListDir(String relativePath) {
        /* KAMUS LOKAL */
        int i;

        /* ALGORITMA */
        File[] listFiles = getListDir(relativePath);
        for (i = 0; i < listFiles.length; i ++) {
            Utils.println(String.format("[%d] %s", i + 1, listFiles[i].getName()));
        }
    }

    /* Read Nanyaknya Row Matrix */
    public static int readRow(String fileName, String relativePath) {
        /* KAMUS LOKAL */
        int count = 0;

        /* ALGORITMA */
        try {
            FileReader reader = new FileReader(relativePath + fileName);
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
    public static int readCol(String fileName, String relativePath) {
        /* KAMUS LOKAL */
        int count = 0;
        int maxCol = 0;

        /* ALGORITMA */
        try {
            FileReader reader = new FileReader(relativePath + fileName);
            BufferedReader bufferReader = new BufferedReader(reader);

            while (bufferReader.readLine() != null) {
                String line = bufferReader.readLine();
                String[] lines = line.split(" ");
                count = lines.length;

                if (count > maxCol) {
                    maxCol = count;
                }
            }  
            
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxCol;
    }

    /* Membaca Matrix Keseluruhan Dalam File */
    public static Matrix readMatrix(String fileName, String relativePath) {
        /* KAMUS LOKAL */
        int i;
        Matrix mRes = new Matrix(readRow(fileName, relativePath), readCol(fileName, relativePath));

        /* ALGORITMA */
        try {

        // file = new File(System.getProperty("user.dir") + "/src/mtrx/test/" + outputName);

            FileReader reader = new FileReader(relativePath + fileName);

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

    public static File getFile(String relativePath, String defaultExtension) throws IOException{

        File file;

        Utils.println("");
        Utils.println("============ Pilih File ===========");
        Utils.println("[0] Ketik nama file");
        outputListDir(relativePath);

        File[] listFiles = IO.getListDir(relativePath);
        Utils.println("Masukkan pilihan: ");
        int input = Utils.select(0, listFiles.length);

        if (input != 0)
        {
            file = listFiles[input - 1];
        }

        else {
            String fileName = new String();
            InputStreamReader streamReader = new InputStreamReader(System.in);
            BufferedReader readInput = new BufferedReader(streamReader);

            do
            {
                Utils.println("Masukkan nama file yang ingin diproses : ");
                try {
                    fileName = readInput.readLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                file = new File(relativePath + getExtension(fileName, defaultExtension));

                if (!(file.exists() && !file.isDirectory()))
                {
                    Utils.println("File yang ingin diproses tidak ada. Mohon masukkan nama file yang benar.");
                }
            }

            while(!(file.exists() && !file.isDirectory()));

        }

        return file;
    }

    public static String getFileName(String relativePath) {
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

        return fileName;
    }



    /* -------------------------- File Output ------------------------- */
    
    public static String getExtension(String fileName, String defaultExtension)
    {
        String newFileName = fileName;

        if (!fileName.contains("."))
        {
            newFileName += defaultExtension;
        }

        return newFileName;
    }

    /* Menulis Matrix Ke File */
    public static void writeFileMatrix(Matrix m, String fileName, String relativePath) {
        /* KAMUS LOKAL */
        int i, j;
        String extension;
        /* ALGORITMA */

        extension = getExtension(fileName, ".txt");

        try {
            
            FileWriter writer = new FileWriter(relativePath + extension);

            for (i = 0; i < m.getRow(); i ++) {
                for (j = 0; j < m.getCol(); j ++) {
                    String temp = Utils.doubleToString(m.getELMT(i, j));
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

    public static void writeFileString(String s, String fileNameOutput, String relativePath) {

        String extension = getExtension(fileNameOutput, ".txt");

        try {
            FileWriter writer = new FileWriter(relativePath + extension);
            writer.write(s);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // check file output
    public static String inputNewFileName(String relativePath, String defaultExtension) throws IOException{

        String outputName = new String();
        String newName = new String();
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

            newName = getExtension(outputName, defaultExtension);

            outFile = new File(relativePath + newName);

            if ((outFile.exists() && !outFile.isDirectory()))
            {
                Utils.println(newName + " sudah ada pada folder tujuan. Apakah Anda ingin mengganti file tersebut?");
                Utils.println("(0 : tidak, 1 : ya)");

                int selectInput = Utils.select(0, 1);
                
                if (selectInput == 1)
                {
                    ignore = true;
                }
            }
        }
        while((outFile.exists() && !outFile.isDirectory()) && !ignore);

        return newName;
    }
}
