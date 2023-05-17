import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame implements ActionListener {

    JFrame loginPage = new JFrame();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    protected static JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton backButton = new JButton("BACK");
    JCheckBox showPassword = new JCheckBox("Show Password");
    ImageIcon logo = new ImageIcon("resources/pieces/black king.png");

    protected static String loggedInUser;
    //This is declared here because we need it in User class to read and write data
    //in the logged-in user's file

    LoginPage() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToFrame();
        addActionEvent();
        loginPage.setTitle("Login Page");
        loginPage.setVisible(true);
        loginPage.setBounds(10, 10, 350, 350);
        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPage.setResizable(false);
        loginPage.setLocationRelativeTo(null);


    }

    public void setLayoutManager() {
        loginPage.setLayout(null);
        loginPage.setIconImage(logo.getImage());
    }

    public void setLocationAndSize() {
        userLabel.setBounds(40, 70, 100, 30);
        passwordLabel.setBounds(40, 140, 100, 30);
        userTextField.setBounds(140, 70, 150, 30);
        passwordField.setBounds(140, 140, 150, 30);
        showPassword.setBounds(140, 170, 150, 30);
        loginButton.setBounds(40, 220, 100, 30);
        backButton.setBounds(190, 220, 100, 30);
    }

    public void addComponentsToFrame() {
        loginPage.add(userLabel);
        loginPage.add(passwordLabel);
        loginPage.add(userTextField);
        loginPage.add(passwordField);
        loginPage.add(showPassword);
        loginPage.add(loginButton);
        loginPage.add(backButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        backButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userText = userTextField.getText(); //username entered by user
            String pwdText = passwordField.getText(); //password entered by user
            if (User.LogInData(userText,pwdText)) {
                loggedInUser=userText;
                JOptionPane.showMessageDialog(this, "Login Successful");
                loginPage.dispose();
                new LoggedIn();
            } else JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
        if (e.getSource() == backButton) {
            loginPage.dispose();
            new LaunchPage();
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('•');
            }
        }
    }
}
