package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //
        LinearLayout racine = findViewById(R.id.ll_racine_result_es);
        TextView tvMessage = findViewById(R.id.tv_message_es);
        Button btnNouveau = findViewById(R.id.btn_nouveau_es);

        // iste9bel el esm wel décision soit approved wala refused
        String m = Cles.MATRICULE;
        boolean approuve = getIntent().getBooleanExtra(Cles.verdict(m), false);
        String nom = getIntent().getStringExtra(Cles.nomVoyageur(m));

        //nbadlou loun el background wel message 7asb e décision ya background 5adhra (approuvé) sinon background 7amra (refusé)
        if (approuve) {
            racine.setBackgroundColor(Color.parseColor("#2E7D32"));
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            racine.setBackgroundColor(Color.parseColor("#C62828"));
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // ki nenzlou 3la bouton "Nouveau voyageur" hakeka nwaliw narj3ou lel mainactivity w nfas5ou les activités l 9dom el kol
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}