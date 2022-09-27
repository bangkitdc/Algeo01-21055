package mtrx.Methods;

import java.io.*;
import java.math.*;

import mtrx.Matrix.*;
import mtrx.Utility.Utils;
import mtrx.Methods.*;

public class BicubicInterpolation {
    public static int function(int x, int y, int i, int j) {
        double p = Math.pow(x, i) * Math.pow(y, j);

        return (int) p;
    }

    public static Matrix Bicubic() {
        int n = 16;
        Matrix m1 = new Matrix(n, n);
        int i, j, x, y;

        x = -1;
        y = -1;
        for (i = 0; i < n; i ++) {
            int im = 0;
            int jm = 0;
            for (j = 0; j < n; j ++) {
                int d = function(x, y, im % 4, jm % 4);
                m1.setELMT(i, j, d);
                jm ++;
                if (jm % 4 == 0) {
                    im ++;
                }
            }
            if (x < 2) {
                x ++;
            } else {
                x = -1;
                y ++;
            }
        }
        return m1;
    }

    public static void displayBicubic(Matrix m) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader readInput = new BufferedReader(streamReader);

        Matrix n;
        n = Bicubic();
        n = Inverse.getInverse(n);
        Matrix s = new Matrix(16, 1);
        int a = 0;
        for (int i = 0; i < m.getRow(); i++) {
            for (int j = 0; j < m.getCol(); j++) {
                s.setELMT(a, 0, m.getELMT(i, j));
                a++;
            }
        }

        Matrix temp;
        temp = n.multiply(s);

        int p = 0;
        Matrix mRes = new Matrix(4, 4);
        for (int i = 0; i < mRes.getRow(); i ++) {
            for (int j = 0; j < mRes.getCol(); j ++) {
                mRes.setELMT(i, j, temp.getELMT(p, 0));
                p ++;
            }
        }

        // driver
        Utils.println("Masukkan N: ");
        int N = Utils.inputInt();
        for (int k = 0; k < N; k ++) {
            Utils.println("Masukkan (x, y): ");
            Matrix xy = new Matrix(1, 2);
            
            String[] element;
            String line = new String();

            try {
                line = readInput.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            element = line.split(" ");
            for (p = 0; p < 2; p++) {
                double d = Utils.eval(element[p]);
                xy.setELMT(0, p, d);       
            }
        
            double res = 0;

            for (int i = 0; i < mRes.getRow(); i ++) {
                for (int j = 0; j < mRes.getCol(); j ++){
                    res += mRes.getELMT(i, j) * Math.pow(xy.getELMT(0, 0), i) * Math.pow(xy.getELMT(0, 1), j);
                } 
            }
            Utils.println(result(res));
        }

    }
    
    public static String result(double d) {
        /* KAMUS LOKAL */
        String s;

        /* ALGORITMA */
        if (d == (int) d) {
            s = String.valueOf((int) d);
        } else {
            d = new BigDecimal(d).setScale(12, RoundingMode.HALF_UP).doubleValue();
            s = String.valueOf(d);
        }
        return s;
    }
}

// 153 59 210 96
// 125 161 72 81
// 98 101 42 12
// 21 51 0 16