package org.example.bookstore.controllers;


import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.Controller;

import org.example.bookstore.models.User;

import org.example.bookstore.views.Index;
import org.example.bookstore.views.Customer;

public class AdminController extends Controller {

    public AdminController() {

    }

    public static void switchAction(Integer choice, User user){
        if(choice == null){
            choice = Index.menuAfterLogin(user);
        }

        switch(choice){
            case 1:
                BookCategoryController.switchAction(null, user);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                CustomerController.switchActionFromAdmin(null, user);
                break;
            case 0:
                System.exit(0);
            default:
                println("Invalid choice");
        }
    }
}
