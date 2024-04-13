package Goal;

import java.util.ArrayList;
import java.util.List;

public class Goal {

    protected String name;
    protected double targetCalories;
    protected double targetProtein;
    protected double targetCarbohydrate;
    protected double targetFat;
    protected double targetWeight;
    protected int targetSteps;

    public Goal(String name, double weight, double targetProtein, double targetCarbohydrate, double targetFat, double targetWeight, int targetSteps) {
        this.name = name;
        this.targetCalories = calculateTargetCalories(targetWeight, weight);
        this.targetProtein = targetProtein;
        this.targetCarbohydrate = targetCarbohydrate;
        this.targetFat = targetFat;
        this.targetWeight = targetWeight;
        this.targetSteps = targetSteps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(double targetCalories) {
        this.targetCalories = targetCalories;
    }

    public double getTargetProtein() {
        return targetProtein;
    }

    public void setTargetProtein(double targetProtein) {
        this.targetProtein = targetProtein;
    }

    public double getTargetCarbohydrate() {
        return targetCarbohydrate;
    }

    public void setTargetCarbohydrate(double targetCarbohydrate) {
        this.targetCarbohydrate = targetCarbohydrate;
    }

    public double getTargetFat() {
        return targetFat;
    }

    public void setTargetFat(double targetFat) {
        this.targetFat = targetFat;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getTargetSteps() {
        return targetSteps;
    }

    public void setTargetSteps(int targetSteps) {
        this.targetSteps = targetSteps;
    }

    //Make TDEE calculator (Total Daily Energy Expenditure) based on activity level with case probably put in service

    protected double calculateTargetCalories(double targetWeight, double weight) {

        double bmr = 10 * weight + 6.25 * targetWeight - 5 * weight + 5;

        return bmr*1.55; //calories needed for targetWeight

    }

    public List<Double> calculateRemainingNutrition(double consumedCalories, double consumedProtein, double consumedFat, double consumedCarbohydrates){

        List<Double> remainingNutrition = new ArrayList<Double>();

        double remainingCalories = targetCalories - consumedCalories;
        double remainingProtein = targetProtein - consumedProtein;
        double remainingFat = targetFat - consumedFat;
        double remainingCarbohydrates = targetCarbohydrate - consumedCarbohydrates;

        remainingNutrition.add(remainingCalories);
        remainingNutrition.add(remainingProtein);
        remainingNutrition.add(remainingFat);
        remainingNutrition.add(remainingCarbohydrates);

        return remainingNutrition;
    }

    public int calculateRemainingSteps(int stepsTaken){
        return targetSteps-stepsTaken;
    }
}
