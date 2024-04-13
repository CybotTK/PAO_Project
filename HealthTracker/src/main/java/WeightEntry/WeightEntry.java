package WeightEntry;

import java.time.LocalDate;

public class WeightEntry {
    protected LocalDate date;
    protected double weight;

    public WeightEntry(LocalDate date, double weight) {
        this.date = date;
        this.weight = weight;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
