package CalorieIntake;

import java.time.LocalDateTime;
import NutritionInfo.NutritionInfo;

public class CalorieIntake {
    protected LocalDateTime dateTime;
    protected String mealType;
    protected NutritionInfo foodItem;
    protected double grams;

    public CalorieIntake(LocalDateTime dateTime, String mealType, NutritionInfo foodItem, double grams) {
        this.dateTime = dateTime;
        this.mealType = mealType;
        this.foodItem = foodItem;
        this.grams = grams;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getMealType() {
        return mealType;
    }

    public NutritionInfo getFoodItem() {
        return foodItem;
    }

    public double getGrams() {
        return grams;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public void setFoodItem(NutritionInfo foodItem) {
        this.foodItem = foodItem;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }
}

