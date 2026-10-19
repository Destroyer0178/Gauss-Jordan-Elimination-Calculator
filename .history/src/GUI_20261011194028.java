package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{

    private JTextField numRowsInput;
    private JTextField numColsInput;
    private JPanel gridPanel;
    private JTextField[][] matrixFields;
    
    public GUI() {
        setTitle("Gaussian Elimination Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Enter your desired matrix size: "));

        numRowsInput = new JTextField("3");
        inputPanel.add(numRowsInput);
        inputPanel.add(new JLabel("X"));
        numColsInput = new JTextField("3");
        inputPanel.add(numColsInput);

        JButton createGridButton = new JButton("Generate Grid");
        inputPanel.add(createGridButton);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(1, 1));

        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new FlowLayout());

        JLabel outputLabel = new JLabel("");
        outputPanel.add(outputLabel);

        JButton createOutputButton = new JButton("Row Reduce");
        outputPanel.add(createOutputButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(gridPanel), BorderLayout.CENTER);
        add(outputPanel, BorderLayout.SOUTH);

        createGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateGrid();
            }
        });

        createOutputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (matrixFields == null) {
                    return;
                }
                double[][] matrix = new double[matrixFields.length][matrixFields[0].length];
                for (int row = 0; row < matrixFields.length; row++) {
                    for (int col = 0; col < matrixFields[row].length; col++) {
                        matrix[row][col] = Double.parseDouble(matrixFields[row][col].getText().trim());
                    }
                }
                outputLabel.setText(GaussianElimination.stringArray(GaussianElimination.rowReduce(matrix)));
            }
        });
    }

    private void generateGrid() {
        try {
            int rows = Integer.parseInt(numRowsInput.getText().trim());
            int cols = Integer.parseInt(numColsInput.getText().trim());

            if (rows <= 0 || cols <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive integer.");
                return;
            }

            matrixFields = new JTextField[rows][cols];
            gridPanel.removeAll(); 
            gridPanel.setLayout(new GridLayout(rows, cols, 5, 5));

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    JTextField textField = new JTextField();
                    matrixFields[row][col] = textField;
                    gridPanel.add(new JScrollPane(textField));
                }
            }

            gridPanel.revalidate();
            gridPanel.repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter an integer.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUI().setVisible(true);
        });

    }
}
