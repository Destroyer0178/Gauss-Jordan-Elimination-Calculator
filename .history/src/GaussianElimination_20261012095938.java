package src;

public class GaussianElimination {

    /**
     * Adds to rows togethor and multiplies each by a given scalar multiple before replacing rowTwo with the result.
     * @param rowOne the first row of the matrix that will remain unchanged.
     * @param rowTwo the secoond row of the matrix that will be assigned the result of the row operation
     * @param scalarOne the scalar value by which the first row will be multiplied
     * @param scalarTwo the scalar value by which the second row will be multiplied
     * @return the changed row resulting from the row operation
     */
    public static double[] rowOperation(double[] rowOne, double[] rowTwo, double scalarOne, double scalarTwo) {
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
    public static double[][] sortMatrix(double[][] matrix) {
        int rowBound;
        if (matrix.length > matrix[0].length) {
            rowBound = matrix[0].length;
        } else {
            rowBound = matrix.length;
        }

        for (int i = 0; i < rowBound; i++) {
            if (matrix[i][i] == 0) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[j][i] != 0) {
                        rowSwap(matrix, j, i);
                        break;
                    }
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
    public static double[][] simplifyMatrix(double[][] matrix) {
        int rowBound;
        if (matrix.length > matrix[0].length) {
            rowBound = matrix[0].length;
        } else {
            rowBound = matrix.length;
        }

        for (int i = 0; i < rowBound; i++) {
            double pivot = matrix[i][i];
            for (int j = 0; j < matrix[i].length; j++) {
                if (pivot != 0) {
                    matrix[i][j] /= pivot;
                }
                if (matrix[i][j] < Math.pow(10.0, -15) && matrix[i][j] >  Math.pow(-10.0, -15)) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (rowBound == matrix[0].length) {
            for (int i = matrix[0].length; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] < Math.pow(10.0, -15) && matrix[i][j] >  Math.pow(-10.0, -15)) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        return matrix;
    }

    /**
     * Applies Gauss-Jordan elimination to a given matrix to transform it into row reduced echelon form.
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

        for (int i = 0; i < rowBound; i++) {
            double pivotVal = matrix[i][i];
            if (pivotVal == 0) {
                sortMatrix(matrix);
                pivotVal = matrix[i][i];
            }
            if (pivotVal == 0) {
                continue;
            }
            for (int j = 0; j < matrix.length; j++) {
                double rowVal = matrix[j][i];
                if (j != i) {
                    if (rowVal > 0) {
                        rowOperation(matrix[i], matrix[j], rowVal / pivotVal, -1);
                    } else if (rowVal < 0) {
                        rowOperation(matrix[i], matrix[j], -1 * rowVal / pivotVal, 1);
                    } 
                }
            } 
        }
        
        matrix = simplifyMatrix(matrix);
        return matrix;
    }

    /**
     * Takes a 2d array and transforms it into a string representation.
     * @param arr the 2d array to be transformed
     * @return the transformed array as a string
     */
    public static String stringArray(double[][] arr) {
        String stringArray = "";
        for (int i = 0; i < arr.length; i++) {
            stringArray += "[";
            for (int j = 0; j < arr[i].length - 1; j++) {
                stringArray += String.format("%.3f, ", arr[i][j]);
            }
            stringArray += String.format("%.3f]\n", arr[i][arr[i].length - 1]);
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

    // public static void main(String[] args) {
    //     double[][] matrix = {{0, randomNum(10), randomNum(10)}, {randomNum(10), randomNum(10), randomNum(10)}, 
    //       {randomNum(10), randomNum(10), randomNum(10)}, {randomNum(10), randomNum(10), randomNum(10)}};
    //     System.out.println(stringArray(matrix));
    //     matrix = rowReduce(matrix);
    //     System.out.println(stringArray(matrix));
    //}
}