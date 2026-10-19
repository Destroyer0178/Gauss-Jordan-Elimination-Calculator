package src;

import javax.swing.*;

public class GUI {
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Gaussian Elimination Calculator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);

        // Create a JButton
        JButton button = new JButton("Row Reduce");
        button.setBounds(375, 500, 150, 50); // Set position and size
        JLabel l1 = new JLabel("Enter your desired matrix size");
        l1.setBounds(300, 80, 200, 60)

        // Add button to the frame
        frame.add(button);
        frame.setLayout(null); // Use no layout manager
        frame.setVisible(true); // Make the frame visible
    }
}
