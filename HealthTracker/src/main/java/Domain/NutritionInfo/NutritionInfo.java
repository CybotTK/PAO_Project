package Domain.NutritionInfo;

public class NutritionInfo {
    protected String name;
    protected double calories;
    protected double protein;
    protected double fat;
    protected double carbohydrates;

    //everything is per 100g of that food

    public NutritionInfo(String name, double calories, double protein, double fat, double carbohydrates){
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat=fat;
        this.carbohydrates=carbohydrates;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void calculateNutritionalValues(int weightInGrams) {
        double weightRatio = (double) weightInGrams / 100;

        double caloriesForWeight = calories * weightRatio;
        double proteinForWeight = protein * weightRatio;
        double fatForWeight = fat * weightRatio;
        double carbohydratesForWeight = carbohydrates * weightRatio;

        System.out.println("Nutritional values for " + weightInGrams + " grams of " + name + ":");
        System.out.println("Calories: " + caloriesForWeight);
        System.out.println("Protein: " + proteinForWeight + "g");
        System.out.println("Fat: " + fatForWeight + "g");
        System.out.println("Carbohydrates: " + carbohydratesForWeight + "g");
    }

    @Override
    public String toString() {
        return "Food: " + name + ", Calories: " + calories + ", Protein: " + protein +
                ", Fat: " + fat + ", Carbohydrates: " + carbohydrates;
    }
}
