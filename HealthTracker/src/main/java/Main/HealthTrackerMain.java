package Main;

import NutritionInfo.NutritionInfo;
import SleepPattern.SleepPattern;
import User.DefaultUser;
import User.PremiumUser;
import Goal.Goal;
import org.w3c.dom.ls.LSOutput;

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
        Goal fitnessGoal = new Goal("Fitness", defaultUser.getWeight(), 200.0, 200.0, 200.0, 60.0, 10000);

        System.out.println("Targe calories for " + defaultUser.getName() + " are " + fitnessGoal.getTargetCalories());

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
        LocalTime wakeuptime = LocalTime.of(6, 30); // wake-up time at 6:30
        int sleepQuality = 8;

        SleepPattern sleepPattern = new SleepPattern(bedtime, wakeuptime, sleepQuality);

        int durationTimeInMinutes = sleepPattern.getDurationInMinutes();
        System.out.println("Sleep duration: " + durationTimeInMinutes + "minutes");
        System.out.println("Bedtime: " +    sleepPattern.getBedTime() );
        System.out.println("Wake-up time: " +    sleepPattern.getWakeUpTime() );
        System.out.println("Sleep Quality: " +    sleepPattern.getSleepQuality() );
    }


}
