package mtrx.Methods;

import mtrx.Matrix.Matrix;

public class Determinan {
    public static Matrix smallerMatrix (Matrix m, int row, int col) {
        /* KAMUS */
        int i, j, im, jm;
        Matrix mRes;

        /* ALGORITMA */
        mRes = new Matrix(m.getRow() - 1, m.getCol() - 1);
        
        im = 1;
        for (i = 0; i < mRes.getRow(); i ++) {
            jm = 0;
            for (j = 0; j < mRes.getCol(); j ++) {
                if (jm == col) {
                    jm ++;
                }
                mRes.setELMT(i, j, m.getELMT(im, jm));
                jm ++;
            }
            im ++;
        }
        return mRes;
    }

    public static double cofactorDet (Matrix m) { // matrix mxm
        /* KAMUS */
        int j;
        double res;

        /* ALGORITMA */
        // Recursive Method
        if (m.getRow() == 1) {
            res = m.getELMT(0, 0);
        } else if (m.getCol() == 2) {
            res = m.getELMT(0, 0) * m.getELMT(1, 1) - m.getELMT(0, 1) * m.getELMT(1, 0);
        } else {
            res = 0;
            for (j = 0; j < m.getRow(); j ++) {
                res += Math.pow(-1, j) * m.getELMT(0, j) * cofactorDet(smallerMatrix(m, 0, j));
            }
        }
        return res;
    }
}