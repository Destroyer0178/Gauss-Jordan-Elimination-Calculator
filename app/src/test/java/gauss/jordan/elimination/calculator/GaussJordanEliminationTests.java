package gauss.jordan.elimination.calculator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class GaussJordanEliminationTests {

    private static final double TOLERANCE = 1.0e-9;

    @Test
    public void findsPivotInLowerRowBeforeLaterColumn() {
        double[][] matrix = {
            {0, 0, 2},
            {0, 3, 4},
            {0, 0, 0}
        };

        ArrayList<Integer> pivotCols = new ArrayList<>();
        int pivotCol = GaussJordanElimination.findPivotCol(matrix, 0, 0, pivotCols);

        assertEquals(1, pivotCol,
            "Should choose the earlier column with a pivot below the active row");
        assertTrue(Math.abs(matrix[0][pivotCol]) > TOLERANCE,
            "The pivot should be moved into the active row");
    }

    @Test
    public void swapsLowerRowPivotIntoActiveRow() {
        double[][] matrix = {
            {1, 0, 2},
            {0, 0, 3},
            {0, 2, 4}
        };

        ArrayList<Integer> pivotCols = new ArrayList<>();
        pivotCols.add(0);
        int pivotCol = GaussJordanElimination.findPivotCol(matrix, 1, 1, pivotCols);

        assertEquals(1, pivotCol,
            "Should retain the active column when it has a lower-row pivot");
        assertTrue(Math.abs(matrix[1][pivotCol]) > TOLERANCE,
            "The pivot should be moved into row 1");
    }

    @Test
    public void returnsMinusOneWhenNoRemainingColumnHasPivot() {
        double[][] matrix = {
            {1, 0, 0},
            {0, 0, 0}
        };

        ArrayList<Integer> pivotCols = new ArrayList<>();
        int pivotCol = GaussJordanElimination.findPivotCol(matrix, 1, 1, pivotCols);

        assertEquals(-1, pivotCol, "Should report no pivot in the remaining columns");
    }

    @Test 
    public void rowReduceCorrectlySolvesMatrixWithDependentRows() {
        double[][] matrix = {
            {1, 2, 3},
            {2, 4, 6},
            {3, 6, 9}
        };

        matrix = GaussJordanElimination.rowReduce(matrix);
        double[][] solutionMatrix = {
            {1, 2, 3},
            {0, 0, 0},
            {0, 0, 0}
        };

        assertMatrixEquals(solutionMatrix, matrix);
    }

    @Test 
    public void rowReduceCorrectlySolvesMatrixWithMoreColumnsThanPivots() {
        double[][] matrix = {
            {1, 2, 3, 6},
            {5, 4, 2, 8},
            {3, 3, 9, 7}
        };

        matrix = GaussJordanElimination.rowReduce(matrix);
        double[][] solutionMatrix = {
            {1, 0, 0, -4.0 / 3.0},
            {0, 1, 0, 11.0 / 3.0},
            {0, 0, 1, 0}
        };

        assertMatrixEquals(solutionMatrix, matrix);
    }

    private static void assertMatrixEquals(double[][] expected, double[][] actual) {
        assertEquals(expected.length, actual.length, "The matrix row counts differ");
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], TOLERANCE,
                "Matrix row " + row + " differs");
        }
    }
}
