package fr.greta.expensemanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arsen Kubatyan on 24/07/2017.
 */
class Tache2Adapter extends ArrayAdapter<MesNotesDeFrais> {

    private List<MesNotesDeFrais>dataNDF; // Le tableau représentant les données à insérer dans la liste
    private int listItemResLayout; // Le Layout de la vue personnalisée, pour chaque ligne de la liste
    private Context context; // Le Context de la vue


    public Tache2Adapter(Context context, int resource, List<MesNotesDeFrais> dataNDF) {
        super(context, resource, dataNDF);
        this.dataNDF = dataNDF;
        this.listItemResLayout = resource;
        this.context = context;

        //this.origData =new MesClients();


    }


    @Override
    public View getView( int position,View convertView, ViewGroup parent) {

        View view = convertView;
        final ViewHolder2 holder;

        if (null == view) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(listItemResLayout, parent, false);

            final TextView IntNoteDeFrais = (TextView) view.findViewById(R.id.IntNoteDeFrais);
            final Button tvEditer = (Button) view.findViewById(R.id.editerNDF);




            // final TextView tvCodePostal = (TextView) view.findViewById(R.id.userCodePostal);

            // On stocke les Widgets dans le Holder pour optimiser les appels au "view.findViewById"
            holder = new ViewHolder2();
            holder.fullIntittuleNDF = IntNoteDeFrais;
            holder.update = tvEditer;

            //holder.codePostal = tvCodePostal;
            view.setTag(holder);

        } else {
            holder = (ViewHolder2) view.getTag();

        }

        return view;
    }
}

class ViewHolder2 {
    TextView fullIntittuleNDF;
    Button update;

}

