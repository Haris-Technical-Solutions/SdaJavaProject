package org.example.bookstore.views;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Category;
import org.example.bookstore.models.Book;
import org.example.bookstore.models.Cart;

import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;

public class CartView extends View {

    // User user;
    // public CartView(User user) {
    //     this.user = user;
    // }

    public static Integer menu(){
        println("\n============= My Cart =============\n");
        println("1) Remove Book from Cart");
        println("2) Place Order");
        println("3) Empty Cart");
        println("0) Back");
        println("Enter your choice: ");
        int choice = nextInt();
        return choice;
    }

    public static Dictionary addMenu(){
        println("\n============= Add Book To Cart =============\n");
        Dictionary cart_input = new Dictionary();
        println("Enter the Book ID: ");
        cart_input.put("book_id", scanner.nextLine());
        return cart_input;
    }

    // public static Dictionary editMenu(List<Category> categories){
    //     println("\n============= Edit Book Category =============\n");
    //     Dictionary book_edit = new Dictionary();

    //     println("Enter the Id : ");
    //     book_edit.put("id", scanner.nextLine());
    //     println("Enter the Name: ");
    //     book_edit.put("name", scanner.nextLine());
    //     println("Enter the author: ");
    //     book_edit.put("author", scanner.nextLine());
    //     println("Enter the ISBN: ");
    //     book_edit.put("isbn", scanner.nextLine());
    //     println("Enter the Price: ");
    //     book_edit.put("price", scanner.nextLine());

    //     CategoryView.listCategories(categories);
    //     println("Enter the Category No: ");
    //     book_edit.put("category_id", scanner.nextLine());

    //     println("Enter the Description: ");
    //     book_edit.put("description", scanner.nextLine());

    //     return book_edit;
    // }

    public static Dictionary deleteMenu(){
        println("\n============= Remove Book From Cart =============\n");
        Dictionary cart_input = new Dictionary();
        println("Enter the Book ID: ");
        cart_input.put("book_id", scanner.nextLine());
        return cart_input;
    }
    
    public static void listCart(List<Cart> carts){
        Book book;
        println("\n============= Cart Books List =============\n");
        for (Cart cart : carts) {
            book =  new Book().where("id", "=", cart.book_id.toString()).first();
            book.displayInfo();
        }
    }
    
    // public static Dictionary searchMenu(){
    //     println("\n============= Explore Books =============\n");
    //     Dictionary explore_book = new Dictionary();

    //     println("Search: ");
    //     explore_book.put("id", scanner.nextLine());
    //     return explore_book;
    // }
    // public static boolean tryAgain(){
    //     println("Do you want to try again? (y/n)");
    //     String op = scanner.nextLine();
    //     return op.equals("y");
    // }
    // public static Integer searchMenu2(){
    //     println("\n============= All above are Searched Books =============\n");

    //     println("1) Search Again");
    //     println("2) Add Some Books to Cart");
    //     println("0) Back");
    //     Integer choice = scanner.nextLine();
    //     return choice;
    // }
   

  


   
}
