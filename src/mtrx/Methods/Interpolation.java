package mtrx.Methods;
import mtrx.Matrix.IO;
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

        public void inputNewProblem(String relativePath) throws IOException {

            inputProblemFile(relativePath);

            solve();
        }

        public void inputNewProblem() throws IOException {

            Utils.println("Masukan derajat polinomial interpolasi : ");
            n = Utils.inputInt();

            points = new Matrix(n+1, 2);
            m = new Matrix(n+1, n+2);
            problem.inputPoints();

            solve();
        }

        public void inputProblemFile(String relativePath) throws IOException {
            Matrix problem = new Matrix();

            problem.inputFileMatrix(relativePath);

            n = (int) problem.getELMT(0, 0);
            m = new Matrix(n+1, n+2);
            points = problem.getSubMatrix(1, problem.getLastRow(), 0, 1);
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

            Utils.println(getPolinom(Interpolation.problem.getResult()));

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
                        Utils.println(Utils.doubleToString(Interpolation.problem.interpolate(x)));
                    }
                }

            } while (Utils.isDouble(input));

            Utils.println("Operasi interpolasi selesai :)");
        }

        public void createOutputFile(String relativePath) throws  IOException {
            String defaultExtension = ".txt";
            String res = getPolinom(result) + "\n";
            BufferedReader bufferedReader = new BufferedReader(Utils.streamReader);
            String input = new String();

            do {

                Utils.println("Masukkan nilai x [" 
                    + Utils.doubleToString(minBound, 4) 
                    + ", " 
                    + Utils.doubleToString(maxBound, 4)
                    + "] (inklusif) untuk interpolasi.");

                Utils.println("(input bukan bilangan akan memberhentikan proses ini)");
                Utils.print("> ");
        
                input = bufferedReader.readLine();
                Double x;

                if (Utils.isDouble(input))
                {
                    x = Double.parseDouble(input);

                    if (x < minBound || x > maxBound)
                    {
                        Utils.println("Mohon masukkan input x sesuai rentang interpolasi.");
                    }

                    else {
                        res += "f(" +input + ") = " + Utils.doubleToString(Interpolation.problem.interpolate(x), 8) + "\n";
                    }
                }

            } while (Utils.isDouble(input));

            IO.writeFileString(res, IO.inputNewFileName(relativePath, defaultExtension), relativePath);
        }

        public String getPolinom(Matrix result) throws IOException {

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

            return polinom;
        }

    }

    public static Problem problem = new Problem();

    
}
