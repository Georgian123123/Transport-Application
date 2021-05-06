package utilites;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

import available_routes.RoutesDetails;

public class RouteDialog extends AppCompatDialogFragment {
    private String[] Companies;
    private Integer position;
    private ArrayList<ArrayList<AvailableRoute>> ourRoutes;

    public RouteDialog(String[] companies, Integer position, ArrayList<ArrayList<AvailableRoute>> ourRoutes) {
        Companies = companies;
        this.position = position;
        this.ourRoutes = ourRoutes;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Information about your route");
        ArrayList<AvailableRoute> avl = ourRoutes.get(position);
        StringBuilder message  = new StringBuilder();
        message.append("Your departure place is ").append(avl.get(0).getSource()).append("\n");
        if (avl.size() > 1) {
            message.append("You are going to travel through those cities : ");
            for (int i = 1; i < avl.size(); i++) {
                AvailableRoute rt = avl.get(i);
                if (i == avl.size() - 1) {
                    if (!message.toString().contains(rt.getDestination()))
                        message.append(rt.getDestination());
                } else {
                    if (!message.toString().contains(rt.getDestination()))
                        message.append(rt.getDestination()).append(", ");
                }
            }
        }
        message.append("\n").append("Your arrival place is ").append(avl.get(avl.size() - 1).getDestination());
        message.append("\n\n");
        message.append("All the companies that supports this trip are: ");
        for (int i = 0; i < avl.size(); i++) {
            AvailableRoute rt = avl.get(i);
            if (i == avl.size() - 1) {
                if (!message.toString().contains(rt.getCompany()))
                    message.append(rt.getCompany());
            } else {
                if (!message.toString().contains(rt.getCompany()))
                    message.append(rt.getCompany()).append(", ");
            }
        }

        message.append("\n");
        builder.setMessage(message);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
