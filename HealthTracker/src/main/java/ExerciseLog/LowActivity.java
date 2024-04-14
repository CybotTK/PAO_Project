package ExerciseLog;

import java.time.LocalDate;

public class LowActivity extends ExerciseLog{

    public LowActivity(LocalDate date, int duration) {
        super("Low Activity", date, duration);
    }

    @Override
    public double calculateCaloriesBurned(int duration){
        return duration * 3.5;
    }
}
