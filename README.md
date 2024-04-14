# PAO_Project

The HealthTracker is a Java application designed to help users monitor and manage their health and nutrition. 
It provides functionalities for tracking calorie intake, macronutrients consumption, exercise activities, sleep patterns, and weight history. 
Users can add foods to their intake, view nutritional information, search for specific foods, and monitor their progress towards health goals. 
Additionally, the HealthTracker offers features such as reminders for medication, hydration, and exercise, enhancing overall health management and well-being.

1.User
   -->class abstract
   -->DefaultUser
   -->PremUser derivate of DefaultUser
   -->name, age, weight, height, ... 

2.NutritionInfo
  -->String name, int calories, double protein, double fat, double carbohydrates
  -->Function calculate calories + macronutrients

3.Health Data
 -->exercise logs, calorie intake, sleep patterns, weight history

4.Goal 
 --> target weight, target daily step count etc

5.Reminder
 --> taking meds, drink water, exercise etc

6.WeightEntry 
 --> entry for weight measurement
 --> attributes : date, weight value, ...

7.SleepPattern
 -->date, duration, quality, etc.

8.ExerciseLog
 -->activity type, date, duration, burned calories...

9.CalorieIntake
 -->date+time(breakfast, lunch, dinner, snack), food item, quantity


Service Class

--> function to add foods in the list with macronutrients and calories for 100g;
--> function to search food by name
