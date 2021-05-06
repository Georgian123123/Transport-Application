package com.example.routes;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import available_routes.Airplane;
import available_routes.Boat;
import available_routes.Bus;
import available_routes.Routes;
import available_routes.RoutesDetails;
import available_routes.Train;
import utilites.AvailableRoute;
import utilites.Pair;
import utilites.TransferData;

@SuppressWarnings("ALL")
public class CheckoutActivity extends AppCompatActivity implements Serializable {
    private ArrayList<AvailableRoute> routes;
    private ArrayList<AvailableRoute> routes_final = new ArrayList<AvailableRoute>();
    private ArrayList<Pair<String, String>> keys = new ArrayList<>();
    private Map<Pair<String, String>, HashMap<String, ArrayList<RoutesDetails>>> availableRoutes = new HashMap<>();
    private ArrayList<String> departures_hours = new ArrayList<>();
    private ArrayList<String> arrival_hours = new ArrayList<>();
    private ArrayList<String> price = new ArrayList<>();
    private ArrayList<String> price_st = new ArrayList<>();
    private ArrayList<String> price_ret = new ArrayList<>();
    private ArrayList<String> distance = new ArrayList<>();
    private ArrayList<String> types = new ArrayList<>();
    private ArrayList<Pair<String, String>> src_dst = new ArrayList<>();
    private ArrayList<String> companies_ticket = new ArrayList<>();
    private ArrayList<String> aux_ = new ArrayList<>();
    private TextView EditRoute, ChooseCompany;
    private Integer positionRoute = 0;
    private Button Done, Next;
    private TextView daysToStay;
    private Spinner dropdown;
    boolean isSpinnerTouched = false;
    private String Company = null;
    private Float last_hour = -1.f;
    private Integer days = -1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        routes = TransferData.routes;
        EditRoute = (TextView) findViewById(R.id.ClientsRoute);
        Done = (Button)findViewById(R.id.DONE);
        dropdown = findViewById(R.id.spinner);
        ChooseCompany = findViewById(R.id.ChooseCompany);
        daysToStay = findViewById(R.id.daysToStay);
        Next = findViewById(R.id.Next);
        editDatas();
        if (positionRoute < keys.size()) {
            EditRoute.setText(keys.get(positionRoute).first + "---->" + keys.get(positionRoute).second);
            src_dst.add(new Pair(keys.get(positionRoute).first, keys.get(positionRoute).second));
            setDrop();
            Done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    positionRoute++;
                    if (positionRoute < keys.size()) {
                        src_dst.add(new Pair(keys.get(positionRoute).first, keys.get(positionRoute).second));
                        EditRoute.setText(keys.get(positionRoute).first + "---->" + keys.get(positionRoute).second);
                        if (dropdown.getSelectedItem() != null) {
                            String route = dropdown.getSelectedItem().toString();
                            String[] splitted = route.split("\n", 6);

                            /* Split departure hours */
                            String[] splitted_helper = splitted[0].split(" ", 4);
                            departures_hours.add(splitted_helper[2] + " " + splitted_helper[3]);

                            splitted_helper = splitted_helper[2].split(":", 2);
                            String aux = splitted_helper[0] + "." + splitted_helper[1];
                            System.out.println(aux);
                            last_hour = Float.parseFloat(aux);

                            /* Split arrival hours */
                            splitted_helper = splitted[1].split(" ", 3);
                            arrival_hours.add(splitted_helper[2]);
                            /* Split Price*/
                            splitted_helper = splitted[2].split(" ", 2);
                            price.add(splitted_helper[1]);

                            /* Split price st/ret*/
                            splitted_helper = splitted[3].split(" ", 2);
                            splitted_helper = splitted_helper[1].split("/", 2);
                            price_ret.add(splitted_helper[0]);
                            price_st.add(splitted_helper[1]);

                            /* Split Distance*/
                            splitted_helper = splitted[4].split(" ", 3);
                            distance.add(splitted_helper[1]);

                            /* split types */
                            splitted_helper = splitted[5].split(" ", 5);
                            types.add(splitted_helper[4]);
                        }
                        setDrop();
                    } else {
                        if (!Done.getText().toString().equals("Buy")) {
                            String route = dropdown.getSelectedItem().toString();
                            String[] splitted = route.split("\n", 6);
                            /* Split departure hours */
                            String[] splitted_helper = splitted[0].split(" ", 4);
                            departures_hours.add(splitted_helper[2] + " " + splitted_helper[3]);

                            splitted_helper = splitted_helper[2].split(":", 2);
                            String aux = splitted_helper[0] + "." + splitted_helper[1];
                            last_hour = Float.parseFloat(aux);

                            /* Split arrival hours */
                            splitted_helper = splitted[1].split(" ", 3);
                            arrival_hours.add(splitted_helper[2]);
                            /* Split Price*/
                            splitted_helper = splitted[2].split(" ", 2);
                            price.add(splitted_helper[1]);

                            /* Split price st/ret*/
                            splitted_helper = splitted[3].split(" ", 2);
                            splitted_helper = splitted_helper[1].split("/", 2);
                            price_ret.add(splitted_helper[0]);
                            price_st.add(splitted_helper[1]);

                            /* Split Distance*/
                            splitted_helper = splitted[4].split(" ", 3);
                            distance.add(splitted_helper[1]);

                            /* split types */
                            splitted_helper = splitted[5].split(" ", 5);
                            types.add(splitted_helper[4]);

                            Done.setText("Buy");
                        } else {
                            createMyPDF();
                        }
                    }
                }
            });
        }
    }

    public String isRon(String city) {
        if (city.equals("Mangalia") || city.equals("RamnicuValcea") || city.equals("Constanta")
            || city.equals("Calimanesti") || city.equals("Bucuresti") || city.equals("Sulina"))
            return "RON";
        return "EURO";
    }
    public void setDrop() {
        Pair<String, String> key = keys.get(positionRoute);
        ArrayList<String> companies = new ArrayList<String>();
        companies.addAll(availableRoutes.get(key).keySet());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, companies);
        ChooseCompany.setText("Choose Company!");
        dropdown.setAdapter(dataAdapter);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseCompany.setText("Choose Route!");
                Company = dropdown.getSelectedItem().toString();
                companies_ticket.add(Company);
                ArrayList<RoutesDetails> rts = availableRoutes.get(keys.get(positionRoute)).get(Company);
                ArrayList<String> toShow = new ArrayList<>();
                if (rts != null && rts.size() > 0) {
                    for (RoutesDetails rd : rts) {
                        String detailsRoute = new String();
                        if (rd.getTimeGo() > last_hour) {
                            Float nr = rd.getTimeGo();
                            int full = Math.round(nr);
                            String mediator = nr.toString();
                            mediator = mediator.substring(mediator.indexOf(".") + 1);
                            if (mediator.length() == 1)
                                mediator += "0";
                            Integer half = Integer.valueOf(mediator);
                            String hr = "";
                            if (full >= 12 && full <= 24) {
                                hr = "PM";
                            } else {
                                hr = "AM";
                            }

                            Float nr2 = rd.getTimeStop();
                            int full2 = Math.round(nr2);
                            mediator = nr2.toString();
                            mediator = mediator.substring(mediator.indexOf(".") + 1);
                            if (mediator.length() == 1)
                                mediator += "0";

                            Integer half2 = Integer.valueOf(mediator);
                            String hr2 = "";
                            if (full2 >= 12 && full2 <= 24) {
                                hr2 = "PM";
                            } else {
                                hr2 = "AM";
                            }

                            String whichType = " ";
                            if (rd instanceof Bus)
                                whichType = "Bus";
                            else if (rd instanceof Airplane)
                                whichType = "Airplane";
                            else if (rd instanceof Train)
                                whichType = "Train";
                            else if (rd instanceof Boat)
                                whichType = "Boat";

                            String priceRet = rd.getOldman().toString();
                            String priceStud = rd.getStudents().toString();
                            if (rd.getOldman() == 0.0)
                                priceRet = "N";
                            if (rd.getStudents() == -1.f
                            )
                                priceStud = "N";
                            detailsRoute = "Time Go " + full + ":" + half +  " " + hr + "\n" +
                                    "Time Arrive " + full2 + ":" + half2 + " " + hr2 + "\n" +
                                    "Price " + rd.getPrice().toString() + "\n" +
                                    "retirees/students " + priceRet + "/" + priceStud + "\n" +
                                    "Distance: " + rd.getDistance() + " km" + "\n" +
                                    "You are going with " + whichType + "\n";
                            toShow.add(detailsRoute);
                        }
                    }
                    if (toShow.size() == 0) {
                        ChooseCompany.setText("No route, go back!");
                    }
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item, toShow);
                    dropdown.setAdapter(dataAdapter);
                } else {
                    ChooseCompany.setText("No route, go back!");
                }
                if (!daysToStay.getText().toString().matches("") && TransferData.dayGo.size() != 0) {
                    days = Integer.parseInt(daysToStay.getText().toString());
                    TransferData.dayGo.add(TransferData.dayGo.get(TransferData.dayGo.size() - 1) + days);
                }
            }
        });
    }

    public boolean my_contain(final Pair<String, String> key, ArrayList<Pair<String, String>> keysSec) {
        for(int i = 0; i < keysSec.size(); i++) {
            if (keysSec.get(i).first.equals(key.first) && keysSec.get(i).second.equals(key.second)) {
                return true;
            }
        }
        return false;
    }

    public void editDatas() {
        HashMap<String, ArrayList<RoutesDetails>> aux;

        for (int i = 0; i < routes.size(); i++) {
            Pair<String, String> rt = new Pair();
            rt.setFirst(routes.get(i).getSource());
            rt.setSecond(routes.get(i).getDestination());
            if (!my_contain(rt, keys)) {
                keys.add(rt);
            }
            if (availableRoutes.containsKey(rt)) {
                availableRoutes.get(rt).put(routes.get(i).getCompany(), (ArrayList)routes.get(i).getRoutes());
            } else {
                aux = new HashMap<>();
                aux.put(routes.get(i).getCompany(), (ArrayList)routes.get(i).getRoutes());
                availableRoutes.put(rt, aux);
            }
        }
    }

    public void createMyPDF() {
        PdfDocument ticket = new PdfDocument();
        PdfDocument.PageInfo ticketInfo = new PdfDocument.PageInfo.Builder(400, 800, 1).create();
        PdfDocument.Page myPage = ticket.startPage(ticketInfo);

        Paint myPain = new Paint();
        StringBuilder textForPdf = new StringBuilder();
        System.out.println(companies_ticket);
        System.out.println(departures_hours);
        System.out.println(arrival_hours);
        System.out.println(price);
        int x = 10;
        int y = 25;
        Float total_distance = 0.f;
        Float total_price = 0.f;
        textForPdf.append("Your ticket: \n");
        myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
        y += 2* (myPain.descent() - myPain.ascent());
        textForPdf = new StringBuilder();
        for (int i = 0; i < departures_hours.size(); i++) {
            textForPdf.append("You are going from ").append(src_dst.get(i).first).append(" to ").append(src_dst.get(i).second).append( " with ").append(types.get(i)).append("\n");
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();

            if (i < TransferData.dayGo.size()) {
                textForPdf.append("Departure day ").append(DashboardActivity.getDayOfWeek(TransferData.dayGo.get(i) % 7));
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += myPain.descent() - myPain.ascent();
                textForPdf = new StringBuilder();
            }

            textForPdf.append("The company for your trip is ").append(companies_ticket.get(i)).append("\n");
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();

            textForPdf.append("Your departure hour is ").append(departures_hours.get(i)).append("\n");
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();


            textForPdf.append("Your arrival hour is ").append(arrival_hours.get(i)).append("\n");
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();

            if (TransferData.students && !price_st.get(i).equals("N")) {
                textForPdf.append("Discount students for this route is ").append(price_st.get(i)).append(isRon(src_dst.get(i).first)).append("\n");
                total_price += Float.parseFloat(price_st.get(i));
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += (myPain.descent() - myPain.ascent());
                textForPdf = new StringBuilder();
            } else if (TransferData.retirees && !price_ret.get(i).equals("N")) {
                textForPdf.append("Discount retirees for this route is ").append(price_ret.get(i)).append(isRon(src_dst.get(i).first)).append("\n");
                total_price += Float.parseFloat(price_ret.get(i));
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += (myPain.descent() - myPain.ascent());
                textForPdf = new StringBuilder();
            } else {
                textForPdf.append("The price for this route is ").append(price.get(i)).append(isRon(src_dst.get(i).first)).append("\n");
                total_price += Float.parseFloat(price.get(i));
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += (myPain.descent() - myPain.ascent());
                textForPdf = new StringBuilder();
            }

            textForPdf.append("The distance for this route is ").append(distance.get(i)).append("KM").append("\n");
            total_distance += Float.parseFloat(distance.get(i));
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += 2 * (myPain.descent() - myPain.ascent());
            textForPdf = new StringBuilder();
        }

        if (TransferData.dayLeave.size() != 0) {
            textForPdf.append("Arrival day ").append(DashboardActivity.getDayOfWeek(TransferData.dayLeave.get(0) % 7));
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();
        }

        /* Put total price */
        textForPdf.append("Total price for this route is: ").append(total_price).append("Ron\n");
        myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
        y += (myPain.descent() - myPain.ascent());
        textForPdf = new StringBuilder();

        /* Put total price */
        textForPdf.append("Total distance for this route is: ").append(total_distance).append(" km\n");
        myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
        y += (myPain.descent() - myPain.ascent());

        ticket.finishPage(myPage);
        String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + src_dst.get(0).getFirst() + "-" + src_dst.get(src_dst.size() - 1).second + ".pdf";
        TransferData.ticket_contor++;

        TransferData.tickets.put(new Pair(src_dst.get(0).first, src_dst.get(src_dst.size() - 1).second), textForPdf.toString());
        File myFile = new File(myFilePath);
        try {
            ticket.writeTo(new FileOutputStream(myFile));
            Toast.makeText(this, "Ticket bought", 1).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ticket.close();
    }
}
