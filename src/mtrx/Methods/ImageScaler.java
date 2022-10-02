package mtrx.Methods;

import mtrx.Matrix.*;

public class ImageScaler {

    final static double oneThird = ((double) 1) / ((double) 3);

    public static Matrix DoubleScale(Matrix m){
        // PREKONDISI : jumlah elemen matriks >= 16
        // KAMUS LOKAL

        Matrix mRes, bicubicInterpolationRes;
        int currentRow, currentCol, i, j, x, y, xLowerMid, yLowerMid, deltaX, deltaY, countInterpolateX, countInterpolateY, currentRowRecord, currentColRecord;
        double interpolateIdxX, interpolateIdxY, upperLimit, lowerLimit;
        boolean midX, midY;

        // ALGORITMA

        if(m.getRow() < 4 || m.getCol() < 4) {
            return m;
        }

        mRes = new Matrix(m.getRow() * 2, m.getCol() * 2);
        // hanya elemen pada lowerMid dan lowerMid+1 yang akan disisipi dua baris atau dua kolom hasil interpolasi secara berurutan
        // dengan x dan y bukan bilangan bulat
        xLowerMid = m.getRow() % 2 == 0? (m.getRow() - 1) : (m.getRow() - 2);
        yLowerMid = m.getCol() % 2 == 0? (m.getCol() - 1) : (m.getCol() - 2);

        currentRow = 0;

        for(i = 0; i < m.getRow() - 3; i++) {

            currentRowRecord = currentRow;
        
            // decide how many row to insert
            // midX tells that if this iteration will insert 2 rows of interpolation results

            if (i == 0) {
                deltaX = 4;

                midX = ((currentRow + deltaX - 1 == xLowerMid)? true : false);
                deltaX += ((currentRow + deltaX - 1 == xLowerMid)? 2 : 1);
            
            }

            else {
                deltaX = ((currentRow == xLowerMid)? 3 : 2);
                midX = ((currentRow == xLowerMid)? true : false);
            }

            if (i + 3 == m.getLastRow())
            {
                deltaX += 2;
            }

            currentCol = 0;

            for (j = 0; j < m.getCol() - 3; j++)
            {
                currentColRecord = currentCol;

                // midY tells that if this iteration will insert 2 columns of interpolation results
                if (j == 0) {
                    deltaY = 4;
    
                    midY = ((currentCol + deltaY - 1 == yLowerMid)? true : false);
                    deltaY += ((currentCol + deltaY - 1 == yLowerMid)? 2 : 1);
                
                }
    
                else {
                    deltaY = ((currentCol == yLowerMid)? 3 : 2);
                    midY = ((currentCol == yLowerMid)? true : false);
                }


                if (j + 3 == m.getLastCol())
                {
                    deltaY += 2;
                }

                bicubicInterpolationRes = BicubicInterpolation.Bicubic(m.getSubMatrix(i, i+3, j, j+3));

                currentRow = currentRowRecord;
                countInterpolateX = 0;

                if (i == 0)
                {
                    interpolateIdxX = -1;
                }

                else 
                {
                    interpolateIdxX = (currentRow == xLowerMid)? (oneThird) : 0.5;
                }

                for (x = 0; x < deltaX; x++)
                {
                    currentCol = currentColRecord;
                    countInterpolateY = 0;

                    if (j == 0){
                        interpolateIdxY = -1;
                    }

                    else {
                        interpolateIdxY = (currentCol == yLowerMid)? (oneThird) : 0.5;
                    }
  
                    for (y = 0; y < deltaY; y++)
                    {

                        // x is horizontal in cartecius (becomes column) and y becomes row
                        mRes.setELMT(currentRow, currentCol, 
                                        BicubicInterpolation.getBicubicInterpolation(bicubicInterpolationRes, 
                                            interpolateIdxY, interpolateIdxX));

                        countInterpolateY += 1;
                        currentCol++;

                        if (midY) {
                            // Jika iterasi saat ini mencakup lowerMid
                            if (j == 0)
                            {
                                upperLimit = 5;
                            }

                            else {
                                upperLimit = 2;
                            }

                            lowerLimit = upperLimit - 2;

                            if (countInterpolateY == upperLimit)
                            {
                                interpolateIdxY = 1;
                            }

                            else if (countInterpolateY > upperLimit)
                            {
                                interpolateIdxY += 0.5;
                            }

                            // countInterpolateY < upperLimit
                            else {
                                interpolateIdxY += ((countInterpolateY < lowerLimit)? 0.5 : oneThird);

                            }
                            
                        }

                        else {
                            interpolateIdxY += 0.5;
                        }
                    
                    
                    }

                    countInterpolateX += 1;
                    currentRow++;
            
                    if (midX) {
                        // Jika iterasi saat ini mencakup lowerMid
                        if (i == 0)
                        {
                            upperLimit = 5;
                        }

                        else {
                            upperLimit = 2;
                        }

                        lowerLimit = upperLimit - 2;

                        if (countInterpolateX == upperLimit)
                        {
                            interpolateIdxX = 1;
                        }

                        else if (countInterpolateX > upperLimit)
                        {
                            interpolateIdxX += 0.5;
                        }

                        // countInterpolateX < upperLimit
                        else {
                            interpolateIdxX += ((countInterpolateX < lowerLimit)? 0.5 : oneThird);

                        }             
                    }

                    else {
                        interpolateIdxX += 0.5;
                    }
                }            
            }
            
        }

        return mRes;
    }
}
