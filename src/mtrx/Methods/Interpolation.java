package mtrx.Methods;
import mtrx.Matrix.Matrix;
import mtrx.Utility.*;
import java.io.*;

public class Interpolation {

    public static class Problem {
        private int n;
        private Matrix points, m, result;
        private double x;

        private void inputPoints() {
            Utils.println("Masukkan semua titik (x y) : ");

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

            Utils.println("Masukan derajat polinomial interpolasi : ");
            n = Utils.inputInt();

            points = new Matrix(n+1, 2);
            m = new Matrix(n+1, n+2);
            problem.inputPoints();

            Utils.println("Masukan nilai x untuk memperoleh nilai f(x) hasil interpolasi : ");
            x = Utils.inputDouble();
            
            solve(); 
        }

        public Matrix getResult()
        {
            return new Matrix(result);
        }

        public double interpolate(double x) 
        {
            // PREKONDISI : solusi tidak kosong
            double sum = 0;

            for (int i = 0; i < result.getRow(); i++){
                sum += result.getELMT(i, 0) * Math.pow(x, i);
            }

            return sum;
        }

        public void displayInterpolation() throws  IOException {  

            displayPolinom(Interpolation.problem.getResult());

            Utils.println("nilai f(x) hasil interpolasi dengan x = " + Double.toString(x) + " : ");
            Utils.println(Interpolation.problem.interpolate(x));

        }

        public void displayPolinom(Matrix result) throws  IOException {

            // PREKONDISI : result tidak kosong
            // KAMUS LOKAL
            String polinom;
            Utils.println("Solusi polinomial : ");
    
            polinom = "f(x) = ";
            
            for (int i = result.getLastRow(); i > 0; i--) {
                
                polinom += result.getELMT(i,0) == 0? 
                    "" : Double.toString(result.getELMT(i,0)) + "x" + (i > 1? (Integer.toString(i)) : "");
    
                if(result.getELMT(i, 0) != 0)
                {
                    polinom += ((result.getELMT(i - 1,0) < 0) || (i == 1 && result.getELMT(0, 0) == 0)) ? "" : "+";
                }
            }
    
            polinom += result.getELMT(0,0) == 0? "" : Double.toString(result.getELMT(0,0));
            Utils.println(polinom);
        }

    }

    public static Problem problem = new Problem();

    
}
