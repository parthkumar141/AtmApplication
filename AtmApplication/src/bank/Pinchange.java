package bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pinchange extends JFrame implements ActionListener {

    JLabel text, pintext, repintext;
    JTextField newPin, rePin;
    JButton change, back;
    String pinno;

    Pinchange(String pinno) {
        this.pinno = pinno;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank/atm.jpg")); // loading an image
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        text = new JLabel("Change your PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 280, 500, 15);
        image.add(text);

        pintext = new JLabel("New PIN");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System", Font.BOLD, 16));
        pintext.setBounds(165, 320, 180, 25);
        image.add(pintext);

        newPin = new JTextField();
        newPin.setFont(new Font("Raleway", Font.BOLD, 25));
        newPin.setBounds(330, 320, 180, 25);
        image.add(newPin);

        repintext = new JLabel("Re-Enter New PIN");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System", Font.BOLD, 16));
        repintext.setBounds(165, 360, 180, 25);
        image.add(repintext);

        rePin = new JTextField();
        rePin.setFont(new Font("Raleway", Font.BOLD, 25));
        rePin.setBounds(330, 360, 180, 25);
        image.add(rePin);

        change = new JButton("Change");
        change.setBounds(355, 405, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Back");
        back.setBounds(355, 445, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 900);
        setLocation(300, 0);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {

            try {
                String npin = newPin.getText();
                String rpin = rePin.getText();

                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "PIN can't be empty");
                    return;
                }
                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-PIN can't be empty");
                    return;
                }

                Conn c = new Conn();
                String query1 = "update bank set pin = '" + rpin + "' where pin = '" + pinno + "'";
                String query2 = "update login set pin = '" + rpin + "' where pin = '" + pinno + "'";
                String query3 = "update signupthree set pin = '" + rpin + "' where pin = '" + pinno + "'";

                

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);

                

                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new Transactions(rpin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }

        } else {
            setVisible(false);
            new Transactions(pinno).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Pinchange("").setVisible(true);
    }
}
