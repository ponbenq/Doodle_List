import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class LoginFrame  implements ActionListener {
    JTextField usernameField;
    JPasswordField passwordField;
    JButton submitButton;
    JFrame loginFrame;
    JLabel loginLabel;
    public static String folderName;
    public LoginFrame (){
        // Set the Nimbus look and feel
        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.put("nimbusBase", new Color(43, 52, 89));
//            UIManager.put("control", new Color(0, 0, 0));
//            UIManager.put("text", new Color(255, 255, 255));
//            UIManager.put("TextField.background", new Color(204, 204, 196));
//            UIManager.put("PasswordField.background", new Color(0, 0, 0));
//            UIManager.put("List.background", new Color(0, 0 ,0));
//            UIManager.put("Button.background", new Color(0, 204, 196));
//            UIManager.put("Button.foreground", new Color(255, 255, 255));

        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        //frame init
        loginFrame = new JFrame();
        loginFrame.setSize(400, 320);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //placing the components
        loginLabel = new JLabel("Login");
        loginLabel.setBounds(180, 60, 100, 20);
        loginFrame.add(loginLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 100, 100, 25);
        loginFrame.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 140, 100, 25);
        loginFrame.add(passwordField);

        submitButton = new JButton("Login");
        submitButton.setBounds(160, 180, 80, 20);
        submitButton.addActionListener(this);
        loginFrame.add(submitButton);

        //frame init(cont.)
        loginFrame.setLayout(null);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == submitButton) {
                //file manipulate
                File file = new File("/Users/b4enq/IdeaProjects/ToDoList/src/authentication-details.txt");
                BufferedReader reader = new BufferedReader(new FileReader("/Users/b4enq/IdeaProjects/ToDoList/src/authentication-details.txt"));
                String line;

                String user = "";
                String pass = "";
                while((line = reader.readLine()) != null){
                    if(usernameField.getText().equals(line)){
                        user = line;
                    }
                    if(passwordField.getText().equals(line)){
                        pass = line;
                    }
                }
                reader.close();

//
//                Scanner scanner = new Scanner(file);
//                //get string from text file
//                String username = scanner.nextLine();
//                String password = scanner.nextLine();

                //string concatenated when found character = ':' for username and password
//                String user = "";
//                String pass = "";
//                for(int i = 0; i <= username.length() - 1; i++){
//                    user += username.charAt(i);
//                }
//                for(int i = 0; i <= password.length() - 1; i++){
//                    pass += password.charAt(i);
//                }
                //display username and password for checking
//                System.out.println(user + " / " + pass);
                //get each character from passwordField it return char
                char[] passwordCollected = passwordField.getPassword();
                String passField = "";
                for(char p : passwordCollected){
                    passField += p;
                }
                if(usernameField.getText().equals(user) && passField.equals(pass)) {
                    JOptionPane.showMessageDialog(null, "Login Successfully!");
                    try {
                        folderName = user;
                        AppFrame appFrame = new AppFrame();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    loginFrame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect username or password!");
                }
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
}
