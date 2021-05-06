package com.example.routes;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import available_routes.Routes;
import available_routes.RoutesDetails;
import utilites.AvailableRoute;
import utilites.Filter;
import utilites.ListAdapter;
import utilites.LoginPage;
import utilites.Pair;
import utilites.TransferData;

public class DashboardActivity extends AppCompatActivity {
    public String[] userDatas;
    public static final String userDatasToSend = "";
    EditText txtDate, txtTime, source, dest, txtDate2, txtTime2;
    Button search;
    private int mYear, mMonth, mDay, mHour, mMinute, edges = 0, nodes = 0;
    private HashMap<String, HashMap<String, HashMap<String, List<RoutesDetails>>>> routes;
    private HashMap<String, ArrayList<Pair<String, ArrayList<String>>>> networkGraph = new HashMap<>();
    private ArrayList<ArrayList<AvailableRoute>> availableRoutes = new ArrayList<ArrayList<AvailableRoute>>();
    private RecyclerView list_routes;
    public int Image;
    public int imageBuy;
    public int imageDetails;
    ConstraintLayout cl2;
    ConstraintLayout cl1;
    private CheckBox retiress, students, shortest, cheapest;
    private Boolean retiressB = false , studentsB = false, pupilsB = false, shortestB = false, cheapestB = false;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        routes = Routes.getInstance().getRoutes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        cl2 = findViewById(R.id.rts);
        cl1 = findViewById(R.id.down);
        list_routes = findViewById(R.id.routes);
        Image = R.drawable.arrow;
        imageBuy = R.drawable.checkout;
        imageDetails = R.drawable.details;
        retiress = findViewById(R.id.DiscountRetirees);
        students = findViewById(R.id.DiscountStudents);
        shortest = findViewById(R.id.Shortest);
        cheapest = findViewById(R.id.Cheap);


        Intent intentName = getIntent();
        userDatas = intentName.getStringArrayExtra(LoginPage.userDatas);

        filter();
        profile();
        date_time();
        getPath();
    }

    public Integer my_find(ArrayList<Pair<String, ArrayList<String>>> arr, String find) {
        if (arr == null)
            return null;
        for (Pair<String, ArrayList<String>> elem : arr) {
            if (elem.first.equals(find)) {
                return arr.indexOf(elem);
            }
        }
        return -1;
    }

    public void constructGraph() {
        for (Map.Entry<String, HashMap<String, HashMap<String, List<RoutesDetails>>>> entry : routes.entrySet()) {
            HashMap<String, HashMap<String, List<RoutesDetails>>> aux = entry.getValue();

            for(Map.Entry<String, HashMap<String, List<RoutesDetails>>> entryRoute : aux.entrySet()) {
                if (networkGraph.get(entryRoute.getKey()) == null) {
                    networkGraph.put(entryRoute.getKey(), new ArrayList<Pair<String, ArrayList<String>>>());
                    nodes++;
                }
                HashMap<String, List<RoutesDetails>> aux2 = entryRoute.getValue();
                for (Map.Entry<String, List<RoutesDetails>> entryFinal : aux2.entrySet()) {

                    Integer index = my_find(networkGraph.get(entryRoute.getKey()), entryFinal.getKey());
                    if (index == -1) {
                        ArrayList<String> comp = new ArrayList<>();
                        comp.add(entry.getKey());
                        networkGraph.get(entryRoute.getKey()).add(new Pair<String, ArrayList<String>>(entryFinal.getKey(), comp));
                    } else {
                        networkGraph.get(entryRoute.getKey()).get(index).second.add(entry.getKey());
                    }
                    edges++;
                    nodes++;
                }
            }
            System.out.println();
        }
    }

    private void printAllPathsUtil(String u, String d,
                                   HashMap<String, Boolean> isVisited,
                                   List<Pair<String, ArrayList<String>>> localPathList)
    {
        if (u.equals(d)) {
            ArrayList<AvailableRoute> auxRoutes = new ArrayList<>();
            for (int i = 0; i < localPathList.size() - 1; i++) {
                String src = localPathList.get(i).first;
                String dst = localPathList.get(i + 1).first;
                for (String company : localPathList.get(i + 1).second) {
                    List<RoutesDetails> rts = routes.get(company).get(src).get(dst);
                    AvailableRoute avlRoute = new AvailableRoute(src, dst, company);
                    avlRoute.setRoutes(rts);
                    auxRoutes.add(avlRoute);
                }
            }
            availableRoutes.add(auxRoutes);
            return;
        }

        isVisited.put(u, true);

        if (networkGraph.get(u) != null) {
            for (Pair<String, ArrayList<String>> i : Objects.requireNonNull(networkGraph.get(u))) {
                if (!isVisited.get(i.first)) {
                    localPathList.add(i);
                    printAllPathsUtil(i.first, d, isVisited, localPathList);

                    localPathList.remove(i);
                }
            }
        }
        // Mark the current node
        isVisited.put(u, false);
    }

    public void printAllPaths(String s, String d)
    {
        HashMap<String, Boolean> isVisited = new HashMap<>();
        ArrayList<Pair<String, ArrayList<String>>> pathList = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Pair<String, ArrayList<String>>>> elem : networkGraph.entrySet()) {
            isVisited.put(elem.getKey(), Boolean.FALSE);
            for (Pair<String, ArrayList<String>> dests : elem.getValue()) {
                isVisited.put(dests.first, Boolean.FALSE);
            }
        }
        pathList.add(new Pair<String, ArrayList<String>>(s, new ArrayList<>()));
        // Call recursive utility
        printAllPathsUtil(s, d, isVisited, pathList);
    }

    public void getPath() {
        source = findViewById(R.id.Source);
        dest = findViewById(R.id.Destination);

        search = findViewById(R.id.Search);
        search.setOnClickListener(v -> {
            routes = Routes.getInstance().getRoutes();
            networkGraph = new HashMap<>();
            constructGraph();
            String src = source.getText().toString();
            String dst = dest.getText().toString();
            availableRoutes = new ArrayList<ArrayList<AvailableRoute>>();
            printAllPaths(src, dst);
            apply_filters_date(src, dst);
            if (TransferData.stops.size() != 0) {
                availableRoutes = apply_stops();
            }
            display(src, dst);
        });
    }

    public  ArrayList<ArrayList<AvailableRoute>> apply_stops() {
        ArrayList<ArrayList<AvailableRoute>> to_ret = new ArrayList<ArrayList<AvailableRoute>>();
        for(ArrayList<AvailableRoute> avl_list : availableRoutes) {
            ArrayList<AvailableRoute> aux = new ArrayList<>();
            TransferData.cont = 0;
            for (AvailableRoute avl : avl_list) {
                if (TransferData.cont < TransferData.stops.size()) {
                    if (avl.getDestination().equals(TransferData.stops.get(TransferData.cont))) {
                        TransferData.cont++;
                    }
                    aux.add(avl);
                } else {
                    aux.add(avl);
                }
            }
            if (aux.size() == avl_list.size() && TransferData.cont == TransferData.stops.size())
                to_ret.add(aux);
        }
        return to_ret;
    }
    public void apply_filters_date(String src, String dst) {
        EditText dep_day = findViewById(R.id.in_time);
        EditText dep_time = findViewById(R.id.in_time2);
        EditText arr_day = findViewById(R.id.in_time3);
        EditText arr_time = findViewById(R.id.in_time4);

        if (!(dep_day.getText().toString().matches("") ||
            dep_time.getText().toString().matches("") ||
            arr_day.getText().toString().matches("") ||
            arr_time.getText().toString().matches(""))) {
            String[] dep_day_elems = dep_day.getText().toString().split("-", 3);
            String[] dep_time_elems = dep_time.getText().toString().split(":", 2);
            String[] arr_day_elems = arr_day.getText().toString().split("-", 3);
            String[] arr_time_elems = arr_time.getText().toString().split(":", 2);
            String time_go = dep_time_elems[0] + "." + dep_time_elems[1];
            String time_arrive = arr_time_elems[0] + "." + arr_time_elems[1];

            StringBuilder date_go = new StringBuilder();
            StringBuilder date_leave = new StringBuilder();
            if (dep_day_elems[0].length() == 1) {
                date_go.append("0").append(dep_day_elems[0]).append("-");
            } else {
                date_go.append(dep_day_elems[0]).append("-");
            }
            if (dep_day_elems[1].length() == 1) {
                date_go.append("0").append(dep_day_elems[1]).append("-");
            } else {
                date_go.append(dep_day_elems[1]).append("-");
            }
            if (arr_day_elems[0].length() == 1) {
                date_leave.append("0").append(arr_day_elems[0]).append("-");
            } else {
                date_leave.append(arr_day_elems[0]).append("-");
            }
            if (arr_day_elems[1].length() == 1) {
                date_leave.append("0").append(dep_day_elems[1]).append("-");
            } else {
                date_leave.append(dep_day_elems[1]).append("-");
            }
            date_go.append(dep_day_elems[2]);
            date_leave.append(arr_day_elems[2]);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = null;
            Date date2 = null;
            int dayOfWeekGo = 0;
            int dayOfWeekLeave = 0;
            final Calendar calendar = Calendar.getInstance();
            try {
                date1 = format1.parse(date_go.toString());
                calendar.setTime(date1);
                dayOfWeekGo = calendar.get(Calendar.DAY_OF_WEEK);
                TransferData.dayGo.add(dayOfWeekGo);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                date2 = format2.parse(date_leave.toString());
                calendar.setTime(date2);
                dayOfWeekLeave = calendar.get(Calendar.DAY_OF_WEEK);
                TransferData.dayLeave.add(dayOfWeekLeave);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            ArrayList<ArrayList<AvailableRoute>> availableRoutes_temp = new ArrayList<ArrayList<AvailableRoute>>();
            for (ArrayList<AvailableRoute> avl_list : availableRoutes) {
                ArrayList<AvailableRoute> routes_temp = new ArrayList<AvailableRoute>();
                for (AvailableRoute avl : avl_list) {
                    if (avl.getSource().equals(src) && avl.getDestination().equals(dst)) {
                        List<RoutesDetails> aux = new ArrayList<RoutesDetails>();
                        for (RoutesDetails rd : avl.getRoutes()) {
                            if (rd.getTimeGo() > Float.parseFloat(time_go) && rd.getTimeStop() < Float.parseFloat(time_arrive) &&
                                    rd.contains(getDayOfWeek(dayOfWeekGo)) && rd.contains(getDayOfWeek(dayOfWeekLeave))) {
                                aux.add(rd);
                            }
                        }
                        AvailableRoute av_aux = new AvailableRoute(avl.getSource(), avl.getDestination(),
                                                avl.getCompany());
                        av_aux.setRoutes(aux);
                        routes_temp.add(av_aux);
                    } else {
                        int i = avl_list.indexOf(avl);
                        List<RoutesDetails> aux = new ArrayList<RoutesDetails>();
                        if (i == 0) {
                            for (RoutesDetails rd : avl_list.get(i).getRoutes()) {
                                if (rd.getTimeGo() > Float.parseFloat(time_go) && rd.contains(getDayOfWeek(dayOfWeekGo))) {
                                    aux.add(rd);
                                }
                            }
                            AvailableRoute av_aux = new AvailableRoute(avl_list.get(i).getSource(), avl_list.get(i).getDestination(),
                                                                        avl_list.get(i).getCompany());
                            av_aux.setRoutes(aux);
                        } else if (i == avl_list.size() - 1) {
                            for (RoutesDetails rd : avl_list.get(i).getRoutes()) {
                                if (rd.getTimeStop() < Float.parseFloat(time_go) && rd.contains(getDayOfWeek(dayOfWeekLeave))) {
                                    aux.add(rd);
                                }
                            }
                            AvailableRoute av_aux = new AvailableRoute(avl_list.get(i).getSource(), avl_list.get(i).getDestination(),
                                                                        avl_list.get(i).getCompany());
                            av_aux.setRoutes(aux);
                        } else {
                            routes_temp.add(avl_list.get(i));
                        }
                    }
                }
                availableRoutes_temp.add(routes_temp);
            }
            availableRoutes = availableRoutes_temp;
        }
    }

    public void display(String src, String dst) {
        cl2.setVisibility(View.VISIBLE);
        cl1.setVisibility(View.INVISIBLE);
        String[] srcs = new String[availableRoutes.size()];
        String[] dsts = new String[availableRoutes.size()];
        String[] cmps = new String[availableRoutes.size()];

        ArrayList<String> sources = new ArrayList<>();
        ArrayList<String> destinations = new ArrayList<>();
        ArrayList<String> companies = new ArrayList<>();
        ArrayList<ArrayList<AvailableRoute>> rts = new ArrayList<ArrayList<AvailableRoute>>();

        for (int i = 0; i < availableRoutes.size(); i++) {
            boolean diff = false;
            for (int j = 0; j < availableRoutes.get(i).size(); j++) {
                if (src.equals(availableRoutes.get(i).get(j).getSource()) &&
                        dst.equals(availableRoutes.get(i).get(j).getDestination())) {
                    sources.add(src);
                    destinations.add(dst);
                    companies.add(availableRoutes.get(i).get(j).getCompany());
                    ArrayList<AvailableRoute> auxiliarySpace = new ArrayList<>();
                    auxiliarySpace.add(availableRoutes.get(i).get(j));
                    rts.add(auxiliarySpace);
                } else {
                    diff = true;
                }
            }
            if (diff) {
                sources.add(src);
                destinations.add(dst);
                companies.add("Multi routes");
                ArrayList<AvailableRoute> auxiliarySpace = new ArrayList<>();
                for (int j = 0; j < availableRoutes.get(i).size(); j++) {
                    auxiliarySpace.addAll(availableRoutes.get(i));
                }
                rts.add(auxiliarySpace);
            }
        }
        srcs = sources.toArray(new String[sources.size()]);
        dsts = destinations.toArray(new String[destinations.size()]);
        cmps = companies.toArray(new String[companies.size()]);
        ListAdapter listAdapter = new ListAdapter(this, srcs, dsts, cmps, Image, imageBuy, imageDetails, rts);
        list_routes.setAdapter(listAdapter);
        list_routes.setLayoutManager(new LinearLayoutManager(this));
    }

    public void filter() {
        cl1.setVisibility(View.INVISIBLE);

        Button filter = findViewById(R.id.filter);
        filter.setOnClickListener(v -> {
            if (cl1.getVisibility() == View.INVISIBLE) {
                cl1.setVisibility(View.VISIBLE);
                cl2.setVisibility(View.INVISIBLE);
            } else if (cl1.getVisibility() == View.VISIBLE) {
                cl1.setVisibility(View.INVISIBLE);
                cl2.setVisibility(View.VISIBLE);
            }
        });
        TransferData.stops = new ArrayList<>();
        EditText nrPasag = findViewById(R.id.nr_pasageri);
        Button add = findViewById(R.id.add);
        EditText add_cities = findViewById(R.id.intra);
        TransferData.cont = 0;
        EditText time_stay = findViewById(R.id.timeStay);
        add.setOnClickListener(v -> {
            TransferData.stops.add(add_cities.getText().toString());
        });
        Button applyFilter = findViewById(R.id.applyFilter);
        applyFilter.setOnClickListener(v -> {
            cl1.setVisibility(View.INVISIBLE);
            cl2.setVisibility(View.VISIBLE);
            if (!time_stay.getText().toString().matches(""))
                TransferData.timeStay = Integer.parseInt(time_stay.getText().toString());
            if (!nrPasag.getText().toString().matches(""))
                TransferData.pasagers = Integer.parseInt(nrPasag.getText().toString());
            if (retiress.isChecked()) {
                retiressB = true;
                TransferData.retirees = true;
            } else {
                TransferData.retirees = false;
                retiressB = false;
            }
            if (students.isChecked()) {
                TransferData.students = true;
                studentsB = true;
            } else {
                TransferData.students = false;
                studentsB = false;
            }
            if (shortest.isChecked()) {
                TransferData.shortest = true;
                shortestB = true;
            } else {
                TransferData.shortest = false;
                shortestB = false;
            }
            if (cheapest.isChecked()) {
                TransferData.cheapest = true;
                cheapestB = true;
            } else {
                TransferData.cheapest = false;
                cheapestB = false;
            }
        });
    }

    public void profile() {
        ImageView profile = findViewById(R.id.profile);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(userDatasToSend, userDatas);
            startActivity(intent);
        });
    }

    public void date_time() {
        txtDate = findViewById(R.id.in_time);
        txtDate.setOnClickListener(v -> {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        txtTime = findViewById(R.id.in_time2);
        txtTime.setOnClickListener(v -> {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        });

        txtDate2 = findViewById(R.id.in_time3);
        txtDate2.setOnClickListener(v -> {
            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> txtDate2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });

        txtTime2 = findViewById(R.id.in_time4);
        txtTime2.setOnClickListener(v -> {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            txtTime2.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        });
    }

    public static String getDayOfWeek(int day) {
        switch (day) {
            case 1: return "Duminica";
            case 2: return "Luni";
            case 3: return "Marti";
            case 4: return "Miercuri";
            case 5: return "Joi";
            case 6: return "Vineri";
            case 7: return "Sambata";
        }
        return " ";
    }
}