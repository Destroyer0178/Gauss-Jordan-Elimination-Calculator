package src;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.util.ArrayList;

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

        assertEquals("Should choose the earlier column with a pivot below the active row",
            1, pivotCol);
        assertTrue("The pivot should be moved into the active row",
            Math.abs(matrix[0][pivotCol]) > TOLERANCE);
    }

    @Test
    public void swapsLowerRowPivotIntoActiveRow() {
        double[][] matrix = {
            {1, 0, 2},
            {0, 0, 3},
            {0, 2, 4}
        };

        int pivotCol = GaussJordanElimination.findPivotCol(matrix, 1, 1);

        assertEquals("Should retain the active column when it has a lower-row pivot",
            1, pivotCol);
        assertTrue("The pivot should be moved into row 1",
            Math.abs(matrix[1][pivotCol]) > TOLERANCE);
    }

    @Test
    public void returnsMinusOneWhenNoRemainingColumnHasPivot() {
        double[][] matrix = {
            {1, 0, 0},
            {0, 0, 0}
        };

        int pivotCol = GaussJordanElimination.findPivotCol(matrix, 1, 1);

        assertEquals("Should report no pivot in the remaining columns", -1, pivotCol);
    }
}
