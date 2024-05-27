package Domain.User;

public class DefaultUser extends User {
    protected boolean hasAccessToAdvancedFeatures;

    public DefaultUser(String name, int age, double weight, double height, boolean hasAccessToAdvancedFeatures) {
        super(name, age, weight, height);
        this.hasAccessToAdvancedFeatures = hasAccessToAdvancedFeatures;
    }

    @Override
    public double calculateBMI() {
        return getWeight() / (getHeight()*getHeight());
    }

    public boolean getHasAccessToAdvancedFeatures() {
        return hasAccessToAdvancedFeatures;
    }

    public void setHasAccessToAdvancedFeatures(boolean hasAccessToAdvancedFeatures) {
        this.hasAccessToAdvancedFeatures = hasAccessToAdvancedFeatures;
    }
}
