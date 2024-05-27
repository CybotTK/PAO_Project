package Domain.User;

public class PremiumUser extends User{
    protected int loyaltyPoints;

    public PremiumUser(String name, int age, double weight, double height, int loyaltyPoints) {
        super(name, age, weight, height);
        this.loyaltyPoints = loyaltyPoints;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public double calculateBMI(){
        return getWeight() / (getHeight() * getHeight());
    }
}
