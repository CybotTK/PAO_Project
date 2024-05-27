package Domain.ExerciseLog;

import java.time.LocalDate;

public class HighActivity extends ExerciseLog{

    public HighActivity(LocalDate date, int duration) {
        super("High Activity", date, duration);
    }

    public double calculateCaloriesBurned(int duration){
        return duration * 7.0;
    }
}
