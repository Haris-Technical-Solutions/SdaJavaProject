package org.example.bookstore.models;
import java.math.BigInteger;
import org.example.bookstore.util.View;

import org.example.bookstore.util.Model;

public class Book extends Model<Book> {
    // static String table = "users";

    public BigInteger id;
    public BigInteger category_id;
    public String name;
    public String author;
    public String isbn;
    public String description;
    public Integer price;

    public Book() {
        super.table("books");
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("subject: " + name);
        System.out.println("deleted_at: " + author);
    }

    public void getCategory(){

    }

    // public Subject create() {
    //     this.subject = "Math";
    //     this.deleted_at = null;
    //     this.created_at = null;
    //     this.updated_at = null;
    //     return this;
    // }

    public void displayInfo(){
        println("\n============ Book "+(this.id)+" ============");
        println("Title => "+this.name);
        println("Author => "+this.author);
        println("ISBN => "+this.isbn);
        println("Description => "+this.description);
        println("Price => "+this.price);
        Category category = new Category().where("id", "=",(String) this.category_id.toString()).first();
        if(category != null){
            println("Book Category => "+category.name);
        }
    }

}
