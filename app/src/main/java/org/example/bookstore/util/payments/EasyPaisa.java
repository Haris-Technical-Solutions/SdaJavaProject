package org.example.bookstore.util.payments;
import org.example.bookstore.util.Dictionary;


public class EasyPaisa implements PaymentBehavior  {
    public static final java.util.Scanner scanner = new java.util.Scanner(System.in);
    public String type = "easypaisa";
    public Dictionary details(){
        Dictionary account = new Dictionary();
        System.out.println("Enter the Account No: ");
        account.put("account_no", scanner.nextLine());
        account.put("payment_type", this.type);

        return account;
    }
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using Easy Paisa.");
    }
}
