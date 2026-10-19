package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextAreaGrid extends JFrame {

    private JTextField numberField;
    private JPanel gridPanel;

    public TextAreaGrid() {
        setTitle("TextArea Grid Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); // Center window

        // Top panel for user input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Enter number of text areas:"));
        numberField = new JTextField(5);
        inputPanel.add(numberField);

        JButton generateButton = new JButton("Generate Grid");
        inputPanel.add(generateButton);

        // Panel to hold the grid of text areas
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(1, 1)); // Default empty

        // Add panels to frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(gridPanel), BorderLayout.CENTER);

        // Button action
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateGrid();
            }
        });
    }

    private void generateGrid() {
        try {
            int num = Integer.parseInt(numberField.getText().trim());

            if (num <= 0) {
                JOptionPane.showMessageDialog(this, "Please enter a positive integer.");
                return;
            }

            // Calculate square size (rows and columns)
            int gridSize = (int) Math.ceil(Math.sqrt(num));

            gridPanel.removeAll(); // Clear old components
            gridPanel.setLayout(new GridLayout(gridSize, gridSize, 5, 5));

            for (int i = 0; i < num; i++) {
                JTextArea textArea = new JTextArea();
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                gridPanel.add(new JScrollPane(textArea));
            }

            // Fill remaining cells if num is not a perfect square
            int totalCells = gridSize * gridSize;
            for (int i = num; i < totalCells; i++) {
                gridPanel.add(new JPanel()); // Empty placeholder
            }

            gridPanel.revalidate();
            gridPanel.repaint();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter an integer.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TextAreaGrid().setVisible(true);
        });
    }
}