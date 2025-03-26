import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    //defines the class (Instance) variables as private
    private String row;
    private int seat;
    private int price;
    private Person person;

    /**
     * Constructs a new Ticket object with the given row, seat number, price, person
     *
     * @param row    the row of the ticket
     * @param seat   the seat number of the ticket
     * @param price  the price of the ticket
     * @param person the person associated with the ticket.
     */
    public Ticket(int row, int seat, int price, Person person) {
        this.row = String.valueOf((char) (row + 64));
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    //getter method for the row
    public String getRow() {
        return row;
    }

    //setter method for the row
    public void setRow(String row) {
        this.row = row;
    }

    //getter method for the price
    public int getPrice() {
        return price;
    }

    //setter method for the price
    public void setPrice(int price) {
        this.price = price;
    }

    //getter method for the seat number
    public int getSeat() {
        return seat;
    }

    //setter method for the seat number
    public void setSeat(int seat) {
        this.seat = seat;
    }

    //getter method for the person
    public Person getPerson() {
        return person;
    }

    //setter method for the person
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Retrieves information about the ticket.
     *
     * @return a formatted string containing the row, seat number, price, and buyer information of the ticket
     */
    public String getTicketInfo() {
        return "Row: " + getRow() + "\n" +
                "Seat: " + getSeat() + "\n" +
                "Price: " + getPrice() + "\n" +
                "Buyer info: \n" +
                getPerson().getInfo();
    }

    /**
     * Prints information about the ticket.
     * Prints a formatted string containing the row, seat number, price, and buyer information of the ticket.
     */
    public void printInfo() {
        System.out.println("-".repeat(10));
        System.out.println(getTicketInfo());
        System.out.println("-".repeat(10));
    }

    /**
     * Saves ticket information to a file.
     * Creates a text file in the "Tickets" directory with the filename derived from the ticket's row and seat number.
     * Writes the ticket information to the file.
     */
    public void save() {
        String fileName = getRow() + getSeat() + ".txt";

        File file = new File("Tickets", fileName);

        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdir();
        }
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(getTicketInfo());
        } catch (IOException e) {
            System.out.println("File not created!!");
        }
    }

    /**
     * Deletes the ticket file associated with the ticket.
     * Deletes the text file in the "tickets" directory with the filename derived from the ticket's row and seat number,
     * if it exists.
     * Prints a message if the file is not found.
     */
    public void deleteFile() {
        String fileName = getRow() + getSeat() + ".txt";

        File file = new File("tickets", fileName);

        if (file.exists()) {
            file.delete();
        } else {
            System.out.println("File not found!!!");
        }
    }
}