package com.timbuchalka;

import java.util.*;

public class Theater {
    private final String name;
    private List<Seat> seats = new ArrayList<>();

    static final Comparator<Seat> PRICE_ORDER = new Comparator<Seat>() {
        @Override
        public int compare(Seat seat1, Seat seat2) {
            if (seat1.getPrice() < seat2.getPrice()) {
                return 1;
            } else if (seat1.getPrice() > seat2.getPrice()) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    public Theater(String name, int numRows, int seatPerRow) {
        this.name = name;

        int lastRow = 'A' + (numRows - 1);
        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNum = 1; seatNum <= seatPerRow; seatNum++) {
                double price = 12.0;
                if ((row < 'D') && (seatNum >= 4 && seatNum <= 9)) {
                    price = 14.0;
                } else if ((row > 'F') || (seatNum < 4 || seatNum > 9)) {
                    price = 7.0;
                }
                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = new Seat(seatNumber, 0);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);

        if (foundSeat >= 0) {
            return seats.get(foundSeat).reserve();
        } else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
    }

    public boolean cancelSeat(String seatNumber) {
        Seat cancelSeat=new Seat(seatNumber,0);
        int foundSeat = Collections.binarySearch(seats, cancelSeat, null);

        if (foundSeat >= 0) {
            return seats.get(foundSeat).cancel();
        } else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        public boolean reserve() {
            if (!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + this.seatNumber + " reserved");
                return true;
            } else {
                return false;
            }
        }

        public boolean cancel() {
            if (this.reserved) {
                this.reserved = false;
                System.out.println("Reservation of seat " + this.seatNumber + " cancelled");
                return true;
            } else {
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        public double getPrice() {
            return price;
        }
    }
}
