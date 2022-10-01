package mtrx.Methods;

import mtrx.Matrix.*;
import mtrx.Utility.Utils;

public class Cramer {
    /* ------------------------- Cramer's Rule ------------------------ */
    /* Console */
        public static String displayCramer(Matrix m) {
        /* Algorithm + Display */
        /* KAMUS LOKAL */
        Matrix a, b, temp;
        double detA, detTemp;
        int i;
        String solution = "";

        /* ALGORITMA */
        a = m.getSubMatrix(0, m.getLastRow(), 0, m.getLastCol() - 1);
        b = m.getSubMatrix(0, m.getLastRow(), m.getLastCol(), m.getLastCol());

        if (a.getCol() != a.getRow()) {
        	solution = "\nMatrix ini tidak memiliki solusi atau tidak bisa diselesaikan dengan Cramer. Silahkan coba pakai cara lain. Rekomendasi: Gauss/Gauss-Jordan\n";
        } else {
            detA = Determinan.cofactorDet(a);
            if (detA == 0) {
            	solution = "\nMatrix ini tidak memiliki solusi karena determinan matrix adalah 0\n";
            } else {
            	solution += "\nSolusi:\n";
                for (i = 0; i < a.getCol(); i ++) {
                    temp = new Matrix(a.getCol(), a.getRow());
                    temp.copyELMT(a);
                    temp.copyToSubMatrix(b, 0, a.getLastRow(), i, i);
                    detTemp = Determinan.cofactorDet(temp);
                    solution += String.format("x%d = %s\n", i + 1, Utils.result(detTemp / detA));
                }
            }
        }
        return solution;
    }

    /* File */
    public static void fileCrammer(Matrix m) {
        /* ALGORITMA - Output Purpose */
    }
}
