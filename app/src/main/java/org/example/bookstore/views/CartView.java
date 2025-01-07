package org.example.bookstore.views;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Category;
import org.example.bookstore.models.Book;

import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;

public class CartView extends View {

    // User user;
    // public CartView(User user) {
    //     this.user = user;
    // }

    public static Integer menu(){
        println("\n============= Manage Books =============\n");
        println("1) List all Books");
        println("2) Add Books");
        println("3) Edit Books");
        println("4) Delete Books");
        println("0) Back");
        int choice = nextInt();
        return choice;
    }

    public static Dictionary addMenu(List<Category> categories){
        println("\n============= Add Book =============\n");
        Dictionary book_add = new Dictionary();
        println("Enter the name: ");
        book_add.put("name", scanner.nextLine());
        println("Enter the author: ");
        book_add.put("author", scanner.nextLine());
        println("Enter the ISBN: ");
        book_add.put("isbn", scanner.nextLine());
        println("Enter the Price: ");
        book_add.put("price", scanner.nextLine());

        CategoryView.listCategories(categories);
        println("Enter the Category No: ");
        book_add.put("category_id", scanner.nextLine());

        println("Enter the Description: ");
        book_add.put("description", scanner.nextLine());
        return book_add;
    }

    public static Dictionary editMenu(List<Category> categories){
        println("\n============= Edit Book Category =============\n");
        Dictionary book_edit = new Dictionary();

        println("Enter the Id : ");
        book_edit.put("id", scanner.nextLine());
        println("Enter the Name: ");
        book_edit.put("name", scanner.nextLine());
        println("Enter the author: ");
        book_edit.put("author", scanner.nextLine());
        println("Enter the ISBN: ");
        book_edit.put("isbn", scanner.nextLine());
        println("Enter the Price: ");
        book_edit.put("price", scanner.nextLine());

        CategoryView.listCategories(categories);
        println("Enter the Category No: ");
        book_edit.put("category_id", scanner.nextLine());

        println("Enter the Description: ");
        book_edit.put("description", scanner.nextLine());

        return book_edit;
    }

    public static Dictionary deleteMenu(){
        println("\n============= Delete Book Category =============\n");
        Dictionary book_delete = new Dictionary();

        println("Enter the Id: ");
        book_delete.put("id", scanner.nextLine());

        return book_delete;
    }
    
    public static void listBooks(List<Book> books){
        println("\n============= Book Categories List =============\n");
        for (Book book : books) {
            // println(book.id+")\t"+category.name);
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
