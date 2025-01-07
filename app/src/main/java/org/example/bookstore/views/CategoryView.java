package org.example.bookstore.views;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Category;

import java.util.List;
import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;

public class CategoryView extends View {

    // User user;
    // public CategoryView(User user) {
    //     this.user = user;
    // }

    public static Integer menu(){
        println("\n============= Manage Book Categories =============\n");
        println("1) List all Book Categories");
        println("2) Add Book Category");
        println("3) Edit Book Category");
        println("4) Delete Book Category");
        println("0) Back");
        int choice = nextInt();
        return choice;
    }

    public static Dictionary addMenu(){
        println("\n============= Add Book Category =============\n");
        Dictionary category = new Dictionary();
        println("Enter the name: ");
        category.put("name", scanner.nextLine());
        println("Enter the Description: ");
        category.put("description", scanner.nextLine());
        return category;
    }

    public static Dictionary editMenu(){
        println("\n============= Edit Book Category =============\n");
        Dictionary category = new Dictionary();

        println("Enter the Id: ");
        category.put("id", scanner.nextLine());
        println("Enter the Name: ");
        category.put("name", scanner.nextLine());
        println("Enter the Description: ");
        category.put("description", scanner.nextLine());

        return category;
    }

    public static Dictionary deleteMenu(){
        println("\n============= Delete Book Category =============\n");
        Dictionary category = new Dictionary();

        println("Enter the Id: ");
        category.put("id", scanner.nextLine());

        return category;
    }
    public static void listCategories(List<Category> categories){
        println("\n============= Book Categories List =============\n");
        for (Category category : categories) {
            println(category.id+")\t"+category.name);
        }
    }
    

   

  


   
}
