package mtrx.Methods;
import mtrx.Matrix.Matrix;
import mtrx.Utility.Utils;
import java.util.Scanner;

public class Regression {
	
	public static Matrix regressionInput() {
		Scanner input = new Scanner(System.in);
		
		Utils.print("Masukkan banyaknya peubah variabel x: ");
		int n = input.nextInt();
		Utils.print("Masukkan banyaknya sampel variabel: ");
		int k = input.nextInt();
		Utils.println("");
		
		Matrix m = new Matrix(n+1, k);
		
		for (int i=0; i<k; i++) {
			Utils.printf("\nPersamaan %d\n", i+1);
			for (int j=0; j<n; j++) {
				Utils.printf("Masukkan nilai variabel x%d: ", j+1);
				m.setELMT(j, i, input.nextDouble());
			}
			Utils.print("Masukkan nilai variabel y: ");
			m.setELMT(n, i, input.nextDouble());
		}
		return m;
	}
	
	public static Matrix regressionMatrix(Matrix mIn) {
		Matrix mOut = new Matrix(mIn.getRow(), mIn.getRow()+1);
		mOut.setELMT(0, 0, mIn.getCol());
		for (int i=0; i<mIn.getCol(); i++) {
			for (int j=1; j<=mIn.getRow(); j++) {
				mOut.setELMT(0, j, mOut.getELMT(0, j) + mIn.getELMT(j-1, i));
				if (j<mIn.getRow()) {
					mOut.setELMT(j, 0, mOut.getELMT(0, j));	
				}
			}
		}
		for (int i=1; i<mIn.getRow(); i++) {
			for (int j=1; j<=mIn.getRow(); j++) {
				for (int k=0; k<mIn.getCol(); k++) {
					mOut.setELMT(i, j, mOut.getELMT(i, j) + mIn.getELMT(j-1, k)*mIn.getELMT(i-1, k));
				}
			}
		}
		return mOut;
	}
	
	public static void regressionFunction(Matrix m, Matrix x) {
		double answer = m.getELMT(0, m.getCol()-1);
		for (int i=0; i<x.getCol(); i++) {
			answer += m.getELMT(i+1, m.getCol()-1) * x.getELMT(0, i);
		}
		Utils.println("Maka untuk input");
		for (int i=0; i<x.getCol(); i++) {
			System.out.printf("x%d = %.2f\n", i+1, x.getELMT(0, i));
		}
		Utils.printf("Didapatkan f(xk) = %.2f", answer);
	}
	
	public static void equationOutput(Matrix m) {
		int idx = m.getCol() - 1;
		boolean isNull = true;
		
		Utils.println("\nPersamaan regresi yang terbentuk:");
		Utils.print("f(x) = ");
		
		if (m.getELMT(0, idx) == 1) {
			Utils.print("1 ");
			isNull = false;
		} else if (m.getELMT(0, idx) != 0) {
			Utils.printf("%.2f ", m.getELMT(0, idx));
			isNull = false;
		}
		
		for (int i=1; i<m.getRow()-1; i++) {
			if (!isNull && i != m.getRow()-1 && m.getELMT(i, idx) != 0) {
				Utils.print("+ ");
			}
			if (m.getELMT(i, idx) == 1) {
				Utils.printf("x%d ", i);
				isNull = false;
			} else if (m.getELMT(i, idx) != 0) {
				System.out.printf("%.2fx%d ", m.getELMT(i, idx), i);
				isNull = false;
			}
		}
		if (isNull) {
			Utils.print("0");
		}
		Utils.println("");
	}
	
	public static void multipleLinearRegression() {
		Scanner input = new Scanner(System.in);
		
		Matrix inputMatrix = regressionInput();
		
		Matrix x = new Matrix(1,inputMatrix.getRow()-1);
		Utils.println("Masukkan nilai-nilai x yang akan ditaksir nilai fungsinya.");
		for (int i=0; i<x.getCol(); i++) {
			Utils.printf("Masukkan nilai x%d: ", i+1);
			x.setELMT(0, i, input.nextDouble());
		}
		
		Matrix regM = regressionMatrix(inputMatrix);
		Utils.println("\nMatriks SPL yang terbentuk:");
		Matrix.displayMatrix(regM);
		
		SolveSPL.gaussJordan(regM);
		Utils.println("\nMatriks SPL yang telah dieliminasi:");
		Matrix.displayMatrix(regM);
		
		equationOutput(regM);		
		
		regressionFunction(regM, x);
	}
}
