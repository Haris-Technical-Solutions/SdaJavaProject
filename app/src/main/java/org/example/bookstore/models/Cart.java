package org.example.bookstore.models;
import java.math.BigInteger;
import org.example.bookstore.util.View;

import org.example.bookstore.util.Model;

public class Cart extends Model<Cart> {
    // static String table = "users";

    public BigInteger id;
    public BigInteger book_id;
    public BigInteger user_id;

    public Cart() {
        super.table("cart");
    }


    // public Subject create() {
    //     this.subject = "Math";
    //     this.deleted_at = null;
    //     this.created_at = null;
    //     this.updated_at = null;
    //     return this;
    // }

    public void displayInfo(){
        // println("\n============ Cart "+(this.id)+" ============");
        // println("Title => "+this.name);
        // println("Author => "+this.author);
        // println("ISBN => "+this.isbn);
        // println("Description => "+this.description);
        // println("Price => "+this.price);
        // Category category = new Category().where("id", "=",(String) this.category_id.toString()).first();
        // if(category != null){
        //     println("Cart Category => "+category.name);
        // }
    }

}
