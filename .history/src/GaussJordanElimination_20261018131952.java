package src;

import java.util.ArrayList;

public class GaussJordanElimination {

    /**
     * Adds two rows together and multiplies each by a given scalar multiple before replacing rowTwo with the result.
     * @param rowOne the first row of the matrix that will remain unchanged.
     * @param rowTwo the second row of the matrix that will be assigned the result of the row operation
     * @param scalarOne the scalar value by which the first row will be multiplied
     * @param scalarTwo the scalar value by which the second row will be multiplied
     * @return the changed row resulting from the row operation
     */
    public static double[] rowAddition(double[] rowOne, double[] rowTwo, double scalarOne, double scalarTwo) {
        double[] tempRowOne = new double[rowOne.length];

        for (int i = 0; i < rowOne.length; i++) {
            tempRowOne[i] = scalarOne * rowOne[i];
            rowTwo[i] = scalarTwo * rowTwo[i];
        }

        for (int i = 0; i < rowOne.length; i++) {
            rowTwo[i] += tempRowOne[i];
        }

        return rowTwo;
    }

    /**
     * Swaps two rows in a matrix with one another.
     * @param matrix the matrix who's rows are to be swapped
     * @param rowOneIndex the index of the first row
     * @param rowTwoIndex the index of the second row
     * @return the matrix after the rows have been swapped
     */
    public static double[][] rowSwap(double[][] matrix, int rowOneIndex, int rowTwoIndex) {
        double[] rowOne = matrix[rowOneIndex];
        double[] rowTwo = matrix[rowTwoIndex];
        double[] tempRowOne = new double[rowOne.length];
        double[] tempRowTwo = new double[rowTwo.length];

        for (int i = 0; i < rowOne.length; i++) {
            tempRowOne[i] = rowOne[i];
            tempRowTwo[i] = rowTwo[i];
        }

        for (int i = 0; i < rowOne.length; i++) {
            rowTwo[i] = tempRowOne[i];
            rowOne[i] = tempRowTwo[i];
        }

        return matrix;
    }

    /**
     * Checks the matrix and swaps the rows of the first row it finds 
     * which has a zero pivot with the first row it locates that has a non-zero pivot.
     * @param matrix the matrix that is to be sorted
     * @return the matrix after the sorting has taken place to eliminate the first found zero pivot
     */
    public static double[][] sortMatrix(double[][] matrix, ArrayList<Integer> pivotCols) {
        int rowBound;
        if (matrix.length > matrix[0].length) {
            rowBound = matrix[0].length;
        } else {
            rowBound = matrix.length;
        }

        int emptyCount = 0;
        for (int i = 0; i < pivotCols.size(); i++) {
            if (pivotCols.get(i) == -1) {
                emptyCount++;
            }
        }

        int searchRow = pivotCols.size() - emptyCount;
        if (shouldZero(matrix[searchRow][searchRow], Math.pow(10.0, -10))) {
            for (int j = searchRow; j < matrix[0].length; j++) {
                if (!shouldZero(matrix[searchRow][j], Math.pow(10.0, -10))) {
                    rowSwap(matrix, j, searchRow);
                    break;
                }
            }
        }

        return matrix;
    }

    /**
     * Normalizes each row with a pivot so that the pivot's value will be one and zeros every entry for which the 
     * absolute value is less than 10E-15.
     * @param matrix the matrix to be normalized
     * @return the normalized matrix
     */
    public static double[][] simplifyMatrix(double[][] matrix, ArrayList<Integer> pivotCols) {
        int rowBound;
        if (matrix.length > matrix[0].length) {
            rowBound = matrix[0].length;
        } else {
            rowBound = matrix.length;
        }

        for (int i = 0; i < pivotCols.size(); i++) {
            if (pivotCols.get(i) == -1) {
                continue;
            } 
            double pivot = matrix[i][pivotCols.get(i)];
            for (int j = 0; j < matrix[i].length; j++) {
                if (!shouldZero(pivot, Math.pow(10.0, -10))) {
                    matrix[i][j] /= pivot;
                }
                if (shouldZero(matrix[i][j], Math.pow(10.0, -10))) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (rowBound == matrix[0].length) {
            for (int i = matrix[0].length; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (shouldZero(matrix[i][j], Math.pow(10.0, -10))) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        return matrix;
    }

    /**
     * Locates the next column in a matrix that contains a valid pivot.
     * @param matrix the matrix to be analyzed
     * @param row the row the pivot must be on
     * @param col the starting column to analyze
     * @return the column in which a valid pivot is located
     */
    public static int findPivotCol(double[][] matrix, int row, int col, ArrayList<Integer> pivotCols) {
        double pivotVal = matrix[row][col];
        if (shouldZero(pivotVal, Math.pow(10.0,-10))) {
            sortMatrix(matrix, pivotCols);
            pivotVal = matrix[row][col];
        } else {
            return col;
        }

        if (shouldZero(pivotVal, Math.pow(10.0,-10))) {
            for (int i = col + 1; i < matrix[0].length; i++) {
                pivotVal = matrix[row][i];
                if (shouldZero(pivotVal, Math.pow(10.0,-10))) {
                    sortMatrix(matrix, pivotCols);
                    pivotVal = matrix[row][i];
                } else {
                    return i;
                }
            }
        } else {
            return col; 
        }

        return -1;
    }

    /**
     * Applies Gauss-Jordan elimination to a given matrix to transform it into reduced row-echelon-form.
     * @param matrix the matrix to be reduced
     * @return the reduced matrix
     */
    public static double[][] rowReduce(double[][] matrix) {
        int rowBound;
        if (matrix.length > matrix[0].length) {
            rowBound = matrix[0].length;
        } else {
            rowBound = matrix.length;
        }

        int currentCol = 0;
        ArrayList<Integer> pivotLocs = new ArrayList<>();
        for (int i = 0; i < rowBound; i++) {
            currentCol = findPivotCol(matrix, i, currentCol, pivotLocs);
            pivotLocs.add(currentCol);
            if (currentCol == -1) {
                break;
            }
            double pivotVal = matrix[i][currentCol];
            for (int j = 0; j < matrix.length; j++) {
                double rowVal = matrix[j][currentCol];
                if (j != i) {
                    if (rowVal > 0) {
                        rowAddition(matrix[i], matrix[j], rowVal / pivotVal, -1);
                    } else if (rowVal < 0) {
                        rowAddition(matrix[i], matrix[j], -1 * rowVal / pivotVal, 1);
                    } 
                }
            } 
        }

        matrix = simplifyMatrix(matrix, pivotLocs);
        return matrix;
    }

    /**
     * Takes a 2d array and transforms it into a string representation.
     * @param arr the 2d array to be transformed
     * @return the transformed array as a string
     */
    public static String formatMatrix(double[][] arr, boolean fraction) {
        String stringArray = "";
        for (int i = 0; i < arr.length; i++) {
            stringArray += "[";
            for (int j = 0; j < arr[i].length - 1; j++) {
                if (fraction) {
                    if (Math.floor(arr[i][j]) != arr[i][j]) {
                        stringArray += String.format("%s, ", convertDecimalToFraction(arr[i][j]));
                    } else {
                        stringArray += String.format("%.1f, ", arr[i][j]);
                    }
                } else {
                    stringArray += String.format("%.3f, ", arr[i][j]);
                }
            }
            if (fraction) {
                if (Math.floor(arr[i][arr[i].length - 1]) != arr[i][arr[i].length - 1]) {
                    stringArray += String.format("%s]\n", convertDecimalToFraction(arr[i][arr[i].length - 1]));
                } else {
                    stringArray += String.format("%.1f]\n", arr[i][arr[i].length - 1]);
                }
            } else {
                stringArray += String.format("%.3f]\n", arr[i][arr[i].length - 1]);
            }
        }
        return stringArray;
    }

    /**
     * Gives the user a random integer number between zero and max - 1.
     * @param max the maximum value the random number can equal minus one
     * @return a random integer between zero and max - 1
     */
    public static double randomNum(int max) {
        return Math.round(Math.random() * max);
    }

    /**
     * Determines if a given floating point number should be zeroed using an inputted tolerance.
     * @param entry the number for which the determination should be made
     * @param tolerance the minimum value for which the absolute value of the floating point number 
     *                  can be without being zeroed
     * @return true if the entry should be zeroed and false otherwise
     */
    public static boolean shouldZero(double entry, double tolerance) {
        return (entry < tolerance && entry >  tolerance * -1);
    }

    /**
     * Uses continued fractions to convert a decimal input to a fraction as a string.
     * obtained code from here: https://stackoverflow.com/questions/31585931/how-to-convert-decimal-to-fractions
     * @param x the decimal value to be converted
     * @return the fraction conversion as a string
     */
    public static String convertDecimalToFraction(double x) {
        if (x < 0){
            return "-" + convertDecimalToFraction(-x);
        }

        double tolerance = 1.0E-6;
        double h1 = 1; double h2 =0 ;
        double k1 = 0; double k2 = 1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = h1; h1 = a * h1 + h2; h2 = aux;
            aux = k1; k1 = a * k1 + k2; k2 = aux;
            b = 1 / (b - a);
        } while (Math.abs(x - h1 / k1) > x * tolerance);

        return (int) h1 + "/" + (int) k1;
    }

    // public static void main(String[] args) {
    //     double[][] matrix = {{0, randomNum(10), randomNum(10)}, {randomNum(10), randomNum(10), randomNum(10)}, 
    //       {randomNum(10), randomNum(10), randomNum(10)}, {randomNum(10), randomNum(10), randomNum(10)}};
    //     System.out.println(stringArray(matrix));
    //     matrix = rowReduce(matrix);
    //     System.out.println(stringArray(matrix));
    //}
}