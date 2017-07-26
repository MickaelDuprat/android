package fr.greta.expensemanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PageAccueil extends AppCompatActivity {
    Button btnConsulterLesClient;
    Button btnAjouterLesClient;
    Button btnConsulterLesNDF;
    Button ajoutNDF;


    View.OnClickListener ConsulterLesClients = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                Intent cosulterClient = new Intent(PageAccueil.this, MainActivity.class);
                startActivity(cosulterClient);
            }


        };

    View.OnClickListener AjouterDesClient = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent ajouterClient = new Intent(PageAccueil.this, AjouterClient.class);
            startActivity(ajouterClient);
        }

    };

    View.OnClickListener ConsulterLesNDF = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent consulterNDF = new Intent(PageAccueil.this, Main2Activity.class);
            startActivity(consulterNDF);
        }
    };

    View.OnClickListener AjoutNDF = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent ajouterNDF = new Intent(PageAccueil.this, AjouterNoteDeFrais.class);
            startActivity(ajouterNDF);
        }

    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_accueil);
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnConsulterLesClient = (Button) findViewById(R.id.consulterClient);
        btnConsulterLesClient.setOnClickListener(ConsulterLesClients);
        btnAjouterLesClient = (Button) findViewById(R.id.ajouterClient);
        btnAjouterLesClient.setOnClickListener(AjouterDesClient);
        btnConsulterLesNDF = (Button) findViewById(R.id.ConsulterNDF);
        btnConsulterLesNDF.setOnClickListener(ConsulterLesNDF);
        ajoutNDF = (Button)findViewById(R.id.ajouterNDF);
        ajoutNDF.setOnClickListener(AjoutNDF);



    }

}
