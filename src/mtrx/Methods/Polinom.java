package mtrx.Methods;

import mtrx.Methods.Interpolation;
import java.io.*;

import javax.naming.spi.DirStateFactory.Result;

import mtrx.Matrix.Matrix;
import mtrx.Utility.*;;

public class Polinom {
    public static void driver() throws  IOException   {
        Interpolation.problem.InputNewProblem();
        displayPolinom(Interpolation.problem.getResult());
    }

    public static void displayPolinom(Matrix result) {

        // PREKONDISI : result tidak kosong

        String polinom = Double.toString(result.getELMT(0,0));
        for (int i = 1; i < result.getRow(); i++) {
            
            polinom += result.getELMT(i,0) < 0 ? "" : "+";
            polinom += result.getELMT(i,0) == 0? 
                "" : Double.toString(result.getELMT(i,0)) + "x" + (i > 1? Integer.toString(i) : "");
        }

        Utils.println(polinom);
    }
    
}
