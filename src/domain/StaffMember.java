package domain;

import services.Discount;

public class StaffMember extends StandardMember implements Discount {

    public StaffMember() {
    }

    public StaffMember(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public double discount( double  rentalFee) {

        double discountedFee = 0.80* rentalFee;

        return discountedFee;
    }
}
