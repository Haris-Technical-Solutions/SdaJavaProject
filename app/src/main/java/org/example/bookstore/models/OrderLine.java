package org.example.bookstore.models;
import java.math.BigInteger;
import java.util.List;
import org.example.bookstore.util.View;
import java.util.ArrayList;


import org.example.bookstore.util.Model;
import org.example.bookstore.util.Dictionary;

public class OrderLine extends Model<OrderLine> {

    public BigInteger id;
    public BigInteger order_id;
    public BigInteger book_id;

    // public Book book;


    public Integer qty;
    public Integer price;
    public Integer total;

    public OrderLine() {
        super.table("order_lines");
        // if(this.book_id != null){
        //     this.book = new Book().where("id", "=", this.book_id.toString()).first();
        // }
    }

    public void displayInfo(){
        Book book = new Book().where("id", "=", this.book_id.toString()).first();

        println("\n============ Order Book ============");
        println("Book => "+book.name);
        println("Price => "+this.price);
    }

}
