package org.example.bookstore.views;

import org.example.bookstore.models.User;
import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;
import java.util.Scanner; 

public class Index extends View {
    public Index() {
    }
    public static int menu(){
        
        println("\n============= Welcome to the bookstore =============\n");
        println("1) Login");
        println("2) Register");
        println("0) Exit");
        println("Enter your choice: ");
        int choice = nextInt();
        return choice;
    }
    public static Integer menuAfterLogin(User user){
        if(user.is_admin == 1){
            Integer op = AdminMenu();
            return op;
        }else{      
            Integer op = CustomerMenu();
            return op;
        }
    }
    
    public static int AdminMenu(){
        println("\n============= Admin Menu =============\n");
        println("1) Manage Book Categories");
        println("2) Manage Books");
        println("3) Manage Orders");
        println("4) Manage Customers");
        println("0) Logout & Exit");
        println("Enter your choice: ");
        int choice = nextInt();
        return choice;
    }
    public static int CustomerMenu(){
        println("\n============= Admin Menu =============\n");
        println("1) Explore Books");
        println("2) My Cart");
        println("3) My Orders");
        println("0) Logout & Exit");
        println("Enter your choice: ");
        int choice = nextInt();
        return choice;
    }

    public static void boot(){
        new Index();
    }


   
}
