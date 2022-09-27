package mtrx.Methods;
import mtrx.Matrix.Matrix;

public class GaussJordan {
	
	public static int firstNotZero(Matrix m, int i) {
		for (int j=0; j<m.getCol(); j++) {
			if (m.getELMT(i,j) != 0) {
				return j;
			}
		}
		return m.getCol();
	}
	
	public static void sortMatrix(Matrix m) {
		for (int i=0; i<m.getRow()-1; i++) {
			for (int j=i+1; j<m.getRow(); j++) {
				if (firstNotZero(m, j) < firstNotZero(m, i)) {
					m.swapRow(i, j);
				}
			}
		}
	}

	public static void makeOne(Matrix m) {
		for (int i=0; i<m.getRow(); i++) {
			int j = firstNotZero(m,i);
			if (j < m.getCol()) {
				double divider = m.getELMT(i,j);
				if (divider != 0) {
					for (int k=j; k < m.getCol(); k++) {
						m.setELMT(i, k, m.getELMT(i,k)/divider);
					}				
				}
			}
		}
	}
	
	public static double gataugajelasbiargaerror(double x) {
		if (x >= -0.00000000000001 && x <= 0.00000000000001) {
			return 0;
		}
		return x;
	}
	
	public static void OBE(Matrix m, int idx) {
		int j = firstNotZero(m, idx);
		if (j < m.getCol()) {
			for (int i=idx+1; i < m.getRow(); i++) {
				double multiplier = m.getELMT(i,j)/m.getELMT(idx, j); 
				for (int k=j; k < m.getCol(); k++) {
					m.setELMT(i, k, gataugajelasbiargaerror(m.getELMT(i,k)- m.getELMT(idx,k)*multiplier));
				}
			}
		}
	}
	
	public static void determinantOBE(Matrix m) {
		// I.S. Matrix
		// F.S. Matrix di OBE tp diagonal gk dibuat 1
		sortMatrix(m);
		for (int i=0; i<m.getRow()-1; i++) {
			OBE(m,i);
		}
	}
}
