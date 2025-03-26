/*I declare that my work contains no examples of misconduct, such as plagiarism, or collusion.
Any code taken from other sources is referenced within my code solution.
Student ID -> IIT ID : 20231922  -> UoW ID :  w2052191
Name : H.B.K.W.K.Herath <Kisara.20231922@iit.ac.lk> <w2052191@westminster.ac.uk>
Date : 22-03-2024*/


import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaneManagement {
    //creating the seat array
    public static final int[][] seat = {new int[14], new int[12], new int[12], new int[14]};
    //constant for Number of Rows
    private static final int NUMBER_OF_ROWS = 4;
    //constant for Number of seats of the edge rows
    private static final int NUMBER_OF_SEATS_MIDDLE_ROWS =12;

    // constant for Number of seats of the middle rows
    private static final int NUMBER_OF_SEATS_EDGE_ROWS = 14;
    static Scanner sc = new Scanner(System.in);
    public static int totalPrice = 0;
    //creating the ticket array
    private  static final Ticket[][] purchasedTickets =  {new Ticket[14], new Ticket[12], new Ticket[12], new Ticket[14]};

    /**
    *Prompts the user to enter a numeric input with a given prompt message.
    * If the input is not a valid integer, it displays the error message and continues prompting until a valid input is received.
    *
    * @param prompt the message to prompt the user for input
    * @param errMessage the error message to display the input is not valid.
    * @return the  numeric input provided by the user. **/
    public static int getNumericInput(String prompt, String errMessage) {
        while (true) {
            System.out.print(prompt);

            try {
                return sc.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println(errMessage);
                sc.nextLine();
            }
        }
    }

   /**
   * Prompts the user to enter a string input with given prompt message.
   * If the input is not a valid string ,it displays the error message and continues prompting until a valid input is received.
   *
   * @param prompt the message to the prompt the user for input
   * @param errMessage the error message to display if the input is not valid
   * @return the string input provided by the user **/

    public static String getStringInput(String prompt, String errMessage) {
        while (true) {
            System.out.print(prompt);

            try {
                return sc.next();
            } catch (InputMismatchException ex) {
                System.out.println(errMessage);
                sc.nextLine();
            }
        }
    }

    public static void deleteTicketsDirectory(){
        File ticketsDir = new File("Tickets");

        if (!ticketsDir.exists()) return;

        try {
            String[] subFiles = ticketsDir.list();
            for (String filename : subFiles) {
                File subFile = new File(ticketsDir.getPath(), filename);
                subFile.delete();
            }
        }catch (NullPointerException e){
            System.out.println();
        }
    }

    public static void main(String[] args) {
        deleteTicketsDirectory();
        System.out.println();
        System.out.println("Welcome to the Plane Management application");

        int choice;
        while (true) {
            printMenu();
            choice = getNumericInput("Please select an option: ", "Enter valid option");

            if (choice == 0) {
                System.out.println("Quit");
                break;
            }
            switch (choice) {
                case 1: // Buy seat option
                    System.out.println("Buy a seat\n" + ("-".repeat(10)));
                    buySeat();
                    break;
                case 2:
                    System.out.println("Cancel a seat\n" + ("-".repeat(10)));
                    cancelSeat();
                    break;
                case 3:
                    System.out.println("Get first available seat\n" + ("-".repeat(10)));
                    getFirstSeatAvailable();
                    break;
                case 4:
                    System.out.println("Seating plan\n" + ("-".repeat(10)));
                    seatingPlan();
                    break;
                case 5:
                    System.out.println("Print all tickets and total sales price\n" + ("-".repeat(10)));
                    printTicketInfo();
                    System.out.println("Total price of sold tickets: £" + totalPrice);
                    break;
                case 6:
                    System.out.println("Search for a ticket\n" + ("-".repeat(10)));
                    searchTickets();
                    break;
                default:
                    System.out.println("Enter again");
            }
        }
    }
    //Prints the menu option for seating plan management system
    public static void printMenu(){

        System.out.println("*".repeat(51));
        System.out.println("*" + " ".repeat(19) + "Menu Option" + " ".repeat(19) + "*");
        System.out.println("*".repeat(51));

        System.out.println("\t1) Buy a seat");
        System.out.println("\t2) Cancel a seat");
        System.out.println("\t3) Find first available seat");
        System.out.println("\t4) Show seating plan");
        System.out.println("\t5) Print tickets information and total sales");
        System.out.println("\t6) Search tickets");
        System.out.println("\t0) Quit");
    }

    /**
    * Prompts the user to enter an alphabetic string input that taken from getStringInput() method
    * If the input contains non-alphabetic characters  (excluding '.'), it displays the error message and continues prompting until a valid input is received.
    *
    * @param prompt teh message to prompt the user for input
    * @param errMessage the error message to display if yje input is not valid.
    * @return the alphabetic string input provided by the user   **/
    public static String getAlphabeticInput(String prompt, String errMessage){
        boolean isInputValid;
        while(true){
            isInputValid = true;
            String input = getStringInput(prompt, errMessage);

            for (int i = 0; i < input.length(); i++) {
                char letter = input.toUpperCase().charAt(i);
                if(!((letter >= 'A' && letter <= 'Z'))){
                    isInputValid = false;
                    break;
                }
            }
            if(isInputValid) return input;
            System.out.println(errMessage);
        }
    }

    /**
    * Prompt the user to enter the row for seat (A - D) and returns the corresponding row number
    * continuously prompt until a valid row input provided.
    *
    * @return the row number of the selected seat.**/
    public static int getRow(){
        while(true) {
            //Getting the seat row from user
            String row = getAlphabeticInput("Enter seat row(A - D): ", "Invalid input!").toUpperCase();

            //converting the row(String) into integer(int)
            int rowNum = row.charAt(0);

            if(rowNum >= '0' && rowNum <= '9'){
                System.out.println("Invalid input!");
                continue;
            }

            rowNum -= 'A' -1;   //64 - ASCII value of A
            if (!(rowNum < 1 || rowNum > NUMBER_OF_ROWS)) return rowNum;

            System.out.println("Invalid seat row!!!");
        }
    }

    /**
    * Prompts the user to enter the seat number based on the given row number.
    * Continuously prompts until a valid seat number input is provided according ti the seating arrangement.
    *
    * @param rowNum the row number of the selected seat
    * @return the seat number of the selected seat.
     **/
    public static int getSeatNum(int rowNum){
        int seatNum;
        while(true) {
            seatNum = getNumericInput("Enter seat number\n{A,D (1-14) | B,C (1-12)}: ", "Invalid input!");

            if (seatNum < 1 || seatNum > NUMBER_OF_SEATS_EDGE_ROWS) {
                System.out.println("Invalid seat selection!!, Enter between 1 - 14 ");
                continue;
            }

            if (rowNum  == 2 || rowNum == 3) {
                if (seatNum > NUMBER_OF_SEATS_MIDDLE_ROWS) {
                    System.out.println("Invalid seat selection!!, Enter between 1 - 12");
                    continue;
                }
                break;
            }
            break;
        }

        return seatNum;
    }

    /**
     * Prompts the user to enter personal information including first name, surname and email.
     * Continuously prompts until a valid inputs are provided for each field
     *
     * @return  a person object containing teh user's information.**/
    private static Person getPerson() {
        //Getting user's First Name
        String firstName = getAlphabeticInput("Enter your first name: ", "Invalid Input!").toUpperCase();
        //Getting user's Surname
        String surname = getAlphabeticInput("Enter your surname: ", "Invalid Input!").toUpperCase();

        String email;
        while (true) {
            System.out.print("Enter your email: ");
            //Getting user's email
            //email = sc.next().toLowerCase();
            email = getStringInput("Enter your email: ", "Invalid input: ").toLowerCase();

            //check the email whether it contains "@"
            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Enter a valid email!!!");
                continue;
            }
            //check the email whether it contains "."
            if (email.contains("@.")) {
                System.out.println("Check again!!");
                continue;
            }
            break;
        }
        //returning values
        return new Person(firstName, surname, email);
    }

    /**
     * Facilitates the process of buying a saat by prompting the user to select row and seat number.
     * Checking if the seat is available, reserving the seat, collecting buyer's information and confirming the purchase.
     * Continuously prompts until a sear is successfully purchased.
     **/
    public static void buySeat() {
        while(true) {
            try {
                int row = getRow();
                int seatNum = getSeatNum(row);

                if (seat[row - 1][seatNum - 1] == 1) {
                    System.out.println("-".repeat(10));
                    System.out.println("Seat already sold.");
                    continue;
                }

                seat[row - 1][seatNum - 1] = 1;
                System.out.println("-".repeat(10));

                System.out.println("Seat is available");
                totalPrice += getPrice(seatNum);
                System.out.println("Price of the ticket: £" + getPrice(seatNum));
                System.out.println("-".repeat(10));

                Person buyer = getPerson();
                reserveTicket(buyer, row, seatNum);
                System.out.println("-".repeat(10));

                System.out.println("Seat sold successfully");
                break;

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Enters an invalid seat number!!!");
            }
        }
    }

    /**
     * Facilitates the process of canceling a saat by prompting the user to select row and seat number.
     * Checking if the seat is sold, canceling the seat, deleting the corresponding ticket file.
     * Updating the purchasedTickets array, and deducting the prices if the cancellation is successful.
     * Continuously prompts until a sear is successfully canceled.
     **/

    public static void cancelSeat(){
        while(true) {
            try {
                int row = getRow();
                int seatNum = getSeatNum(row);

                if (seat[row - 1][seatNum - 1] == 0) {
                    System.out.println("-".repeat(10));
                    System.out.println("Can't cancel the seat. Seat not sold.");
                } else {
                    seat[row - 1][seatNum - 1] = 0;

                    purchasedTickets[row - 1][seatNum - 1].deleteFile();
                    purchasedTickets[row - 1][seatNum - 1] = null;

                    System.out.println("-".repeat(10));
                    System.out.println("Seat canceled successfully");

                    totalPrice -= getPrice(seatNum);
                    System.out.println("-".repeat(10));
                }
                break;
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Seat number out of range!!!");
            }
        }
    }

    /**
     * Finds the first available seat in the seating plan.
     *prints the seat number of the first available seat in the specified row if found.
     * Prints a message indicating all the seats are booked in the ro if no available seats are found*/
    public static void getFirstSeatAvailable(){
        int seatNumber;
        boolean seatFound = false;
        int row = getRow();//set the seatFound boolean to value false
        for (seatNumber = 0; seatNumber < seat[row - 1].length; seatNumber++) {
                if (seat[row-1][seatNumber] == 0) {
                    seatFound = true;
                    break;
                }
            }
        if (!seatFound) {
            System.out.println("All seats are booked in row: " + row);
            return;
        }
        System.out.println("-".repeat(10));
        char rowLetter = (char)(row + 64);
        System.out.println("First available seat is: "+ rowLetter+ (seatNumber + 1));
        System.out.println("-".repeat(10));
        }

        /**
         * Displays the availability of seats in the seating plan
         *Prints " X" for seats that are sold (reserved) and " O" for seats that are available
         */
    public static void seatingPlan() {
        for (int[] seatRow : seat) {
            for (int seat : seatRow) {
                System.out.print(seat == 1 ? " X" : " O");
            }
            System.out.println();
        }
    }
    /**
     * Retrieving the price for a given seat number.
     * @param seatNum the seat number for which to retrieve the price
     * @return the price of the seat
     */
    private static int getPrice(int seatNum) {
        int price = 0;
        if (seatNum > 0 && seatNum < 6) {
            price = 200;
        } else if (seatNum > 5 && seatNum < 10) {
            price = 150;
        } else if (seatNum > 9 && seatNum < 15) {
            price = 180;
        }
        return price;
    }

    /**
     * Searches for a ticket based on the selected row and seat number.
     * If the seat is sols, prints the ticket information.
     * If the seat is available, indicates that teh seat is available.
     * Handles  the ArrayOutOfBound Exception for invalid seat number
     */
    public static void searchTickets(){
        try {
            int row = getRow();
            int seatNum = getSeatNum(row);
            System.out.println("-".repeat(10));

            if (seat[row - 1][seatNum - 1] == 1) {
                System.out.println("Seat not available");
                Ticket ticket = purchasedTickets[row - 1][seatNum - 1];
                ticket.printInfo();
            } else {
                System.out.println("Seat is available");
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Seat Number out of range!!!");
        }
    }

    /**
     * reserves a ticket for a person at thee specified row and seat number.
     * Creates a Ticket object with the provided information , adds it to the purchasedTickets array.
     * and saves the ticket information into a text file.
     * @param person the peron for whom to reserve the ticket
     * @param row the row of the seat to reserve.
     * @param seatNum the seat number to reserve.
     */
    public static void reserveTicket(Person person, int row, int seatNum){
        Ticket ticket = new Ticket(row, seatNum, getPrice(seatNum), person);
        purchasedTickets[row - 1][seatNum - 1] = ticket;
        ticket.save();
    }

    /**
     * Prints information for all purchased tickets.
     *Iterates through teh purchasedTickets array and prints information for each npn-null ticket.
     */
    public static void printTicketInfo() {
        for (Ticket[] ticketRow : purchasedTickets) {
            for (Ticket ticket : ticketRow) {
                if (ticket != null) {
                    ticket.printInfo();
                }
            }
        }
    }
}