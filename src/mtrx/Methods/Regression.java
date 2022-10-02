package mtrx.Methods;
import mtrx.Matrix.*;
import mtrx.Utility.*;
import java.io.*;
import java.lang.Math;

public class Regression {

	public static Matrix regressionInput() throws IOException {
		// I.S. -
		// F.S. return matrix berisi input untuk regresi
		
		// input baris kolom
		Utils.print("\nMasukkan banyaknya peubah variabel x\n");
		int n = Utils.inputInt();
		Utils.print("Masukkan banyaknya sampel variabel\n");
		int k = Utils.inputInt();
		Utils.println("");

		Matrix m = new Matrix(k, n+1);
		
		// input nilai x dan y setiap persamaan
		for (int i=0; i<k; i++) {
			Utils.printf("\nPersamaan %d\n", i+1);
			for (int j=0; j<n; j++) {
				Utils.printf("Masukkan nilai variabel x%d\n", j+1);
				m.setELMT(i, j, Utils.inputDouble());
			}
			Utils.print("Masukkan nilai variabel y\n");
			m.setELMT(i, n, Utils.inputDouble());
		}
		
		Matrix.displayMatrix(m);
		return m;
	}
	
	public static Matrix regressionMatrix(Matrix mIn) {
		// I.S. matrix berisi input regresi
		// F.S. matrix SPL yg merupakan matrix input yg sudah diproses
		
		Matrix mOut = new Matrix(mIn.getCol(), mIn.getCol()+1);
		
		// set m[0][0]
		mOut.setELMT(0, 0, mIn.getRow());
		
		// set baris paling atas dan kolom paling kiri
		for (int i=0; i<mIn.getRow(); i++) {
			for (int j=1; j<=mIn.getCol(); j++) {
				mOut.setELMT(0, j, mOut.getELMT(0, j) + mIn.getELMT(i, j-1));
				if (j<mIn.getCol()) {
					mOut.setELMT(j, 0, mOut.getELMT(0, j));	
				}
			}
		}
		
		// set sisa matrix yg kosong
		for (int i=1; i<mIn.getCol(); i++) {
			for (int j=1; j<=mIn.getCol(); j++) {
				for (int k=0; k<mIn.getRow(); k++) {
					mOut.setELMT(i, j, mOut.getELMT(i, j) + mIn.getELMT(k, j-1)*mIn.getELMT(k, i-1));
				}
			}
		}
		
		return mOut;
	}
	
	public static String regressionFunction(Matrix m, Matrix x) {
		// I.S. matrix m berisi SPL regresi yg sudah dieliminasi
		//      matrix x berisi nilai xi yang ingin ditaksir 
		// F.S. string berisi hasil fungsi regresi
		
		String solution= "";
		
		// menghitung hasil fungsi
		double answer = m.getELMT(0, m.getCol()-1);
		for (int i=0; i<x.getCol(); i++) {
			answer += m.getELMT(i+1, m.getCol()-1) * x.getELMT(0, i);
		}
		
		// output hasil
		solution += "\nMaka untuk input:\n";
		for (int i=0; i<x.getCol(); i++) {
			solution += String.format("x%d = %s\n", i+1, Utils.result(x.getELMT(0, i)));
		}
		solution += "Didapatkan f(x) = " + Utils.result(answer) + "\n";
		
		return solution;
	}
	
	public static String equationOutput(Matrix m) {
		// I.S. Matrix m sudah dieliminasi
		// F.S. return String solusi regresi
		
		
		// KAMUS
		int idx = m.getCol() - 1;
		boolean isNull = true;
		String solution = "Persamaan regresi yang terbentuk:\nf(x) = ";
		
		
		// handle konstanta
		if (m.getELMT(0, idx) != 0) {
			solution += Utils.result(m.getELMT(0, idx)) + " ";
			isNull = false;
		}
		
		// handle variabel x
		for (int i=1; i<m.getRow(); i++) {
			
			// handle operator
			if (!isNull && m.getELMT(i, idx) != 0) {
				if (m.getELMT(i,idx) > 0) {
					solution += "+ ";
				}
				else {
					solution += "- ";
				}
			}
			
			// handle koefisien
			double coef = Math.abs(m.getELMT(i,idx));
			if (coef == 1) {
				solution += String.format("x%d ", i);
				isNull = false;
			} else if (m.getELMT(i, idx) != 0) {
				solution += String.format("%sx%d ", Utils.result(coef), i);
				isNull = false;
			}
		}
		
		// f(x) = 0
		if (isNull) {
			solution += "0";
		}
		
		solution += "\n";
		return solution;
	}
	
	public static void regConsole(Matrix inputMatrix) throws IOException {
		// DEKLARASI VARIABEL
		String solution;
		
    	// INPUT NILAI Xi YANG AKAN DITAKSIR HASILNYA
		Matrix x = new Matrix(1,inputMatrix.getCol()-1);
		Utils.println("\nMasukkan nilai-nilai x yang akan ditaksir nilai fungsinya.");
		for (int i=0; i<x.getCol(); i++) {
			Utils.printf("Masukkan nilai variabel x%d\n", i+1);
			x.setELMT(0, i, Utils.inputDouble());
		}
		
		// Membentuk Matrix SPL dari input
		Matrix regM = regressionMatrix(inputMatrix);
		Utils.println("\nMatriks SPL yang terbentuk:");
		Matrix.displayMatrixAugmented(regM, regM.getLastCol()-1);
		
		// Mengeliminasi Matrix SPL
		SolveSPL.gaussJordan(regM);
		Utils.println("\nMatriks SPL yang telah dieliminasi:");
		Matrix.displayMatrixAugmented(regM, regM.getLastCol()-1);
		
		// Membuat solusi regresi
		solution = equationOutput(regM);		
		solution += regressionFunction(regM, x);
		Utils.print("\n" + solution);
	}
	
	public static void regFile(Matrix inputMatrix) throws IOException {
		// DEKLARASI VARIABEL
		String outputFile = Menu.outputFile();
		String solution;

    	// INPUT NILAI Xi YANG AKAN DITAKSIR HASILNYA
		Matrix x = new Matrix(1,inputMatrix.getCol()-1);
		Utils.println("\nMasukkan nilai-nilai x yang akan ditaksir nilai fungsinya.");
		for (int i=0; i<x.getCol(); i++) {
			Utils.printf("Masukkan nilai variabel x%d\n", i+1);
			x.setELMT(0, i, Utils.inputDouble());
		}
		
		// Membentuk Matrix SPL dari input
		Matrix regM = regressionMatrix(inputMatrix);
		
		// Mengeliminasi Matrix SPL
		SolveSPL.gaussJordan(regM);
		
		// Membuat solusi regresi
		solution = equationOutput(regM);		
		solution += regressionFunction(regM, x);
		IO.writeFileString(outputFile, solution);
		Utils.println("Berhasil menuliskan file :)");
	}    
}
	
