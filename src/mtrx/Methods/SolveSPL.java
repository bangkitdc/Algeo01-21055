package mtrx.Methods;
import mtrx.Matrix.Matrix;
import mtrx.Methods.Inverse;
import mtrx.Methods.Solution;

public class SolveSPL {

    public static void gauss (Matrix m) {
        // change m to gauss form
    }

    public static void gaussJordan (Matrix m) {
        // change m to gaussjordan form
    }

    public static Matrix getGauss (Matrix m) {
        // return the gauss form of m
        return null;
    }

    public static Matrix getGaussJordan (Matrix m) {
        // reutrn the gaussjordan form of m
        return null;
    }

    public static Matrix inverseSolution (Matrix m) {
        // KAMUS LOKAL
        Matrix b, A, inverse;

        // ALGORITMA

        if (!m.isSquare())
        {
            return null;
        }

        b = m.getSubMatrix(0, m.getLastRow(), m.getLastCol(), m.getLastCol());
        A = m.getSubMatrix(0, m.getLastRow(), 0, m.getLastCol() - 1);
        inverse = Inverse.getInverse(A);

        if (inverse == null)
        {
            return null;
        }

        return inverse.multiply(b);
    }
}
