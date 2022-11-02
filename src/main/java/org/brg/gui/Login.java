package org.brg.gui;

import org.brg.classes.Operation;
import org.brg.data.UserDAO;
import org.brg.model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class Login {

    private JButton loginButton;
    private JTextField userNameTextField;
    private JPanel container;
    private JPasswordField passwordField;

    public Login() {
        loginButton.addActionListener(e -> {
            Operation operation = new Operation();
            UserDAO userDAO = new UserDAO();
            User user = new User(userNameTextField.getText(), new String(passwordField.getPassword()).trim());
            if (!operation.isFieldValidated(user, false)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!userDAO.login(user)) {
                JOptionPane.showMessageDialog(null, "Revise sus credenciales de acceso", "Error al ingresar", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Calendar now = Calendar.getInstance();
            JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso, año " + now.get(Calendar.YEAR), "Bienvenido " + user.getUserName(), JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().container);
        frame.setResizable(false);
        frame.setMinimumSize(new Dimension(450, 450));
        frame.setLocationRelativeTo(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
