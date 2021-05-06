package utilites;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.routes.CheckoutActivity;
import com.example.routes.CheckoutActivityV2;
import com.example.routes.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import available_routes.Airplane;
import available_routes.RoutesDetails;
import available_routes.Train;

public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.MyViewHolder> {
    Context context;
    ArrayList<Pair<RoutesDetails, String>> ourRoutes;
    FragmentManager manager;
    String src, dst;

    public RouteAdapter(Context ct, ArrayList<Pair<RoutesDetails, String>> routes, String src, String dst) {
        ourRoutes = new ArrayList<Pair<RoutesDetails, String>>(routes);
        context = ct;
        this.src = src;
        this.dst = dst;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.the_avlroute, parent, false);
        manager = ((AppCompatActivity)context).getSupportFragmentManager();
        return new MyViewHolder(view);
    }

    public String isRon(String city) {
        if (city.equals("Mangalia") || city.equals("RamnicuValcea") || city.equals("Constanta")
                || city.equals("Calimanesti") || city.equals("Bucuresti") || city.equals("Sulina")
                || city.equals("Tulcea"))
            return "RON";
        return "EURO";
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String hr = "";
        int full = (int)Math.floor(ourRoutes.get(position).getFirst().getTimeGo());
        String mediator = ourRoutes.get(position).getFirst().getTimeGo().toString();
        mediator = mediator.substring(mediator.indexOf(".") + 1);
        if (mediator.length() == 1)
            mediator += "0";
        if (full >= 12 && full <= 24) {
            hr = "PM";
        } else {
            hr = "AM";
        }
        holder.time_go.setText(full + ":" + mediator + hr);

        full = (int)Math.floor(ourRoutes.get(position).getFirst().getTimeStop());
        mediator = ourRoutes.get(position).getFirst().getTimeStop().toString();
        mediator = mediator.substring(mediator.indexOf(".") + 1);
        if (mediator.length() == 1) {
            System.out.println(mediator);
            mediator += "0";
        }
        if (full >= 12 && full <= 24) {
            hr = "PM";
        } else {
            hr = "AM";
        }
        holder.time_leave.setText(full + ":" + mediator + hr);

        holder.price.setText(ourRoutes.get(position).getFirst().getPrice().toString() + isRon(src));
        holder.distance.setText(ourRoutes.get(position).getFirst().getDistance().toString() + "km");
        if (ourRoutes.get(position).getFirst() instanceof Train) {
            if (((Train) ourRoutes.get(position).getFirst()).getClassTrain() != 0) {
                holder.clas.setText("class " + ((Train) ourRoutes.get(position).getFirst()).getClassTrain().toString());
            } else {
                holder.clas.setText("class 2");
            }
        } else if (ourRoutes.get(position).getFirst() instanceof Airplane) {
            holder.clas.setText("Economic");
        }
        holder.company.setText(ourRoutes.get(position).getSecond());
    }

    @Override
    public int getItemCount() {
        return ourRoutes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView time_go, time_leave, price, distance, clas, company;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            time_go = itemView.findViewById(R.id.timeGo);
            time_leave = itemView.findViewById(R.id.timeArrive);
            price = itemView.findViewById(R.id.price);
            distance = itemView.findViewById(R.id.distance);
            clas = itemView.findViewById(R.id.clas);
            company = itemView.findViewById(R.id.company);
        }
    }
}
