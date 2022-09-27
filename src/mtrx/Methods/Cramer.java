package mtrx.Methods;

import java.math.*;
import mtrx.Matrix.*;
import mtrx.Utility.Utils;

public class Cramer {
    /* ------------------------- Cramer's Rule ------------------------ */
    /* Console */
    public static void displayCrammer(Matrix m) {
        /* Algorithm + Display */
        /* KAMUS LOKAL */
        Matrix a, b, temp;
        double detA, detTemp;
        int i;

        /* ALGORITMA */
        a = m.getSubMatrix(0, m.getLastRow(), 0, m.getLastCol() - 1);
        b = m.getSubMatrix(0, m.getLastRow(), m.getLastCol(), m.getLastCol());

        if (a.getCol() != a.getRow()) {
            Utils.print("Matrix ini tidak memiliki solusi atau tidak bisa diselesaikan dengan Cramer. Silahkan coba pakai cara lain. Rekomendasi: Gauss/Gauss-Jordan");
        } else {
            detA = Determinan.cofactorDet(a);
            if (detA == 0) {
                Utils.print("Matrix ini tidak memiliki solusi karena determinan matrix adalah 0");
            } else {
                Utils.println("Matrix awal:");
                Matrix.displayMatrixAugmented(m, m.getLastCol() - 1);
                Utils.println("");
                for (i = 0; i < a.getCol(); i ++) {
                    temp = new Matrix(a.getCol(), a.getRow());
                    temp.copyELMT(a);
                    temp.copyToSubMatrix(b, 0, a.getLastRow(), i, i);
                    detTemp = Determinan.cofactorDet(temp);
                    Utils.println(String.format("x%d = %s", i + 1, result(detTemp, detA)));
                }
            }
        }
    }

    /* Formatting Output */
    public static String result (double det1, double det2) {
        /* KAMUS LOKAL */
        double res = det1 / det2;
        String s;

        /* ALGORITMA */
        if (res == (int) res) {
            s = String.valueOf((int) res);
        } else {
            res = new BigDecimal(res).setScale(8, RoundingMode.HALF_UP).doubleValue();
            s = String.valueOf(res);
        }
        return s;
    }

    /* File */
    public static void fileCrammer(Matrix m) {
        /* ALGORITMA - Output Purpose */
    }
}
