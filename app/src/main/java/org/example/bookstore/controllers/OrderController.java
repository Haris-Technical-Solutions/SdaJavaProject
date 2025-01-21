package org.example.bookstore.controllers;


import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.Controller;

import org.example.bookstore.util.PaymentProcessor;
import org.example.bookstore.util.payments.DebitCard;
import org.example.bookstore.util.payments.CreditCard;
import org.example.bookstore.util.payments.JazzCash;
import org.example.bookstore.util.payments.EasyPaisa;



import org.example.bookstore.models.User;
import org.example.bookstore.models.Book;
import org.example.bookstore.models.Cart;
import org.example.bookstore.models.Order;
import org.example.bookstore.models.OrderLine;

import org.example.bookstore.views.Index;
import org.example.bookstore.views.Customer;
import org.example.bookstore.views.BookView;
import org.example.bookstore.views.CartView;
import org.example.bookstore.views.OrderView;

public class OrderController extends Controller {

   
    // public static void switchAction(Integer choice, User user){
    //     if(choice == null){
    //         choice = Index.menuAfterLogin(user);
    //     }
        
    //     switch (choice) {
    //         case 1:
    //             switchAction(null, user);
    //             break;
    //         case 2:
    //             switchAction(null, user);
    //             break;
    //         case 3:
    //             break;
    //         case 0:
    //             System.exit(0);
    //         default:
    //             break;
    //     }
    // }

    public static void takeOrder(User user){
        Integer paymentType = OrderView.askPaymentMethod();
        // PaymentProcessor
        Integer total = 0;

        List<Book> cartBooks = Cart.getCartBooks(user);
        BookView.listBooks(cartBooks);
        for(Book book : cartBooks){
            total += book.price;
        }

        PaymentProcessor processor;
        Dictionary details;

        switch(paymentType){
            case 1:
                processor = new PaymentProcessor(new DebitCard());
            break;
            case 2:
                processor = new PaymentProcessor(new CreditCard());
            break;
            case 3:
                processor = new PaymentProcessor(new JazzCash());
            break;
            case 4:
                processor = new PaymentProcessor(new EasyPaisa());
            break;
            default:
                processor = null;
                println("Invalid Payment Method Option!");
                takeOrder(user);
        }
        if(processor != null){
            println("\n");
            details = processor.inputDetails();
            if(details == null){
                takeOrder(user);
            }
            // println(details);
            details.put("user_id", user.id.toString());
            details.put("total_paid", total.toString());
            // println("Enter Your Delivery Address");
            // details.put("address", scanner.nextLine());
            details.remove("pin");
            processor.processPayment(total);
            Order order = new Order().create(details);
            Dictionary orderLine;
            for(Book orderd_book: cartBooks){
                orderLine = new Dictionary();
                orderLine.put("order_id", order.id.toString());
                orderLine.put("book_id", orderd_book.id.toString());
                orderLine.put("qty", 1);
                orderLine.put("price", orderd_book.price.toString());
                orderLine.put("total", orderd_book.price.toString());
                new OrderLine().create(orderLine);
            }
            Cart.emptyCart(user);
            println("Order Placed Successfully!");

        }


        // println(paymentType);
    }


    public static void OrderMenu(){
        // getOrders();

        Integer op = OrderView.menu();
        List<Order> orders;
        Dictionary status;
        switch(op){
            case 1:
                status = OrderView.changeOrderStatus();

                Order order = new Order().where("id", "=",(String) status.get("order_id")).first();
                if(order != null){
                    String order_id = (String) status.get("order_id");
                    status.remove("order_id");
                    new Order().where("id", "=", order_id).update(status);
                    println("Order Status Updated Successfully!");
                }else{
                    println("Order Not Found!");
                }
                OrderMenu();
                break;
            case 2:
                status = new Dictionary();
                status.put("status", "delivered");
                new Order()
                // .where("status", "=", "shipped")
                .update(status);
                println("All Orders Status Updated Successfully!");
                OrderMenu();
                break;
                
            case 3:
                orders = new Order().where("status", "=", "pending").get();
                OrderView.listOrders(orders);
                OrderMenu();
                break;
            case 4:
                orders = new Order().where("status", "=", "shipped").get();
                OrderView.listOrders(orders);
                OrderMenu();
                break;
            case 5:
                orders = new Order().where("status", "=", "delivered").get();
                OrderView.listOrders(orders);
                OrderMenu();
                break;
            case 0:

                break;
            default:
                OrderMenu();


        }



    }

    public static void getOrders(){
        List<Order> orders = new Order().get();
        OrderView.listOrders(orders);
    }


    // public static void getCustomers(){
    //     List<User> users = new User().where("is_admin", "=", "0").get();
    //     Customer.listCustomers(users);
    // }

    // public static void exploreBooks(User user, String search){
    //     // println(search);
    //     if(search == null){
    //         Dictionary searchDict = BookView.searchMenu();
    //         search = (String) searchDict.get("search");
    //     }
    //     List<Book> books = new Book()
    //     .join("categories", "categories.id", "=", "books.id")
    //     .where("books.id", "LIKE", search)
    //     .orWhere("books.name", "LIKE", "%"+search+"%")
    //     .get();
        
    //     BookView.listBooks(books);
    //     Integer op = BookView.searchMenu2();
    //     switch(op){
    //         case 1:
    //             exploreBooks(user, null);
    //             break;
    //         case 2:
    //             Dictionary bookID = CartView.addMenu();
    //             if(new Book().where("id","=", bookID.get("book_id").toString()).first() != null){
    //                 if(new Cart().where("user_id", "=", user.id.toString()).where("book_id", "=",(String) bookID.get("book_id")).first() == null){
    //                     bookID.put("user_id", user.id);
    //                     new Cart().create(bookID);
    //                     println("This Book is added to Cart Successfully");

    //                 }else{
    //                     println("This Book is already added to Cart");
    //                 }
    //             }else{
    //                 println("No book Exist with this id!");
    //             }
    //             exploreBooks(user, search);
    //             break;
    //         default:

    //     }

    // }

    // public static void myCart(User user){
    //     // println(search);
    //     List<Cart> cart_items  = new Cart().where("user_id", "=", user.id.toString()).get();
    //     CartView.listCart(cart_items);

    //     // List<Book> books = new Book()
    //     // .join("categories", "categories.id", "=", "books.id")
    //     // .where("books.id", "LIKE", search)
    //     // .orWhere("books.name", "LIKE", "%"+search+"%")
    //     // .get();
        
    //     // CartView.menu();
    //     Integer op = CartView.menu();
    //     switch(op){
    //         case 1:
    //             Dictionary bookID = CartView.deleteMenu();
    //             if(new Cart().where("book_id", "=", bookID.get("book_id").toString()).where("user_id", "=", user.id.toString()).first() != null){
    //                 new Cart().where("book_id", "=", bookID.get("book_id").toString()).where("user_id", "=", user.id.toString()).delete();
    //                 println("Book Id = "+bookID.get("book_id").toString()+" removed Successfully from Cart!");
    //             }else{
    //                 println("Book with this Id = "+bookID.get("book_id").toString()+" not found in Cart!");
    //             }
    //             myCart(user);
    //             break;
    //         case 2:
                
    //             break;
    //         default:

    //     }

    // }
}
