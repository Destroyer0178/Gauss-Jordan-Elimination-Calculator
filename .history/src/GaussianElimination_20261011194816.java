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

    public static double[][] simplifyMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            double pivot = matrix[i][i];
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] /= pivot;
                }
            }
        }
        return matrix;
    }

    public static double[][] rowReduce(double[][] matrix) {
        int shiftingFactor = 0;
        for (int i = 0; i < matrix.length; i++) {
            double pivotVal = matrix[i][i + shiftingFactor];
            if (pivotVal != 0) {
                for (int j = 0; j < matrix.length; j++) {
                    double rowVal = matrix[j][i + shiftingFactor];
                    if (j != i) {
                        if (rowVal > 0) {
                            rowOperation(matrix[i], matrix[j], rowVal / pivotVal, -1);
                        } else if (rowVal < 0) {
                            rowOperation(matrix[i], matrix[j], -1 * rowVal / pivotVal, 1);
                        } 
                    }
                }
            } else {
                //shiftingFactor++;
            }
        }
        matrix = simplifyMatrix(matrix);
        return matrix;
    }

    public static String stringArray(double[][] arr) {
        String stringArray = "";
        for (int i = 0; i < arr.length; i++) {
            stringArray += "["
            for (int j = 0; j < arr[i].length - 1; j++) {
                stringArray += String.format("%.3f, ", arr[i][j]);
            }
            stringArray += String.format("%.3f]", arr[i][arr[i].length - 1]);
        }
        return stringArray;
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

    public static double randomNum() {
        return Math.round(Math.random() * 10000);
    }
    //public static void main(String[] args) {
        //double[][] matrix = {{randomNum(), randomNum(), randomNum()}, {randomNum(), randomNum(), randomNum()}, 
        // {randomNum(), randomNum(), randomNum()}};
        //matrix = rowReduce(matrix);
        //printArray(matrix);
    //}
}