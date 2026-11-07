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

    // 1- تعريف العناصر ;
    private EditText etNom, etNaissance, etDestination;
    private RadioGroup rgMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2) ربط العناصر بال XML عن طريق ال id
        etNom = findViewById(R.id.et_nom_es);
        etNaissance = findViewById(R.id.et_naissance_es);
        etDestination = findViewById(R.id.et_destination_es);
        rgMotif = findViewById(R.id.rg_motif_es);
        btnInspecter = findViewById(R.id.btn_inspecter_es);

        // 3- الضغط على الزر
        btnInspecter.setOnClickListener(v -> inspecter());
    }

    private void inspecter() {

        String nom = etNom.getText().toString().trim();
        String strNaiss = etNaissance.getText().toString().trim();
        String strDest = etDestination.getText().toString().trim();

        //4- التحقق
        if (nom.isEmpty() || strNaiss.isEmpty() || strDest.isEmpty()) {
            Toast.makeText(this, " tous les champs doivent être remplis ", Toast.LENGTH_SHORT).show();
            return;
        }

        // 5- التحقق من اختيار سبب السفر
        int idChoisi = rgMotif.getCheckedRadioButtonId();
        if (idChoisi == -1) {
            Toast.makeText(this, "Choisissez un motif ", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton rbChoisi = findViewById(idChoisi);
        String motif = rbChoisi.getText().toString();
        //6- تحويل النصوص إلى أرقام
        int naissance = Integer.parseInt(strNaiss);
        int destination = Integer.parseInt(strDest);

        // 7- إنشاء Intent صريح وإرسال المعطيات
        String m = Cles.MATRICULE;
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
        intent.putExtra(Cles.nomVoyageur(m), nom);
        intent.putExtra(Cles.anneeNaissance(m), naissance);
        intent.putExtra(Cles.anneeDestination(m), destination);
        intent.putExtra(Cles.motif(m), motif);
        startActivity(intent);
    }
}