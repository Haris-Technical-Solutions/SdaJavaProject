package org.example.bookstore.views;

import org.example.bookstore.models.User;
// import org.example.bookstore.models.Category;
// import org.example.bookstore.models.Book;
// import org.example.bookstore.models.Cart;
import org.example.bookstore.models.Order;

import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;

public class OrderView extends View {


    public static Integer askPaymentMethod(){
        println("\n============= Select Payment Method =============\n");
        println("1) Debit Card");
        println("2) Credit Card");
        println("3) JazzCash");
        println("4) EasyPaisa");
        println("Enter your choice: ");

        Integer op = nextInt();
        return op;
        // switch()

        // println("Enter the Payment Method : ");
        // order.put("payment_type", scanner.nextLine());
        // return order;
    }

    public static void listOrders(List<Order> orders){
        println("\n============= Orders List =============\n");
        for (Order order : orders) {
            order.displayInfo();
        }
    }
    // User user;
    // public CartView(User user) {
    //     this.user = user;
    // }

    public static Integer menu(){
        println("\n============= Orders =============\n");
        println("1) Change Order Status");
        println("2) Marke all pending or shipped orders as delivered");
        println("3) List Pending Orders");
        println("4) List Shipped Orders");
        println("5) List Delivered Orders");
        println("0) Back");
        println("Enter your choice: ");


        int choice = nextInt();
        return choice;
    }

    public static Dictionary changeOrderStatus(){
        Dictionary status = new Dictionary();

        println("\n============= Change Order Status =============\n");
        println("Enter the Order ID: ");
        // Integer order_id = nextInt();
        status.put("order_id", scanner.nextLine());
        do{
            println("1) Pending");
            println("2) Shipped");
            println("3) Delivered");
            println("Enter the Status: ");
            // Integer status = nextInt();
            status.put("status", scanner.nextLine());

        }while(Integer.parseInt((String) status.get("status")) < 1 || Integer.parseInt((String) status.get("status")) > 3);
        return status;

        // Order order = new Order().where("id", "=", order_id.toString()).first();
        // order.status = status;
        // order.save();
    }

    // public static Dictionary addMenu(){
    //     println("\n============= Add Book To Cart =============\n");
    //     Dictionary cart_input = new Dictionary();
    //     println("Enter the Book ID: ");
    //     cart_input.put("book_id", scanner.nextLine());
    //     return cart_input;
    // }

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

    // public static Dictionary deleteMenu(){
    //     println("\n============= Remove Book From Cart =============\n");
    //     Dictionary cart_input = new Dictionary();
    //     println("Enter the Book ID: ");
    //     cart_input.put("book_id", scanner.nextLine());
    //     return cart_input;
    // }
    
    // public static void listCart(List<Cart> carts){
    //     Book book;
    //     println("\n============= Cart Books List =============\n");
    //     for (Cart cart : carts) {
    //         book =  new Book().where("id", "=", cart.book_id.toString()).first();
    //         book.displayInfo();
    //     }
    // }
    
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
