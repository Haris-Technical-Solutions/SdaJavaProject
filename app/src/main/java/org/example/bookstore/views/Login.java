package org.example.bookstore.views;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Subject;
import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;

public class Login extends View {

    public Login() {
       
    }

    public static User menu(){
        
        println("\n============= Login =============\n");
        println("Enter your email: ");
        String email = scanner.nextLine();
        println("Enter your password: ");
        String password = scanner.nextLine();
        User user = new User().where("email", "=", email).where("password", "=", password).first();
        if(user == null){
            println("Invalid email or password");
            if(tryAgain()){
                menu();
            }
        }
        return user;
    }


   
}
