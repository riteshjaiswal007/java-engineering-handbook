package solid;

public class LiskovSubstitutionDemo {

    // Base abstraction
    interface Bird {
        void eat();
    }

    // Only birds capable of flying implement this interface
    interface FlyingBird extends Bird {
        void fly();
    }

    // Eagle can fly
    static class Eagle implements FlyingBird {

        public void eat() {
            System.out.println("Eagle is eating");
        }

        public void fly() {
            System.out.println("Eagle is flying");
        }
    }

    // Ostrich cannot fly
    static class Ostrich implements Bird {

        public void eat() {
            System.out.println("Ostrich is eating");
        }
    }

    // Works with any Bird
    static class BirdService {

        public void feedBird(Bird bird) {
            bird.eat();
        }

        public void makeBirdFly(FlyingBird bird) {
            bird.fly();
        }
    }

    public static void main(String[] args) {

        BirdService birdService = new BirdService();

        Bird eagle = new Eagle();
        Bird ostrich = new Ostrich();

        birdService.feedBird(eagle);
        birdService.feedBird(ostrich);

        FlyingBird flyingBird = new Eagle();
        birdService.makeBirdFly(flyingBird);
    }
}
