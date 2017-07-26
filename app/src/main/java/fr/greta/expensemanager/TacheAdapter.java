package fr.greta.expensemanager;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by Arsen Kubatyan on 17/07/2017.
 */


class TacheAdapter extends ArrayAdapter<MesClients> {

    static public String CLIENT_ID="fr.greta.getclientjson";
    private List<MesClients> data; // Le tableau représentant les données à insérer dans la liste
    private int listItemResLayout; // Le Layout de la vue personnalisée, pour chaque ligne de la liste
    private Context context; // Le Context de la vue

    private ArrayList<MesClients> origData;

    public TacheAdapter(Context context, int resource, List<MesClients> data) {
        super(context, resource, data);
        this.data = data;
        this.listItemResLayout = resource;
        this.context = context;

        //this.origData =new MesClients();
        this.origData = new ArrayList<MesClients>(this.data);

    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        final ViewHolder holder;

        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(listItemResLayout, parent, false);

            final TextView tvPastille = (TextView) view.findViewById(R.id.pastille);
            final TextView tvUserFullname = (TextView) view.findViewById(R.id.userFullname);
            final Button tvEditer = (Button) view.findViewById(R.id.editer);


            /*tvEditer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    MesClients selectedClient = (MesClients) View.setTag(position);
                    int clientId = selectedClient.getClientId();

                    Intent intent = new Intent(context, ClientDetails.class);
                    intent.putExtra(CLIENT_ID, String.valueOf(clientId));
                    context.startActivity(intent);
                   /* int pos = Integer.parseInt(v.getTag().toString());
                    Toast.makeText(context,"Item in position " + pos + " clicked",Toast.LENGTH_LONG).show();*/


                      //  v.getContext().startActivity(new Intent(context, ModifierClient.class));
                      //  Toast.makeText(getContext(), "was clicked in" + position, Toast.LENGTH_SHORT).show();


               /* }

            });*/



            tvEditer.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(context, ModifierClient.class);
                   intent.putExtra(CLIENT_ID,String.valueOf(position));
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    Toast.makeText(getContext(), "was clicked in" + position, Toast.LENGTH_SHORT).show();

                }

                });






           // final TextView tvCodePostal = (TextView) view.findViewById(R.id.userCodePostal);

            // On stocke les Widgets dans le Holder pour optimiser les appels au "view.findViewById"
            holder = new ViewHolder();
            holder.pastille = tvPastille;
            holder.fullName = tvUserFullname;
            holder.update =  tvEditer;

            //holder.codePostal = tvCodePostal;
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();

        }

        final MesClients currentClient = data.get(position);

        if (currentClient != null) {
            if (currentClient.getFullName().equals("Ervin Howell")) {
                holder.pastille.setBackgroundColor(0xFF00FF00);
            }
            holder.fullName.setText(currentClient.getFullName());
          //  holder.codePostal.setText(currentClient.getCodePostal());
        }

        return view;
    }


    /*@Override
    public MesClients getItem(int position) {

        return found.get(position);
    }*/



    @Override
    public Filter getFilter(){
        return new Filter(){

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults result = new FilterResults();

                if (constraint != null && constraint.toString().length() > 0) {
                    List<MesClients> found = new ArrayList<MesClients>();
                    for(MesClients item: origData){
                        if(item.toString().toLowerCase().contains(constraint)){
                            found.add(item);
                        }
                    }

                    result.values = found;
                    result.count = found.size();
                }else {
                    result.values = origData;
                    result.count = origData.size();
                }
                return result;


            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                clear();
                for (MesClients item : (List<MesClients>) results.values) {
                    add(item);
                }
                notifyDataSetChanged();


            }

        };
    }



}
    class ViewHolder {
    TextView pastille;
    TextView fullName;
        Button update;
    TextView codePostal;

}

