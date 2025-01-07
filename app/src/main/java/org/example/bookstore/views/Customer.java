package org.example.bookstore.views;

import org.example.bookstore.models.User;

import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;

public class Customer extends View {

    User user;
    public Customer(User user) {
        this.user = user;
    }

    public static int menuAdmin(){
        println("\n============= Manage Customer =============\n");
        println("1) List all Customers");
        println("0) Back");
        int choice = nextInt();
        return choice;
    }
    

    // public st
    public static void listCustomers(List<User> users){
        println("\n============= Customers List =============\n");
        for (User user : users) {
            println(user.id+") "+user.name+"\t"+user.email);
        }
    }

  


   
}
