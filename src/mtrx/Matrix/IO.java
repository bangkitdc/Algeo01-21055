package mtrx.Matrix;

import mtrx.Utility.Utils;

public class IO {
    /* ------------------------- Console Input ------------------------ */
    public static void inputMatrix() {

    }

    /* --------------------------- File Input ------------------------- */


    /* ------------------------ Console Output ------------------------ */
    /* Display Matrix 2D */
    public static void displayMatrix(Matrix m) {
        /* KAMUS LOKAL */
        int i, j;
        int weight;
        String s;

        /* ALGORITMA */
        weight = 0;
        // Search the biggest weight first
        for (i = 0; i < m.getRow(); i++) {
            for (j = 0; j < m.getCol(); j++) {
                if (Utils.getLengthELMT(m.getELMT(i, j)) > weight) {
                    weight = Utils.getLengthELMT(m.getELMT(i, j));
                }
            }
        }
        weight++;

        // Search the biggest weight (first column only)
        int wLeft = 0;
        for (i = 0; i < m.getRow(); i++) {
            if (Utils.getLengthELMT(m.getELMT(i, 0)) > wLeft) {
                wLeft = Utils.getLengthELMT(m.getELMT(i, 0));
            }
        }

        for (i = 0; i < m.getRow(); i++) {
            for (j = 0; j < m.getCol(); j++) {
                s = String.format("%d", weight);
                if (m.getELMT(i, j) == (int) m.getELMT(i, j)) { // int
                    if (j == 0) {
                        s = String.format("%d", wLeft);
                    }
                    Utils.printf("%" + s + "d", (int) m.getELMT(i, j));
                } else { // double
                    if (j == 0) {
                        s = String.format("%d", wLeft);
                    }
                    Utils.printf("%" + s + "s", String.valueOf(m.getELMT(i, j)));
                }
            }
            Utils.println("");
        }
    }

    /* Display Matrix Augmented */ 
    public static void displayMatrixAugmented(Matrix m, int col) {
        /* KAMUS LOKAL */
        int i, j;
        int weight;
        String s;

        /* ALGORITMA */
        weight = 0;
        // Search the biggest weight first
        for (i = 0; i < m.getRow(); i++) {
            for (j = 0; j < m.getCol(); j++) {
                if (Utils.getLengthELMT(m.getELMT(i, j)) > weight) {
                    weight = Utils.getLengthELMT(m.getELMT(i, j));
                }
            }
        }
        weight++;

        // Search the biggest weight (first column only)
        int wLeft = 0;
        for (i = 0; i < m.getRow(); i++) {
            if (Utils.getLengthELMT(m.getELMT(i, 0)) > wLeft) {
                wLeft = Utils.getLengthELMT(m.getELMT(i, 0));
            }
        }

        for (i = 0; i < m.getRow(); i++) {
            for (j = 0; j < m.getCol(); j++) {
                s = String.format("%d", weight);
                if (m.getELMT(i, j) == (int) m.getELMT(i, j)) { // int
                    if (j == 0) {
                        s = String.format("%d", wLeft);
                    }
                    Utils.printf("%" + s + "d", (int) m.getELMT(i, j));
                } else { // double
                    if (j == 0) {
                        s = String.format("%d", wLeft);
                    }
                    Utils.printf("%" + s + "s", String.valueOf(m.getELMT(i, j)));
                }
                // Augmented
                if (j == col) {
                    String sep = "|";
                    Utils.printf("%" + s + "s", sep);
                }
            }
            Utils.println("");
        }
    }

    /* -------------------------- File Output ------------------------- */

    
}
