package mtrx.Utility;

import java.io.*;

import mtrx.Matrix.*;
import mtrx.Methods.*;

public class Menu extends Utils{
    
    public static void menuLoop() throws IOException {
        Polinom.interpolationDriver();
        mainMenu();
        
        
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
