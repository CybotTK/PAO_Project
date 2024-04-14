package Main;

import ExerciseLog.ExerciseLog;
import ExerciseLog.HighActivity;
import ExerciseLog.LowActivity;
import ExerciseLog.ModerateActivity;
import NutritionInfo.NutritionInfo;
import Reminder.Reminder;
import Services.FoodService;
import Services.HealthTrackerService;
import SleepPattern.SleepPattern;
import User.DefaultUser;
import User.PremiumUser;
import Goal.Goal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class HealthTrackerMain {

    public static void main(String[] args) {

        //Create defaultUser
        DefaultUser defaultUser = new DefaultUser("Alice", 30, 65.5,1.75,false);

        //Create premiumUser
        PremiumUser premiumUser = new PremiumUser("Bob The Builder", 35, 75.0, 1.90, 100);

        System.out.println("BMI Calculation:");
        System.out.println(defaultUser.getName() + " has a BMI of " +defaultUser.calculateBMI());
        System.out.println(premiumUser.getName() + " has a BMI of " +premiumUser.calculateBMI());

        if (defaultUser.getHasAccessToAdvancedFeatures())
        {
            PremiumUser premiumUser2 = new PremiumUser(defaultUser.getName(), defaultUser.getAge(), defaultUser.getWeight(), defaultUser.getHeight(), 0);
        }
        else {
            System.out.println(defaultUser.getName()+" buy premium to have access to the advanced features");
        }

        //Nutrition Class
        NutritionInfo rice = new NutritionInfo("Rice", 130, 2.7, 0.3, 28.7);

        rice.calculateNutritionalValues(150);

        // Create a Goal object representing the user's fitness goal
        Goal fitnessGoal = new Goal("Fitness", 60.0, defaultUser.getWeight(), defaultUser.getAge(), defaultUser.getHeight(), 3, 10000);

        System.out.println("Target calories for " + defaultUser.getName() + " are " + fitnessGoal.getTargetCalories());

        // Simulate consumed values for a day
        double consumedCalories = 1800.0;
        double consumedProtein = 70.0;
        double consumedFat = 50.0;
        double consumedCarbohydrates = 200.0;
        int stepsTaken = 8000;

        // Calculate remaining values for the day using GoalService
        List<Double> remainingValues = fitnessGoal.calculateRemainingNutrition(consumedCalories, consumedProtein, consumedFat, consumedCarbohydrates);

        int remainingSteps = fitnessGoal.calculateRemainingSteps(stepsTaken);

        // Display remaining values
        System.out.println("Remaining values for the day:");
        System.out.println("Remaining Calories: " + remainingValues.get(0));
        System.out.println("Remaining Protein: " + remainingValues.get(1));
        System.out.println("Remaining Fat: " + remainingValues.get(2));
        System.out.println("Remaining Carbohydrates: " + remainingValues.get(3));

        System.out.println("Remaining Steps: " + remainingSteps);

        //SleepPattern class
        LocalTime bedtime = LocalTime.of(22, 30); // bedtime at 22:30
        LocalTime wakeupTime = LocalTime.of(6, 30); // wake-up time at 6:30
        int sleepQuality = 8;

        SleepPattern sleepPattern = new SleepPattern(bedtime, wakeupTime, sleepQuality);

        int durationTimeInMinutes = sleepPattern.getDurationInMinutes();
        System.out.println("Sleep duration: " + durationTimeInMinutes + "minutes");
        System.out.println("Bedtime: " +    sleepPattern.getBedTime() );
        System.out.println("Wake-up time: " +    sleepPattern.getWakeUpTime() );
        System.out.println("Sleep Quality: " +    sleepPattern.getSleepQuality() );

        ExerciseLog lowActivity = new LowActivity(LocalDate.now(), 30);
        ExerciseLog moderateActivity = new ModerateActivity(LocalDate.now(), 60);
        ExerciseLog highActivity = new HighActivity(LocalDate.now(), 45);

        displayExerciseLog(lowActivity);
        displayExerciseLog(moderateActivity);
        displayExerciseLog(highActivity);

        Reminder reminder = new Reminder();

        reminder.setReminderTime("Medication", 8, 0);
        reminder.setReminderTime("Water", 10, 30);
        reminder.setReminderTime("Exercise", 19, 0);

        LocalTime currentTime = LocalTime.now();
        reminder.displayReminders(currentTime);

        HealthTrackerService trackerService = new HealthTrackerService();

        // Set custom reminder times
        trackerService.setReminderTime("Medication", 8, 0);   // 8:00 AM
        trackerService.setReminderTime("Water", 10, 30);      // 10:30 AM
        trackerService.setReminderTime("Exercise", 18, 30);   // 6:30 PM

        currentTime = LocalTime.now();
        trackerService.displayReminders(currentTime);

        FoodService foodService = new FoodService();

        // Create some NutritionInfo objects (foods)
        NutritionInfo food1 = new NutritionInfo("Apple", 52, 0.3, 0.2, 14);
        NutritionInfo food2 = new NutritionInfo("Banana", 89, 1.1, 0.3, 23);
        NutritionInfo food3 = new NutritionInfo("Orange", 47, 0.9, 0.1, 12);

        // Add foods to the food service with a comparator that sorts by name
        foodService.addFood(food1, Comparator.comparing(NutritionInfo::getName));
        foodService.addFood(food2, Comparator.comparing(NutritionInfo::getName));
        foodService.addFood(food3, Comparator.comparing(NutritionInfo::getName));

        // Display the sorted list of foods
        foodService.displayFoods();

        String searchName = "Banana";
        NutritionInfo foundFood = foodService.searchFood(searchName);
        if (foundFood != null) {
            System.out.println("Found food: " + foundFood);
        } else {
            System.out.println("Food not found: " + searchName);
        }
    }

    public static void displayExerciseLog(ExerciseLog exerciseLog) {
        System.out.println();
        System.out.println("Activity Type: " + exerciseLog.getActivityType());
        System.out.println("Date: " + exerciseLog.getDate());
        System.out.println("Duration: " + exerciseLog.getDuration());
        System.out.println("Burned Calories: " + exerciseLog.getBurnedCalories());
        System.out.println();
    }

}
