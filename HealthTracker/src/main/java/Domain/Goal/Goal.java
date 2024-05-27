package Domain.Goal;

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

    public Goal(String name, double targetWeight, double weight, int age, double height, int activityLevel, int targetSteps) {
        this.name = name;
        this.targetWeight = targetWeight;
        this.targetSteps = targetSteps;

        // Calculate target calories based on moderate activity level
        double bmr = calculateBMR(weight, (int) (height*100), age); //i need height in cm
        double tdee = calculateTDEE(bmr, activityLevel);
        this.targetCalories = tdee;

        // Set target macronutrients based on general distribution percentages
        this.targetProtein = 0.15 * tdee / 4; // 15% of calories from protein
        this.targetFat = 0.25 * tdee / 9;     // 25% of calories from fat
        this.targetCarbohydrate = 0.60 * tdee / 4; // 60% of calories from carbohydrates
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


    private double calculateBMR(double weight, int height, int age) {
        // Calculate Basal Metabolic Rate (BMR) using Harris-Benedict Equation
        return 10 * weight + 6.25 * height - 5 * age + 5;
    }

    private double calculateTDEE(double bmr, int activityLevel) {
        // Calculate Total Daily Energy Expenditure (TDEE) based on activity level
        switch (activityLevel) {
            case 1: return bmr * 1.2; // Sedentary (little to no exercise)
            case 2: return bmr * 1.375; // Lightly active (light exercise/sports 1-3 days/week)
            case 3: return bmr * 1.55; // Moderately active (moderate exercise/sports 3-5 days/week)
            case 4: return bmr * 1.725; // Very active (hard exercise/sports 6-7 days a week)
            case 5: return bmr * 1.9; // Extra active (very hard exercise/sports & physical job or 2x training)
            default: return bmr; // Default to sedentary
        }
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
