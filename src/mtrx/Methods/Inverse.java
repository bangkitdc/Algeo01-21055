package mtrx.Methods;
import mtrx.Matrix.Matrix;

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

        if(method == 0) {
            return getInverse(m);
        }

        else {

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
            det = Determinan.rowReductionDet(m).det;

            return adj.multiply(det);
            
        }
    }
}
