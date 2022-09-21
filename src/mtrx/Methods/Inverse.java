package mtrx.Methods;
import mtrx.Matrix.Matrix;
import mtrx.Methods.SolveSPL;

public class Inverse {

    public static Matrix GetInverse (Matrix m) {
        // PREKONDISI : m matrix persegi 

        // KAMUS LOKAL
        Matrix mRes, mIdentity;

        // ALGORITMA

        mRes = new Matrix(m.getRow(), m.getCol() * 2);
        mIdentity = new Matrix(m.getRow(), m.getCol());

        mIdentity.ToIdentity();

        mRes.CopyToSubMatrix(m, 0, m.getLastRow(), 0, m.getLastCol());
        mRes.CopyToSubMatrix(mIdentity, 0, m.getLastRow(), m.getLastCol() + 1, mRes.getLastCol());

        SolveSPL.Gauss(mRes);

        if (mRes.getELMT(m.getLastRow(), m.getLastCol()) == 0) {
            return null;
        }

        return mRes.GetSubMatrix(0, mRes.getLastRow(), m.getLastCol() + 1, mRes.getLastCol());
    }
}
