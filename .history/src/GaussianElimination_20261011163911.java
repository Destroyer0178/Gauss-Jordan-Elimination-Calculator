package src;

import java.util.Arrays;

public class GaussianElimination {

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

    public static double[][] rowReduce(double[][] matrix) {
        int shiftingFactor = 0;
        for (int i = 0; i < matrix.length; i++) {
            double pivotVal = matrix[i][i + shiftingFactor];
            if (pivotVal != 0) {
                for (int j = 0; j < matrix.length; j++) {
                    if (j != i) {
                        if (matrix[j][j + shiftingFactor] > 0) {
                            rowOperation(matrix[i], matrix[j], 1 / pivotVal, -1);
                        } else {
                            rowOperation(matrix[i], matrix[j], 1 / pivotVal, 1);
                        }
                    }
                }
            } else {
                continue;
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        double[][] matrix = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        matrix = rowReduce(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}