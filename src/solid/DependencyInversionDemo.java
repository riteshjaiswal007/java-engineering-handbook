package solid;

public class DependencyInversionDemo {

    // Abstraction
    interface NotificationService {
        void sendNotification(String message);
    }

    // Email implementation
    static class EmailNotificationService
            implements NotificationService {

        public void sendNotification(String message) {
            System.out.println("Email sent: " + message);
        }
    }

    // SMS implementation
    static class SmsNotificationService
            implements NotificationService {

        public void sendNotification(String message) {
            System.out.println("SMS sent: " + message);
        }
    }

    // High-level module depends on abstraction
    static class OrderService {

        private final NotificationService notificationService;

        // Constructor Dependency Injection
        OrderService(NotificationService notificationService) {
            this.notificationService = notificationService;
        }

        public void placeOrder() {

            System.out.println("Order placed successfully");

            notificationService.sendNotification(
                "Your order has been confirmed"
            );
        }
    }

    public static void main(String[] args) {

        // Inject Email implementation
        OrderService emailOrderService =
            new OrderService(new EmailNotificationService());

        emailOrderService.placeOrder();

        System.out.println("----------------");

        // Inject SMS implementation
        OrderService smsOrderService =
            new OrderService(new SmsNotificationService());

        smsOrderService.placeOrder();
    }
}
