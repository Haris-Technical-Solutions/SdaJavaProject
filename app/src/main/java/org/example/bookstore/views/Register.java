package org.example.bookstore.views;

// import org.example.bookstore.models.User;
// 
import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;
import org.example.bookstore.controllers.AuthController;

public class Register extends View {

    public Register() {
       
    }

    public static Dictionary menu(){
        println("\n============= Register =============\n");
        println("Enter your name: ");
        String name = scanner.nextLine();
        println("Enter your email: ");
        String email = scanner.nextLine();
        println("Enter your password: ");
        String password = scanner.nextLine();

        Dictionary register = new Dictionary();
        register.put("name", name);
        register.put("email", email);
        register.put("password", password);
        return register;

        
        

    }

   
}
