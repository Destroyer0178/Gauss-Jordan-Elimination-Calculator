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

    public static double[][] replaceRow(double[][] matrix, double[] row, int rowIndex) {
        for (int i = 0; i < matrix[rowIndex].length; i++) {
            matrix[rowIndex][i] = row[i];
        }
        return matrix;
    }

    public static double[][] rowReduce(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            return null;
        }
    }

    public static void main(String[] args) {
        double[][] matrix = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        double[] row = rowOperation(matrix[0], matrix[1], 2, -1);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}