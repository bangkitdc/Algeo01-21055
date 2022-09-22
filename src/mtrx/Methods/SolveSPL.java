package mtrx.Methods;
import mtrx.Matrix.Matrix;
import mtrx.Methods.Inverse;
import mtrx.Methods.Solution;

public class SolveSPL {

    public static void Gauss (Matrix m) {
        // change m to gauss form
    }

    public static void GaussJordan (Matrix m) {
        // change m to gaussjordan form
    }

    public static Matrix GetGauss (Matrix m) {
        // return the gauss form of m
        return null;
    }

    public static Matrix GetGaussJordan (Matrix m) {
        // reutrn the gaussjordan form of m
        return null;
    }

    public static Matrix InverseSolution (Matrix m) {
        // KAMUS LOKAL
        Matrix b, A, inverse;

        // ALGORITMA

        if (!m.isSquare())
        {
            return null;
        }

        b = m.GetSubMatrix(0, m.getLastRow(), m.getLastCol(), m.getLastCol());
        A = m.GetSubMatrix(0, m.getLastRow(), 0, m.getLastCol() - 1);
        inverse = Inverse.GetInverse(A);

        if (inverse == null)
        {
            return null;
        }

        return inverse.Multiply(b);
    }
}
