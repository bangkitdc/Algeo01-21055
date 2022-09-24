package mtrx.Methods;
import mtrx.Matrix.Matrix;
import mtrx.Methods.SolveSPL;

public class Inverse {

    public static Matrix getInverse (Matrix m) {
        // PREKONDISI : m matrix persegi 

        // KAMUS LOKAL
        Matrix mRes, mIdentity;

        // ALGORITMA

        mRes = new Matrix(m.getRow(), m.getCol() * 2);
        mIdentity = new Matrix(m.getRow(), m.getCol());

        mIdentity.toIdentity();

        mRes.copyToSubMatrix(m, 0, m.getLastRow(), 0, m.getLastCol());
        mRes.copyToSubMatrix(mIdentity, 0, m.getLastRow(), m.getLastCol() + 1, mRes.getLastCol());

        SolveSPL.gauss(mRes);

        if (mRes.getELMT(m.getLastRow(), m.getLastCol()) == 0) {
            return null;
        }

        return mRes.getSubMatrix(0, mRes.getLastRow(), m.getLastCol() + 1, mRes.getLastCol());
    }
}
