public class StandardMember  {

    private String firstName;
    private String lastName;
    private  double payment;
    private static int count =1;
    private int id;
    private static double totalPayment;
    private static final double DISCOUNTEDAMOUNT=0.0;



    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }



    public int getId() {
        return id;
    }



    public double getPayment() {


        return payment;
    }

    public static double getTotalPayment() {

        return totalPayment;
    }


    public static double getDISCOUNTEDAMOUNT() {
        return DISCOUNTEDAMOUNT;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }



    public void addPayment(double newPayment){
        this.payment += newPayment;
        totalPayment+=payment;

    }

    public StandardMember(String firstName, String lastName) {
        id=count++;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StandardMember(String firstName, String lastName, double payment) {
        id=count++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.payment = payment;
        totalPayment+=payment;


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
