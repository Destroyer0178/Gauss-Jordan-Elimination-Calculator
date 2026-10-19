package src;

import java.util.HashMap;

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

    public static double[][] sortMatrix(double[][] matrix) {
        int rowBound;
        if (matrix.length > matrix[0].length) {
            rowBound = matrix[0].length;
        } else {
            rowBound = matrix.length;
        }
        HashMap<Double, Integer> pivots = new HashMap<>();
        for (int i = 0; i < rowBound; i++) {
            pivots.put(matrix[i][i], i);
        }
        if (pivots.containsKey(0.0)) {
            for (int i = 0; i < matrix[0].length; i++) {
                if (matrix[i][pivots.get(0.0)] != 0) {
                    rowSwap(matrix, pivots.get(0.0), i);
                }
            }
        }    
        return matrix;
    }

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
                if (matrix[i][j] != 0) {
                    matrix[i][j] /= pivot;
                }
            }
        }
        return matrix;
    }

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

    public static void printArray(double[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static double randomNum(int max) {
        return Math.round(Math.random() * max);
    }
    public static void main(String[] args) {
        double[][] matrix = {{0, randomNum(10), randomNum(10)}, {randomNum(10), randomNum(10), randomNum(10)}, 
          {randomNum(10), randomNum(10), randomNum(10)}, {randomNum(10), randomNum(10), randomNum(10)}};
        printArray(matrix);
        matrix = rowReduce(matrix);
        printArray(matrix);
    }
}