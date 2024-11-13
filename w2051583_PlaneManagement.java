import java.util.InputMismatchException;
import java.util.Scanner;
public class w2051583_PlaneManagement {
    static boolean validRow;
    private static int[][] seats_per_row;
    private static Ticket[] soldTickets = new Ticket[52];

    static {

        seats_per_row = new int[4][];
        seats_per_row[0] = new int[14];
        seats_per_row[1] = new int[12];
        seats_per_row[2] = new int[12];
        seats_per_row[3] = new int[14];
    }
    static int ticketCount = 0;


    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application");

        boolean terminate = false;

        while(!terminate) {
            for (int i = 0; i < 50; i++) {
                System.out.print("*");
            }
            System.out.println("\n *                MENU OPTIONS                *");
            for (int i = 0; i < 50; i++) {
                System.out.print("*");
            }
            System.out.println("\n1-Buy a seat\n 2-Cancel a seat\n 3-Find first available seat\n 4-Show seating plan\n 5-Print ticket information and total sales\n 6-Search ticket\n 0-Quit");
            System.out.println("Please select an option:");
            Scanner menu_input = new Scanner(System.in);
            int option = menu_input.nextInt();


            switch (option) {
                case 0:
                    System.out.println("Exited program");
                    terminate = true;
                    break;
                case 1:
                    buy_seat();
                    break;
                case 2:
                    cancel_seat();
                    break;
                case 3:
                    find_first_available(seats_per_row);
                    break;
                case 4:
                    show_seating_plan();
                    break;
                case 5:
                    print_tickets_info();
                    break;
                case 6:
                    search_ticket();
                    break;
                default:
                    System.out.println("Invalid option");

            }
        }

    }



    private static void buy_seat() {
        Scanner input = new Scanner(System.in);
        try {
            char[] rowLetters = {'A', 'B', 'C', 'D'};
            System.out.println("Please enter the row letter:");
            char rowLetterInput = input.next().toUpperCase().charAt(0) ;

            // Validate row letter input
            if (!isValidRow(rowLetterInput)) {
                System.out.println("Invalid row letter.");
                buy_seat();
            }
            else{
                validRow = isValidRow(rowLetterInput);
            }

            // Ask for seat number
            System.out.println("Please enter the seat number:");
            int seatNumber = input.nextInt();

            // Validate seat number
            if (!isValidSeat(rowLetterInput, seatNumber)) {
                System.out.println("Invalid seat number.");
                return;
            }

            // Check seat availability and record the status
            if (seats_per_row[rowLetterInput - 'A'][seatNumber - 1] == 0) {
                // Seat is available, mark it as sold (set to 1)
                seats_per_row[rowLetterInput - 'A'][seatNumber - 1] = 1;
                System.out.println("Seat purchased successfully.");

                System.out.println("Updated seating plan:");
                show_seating_plan();

                //extend buy seat to create ticket here
                double price;
                if (seatNumber < 6) {
                    price = 200; // Price is 200 if seat number is less than 6
                } else if (seatNumber < 10) {
                    price =  180; // Price is 180 if seat number is less than 10
                } else {
                    price = 150; // Price is 150 otherwise
                }
                // Ask for person's information
                System.out.println("Enter passenger details:");
                System.out.print("Name: ");
                String name = input.next();
                System.out.print("Surname: ");
                String surname = input.next();
                System.out.print("Email: ");
                String personal_email = input.next();

                // Create a new Person object
                Person person = new Person(name, surname, personal_email);

                // Create a new Ticket object
                Ticket ticket = new Ticket(rowLetterInput, seatNumber,person);

                // Add the ticket to the array of sold tickets

                soldTickets[ticketCount] = ticket;
                ticketCount++;


            } else {
                System.out.println("Seat is already sold. Please choose a different seat.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index out of bounds: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    private static boolean isValidRow(char rowLetter) {
        if (rowLetter == 'A' || rowLetter == 'B' || rowLetter == 'C'||rowLetter == 'D' ) {
            return true; // Row letter is not A, B, C, or D
        }
        return true;
    }

    private static boolean isValidSeat(char rowLetter, int seatNumber) {
        if (validRow) {
            // Validate seat number
            if (rowLetter == 'A' || rowLetter == 'D') {
                if (seatNumber > 1 && seatNumber < 15) {
                    return true; // Seat number is not between 1 and 14
                }
                else{
                    return false;
                }

            }

            if (rowLetter == 'B' || rowLetter == 'C') {
                if (seatNumber > 1 && seatNumber < 13) {
                    return true; // Seat number is not between 1 and 14
                }
                else{
                    return false;
                }
            }
            else {
                return false;
            }
            // If both row letter and seat number are valid, return true
        }
        else{
            return false;
        }

    }



    private static void cancel_seat() {
        Scanner input = new Scanner(System.in);
        try {
            char[] rowLetters = {'A', 'B', 'C', 'D'};
            System.out.println("Please enter the row letter:");
            char rowLetterInput = input.next().toUpperCase().charAt(0);

            // Validate row letter input
            if (!isValidRow(rowLetterInput)) {
                System.out.println("Invalid row letter.");
                cancel_seat();
            }

            // Ask for seat number
            System.out.println("Please enter the seat number:");
            int seatNumber = input.nextInt();

            // Validate seat number
            if (!isValidSeat(rowLetterInput, seatNumber)) {
                System.out.println("Invalid seat number.");
                cancel_seat();
            }

            if(isValidSeat(rowLetterInput, seatNumber) && isValidRow(rowLetterInput) ){
                for(int i = 0; i <= 52 ; i++){
                    Ticket ticket = soldTickets[i];
                    if( ticket.getRowLetter() == rowLetterInput && ticket.getSeatNo() == seatNumber){
                        soldTickets[i] = null;
                        System.out.println("Cancelled the seat.");
                        break;
                    }
                    else{
                        continue;
                    }
                }
            }

            // Check if the seat is already sold and mark it as available
            if (seats_per_row[rowLetterInput - 'A'][seatNumber - 1] == 1) {
                seats_per_row[rowLetterInput - 'A'][seatNumber - 1] = 0; // Mark seat as available
                System.out.println("Seat canceled successfully.");
            } else {
                System.out.println("Seat is not sold.");
            }

            // Display the updated seating plan
            System.out.println("Updated seating plan:");
            show_seating_plan();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index out of bounds: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    private static void find_first_available(int[][] seats_per_row) {
        char[] rowLetters = {'A', 'B', 'C', 'D'};

        // Iterate through each row
        for (char rowLetter : rowLetters) {
            // Convert row letter to array index
            int rowIndex = rowLetter - 'A';

            // Iterate through each seat in the row
            for (int seatNumber = 0; seatNumber < seats_per_row[rowIndex].length; seatNumber++) {
                // Check if the seat is available (0 indicates available)
                if (seats_per_row[rowIndex][seatNumber] == 0) {
                    // Seat found, print and return
                    System.out.println("First available seat: Row " + rowLetter + ", Seat " + (seatNumber + 1));
                    return;
                }
            }
        }
        // If no available seat found
        System.out.println("Sorry, no available seats.");
    }


   // Anon, (n.d.). Available at: https://chat.openai.com/c/50b099e8-a85a-47dd-9cfd-d7be8a11886a [Accessed 24 Mar. 2024].


    private static void show_seating_plan() {
        char[] rowLetters = {'A', 'B', 'C', 'D'};

        for (int i = 0; i < seats_per_row.length; i++) {
            System.out.print(rowLetters[i] + " ");
            for (int j = 0; j < seats_per_row[i].length; j++) {
                if (seats_per_row[i][j] == 1) {
                    System.out.print("X ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println(); // Move to the next row
        }
    }


    public static void print_tickets_info(){
        boolean isEmpty = true;
        for (Ticket ticket : soldTickets) {
            if (ticket != null) {
                ticket.ticketInfo();
                System.out.println();
            }
        }
        if(isEmpty){
            System.out.println("No tickets have been sold");
        }
        else {


            // Initialize total price variable
            double totalTicketPrice = 0;

            // Iterate over sold tickets to print information and calculate total price
            System.out.println("Ticket information:");
            for (Ticket ticket : soldTickets) {
                System.out.println("Ticket: Row " + ticket.getRowLetter() + ", Seat " + ticket.getSeatNo());
                totalTicketPrice += ticket.getPrice();
            }

            // Print total price
            System.out.println("Total ticket sale: £" + totalTicketPrice);
        }
    }



    public static void search_ticket() {
        Scanner input = new Scanner(System.in);
        try {
            char[] rowLetters = {'A', 'B', 'C', 'D'};
            System.out.println("Please enter the row letter:");
            char rowLetterInput = input.next().toUpperCase().charAt(0);

            // Validate row letter input
            if (!isValidRow(rowLetterInput)) {
                System.out.println("Invalid row letter.");
                return;
            }

            // Ask for seat number
            System.out.println("Please enter the seat number:");
            int seatNumber = input.nextInt();

            // Validate seat number
            if (!isValidSeat(seats_per_row , rowLetterInput, seatNumber)) {
                System.out.println("Invalid seat.");
                return;
            }
            boolean seatFound = false;
            for (Ticket ticket : soldTickets) {
                if (ticket != null && ticket.getRowLetter() == rowLetterInput && ticket.getSeatNo() == seatNumber) {
                    // Ticket found
                    seatFound = true;
                    System.out.println("Ticket information:");
                    System.out.println("Ticket: Row " + ticket.getRowLetter() + ", Seat " + ticket.getSeatNo());
                    System.out.println("Passenger: " + ticket.getPerson().getName() + " " + ticket.getPerson().getSurname());
                    System.out.println("Email: " + ticket.getPerson().getPersonalemail());
                    break;
                }
            }

            if (!seatFound) {
                System.out.println("This seat is available.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }                                                                                                                                                            private static boolean isValidSeat(int[][] seatsPerRow, char rowLetterInput, int seatNumber) {

        return false;
    }
}