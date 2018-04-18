package domain;

import services.Discount;

public class LoyaltyMember extends StandardMember implements Discount {


    @Override
    public double discount(double rentalFees) {

        double discountedFees = 0.9*rentalFees;
        return discountedFees;
    }
}
