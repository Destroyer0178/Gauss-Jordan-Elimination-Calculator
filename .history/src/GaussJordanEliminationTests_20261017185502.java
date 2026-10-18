package src;

public class GaussianEliminationTests {

    private static final double TOLERANCE = 1.0e-9;

    public static void main(String[] args) {
        int failures = 0;
        failures += runTest("finds a lower-row pivot before a later column",
            GaussianEliminationTests::findsLowerRowPivotBeforeLaterColumn);
        failures += runTest("swaps a lower-row pivot into the active row",
            GaussianEliminationTests::swapsLowerRowPivotIntoActiveRow);
        failures += runTest("reports no pivot when the remaining columns are zero",
            GaussianEliminationTests::reportsNoPivotWhenRemainingColumnsAreZero);

        if (failures > 0) {
            throw new AssertionError(failures + " pivot test(s) failed.");
        }
        System.out.println("All Gauss-Jordan pivot tests passed.");
    }

    private static int runTest(String name, Runnable test) {
        try {
            test.run();
            System.out.println("PASS: " + name);
            return 0;
        } catch (AssertionError | RuntimeException ex) {
            System.err.println("FAIL: " + name + " - " + ex.getMessage());
            return 1;
        }
    }

    private static void findsLowerRowPivotBeforeLaterColumn() {
        double[][] matrix = {
            {0, 0, 2},
            {0, 3, 4},
            {0, 0, 0}
        };

        int pivotCol = GaussJordanElimination.findPivotCol(matrix, 0, 0);

        assertEquals(1, pivotCol, "Should select the first column with a pivot below row 0");
        if (Math.abs(matrix[0][pivotCol]) <= TOLERANCE) {
            throw new AssertionError("The lower-row pivot was not moved into row 0");
        }
    }

    private static void swapsLowerRowPivotIntoActiveRow() {
        double[][] matrix = {
            {1, 0, 2},
            {0, 0, 3},
            {0, 2, 4}
        };

        int pivotCol = GaussJordanElimination.findPivotCol(matrix, 1, 1);

        assertEquals(1, pivotCol, "Should retain the active column when it has a lower-row pivot");
        if (Math.abs(matrix[1][pivotCol]) <= TOLERANCE) {
            throw new AssertionError("The lower-row pivot was not moved into row 1");
        }
    }

    private static void reportsNoPivotWhenRemainingColumnsAreZero() {
        double[][] matrix = {
            {1, 0, 0},
            {0, 0, 0}
        };

        int pivotCol = GaussJordanElimination.findPivotCol(matrix, 1, 1);

        assertEquals(-1, pivotCol, "Should report no pivot in the remaining columns");
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + "; expected " + expected + ", got " + actual);
        }
    }
}
