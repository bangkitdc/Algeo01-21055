package mtrx.Methods;

import mtrx.Matrix.*;
import mtrx.Utility.*;
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
        	// return the gaussjordan form of m
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
	
  	public static String gaussJordanOutputSolution(Matrix m) {
    		// I.S. Matrix m sudah dieliminasi Gauss-Jordan
    		// F.S. return String solusi 
    	
    		String solution = "";
    	
    		// Mengecek apakah Matrix memiliki solusi SPL
    		for (int i=0; i<m.getRow(); i++) {
    			if (GaussJordan.firstNotZero(m, i) == m.getCol()-1) {
    				solution = "SPL tidak memiliki solusi.";
    				return solution;
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
    		solution += "Solusi SPL:\n";
    		i = 0; j = 0;
    		while (j < m.getCol() - 1) {
    		
    		// xj merupakan variabel bebas
    		if (j != GaussJordan.firstNotZero(m, i)) {
    			solution += String.format("x%d = t%d", j+1, freePosition[j]);
    		}
    		
    		// xj bukan variabel bebas
    		if (j == GaussJordan.firstNotZero(m, i)) {
    			solution += String.format("x%d =", j+1);
    			//System.out.printf("x%d =", j+1);
        		double cons = m.getELMT(i, m.getCol()-1);
        		// constanta persamaan 
        		boolean isNull = true;
        		// apakah persamaan sudah terisi
        		
        		if (cons != 0) {
        			solution += " " + Utils.doubleToString(cons);
        			//System.out.printf(" " + Determinan.result(cons));
        			isNull = false;
        		}
        		
        		// output persamaan
        		for (int k=j+1; k<m.getCol()-1; k++) {
        			double coef = m.getELMT(i,k);
        			
        			// output operator
        	    		if (coef > 0) {
        	    			solution += " -";
        	    		} else if (coef < 0 && !isNull) {
        	    			solution += " +";
        	    		}
        	    	 
        	    		// jika koefisien bernilai -1 or 1 maka koef tidak ditulis di persamaan
        	    		// hrs pake ini krn java jelek gbs handle double
        	    		if (Math.abs(coef) - 1 <= 0.00000000000001 && Math.abs(coef)  -1 >= 0) {
        	    			solution += String.format(" t%d", freePosition[k]);
        	    			isNull = false;
        	    		} else if (coef != 0) {
        	    			solution += String.format(" " + Utils.doubleToString(Math.abs(coef)) + "t%d", freePosition[k]);
        	    			isNull = false;
        	    		}
        		}
        		
        		// jika persamaan masih kosong berarti var = 0
        		if (isNull) {
      		  			solution += " 0";
        			}
        			
    			}
	    		solution += "\n";
    			
    			// ubah indeks
    			if (j == GaussJordan.firstNotZero(m, i) && i < m.getRow()-1) {
    				i++;
    			}
    			j++;
   	 	}
    	
    		// menulis keterangan jika terdapat variabel bebas
    		if (freeVarCount > 0) {
    			solution += "Dengan";
        		for (int k=1; k<=freeVarCount; k++) {
        			solution += String.format(" t%d", k);
       	 			if (k < freeVarCount - 1) {
        				solution += ",";
        			} else if (k == freeVarCount - 1) {
        				solution += " dan";
        			}
        		}
        		solution += " merupakan variabel bebas.\n";
    		}
    		return solution;
   	 }

   	 public static String gaussOutputSolution(Matrix m) {
    		// blm sempet buat penyuluhan mundur hehe
    		gaussJordan(m);
    		return gaussJordanOutputSolution(m);
    	}
    
    	public static String inverseOutputSolution(Matrix m) {
    		String solution = "";
    		if (m == null) {
    			solution = "SPL tidak memiliki solusi atau tidak bisa diselesaikan dengan Metode Matrix Inverse.\n";
    			return solution;
    		}
    	
    		solution += "Solusi:\n";
    		for (int i=0; i<m.getRow(); i++) {
    			solution += String.format("x%d = %s\n", i+1, Utils.doubleToString(m.getELMT(i,0)));
    		}
    		return solution;
	}
    
    
	/* -------------------------- Input ------------------------- */
	/* Console */
	public static void splConsole(Matrix m) throws IOException{
		Utils.println("");
		Utils.println("=========== Pilih Metode ==========");
		Utils.println("[1] Metode Eliminasi Gauss");
		Utils.println("[2] Metode Eliminasi Gauss-Jordan");
		Utils.println("[3] Metode Matrix Balikan");
		Utils.println("[4] Kaidah Cramer");
		Utils.println("Masukkan pilihan: ");

		int input = Utils.select(1, 4);
		switch (input) {
			case 1: // Gauss
				Utils.println("\nMatrix awal:");
				Matrix.displayMatrixAugmented(m, m.getLastCol() - 1);
				gauss(m);
				Utils.println("\nMatrix hasil eliminasi Gauss:");
				Matrix.displayMatrixAugmented(m, m.getLastCol() - 1);
				Utils.print("\n" + gaussOutputSolution(m));
				break;
			case 2: // Gauss-Jordan
				Utils.println("\nMatrix awal:");
				Matrix.displayMatrixAugmented(m, m.getLastCol() - 1);
				gaussJordan(m);
				Utils.println("\nMatrix hasil eliminasi Gauss-Jordan:");
				Matrix.displayMatrixAugmented(m, m.getLastCol() - 1);
				Utils.print("\n" + gaussJordanOutputSolution(m));
				break;
			case 3: // Inverse
				Utils.println("\nMatrix awal:");
				Matrix.displayMatrixAugmented(m, m.getLastCol() - 1);
				m = inverseSolution(m);
				Utils.print("\n" + inverseOutputSolution(m));
				break;
			case 4: // Cramer
				Cramer.displayCramer(m);
				break;
			default:
				break;
		}
	}

	/* File */
	public static void splFile(Matrix m, String relativePath) throws IOException{
		String outputFile = IO.inputNewFileName(relativePath, ".txt");
		Utils.println("");
		Utils.println("=========== Pilih Metode ==========");
		Utils.println("[1] Metode Eliminasi Gauss");
		Utils.println("[2] Metode Eliminasi Gauss-Jordan");
		Utils.println("[3] Metode Matrix Balikan");
		Utils.println("[4] Kaidah Cramer");
		Utils.println("Masukkan pilihan: ");

		int input = Utils.select(1, 4);
		switch (input) {
			case 1: // Gauss
				gauss(m);
	    		IO.writeFileString(gaussOutputSolution(m), outputFile, relativePath);
				Utils.println("\nBerhasil menuliskan file :)");
				break;
			case 2: // Gauss-Jordan
				gaussJordan(m);
	    		IO.writeFileString(gaussJordanOutputSolution(m), outputFile, relativePath);
				Utils.println("\nBerhasil menuliskan file :)");
				break;
			case 3: // Inverse
				m = inverseSolution(m);
	    		IO.writeFileString(inverseOutputSolution(m), outputFile, relativePath);
				Utils.println("\nBerhasil menuliskan file :)");
				break;
			case 4: // Cramer
				Cramer.fileCramer(m, outputFile, relativePath);
				Utils.println("\nBerhasil menuliskan file :)");
				break;
			default:
				break;
		}
	}
}
