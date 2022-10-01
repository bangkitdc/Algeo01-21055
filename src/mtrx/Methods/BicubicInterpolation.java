package mtrx.Methods;

import java.io.*;
import java.math.*;

import mtrx.Matrix.*;
import mtrx.Utility.Utils;

public class BicubicInterpolation {
    public static int function(int x, int y, int i, int j) {
        /* ALGORITMA mendapatkan koefisien dari m16 */
        double p = Math.pow(x, i) * Math.pow(y, j);
        return (int) p;
    }

    public static Matrix Bicubic(Matrix m4) {
        /* Menghasilkan matrix 4x4 dari input matrix 4x4
        | a00 a01 a02 a03 |
        | a10 a11 a12 a13 |
        | a20 a21 a22 a23 |
        | a30 a31 a32 a33 |
        */

        /* y = X . a */
        /* 
        | f(-1, -1) |   |           |   | a00 |
        | f(-1, 0)  |   |   16x16   |   | a01 |
        |     .     | = |           | . |  .  |
        |     .     |   |           |   |  .  |
        | f(2, 2)   |   |           |   | a33 | 
        */

        int n = 16;
        Matrix X = new Matrix(n, n);

        int xi = -1;
        int yi = -1;
        for (int i = 0; i < n; i ++) {
            int im = 0;
            int jm = 0;
            for (int j = 0; j < n; j ++) {
                int d = function(xi, yi, im % 4, jm % 4);
                X.setELMT(i, j, d);
                jm ++;
                if (jm % 4 == 0) {
                    im ++;
                }
            }
            if (xi < 2) {
                xi ++;
            } else {
                xi = -1;
                yi ++;
            }
        }
        // X^-1 . y = a
        X = Inverse.getInverse(X);

        // Matrix y adalah matrix m4 (4x4)
        Matrix y = new Matrix(16, 1);
        int a = 0;
        for (int i = 0; i < m4.getRow(); i++) {
            for (int j = 0; j < m4.getCol(); j++) {
                y.setELMT(a, 0, m4.getELMT(i, j));
                a++;
            }
        }

        // temp -> X^-1 . y
        Matrix temp;
        temp = X.multiply(y);

        int p = 0;
        Matrix mRes = new Matrix(4, 4);
        for (int i = 0; i < mRes.getRow(); i++) {
            for (int j = 0; j < mRes.getCol(); j++) {
                mRes.setELMT(i, j, temp.getELMT(p, 0));
                p++;
            }
        }
        return mRes;
    }

    public static double getBicubicInterpolation(Matrix n, double x, double y) {
        double res = 0;

        for (int i = 0; i < n.getRow(); i++) {
            for (int j = 0; j < n.getCol(); j++) {
                res += n.getELMT(i, j) * Math.pow(x, i) * Math.pow(y, j);
            }
        }
        return res;
    }

    public static void displayBicubic(Matrix m) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader readInput = new BufferedReader(streamReader);

        Matrix n;
        n = Bicubic(m);
        
        // driver
        Utils.println("Akan dihitung f(x, y) sebanyak N, masukkan N: ");
        int N = Utils.inputInt();
        Utils.println("");
        for (int k = 0; k < N; k ++) {
            Utils.println("Masukkan x dan y (dipisah spasi): ");
            Matrix xy = new Matrix(1, 2);
            
            String[] element;
            String line = new String();

            try {
                Utils.print("> ");
                line = readInput.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            element = line.split(" ");
            for (int p = 0; p < 2; p++) {
                double d = Utils.eval(element[p]);
                xy.setELMT(0, p, d);       
            }
            String res = result(getBicubicInterpolation(n, xy.getELMT(0, 0), xy.getELMT(0, 1)));
            Utils.println(String.format("f(%s, %s) = %s", result(xy.getELMT(0, 0)), result(xy.getELMT(0, 1)), res));
            Utils.println("");
        }
    }
    
    public static String result(double d) {
        /* KAMUS LOKAL */
        String s;

        /* ALGORITMA */
        if (d == (int) d) {
            s = String.valueOf((int) d);
        } else {
            d = new BigDecimal(d).setScale(13, RoundingMode.HALF_UP).doubleValue();
            s = String.valueOf(d);
        }
        return s;
    }
}

// 153 59 210 96
// 125 161 72 81
// 98 101 42 12
// 21 51 0 16