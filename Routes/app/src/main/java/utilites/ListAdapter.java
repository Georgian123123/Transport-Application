package utilites;

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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import available_routes.RoutesDetails;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    String[] Source;
    String[] Destination;
    String[] Companies;
    Context context;
    int image;
    int imageBuy;
    int imageDetails;
    ArrayList<ArrayList<AvailableRoute>> ourRoutes;
    public int pos;
    FragmentManager manager;

    public ListAdapter(Context ct, String[] src, String[] dst, String[] cmps, int img, int imgbuy,
                       int imagedetails, ArrayList<ArrayList<AvailableRoute>> routes) {
        context = ct;
        Source = src;
        Companies = cmps;
        Destination = dst;
        image = img;
        imageBuy = imgbuy;
        imageDetails = imagedetails;
        ourRoutes = new ArrayList<ArrayList<AvailableRoute>>(routes);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.the_route, parent, false);
        manager = ((AppCompatActivity)context).getSupportFragmentManager();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.src.setText(Source[position]);
        holder.dst.setText(Destination[position]);
        holder.imageView.setImageResource(image);
        holder.imageView2.setImageResource(imageBuy);
        holder.imageView3.setImageResource(imageDetails);
        holder.cmp.setText(Companies[position]);

        holder.imageView3.setOnClickListener(v -> {
                openDialog(Companies, position, ourRoutes);
        });
        holder.imageView2.setOnClickListener(v -> {
            /* Start new activity*/
            TransferData.routes = ourRoutes.get(position);
            Intent intent = new Intent(context, CheckoutActivityV2.class);
            context.startActivity(intent);
        });
    }

    public void openDialog(String[] Companies, int position, ArrayList<ArrayList<AvailableRoute>> ourRoutes) {
        RouteDialog rtDialog = new RouteDialog(Companies, position, ourRoutes);
        rtDialog.show(manager, "Route Dialog");
    }
    @Override
    public int getItemCount() {
        return Source.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView src, dst, cmp;
        ImageView imageView, imageView2, imageView3;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            src = itemView.findViewById(R.id.Src);
            dst = itemView.findViewById(R.id.Dest);
            cmp = itemView.findViewById(R.id.Company);
            imageView = itemView.findViewById(R.id.arrow);
            imageView2 = itemView.findViewById(R.id.buy);
            imageView3 = itemView.findViewById(R.id.details);
        }
    }
}
