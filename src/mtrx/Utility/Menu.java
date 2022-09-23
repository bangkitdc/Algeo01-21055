package mtrx.Utility;

import java.io.*;
import mtrx.Matrix.*;
import mtrx.Methods.*;

public class Menu extends Utils{
    
    public static void menuLoop() throws IOException {
        mainMenu();
        // Tester
        double test;

        Frac.Type a, b, c,d, e, f;

        a = new Frac.Type(-3, -17);
        b = new Frac.Type(35, 49);

        c = Frac.multp(a, b);
        d = Frac.multp(a, -5);
        e = Frac.add(a, b);
        f = Frac.add(a, -3);
        print("a * b : ");
        print("top frac: " + c.getTop());
        print("bot frac: " + c.getBot());
        print("a * -5 : ");
        print("top frac: " + d.getTop());
        print("bot frac: " + d.getBot());
        print("a + b : ");
        print("top frac: " + e.getTop());
        print("bot frac: " + e.getBot());
        print("a -3 : ");
        print("top frac: " + f.getTop());
        print("bot frac: " + f.getBot());

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
