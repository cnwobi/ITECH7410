package domain;

import services.Discount;

public class StaffMember extends StandardMember implements Discount {
    private double discountedPayment;
    private double discount;
    private static double totalDiscountedPayment;
    private static double totalDiscountGiven;

    public StaffMember(String firstName, String lastName, double payment) {
        super(firstName, lastName);
        super.setPayment(payment);
        discount();
    }

    @Override
    public void discount() {
        // calculate discount according to business rule
        discount += 0.2 * getPayment();
        //calculate total discount for all instances
        totalDiscountGiven += discount;

        discountedPayment += 0.8 * getPayment();
        totalDiscountedPayment += discountedPayment;
        super.setPayment(discountedPayment);


    }

    @Override
    public double getPayment() {
        return super.getPayment();
    }


    public double getDiscount() {
        return discount;
    }

    @Override
    public void addPayment(double newPayment) {
        discount += 0.2 * newPayment;
        totalDiscountGiven += discount;
        discountedPayment += 0.8 * newPayment;
        totalDiscountedPayment += discountedPayment;
        super.setPayment(discountedPayment);
    }

    public double getDiscountedPayment() {
        return discountedPayment;
    }

    public static double getTotalDiscountGiven() {
        return totalDiscountGiven;
    }

    public static double getTotalDiscountedPayment() {
        return totalDiscountedPayment;
    }
}
