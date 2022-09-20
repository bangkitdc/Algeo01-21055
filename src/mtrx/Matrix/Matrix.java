package mtrx.Matrix;

public class Matrix{
    public static final int ROW_CAP = 100;
    public static final int COL_CAP = 100;
    private double[][] mem;
    private int ROW_EFF;
    private int COL_EFF;

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
}