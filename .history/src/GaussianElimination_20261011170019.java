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
            printArray(matrix);
            if (pivotVal != 0) {
                for (int j = 0; j < matrix.length; j++) {
                    double rowVal = matrix[j][i + shiftingFactor];
                    if (j != i) {
                        if (matrix[j][i + shiftingFactor] > 0) {
                            rowOperation(matrix[i], matrix[j], rowVal / pivotVal, -1);
                        } else {
                            rowOperation(matrix[i], matrix[j], rowVal / pivotVal, 1);
                        }
                    }
                }
            } else {
                continue;
            }
        }
        return matrix;
    }

    public static void printArray(double[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        double[][] matrix = {{1, 3, 1}, {2, 3, 2}, {3, 8, 9}};
        for (int i = 0; i < 3; i++) {
            System.out.print(matrix[i][i]);
        }
        //matrix = rowReduce(matrix);
        
    }
}