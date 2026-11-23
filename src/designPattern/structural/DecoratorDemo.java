package designPattern.structural;

/*
4. Decorator Pattern
Purpose: Adds behavior to an object dynamically without modifying its
original class.
Real-world example: Adding milk or sugar to a coffee.*/
interface Coffee {
    String description();
    double cost();
}

class BasicCoffee implements Coffee {
    public String description() {
        return "Coffee";
    }

    public double cost() {
        return 50;
    }
}

class MilkDecorator implements Coffee {
    private final Coffee coffee;

    MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String description() {
        return coffee.description() + " + Milk";
    }

    public double cost() {
        return coffee.cost() + 10;
    }
}

class SugarDecorator implements Coffee {
    private final Coffee coffee;

    SugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public String description() {
        return coffee.description() + " + Sugar";
    }

    public double cost() {
        return coffee.cost() + 5;
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        Coffee coffee = new SugarDecorator(
            new MilkDecorator(new BasicCoffee()));

        System.out.println(coffee.description());
        System.out.println(coffee.cost());
    }
}