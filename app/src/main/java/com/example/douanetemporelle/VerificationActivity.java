package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        TextView tvNom = findViewById(R.id.tv_nom_verif_es);
        TextView tvAge = findViewById(R.id.tv_age_verif_es);
        TextView tvMotif = findViewById(R.id.tv_motif_verif_es);
        Button btnApprouver = findViewById(R.id.btn_approuver_es);
        Button btnRefuser = findViewById(R.id.btn_refuser_es);

        // 1- استقبال المعطيات القادمة من النشاط الأول بنفس المفاتيح
        String m = Cles.MATRICULE;
        Intent recu = getIntent();
        nom = recu.getStringExtra(Cles.nomVoyageur(m));
        int naissance = recu.getIntExtra(Cles.anneeNaissance(m), 0);
        int destination = recu.getIntExtra(Cles.anneeDestination(m), 0);
        String motif = recu.getStringExtra(Cles.motif(m));

        // 2- calcul d'age temporel
        int ageTemporel = destination - naissance;

        // 3- resume
        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + ageTemporel + " ans");
        tvMotif.setText("Motif : " + motif);

        // 4- شرط التعطيل: السبب "Modification du passé" والفرق عدد أولي
        boolean modif = getString(R.string.motif_modification).equals(motif);
        if (modif && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        // 5- الزران يرسلان الحكم النهائي (true / false) مع اسم المسافر
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

    // 6- دالة التحقق من العدد الأولي
    private boolean estPremier(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}