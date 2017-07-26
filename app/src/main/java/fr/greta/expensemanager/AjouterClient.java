package fr.greta.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterClient extends AppCompatActivity {
    Button btnAjoutClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_client);
    }


    View.OnClickListener ajouterClient = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FileDownloader myFd = new FileDownloader(AjouterClient.this) {
                @Override
                protected void onPostExecute(String result) {


                    Toast.makeText(AjouterClient.this, result, Toast.LENGTH_LONG).show();

                    Intent ajouterClient = new Intent(AjouterClient.this, ClientDetails.class);
                    startActivity(ajouterClient);
                }
            };
            myFd.setMethod("POST");

            myFd.addVariable( "NomClient", ((EditText) findViewById(R.id.ajoutNom)).getText().toString());
            myFd.addVariable( "PrenomClient", ((EditText) findViewById(R.id.ajoutPrenom)).getText().toString());
            myFd.addVariable( "Adresse1Client", ((EditText) findViewById(R.id.ajoutAdresse1)).getText().toString());
            myFd.addVariable( "Adresse2Client", ((EditText) findViewById(R.id.ajoutAdresse2)).getText().toString());
            myFd.addVariable( "CodePostalClient", ((EditText) findViewById(R.id.ajoutCodePostalClient)).getText().toString());
            myFd.addVariable( "VilleClient", ((EditText) findViewById(R.id.ajoutVilleClient)).getText().toString());
            myFd.addVariable("TelephoneBureauClient", ((EditText) findViewById(R.id.ajoutTelephoneBureauClient)).getText().toString());
            myFd.addVariable("TelephoneMobileClient", ((EditText) findViewById(R.id.ajoutTelephoneMobileClient)).getText().toString());
            myFd.addVariable("MailClient", ((EditText) findViewById(R.id.ajoutMail)).getText().toString());
            myFd.addVariable("BudgetMaxRemboursementClient", ((EditText) findViewById(R.id.ajoutBudgetMaxRemboursementClient)).getText().toString());
            myFd.execute("http://mickaelduprat.fr/API/v1/client");
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
        btnAjoutClient = (Button) findViewById(R.id.ajouter);
        btnAjoutClient.setOnClickListener(ajouterClient);
    }
}


