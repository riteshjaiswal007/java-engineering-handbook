package designPattern.behavioural;
/*12. Chain of Responsibility Pattern
Purpose: Passes a request through a chain of handlers.
Each handler decides whether to process it or pass it to the next handler.
Real-world example: An HTTP request passing through authentication,
authorization, and logging filters.*/

abstract class RequestHandler {
    protected RequestHandler next;

    void setNext(RequestHandler next) {
        this.next = next;
    }

    abstract void handle(String request);
}

class AuthenticationHandler extends RequestHandler {
    void handle(String request) {
        System.out.println("Authentication checked");

        if (next != null) {
            next.handle(request);
        }
    }
}

class AuthorizationHandler extends RequestHandler {
    void handle(String request) {
        System.out.println("Authorization checked");

        if (next != null) {
            next.handle(request);
        }
    }
}

class LoggingHandler extends RequestHandler {
    void handle(String request) {
        System.out.println("Request logged: " + request);

        if (next != null) {
            next.handle(request);
        }
    }
}

public class ChainDemo {
    public static void main(String[] args) {
        RequestHandler auth = new AuthenticationHandler();
        RequestHandler authorization = new AuthorizationHandler();
        RequestHandler logging = new LoggingHandler();

        auth.setNext(authorization);
        authorization.setNext(logging);

        auth.handle("/api/orders");
    }
}