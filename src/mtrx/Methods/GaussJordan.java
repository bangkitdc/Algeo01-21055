package mtrx.Methods;
import mtrx.Matrix.Matrix;

public class GaussJordan {

	public static Matrix swapRow(Matrix m, int a, int b) {
		for (int i=0; i<m.getCol(); i++) {
			double temp = m.getELMT(b, i);
			m.setELMT(b, i, m.getELMT(a,i));
			m.setELMT(a, i, temp);
		}
		return m;
	}
	
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
					swapRow(m, i, j);
				}
			}
		}
		return m;
	}


	public static Matrix makeOne(Matrix m, int i) {
		int j = firstNotZero(m,i);
		if (j < m.getCol()) {
			double divider = m.getELMT(i,j);
			if (divider != 0) {
				for (int k=j; k < m.getCol(); k++) {
					m.setELMT(i, k, m.getELMT(i,k)/divider);
				}				
			}
		}
		return m;
	}
	
	
	public static Matrix OBE(Matrix m, int idx) {
		for (int i=0; i<m.getRow(); i++) {
			makeOne(m, i);
		}
		int j = firstNotZero(m, idx);
		if (j < m.getCol()) {
			for (int i=idx+1; i < m.getRow(); i++) {
				if (m.getELMT(i,j) == 1) {
					for (int k=j; k < m.getCol(); k++) {
						m.setELMT(i, k, m.getELMT(i,k)-m.getELMT(idx,k));
					}
				}
			}
		}
		return m;
	}

	public static Matrix gauss(Matrix m) {
		// I.S. Matrix non parametrik
		// F.S. Matrix eselon yg sudah dieliminasi Gauss
		for (int i=0; i < m.getRow()-1; i++) {
			m = sortMatrix(m);
			m = OBE(m,i);
		}
		m = makeOne(m, m.getRow()-1);
		return m;
	}
	
	public static Matrix gaussJordan(Matrix m) {
		// I.S. Matrix non parametrik
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
