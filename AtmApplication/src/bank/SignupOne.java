package bank;
import java.awt.Color;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignupOne extends JFrame implements ActionListener {
    long random;
    JTextField nameTextField, fnameTextField, emailTextField, addrTextField;
    JButton next;
    JRadioButton male, female;
    JDateChooser date; // Correct reference to JDateChooser
    JComboBox<String> dropdown;

    SignupOne() {
        setLayout(null);

        Random ran = new Random();
        random = Math.abs(ran.nextLong() % 1000L + 1000L); // Use class field 'random'
        JLabel formno = new JLabel("APPLICATION FORM NO " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 38));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel personaldetails = new JLabel("Page 1: Personal Details");
        personaldetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personaldetails.setBounds(290, 80, 400, 30);
        add(personaldetails);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        nameLabel.setBounds(100, 140, 100, 40);
        add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);

        JLabel fnameLabel = new JLabel("Father's Name");
        fnameLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        fnameLabel.setBounds(100, 190, 200, 40);
        add(fnameLabel);

        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        fnameTextField.setBounds(300, 190, 400, 30);
        add(fnameTextField);

        JLabel dobLabel = new JLabel("Date Of Birth");
        dobLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        dobLabel.setBounds(100, 240, 200, 40);
        add(dobLabel);

        date = new JDateChooser();
        date.setBounds(300, 240, 400, 30);
        add(date);

        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        genderLabel.setBounds(100, 290, 200, 40);
        add(genderLabel);

        male = new JRadioButton("Male");
        male.setBounds(300, 290, 200, 40);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(450, 290, 200, 40);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        emailLabel.setBounds(100, 340, 200, 40);
        add(emailLabel);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        emailTextField.setBounds(300, 340, 400, 30);
        add(emailTextField);

        JLabel addrLabel = new JLabel("Address");
        addrLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        addrLabel.setBounds(100, 390, 200, 40);
        add(addrLabel);

        addrTextField = new JTextField();
        addrTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        addrTextField.setBounds(300, 390, 400, 30);
        add(addrTextField);

        JLabel typeofaccount = new JLabel("Type of account");
        typeofaccount.setFont(new Font("Raleway", Font.BOLD, 20));
        typeofaccount.setBounds(100, 440, 200, 40);
        add(typeofaccount);

        String[] accountTypes = {"Current", "Savings", "Joint", "Student"};
        dropdown = new JComboBox<>(accountTypes);
        dropdown.setBounds(300, 440, 400, 40);
        add(dropdown);

        next = new JButton("Next");
        next.setForeground(Color.BLACK); 
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.WHITE);
        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the program exits on window close
    }

    
    public void actionPerformed(ActionEvent ae) {
        String formno = "" + random;
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();
        String dob = (date.getDate() != null) ? ((JTextField) date.getDateEditor().getUiComponent()).getText() : ""; // Check if date is null
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";
        }
        String email = emailTextField.getText();
        String address = addrTextField.getText();
        String selectedItem = (String) dropdown.getSelectedItem();

        try {
            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is required");
            } else {
                Conn c = new Conn();
                String query = "INSERT INTO signup (formno, name, father_name, dob, gender, email, address, selected_item) VALUES ('"
                                + formno + "', '"
                                + name + "', '"
                                + fname + "', '"
                                + dob + "', '"
                                + gender + "', '"
                                + email + "', '"
                                + address + "', '"
                                + selectedItem + "')";
                System.out.println("Executing query: " + query); // Print query for debugging
                 c.s.executeUpdate(query);
                 setVisible(false);
                 new SignupTwo(formno).setVisible(true);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SignupOne();
    }
}
