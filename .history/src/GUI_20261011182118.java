package src;

import javax.swing.*;

public class GUI {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Gaussian Elimination Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a JButton
        JButton button = new JButton("Row Reduce");
        button.setBounds(400, 500, 100, 50); // Set position and size

        // Add button to the frame
        frame.add(button);
        frame.setLayout(null); // Use no layout manager
        frame.setVisible(true); // Make the frame visible
    }
}
