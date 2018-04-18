package domain;



public class StandardMember  {

    private String firstName;
    private String lastName;
    private  double payment;
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



    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public StandardMember() {

    }

    public double addPayment(double newPayment){
        this.payment += newPayment;
        return newPayment;
    }

    public StandardMember(String firstName, String lastName, double payment) {
        id=count++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.payment = payment;
    }

    public StandardMember(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.payment =0.00;
    }

    @Override
    public String toString() {
        return "StandardMember{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", payment=" + payment + + '\'' +
                ", id=" + id +
                '}';
    }
}
