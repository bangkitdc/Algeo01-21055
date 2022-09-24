package mtrx.Methods;

import mtrx.Matrix.Matrix;

public class Solution {
    public static enum SolutionType {
        UNIQUE,
        MANY,
        NO_SOLUTION
    } 

    private SolutionType type;
    // MATRIX TYPE BELUM PASTI KARENA SOLUSI BANYAK BUTUH ELEMEN STRING UTK JAWABAN PARAMETRIK
    private Matrix matrix;

    // KONSTRUKTOR 

    public Solution (int rowSize) {
        this.matrix = new Matrix(rowSize, 1);
    }

    // SELEKTOR 

    public SolutionType getType()
    {
        return this.type;
    }

    public void setType(SolutionType newType)
    {
        this.type = newType;
    }

    public double getSolution (int idx)
    {
        // prekondisi : this.type != NO_SOLUTION dan i index baris efektif this.matrix 
        return matrix.getELMT(idx, 0);
    }

    public void setSolution(Matrix m)
    {
        // prekondisi : m matriks kolom dengan ukuran sama dengan this.matrix
        this.matrix.copyELMT(m);
    }

    public void SetSolution(int idx, double value)
    {
        this.matrix.setELMT(idx, 0, value);
    }
}
