package Services;

import Domain.ExerciseLog.ExerciseLog;
import Domain.HealthData.HealthData;
import Domain.Reminder.Reminder;
import Domain.CalorieIntake.CalorieIntake;
import Domain.SleepPattern.SleepPattern;
import Domain.WeightEntry.WeightEntry;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HealthTrackerService {
    private HealthData healthData;
    private Reminder reminder;

    public HealthTrackerService() {
        healthData = new HealthData();
        reminder = new Reminder();
    }

    // Method to add exercise log
    public void addExerciseLog(ExerciseLog exerciseLog) {
        healthData.addExerciseLog(exerciseLog);
    }

    // Method to add calorie intake
    public void addCalorieIntake(CalorieIntake calorieIntake) {
        healthData.addCalorieIntake(calorieIntake);
    }

    // Method to add sleep pattern
    public void addSleepPattern(SleepPattern sleepPattern) {
        healthData.addSleepPattern(sleepPattern);
    }

    // Method to add weight entry
    public void addWeightEntry(WeightEntry weightEntry) {
        healthData.addWeightEntry(weightEntry);
    }

    // Method to display all exercise logs
    public void displayExerciseLogs() {
        for (ExerciseLog log : healthData.getExerciseLogs()) {
            System.out.println(log);
        }
    }

    // Method to display all calorie intakes
    public void displayCalorieIntakes() {
        for (CalorieIntake intake : healthData.getCalorieIntakes()) {
            System.out.println(intake);
        }
    }

    // Method to display all sleep patterns
    public void displaySleepPatterns() {
        for (SleepPattern pattern : healthData.getSleepPatterns()) {
            System.out.println(pattern);
        }
    }

    // Method to display all weight entries
    public void displayWeightHistory() {
        for (WeightEntry entry : healthData.getWeightHistory()) {
            System.out.println(entry);
        }
    }

    // Method to set reminder time for a specific type of reminder
    public void setReminderTime(String reminderType, int hour, int minute) {
        reminder.setReminderTime(reminderType, hour, minute);
    }

    // Method to display reminders
    public void displayReminders(LocalTime currentTime) {
        reminder.displayReminders(currentTime);
    }

}

