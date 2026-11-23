package designPattern.structural;
/*
1. Adapter Pattern
Purpose: Converts one interface into another interface expected by the client.
Real-world example: Integrating a third-party payment gateway with your
existing payment interface.
*/
// Target interface
interface PaymentProcessor {
    void pay(double amount);
}

// Existing third-party API
class PayPalService {
    void makePayment(double amount) {
        System.out.println("PayPal payment: " + amount);
    }
}

// Adapter
class PayPalAdapter implements PaymentProcessor {
    private final PayPalService payPal;

    PayPalAdapter(PayPalService payPal) {
        this.payPal = payPal;
    }

    @Override
    public void pay(double amount) {
        payPal.makePayment(amount);
    }
}

// Client
public class AdapterDemo {
    public static void main(String[] args) {
        PaymentProcessor processor =
            new PayPalAdapter(new PayPalService());

        processor.pay(1000);
    }
}