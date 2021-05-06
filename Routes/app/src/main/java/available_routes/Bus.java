package available_routes;

import java.io.Serializable;

public class Bus extends RoutesDetails implements Serializable {
    private Float oldMan;
    public Bus(Float timeGo, Float timeStop, Float price, Float distance, Float oldMan) {
        super(timeGo, timeStop, price, distance, oldMan);
        this.oldMan = oldMan;
    }

    public Float getOldMan() {
        return oldMan;
    }
}
