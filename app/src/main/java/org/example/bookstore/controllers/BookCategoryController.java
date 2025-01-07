package org.example.bookstore.controllers;


import java.util.List;
import java.math.BigInteger;

import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.Controller;

import org.example.bookstore.models.User;
import org.example.bookstore.models.Book;
import org.example.bookstore.models.Category;

// import org.example.bookstore.views.Index;
// import org.example.bookstore.views.Customer;
import org.example.bookstore.views.CategoryView;

public class BookCategoryController extends Controller {

    public BookCategoryController() {

    }

    public static void switchAction(Integer choice, User user){
        if(choice == null){
            choice = CategoryView.menu();
        }

        switch(choice){
            case 1:
                listCategories();
                switchAction(null, user);
                break;
            case 2:
                Dictionary category = CategoryView.addMenu();
                new Category().create(category);
                println("Category added successfully");

                switchAction(null, user);
                break;
            case 3:
                Dictionary category_edit = CategoryView.editMenu();
                Category cat = new Category().where("id", "=",(String) category_edit.get("id")).first();
                Category cat_check = new Category().where("id", "!=",(String) category_edit.get("id")).where("name", "=",(String) category_edit.get("name")).first();
                
                if(cat_check != null){
                    println("Category with the same name already exists");
                    switchAction(null, user);
                    break;
                }
                if(cat != null){
                    Dictionary payload = new Dictionary(category_edit);
                    payload.remove("id");
                    // println(category_edit);
                    // println(payload);
                    new Category().where("id", "=",(String) category_edit.get("id")).update(payload);
                    println("Category updated successfully");
                }else{
                    println("Category not with given id not found");
                }
                switchAction(null, user);

                break;
            case 4:
                Dictionary category_delete = CategoryView.deleteMenu();
                Category cat_del = new Category().where("id", "=",(String) category_delete.get("id")).first();
                
                if(cat_del != null){
                    new Category().where("id", "=",(String) category_delete.get("id")).delete();
                    println("Category deleted successfully");
                }else{
                    println("Category not with given id not found");
                }
                switchAction(null, user);
                break;
            case 0:
                break;
            default:
                println("Invalid choice");
        }
    }

    public static void listCategories(){
        List<Category> categories = new Category().get();
        CategoryView.listCategories(categories);
    }
}
