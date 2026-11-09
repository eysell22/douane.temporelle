package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // ta3rif les variables eli bech nesta3mlouhom
    private EditText etNom, etNaissance, etDestination;
    private RadioGroup rgMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //norbtou ces variables bel XML 3la tri9 l'ID
        etNom = findViewById(R.id.et_nom_es);
        etNaissance = findViewById(R.id.et_naissance_es);
        etDestination = findViewById(R.id.et_destination_es);
        rgMotif = findViewById(R.id.rg_motif_es);
        btnInspecter = findViewById(R.id.btn_inspecter_es);

        // wa9t el user yenzel 3la el bouton
        btnInspecter.setOnClickListener(v -> inspecter());
    }

    private void inspecter() {


        String nom = etNom.getText().toString().trim();
        String strNaiss = etNaissance.getText().toString().trim();
        String strDest = etDestination.getText().toString().trim();

        //bech net2akdou idha ken les champs lkol m3amrin
        if (nom.isEmpty() || strNaiss.isEmpty() || strDest.isEmpty()) {
            Toast.makeText(this, " tous les champs doivent être remplis ", Toast.LENGTH_SHORT).show();
            return;
        }

        // bech nthabtou idha ken el user 5tar sbab el sfar
        int idChoisi = rgMotif.getCheckedRadioButtonId();
        if (idChoisi == -1) {
            Toast.makeText(this, "Choisissez un motif ", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rbChoisi = findViewById(idChoisi);
        String motif = rbChoisi.getText().toString();

        //n7awlou el klem (eli howa string) l nwamer (eli houma int)
        int naissance = Integer.parseInt(strNaiss);
        int destination = Integer.parseInt(strDest);

        // na3mlou intent mfasra w naba3thou les données lel activité thenya
        String m = Cles.MATRICULE;
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
        intent.putExtra(Cles.nomVoyageur(m), nom);
        intent.putExtra(Cles.anneeNaissance(m), naissance);
        intent.putExtra(Cles.anneeDestination(m), destination);
        intent.putExtra(Cles.motif(m), motif);
        startActivity(intent);
    }
}