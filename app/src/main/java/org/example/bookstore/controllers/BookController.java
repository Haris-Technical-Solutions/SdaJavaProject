package org.example.bookstore.controllers;


import java.util.List;
import java.math.BigInteger;

import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.Controller;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Category;
import org.example.bookstore.models.Book;

// import org.example.bookstore.views.Index;
// import org.example.bookstore.views.Customer;
import org.example.bookstore.views.BookView;

public class BookController extends Controller {

    public BookController() {

    }

    public static void switchAction(Integer choice, User user){
        if(choice == null){
            choice = BookView.menu();
        }
        List<Category> categories = new Category().get();


        switch(choice){
            case 1:
                listBooks();
                switchAction(null, user);
                break;
            case 2:

                Dictionary book_store = BookView.addMenu(categories);
                new Book().create(book_store);
                println("Book added successfully");

                switchAction(null, user);
                break;
            case 3:
                Dictionary book_edit = BookView.editMenu(categories);
                Book book = new Book().where("id", "=",(String) book_edit.get("id")).first();
                Book book_check = new Book().where("id", "!=",(String) book_edit.get("id")).where("name", "=",(String) book_edit.get("name")).first();
                
                if(book_check != null){
                    println("Book with the same name already exists");
                    switchAction(null, user);
                    break;
                }
                if(book != null){
                    Dictionary payload = new Dictionary(book_edit);
                    payload.remove("id");
                    new Book().where("id", "=",(String) book_edit.get("id")).update(payload);
                    println("Book updated successfully");
                }else{
                    println("Book not with given id not found");
                }
                switchAction(null, user);

                break;
            case 4:
                Dictionary book_delete = BookView.deleteMenu();
                Book book_del = new Book().where("id", "=",(String) book_delete.get("id")).first();
                
                if(book_del != null){
                    new Book().where("id", "=",(String) book_delete.get("id")).delete();
                    println("Book deleted successfully");
                }else{
                    println("Book not with given id not found");
                }
                switchAction(null, user);
                break;
            case 0:
                break;
            default:
                println("Invalid choice");
        }
    }

    public static void listBooks(){
        List<Book> books = new Book().get();
        BookView.listBooks(books);
    }
}
