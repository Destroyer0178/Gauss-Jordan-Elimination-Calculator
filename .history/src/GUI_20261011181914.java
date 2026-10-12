package src;

import javax.swing.*;

public class GUI {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Swing Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        // Create a JButton
        JButton button = new JButton("Click Me!");
        button.setBounds(150, 100, 100, 50); // Set position and size

        // Add button to the frame
        frame.add(button);
        frame.setLayout(null); // Use no layout manager
        frame.setVisible(true); // Make the frame visible
    }
}
