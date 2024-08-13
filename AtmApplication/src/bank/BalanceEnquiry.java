package bank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener {

    JButton back; 
    String pinno;
    BalanceEnquiry(String pinno) {

        this.pinno = pinno;
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("bank/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        back = new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

         Conn c = new Conn();
         int balance = 0;
            try {
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinno + "'");
               
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }
            }catch (Exception e) {
                System.out.println(e);
            }

            JLabel text = new JLabel("Your current account balance is Rs." +balance);
            text.setForeground(Color.WHITE);
            text.setBounds(170,300,400,30);
            image.add(text);

        

        
        setSize(900, 900);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);



    }

    public void actionPerformed (ActionEvent ae) {
        setVisible(false);
        new Transactions(pinno).setVisible(true);

    }
    public static void main(String[] args) {
        new BalanceEnquiry(""); 
    }

}
