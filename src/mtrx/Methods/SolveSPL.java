package mtrx.Methods;

import mtrx.Matrix.Matrix;
import mtrx.Utility.Utils;
import java.io.*;
import java.lang.Math;

public class SolveSPL {

    public static void gauss (Matrix m) {
        // change m to gauss form

        // I.S. Matrix
	// F.S. Matrix eselon yg sudah dieliminasi Gauss
	// sort Matrix m
	GaussJordan.sortMatrix(m);
	// untuk perulangan semua baris,
	// buat semua baris berawalan 1 dan lakukan eliminasi OBE
	for (int i=0; i < m.getRow()-1; i++) {
		GaussJordan.makeOne(m);
		GaussJordan.OBE(m,i);
	}
        // buat semua baris berawalan 1 lalu sort Matrix
	GaussJordan.makeOne(m);
        GaussJordan.sortMatrix(m);
    }

    public static void gaussJordan (Matrix m) {
        // change m to gaussjordan form

        // I.S. Matrix
	// F.S. Matrix eselon yg sudah dieliminasi Gauss Jordan
	gauss(m);
	for (int idx=0; idx<m.getRow(); idx++) {
		for (int i=0; i<idx; i++) {
			int j = GaussJordan.firstNotZero(m, idx);
			if (j < m.getCol()) {
				for (int k=m.getCol()-1; k>=j; k--) {
					m.setELMT(i, k, m.getELMT(i,k) - (m.getELMT(idx,k) * m.getELMT(i,j)));
				}
			}
		}
	}
    }

    public static Matrix getGauss (Matrix m) {
        // return the gauss form of m
        
        Matrix res = new Matrix(m);

        gauss(res);
        return res;
    }

    public static Matrix getGaussJordan (Matrix m) {
        // reutrn the gaussjordan form of m
        Matrix res = new Matrix(m);

        gaussJordan(res);
        return res;
    }

    public static Matrix inverseSolution (Matrix m) {
        // KAMUS LOKAL
        Matrix b, A;

        // ALGORITMA

        b = m.getSubMatrix(0, m.getLastRow(), m.getLastCol(), m.getLastCol());
        A = m.getSubMatrix(0, m.getLastRow(), 0, m.getLastCol() - 1);

        return inverseSolution(A, b);
    }

    public static Matrix inverseSolution (Matrix A, Matrix b) {
        // KAMUS LOKAL
        Matrix inverse;

        // ALGORITMA

        if (!A.isSquare())
        {
            return null;
        }

        inverse = Inverse.getInverse(A);

        if (inverse == null)
        {
            return null;
        }
        
        return inverse.multiply(b);
    }
	
    
    public static void gaussJordanShowSolution(Matrix m) {
    	// I.S. Matrix m sudah dieliminasi Gauss-Jordan
    	// F.S. Menampilkan solusi SPL dari matrix m
    	
    	
    	// Mengecek apakah Matrix memiliki solusi SPL
    	for (int i=0; i<m.getRow(); i++) {
    		if (GaussJordan.firstNotZero(m, i) == m.getCol()-1) {
    			 Utils.println("SPL tidak memiliki solusi.");
    			 return;
    		}
    	}
    	
    	
    	// Menyimpan urutan variabel bebas
    	int freeVarCount = 0;
    	// counter jumlah variabel bebas
    	int[] freePosition;
    	// array int untuk menyimpan urutan var bebas, bernilai 0 jika bukan var bebas
    	freePosition = new int[m.getCol()-1];
    	int i = 0, j = 0;
    	while (j < m.getCol() - 1) {
    		if (j != GaussJordan.firstNotZero(m, i)) {
    			freeVarCount++;
    			freePosition[j] = freeVarCount;
    		}
    		if (m.getELMT(i, j) == 1 && i < m.getRow()-1) {
    			i++;
    		}
    		j++;
    	}
    	
    	
    	// Output hasil SPL 
    	i = 0; j = 0;
    	while (j < m.getCol() - 1) {
    		
    		// xj merupakan variabel bebas
    		if (j != GaussJordan.firstNotZero(m, i)) {
    			System.out.printf("x%d = t%d", j+1, freePosition[j]);
    		}
    		
    		// xj bukan variabel bebas
    		if (j == GaussJordan.firstNotZero(m, i)) {
    			System.out.printf("x%d =", j+1);
        		double cons = m.getELMT(i, m.getCol()-1);
        		// constanta persamaan 
        		boolean isNull = true;
        		// apakah persamaan sudah terisi
        		
        		if (cons != 0) {
        			System.out.printf(" " + Determinan.result(cons));
        			isNull = false;
        		}
        		
        		// output persamaan
        		for (int k=j+1; k<m.getCol()-1; k++) {
        			double coef = m.getELMT(i,k);
        			
        			// output operator
        	    	if (coef > 0) {
        	    		Utils.print(" -");
        	    	} else if (coef < 0 && !isNull) {
        	    		Utils.print(" +");
        	    	}
        	    	 
        	    	// jika koefisien bernilai -1 or 1 maka koef tidak ditulis di persamaan
        	    	// hrs pake ini krn java jelek gbs handle double
        	    	if (Math.abs(coef) - 1 <= 0.00000000000001 && Math.abs(coef)  -1 >= 0) {
        	    		System.out.printf(" t%d", freePosition[k]);
        	    		isNull = false;
        	    	} else if (coef != 0) {
        	    		System.out.print(" " + Determinan.result(Math.abs(coef)));
        	    		System.out.printf("t%d", freePosition[k]);
        	    		isNull = false;
        	    	}
        		}
    		}
    		Utils.println("");
    		
    		// ubah indeks
    		if (j == GaussJordan.firstNotZero(m, i) && i < m.getRow()-1) {
    			i++;
    		}
    		j++;
    	}
    	
    	// menulis keterangan jika terdapat variabel bebas
    	if (freeVarCount > 0) {
    		System.out.print("Dengan");
        	for (int k=1; k<=freeVarCount; k++) {
        		System.out.printf(" t%d", k);
        		if (k < freeVarCount - 1) {
        			System.out.print(",");
        		} else if (k == freeVarCount - 1) {
        			System.out.print(" dan");
        		}
        	}
        	System.out.println(" merupakan variabel bebas.");
    	}
 
    }

    public static void gaussShowSolution(Matrix m) {
    	// blm sempet buat penyuluhan mundur hehe
    	gaussJordan(m);
    	gaussJordanShowSolution(m);
    }
    
    public static void sistemPersamaanLinear() throws IOException {  	
    	// KAMUS
    	int op = 0;
    	Matrix m = new Matrix();
    	
    	// MENU SPL
    	Utils.println("SISTEM PERSAMAAN LINEAR\n");
    	Utils.println("Pilih metode pembacaan Matriks:");
    	Utils.println("1. File");
    	Utils.println("2. Keyboard");
    	
    	// INPUT MATRIX
    	op = Utils.select(1,3);
    	
    	if (op == 1) {
    		Utils.println("blom bisa banh awokwokwok.");
    		return;
    	} 
    	else {
    		m.inputMatrix();
    	}
    	
    	// MENU METODE
    	Utils.println("Pilih metode pembacaan Matriks SPL:");
    	Utils.println("1. Metode Eliminasi Gauss");
    	Utils.println("2. Metode Eliminasi Gauss-Jordan");
    	Utils.println("3. Metode Matrix Inverse");
    	Utils.println("4. Metode Cramer");
    	  	
    	op = Utils.select(1,4); 
    	
    	// proses sesuai metode yg dipilih
    	if (op == 1) {
    		Utils.println("\nMatrix awal:");
    		Matrix.displayMatrixAugmented(m, m.getLastCol() - 1);
    		gauss(m);
    		Utils.println("\n\nMatrix hasil eliminasi Gauss:");
    		Matrix.displayMatrixAugmented(m, m.getLastCol() - 1);
    		Utils.println("\n\nSolusi SPL:");
    		gaussShowSolution(m);
    	}
    	else if (op == 2) {
    		Utils.println("\nMatrix awal:");
    		Matrix.displayMatrixAugmented(m, m.getLastCol() - 1);
    		gaussJordan(m);
    		Utils.println("\n\nMatrix hasil eliminasi Gauss-Jordan:");
    		Matrix.displayMatrixAugmented(m, m.getLastCol() - 1);
    		Utils.println("\n\nSolusi SPL:");
    		gaussJordanShowSolution(m);
    	}
    	else if (op == 3) {
    		// blm fix
    		m = inverseSolution(m);
    	}
    	else {
    		// blm fix
    		Cramer.displayCrammer(m);
    	}
    	
    }
}
