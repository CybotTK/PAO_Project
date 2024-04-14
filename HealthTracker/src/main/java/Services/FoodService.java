package Services;

import NutritionInfo.NutritionInfo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FoodService {
    private List<NutritionInfo> foodList;

    public FoodService() {
        foodList = new ArrayList<>();
    }

    // Method to add food to the list
    public void addFood(NutritionInfo food, Comparator<NutritionInfo> comparator) {
        foodList.add(food);
        // Sort the food list after adding a new item
        foodList.sort(comparator);
    }

    // Method to remove food from the list
    public void removeFood(NutritionInfo food) {
        foodList.remove(food);
    }

    // Method to display all foods in the list
    public void displayFoods() {
        for (NutritionInfo food : foodList) {
            System.out.println(food);
        }
    }

    // Method to search for a food by name
    public NutritionInfo searchFood(String name) {
        for (NutritionInfo food : foodList) {
            if (food.getName().equalsIgnoreCase(name)) {
                return food;
            }
        }
        return null; // Return null if food not found
    }

}
