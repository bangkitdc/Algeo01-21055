package mtrx.Methods;
import mtrx.Matrix.Matrix;
import mtrx.Utility.*;
import java.io.*;

import javax.rmi.CORBA.Util;

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

            Matrix res;
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

                m.setELMT(i, n, getPointOrdinat(i));
                
            }
        }

        private int getAmmount() {
            return n;
        }

        private double getPointAbsis(int row) {
            return points.getELMT(row, 0);
        }

        private double getPointOrdinat(int row) {
            return points.getELMT(row, 1);
        }

        private double getX() {
            return x;
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

    }

    public static Problem problem;

    public static void Driver () throws  IOException {
        problem.InputNewProblem();
    }

}
