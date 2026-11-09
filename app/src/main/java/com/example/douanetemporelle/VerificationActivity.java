package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    private String nom; //nesta7efdhou 3al esm bech naba3thouh lel activité theltha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        //norbtou les variables mte3na bel xml
        TextView tvNom = findViewById(R.id.tv_nom_verif_es);
        TextView tvAge = findViewById(R.id.tv_age_verif_es);
        TextView tvMotif = findViewById(R.id.tv_motif_verif_es);
        Button btnApprouver = findViewById(R.id.btn_approuver_es);
        Button btnRefuser = findViewById(R.id.btn_refuser_es);

        // nesta9blou les données eli jewna mel activité lowla b nafs les clés
        String m = Cles.MATRICULE;
        Intent recu = getIntent();
        nom = recu.getStringExtra(Cles.nomVoyageur(m));
        int naissance = recu.getIntExtra(Cles.anneeNaissance(m), 0);
        int destination = recu.getIntExtra(Cles.anneeDestination(m), 0);
        String motif = recu.getStringExtra(Cles.motif(m));

        //   ne7sbou l'âge temporel
        int ageTemporel = destination - naissance;

        // ndhahrou el résumé 3al écran (les donées el 7ajetna bihom el kol eli houma el esm wel âge temporel w wa7da men les motifs el 3 el mawjoudin)
        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + ageTemporel + " ans");
        tvMotif.setText("Motif : " + motif);

        // idha ken el motif el mokhtara heya "modification du passé" wel far9 3dad awali automatiquement nebloukiw el bouton "approuver"
        boolean modif = getString(R.string.motif_modification).equals(motif);
        if (modif && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        // aka les deux boutons yaba3thoulna el ijeba soit approuver soit refuser + esm eli bech ysefer
        btnApprouver.setOnClickListener(v -> lancerVerdict(true));
        btnRefuser.setOnClickListener(v -> lancerVerdict(false));
    }

    private void lancerVerdict(boolean approuve) {
        String m = Cles.MATRICULE;
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.verdict(m), approuve);
        intent.putExtra(Cles.nomVoyageur(m), nom);
        startActivity(intent);
    }

    // nthabtou idha ken l 3dad premier wala lé, ya3ni ye9bel el 9esma 3la rou7ou w 3la 1 kahaw
    private boolean estPremier(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}