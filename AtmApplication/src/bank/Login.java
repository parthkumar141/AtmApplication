package bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear; // defining the variables globally so we can use them in method
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        setTitle("ATM"); // title of the frame
        setLayout(null); // we have to set default frame border layout to null in order to give our own values

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank/logo.jpg")); // loading an image
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); // label does not take images, hence we make i3 object image icon which stores the reference of image i2
        JLabel label = new JLabel(i3); // command to create label from image icon
        label.setBounds(70, 10, 100, 100); // giving custom boundaries to icon

        JLabel text = new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Osward", Font.BOLD, 28));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardno = new JLabel("CARD NO");
        cardno.setFont(new Font("Raleway", Font.BOLD, 16));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);
        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 250, 30);
        add(cardTextField);

        JLabel pin = new JLabel("ENTER SECRET PIN");
        pin.setFont(new Font("Raleway", Font.BOLD, 16));
        pin.setBounds(120, 220, 250, 30);
        add(pin);
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 250, 30);
        add(pinTextField);

        login = new JButton("SIGN IN"); // Use the instance variable instead of redeclaring
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.BLACK);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR"); // Use the instance variable instead of redeclaring
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.BLACK);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP"); // Use the instance variable instead of redeclaring
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.black);
        signup.setForeground(Color.BLACK);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.WHITE);

        add(label);
        setSize(800, 480);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true);
        } else if (ae.getSource() == login) {
            Conn c = new Conn();
            String cardno = cardTextField.getText();
            String pinno = pinTextField.getText();
            String query = "select * from login where cardno = '"+cardno+ "' and pin = '"+pinno+"'";
            try{
                ResultSet rs= c.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Transactions(pinno).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect cardnumber or pin");
                }

            }catch (Exception e) {
                System.out.println(e);

            }

        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
