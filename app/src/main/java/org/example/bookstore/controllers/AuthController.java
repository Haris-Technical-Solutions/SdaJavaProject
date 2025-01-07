package org.example.bookstore.controllers;

import org.example.bookstore.models.User;

import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.Controller;
import org.example.bookstore.views.Index;

public class AuthController extends Controller {

    public AuthController() {

    }

    public static User login(Dictionary login){
        // String email = (String) login.get("email");
        // String password = (String) login.get("password");
        // User user = new User().where("email", "=", email).where("password", "=", password).first();
        // if(user != null){
        //     return true;
        // }
        // return false;

        User user = new User().where("email", "=", (String) login.get("email")).where("password", "=", (String) login.get("password")).first();
        return user;
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
