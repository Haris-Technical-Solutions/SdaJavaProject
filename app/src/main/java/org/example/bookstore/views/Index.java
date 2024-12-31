package org.example.bookstore.views;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Subject;
import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;
import java.util.Scanner; 

public class Index extends View {
    public Index() {
        // super();
        // while(true){
            // menu();
            // if(nextInt() == 0){
            //     break;
            // }
        // }
        // menu();
    }
    public static int menu(){
        
        println("\n============= Welcome to the bookstore =============\n");
        println("1) Login");
        println("2) Register");
        println("0) Exit");
        println("Enter your choice: ");
        int choice = nextInt();
        return choice;
        // switch(choice){
        //     case 1:
        //         User user = Login.menu();
        //         if(user != null){
        //             if(user.is_admin == 1){
        //                 println("\n============= Welcome Admin =============\n");
        //             }else{
        //                 println("\n============= Welcome Customer =============\n");
        //                 new Customer(user);
        //             }
        //         }
        //         break;
        //     case 2:
        //         Register.menu();
        //         menu();
        //         break;
        //     case 0:
        //         break;
        //     default:
        //         println("Invalid choice");
        //         menu();
        // }
    }

    public static void boot(){
        new Index();
    }


   
}
