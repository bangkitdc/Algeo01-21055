package mtrx.Matrix;

public class Matrix{
    public static final int ROW_CAP = 100;
    public static final int COL_CAP = 100;
    private double[][] mem;
    private int ROW_EFF;
    private int COL_EFF;

    // KONSTRUKTOR
    public Matrix (int row, int col) {
        this.ROW_EFF = row;
        this.COL_EFF = col;
        this.mem = new double [row][col];
    } 

    // SELEKTOR 
    public double getELMT(int row, int col) {
        return this.mem[row][col];
    }

    public void setELMT(int row, int col, double d) {
        this.mem[row][col] = d;
    }

    public int getRow() {
        return ROW_EFF;
    }

    public int getCol() {
        return COL_EFF;
    }

    public int getLastRow() {
        return this.getRow() - 1;
    }

    public int getLastCol() {
        return this.getCol() - 1;
    }

    public boolean isSquare() {
        return (this.getRow() == this.getCol());
    }
    
    public void CopyELMT(Matrix mIn) {
        // prekondisi : size sama

        // KAMUS LOKAL
        int i, j;

        // ALGORITMA
        for (i = 0; i < mIn.getRow(); i++)
        {
            for (j = 0; j < mIn.getCol(); j++)
            {
                this.setELMT(i, j, mIn.getELMT(i, j));
            }
        }
    }
    public void CopyToSubMatrix(Matrix mIn, int InitRow, int LastRow, int InitCol, int LastCol) {
        // PREKONDISI : (InitRow - LastRow + 1) == mIn.getRow() && (InitCol - LastCol + 1) == mIn.getCol()
        // PREKONDISI : InitRow <= LastRow dan InitCol <= LastCol, semuanya index yang efektif

        // KAMUS LOKAL
        int i, j, currentRow, currentCol;

        // ALGORITMA

        currentRow = 0;

        for(i = InitRow; i <= LastRow; i++)
        {
            currentCol = 0;

            for(j = InitCol; j <= LastCol; j++)
            {
                this.setELMT(i, j, mIn.getELMT(currentRow, currentCol));

                currentCol++;
            }

            currentRow++;
        }
    }

    public void ToIdentity() {
        // prekondisi : this.isSquare()
        // KAMUS LOKAL
        int i, j;

        // ALGORITMA

        for(i = 0; i < this.getRow(); i++)
        {
            for(j = 0; j < this.getCol(); j++)
            {
                if (i == j)
                {
                    this.setELMT(i, j,1);
                }
                
                else 
                {
                    this.setELMT(i, j, 0);
                }
            }
        }
    }

    public static Matrix GetIdentity(int size) {
        // KAMUS LOKAL
        Matrix mRes;

        // ALGORITMA

        mRes = new Matrix(size, size);
        mRes.ToIdentity();

        return mRes;

    }

    public Matrix GetSubMatrix(int InitRow, int LastRow, int InitCol, int LastCol) {
        // PREKONDISI : InitRow <= LastRow dan InitCol <= LastCol, semuanya index yang efektif

        // KAMUS LOKAL
        int i, j, currentRow, currentCol;
        Matrix mRes;

        // ALGORITMA

        mRes = new Matrix(LastRow - InitRow + 1, LastCol - InitCol + 1);
        currentRow = InitRow;

        for(i = 0; i < mRes.getRow(); i++)
        {
            currentCol = InitCol;

            for(j = 0; j < mRes.getCol(); j++)
            {
                mRes.setELMT(i, j, this.getELMT(currentRow, currentCol));

                currentCol++;
            }

            currentRow++;
        }

        return mRes;
    }

    public Matrix Multiply (Matrix m) {
        // Prekondisi : jumlah kolom matrix ini sama dengan jumlah baris matrix m

        // KAMUS LOKAL
        int i, j, k;
        double sum;
        Matrix mRes;

        // ALGORITMA
        
        mRes = new Matrix(this.getRow(), m.getCol());

        for (i = 0; i < this.getRow(); i++)
        {
            for (j = 0; j < m.getCol(); j++)
            {
                sum = 0;
                for (k = 0; k < this.getCol(); k++)
                {
                    sum += this.getELMT(i, k) * m.getELMT(k, j);
                }

                mRes.setELMT(i, j, sum);
            }
        }

        return mRes;
    }
}