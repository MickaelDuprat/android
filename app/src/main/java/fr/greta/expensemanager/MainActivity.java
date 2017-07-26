package fr.greta.expensemanager;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

   static public String CLIENT_ID="fr.greta.getclientjson.clientId";

    TextView tvClients = null;
    public ListView listView;
    TacheAdapter ta;
    public ArrayList<MesClients> data = null;
    ListView listing;
    SearchView searchView;


    public void onClick(View v) {
        MiseAjourListe();

    }



    private void MiseAjourListe() {

        new FileDownloader(MainActivity.this) {
            @Override
            protected void onPostExecute(String result) { //les resultats de Json reviens dans ma variable result
                super.onPostExecute(result);
                Log.i("gretaaaaaaaaaaaaaaaaaaa",result);

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);
                    JSONArray users = jsonObj.getJSONArray("clients");


                    data = new ArrayList<MesClients>();

                    int len = users.length();
                    for (int i = 0; i < len; i++) {
                        Log.i("----------------greta", String.valueOf(i));
                        JSONObject curUser = users.getJSONObject(i);

                        int clientId = curUser.getInt("IdClient");
                        String clientNom = curUser.getString("NomClient");
                        String clientPrenom = curUser.getString("PrenomClient");
                        String clientAdresse1 = curUser.getString("Adresse1Client");

                        String userFullName = clientPrenom + " " + clientNom;
                       // String userCodePostal = "Code Postale: " + clientAdresse1;

                        MesClients newClient = new MesClients(userFullName,clientId);
                        data.add(newClient);

                    }


                    // Adding items to listview
                    listing = (ListView) findViewById(R.id.listViewClients); //On initialise le listView
                    ta = new TacheAdapter(MainActivity.this, R.layout.item_client, data); //On initialise l'Adapter
                    listing.setAdapter(ta);

                   // listing.setTextFilterEnabled(true);


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            MesClients selectedClient = (MesClients) adapterView.getItemAtPosition(position);
                            int clientId = selectedClient.getClientId();

                            //Recupere le client qui est selectionné
                            // Recupere le Item qui est selectionner  et ca me retourne un Objet et je le converti en Client


                            Intent intent = new Intent(MainActivity.this, ClientDetails.class);

                            intent.putExtra(CLIENT_ID, String.valueOf(clientId));
                            startActivity(intent);



                            /*Intent modifierLeClient = new Intent(MainActivity.this, ModifierClient.class);

                            intent.putExtra(CLIENT_ID, String.valueOf(clientId));
                            startActivity(modifierLeClient);*/


                /*Toast.makeText( MainActivity.this,
                                        "Selection du client id = " + String.valueOf(selectedClient.getClientId()),
                                        Toast.LENGTH_SHORT
                                ).show();*/

                        }

                    });
                    //tvClients.setText(strAllClients);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("http://mickaelduprat.fr/API/v1/client");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView)MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override

            public boolean onQueryTextChange(String s) {

                Log.i("Nomad", "onQueryTextChange");

                if (TextUtils.isEmpty(s)) {
                    ta.getFilter().filter("");
                    Log.i("Nomad", "onQueryTextChange Empty String");
                   // listing.clearTextFilter();
                } else {
                    Log.i("Nomad", "onQueryTextChange " + s.toString());
                    ta.getFilter().filter(s.toString());
                }
                return true;
                /*Log.i("fileeeeeeeer", s.toString().trim());
                return false;*/
            }
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.i("Nomad", "onQueryTextSubmit");
                return false;


              /* Log.i("fileeeeeeeer", query.toString().trim());

                ta.getFilter().filter(query.toString().trim());
                listing.invalidate();
               // ta.getFilter().filter(query);
                return true;*/
            }

            public boolean onClose() {
                Log.i("Nomad", "onClose");
                return false;
            }


        });

        return true;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }




    @Override
    protected void onResume() {
        super.onResume();
        tvClients = (TextView) findViewById(R.id.tvClients);
        listView = (ListView) findViewById(R.id.listViewClients);


        MiseAjourListe();


    }






    @Override
    protected void onPause() {
        super.onPause();
        tvClients = null;

    }



}
