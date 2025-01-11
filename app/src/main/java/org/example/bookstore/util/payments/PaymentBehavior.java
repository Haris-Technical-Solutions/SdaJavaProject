package org.example.bookstore.util.payments;
import java.util.Scanner; 

import org.example.bookstore.util.Dictionary;
import org.example.bookstore.util.View;

public interface PaymentBehavior {

    Dictionary details();
    void pay(double amount);



}