package designPattern.behavioural;

abstract class DataProcessor {

    // Template method
/*    Purpose: Defines the skeleton of an algorithm in a base class while
allowing subclasses to implement specific steps.
Real-world example: Different types of reports follow the same generation
process but use different data formats.*/
    public final void process() {
        readData();
        processData();
        saveData();
    }

    abstract void readData();
    abstract void processData();

    void saveData() {
        System.out.println("Saving processed data");
    }
}

class CsvProcessor extends DataProcessor {
    void readData() {
        System.out.println("Reading CSV");
    }

    void processData() {
        System.out.println("Processing CSV");
    }
}

class JsonProcessor extends DataProcessor {
    void readData() {
        System.out.println("Reading JSON");
    }

    void processData() {
        System.out.println("Processing JSON");
    }
}

public class TemplateMethodDemo {
    public static void main(String[] args) {
        DataProcessor processor = new CsvProcessor();
        processor.process();

        processor = new JsonProcessor();
        processor.process();
    }
}