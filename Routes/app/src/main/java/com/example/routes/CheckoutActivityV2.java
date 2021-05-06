package com.example.routes;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

import available_routes.Airplane;
import available_routes.Boat;
import available_routes.Bus;
import available_routes.RoutesDetails;
import available_routes.Train;
import utilites.AvailableRoute;
import utilites.Pair;
import utilites.RecyclerItemClickListener;
import utilites.RouteAdapter;
import utilites.TransferData;

public class CheckoutActivityV2 extends AppCompatActivity {
    private HashMap<Pair<String, String>, ArrayList<Pair<RoutesDetails, String>>> avlRoutes = new HashMap<>();
    private RecyclerView list_routes;
    private ArrayList<Pair<String, String>> src_dst = new ArrayList<>();
    private TextView txt;
    private Button next;
    private Integer position = 0;
    private RouteAdapter routeAdapter;
    private TextView days, vagon, row, place, result, show;
    private Button done;
    private int x, y;
    private StringBuilder textForPdf = new StringBuilder();
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout2);
        txt = findViewById(R.id.src_dst);
        list_routes = findViewById(R.id.list);
        next = findViewById(R.id.info);
        days = findViewById(R.id.days);
        days.setVisibility(View.INVISIBLE);
        done = findViewById(R.id.dn);
        done.setVisibility(View.INVISIBLE);
        vagon = findViewById(R.id.vagon);
        vagon.setVisibility(View.INVISIBLE);
        place = findViewById(R.id.place);
        place.setVisibility(View.INVISIBLE);
        TransferData.days_stay = new Stack<>();
        TransferData.vagon = new Stack<>();
        TransferData.place = new Stack<>();
        TransferData.row = new Stack<>();
        TransferData.toShow = new ArrayList<>();
        TransferData.time_limie = 0.f;
        TransferData.price_total = 0.f;
        TransferData.distance_total = 0.f;
        editDatas();
        txt.setText(src_dst.get(position).getFirst() + "---->" + src_dst.get(position).getSecond());
        routeAdapter = new RouteAdapter(this, avlRoutes.get(src_dst.get(position)), src_dst.get(position).getFirst(), src_dst.get(position).getSecond());
        list_routes.setAdapter(routeAdapter);
        list_routes.setLayoutManager(new LinearLayoutManager(this));

        next.setOnClickListener(v -> {
            list_routes.setVisibility(View.VISIBLE);
            txt.setVisibility(View.VISIBLE);

            days = findViewById(R.id.days);
            days.setVisibility(View.INVISIBLE);

            done = findViewById(R.id.dn);
            done.setVisibility(View.INVISIBLE);

            row = findViewById(R.id.vagon);
            if (row.getVisibility() == View.VISIBLE) {
                row.setVisibility(View.INVISIBLE);
            }
            place = findViewById(R.id.place);
            if (place.getVisibility() == View.VISIBLE) {
                place.setVisibility(View.INVISIBLE);
            }
            result = findViewById(R.id.result);
            if (result.getVisibility() == View.VISIBLE) {
                result.setVisibility(View.INVISIBLE);
            }

            this.position++;
            if (position >= src_dst.size()) {
                list_routes.setVisibility(View.INVISIBLE);
                txt.setText("Detalii ticket");
                next.setText("Continua");
                /* Do pdf*/
                makePDF();
            } else {
                if (getNewData(Objects.requireNonNull(avlRoutes.get(src_dst.get(position)))).size() == 0) {
                    txt.setText("No route for this schedule, go back");
                } else {
                    txt.setText(src_dst.get(position).getFirst() + "---->" + src_dst.get(position).getSecond());
                }
                RouteAdapter routeAdapter = new RouteAdapter(this, getNewData(Objects.requireNonNull(avlRoutes.get(src_dst.get(position)))), src_dst.get(position).getFirst(), src_dst.get(position).getSecond());
                list_routes.setAdapter(routeAdapter);
                list_routes.setLayoutManager(new LinearLayoutManager(this));
            }
        });

        press_item();
    }

    @SuppressLint("DefaultLocale")
    public void makePDF() {

        PdfDocument ticket = new PdfDocument();
        PdfDocument.PageInfo ticketInfo = new PdfDocument.PageInfo.Builder(400, 800, 1).create();
        PdfDocument.Page myPage = ticket.startPage(ticketInfo);
        Paint myPain = new Paint();
        StringBuilder text = new StringBuilder();
        textForPdf = new StringBuilder();
        x = 10;
        y = 25;

        done.setVisibility(View.INVISIBLE);
        days.setVisibility(View.INVISIBLE);
        vagon.setVisibility(View.INVISIBLE);
        row.setVisibility(View.INVISIBLE);
        place.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);
        list_routes.setVisibility(View.INVISIBLE);
        for (int i = 0; i < TransferData.toShow.size(); i++) {
            Pair<Pair<String, String>, Pair<RoutesDetails, String>> rt = TransferData.toShow.get(i);
            System.out.println(rt.getSecond().getFirst().getTimeGo() + "asdasdsa");
            text.append("Route: ").append(rt.first.first).append(" -> ").append(rt.first.second).append("\n");
            textForPdf.append("Route: ").append(rt.first.first).append(" -> ").append(rt.first.second).append("\n");
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();

            text.append("Passagers: ").append(TransferData.pasagers).append("\n");
            textForPdf.append("Passagers: ").append(TransferData.pasagers).append("\n");
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();

            String type = findType(rt);
            text.append("Company: ").append(rt.second.second).append("\n").append("You are going with ").append(type).append("\n");
            textForPdf.append("Company: ").append(rt.second.second).append("\n").append("You are going with ").append(type).append("\n");
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();

            if (rt.second.first instanceof Train) {
                String src = TransferData.vagon.pop();
                text.append(src).append("\n");
                textForPdf.append(src).append("\n");
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += myPain.descent() - myPain.ascent();
                textForPdf = new StringBuilder();
            } else if (rt.second.first instanceof Airplane) {
                String plc = TransferData.place.pop();
                text.append("Place ").append(plc).append("\n");
                text.append("Economic class").append("\n");
                textForPdf.append("Place ").append(plc).append("Economic Class").append("\n");
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += myPain.descent() - myPain.ascent();
                textForPdf = new StringBuilder();
            } else if (rt.second.first instanceof Bus || rt.second.first instanceof Boat){
                String day = TransferData.days_stay.pop();
                text.append("You will stay here ").append(day).append("days\n");
                textForPdf.append("You will stay here ").append(day).append("days\n");
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += myPain.descent() - myPain.ascent();
                textForPdf = new StringBuilder();
            }
            text.append("You're leaving at ").append(convertTime(rt).first.first).append(":").append(convertTime(rt).first.second).append(getAMPM(Integer.parseInt(convertTime(rt).first.first))).append("\n");
            textForPdf.append("You're leaving at ").append(convertTime(rt).first.first).append(":").append(convertTime(rt).first.second).append(getAMPM(Integer.parseInt(convertTime(rt).first.first))).append("\n");;
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();

            text.append("You're arriving at").append(convertTime(rt).second.first).append(":").append(convertTime(rt).second.second).append(getAMPM(Integer.parseInt(convertTime(rt).second.first))).append("\n");
            textForPdf.append("You're arriving at").append(convertTime(rt).second.first).append(":").append(convertTime(rt).second.second).append(getAMPM(Integer.parseInt(convertTime(rt).second.first))).append("\n");;
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();


            if (TransferData.students && rt.getSecond().first.getStudents() != -1.f) {
                String res = "";
                Float r = 0.f;
                if (isRon(rt.first.first).equals("EURO")) {
                    r = rt.second.first.getStudents() * 4.9f;
                } else {
                    r = rt.second.first.getStudents();
                }

                text.append("Price students ").append(res).append("RON").append("\n");
                textForPdf.append("Price students ").append(res).append("RON").append("\n");
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += myPain.descent() - myPain.ascent();
                textForPdf = new StringBuilder();

                TransferData.price_total += r;
            } else if (TransferData.retirees && rt.getSecond().first.getOldman() != 0.f) {
                String res = "";
                Float r = 0.f;
                if (isRon(rt.first.first).equals("EURO")) {
                    r = rt.second.first.getOldman() * 4.9f;
                } else {
                    r = rt.second.first.getOldman();
                }
                res = String.format("%.2f", r);
                text.append("Price retirees ").append(res).append("RON").append("\n");
                textForPdf.append("Price retirees ").append(res).append("RON").append("\n");
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += myPain.descent() - myPain.ascent();
                textForPdf = new StringBuilder();

                TransferData.price_total += r;
            } else {
                String res = "";
                Float r = 0.f;
                if (isRon(rt.first.first).equals("EURO")) {
                    r = rt.second.first.getPrice() * 4.9f;
                } else {
                    r = rt.second.first.getPrice();
                }
                res = String.format("%.2f", r);

                TransferData.price_total += r;
                text.append("Price ").append(res).append("RON").append("\n");
                textForPdf.append("Price ").append(res).append("RON").append("\n");
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += myPain.descent() - myPain.ascent();
                textForPdf = new StringBuilder();

            }

            TransferData.distance_total += rt.getSecond().first.getDistance();
            text.append("Distance ").append(rt.getSecond().first.getDistance()).append("KM").append("\n\n");
            textForPdf.append("Distance ").append(rt.getSecond().first.getDistance()).append("KM").append("\n\n");
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();

        }
        if (TransferData.pasagers != 0) {
            text.append("Total price ").append(String.format("%.2f", TransferData.pasagers * TransferData.price_total)).append("RON\n");
            text.append("Total price ").append(String.format("%.2f", TransferData.pasagers * TransferData.price_total / 4.9)).append("EURO\n");

            textForPdf.append("Total price ").append(String.format("%.2f", TransferData.pasagers * TransferData.price_total)).append("RON\n");
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();
            textForPdf.append("Total price ").append(String.format("%.2f", TransferData.pasagers * TransferData.price_total / 4.9)).append("EURO\n");

        } else {
            text.append("Total price ").append(String.format("%.2f", TransferData.price_total)).append("RON\n");
            text.append("Total price ").append(String.format("%.2f", TransferData.price_total / 4.9)).append("EURO\n");

            textForPdf.append("Total price ").append(String.format("%.2f", TransferData.price_total)).append("RON\n");
            myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
            y += myPain.descent() - myPain.ascent();
            textForPdf = new StringBuilder();
            textForPdf.append("Total price ").append(String.format("%.2f", TransferData.price_total / 4.9)).append("EURO\n");
        }

        myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
        y += myPain.descent() - myPain.ascent();
        textForPdf = new StringBuilder();

        text.append("Total Distance ").append(TransferData.distance_total).append("KM");
        textForPdf.append("Total Distance ").append(TransferData.distance_total).append("KM");
        myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
        y += myPain.descent() - myPain.ascent();
        textForPdf = new StringBuilder();

        show = findViewById(R.id.ticket);
        show.setText(text);
        show.setMovementMethod(new ScrollingMovementMethod());


        next.setOnClickListener(v -> {
            show.setVisibility(View.INVISIBLE);
            days.setVisibility(View.VISIBLE);
            days.setText("");
            days.setHint("adauga nume");

            done.setVisibility(View.VISIBLE);
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TransferData.names.add(days.getText().toString());
                    Toast.makeText(CheckoutActivityV2.this, "Nume adugat", Toast.LENGTH_SHORT).show();
                }
            });

            next.setText("Cumpara");
            next.setOnClickListener(g -> {
                text.append("Nume Calator ");
                myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                y += myPain.descent() - myPain.ascent();
                textForPdf = new StringBuilder();

                for (int i = 0; i < TransferData.names.size(); i++) {
                    textForPdf.append(TransferData.names.get(i));
                    myPage.getCanvas().drawText(textForPdf.toString(), x, y, myPain);
                    y += myPain.descent() - myPain.ascent();
                    textForPdf = new StringBuilder();
                }
                try {
                    ticket.finishPage(myPage);
                    String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + src_dst.get(0).getFirst() + "-" + src_dst.get(src_dst.size() - 1).second + ".pdf";
                    File myFile = new File(myFilePath);
                    ticket.writeTo(new FileOutputStream(myFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this, "Tichet cumparat", Toast.LENGTH_LONG).show();
                ticket.close();
            });
        });
    }

    public String isRon(String city) {
        if (city.equals("Mangalia") || city.equals("RamnicuValcea") || city.equals("Constanta")
                || city.equals("Calimanesti") || city.equals("Bucuresti") || city.equals("Sulina") || city.equals("Tulcea"))
            return "RON";
        return "EURO";
    }

    public Pair<Pair<String, String>, Pair<String, String>> convertTime(Pair<Pair<String, String>, Pair<RoutesDetails, String>> rt) {
        Float nr = rt.second.first.getTimeGo();
        Integer full =  (int)Math.floor(Double.parseDouble(nr.toString()));
        String mediator = nr.toString();
        mediator = mediator.substring(mediator.indexOf(".") + 1);
        if (mediator.length() == 1)
            mediator += "0";

        Pair<String, String> first = new Pair<String, String>(full.toString(), mediator);
        Float nr2 = rt.second.first.getTimeStop();
        Integer full2 = (int)Math.floor(Double.parseDouble(nr2.toString()));
        mediator = nr2.toString();
        mediator = mediator.substring(mediator.indexOf(".") + 1);
        if (mediator.length() == 1)
            mediator += "0";

        Pair<String, String> sec = new Pair<String, String>(full2.toString(), mediator);
        return new Pair<Pair<String, String>, Pair<String, String>> (first, sec);
    }

    public String getAMPM(Integer full) {
        String hr2 = "";
        if (full >= 12 && full <= 24) {
            hr2 = "PM";
        } else {
            hr2 = "AM";
        }
        return hr2;
    }

    public String findType(Pair<Pair<String, String>, Pair<RoutesDetails, String>> rt) {
        String type = "";
        if (rt.second.first instanceof Train)
            type = "Train";
        else if (rt.second.first instanceof Bus)
            type = "Bus";
        else if (rt.second.first instanceof Airplane)
            type = "Airplane";
        else if (rt.second.first instanceof Boat)
            type = "Boat";
        return type;
    }

    public ArrayList<Pair<RoutesDetails, String>> getNewData(ArrayList<Pair<RoutesDetails, String>> toChange) {
        ArrayList<Pair<RoutesDetails, String>> to_ret = new ArrayList<>();
        for (Pair<RoutesDetails, String> it : toChange) {
            if (it.getFirst().getTimeGo() > TransferData.time_limie + TransferData.timeStay ||
                    (it.getFirst().getTimeGo() > 0 && TransferData.time_limie < 24)) {
                to_ret.add(new Pair(it.getFirst(), it.getSecond()));
            }
        }
        return to_ret;
    }

    public void press_item() {
        list_routes.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), list_routes ,new RecyclerItemClickListener.OnItemClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override public void onItemClick(View view, int position2) {
                        // do whatever
                        Button done = findViewById(R.id.dn);
                        done.setVisibility(View.VISIBLE);

                        days = findViewById(R.id.days);
                        days.setVisibility(View.VISIBLE);
                        days.setText("");
                        if (position == avlRoutes.size() - 1 || (!TransferData.stops.contains(src_dst.get(position).second))) {
                            days.setHint("Not available days");
                        } else {
                            days.setHint("days to stay here");
                        }
                        list_routes.setVisibility(View.INVISIBLE);
                        txt.setVisibility(View.INVISIBLE);
                        if (days.getText().toString().matches("")) {
                            TransferData.days_stay.push("0");
                        } else {
                            TransferData.days_stay.push(days.getText().toString());
                        }
                        Pair<RoutesDetails, String> rt = getNewData(Objects.requireNonNull(avlRoutes.get(src_dst.get(position)))).get(position2);
                        Pair<Pair<String, String>, Pair<RoutesDetails, String>> to_put = new Pair(new Pair<String, String>(), new Pair<RoutesDetails, String>());
                        to_put.first.first = src_dst.get(position).first;
                        to_put.first.second = src_dst.get(position).second;
                        to_put.second = rt;
                        TransferData.toShow.add(to_put);

                        TransferData.time_limie = rt.getFirst().getTimeStop();

                        if (rt.getFirst() instanceof Train) {
                            vagon = findViewById(R.id.vagon);
                            vagon.setVisibility(View.VISIBLE);
                            vagon.setText("");
                            vagon.setHint("What couch? 1-5");

                            place = findViewById(R.id.place);
                            place.setVisibility(View.VISIBLE);
                            place.setText("");
                            place.setHint("What place? 1-10");
                            done = findViewById(R.id.dn);
                            done.setVisibility(View.VISIBLE);
                            done.setOnClickListener(v -> {
                                if (!vagon.getText().toString().matches("") && ((Train) rt.getFirst()).getClassTrain() != 0) {
                                    result = findViewById(R.id.result);
                                    result.setVisibility(View.VISIBLE);
                                    if (days.getText().toString().matches(""))
                                        days.setText("0");
                                    result.setText("Class " + ((Train) rt.getFirst()).getClassTrain().toString() + " Vagon " + vagon.getText().toString()
                                                    +  "Place " + place.getText().toString() + " \nYou will stay here " + days.getText().toString() +" days");
                                    TransferData.vagon.push(result.getText().toString());
                                }
                            });

                        } else if (rt.getFirst() instanceof Airplane) {
                            row = findViewById(R.id.vagon);
                            place = findViewById(R.id.place);
                            row.setVisibility(View.VISIBLE);
                            row.setText("");
                            place.setVisibility(View.VISIBLE);
                            place.setText("");
                            row.setHint("What row? 1-30");
                            place.setHint("What place? A-F");
                            if (!row.getText().toString().matches("")) {
                                TransferData.row.push(row.getText().toString());
                            }

                            if (!place.getText().toString().matches("")) {
                                TransferData.place.push(place.getText().toString());
                            }

                            done = findViewById(R.id.dn);
                            done.setOnClickListener(v -> {
                                if (!(place.getText().toString().matches("") && row.getText().toString().matches(""))) {
                                    result = findViewById(R.id.result);
                                    result.setVisibility(View.VISIBLE);
                                    if (days.getText().toString().matches(""))
                                        days.setText("0");
                                    result.setText("Place " + place.getText().toString() + row.getText().toString()
                                                    + " \nYou will stay here " + days.getText().toString() +" days");

                                    TransferData.days_stay.push(days.getText().toString());
                                    TransferData.place.push(result.getText().toString());
                                }
                            });

                        } else if (rt.getFirst() instanceof Bus || rt.getFirst() instanceof Boat) {
                            done.setOnClickListener(v -> {
                                result = findViewById(R.id.result);
                                result.setVisibility(View.VISIBLE);
                                if (days.getText().toString().matches(""))
                                    days.setText("0");
                                result.setText(" You will stay here " + days.getText().toString() +" days");
                                TransferData.days_stay.push(days.getText().toString());
                            });
                        }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    public boolean my_contain(final Pair<String, String> key, ArrayList<Pair<String, String>> keysSec) {
        for(int i = 0; i < keysSec.size(); i++) {
            if (keysSec.get(i).first.equals(key.first) && keysSec.get(i).second.equals(key.second)) {
                return true;
            }
        }
        return false;
    }

    public boolean same_route(final RoutesDetails rt, ArrayList<Pair<RoutesDetails, String>> lst) {
        for (Pair<RoutesDetails, String> l : lst) {
            if (l.getFirst().getTimeGo() == rt.getTimeGo() && l.getFirst().getTimeStop() == rt.getTimeStop()
                && l.getFirst().getDistance() == rt.getDistance() && l.getFirst().getPrice() == rt.getPrice()) {
                return true;
            }
        }
        return false;
    }

    public void editDatas() {
        for (AvailableRoute avl : TransferData.routes) {
            Pair<String, String> key = new Pair();
            key.setFirst(avl.getSource());
            key.setSecond(avl.getDestination());
            if (!my_contain(key, src_dst)) {
                src_dst.add(key);
            }
            if (avlRoutes.containsKey(key)) {
                List<RoutesDetails> rts = avl.getRoutes();
                for (RoutesDetails rt : rts) {
                    Pair<RoutesDetails, String> aux = new Pair(rt, avl.getCompany());
                    if (!same_route(rt, Objects.requireNonNull(avlRoutes.get(key)))) {
                        avlRoutes.get(key).add(new Pair(rt, avl.getCompany()));
                    }
                }
            } else {
                avlRoutes.put(key, new ArrayList<Pair<RoutesDetails, String>>());
                List<RoutesDetails> rts = avl.getRoutes();
                for (RoutesDetails rt : rts) {

                    avlRoutes.get(key).add(new Pair(rt, avl.getCompany()));
                }
            }

            if (TransferData.shortest) {
                avlRoutes =  filter_shortest();
                System.out.println("iontru");
            } else if (TransferData.cheapest) {
               avlRoutes =  filter_cheapest();
                System.out.println("iontru la patrat");

            }
        }
    }

    public HashMap<Pair<String, String>, ArrayList<Pair<RoutesDetails, String>>>  filter_shortest() {
        Float min_price = 10000.f;
        HashMap<Pair<String, String>, ArrayList<Pair<RoutesDetails, String>>> temp = this.avlRoutes;
        HashMap<Pair<String, String>, ArrayList<Pair<RoutesDetails, String>>> to_ret = new HashMap<>();

        for (Map.Entry<Pair<String, String>, ArrayList<Pair<RoutesDetails, String>>> elem : temp.entrySet()) {
            float min = 100000.f;
            ArrayList<Pair<RoutesDetails, String>> aux = elem.getValue();
            ArrayList<Pair<RoutesDetails, String>> to_put = new ArrayList<>();

            for (Pair<RoutesDetails, String> el : aux) {
                if (el.first.getDistance() < min) {
                    min = el.first.getDistance();
                }
            }

            for (Pair<RoutesDetails, String> el : aux) {
                if (el.first.getDistance() == min) {
                    if (!same_route(el.first, Objects.requireNonNull(to_put))) {
                        to_put.add(el);
                    }
                }
            }

            to_ret.put(elem.getKey(), to_put);
        }
        return to_ret;
    }

    public HashMap<Pair<String, String>, ArrayList<Pair<RoutesDetails, String>>>  filter_cheapest() {
        Float min_price = 10000.f;
        HashMap<Pair<String, String>, ArrayList<Pair<RoutesDetails, String>>> temp = this.avlRoutes;
        HashMap<Pair<String, String>, ArrayList<Pair<RoutesDetails, String>>> to_ret = new HashMap<>();

        for (Map.Entry<Pair<String, String>, ArrayList<Pair<RoutesDetails, String>>> elem : temp.entrySet()) {
            float min = 100000.f;
            ArrayList<Pair<RoutesDetails, String>> aux = elem.getValue();
            ArrayList<Pair<RoutesDetails, String>> to_put = new ArrayList<>();

            for (Pair<RoutesDetails, String> el : aux) {
                if (el.first.getPrice() < min) {
                    min = el.first.getPrice();
                }
            }

            for (Pair<RoutesDetails, String> el : aux) {
                if (el.first.getPrice() == min) {
                    if (!same_route(el.first, Objects.requireNonNull(to_put))) {
                        to_put.add(el);
                    }
                }
            }

            to_ret.put(elem.getKey(), to_put);
        }
        return to_ret;
    }
}
