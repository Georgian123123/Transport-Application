package available_routes;

import java.io.Serializable;

public class RoutesDetails implements Serializable {
    private Float timeGo;
    private Float timeStop;
    private Float price;
    private Float distance;
    private Float oldman;
    private String[] daysOfWeek = {"Luni", "Marti", "Miercuri", "Joi", "Vineri", "Sambata", "Duminica"};
    private Float students = -1.f;

    public RoutesDetails(Float timeGo, Float timeStop, Float price, Float distance, Float oldman) {
        this.timeGo = timeGo;
        this.timeStop = timeStop;
        this.price = price;
        this.distance = distance;
        this.oldman = oldman;
    }

    public Float getOldman() {
        return oldman;
    }

    public Float getTimeGo() {
        return timeGo;
    }

    public Float getTimeStop() {
        return timeStop;
    }

    public Float getPrice() {
        return price;
    }

    public Float getDistance() {
        return distance;
    }

    public String[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public boolean contains(String str) {
        for (int i = 0; i < daysOfWeek.length; i++) {
            if (daysOfWeek[i].equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void setDaysOfWeek(String[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public Float getStudents() {
        return students;
    }

    public void setStudents(Float students) {
        this.students = students;
    }

    public void setOldman(Float oldman) {
        this.oldman = oldman;
    }
}
