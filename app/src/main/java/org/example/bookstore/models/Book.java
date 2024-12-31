package org.example.bookstore.models;
import java.math.BigInteger;

import org.example.bookstore.util.Model;

public class Book extends Model<Book> {
    // static String table = "users";

    public BigInteger id;
    public BigInteger category_id;
    public String name;
    public String author;
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

}
