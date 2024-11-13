import java.io.FileWriter;
import java.io.IOException;
public class Ticket {

    private char rowLetter;
    private int seatNo;
    private Person person;


    // Constructor and other methods
    public Ticket(char rowLetter, int seatNo, Person person){
        this.rowLetter = rowLetter;
        this.seatNo = seatNo;
        this.person = person;
    }

    public void ticketInfo(){
        System.out.println("Name: " + this.person.getName());
        System.out.println("Surname: " + this.person.getSurname());
        System.out.println("Email: " + this.person.getPersonalemail());
        System.out.println("RowLetter: " + this.rowLetter);
        System.out.println("Seat number: " + this.seatNo);
    }
    public void save_info() {
        // Generate the file name based on the row and seat number
        String fileName = rowLetter + seatNo + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            // Write ticket information to the file
            writer.write("Ticket Information:\n");
            writer.write("Row: " + rowLetter + "\n");
            writer.write("Seat Number: " + seatNo + "\n");
            writer.write("First Name: " + person.getName() + "\n");
            writer.write("Surname: " + person.getSurname() + "\n");
            writer.write("Email" + person.getPersonalemail() + "\n");
            writer.close();
// Closed the file
            System.out.println("Ticket information saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error occurred : " + e.getMessage());
        }
    }

    public int getPrice() {
        if (seatNo < 6) {
            return 200; // Price is 200 if seat number is less than 6
        } else if (seatNo < 10) {
            return 180; // Price is 180 if seat number is less than 10
        } else {
            return 150; // Price is 150 otherwise
        }
    }

    public char getRowLetter() {
        return rowLetter;
    }

    public void setRowLetter(char rowLetter) {
        return;
    }
    public int getSeatNo(){
        return seatNo;
    }
    public void setSeatNo(){
        return;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    // Getters for rowLetter and seatNumber
}









