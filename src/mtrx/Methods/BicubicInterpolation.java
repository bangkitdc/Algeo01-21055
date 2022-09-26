package mtrx.Methods;

import mtrx.Matrix.*;
import mtrx.Utility.Utils;

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
}
