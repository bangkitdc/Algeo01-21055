package mtrx.Utility;

import java.io.*;
import mtrx.Matrix.*;
import mtrx.Methods.*;

public class Menu extends Utils{
    
    public static void menuLoop() throws IOException {
        mainMenu();
        // Tester
        Matrix n = new Matrix();
       
        n.inputSquaredMatrix();
        
        Matrix m;
        m = BicubicInterpolation.Bicubic();
        m = Inverse.getInverse(m);
        Matrix y = new Matrix(16, 1);
        int a = 0;
        for (int i = 0; i < n.getRow(); i ++) {
            for (int j = 0; j < n.getCol(); j ++) {
                y.setELMT(a, 0, n.getELMT(j, i));
                a ++;
            }
        }

        Matrix res;
        res = m.multiply(y);

        Matrix.displayMatrix(res);

        // Matrix m = new Matrix();
       
        // m.inputSquaredMatrix();

        // Determinan.displayCofactorDet(m);

        // Determinan.displayRowReductionDet(m);
        
        
        // Matrix m;
        // m = BicubicInterpolation.Bicubic();
        // Matrix.displayMatrix(m);
        
        // Matrix m2;
        // m2 = Inverse.getInverse(m);
        // Matrix.displayMatrix(m2);

        Polinom.driver();
        
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
