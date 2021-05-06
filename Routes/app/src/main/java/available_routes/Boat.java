package available_routes;

import java.io.Serializable;

public class Boat extends RoutesDetails implements Serializable {
    private Float oldman;
    public Boat(Float timeGo, Float timeStop, Float price, Float distance, Float oldman) {
        super(timeGo, timeStop, price, distance, oldman);
        this.oldman = oldman;
    }

    public void setDays(String[] str) {
        super.setDaysOfWeek(str);
    }

    public Float getOldman() {
        return oldman;
    }
}

