package HealthData;

import CalorieIntake.CalorieIntake;
import ExerciseLog.ExerciseLog;
import SleepPattern.SleepPattern;
import WeightEntry.WeightEntry;

import java.util.ArrayList;
import java.util.List;

public class HealthData {
    protected List<ExerciseLog> exerciseLogs;
    protected List<CalorieIntake> calorieIntakes;
    protected List<SleepPattern> sleepPatterns;
    protected List<WeightEntry> weightHistory;

    public HealthData() {
        this.exerciseLogs = new ArrayList<ExerciseLog>();
        this.calorieIntakes = new ArrayList<CalorieIntake>();
        this.sleepPatterns = new ArrayList<SleepPattern>();
        this.weightHistory = new ArrayList<WeightEntry>();
    }

    public void addExerciseLog(ExerciseLog exerciseLog) {
        exerciseLogs.add(exerciseLog);
    }

    public void addCalorieIntake(CalorieIntake calorieIntake) {
        calorieIntakes.add(calorieIntake);
    }

    public void addSleepPattern(SleepPattern sleepPattern) {
        sleepPatterns.add(sleepPattern);
    }

    public void addWeightEntry(WeightEntry weightEntry) {
        weightHistory.add(weightEntry);
    }

    public List<ExerciseLog> getExerciseLogs() {
        return exerciseLogs;
    }

    public List<CalorieIntake> getCalorieIntakes() {
        return calorieIntakes;
    }

    public List<SleepPattern> getSleepPatterns() {
        return sleepPatterns;
    }

    public List<WeightEntry> getWeightHistory() {
        return weightHistory;
    }
}
