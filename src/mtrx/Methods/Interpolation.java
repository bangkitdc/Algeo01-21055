package mtrx.Methods;
import mtrx.Matrix.Matrix;
import mtrx.Utility.*;
import java.io.*;

public class Interpolation {

    public static class Problem {
        private int n;
        private Matrix points, m, result;
        private Double minBound, maxBound;

        private void inputPoints() {
            Utils.println("Masukkan semua titik (x y) : ");

            points.createMatrix();
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

        private void getBound() {

            Double min, max;

            min = getPointAbsis(0);
            max = getPointAbsis(0);
            for(int i = 1; i<= n; i++)
            {
                if (min > getPointAbsis(i))
                {
                    min = getPointAbsis(i);
                }

                if (max < getPointAbsis(i))
                {
                    max = getPointAbsis(i);
                }
                
            }

            minBound = min;
            maxBound = max;
        }

        private void solve() {

            createAugmented();       
            result = SolveSPL.inverseSolution(m);
            getBound();

        }

        private double getPointAbsis(int row) {
            return points.getELMT(row, 0);
        }

        private double getPointOrdinat(int row) {
            return points.getELMT(row, 1);
        }

        public void InputNewProblem(int inputMethod) throws IOException {

            switch (inputMethod) {
                case 1:
                 
                    break;
                case 2:
                    InputNewProblem();
                    break;
                default:
                    break;
            }

            solve();

            displayInterpolation();
            
        }

        public void InputNewProblem() throws IOException {

            Utils.println("Masukan derajat polinomial interpolasi : ");
            n = Utils.inputInt();

            points = new Matrix(n+1, 2);
            m = new Matrix(n+1, n+2);
            problem.inputPoints();
        }

        public void InputAbsis() throws IOException {
            
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

            double x;
            String input;
            BufferedReader bufferedReader = new BufferedReader(Utils.streamReader);

            displayPolinom(Interpolation.problem.getResult());

            do {

                Utils.println("Masukkan nilai x [" 
                    + Utils.doubleToString(minBound, 4) 
                    + ", " 
                    + Utils.doubleToString(maxBound, 4)
                    + "] (inklusif) untuk interpolasi.");

                Utils.println("(input bukan bilangan akan memberhentikan proses ini)");
                Utils.print("> ");
        
                input = bufferedReader.readLine();

                if (Utils.isDouble(input))
                {
                    x = Double.parseDouble(input);

                    if (x < minBound || x > maxBound)
                    {
                        Utils.println("Mohon masukkan input x sesuai rentang interpolasi.");
                    }

                    else {
                        Utils.print("f(" +input + ") = ");
                        Utils.println(Utils.result(Interpolation.problem.interpolate(x)));
                    }
                }

            } while (Utils.isDouble(input));

            Utils.println("Operasi interpolasi selesai :)");
        }

        public void createOutputFile() throws  IOException {

        }

        public void displayPolinom(Matrix result) throws  IOException {

            // PREKONDISI : result tidak kosong
            // KAMUS LOKAL
            String polinom;
            Utils.println("Solusi polinomial : ");
    
            polinom = "f(x) = ";
            
            for (int i = result.getLastRow(); i > 0; i--) {
                
                polinom += result.getELMT(i,0) == 0? 
                    "" : Utils.doubleToString(result.getELMT(i,0), 4) + "x" + (i > 1? (Integer.toString(i)) + " ": " ");
    
                if(result.getELMT(i, 0) != 0)
                {
                    polinom += ((result.getELMT(i - 1,0) < 0) || (i == 1 && result.getELMT(0, 0) == 0)) ? "" : "+ ";
                }
            }
    
            polinom += result.getELMT(0,0) == 0? "" : Utils.doubleToString(result.getELMT(0,0), 4);

            Utils.println(polinom);
        }

    }

    public static Problem problem = new Problem();

    
}
