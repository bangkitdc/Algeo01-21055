package mtrx.Methods;

import mtrx.Methods.Interpolation;
import java.io.*;

import mtrx.Matrix.Matrix;
import mtrx.Utility.*;;

public class Polinom {
    public static void driver() throws  IOException   {
        Interpolation.problem.InputNewProblem();
        displayPolinom(Interpolation.problem.getResult());

        while(true) {
            double x = Utils.inputDouble();

            Utils.println(Interpolation.problem.interpolate(x));
        }
    }

    public static void displayPolinom(Matrix result) {

        // PREKONDISI : result tidak kosong
        // KAMUS LOKAL
        String polinom;
        Utils.println("Solusi polinomial : ");

        polinom = "";
        
        for (int i = result.getLastRow(); i > 0; i--) {
            
            polinom += result.getELMT(i,0) == 0? 
                "" : Double.toString(result.getELMT(i,0)) + "x" + (i > 1? Integer.toString(i) : "");

            polinom += (result.getELMT(i - 1,0) < 0 || (i == 1 && result.getELMT(0, 0) == 0)) ? "" : "+";
        }

        polinom += result.getELMT(0,0) == 0? "" : Double.toString(result.getELMT(0,0));
        Utils.println(polinom);
    }
    
}
