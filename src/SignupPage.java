import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupPage extends JFrame implements ActionListener {

    JFrame signupPage = new JFrame();
    JLabel signupLabel= new JLabel("SIGNUP");
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel confirmPasswordLabel= new JLabel("CONFIRM PASSWORD");
    protected static JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField confirmPasswordField = new JPasswordField();
    JButton signupButton = new JButton("SIGNUP");
    JButton backButton = new JButton("BACK");
    JCheckBox showPassword = new JCheckBox("Show Password");
    protected static String userText; //This is declared here because we need it in GameData class

    SignupPage() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToFrame();
        addActionEvent();
        signupPage.setTitle("Signup Form");
        signupPage.setVisible(true);
        signupPage.setBounds(10, 10, 450, 450);
        signupPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signupPage.setResizable(false);
        signupPage.setLocationRelativeTo(null);

    }

    public void setLayoutManager() {
        signupPage.setLayout(null);
        ImageIcon logo = new ImageIcon("resources/pieces/black king.png");
        signupPage.setIconImage(logo.getImage());
    }

    public void setLocationAndSize() {
        signupLabel.setBounds(190,40,100,30);
        userLabel.setBounds(60, 110, 250, 30);
        passwordLabel.setBounds(60, 180, 250, 30);
        confirmPasswordLabel.setBounds(30,250,200,30);
        userTextField.setBounds(200, 110, 200, 30);
        passwordField.setBounds(200, 180, 200, 30);
        confirmPasswordField.setBounds(200,260,200,30);
        showPassword.setBounds(200, 290, 150, 30);
        signupButton.setBounds(90, 340, 100, 30);
        backButton.setBounds(240, 340, 100, 30);


    }

    public void addComponentsToFrame() {
        signupPage.add(signupLabel);
        signupPage.add(userLabel);
        signupPage.add(passwordLabel);
        signupPage.add(confirmPasswordLabel);
        signupPage.add(userTextField);
        signupPage.add(passwordField);
        signupPage.add(confirmPasswordField);
        signupPage.add(showPassword);
        signupPage.add(signupButton);
        signupPage.add(backButton);
    }

    public void addActionEvent() {
        signupButton.addActionListener(this);
        backButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String pwdText;
            String confirmPwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            confirmPwdText = confirmPasswordField.getText();
            if (!User.checkUsernameValidity(userText)) {
                if (confirmPwdText.equals(pwdText)) {
                    User.signUpData(userText, pwdText);
                    JOptionPane.showMessageDialog(this, "Signup Successful");
                    User.CreateNewGameFile();
                    signupPage.dispose();
                    new GameInfo();
                } else {
                    JOptionPane.showMessageDialog(this, "Passwords don't match. Try again.");
                }
            } else JOptionPane.showMessageDialog(this, "This username is already taken. " +
                    "Please choose a different one or log into existing account.");
        }
        if (e.getSource() == backButton) {
            signupPage.dispose();
            new LaunchPage();
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
                confirmPasswordField.setEchoChar((char)0);
            } else {
                passwordField.setEchoChar('•');
                confirmPasswordField.setEchoChar('•');
            }
        }
    }
}
