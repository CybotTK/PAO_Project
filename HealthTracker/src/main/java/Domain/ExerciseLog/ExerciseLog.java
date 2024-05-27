package Domain.ExerciseLog;

import java.time.LocalDate;

public abstract class ExerciseLog {
    protected String activityType;
    protected LocalDate date;
    protected int duration; //in minutes
    protected double burnedCalories;

    public ExerciseLog(String activityType, LocalDate date, int duration) {
        this.activityType = activityType;
        this.date = date;
        this.duration = duration;
        this.burnedCalories = calculateCaloriesBurned(duration);
    }

    public abstract double calculateCaloriesBurned(int duration);

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getBurnedCalories() {
        return burnedCalories;
    }

    public void setBurnedCalories(double burnedCalories) {
        this.burnedCalories = burnedCalories;
    }
}
