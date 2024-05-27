package Domain.Reminder;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Reminder {
    protected Map<String, LocalTime> reminders;

    public Reminder() {
        reminders = new HashMap<>();
        // default reminder times
        reminders.put("Medication", LocalTime.of(8, 0));
        reminders.put("Water", LocalTime.of(9, 0));
        reminders.put("Exercise", LocalTime.of(19, 0));
    }

    public void setReminderTime(String reminderType, int hour, int minute){
        reminders.put(reminderType, LocalTime.of(hour, minute));
    }

    public void displayReminders(LocalTime currentTime){
        for(Map.Entry<String, LocalTime> entry : reminders.entrySet()){
            if(currentTime.getHour() == entry.getValue().getHour()){
                System.out.println("Reminder for " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}
