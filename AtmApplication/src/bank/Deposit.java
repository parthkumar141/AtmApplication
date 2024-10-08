package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit, back;
    String pinno;

    Deposit(String pinno) {

        this.pinno = pinno;

        // setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("Enter deposit amount");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Raleway", Font.BOLD, 18));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        deposit = new JButton("Deposit");
        deposit.setBounds(355, 400, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("back");
        back.setBounds(355, 430, 150, 30);
        back.addActionListener(this);

        image.add(back);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        amount.addActionListener(this);

        image.add(amount);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String number = amount.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
            } else {
                try {
                    Conn conn = new Conn();
                    String transactionType = "Deposit"; // Define the transaction type
                    String query = "insert into bank values ('" + pinno + "', '" + date + "', '" + transactionType + "', '" + number + "')";
                    conn.s.executeUpdate(query);

                    // Logging for debugging
                    System.out.println("Data being inserted into 'type' column: " + transactionType);
                    System.out.println("Length of data: " + transactionType.length());

                    JOptionPane.showMessageDialog(null, "Deposited successfully");
                    setVisible(false);
                    new Transactions(pinno).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinno).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Deposit(" ");
    }
}
