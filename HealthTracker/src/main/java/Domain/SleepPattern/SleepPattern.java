package Domain.SleepPattern;

import java.time.LocalTime;

public class SleepPattern {
    protected LocalTime bedtime;
    protected LocalTime wakeUpTime;
    protected int sleepQuality; //scale from 1 to 10

    public SleepPattern(LocalTime bedTime, LocalTime wakeUpTime, int sleepQuality) {
        this.bedtime = bedTime;
        this.wakeUpTime = wakeUpTime;
        this.sleepQuality = sleepQuality;
    }

    public LocalTime getBedTime() {
        return bedtime;
    }

    public void setBedTime(LocalTime bedTime) {
        this.bedtime = bedTime;
    }

    public LocalTime getWakeUpTime() {
        return wakeUpTime;
    }

    public void setWakeUpTime(LocalTime wakeUpTime) {
        this.wakeUpTime = wakeUpTime;
    }

    public int getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(int sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public int calculateDurationOfSleep(){
        int bedtimeHour= bedtime.getHour();
        int bedtimeMinute= bedtime.getMinute();
        int wakeUpTimeHour= wakeUpTime.getHour();
        int wakeUpTimeMinute= wakeUpTime.getMinute();

        int durationHours;
        int durationMinutes;

        if (bedtimeHour <= wakeUpTimeHour) {
            durationHours = wakeUpTimeHour - bedtimeHour;
            durationMinutes = wakeUpTimeMinute - bedtimeMinute;
        }else{
            durationHours = (24-bedtimeHour) + wakeUpTimeHour;
            durationMinutes =  wakeUpTimeMinute - bedtimeMinute;
        }

        if (durationMinutes < 0) {
            durationHours--;
            durationMinutes+=60;
        }

        return durationHours * 60 + durationMinutes;
    }

    public int getDurationInMinutes(){
        return calculateDurationOfSleep();
    }
}
