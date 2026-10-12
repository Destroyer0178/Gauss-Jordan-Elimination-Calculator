package src;

import javax.swing.*;

public class GUI {
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Gaussian Elimination Calculator");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800, 600);

        // Create a JButton
        JButton button = new JButton("Row Reduce");
        button.setBounds(375, 500, 150, 50);
        f.add(button);

        JLabel l1 = new JLabel("Enter your desired matrix size");
        l1.setBounds(300, 0, 200, 50);
        f.add(l1);

        JTextField tf1 = new JTextField();
        tf1.setBounds(300, 40, 50, 50);
        f.add(tf1);

        f.setLayout(null); // Use no layout manager
        f.setVisible(true); // Make the frame visible
    }
}
