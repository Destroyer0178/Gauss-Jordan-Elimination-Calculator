package src;

import java.util.Arrays;

public class GaussianElimination {

    public static double[] rowOperation(double[] rowOne, double[] rowTwo, double scalarOne, double scalarTwo) {
        for (int i = 0; i < rowOne.length; i++) {
            rowOne[i] = scalarOne * rowOne[i];
            rowTwo[i] = scalarTwo * rowTwo[i];
        }
        for (int i = 0; i < rowOne.length; i++) {
            rowTwo[i] += rowOne[i];
        }
        return rowTwo;
    }

    public static double[][] replaceRow(double[][] matrix, double[] row, double rowIndex) {
        return null;
    }

    public static double[][] rowReduce(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            return null;
        }
    }

    public static void main(String[] args) {
        double[][] matrix = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        System.out.println(Arrays.toString(rowOperation(matrix[0], matrix[1], 2, 1)));
    }
}