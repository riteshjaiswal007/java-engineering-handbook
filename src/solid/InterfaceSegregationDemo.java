package solid;

public class InterfaceSegregationDemo {

    // Separate focused interfaces
    interface Printer {
        void print();
    }

    interface Scanner {
        void scan();
    }

    interface Fax {
        void fax();
    }

    // Basic printer only supports printing
    static class BasicPrinter implements Printer {

        public void print() {
            System.out.println("Basic printer printing document");
        }
    }

    // Advanced printer supports all operations
    static class AdvancedPrinter
            implements Printer, Scanner, Fax {

        public void print() {
            System.out.println("Advanced printer printing");
        }

        public void scan() {
            System.out.println("Advanced printer scanning");
        }

        public void fax() {
            System.out.println("Advanced printer sending fax");
        }
    }

    public static void main(String[] args) {

        BasicPrinter basicPrinter = new BasicPrinter();

        basicPrinter.print();

        AdvancedPrinter advancedPrinter = new AdvancedPrinter();

        advancedPrinter.print();
        advancedPrinter.scan();
        advancedPrinter.fax();
    }
}
