package org.example.bookstore.controllers;


import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.Controller;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Book;

import org.example.bookstore.views.Index;
import org.example.bookstore.views.Customer;
import org.example.bookstore.views.BookView;

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
                exploreBooks();
                switchAction(null, user);
                break;
            case 2:
                break;
                
            case 3:
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

    public static void exploreBooks(){
        Dictionary searchDict = BookView.searchMenu();
        // println(search);
        String search = (String) searchDict.get("search");
        List<Book> books = new Book()
        .join("categories", "categories.id", "=", "books.id")
        .where("books.id", "=", search)
        .orWhere("books.name", "=", "%"+search+"%")
        .get();
        
        Integer op = BookView.searchMenu2();
        switch(op){
            case 1:
                BookView.searchMenu();
                break;
            case 2:
                break;
            default:

        }

    }
}
