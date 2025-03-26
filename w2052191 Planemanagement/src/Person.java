public class Person {
    //defines the class (Instance) variables as private
    private String firstName;
    private String surname;
    private String email;

    /**
     * Constructs a new Person object with the given first name, surname, email
     * @param firstName the first name of the person
     * @param surname the surname of the person
     * @param email the email of the person
     */
    public Person(String firstName, String surname, String email){
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
    }

    //Setter method for first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //getter method for first name
    public String getFirstName() {
        return firstName;
    }

    //getter method for surname
    public String getSurname() {
        return surname;
    }

    //setter method for surname
    public void setSurname(String surname) {
        this.surname = surname;
    }

    //getter method for email
    public String getEmail() {
        return email;
    }

    //setter method for email
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves information about the user.
     * @return a formatted string containing the user's first name, surname, email.
     */
    public String getInfo() {                                                               //getting person information via getters
        return "First name: " + getFirstName() + "\n" +
                "Surname: " + getSurname() + "\n" +
                "Email: " + getEmail();
    }
}