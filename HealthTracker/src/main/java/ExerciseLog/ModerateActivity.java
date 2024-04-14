package ExerciseLog;

import java.time.LocalDate;

public class ModerateActivity extends ExerciseLog{
    public ModerateActivity(LocalDate date, int duration) {
        super("Moderate Activity", date, duration);
    }

    @Override
    public double calculateCaloriesBurned(int duration){
        return duration * 5.0;
    }
}
