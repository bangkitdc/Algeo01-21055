package mtrx.Methods;

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
                    Utils.println(String.format("x%d = %s", i + 1, Utils.result(detTemp / detA)));
                }
            }
        }
    }

    /* File */
    public static void fileCrammer(Matrix m) {
        /* ALGORITMA - Output Purpose */
    }
}
