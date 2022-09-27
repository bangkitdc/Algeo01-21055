package mtrx.Methods;
import mtrx.Matrix.Matrix;
import mtrx.Utility.*;
import java.io.*;

public class Interpolation {

    public static class Problem {
        private int n;
        private Matrix points;
        private Matrix m;
        private double x;
        private Matrix result;

        private void inputPoints() {
            points.createMatrix();
        }

        private void solve() {

            createAugmented();       
            result = SolveSPL.inverseSolution(m);

        }

        private void createAugmented(){
            for(int i = 0; i<= n; i++)
            {
                for(int j = 0; j <= n; j++)
                {
                    m.setELMT(i, j, Math.pow(getPointAbsis(i), j));
                }
            
                m.setELMT(i, n+1, getPointOrdinat(i));
                
            }
        }

        private double getPointAbsis(int row) {
            return points.getELMT(row, 0);
        }

        private double getPointOrdinat(int row) {
            return points.getELMT(row, 1);
        }

        public void InputNewProblem() throws IOException {

            n = Utils.inputInt();

            points = new Matrix(n+1, 2);
            m = new Matrix(n+1, n+2);

            problem.inputPoints();

            x = Utils.inputDouble();
            
            solve();       
        }

        public Matrix getResult()
        {
            return new Matrix(result);
        }

        public double interpolate() 
        {
            // PREKONDISI : solusi tidak kosong
            double sum = 0;

            for (int i = 0; i < result.getRow(); i++){
                sum += result.getELMT(i, 0) * Math.pow(x, i);
            }

            return sum;
        }

    }

    public static Problem problem = new Problem();
}
