package mtrx.Methods;

import mtrx.Utility.*;
import mtrx.Matrix.*;
import java.math.*;
public class Determinan {
    /* ---------------------- Cofactor Expansion ---------------------- */
    public static double cofactorDet(Matrix m) {
        /* PRE-KONDISI */
        // matrix (N x N)

        /* KAMUS LOKAL */
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
            for (j = 0; j < m.getRow(); j++) {
                res += Math.pow(-1, j) * m.getELMT(0, j) * cofactorDet(smallerMatrix(m, 0, j));
            }
        }
        return res;
    }
    
    public static Matrix smallerMatrix (Matrix m, int row, int col) {
        /* PRE-KONDISI */
        // matrix (N x N)

        /* KAMUS LOKAL */
        int i, j, im, jm;
        Matrix mRes;

        /* ALGORITMA */
        mRes = new Matrix(m.getRow() - 1, m.getCol() - 1);
        
        im = 0;
        for (i = 0; i < mRes.getRow(); i ++) {
            if (im == row) {
                im++;
            }

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

    /* Console */
    public static void displayCofactorDet(Matrix m) {
        /* KAMUS LOKAL */
        double det;

        /* ALGORITMA - Output Purpose*/
        Utils.println("Matrix awal:");
        Matrix.displayMatrix(m);
        Utils.println("");

        det = cofactorDet(m);
        Utils.println("Dengan ekspansi baris pertama, Determinan matrix tersebut adalah " + result(det));
    }

    /* File */
    public static void fileCofactorDet(Matrix m) {
        /* ALGORITMA - Output Purpose */

    }

    /* ------------------------- Row Reduction ------------------------ */
    /**
     * MatrixNDet
     */
    public static class MatrixNDet {
        Matrix matrix;
        double det;
        MatrixNDet(Matrix m, double d) {
            matrix = m;
            det = d;
        }
    }

    public static MatrixNDet rowReductionDet(Matrix m) {
        /* KAMUS LOKAL */
        Matrix mRes;
        int i;
        double det;
        
        /* ALGORITMA */
        mRes = new Matrix(m);
        GaussJordan.determinantOBE(mRes);
        
        det = 1;
        for (i = 0; i < mRes.getRow(); i ++) {
            det *= mRes.getELMT(i, i);
        }

        return new MatrixNDet(mRes, det); 
    }

    /* Console */
    public static void displayRowReductionDet(Matrix m) {
        /* KAMUS LOKAL */
        double det;

        /* ALGORITMA - Output Purpose */
        Utils.println("Matrix awal:");
        Matrix.displayMatrix(m);
        Utils.println("");

        Utils.println("Matrix setelah reduksi baris:");
        MatrixNDet res = rowReductionDet(m);
        Matrix.displayMatrix(res.matrix);

        det = res.det;
        Utils.println("Determinan matrix tersebut adalah " + result(det));
    }

    /* File */
    public static void fileRowReductionDet(Matrix m) {
        /* ALGORITMA - Output Purpose */

    }

    /* Formatting Output */
    public static String result(double det) {
        /* KAMUS LOKAL */
        String s;

        /* ALGORITMA */
        if (det == (int) det) {
            s = String.valueOf((int) det);
        } else {
            det = new BigDecimal(det).setScale(8, RoundingMode.HALF_UP).doubleValue();
            if (det == (int) det) { // 2x times to make sure
                s = String.valueOf((int) det);
            } else {
                s = String.valueOf(det);
            }
        }
        return s;
    }
}