package designPattern.structural;

class InventoryService {
    void checkStock() {
        System.out.println("Stock available");
    }
}

class PaymentService {
    void processPayment() {
        System.out.println("Payment completed");
    }
}

class ShippingService {
    void shipOrder() {
        System.out.println("Order shipped");
    }
}

// Facade
/*
Purpose: Provides a simple interface over a complex subsystem.
Real-world example: An order service coordinating inventory, payment, and shipping.
*/
class OrderFacade {
    private final InventoryService inventory = new InventoryService();
    private final PaymentService payment = new PaymentService();
    private final ShippingService shipping = new ShippingService();

    void placeOrder() {
        inventory.checkStock();
        payment.processPayment();
        shipping.shipOrder();
    }
}

public class FacadeDemo {
    public static void main(String[] args) {
        new OrderFacade().placeOrder();
    }
}