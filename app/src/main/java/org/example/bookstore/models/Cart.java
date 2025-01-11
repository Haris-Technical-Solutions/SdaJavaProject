package org.example.bookstore.models;
import java.math.BigInteger;
import java.util.List;
import org.example.bookstore.util.View;
import java.util.ArrayList;


import org.example.bookstore.util.Model;
import org.example.bookstore.util.Dictionary;

public class Cart extends Model<Cart> {
    // static String table = "users";

    public BigInteger id;
    public BigInteger book_id;
    public BigInteger user_id;

    public Cart() {
        super.table("cart");
    }

    public static List<Book> getCartBooks(User user){
        List<Book> books = new ArrayList<>();
        List<Cart> carts = new Cart().where("user_id", "=", user.id.toString()).get();

        for(Cart cart : carts){
            books.add(new Book().where("id", "=", cart.book_id.toString()).first());
        }
        return books;
    }
    public static void emptyCart(User user){
        new Cart().where("user_id", "=", user.id.toString()).delete();
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
