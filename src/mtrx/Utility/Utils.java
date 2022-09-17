package mtrx.Utility;

import java.io.*;

public class Utils {
    private static InputStreamReader streamReader = new InputStreamReader(System.in);
    private static BufferedReader bufferedReader = new BufferedReader(Utils.streamReader);

    // Print String
    public static void print(String s) {
        System.out.println(s);
    }

    // Cek Angka/ Bukan
    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Input Integer
    public static int inputInt() throws IOException {
        System.out.print("> ");
        String input = bufferedReader.readLine();
        while (!isInt(input)) {
            System.out.printf("\"%s\" bukan Integer yang valid.\n", input);
            System.out.print("> ");
            input = bufferedReader.readLine();
        }
        return Integer.parseInt(input);
    }

    // Input Integer Within Range (min, max)
    public static int select(int min, int max) throws IOException {
        int input = inputInt();
        while (input > max || input < min) {
            System.out.println("Pilihan tidak ada. Silahkan input ulang!");
            input = inputInt();
        }
        return input;
    }
}
