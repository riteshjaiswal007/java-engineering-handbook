package designPattern.behavioural;
/*

9. Command Pattern
Purpose: Encapsulates a request as an object, separating the sender
from the receiver.
Real-world example: A remote control that executes different operations.
*/
interface Command {
    void execute();
}

class Light {
    void turnOn() {
        System.out.println("Light ON");
    }

    void turnOff() {
        System.out.println("Light OFF");
    }
}

class LightOnCommand implements Command {
    private final Light light;

    LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}

class RemoteControl {
    private Command command;

    void setCommand(Command command) {
        this.command = command;
    }

    void pressButton() {
        command.execute();
    }
}

public class CommandDemo {
    public static void main(String[] args) {
        Light light = new Light();
        RemoteControl remote = new RemoteControl();

        remote.setCommand(new LightOnCommand(light));
        remote.pressButton();
    }
}