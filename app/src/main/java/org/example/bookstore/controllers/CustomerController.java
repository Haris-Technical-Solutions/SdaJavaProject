package org.example.bookstore.controllers;


import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.Controller;

import org.example.bookstore.models.User;

import org.example.bookstore.views.Index;
import org.example.bookstore.views.Customer;

public class CustomerController extends Controller {

    public CustomerController() {

    }

    public static void switchActionFromAdmin(Integer choice, User user){
        if(choice == null){
            choice = Customer.menuAdmin();
        }
        switch (choice) {
            case 1:
                getCustomers();
                switchActionFromAdmin(null, user);
                break;
            case 0:
                break;
            default:
                switchActionFromAdmin(null, user);
                break;
        }
    }
    public static void switchAction(Integer choice, User user){
        if(choice == null){
            choice = Index.menuAfterLogin(user);
        }
        
        switch (choice) {
            case 1:
                // getCustomers();
                break;
            case 0:
                System.exit(0);
            default:
                break;
        }
    }
    public static void getCustomers(){
        List<User> users = new User().where("is_admin", "=", "0").get();
        Customer.listCustomers(users);
    }
}
