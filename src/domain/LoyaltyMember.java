package domain;

import services.Discount;

public class LoyaltyMember extends StandardMember implements Discount {
private double discountedAmount;
private double discount;



    public LoyaltyMember(String firstName, String lastName, double payment) {
        super(firstName, lastName, payment);

    }

    @Override
    public double discount(double rentalFees) {
        discount=0.1*getPayment();
        discountedAmount = 0.9*getPayment();
        setPayment(discountedAmount);
        return discountedAmount;

    }
    @Override
    public double getPayment() {
        return super.getPayment();
    }

    @Override
    public void setPayment(double payment) {
        super.setPayment(payment);
    }
}
