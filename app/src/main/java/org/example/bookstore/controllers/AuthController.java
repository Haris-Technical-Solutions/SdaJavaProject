package org.example.bookstore.controllers;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Subject;
import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.views.Index;

public class AuthController {

    public AuthController() {

    }

    public static boolean register(Dictionary register){
        String email = (String) register.get("email");
        User user = new User().where("email", "=", email).first();
        if(user != null){
            return false;
        }
        user = new User().create(register);
        return true;
    }


   
}
