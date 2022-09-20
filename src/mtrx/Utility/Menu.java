package mtrx.Utility;

import java.io.*;

public class Menu extends Utils{
    
    public static void menuLoop() throws IOException {
        mainMenu();
        int choice = select(1, 7);
        System.out.println(choice);
    }
    
    public static void mainMenu() {
        print("Selamat Datang di Matrix Calculator");
        print("=========== Menu ===========");
        print("Aplikasi ini akan menyelesaikan persoalan:");
        print("[1] Sistem Persamaan Linear (SPL)");
        print("[2] Determinan");
        print("[3] Matriks Balikan (Inverse)");
        print("[4] Interpolasi Polinom");
        print("[5] Interpolasi Bicubic");
        print("[6] Regresi Linear Berganda");
        print("");
        print("[7] Keluar");
    }
}
