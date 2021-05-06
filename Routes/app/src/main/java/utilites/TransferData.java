package utilites;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import available_routes.RoutesDetails;

public class TransferData {
    public static ArrayList<AvailableRoute> routes;
    public static Boolean students = false;
    public static Boolean retirees = false;
    public static Boolean shortest = false;
    public static Boolean cheapest = false;
    public static Integer ticket_contor = 0;
    public static ArrayList<Integer> dayGo = new ArrayList<>();
    public static ArrayList<Integer> dayLeave = new ArrayList<>();
    public static HashMap<Pair<String, String>, String> tickets = new HashMap<>();

    public static ArrayList<String> stops = new ArrayList<>();
    public static ArrayList<String> names = new ArrayList<>();
    public static Stack<String> days_stay = new Stack<String>();
    public static Stack<String> vagon = new Stack<String>();
    public static Stack<String> row = new Stack<String>();
    public static Stack<String> place = new Stack<String>();

    public static ArrayList<Pair<Pair<String, String>, Pair<RoutesDetails, String>>> toShow = new ArrayList<>();
    public static Float price_total = 0.f;
    public static Float distance_total = 0.f;
    public static Integer pasagers = 1;
    public static Float time_limie = 0.f;
    public static Integer cont = 0;
    public static Integer timeStay = 0;
}
