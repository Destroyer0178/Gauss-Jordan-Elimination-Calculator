package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{

    private JTextField numRowsInput;
    private JTextField numColsInput;
    private JPanel gridPanel;
    private JPanel outputPanel;
    
    public GUI() {
        setTitle("Gaussian Elimination Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Enter your desired matrix size"));


        numRowsInput = new JTextField("3");
        inputPanel.add(numRowsInput);
        inputPanel.add(new JLabel("X"));
        numColsInput = new JTextField("3");
        inputPanel.add(numColsInput);

        JButton createGridButton = new JButton("Row Reduce");
        inputPanel.add(createGridButton);

        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(1, 1));

        outputPanel = new JPanel();
        outputPanel.setLayout(new FlowLayout());

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(gridPanel), BorderLayout.CENTER);

        createGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateGrid();
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

            gridPanel.removeAll(); 
            gridPanel.setLayout(new GridLayout(rows, cols, 5, 5));

            for (int i = 0; i < rows * cols; i++) {
                JTextArea textField = new JTextArea();
                textField.setLineWrap(true);
                textField.setWrapStyleWord(true);
                gridPanel.add(new JScrollPane(textField));
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
