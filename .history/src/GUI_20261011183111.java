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
        JLabel l2 = new JLabel("X");
        l2.setBounds(370, 40, 20, 30);
        f.add(l2);

        JTextField tf1 = new JTextField("3");
        tf1.setBounds(325, 40, 30, 30);
        f.add(tf1);
        JTextField tf2 = new JTextField("3");
        tf2.setBounds(400, 40, 30, 30);
        f.add(tf2);

        f.setLayout(null); // Use no layout manager
        f.setVisible(true); // Make the frame visible
    }
}
