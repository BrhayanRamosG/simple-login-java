package org.brg.classes;

import org.brg.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operation {

    public boolean isFieldValidated(User user, boolean signUp) {
        if (signUp) {
            return !user.getUserName().equals("") && !user.getEmail().equals("") && !user.getPassword().equals("");
        }
        return !user.getUserName().equals("") && !user.getPassword().equals("");
    }

    public boolean isEmailValidated(User user) {
        String regex = "[a-z0-9]+@[a-z]+\\.[a-z]{2,3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher mat = pattern.matcher(user.getEmail());
        return mat.find();
    }
}
