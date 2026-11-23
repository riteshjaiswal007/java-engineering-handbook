package designPattern.creational;

public class SingletonTest {

// example Cache manager, logger, AppConfig, database connection,

    // 1- eager initialization
//    private static  final SingletonTest instance=new SingletonTest();
//
//    public SingletonTest getInstance(){
//        return instance;
//    }

    //2- lazy initialization and thread safe

    private static SingletonTest instance;

    public SingletonTest getInstance() {

        if (instance == null) {
            synchronized (SingletonTest.this) {
                if (instance == null)
                    instance = new SingletonTest();
            }
        }
        return instance;
    }

}
