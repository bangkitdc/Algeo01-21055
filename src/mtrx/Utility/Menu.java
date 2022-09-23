package mtrx.Utility;

import java.io.*;
import mtrx.Matrix.*;
import mtrx.Methods.*;

public class Menu extends Utils{
    
    public static void menuLoop() throws IOException {
        mainMenu();
        // Tester
        double test;

        Matrix m = new Matrix(3, 3);
        m.setELMT(0, 0, 3);
        m.setELMT(0, 1, 2);
        m.setELMT(0, 2, 2);
        m.setELMT(1, 0, 10);
        m.setELMT(1, 1, 2);
        m.setELMT(1, 2, 3);
        m.setELMT(2, 0, 2);
        m.setELMT(2, 1, 5);
        m.setELMT(2, 2, 3);

        test = Determinan.cofactorDet(m);
        print("determinan: " + test);
        
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
