package designPattern.behavioural;

/*
6. Strategy Pattern
Purpose: Defines multiple algorithms and allows you to switch between
them at runtime.
Real-world example: Selecting a payment method such as UPI, credit card, or
 net banking.
*/
interface PaymentStrategy {
    void pay(double amount);
}

class UpiPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid using UPI: " + amount);
    }
}

class CardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid using Card: " + amount);
    }
}

class PaymentContext {
    private PaymentStrategy strategy;

    PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void pay(double amount) {
        strategy.pay(amount);
    }
}

public class StrategyDemo {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext(new UpiPayment());
        context.pay(1000);

        context.setStrategy(new CardPayment());
        context.pay(2000);
    }
}