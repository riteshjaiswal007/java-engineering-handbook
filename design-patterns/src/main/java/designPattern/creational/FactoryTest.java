package designPattern.creational;


interface NotificationService{
    void send();
}
class EmailNotificationService implements NotificationService{

    @Override
    public void send() {
        System.out.println("Sent email");
    }
}
class SMSNotificationService implements NotificationService{

    @Override
    public void send() {
        System.out.println("Sent Sms");
    }
}

abstract class NotificationCreator{
     abstract NotificationService createNotification();
    public void send() {
        NotificationService s= createNotification() ;
        s.send();
    }

}
class EmailNotificationCreator extends NotificationCreator{
    @Override
    NotificationService createNotification() {
        return new EmailNotificationService();
    }
}
class SMSNotificationCreator extends NotificationCreator{
    @Override
    NotificationService createNotification() {
        return new SMSNotificationService();
    }
}
public class FactoryTest {

    public static void main(String[] args){
        NotificationCreator c=new EmailNotificationCreator();
        c.send();
        NotificationCreator c1=new SMSNotificationCreator();
        c1.send();
    }

}
