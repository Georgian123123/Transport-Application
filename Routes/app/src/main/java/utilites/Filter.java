package utilites;

import android.widget.CheckBox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import available_routes.Routes;
import available_routes.RoutesDetails;

public class Filter {
    private ArrayList<ArrayList<AvailableRoute>> availableRoutes = new ArrayList<ArrayList<AvailableRoute>>();
    private Boolean retiressB = false , studentsB = false, pupilsB = false, shortestB = false, cheapestB = false;
    private String src, dst;

    public Filter(ArrayList<ArrayList<AvailableRoute>> availableRoutes, Boolean retiressB, Boolean studentsB, Boolean pupilsB, Boolean shortestB, Boolean cheapestB, String src, String dst) {
        this.availableRoutes = new ArrayList<ArrayList<AvailableRoute>>(availableRoutes);

        this.retiressB = retiressB;
        this.studentsB = studentsB;
        this.pupilsB = pupilsB;
        this.shortestB = shortestB;
        this.cheapestB = cheapestB;
        this.src = src;
        this.dst = dst;
    }

    public ArrayList<ArrayList<AvailableRoute>>  apply_other(String src, String dst) {
        if (retiressB) {
//            return filter_retirees();
            return this.availableRoutes;
        }
        if (studentsB) {
//            return filter_students();
            return this.availableRoutes;
        }
        if (shortestB) {
            return filter_shortest(src, dst);
        }
        if (cheapestB) {
            return filter_cheapest(src, dst);
        }
        return this.availableRoutes;
    }

    public ArrayList<ArrayList<AvailableRoute>> filter_retirees() {
        ArrayList<ArrayList<AvailableRoute>> to_ret = new ArrayList<ArrayList<AvailableRoute>>();

        for (ArrayList<AvailableRoute> avl_list : availableRoutes) {
            ArrayList<AvailableRoute> avl_list_temp = new ArrayList<AvailableRoute>();
            for (AvailableRoute avl : avl_list) {
                List<RoutesDetails> rt_temp = new ArrayList<RoutesDetails>();
                for (RoutesDetails rd : avl.getRoutes()) {
                    if (rd.getOldman() > 0) {
                        rt_temp.add(rd);
                    }
                }

                AvailableRoute avl_temp = new AvailableRoute(avl.getSource(), avl.getDestination(), avl.getCompany());
                if (rt_temp.size() != 0) {
                    avl_temp.setRoutes(rt_temp);
                    avl_list_temp.add(avl_temp);
                }
            }
            if (avl_list_temp.size() != 0) {
                to_ret.add(avl_list_temp);
            }
        }
        return to_ret;
    }

    public ArrayList<ArrayList<AvailableRoute>> filter_students() {
        ArrayList<ArrayList<AvailableRoute>> to_ret = new ArrayList<ArrayList<AvailableRoute>>();

        for (ArrayList<AvailableRoute> avl_list : availableRoutes) {
            ArrayList<AvailableRoute> avl_list_temp = new ArrayList<AvailableRoute>();
            for (AvailableRoute avl : avl_list) {
                List<RoutesDetails> rt_temp = new ArrayList<RoutesDetails>();
                for (RoutesDetails rd : avl.getRoutes()) {
                    if (rd.getStudents() >= 0) {
                        rt_temp.add(rd);
                    }
                }

                AvailableRoute avl_temp = new AvailableRoute(avl.getSource(), avl.getDestination(), avl.getCompany());
                if (rt_temp.size() != 0) {
                    avl_temp.setRoutes(rt_temp);
                    avl_list_temp.add(avl_temp);
                }
            }
            if (avl_list_temp.size() != 0) {
                to_ret.add(avl_list_temp);
            }
        }
        return to_ret;
    }

    public ArrayList<ArrayList<AvailableRoute>>  filter_cheapest(String src, String dst) {
        Float min_price = 10000.f;
        ArrayList<ArrayList<AvailableRoute>> temp = this.availableRoutes;
        ArrayList<ArrayList<AvailableRoute>> to_ret = new ArrayList<ArrayList<AvailableRoute>>();

        for (ArrayList<AvailableRoute> avl_list : temp) {
            ArrayList<AvailableRoute> avl_list_temp = new ArrayList<AvailableRoute>();
            min_price = 1000000.f;
            for (AvailableRoute avl : avl_list) {
                for (RoutesDetails rd : avl.getRoutes()) {
                    if (rd.getPrice() < min_price) {
                        min_price = rd.getPrice();
                    }
                }

                List<RoutesDetails> rt_temp = new ArrayList<RoutesDetails>();
                for (RoutesDetails rd : avl.getRoutes()) {
                    if (rd.getPrice().equals(min_price)) {
                        rt_temp.add(rd);
                    }
                }
                AvailableRoute avl_temp = new AvailableRoute(avl.getSource(), avl.getDestination(), avl.getCompany());

                if (rt_temp.size() != 0) {
                    avl_temp.setRoutes(rt_temp);
                    avl_list_temp.add(avl_temp);
                }
            }
            if (avl_list_temp.size() != 0)
                to_ret.add(avl_list_temp);
        }
        return to_ret;
    }

    public ArrayList<ArrayList<AvailableRoute>>  filter_shortest(String src, String dst) {
        Float min_price = 10000.f;
        ArrayList<ArrayList<AvailableRoute>> temp = this.availableRoutes;
        ArrayList<ArrayList<AvailableRoute>> to_ret = new ArrayList<ArrayList<AvailableRoute>>();

        for (ArrayList<AvailableRoute> avl_list : temp) {
            ArrayList<AvailableRoute> avl_list_temp = new ArrayList<AvailableRoute>();
            min_price = 1000000.f;
            for (AvailableRoute avl : avl_list) {
                for (RoutesDetails rd : avl.getRoutes()) {
                    if (rd.getDistance() < min_price) {
                        min_price = rd.getDistance();
                    }
                }
                List<RoutesDetails> rt_temp = new ArrayList<RoutesDetails>();
                for (RoutesDetails rd : avl.getRoutes()) {
                    if (rd.getDistance().equals(min_price)) {
                        rt_temp.add(rd);
                    }
                }
                AvailableRoute avl_temp = new AvailableRoute(avl.getSource(), avl.getDestination(), avl.getCompany());

                if (rt_temp.size() != 0) {
                    avl_temp.setRoutes(rt_temp);
                    avl_list_temp.add(avl_temp);
                }
            }
            to_ret.add(avl_list_temp);
        }
        return to_ret;
    }

    public ArrayList<ArrayList<AvailableRoute>> getAvailableRoutes() {
        return apply_other(src, dst);
    }
}
