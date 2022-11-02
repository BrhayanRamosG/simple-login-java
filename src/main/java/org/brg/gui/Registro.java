package org.brg.gui;

import org.brg.classes.Operation;
import org.brg.data.UserDAO;
import org.brg.model.User;

import javax.swing.*;
import java.awt.*;

public class Registro {
    private JPanel container;
    private JButton registroButton;
    private JTextField userNameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    public Registro() {
        registroButton.addActionListener(e -> {
            Operation operation = new Operation();
            UserDAO userDAO = new UserDAO();
            User user = new User(userNameField.getText(), emailField.getText(), new String(passwordField.getPassword()).trim());

            if (!operation.isFieldValidated(user, true)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar todos los datos", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!operation.isEmailValidated(user)) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un correo electrónico válido", "Correo electrónico inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (userDAO.insert(user) == 0) {
                JOptionPane.showMessageDialog(null, "Revise su conexión a internet", "Error al registrar", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Registro de usuario exitoso!", "Usuario " + user.getUserName() + " registrado", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro");
        frame.setContentPane(new Registro().container);
        frame.setResizable(false);
        frame.setMinimumSize(new Dimension(450, 450));
        frame.setLocationRelativeTo(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
