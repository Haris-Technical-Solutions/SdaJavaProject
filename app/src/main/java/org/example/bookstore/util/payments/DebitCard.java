package org.example.bookstore.util.payments;
import org.example.bookstore.util.Dictionary;


public class DebitCard implements PaymentBehavior {
    public static final java.util.Scanner scanner = new java.util.Scanner(System.in);
    public String type = "debit";
    public Dictionary details(){
        Dictionary card = new Dictionary();
        System.out.println("Enter the Card No: ");
        card.put("card_no", scanner.nextLine());
        System.out.println("Enter the Card CVC: ");
        card.put("card_cvc", scanner.nextLine());
        System.out.println("Enter the Card PIN: ");
        card.put("pin", scanner.nextLine());

        card.put("payment_type", this.type);

        return card;
    }
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using Debit Card.");
    }
}
