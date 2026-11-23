package designPattern.behavioural;

import java.util.*;

/*7. Observer Pattern
 Purpose: Notifies multiple dependent objects when an object's state changes.
Real-world example: Sending notifications to users when a stock price changes.*/
interface Observer {
    void update(double price);
}

class MobileApp implements Observer {
    public void update(double price) {
        System.out.println("Mobile notification: " + price);
    }
}

class EmailService implements Observer {
    public void update(double price) {
        System.out.println("Email notification: " + price);
    }
}

class Stock {
    private final List<Observer> observers = new ArrayList<>();

    void subscribe(Observer observer) {
        observers.add(observer);
    }

    void setPrice(double price) {
        System.out.println("Stock price changed");
        observers.forEach(o -> o.update(price));
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        Stock stock = new Stock();

        stock.subscribe(new MobileApp());
        stock.subscribe(new EmailService());

        stock.setPrice(1500);
    }
}