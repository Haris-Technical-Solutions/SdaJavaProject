package org.example.bookstore.views;

import org.example.bookstore.models.User;

import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;

public class Login extends View {

    public Login() {
       
    }

    public static Dictionary menu(){
        
        println("\n============= Login =============\n");
        println("Enter your email: ");
        String email = scanner.nextLine();
        println("Enter your password: ");
        String password = scanner.nextLine();

        Dictionary login = new Dictionary();
        login.put("email", email);
        login.put("password", password);
        return login;

        
    }


   
}
