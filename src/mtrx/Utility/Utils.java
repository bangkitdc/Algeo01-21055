package mtrx.Utility;

import java.io.*;

public class Utils {
    public static InputStreamReader streamReader = new InputStreamReader(System.in);
    private static BufferedReader bufferedReader = new BufferedReader(Utils.streamReader);

    /* Print String (without \n) */
    public static void print(Object arg0) {
        System.out.print(arg0);
    }

    /* Print String (with \n) */
    public static void println(Object arg0) {
        System.out.println(arg0);
    }

    /* Print String (with format -> printf(f:format, a:args)) */
    public static void printf(String format, Object arg0) {
        System.out.printf(format, arg0);
    }

    /* Cek Angka/ Bukan */ 
    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* Input Integer */ 
    public static int inputInt() throws IOException {
        print("> ");
        String input = bufferedReader.readLine();
        while (!isInt(input)) {
            printf("\"%s\" bukan Integer yang valid.\n", input);
            print("> ");
            input = bufferedReader.readLine();
        }
        return Integer.parseInt(input);
    }

    /* Input Integer Within Range (min, max) */ 
    public static int select(int min, int max) throws IOException {
        /* KAMUS LOKAL */
        int input; 

        /* ALGORITMA */
        input = inputInt();
        while (input > max || input < min) {
            println("Pilihan tidak ada. Silahkan input ulang!");
            input = inputInt();
        }
        return input;
    }

    /* Panjang Angka */
    public static int getLengthELMT(double d) {
        /* KAMUS LOKAL */
        String s;

        /* ALGORITMA */
        if (d == (int) d) {
            s = String.valueOf((int) d);
        } else {
            s = String.valueOf(d);
        }
        return s.length();
    }

    /* Input String (a/b) or double -> double */
    public static double eval(String s) {
        if (s.split("/").length == 1) {
            return Double.parseDouble(s);
        } else {
            return Double.parseDouble(s.split("/")[0]) / Double.parseDouble(s.split("/")[1]);
        }
    }
}
