package org.example.bookstore.models;
import java.math.BigInteger;
import java.util.List;
import org.example.bookstore.util.View;
import java.util.ArrayList;


import org.example.bookstore.util.Model;
import org.example.bookstore.util.Dictionary;

public class Order extends Model<Order> {

    public BigInteger id;
    public BigInteger user_id;
    public String payment_type;
    public String status;
    public Integer total_paid;
    public String account_no;
    public String card_no;
    public String card_cvc;
    public String address;


    public Order() {
        super.table("orders");
    }


    public void displayInfo(){
        println("\n======================== Order, Invoice no = "+(this.id)+" ========================");
        println("Status => "+this.status);
        println("Payment Type => "+this.payment_type);
        println("Account No => "+this.account_no);
        println("Card No => "+this.card_no);
        println("Total Paid => "+this.total_paid);
        List<OrderLine> lines = new OrderLine().where("order_id", "=", this.id.toString()).get();

        for(OrderLine line: lines){
            line.displayInfo();
        }
    }

}
