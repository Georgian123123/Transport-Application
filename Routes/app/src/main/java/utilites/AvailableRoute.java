package utilites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import available_routes.RoutesDetails;

public class AvailableRoute implements Serializable {
    private String Source;
    private String Destination;
    private String Company;

    private List<RoutesDetails> routes = new ArrayList<>();

    public AvailableRoute() {

    }
    public AvailableRoute(String source, String destination, String company) {
        this.Source = source;
        this.Destination = destination;
        this.Company = company;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public List<RoutesDetails> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RoutesDetails> routes) {
        this.routes = routes;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public void print() {
        System.out.println(Source + " " + Destination + Company);
        for (RoutesDetails rts : routes) {
            System.out.println(rts.getTimeGo() + " " + rts.getTimeStop() + " " + rts.getDistance() + rts.getPrice());
        }
    }

    public int compareTo(AvailableRoute av) {
        return Source.compareTo(av.getSource());
    }
}
