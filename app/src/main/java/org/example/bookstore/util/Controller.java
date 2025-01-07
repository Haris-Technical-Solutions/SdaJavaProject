package org.example.bookstore.util;

// import org.example.bookstore.models.User;
// 
// import java.util.List;
// import org.example.bookstore.util.Dictionary;
import java.util.Scanner; 

public class Controller{
    public static final Scanner scanner = new Scanner(System.in);
    public Controller() {

    }
    public static int nextInt(){
        int inp = -1;
        while (true) {
            try {
                inp = Integer.parseInt(scanner.nextLine());
            break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return inp;
    }
    // public Scanner scanner(){
    //     return new Scanner(System.in); 
    // }

    public static  void println(java.lang.Object val){
        System.out.println(val);
    } 
    
    public static boolean tryAgain(){
        println("Do you want to try again? (y/n)");
        String op = scanner.nextLine();
        return op.equals("y");
    }
}