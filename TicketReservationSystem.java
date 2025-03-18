class TicketNode {
    int ticketId;
    String customerName;
    String movieName;
    int seatNumber;
    String bookingTime;
    TicketNode next;

    public TicketNode(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = this;
    }
}

class TicketReservationCLL {
    TicketNode head;
    TicketNode tail;

    void addTicket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        TicketNode node = new TicketNode(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.next = head;
            tail = node;
        }
    }

    void removeTicket(int ticketId) {
        if (head == null) return;
        TicketNode temp = head;
        TicketNode prev = tail;

        do {
            if (temp.ticketId == ticketId) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    void displayAllTickets() {
        if (head == null) return;
        TicketNode node = head;
        do {
            displayDetails(node);
            node = node.next;
        } while (node != head);
    }

    void displayDetails(TicketNode node) {
        System.out.println("Ticket ID: " + node.ticketId);
        System.out.println("Customer Name: " + node.customerName);
        System.out.println("Movie Name: " + node.movieName);
        System.out.println("Seat Number: " + node.seatNumber);
        System.out.println("Booking Time: " + node.bookingTime);
        System.out.println("----------------------------");
    }

    TicketNode searchByCustomer(String customerName) {
        if (head == null) return null;
        TicketNode node = head;
        do {
            if (node.customerName.equalsIgnoreCase(customerName)) {
                return node;
            }
            node = node.next;
        } while (node != head);
        return null;
    }

    TicketNode searchByMovie(String movieName) {
        if (head == null) return null;
        TicketNode node = head;
        do {
            if (node.movieName.equalsIgnoreCase(movieName)) {
                return node;
            }
            node = node.next;
        } while (node != head);
        return null;
    }

    int countTotalTickets() {
        if (head == null) return 0;
        int count = 0;
        TicketNode node = head;
        do {
            count++;
            node = node.next;
        } while (node != head);
        return count;
    }
}

public class TicketReservationSystem {
    public static void main(String[] args) {
        TicketReservationCLL reservationSystem = new TicketReservationCLL();

        reservationSystem.addTicket(1, "John Doe", "Movie A", 12, "18:30");
        reservationSystem.addTicket(2, "Jane Smith", "Movie B", 15, "19:00");
        reservationSystem.addTicket(3, "Alice Brown", "Movie A", 22, "18:30");

        System.out.println("All Booked Tickets:");
        reservationSystem.displayAllTickets();

        System.out.println("\nSearching for a ticket by Customer Name (Jane Smith):");
        TicketNode foundTicket = reservationSystem.searchByCustomer("Jane Smith");
        if (foundTicket != null) {
            reservationSystem.displayDetails(foundTicket);
        } else {
            System.out.println("No ticket found for the customer.");
        }

        System.out.println("\nRemoving Ticket ID 2");
        reservationSystem.removeTicket(2);
        reservationSystem.displayAllTickets();

        System.out.println("\nTotal number of booked tickets: " + reservationSystem.countTotalTickets());


//        All Booked Tickets:
//        Ticket ID: 1
//        Customer Name: John Doe
//        Movie Name: Movie A
//        Seat Number: 12
//        Booking Time: 18:30
//                ----------------------------
//        Ticket ID: 2
//        Customer Name: Jane Smith
//        Movie Name: Movie B
//        Seat Number: 15
//        Booking Time: 19:00
//                ----------------------------
//        Ticket ID: 3
//        Customer Name: Alice Brown
//        Movie Name: Movie A
//        Seat Number: 22
//        Booking Time: 18:30
//                ----------------------------
//
//        Searching for a ticket by Customer Name (Jane Smith):
//        Ticket ID: 2
//        Customer Name: Jane Smith
//        Movie Name: Movie B
//        Seat Number: 15
//        Booking Time: 19:00
//                ----------------------------
//
//        Removing Ticket ID 2
//        Ticket ID: 1
//        Customer Name: John Doe
//        Movie Name: Movie A
//        Seat Number: 12
//        Booking Time: 18:30
//                ----------------------------
//        Ticket ID: 3
//        Customer Name: Alice Brown
//        Movie Name: Movie A
//        Seat Number: 22
//        Booking Time: 18:30
//                ----------------------------
//
//        Total number of booked tickets: 2
    }
}
