public class LoyaltyMember extends StandardMember implements Discount {
    private double discountedPayment;
    private double discount;
    private static double totalDiscountedPayment = 0;
    private static double totalDiscountGiven = 0;

    public LoyaltyMember(String firstName, String lastName, double payment) {
        super(firstName, lastName);
        super.setPayment(payment);
        discount();
    }

    @Override
    public void discount() {
        discount += 0.1 * getPayment();
        totalDiscountGiven += discount;
        discountedPayment += 0.9 * getPayment();
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
        discount += 0.1 * newPayment;
        totalDiscountGiven += 0.1 * newPayment;
        discountedPayment += 0.9 * newPayment;
        totalDiscountedPayment += 0.9 * newPayment;
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
