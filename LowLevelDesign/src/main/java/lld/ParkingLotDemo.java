package lld;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ParkingLotDemo {

    // 1. Vehicle types
    enum VehicleType {
        BIKE, CAR, TRUCK
    }

    // 2. Parking spot types
    enum SpotType {
        MOTORCYCLE, COMPACT, LARGE
    }

    // 3. Vehicle
    static class Vehicle {
        private final String number;
        private final VehicleType type;

        Vehicle(String number, VehicleType type) {
            this.number = number;
            this.type = type;
        }

        public String getNumber() {
            return number;
        }

        public VehicleType getType() {
            return type;
        }
    }

    // 4. Parking spot
    static class ParkingSpot {
        private final String id;
        private final SpotType type;
        private Vehicle vehicle;

        ParkingSpot(String id, SpotType type) {
            this.id = id;
            this.type = type;
        }

        boolean isAvailable() {
            return vehicle == null;
        }

        boolean canFit(Vehicle vehicle) {
            return switch (vehicle.getType()) {
                case BIKE -> true;
                case CAR -> type == SpotType.COMPACT
                         || type == SpotType.LARGE;
                case TRUCK -> type == SpotType.LARGE;
            };
        }

        void park(Vehicle vehicle) {
            if (!isAvailable() || !canFit(vehicle)) {
                throw new IllegalStateException(
                    "Spot is not available or compatible"
                );
            }
            this.vehicle = vehicle;
        }

        void removeVehicle() {
            this.vehicle = null;
        }

        String getId() {
            return id;
        }
    }

    // 5. Parking floor
    static class ParkingFloor {
        private final int floorNumber;
        private final List<ParkingSpot> spots;

        ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
            this.floorNumber = floorNumber;
            this.spots = new ArrayList<>(spots);
        }

        ParkingSpot findAvailableSpot(Vehicle vehicle) {
            for (ParkingSpot spot : spots) {
                if (spot.isAvailable() && spot.canFit(vehicle)) {
                    return spot;
                }
            }
            return null;
        }

        int getFloorNumber() {
            return floorNumber;
        }
    }

    // 6. Parking ticket
    static class ParkingTicket {
        private final String ticketId;
        private final Vehicle vehicle;
        private final ParkingSpot spot;
        private final int floorNumber;
        private final Instant entryTime;

        private Instant exitTime;

        ParkingTicket(
                String ticketId,
                Vehicle vehicle,
                ParkingSpot spot,
                int floorNumber) {

            this.ticketId = ticketId;
            this.vehicle = vehicle;
            this.spot = spot;
            this.floorNumber = floorNumber;
            this.entryTime = Instant.now();
        }

        void close() {
            if (exitTime != null) {
                throw new IllegalStateException(
                    "Ticket already closed"
                );
            }
            exitTime = Instant.now();
        }

        public String getTicketId() {
            return ticketId;
        }

        public Vehicle getVehicle() {
            return vehicle;
        }

        public ParkingSpot getSpot() {
            return spot;
        }

        public Instant getEntryTime() {
            return entryTime;
        }

        public Instant getExitTime() {
            return exitTime;
        }

        public int getFloorNumber() {
            return floorNumber;
        }
    }

    // 7. Parking allocation strategy
    interface ParkingStrategy {
        ParkingSpot findSpot(
            List<ParkingFloor> floors,
            Vehicle vehicle
        );
    }

    static class FirstAvailableStrategy implements ParkingStrategy {

        @Override
        public ParkingSpot findSpot(
                List<ParkingFloor> floors,
                Vehicle vehicle) {

            for (ParkingFloor floor : floors) {
                ParkingSpot spot = floor.findAvailableSpot(vehicle);

                if (spot != null) {
                    return spot;
                }
            }

            return null;
        }
    }

    // 8. Pricing strategy
    interface PricingStrategy {
        double calculateFee(ParkingTicket ticket);
    }

    static class HourlyPricingStrategy
            implements PricingStrategy {

        private final Map<VehicleType, Double> hourlyRates =
            Map.of(
                VehicleType.BIKE, 10.0,
                VehicleType.CAR, 30.0,
                VehicleType.TRUCK, 50.0
            );

        @Override
        public double calculateFee(ParkingTicket ticket) {

            Instant exitTime = ticket.getExitTime();

            if (exitTime == null) {
                throw new IllegalStateException(
                    "Ticket is not closed"
                );
            }

            long minutes = Math.max(
                0,
                Duration.between(
                    ticket.getEntryTime(),
                    exitTime
                ).toMinutes()
            );

            long hours = Math.max(
                1,
                (minutes + 59) / 60
            );

            return hours * hourlyRates.get(
                ticket.getVehicle().getType()
            );
        }
    }

    // 9. Parking lot
    static class ParkingLot {

        private final List<ParkingFloor> floors;
        private final ParkingStrategy parkingStrategy;
        private final PricingStrategy pricingStrategy;

        private final Map<String, ParkingTicket> activeTickets =
            new HashMap<>();

        private final AtomicLong ticketSequence =
            new AtomicLong(1000);

        ParkingLot(
                List<ParkingFloor> floors,
                ParkingStrategy parkingStrategy,
                PricingStrategy pricingStrategy) {

            this.floors = new ArrayList<>(floors);
            this.parkingStrategy = parkingStrategy;
            this.pricingStrategy = pricingStrategy;
        }

        // Park vehicle
        public synchronized ParkingTicket parkVehicle(
                Vehicle vehicle) {

            if (activeTickets.values().stream().anyMatch(
                    ticket -> ticket.getVehicle()
                        .getNumber().equals(vehicle.getNumber()))) {
                throw new IllegalStateException(
                    "Vehicle is already parked"
                );
            }

            for (ParkingFloor floor : floors) {

                ParkingSpot spot =
                    floor.findAvailableSpot(vehicle);

                if (spot != null) {

                    spot.park(vehicle);

                    String ticketId =
                        "T-" + ticketSequence.incrementAndGet();

                    ParkingTicket ticket = new ParkingTicket(
                        ticketId,
                        vehicle,
                        spot,
                        floor.getFloorNumber()
                    );

                    activeTickets.put(ticketId, ticket);

                    return ticket;
                }
            }

            throw new IllegalStateException(
                "No compatible parking spot available"
            );
        }

        // Unpark vehicle and calculate fee
        public synchronized double unparkVehicle(
                String ticketId) {

            ParkingTicket ticket = activeTickets.get(ticketId);

            if (ticket == null) {
                throw new IllegalArgumentException(
                    "Invalid or already closed ticket"
                );
            }

            ticket.close();

            double fee = pricingStrategy.calculateFee(ticket);

            ticket.getSpot().removeVehicle();

            activeTickets.remove(ticketId);

            return fee;
        }

        public synchronized int getActiveVehicleCount() {
            return activeTickets.size();
        }
    }

    // 10. Main method
    public static void main(String[] args) {

        ParkingFloor floor1 = new ParkingFloor(
            1,
            List.of(
                new ParkingSpot("M1", SpotType.MOTORCYCLE),
                new ParkingSpot("C1", SpotType.COMPACT),
                new ParkingSpot("L1", SpotType.LARGE)
            )
        );

        ParkingFloor floor2 = new ParkingFloor(
            2,
            List.of(
                new ParkingSpot("C2", SpotType.COMPACT),
                new ParkingSpot("L2", SpotType.LARGE)
            )
        );

        ParkingLot parkingLot = new ParkingLot(
            List.of(floor1, floor2),
            new FirstAvailableStrategy(),
            new HourlyPricingStrategy()
        );

        Vehicle car = new Vehicle("KA01AB1234", VehicleType.CAR);
        Vehicle bike = new Vehicle("KA01CD5678", VehicleType.BIKE);

        ParkingTicket carTicket =
            parkingLot.parkVehicle(car);

        ParkingTicket bikeTicket =
            parkingLot.parkVehicle(bike);

        System.out.println(
            "Car Ticket: " + carTicket.getTicketId()
        );

        System.out.println(
            "Car Spot: " + carTicket.getSpot().getId()
        );

        System.out.println(
            "Bike Ticket: " + bikeTicket.getTicketId()
        );

        System.out.println(
            "Active vehicles: " +
            parkingLot.getActiveVehicleCount()
        );

        double fee =
            parkingLot.unparkVehicle(carTicket.getTicketId());

        System.out.println("Car parking fee: " + fee);

        System.out.println(
            "Active vehicles: " +
            parkingLot.getActiveVehicleCount()
        );
    }
}
