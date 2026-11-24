package lld;

import java.util.*;

public class ElevatorSystemDemo {

    enum Direction {
        UP, DOWN, IDLE
    }

    enum ElevatorState {
        MOVING, STOPPED, DOOR_OPEN
    }

    enum DoorState {
        OPEN, CLOSED
    }

    // 1. External elevator request
    static class ElevatorRequest {
        private final int sourceFloor;
        private final Direction direction;

        ElevatorRequest(int sourceFloor, Direction direction) {
            this.sourceFloor = sourceFloor;
            this.direction = direction;
        }

        int getSourceFloor() {
            return sourceFloor;
        }

        Direction getDirection() {
            return direction;
        }
    }

    // 2. Elevator
    static class Elevator {

        private final int id;
        private final int capacity;
        private final int maxFloor;

        private int currentFloor;
        private int passengers;

        private Direction direction = Direction.IDLE;
        private ElevatorState state = ElevatorState.STOPPED;
        private DoorState doorState = DoorState.CLOSED;

        private final TreeSet<Integer> stops = new TreeSet<>();

        Elevator(int id, int maxFloor, int capacity) {
            this.id = id;
            this.maxFloor = maxFloor;
            this.capacity = capacity;
            this.currentFloor = 0;
        }

        synchronized void addStop(int floor) {
            validateFloor(floor);
            stops.add(floor);

            if (direction == Direction.IDLE) {
                updateDirection();
            }
        }

        synchronized void boardPassenger() {
            if (passengers >= capacity) {
                throw new IllegalStateException(
                    "Elevator capacity exceeded"
                );
            }

            passengers++;
        }

        synchronized void exitPassenger() {
            if (passengers <= 0) {
                throw new IllegalStateException(
                    "Elevator is empty"
                );
            }

            passengers--;
        }

        // One simulation step
        synchronized void step() {

            if (state == ElevatorState.DOOR_OPEN) {
                doorState = DoorState.CLOSED;
                state = ElevatorState.STOPPED;
                updateDirection();
                return;
            }

            if (stops.isEmpty()) {
                direction = Direction.IDLE;
                state = ElevatorState.STOPPED;
                return;
            }

            if (stops.contains(currentFloor)) {

                stops.remove(currentFloor);

                doorState = DoorState.OPEN;
                state = ElevatorState.DOOR_OPEN;

                System.out.println(
                    "Elevator " + id +
                    " opened door at floor " + currentFloor
                );

                return;
            }

            updateDirection();

            if (direction == Direction.UP) {
                currentFloor++;
            } else if (direction == Direction.DOWN) {
                currentFloor--;
            }

            state = ElevatorState.MOVING;

            System.out.println(
                "Elevator " + id +
                " moving " + direction +
                " to floor " + currentFloor
            );

            if (stops.contains(currentFloor)) {

                stops.remove(currentFloor);

                doorState = DoorState.OPEN;
                state = ElevatorState.DOOR_OPEN;

                System.out.println(
                    "Elevator " + id +
                    " opened door at floor " + currentFloor
                );
            } else if (stops.isEmpty()) {
                direction = Direction.IDLE;
                state = ElevatorState.STOPPED;
            }
        }

        private void updateDirection() {

            if (stops.isEmpty()) {
                direction = Direction.IDLE;
                return;
            }

            if (direction == Direction.UP) {

                Integer next = stops.ceiling(currentFloor);

                if (next != null) {
                    return;
                }

                direction = Direction.DOWN;
            }

            if (direction == Direction.DOWN) {

                Integer next = stops.floor(currentFloor);

                if (next != null) {
                    return;
                }

                direction = Direction.UP;
            }

            if (direction == Direction.IDLE) {

                int nearest = stops.first();

                direction = nearest > currentFloor
                    ? Direction.UP
                    : nearest < currentFloor
                        ? Direction.DOWN
                        : Direction.IDLE;
            }
        }

        private void validateFloor(int floor) {
            if (floor < 0 || floor > maxFloor) {
                throw new IllegalArgumentException(
                    "Invalid floor: " + floor
                );
            }
        }

        synchronized int getCurrentFloor() {
            return currentFloor;
        }

        synchronized Direction getDirection() {
            return direction;
        }

        synchronized int getPendingStops() {
            return stops.size();
        }

        synchronized int getPassengers() {
            return passengers;
        }

        int getId() {
            return id;
        }

        int getCapacity() {
            return capacity;
        }
    }

    // 3. Elevator scheduling strategy
    interface ElevatorSchedulingStrategy {

        Elevator selectElevator(
            List<Elevator> elevators,
            ElevatorRequest request
        );
    }

    // 4. Nearest elevator scheduling
    static class NearestElevatorStrategy
            implements ElevatorSchedulingStrategy {

        @Override
        public Elevator selectElevator(
                List<Elevator> elevators,
                ElevatorRequest request) {

            Elevator best = null;
            int bestScore = Integer.MAX_VALUE;

            for (Elevator elevator : elevators) {

                Direction currentDirection =
                    elevator.getDirection();

                int distance = Math.abs(
                    elevator.getCurrentFloor()
                    - request.getSourceFloor()
                );

                int score = distance
                    + elevator.getPendingStops() * 3;

                // Penalize opposite direction
                if (currentDirection != Direction.IDLE
                    && currentDirection != request.getDirection()) {

                    score += 10;
                }

                if (score < bestScore) {
                    bestScore = score;
                    best = elevator;
                }
            }

            return best;
        }
    }

    // 5. Elevator Controller
    static class ElevatorController {

        private final List<Elevator> elevators;

        private final ElevatorSchedulingStrategy strategy;

        ElevatorController(
                List<Elevator> elevators,
                ElevatorSchedulingStrategy strategy) {

            this.elevators = new ArrayList<>(elevators);
            this.strategy = strategy;
        }

        public synchronized Elevator requestElevator(
                ElevatorRequest request) {

            Elevator elevator = strategy.selectElevator(
                elevators,
                request
            );

            if (elevator == null) {
                throw new IllegalStateException(
                    "No elevator available"
                );
            }

            elevator.addStop(request.getSourceFloor());

            System.out.println(
                "Request assigned to Elevator " +
                elevator.getId()
            );

            return elevator;
        }

        public void stepAllElevators() {

            for (Elevator elevator : elevators) {
                elevator.step();
            }
        }

        public List<Elevator> getElevators() {
            return Collections.unmodifiableList(elevators);
        }
    }

    // 6. Main
    public static void main(String[] args) {

        List<Elevator> elevators = List.of(
            new Elevator(1, 20, 8),
            new Elevator(2, 20, 8),
            new Elevator(3, 20, 8)
        );

        ElevatorController controller =
            new ElevatorController(
                elevators,
                new NearestElevatorStrategy()
            );

        // Passenger requests elevator at floor 5
        Elevator assigned = controller.requestElevator(
            new ElevatorRequest(5, Direction.UP)
        );

        // Simulate movement until pickup
        for (int i = 0; i < 10; i++) {
            controller.stepAllElevators();

            if (assigned.getCurrentFloor() == 5
                && assigned.getDirection() != Direction.IDLE) {
                break;
            }
        }

        // Passenger boards
        assigned.boardPassenger();

        // Passenger selects destination floor 10
        assigned.addStop(10);

        System.out.println(
            "Passenger boarded Elevator " +
            assigned.getId()
        );

        // Simulate travel to destination
        for (int i = 0; i < 15; i++) {
            controller.stepAllElevators();

            if (assigned.getCurrentFloor() == 10
                && assigned.getDirection() == Direction.IDLE
                && assigned.getPendingStops() == 0) {
                break;
            }
        }

        assigned.exitPassenger();

        System.out.println(
            "Passenger exited at floor 10"
        );
    }
}
