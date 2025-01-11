package org.example.bookstore.controllers;


import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.Controller;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Book;
import org.example.bookstore.models.Cart;
import org.example.bookstore.models.Order;

import org.example.bookstore.views.Index;
import org.example.bookstore.views.Customer;
import org.example.bookstore.views.BookView;
import org.example.bookstore.views.CartView;
import org.example.bookstore.views.OrderView;

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
                exploreBooks(user, null);
                switchAction(null, user);
                break;
            case 2:
                myCart(user);
                switchAction(null, user);
                break;
            case 3:
                myOrders(user);
                switchAction(null, user);
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

    public static void exploreBooks(User user, String search){
        // println(search);
        if(search == null){
            Dictionary searchDict = BookView.searchMenu();
            search = (String) searchDict.get("search");
        }
        List<Book> books = new Book()
        .join("categories", "categories.id", "=", "books.id")
        .where("books.id", "LIKE", search)
        .orWhere("books.name", "LIKE", "%"+search+"%")
        .get();
        
        BookView.listBooks(books);
        Integer op = BookView.searchMenu2();
        switch(op){
            case 1:
                exploreBooks(user, null);
                break;
            case 2:
                Dictionary bookID = CartView.addMenu();
                if(new Book().where("id","=", bookID.get("book_id").toString()).first() != null){
                    if(new Cart().where("user_id", "=", user.id.toString()).where("book_id", "=",(String) bookID.get("book_id")).first() == null){
                        bookID.put("user_id", user.id);
                        new Cart().create(bookID);
                        println("This Book is added to Cart Successfully");

                    }else{
                        println("This Book is already added to Cart");
                    }
                }else{
                    println("No book Exist with this id!");
                }
                exploreBooks(user, search);
                break;
            default:

        }

    }

    public static void myCart(User user){
        List<Cart> cart_items  = new Cart().where("user_id", "=", user.id.toString()).get();
        CartView.listCart(cart_items);

      
        Integer op = CartView.menu();
        switch(op){
            case 1:
                Dictionary bookID = CartView.deleteMenu();
                if(new Cart().where("book_id", "=", bookID.get("book_id").toString()).where("user_id", "=", user.id.toString()).first() != null){
                    new Cart().where("book_id", "=", bookID.get("book_id").toString()).where("user_id", "=", user.id.toString()).delete();
                    println("Book Id = "+bookID.get("book_id").toString()+" removed Successfully from Cart!");
                }else{
                    println("Book with this Id = "+bookID.get("book_id").toString()+" not found in Cart!");
                }
                myCart(user);
                break;
            case 2:
                OrderController.takeOrder(user);
                myCart(user);
                break;
                
            case 3:
                Cart.emptyCart(user);
                myCart(user);
                break;
            default:

        }

    }

    public static void myOrders(User user){
        List<Order> orders = new Order().where("user_id", "=", user.id.toString()).get();
        OrderView.listOrders(orders);
    }
}
