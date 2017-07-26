package fr.greta.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class ModifierClient extends AppCompatActivity {
Button btnModifier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_client);





        Intent itt = getIntent();
        String clientId= itt.getStringExtra(TacheAdapter.CLIENT_ID);


        new FileDownloader(ModifierClient.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.i("JSOOOOOOOOOOOOOOOOOOONN",result);

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);

                    JSONArray clients = jsonObj.getJSONArray("client");
                    JSONObject curClient = clients.getJSONObject(0);

                    //Récuperer les valeurs de mon tableau
                    int clientId=curClient.getInt("IdClient");
                    String NomClient = curClient.getString("NomClient");
                    String clientPrenom = curClient.getString("PrenomClient");
                    String client1Adresse = curClient.getString("Adresse1Client");
                    String Adresse2Client = curClient.getString("Adresse2Client");
                    String CodePostalClient = curClient.getString("CodePostalClient");
                    String TelephoneBureauClient = curClient.getString("TelephoneBureauClient");
                    String TelephoneMobileClient = curClient.getString("TelephoneMobileClient");
                    String MailClient = curClient.getString("MailClient");
                    int BudgetMaxRemboursementClient = curClient.getInt("BudgetMaxRemboursementClient");
                    String clientVille = curClient.getString("VilleClient");



                    EditText clientNameView = (EditText) findViewById(R.id.ModifierNom);
                    clientNameView.setText(NomClient);

                    EditText clientFirstnameView = (EditText) findViewById(R.id.ModifierPrenom);
                    clientFirstnameView.setText(clientPrenom);

                    EditText clientAdresse1View = (EditText) findViewById(R.id.ModifierAdresse1);
                    clientAdresse1View.setText(client1Adresse);

                    EditText clientAddress2View = (EditText) findViewById(R.id.ModifierAdresse2);
                    clientAddress2View.setText(Adresse2Client);

                    EditText CodePostalClientView = (EditText) findViewById(R.id.ModifierCodePostalClient);
                    CodePostalClientView.setText(CodePostalClient);

                    EditText TelephoneBureauClientView = (EditText) findViewById(R.id.ModifierTelephoneBureauClient);
                    TelephoneBureauClientView.setText(TelephoneBureauClient);

                    EditText TelephoneMobileClientView = (EditText) findViewById(R.id.ModifierTelephoneMobileClient);
                    TelephoneMobileClientView.setText(TelephoneMobileClient);

                    EditText MailClientView = (EditText) findViewById(R.id.ModifierMail);
                    MailClientView.setText(MailClient);

                    /*EditText BudgetMaxRemboursementClientView = (EditText) findViewById(R.id.ModifierBudgetMaxRemboursementClient);
                    BudgetMaxRemboursementClientView.setText(BudgetMaxRemboursementClient);*/


                    EditText clientVilleView = (EditText) findViewById(R.id.ModifierVilleClient);
                    clientVilleView.setText(clientVille);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("http://mickaelduprat.fr/API/v1/client/"+clientId);
    }

    View.OnClickListener UpdateClient = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FileDownloader myFd = new FileDownloader(ModifierClient.this) {
                @Override
                protected void onPostExecute(String result) {


                    Toast.makeText(ModifierClient.this, result, Toast.LENGTH_LONG).show();

                    Intent ajouterClient = new Intent(ModifierClient.this, PageAccueil.class);
                    startActivity(ajouterClient);
                }
            };

            Intent itt = getIntent();
            String clientId= itt.getStringExtra(TacheAdapter.CLIENT_ID);

            myFd.setMethod("PUT");

            public JSONObject psqut( "NomClient", ((EditText) findViewById(R.id.ajoutNom)).getText().toString());
            myFd.addVariable( "PrenomClient", ((EditText) findViewById(R.id.ajoutPrenom)).getText().toString());
            myFd.addVariable( "Adresse1Client", ((EditText) findViewById(R.id.ajoutAdresse1)).getText().toString());
            myFd.addVariable( "Adresse2Client", ((EditText) findViewById(R.id.ajoutAdresse2)).getText().toString());
            myFd.addVariable( "CodePostalClient", ((EditText) findViewById(R.id.ajoutCodePostalClient)).getText().toString());
            myFd.addVariable( "VilleClient", ((EditText) findViewById(R.id.ajoutVilleClient)).getText().toString());
            myFd.addVariable("TelephoneBureauClient", ((EditText) findViewById(R.id.ajoutTelephoneBureauClient)).getText().toString());
            myFd.addVariable("TelephoneMobileClient", ((EditText) findViewById(R.id.ajoutTelephoneMobileClient)).getText().toString());
            myFd.addVariable("MailClient", ((EditText) findViewById(R.id.ajoutMail)).getText().toString());
            myFd.addVariable("BudgetMaxRemboursementClient", ((EditText) findViewById(R.id.ajoutBudgetMaxRemboursementClient)).getText().toString());
            myFd.execute("http://mickaelduprat.fr/API/v1/client/"+clientId);
        }

    };


    @Override
    protected void onResume() {
        super.onResume();

        btnModifier = (Button)findViewById(R.id.modifier);
        btnModifier.setOnClickListener(UpdateClient);
    }


}
