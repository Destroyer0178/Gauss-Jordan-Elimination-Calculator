package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{

    private JTextField numRowsInput;
    private JTextField numColsInput;
    private JPanel gridPanel;
    
    public GUI() {
        JFrame f = new JFrame("Gaussian Elimination Calculator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);

        JButton createGridButton = new JButton("Row Reduce");
        createGridButton.setBounds(375, 500, 150, 50);
        f.add(createGridButton);

        JLabel l1 = new JLabel("Enter your desired matrix size");
        l1.setBounds(300, 0, 200, 50);
        f.add(l1);
        JLabel l2 = new JLabel("X");
        l2.setBounds(373, 40, 20, 30);
        f.add(l2);

        JTextField numRowsInput = new JTextField("3");
        numRowsInput.setBounds(325, 40, 30, 30);
        f.add(numRowsInput);
        JTextField numColsInput = new JTextField("3");
        numColsInput.setBounds(400, 40, 30, 30);
        f.add(numColsInput);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Enter number of text areas:"));
        numberField = new JTextField(5);
        inputPanel.add(numberField);
        
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(1, 1));

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(gridPanel), BorderLayout.CENTER);

        createGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateGrid();
            }
        });

        f.setLayout(null); // Use no layout manager
        f.setVisible(true);
    }

    private void generateGrid() {
        try {
            int rows = Integer.parseInt(numRowsInput.getText().trim());
            int cols = Integer.parseInt(numRowsInput.getText().trim());

            if (rows <= 0 || cols <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive integer.");
                return;
            }

            gridPanel.removeAll(); // Clear old components
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
