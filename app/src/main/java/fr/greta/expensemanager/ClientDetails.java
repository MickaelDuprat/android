package fr.greta.expensemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Arsen Kubatyan on 17/07/2017.
 */

public class ClientDetails extends AppCompatActivity {


    Button Appeler;
    Button EnvoyerMail;
    Button OpenMaps2;
    Button OpenMaps1;
    Button modifier;

    TextView textLongitude;
    TextView textLatitude;
    Button btnLngLat;

    public String currAddress2;
    public String currAddress1;
    public String currMail;
    TextView clientAdresse1View;



    View.OnClickListener AfficherLongitudeEtLatitude = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button btnModifier;


            new FileDownloader(ClientDetails.this) {
                @Override
                protected void onPostExecute(String result) {
                    super.onPostExecute(result);


                    JSONObject jsonObj;
                    try {
                        jsonObj = new JSONObject(result);
                        JSONArray results = jsonObj.getJSONArray("client");
                        JSONObject res0 = results.getJSONObject(0);
                        JSONObject geom = res0.getJSONObject("geometry");
                        JSONObject loc = geom.getJSONObject("location");
                        String lat = String.valueOf(loc.getDouble("lat"));
                        String lng = String.valueOf(loc.getDouble("lng"));


                        textLatitude = (TextView) findViewById(R.id.btnLattitude);
                        textLatitude.setText(lat);

                        textLongitude = (TextView) findViewById(R.id.btnLongitude);
                        textLongitude.setText(lng);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }.execute("https://maps.googleapis.com/maps/api/geocode/json?address=2+avenue+pierre+angot,pau,france");
            //.execute("https://maps.googleapis.com/maps/api/geocode/json?address=" + clientAddressView.getText().toString());

        }
    };
    View.OnClickListener GoMaps2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent ittMaps = new Intent();
            ittMaps.setAction(Intent.ACTION_VIEW);
            ittMaps.setData(Uri.parse("geo:0,0?q="+currAddress2));
            ittMaps.setPackage("com.google.android.apps.maps");

            if (ittMaps.resolveActivity(getPackageManager()) != null){
                startActivity(ittMaps);
            } else {
                Toast.makeText(ClientDetails.this,
                        "Impossible d'ouvir l'application Maps", Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener GoMaps1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent ittMaps = new Intent();
            ittMaps.setAction(Intent.ACTION_VIEW);
            ittMaps.setData(Uri.parse("geo:0,0?q="+currAddress1));
            ittMaps.setPackage("com.google.android.apps.maps");

            if (ittMaps.resolveActivity(getPackageManager()) != null){
                startActivity(ittMaps);
            } else {
                Toast.makeText(ClientDetails.this,
                        "Impossible d'ouvir l'application Maps", Toast.LENGTH_SHORT).show();
            }
        }
    };


    View.OnClickListener EnvoyerMailClient = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", currMail, null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sujet");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Saisissez votre mail...");
            startActivity(Intent.createChooser(emailIntent, "Saisissez votre mail..."));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_details);

        clientAdresse1View=(TextView)findViewById(R.id.Adresse1Client);

        Intent itt = getIntent();
        String clientId = itt.getStringExtra(MainActivity.CLIENT_ID); //EXTRA

        // Toast.makeText(ClientDetails.this, String.valueOf(clientId), Toast.LENGTH_SHORT).show();


        new FileDownloader(ClientDetails.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);

                    JSONArray clients = jsonObj.getJSONArray("client");
                    JSONObject curClient = clients.getJSONObject(0);

                    //Récuperer les valeurs de mon tableau
                    int clientId = curClient.getInt("IdClient");
                    String clientNom = curClient.getString("NomClient");
                    String clientPrenom = curClient.getString("PrenomClient");
                    String client1Adresse = curClient.getString("Adresse1Client");
                    String Adresse2Client = curClient.getString("Adresse2Client");
                    String clientMail = curClient.getString("VilleClient");



                    TextView clientNameView = (TextView) findViewById(R.id.clientName);
                    clientNameView.setText(clientNom);

                    EditText clientFirstnameView = (EditText) findViewById(R.id.clientFirstName);
                    clientFirstnameView.setText(clientPrenom);

                    TextView clientAdresse1View = (TextView) findViewById(R.id.Adresse1Client);
                    clientAdresse1View.setText(client1Adresse);

                    currAddress1 = clientAdresse1View.getText().toString();

                    TextView clientAddress2View = (TextView) findViewById(R.id.Adresse2Client);
                    clientAddress2View.setText(Adresse2Client);

                    currAddress2 = clientAddress2View.getText().toString();

                    TextView clientMailView = (TextView) findViewById(R.id.clientMail);
                    clientMailView.setText(clientMail);

                    currMail = clientMailView.getText().toString();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("http://mickaelduprat.fr/API/v1/client/"+clientId);
    }



    View.OnClickListener UpdateClient = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent modifierClient = new Intent(ClientDetails.this, ModifierClient.class);
            startActivity(modifierClient);



        }

    };

    @Override
    protected void onResume() {
        super.onResume();


        EnvoyerMail = (Button) findViewById(R.id.btnMail);
        EnvoyerMail.setOnClickListener(EnvoyerMailClient);

        OpenMaps2 = (Button) findViewById(R.id.btnMaps2);
        OpenMaps2.setOnClickListener(GoMaps2);

        OpenMaps1 = (Button) findViewById(R.id.btnMaps1);
        OpenMaps1.setOnClickListener(GoMaps1);

        modifier = (Button)findViewById(R.id.Mofifier);
        modifier.setOnClickListener(UpdateClient);




        btnLngLat = (Button) findViewById(R.id.btnLongitudeLatitude);
        btnLngLat.setOnClickListener(AfficherLongitudeEtLatitude);




    }
}



