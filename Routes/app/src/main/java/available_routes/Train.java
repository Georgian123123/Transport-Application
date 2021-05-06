package available_routes;

import java.io.Serializable;

public class Train extends RoutesDetails implements Serializable {
    private Integer classTrain = -1;
    private Float oldMan;

    public Train(Float timeGo, Float timeStop, Float price, Float distance, Integer classTrain,
                 Float oldMan) {
        super(timeGo, timeStop, price, distance, oldMan);
        this.classTrain = classTrain;
        this.oldMan = oldMan;
    }

    public Integer getClassTrain() {
        return classTrain;
    }

    public Float getOldMan() {
        return oldMan;
    }
}
