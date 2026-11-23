package solid;

public class OpenClosedDemo {

    // Abstraction
    interface PaymentMethod {
        void pay(double amount);
    }

    // Credit Card implementation
    static class CreditCardPayment implements PaymentMethod {

        public void pay(double amount) {
            System.out.println(
                "Credit Card payment: " + amount
            );
        }
    }

    // UPI implementation
    static class UpiPayment implements PaymentMethod {

        public void pay(double amount) {
            System.out.println(
                "UPI payment: " + amount
            );
        }
    }

    // PayPal implementation
    static class PayPalPayment implements PaymentMethod {

        public void pay(double amount) {
            System.out.println(
                "PayPal payment: " + amount
            );
        }
    }

    // Existing service does not change
    static class PaymentService {

        public void processPayment(
                PaymentMethod paymentMethod, double amount) {

            paymentMethod.pay(amount);
        }
    }

    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();

        paymentService.processPayment(
            new CreditCardPayment(), 1000
        );

        paymentService.processPayment(
            new UpiPayment(), 2000
        );

        paymentService.processPayment(
            new PayPalPayment(), 3000
        );
    }
}
