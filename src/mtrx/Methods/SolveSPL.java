package mtrx.Methods;

import mtrx.Utility.*;
import mtrx.Matrix.Matrix;
import mtrx.Methods.*;

public class SolveSPL {

    public static void gauss (Matrix m) {
        // change m to gauss form

        // I.S. Matrix
		// F.S. Matrix eselon yg sudah dieliminasi Gauss
		m = GaussJordan.sortMatrix(m);
		for (int i=0; i < m.getRow()-1; i++) {
			m = GaussJordan.makeOne(m);
			m = GaussJordan.OBE(m,i);
		}
		m = GaussJordan.makeOne(m);
    }

    public static void gaussJordan (Matrix m) {
        // change m to gaussjordan form

        // I.S. Matrix
		// F.S. Matrix eselon yg sudah dieliminasi Gauss Jordan
		m = GaussJordan.gauss(m);
		for (int idx=0; idx<m.getRow(); idx++) {
			for (int i=0; i<idx; i++) {
				int j = GaussJordan.firstNotZero(m, idx);
				if (j < m.getCol()) {
					for (int k=m.getCol()-1; k>=j; k--) {
						m.setELMT(i, k, m.getELMT(i,k) - (m.getELMT(idx,k) * m.getELMT(i,j)));
					}
				}
			}
		}
    }

    public static Matrix getGauss (Matrix m) {
        // return the gauss form of m
        
        Matrix res = new Matrix(m);

        gauss(res);
        return res;
    }

    public static Matrix getGaussJordan (Matrix m) {
        // reutrn the gaussjordan form of m
        Matrix res = new Matrix(m);

        gaussJordan(res);
        return res;
    }

    public static Matrix inverseSolution (Matrix m) {
        // KAMUS LOKAL
        Matrix b, A;

        // ALGORITMA

        b = m.getSubMatrix(0, m.getLastRow(), m.getLastCol(), m.getLastCol());
        A = m.getSubMatrix(0, m.getLastRow(), 0, m.getLastCol() - 1);

        return inverseSolution(A, b);
    }

    public static Matrix inverseSolution (Matrix A, Matrix b) {
        // KAMUS LOKAL
        Matrix inverse;

        // ALGORITMA

        if (!A.isSquare())
        {
            return null;
        }

        inverse = Inverse.getInverse(A);

        if (inverse == null)
        {
            return null;
        }
        
        return inverse.multiply(b);
    }
}
