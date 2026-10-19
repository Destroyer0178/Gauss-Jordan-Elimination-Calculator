## Functionality
Utilizes an implementation of Gauss-Jordan Elimination in Java alongside a GUI created with Java Swing to transform user-entered matrices into reduced row-echelon-form. Works for any user-entered m x n matrix.


## Algorithm
The program primarily utilizes six methods to implement the Gauss-Jordan Elimination algorithm: rowReduce, rowAddition, rowSwap, simplifyMatrix, sortMatrix, and findPivotCol. The rowReduce method serves as the central method which utilizes each other method to fully reduce an inputted matrix. For each row of the matrix, rowReduce calls findPivotCol to search each column for a valid pivot. The findPivotCol method in turn calls the sortMatrix method for each column to determine if rows can be swapped to place a valid pivot in the searched row. If so, the rowSwap method is called to swap the two rows of the matrix. Using this new pivot and the rowAddition method, the other entries in the column are zeroed out through multiplying the pivot row by the column entry that must be zeroed and dividing by the pivot value. The column entry row is then multiplied by -1 and added to the prior result. This result is then applied to the column entry row. After repeating this process for each pivot, the matrix has been reduced. The final step is to utilize the simplifyMatrix method to normalize each matrix row by the value of the pivot, setting each pivot value to one.

Implementing the column searching algorithm for pivots rather than simply searching diagonally across the matrix improves the accuracy of the solver. While earlier versions of this repo attempted this method of pivot searching, searching diagonally leads to missing pivots in several instances, such as when a matrix has more columns than rows with a zeroed column in the first column. Valid pivots can and will be located in any column which makes searching each one imperative to the function of an accurate solver.


## GUI
This program includes a graphical user interface in which the user can input the entries of a matrix of any given size. The GUI itself utilizes Java Swing to create a 2d array of text fields which function as matrix value inputs. The GUI will reject any invalid inputs such as matrices that do not have entries in each row and column or attempting to list the size of either the rows or columns of the matrix as less than one or a non-integer value. It also offers the ability for the user to switch between decimal and fraction output for the row reduced matrix. The user is also able to toggle between viewing the reduction steps or only viewing the final result.


## Usage as a System Solver
This program can also be utilized as a solver of systems of linear equations. Simply enter each equation, such as ax<sub>1</sub>+bx<sub>2</sub>=c, into the solver in the order [a, b, c] for each equation, press row reduce, and the solver will output the solution. Assuming the number of linearly independent equations is greater than or equal to the number of variables, the final column will be equal to the solutions for each variable in the order they were inputted into the solver.
