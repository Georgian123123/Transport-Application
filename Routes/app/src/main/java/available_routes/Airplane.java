package available_routes;

import java.io.Serializable;

public class Airplane extends RoutesDetails implements Serializable {
    private Integer numberEscales;
    private Float oldman;
    private String economy = "Economic";
    public Airplane(Float timeGo, Float timeStop, Float price, Float distance, Integer numberEscales,
                    Float oldman) {
        super(timeGo, timeStop, price, distance, oldman);
        this.numberEscales = numberEscales;
        this.oldman = oldman;
    }

    public Integer getNumberEscales() {
        return numberEscales;
    }

    public Float getOldman() {
        return oldman;
    }
}
