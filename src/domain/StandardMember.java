package domain;



public class StandardMember  {

    private String firstName;
    private String lastName;
    private  double rentalFees;
    private static int count =1;
    private int id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }



    public double getRentalFees() {
        return rentalFees;
    }

    public void setRentalFees(double rentalFees) {
        this.rentalFees = rentalFees;
    }

    public StandardMember() {

    }

    public StandardMember(String firstName, String lastName, double rentalFees) {
        id=count++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rentalFees = rentalFees;
    }

    public StandardMember(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.rentalFees=0.00;
    }

    @Override
    public String toString() {
        return "StandardMember{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rentalFees=" + rentalFees + + '\'' +
                ", id=" + id +
                '}';
    }
}
