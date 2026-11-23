package designPattern.behavioural;
/*

8. State Pattern
Purpose: Changes an object's behavior when its internal state changes.
Real-world example: An order can be CREATED, PAID, SHIPPED, or DELIVERED.
*/
interface OrderState {
    void next(Order order);
    String name();
}

class CreatedState implements OrderState {
    public void next(Order order) {
        order.setState(new PaidState());
    }

    public String name() {
        return "CREATED";
    }
}

class PaidState implements OrderState {
    public void next(Order order) {
        order.setState(new ShippedState());
    }

    public String name() {
        return "PAID";
    }
}

class ShippedState implements OrderState {
    public void next(Order order) {
        order.setState(new DeliveredState());
    }

    public String name() {
        return "SHIPPED";
    }
}

class DeliveredState implements OrderState {
    public void next(Order order) {
        System.out.println("Order already delivered");
    }

    public String name() {
        return "DELIVERED";
    }
}

class Order {
    private OrderState state = new CreatedState();

    void setState(OrderState state) {
        this.state = state;
    }

    void next() {
        state.next(this);
    }

    void showStatus() {
        System.out.println(state.name());
    }
}

public class StateDemo {
    public static void main(String[] args) {
        Order order = new Order();

        order.showStatus();
        order.next();

        order.showStatus();
        order.next();

        order.showStatus();
        order.next();

        order.showStatus();
    }
}