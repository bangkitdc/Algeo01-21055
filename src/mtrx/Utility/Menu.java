package mtrx.Utility;

import java.io.*;
import mtrx.Matrix.*;
import mtrx.Methods.*;

public class Menu extends Utils{
    
    public static void menuLoop() throws IOException {
        mainMenu();
        // Tester
        Matrix m = new Matrix(3, 4);
        m.setELMT(0, 0, -1);
        m.setELMT(0, 1, 2);
        m.setELMT(0, 2, -3);
        m.setELMT(0, 3, 1);
        m.setELMT(1, 0, 2);
        m.setELMT(1, 1, 0);
        m.setELMT(1, 2, 1);
        m.setELMT(1, 3, 0);
        m.setELMT(2, 0, 3);
        m.setELMT(2, 1, -4);
        m.setELMT(2, 2, 4);
        m.setELMT(2, 3, 2);

        Cramer.displayCrammer(m);
        
        int choice = select(1, 7);
        System.out.println(choice);
    }

    public static void mainMenu() {
        println("Selamat Datang di Matrix Calculator");
        println("=========== Menu ===========");
        println("Aplikasi ini akan menyelesaikan persoalan:");
        println("[1] Sistem Persamaan Linear (SPL)");
        println("[2] Determinan");
        println("[3] Matriks Balikan (Inverse)");
        println("[4] Interpolasi Polinom");
        println("[5] Interpolasi Bicubic");
        println("[6] Regresi Linear Berganda");
        println("");
        println("[7] Keluar");
    }
}
