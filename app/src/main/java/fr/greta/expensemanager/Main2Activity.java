package fr.greta.expensemanager;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    static public String NoteDeFrais_ID="fr.greta.getclientjson.NoteDeFraisId";

    TextView tvNDF = null;
    public ListView listViewNoteDeFrais;
    Tache2Adapter taNDF;
    public ArrayList<MesNotesDeFrais> dataNDF = null;
    ListView listingNDF;



    public void onClick(View v) {
        MiseAjourListe();

    }



    private void MiseAjourListe() {

        new FileDownloader(Main2Activity.this) {
            @Override
            protected void onPostExecute(String result) { //les resultats de Json reviens dans ma variable result
                super.onPostExecute(result);
                Log.i("gretaaaaaaaaaaaaaaaaaaa",result);

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);
                    JSONArray NDF = jsonObj.getJSONArray("noteDeFrais");


                    dataNDF = new ArrayList<MesNotesDeFrais>();

                    int len = NDF.length();
                    for (int i = 0; i < len; i++) {
                        Log.i("----------------greta", String.valueOf(i));
                        JSONObject curNoteDeFrais = NDF.getJSONObject(i);

                        int NoteDeFraisId = curNoteDeFrais.getInt("IdNoteDeFrais");
                        String DateNDF = curNoteDeFrais.getString("DateNDF");
                        String TypeDeTransport = curNoteDeFrais.getString("TypeDeTransport");

                        String AssocierNDF = DateNDF + " " + TypeDeTransport;
                        Log.i("valeeeuurrrr",AssocierNDF);
                        MesNotesDeFrais newNDF = new MesNotesDeFrais(AssocierNDF,NoteDeFraisId);
                        dataNDF.add(newNDF);

                    }


                    // Adding items to listview
                    listingNDF = (ListView) findViewById(R.id.listViewNoteDeFrais); //On initialise le listView
                    taNDF = new Tache2Adapter(Main2Activity.this, R.layout.item_notedefrais, dataNDF); //On initialise l'Adapter
                    listingNDF.setAdapter(taNDF);

                    // listing.setTextFilterEnabled(true);


                    listViewNoteDeFrais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            MesNotesDeFrais selectedNoteDeFrais = (MesNotesDeFrais) adapterView.getItemAtPosition(position);
                            int NoteDeFraisId = selectedNoteDeFrais.getNoteDeFraisId();

                            //Recupere le client qui est selectionné
                            // Recupere le Item qui est selectionner  et ca me retourne un Objet et je le converti en Client


                            Intent intent = new Intent(Main2Activity.this, NoteDeFraisDetails.class);

                            intent.putExtra(NoteDeFrais_ID, String.valueOf(NoteDeFraisId));
                            startActivity(intent);

                        }

                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("http://mickaelduprat.fr/API/v1/notedefrais");
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }




    @Override
    protected void onResume() {
        super.onResume();
        tvNDF = (TextView) findViewById(R.id.tvNoteDeFrais);
        listViewNoteDeFrais = (ListView) findViewById(R.id.listViewNoteDeFrais);
        MiseAjourListe();

    }

    @Override
    protected void onPause() {
        super.onPause();
        tvNDF = null;

    }



}
