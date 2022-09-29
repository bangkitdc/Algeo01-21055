package mtrx.Utility;

import java.io.*;

import javafx.scene.image.Image;
import mtrx.Matrix.*;
import mtrx.Methods.*;

public class Menu extends Utils{
    
    public static void menuLoop() throws IOException {
        mainMenu();
        // Tester
        Matrix n = new Matrix();
       
        n.inputMatrix();

        // Determinan.displayCofactorDet(n);
        // Determinan.displayRowReductionDet(n);
        
        // Matrix.displayMatrix(n);

        // BicubicInterpolation.displayBicubic(n);

        // Matrix m = new Matrix();
       

        //Matrix.displayMatrix(Inverse.getInverse(m));

        // Determinan.displayCofactorDet(m);

        // Determinan.displayRowReductionDet(m);
        
        
        // Matrix m;
        // m = BicubicInterpolation.Bicubic();
        // Matrix.displayMatrix(m);
        
        // Matrix m2;
        // m2 = Inverse.getInverse(m);
        // Matrix.displayMatrix(m2);

        Matrix.displayMatrix(ImageScaler.DoubleScale(n));
        
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
