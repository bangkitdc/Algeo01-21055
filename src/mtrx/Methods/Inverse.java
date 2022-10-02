package mtrx.Methods;

import mtrx.Matrix.Matrix;
import mtrx.Utility.Utils;

public class Inverse {

    public static Matrix getInverse (Matrix m) {


        // KAMUS LOKAL
        Matrix mRes, mIdentity;

        // ALGORITMA

        // Jika bukan matriks persegi
        if (!m.isSquare())
        {
            return null;
        }

        mRes = new Matrix(m.getRow(), m.getCol() * 2);
        mIdentity = new Matrix(m.getRow(), m.getCol());

        mIdentity.toIdentity();

        mRes.copyToSubMatrix(m, 0, m.getLastRow(), 0, m.getLastCol());
        mRes.copyToSubMatrix(mIdentity, 0, m.getLastRow(), m.getLastCol() + 1, mRes.getLastCol());

        SolveSPL.gaussJordan(mRes);

        if (mRes.getELMT(m.getLastRow(), m.getLastCol()) == 0) {
            return null;
        }

        return mRes.getSubMatrix(0, mRes.getLastRow(), m.getLastCol() + 1, mRes.getLastCol());
    }

    public static Matrix getInverse (Matrix m, int method) {

        // method == 0 menandakan getInverse akan menggunakan methode OBE, sedangkan nilai method lainnya menandakan
        // getInverse menggunakan metode matriks adjoin

        // KAMUS LOKAL
        Matrix adj;
        Matrix cofactor;
        int i, j;
        double det;

        if(method == 1) {
            return getInverse(m);
        }

        else if (method == 2) {

            det = Determinan.rowReductionDet(m).det;

            if(!m.isSquare() || det == 0)
            {
                return null;
            }

            cofactor = new Matrix(m.getRow(), m.getCol()); 
            for (i = 0; i < m.getRow(); i++)
            {
                for (j = 0; j < m.getCol(); j++)
                {
                    cofactor.setELMT(i, j, 
                    Determinan.rowReductionDet(Determinan.smallerMatrix(m, i, j)).det * ((i + j) % 2 == 1 ? -1 : 1));
                }
            }

            adj = cofactor.transpose();

            return adj.multiply(1.0 / det);
            
        }

        return null;
    }

    public static void outputInverse(Matrix m, int method) {
        // Matrix m adalah matriks yang ingin dicari inversenya

        if (method == 1)
        {

        }

        else if (method == 2)
        {
            Utils.println("Hasil inverse : ");
            Matrix.displayMatrix(getInverse(m));
        }
    }
}
