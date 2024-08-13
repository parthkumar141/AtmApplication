package bank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Transactions extends JFrame implements ActionListener {

    JButton deposit, withdrawl, fastcash, ministatement, pinchange, balanceenq, exit;
    String pinno;

    Transactions(String pinno) {
        this.pinno = pinno;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        JLabel text = new JLabel("Please select transaction");
        text.setBounds(235, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Raleway", Font.BOLD, 16));
        image.add(text);

        deposit = new JButton("Deposit");
        deposit.setBounds(355, 350, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawl = new JButton("Withdrawl");
        withdrawl.setBounds(170, 350, 150, 30);
        withdrawl.addActionListener(this);
        image.add(withdrawl);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(170, 400, 150, 30);
        fastcash.addActionListener(this);
        image.add(fastcash);

        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(355, 400, 150, 30);
        ministatement.addActionListener(this);
        image.add(ministatement);

        pinchange = new JButton("Pin Change");
        pinchange.setBounds(170, 450, 150, 30);
        pinchange.addActionListener(this);
        image.add(pinchange);

        balanceenq = new JButton("Balance Enquiry");
        balanceenq.setBounds(355, 450, 150, 30);
        balanceenq.addActionListener(this);
        image.add(balanceenq);

        exit = new JButton("Exit");
        exit.setBounds(260, 490, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        setSize(900, 900);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(pinno).setVisible(true);
        } else if (ae.getSource() == withdrawl) {
            setVisible(false);
            new Withdrawl(pinno).setVisible(true);
        } else if  (ae.getSource() == fastcash) {
            setVisible(false);
            new Fastcash(pinno).setVisible(true);
        } else if (ae.getSource()== pinchange) {
            setVisible(false);
            new Pinchange(pinno).setVisible(true);
        } else if (ae.getSource()== balanceenq) {
            setVisible(false);
            new BalanceEnquiry(pinno).setVisible(true);
        }else if (ae.getSource() == ministatement) {
            
            new Ministatement(pinno).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Transactions(" ");
    }
}
