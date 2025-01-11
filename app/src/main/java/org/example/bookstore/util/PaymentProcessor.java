package org.example.bookstore.util;

import org.example.bookstore.util.payments.PaymentBehavior;
// Payment Method Strategy:
// Imagine a scenario where an online store offers multiple payment methods (e.g., credit card, PayPal, and Bitcoin). The store needs to process payments based on the chosen method.
// Define a PaymentBehavior interface.
// Create concrete strategies for CreditCardPayment, PayPalPayment, and BitcoinPayment.
// Create a PaymentProcessor class that can dynamically change the payment strategy.
// Demonstrate how a customer can switch between payment methods at runtime.

// Muhammad Haris
// F2022065116

// public class PaymentSimulator {
//     public static void main(String[] args) {
//         System.out.println("\n====================== Strategiy pattren ===========================\n");

//         PaymentProcessor processor = new PaymentProcessor(new CreditCardPayment());
//         processor.processPayment(100.00);

//         System.out.println("\nSwitching to PayPal Payment...");
//         processor.setPaymentBehavior(new PayPalPayment());
//         processor.processPayment(200.00);

//         System.out.println("\nSwitching to Bitcoin Payment...");
//         processor.setPaymentBehavior(new BitcoinPayment());
//         processor.processPayment(300.00);
//     }
// }
public class PaymentProcessor {
    private PaymentBehavior paymentBehavior;

    public PaymentProcessor(PaymentBehavior paymentBehavior) {
        this.paymentBehavior = paymentBehavior;
    }

    public void setPaymentBehavior(PaymentBehavior paymentBehavior) {
        this.paymentBehavior = paymentBehavior;
    }

    public Dictionary inputDetails() {
        if (paymentBehavior != null) {
            Dictionary details = paymentBehavior.details();
            return details;
        } else {
            System.out.println("No payment method selected.");
        }
        return null;

    }
    public void processPayment(double amount) {
        if (paymentBehavior != null) {
            paymentBehavior.pay(amount);
        } else {
            System.out.println("No payment method selected.");
        }
    }
}


