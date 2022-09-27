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
	
	public static Matrix sortMatrix(Matrix m) {
		for (int i=0; i<m.getRow()-1; i++) {
			for (int j=i+1; j<m.getRow(); j++) {
				if (firstNotZero(m, j) < firstNotZero(m, i)) {
					m.swapRow( i, j);
				}
			}
		}
		return m;
	}

	public static Matrix makeOne(Matrix m) {
		for (int i=0; i<m.getRow(); i++) {
			int j = firstNotZero(m,i);
			if (j < m.getCol()-1) {
				double divider = m.getELMT(i,j);
				if (divider != 0) {
					for (int k=j; k < m.getCol(); k++) {
						m.setELMT(i, k, m.getELMT(i,k)/divider);
					}				
				}
			}
		}
		return m;
	}
	
	public static double roundOff(double x) {
		if (x >= -0.00000000000001 && x < 0) {
			return 0;
		}
		return x;
	}
	
	public static Matrix OBE(Matrix m, int idx) {
		int j = firstNotZero(m, idx);
		if (j < m.getCol()) {
			for (int i=idx+1; i < m.getRow(); i++) {
				double multiplier = m.getELMT(i,j)/m.getELMT(idx, j); 
				for (int k=j; k < m.getCol(); k++) {
					m.setELMT(i, k, roundOff(m.getELMT(i,k)- m.getELMT(idx,k)*multiplier));
				}
			}
		}
		return m;
	}
	
	public static Matrix determinantOBE(Matrix m) {
		// I.S. Matrix
		// F.S. Matrix di OBE tp diagonal gk dibuat 1
		m = sortMatrix(m);
		for (int i=0; i<m.getRow()-1; i++) {
			m = OBE(m,i);
		}
		return m;
	}
	

	public static Matrix gauss(Matrix m) {
		// I.S. Matrix
		// F.S. Matrix eselon yg sudah dieliminasi Gauss
		m = sortMatrix(m);
		for (int i=0; i < m.getRow()-1; i++) {
			m = OBE(makeOne(m),i);
		}
		return sortMatrix(makeOne(m));
	}
	
	public static Matrix gaussJordan(Matrix m) {
		// I.S. Matrix
		// F.S. Matrix eselon yg sudah dieliminasi Gauss Jordan
		m = gauss(m);
		for (int idx=0; idx<m.getRow(); idx++) {
			for (int i=0; i<idx; i++) {
				int j = firstNotZero(m, idx);
				if (j < m.getCol()) {
					for (int k=m.getCol()-1; k>=j; k--) {
						m.setELMT(i, k, m.getELMT(i,k) - (m.getELMT(idx,k) * m.getELMT(i,j)));
					}
				}
			}
		}
		return m;
	}
}
