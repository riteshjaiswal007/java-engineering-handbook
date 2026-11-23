package designPattern.structural;

/*3. Proxy Pattern
Purpose: Provides a substitute object that controls access to the real object.
Real-world example: Checking authorization before accessing a sensitive service.*/
interface DocumentService {
    void readDocument();
}

class RealDocumentService implements DocumentService {
    public void readDocument() {
        System.out.println("Reading confidential document");
    }
}

class DocumentProxy implements DocumentService {
    private final RealDocumentService service;
    private final boolean authorized;

    DocumentProxy(boolean authorized) {
        this.authorized = authorized;
        this.service = new RealDocumentService();
    }

    public void readDocument() {
        if (authorized) {
            service.readDocument();
        } else {
            System.out.println("Access denied");
        }
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        DocumentService service = new DocumentProxy(false);
        service.readDocument();
    }
}