package fr.greta.expensemanager;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NoteDeFraisDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_de_frais_details);

        //clientAdresse1View=(TextView)findViewById(R.id.Adresse1Client);

        Intent itt = getIntent();
        String NoteDeFraisId = itt.getStringExtra(Main2Activity.NoteDeFrais_ID); //EXTRA

        // Toast.makeText(ClientDetails.this, String.valueOf(clientId), Toast.LENGTH_SHORT).show();


        new FileDownloader(NoteDeFraisDetails.this) {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                JSONObject jsonObj;
                try {
                    jsonObj = new JSONObject(result);

                    JSONArray notedefrais = jsonObj.getJSONArray("noteDeFrais");
                    JSONObject curNDF = notedefrais.getJSONObject(0);

                    //Récuperer les valeurs de mon tableau
                    int NoteDeFraisId = curNDF.getInt("IdNoteDeFrais");
                    String IntituleNDF = curNDF.getString("IntituleNDF");
                    String MotifNDF = curNDF.getString("MotifNDF");
                    String MontantPrevu = curNDF.getString("MontantPrevu");
                    String Date = curNDF.getString("DateNDF");
                    String Commentaire = curNDF.getString("Commentaire");


                    TextView IntitulteView = (TextView) findViewById(R.id.IntituleNDF);
                    IntitulteView.setText(IntituleNDF);

                    EditText MotifNDFView = (EditText) findViewById(R.id.MotifNDF);
                    MotifNDFView.setText(MotifNDF);

                    TextView MontantPrevuView = (TextView) findViewById(R.id.MontantPrevu);
                    MontantPrevuView.setText(MontantPrevu);

                    //currAddress1 = clientAdresse1View.getText().toString();

                    TextView DateNDFView = (TextView) findViewById(R.id.DateNDF);
                    DateNDFView.setText(Date);

                    //  currAddress2 = clientAddress2View.getText().toString();

                    TextView CommentaireView = (TextView) findViewById(R.id.Commentaire);
                    CommentaireView.setText(Commentaire);

                    //currMail = clientMailView.getText().toString();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute("http://mickaelduprat.fr/API/v1/notedefrais/" + NoteDeFraisId);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
