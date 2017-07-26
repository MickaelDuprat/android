package fr.greta.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AjouterNoteDeFrais extends AppCompatActivity {
    Button btnajoutNDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajouter_note_de_frais);
    }


    View.OnClickListener ajouterNoteDeFrais = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FileDownloader myFd = new FileDownloader(AjouterNoteDeFrais.this) {
                @Override
                protected void onPostExecute(String result) {


                    Toast.makeText(AjouterNoteDeFrais.this, result, Toast.LENGTH_LONG).show();
                    Log.i("resuuuuuuuuuuuuuuuult",result);

                    Intent ajouterNDF = new Intent(AjouterNoteDeFrais.this, NoteDeFraisDetails.class);
                    startActivity(ajouterNDF);
                }
            };
            myFd.setMethod("POST");

            myFd.addVariable( "IntituleNDF", ((EditText) findViewById(R.id.ajoutIntituleNDF)).getText().toString());
            myFd.addVariable( "DateNDF", ((EditText) findViewById(R.id.ajoutDateNDF)).getText().toString());
            myFd.addVariable( "MotifNDF", ((EditText) findViewById(R.id.ajoutMotifNDF)).getText().toString());
            myFd.addVariable( "MontantPrevu", ((EditText) findViewById(R.id.ajoutMontantPrevu)).getText().toString());
            myFd.addVariable( "EtatNDF", ((EditText) findViewById(R.id.ajoutEtatNDF)).getText().toString());
            myFd.addVariable( "Commentaire", ((EditText) findViewById(R.id.ajoutCommentaire)).getText().toString());
            myFd.addVariable("NbreNuiteesSiHotellerie", ((EditText) findViewById(R.id.ajoutNbreNuiteesSiHotellerie)).getText().toString());
            myFd.addVariable("NbreRepasSiRestauration", ((EditText) findViewById(R.id.ajoutNbreRepasSiRestauration)).getText().toString());
            myFd.addVariable("CoordonneesGPSDepartSiTransport", ((EditText) findViewById(R.id.ajoutCoordonneesGPSDepartSiTransport)).getText().toString());
            myFd.addVariable("CoordonneesGPSArriveeSiTransport", ((EditText) findViewById(R.id.ajoutCoordonneesGPSArriveeSiTransport)).getText().toString());

            myFd.addVariable("TypeDeTransport", ((EditText) findViewById(R.id.ajoutTypeDeTransport)).getText().toString());
            myFd.addVariable("DistanceSiTransport", ((EditText) findViewById(R.id.ajoutDistanceSiTransport)).getText().toString());
            myFd.addVariable("Login", ((EditText) findViewById(R.id.ajoutLogin)).getText().toString());
            myFd.addVariable("IdClient", ((EditText) findViewById(R.id.ajoutIdClient)).getText().toString());

            myFd.execute("http://mickaelduprat.fr/API/v1/notedefrais");
        }

    };

    @Override
    protected void onResume() {
        super.onResume();
        btnajoutNDF = (Button) findViewById(R.id.ajouterNDF);
        btnajoutNDF.setOnClickListener(ajouterNoteDeFrais);

    }
}

