package mtrx.Matrix;
import mtrx.Utility.Utils;
import java.io.*;
import java.math.*;

public class Matrix{
    public static final int ROW_CAP = 100;
    public static final int COL_CAP = 100;
    private double[][] mem;
    private int ROW_EFF;
    private int COL_EFF;

    /* -------------------------- KONSTRUKTOR ------------------------- */
    public Matrix (int row, int col) {
        this.ROW_EFF = row;
        this.COL_EFF = col;
        InitializeMatrix();
    }

    public Matrix (Matrix mIn) {
        this.ROW_EFF = mIn.getRow();
        this.COL_EFF = mIn.getCol();
        InitializeMatrix();
        this.copyELMT(mIn);
    }

    public Matrix () {
        this.ROW_EFF = 0;
        this.COL_EFF = 0;
        InitializeMatrix();
    }

    private void InitializeMatrix() {
        this.mem = new double [this.getRow()][this.getCol()];
    }
    
     public void setMatrix (int row, int col) {
        this.ROW_EFF = row;
        this.COL_EFF = col;
        this.mem = new double[row][col];
    }

    /* ---------------------------- SELEKTOR -------------------------- */
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

    /* -------------------------- BACA/ TULIS ------------------------- */
    /* ------------------------- Console Input ------------------------ */
    public void inputMatrix() throws IOException {
        /* KAMUS LOKAL */
        int n, m;

        /* ALGORITMA */
        Utils.println("");
        Utils.println("Akan dibuat matrix berukuran mxn");
        Utils.println("Masukkan nilai m: ");
        m = Utils.inputInt();

        Utils.println("Masukkan nilai n: ");
        n = Utils.inputInt();
        Utils.println("");

        this.setMatrix(m, n);
        Utils.println("Silahkan masukkan setiap elemen matrix: ");
        this.createMatrix();
    }

    public void inputSquaredMatrix() throws IOException {
        /* KAMUS LOKAL */
        int n;

        /* ALGORITMA */
        Utils.println("");
        Utils.println("Akan dibuat matrix berukuran nxn. Masukkan nilai n: ");
        n = Utils.inputInt();
        Utils.println("");

        this.setMatrix(n, n);
        Utils.println("Silahkan masukkan setiap elemen matrix: ");
        this.createMatrix();
    }

    public void input4x4Matrix() throws IOException {
        /* KAMUS LOKAL */
        int n;

        /* ALGORITMA */
        Utils.println("");
        Utils.println("Akan dibuat matrix berukuran 4x4.");

        this.setMatrix(4, 4);
        Utils.println("Silahkan masukkan setiap elemen matrix: ");
        this.createMatrix();
    }
    /* --------------------------- File Input ------------------------- */
    public void inputFileMatrix() throws IOException {
        /* Include Squared Matrix */
        Utils.println("");
        Utils.println("============ Pilih File ===========");

        IO.outputListDir();

        File[] listFiles = IO.getListDir();
        Utils.println("Masukkan pilihan: ");
        int input = Utils.select(1, listFiles.length);

        Matrix mRes = new Matrix();
        mRes = IO.readMatrix(listFiles[input - 1].getName());
        this.setMatrix(mRes.getRow(), mRes.getCol());
        this.copyELMT(mRes);
    }

    /* ------------------------ Console Output ------------------------ */
    /* Display Matrix 2D */
    public static void displayMatrix(Matrix m) {
        /* KAMUS LOKAL */
        int i, j;
        int weight, wLeft;
        String s;
        double d;

        /* ALGORITMA */
        weight = 0;
        // Search the biggest weight first
        for (i = 0; i < m.getRow(); i++) {
            for (j = 0; j < m.getCol(); j++) {
                d = new BigDecimal(m.getELMT(i, j)).setScale(3, RoundingMode.HALF_UP).doubleValue();
                if (Utils.getLengthELMT(d) > weight) {
                    weight = Utils.getLengthELMT(d);
                }
            }
        }
        weight ++;

        // Search the biggest weight (first column only)
        wLeft = 0;
        for (i = 0; i < m.getRow(); i++) {
            d = new BigDecimal(m.getELMT(i, 0)).setScale(3, RoundingMode.HALF_UP).doubleValue();
            if (Utils.getLengthELMT(d) > wLeft) {
                wLeft = Utils.getLengthELMT(d);
            }
        }

        for (i = 0; i < m.getRow(); i++) {
            for (j = 0; j < m.getCol(); j++) {
                s = String.format("%d", weight);
                if (m.getELMT(i, j) == (int) m.getELMT(i, j)) { // int
                    if (j == 0) {
                        s = String.format("%d", wLeft);
                    }
                    Utils.printf("%" + s + "d", (int) m.getELMT(i, j));
                } else { // double
                    if (j == 0) {
                        s = String.format("%d", wLeft);
                    }
                    d = new BigDecimal(m.getELMT(i, j)).setScale(3, RoundingMode.HALF_UP).doubleValue();
                    if (d == (int) d) { // 2 times to make sure
                        Utils.printf("%" + s + "s", String.valueOf((int) d));
                    } else {
                        Utils.printf("%" + s + "s", String.valueOf(d));
                    }
                }
            }
            Utils.println("");
        }
    }

    /* Display Matrix Augmented */
    public static void displayMatrixAugmented(Matrix m, int col) {
        /* KAMUS LOKAL */
        int i, j;
        int weight, wLeft;
        String s;
        double d;

        /* ALGORITMA */
        weight = 0;
        // Search the biggest weight first
        for (i = 0; i < m.getRow(); i++) {
            for (j = 0; j < m.getCol(); j++) {
                d = new BigDecimal(m.getELMT(i, j)).setScale(3, RoundingMode.HALF_UP).doubleValue();
                if (Utils.getLengthELMT(d) > weight) {
                    weight = Utils.getLengthELMT(d);
                }
            }
        }
        weight++;

        // Search the biggest weight (first column only)
        wLeft = 0;
        for (i = 0; i < m.getRow(); i++) {
            if (Utils.getLengthELMT(m.getELMT(i, 0)) > wLeft) {
                d = new BigDecimal(m.getELMT(i, 0)).setScale(3, RoundingMode.HALF_UP).doubleValue();
                wLeft = Utils.getLengthELMT(d);
            }
        }

        wLeft++;

        for (i = 0; i < m.getRow(); i++) {
            for (j = 0; j < m.getCol(); j++) {
                s = String.format("%d", weight);
                if (m.getELMT(i, j) == (int) m.getELMT(i, j)) { // int
                    if (j == 0) {
                        s = String.format("%d", wLeft);
                    }
                    Utils.printf("%" + s + "d", (int) m.getELMT(i, j));
                } else { // double
                    if (j == 0) {
                        s = String.format("%d", wLeft);
                    }
                    d = new BigDecimal(m.getELMT(i, j)).setScale(3, RoundingMode.HALF_UP).doubleValue();
                    if (d == (int) d) { // 2 times to make sure
                        Utils.printf("%" + s + "s", String.valueOf((int) d));
                    } else {
                        Utils.printf("%" + s + "s", String.valueOf(d));
                    }
                }
                // Augmented
                if (j == col) {
                    String sep = "|";
                    Utils.printf("%" + s + "s", sep);
                }
            }
            Utils.println("");
        }
    }
    
    public void copyELMT(Matrix mIn) {
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

    public void copyELMT(int[][] mIn)
    {
        // Prekondisi : size sama

        // KAMUS LOKAL
        int i, j;

        // ALGORITMA
        for (i = 0; i < this.getRow(); i++)
        {
            for (j = 0; j < this.getCol(); j++)
            {
                this.setELMT(i, j,mIn[i][j]);
            }
        }

    }

    public int[][] getIntMatrix(int min, int max)
    {
        
        // KAMUS LOKAL
        int i, j, elmt;
        int[][] mRes;

        // ALGORITMA

        mRes = new int[this.getRow()][this.getCol()];

        for (i = 0; i < this.getRow(); i++)
        {
            for (j = 0; j < this.getCol(); j++)
            {
                if ((int)this.getELMT(i, j) < min)
                {
                    elmt = min;
                }

                else if ((int)this.getELMT(i, j) > max)
                {
                    elmt = max;
                }

                else {
                    elmt = (int)this.getELMT(i, j);
                }
                
                mRes[i][j] = elmt;
            }
        }
        return mRes;

    }
    public void copyToSubMatrix(Matrix mIn, int InitRow, int LastRow, int InitCol, int LastCol) {
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

    public void toIdentity() {
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

    public static Matrix getIdentity(int size) {
        // KAMUS LOKAL
        Matrix mRes;

        // ALGORITMA

        mRes = new Matrix(size, size);
        mRes.toIdentity();

        return mRes;

    }

    public Matrix getSubMatrix(int InitRow, int LastRow, int InitCol, int LastCol) {
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

    public Matrix multiply (Matrix m) {
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

    public Matrix multiply (double k) {
        // KAMUS LOKAL
        Matrix mRes;
        int i, j;
        
        // ALGORITMA
        mRes = new Matrix(this.getRow(), this.getCol());

        for (i = 0; i < this.getRow(); i++)
        {
            for(j = 0; j < this.getCol(); j++)
            {
                mRes.setELMT(i, j, this.getELMT(i, j) * k);
            }
        }

        return mRes;

    }

    public void multiplyByConst(double k)
    {
        // KAMUS LOKAL
         Matrix mRes;

         // ALGORITMA
         mRes = this.multiply(k);

         this.copyELMT(mRes);
    }

    public void createMatrix() {
        /* KAMUS LOKAL */
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader readInput = new BufferedReader(streamReader);
        int i, j;

        /* ALGORITMA */
        for (i = 0; i < this.getRow(); i ++) {

            String[] element;
            String line = new String();

            try {
                line = readInput.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            element = line.split(" ");
            for (j = 0; j < this.getCol(); j ++) {
                double d = Utils.eval(element[j]);
                this.setELMT(i, j, d);
            }
        }
    }

    public void swapRow(int a, int b) {
		for (int i=0; i<this.getCol(); i++) {
			double temp = this.getELMT(b, i);
			this.setELMT(b, i, this.getELMT(a,i));
			this.setELMT(a, i, temp);
		}

	}

    public Matrix transpose() {
        Matrix mRes = new Matrix(this.getCol(), this.getRow());
        for (int i = 0; i < this.getRow(); i++) {
            for (int j = 0; j < this.getCol(); j++) {
                mRes.setELMT(j, i, this.getELMT(i, j));
            }
        }
        return mRes;
    }

}