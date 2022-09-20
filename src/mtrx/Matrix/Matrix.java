package mtrx.Matrix;

public class Matrix{

    public static final int ROW_CAP = 100;
    public static final int COL_CAP = 100;

    public static class MatrixType {
        private double[][] mem;
        private int ROW_EFF;
        private int COL_EFF;

        public MatrixType (int row, int col) {
            this.ROW_EFF = row;
            this.COL_EFF = col;
            this.mem = new double [row][col];
        } 

        // SELEKTOR 
        public double GetELMT(int row, int col) {
            return this.mem[row][col];
        }

        public void SetELMT(int row, int col, float value) {
            this.mem[row][col] = value;
        }

        public int GetRowEff() {
            return ROW_EFF;
        }

        public int GetColEff() {
            return COL_EFF;
        }
    }
}