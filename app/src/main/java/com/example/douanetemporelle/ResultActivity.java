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

        LinearLayout racine = findViewById(R.id.ll_racine_result_es);
        TextView tvMessage = findViewById(R.id.tv_message_es);
        Button btnNouveau = findViewById(R.id.btn_nouveau_es);

        // 1- استقبال الحكم والاسم
        String m = Cles.MATRICULE;
        boolean approuve = getIntent().getBooleanExtra(Cles.verdict(m), false);
        String nom = getIntent().getStringExtra(Cles.nomVoyageur(m));

        // 2- تغيير لون الخلفية والرسالة حسب الحكم
        if (approuve) {
            racine.setBackgroundColor(Color.parseColor("#2E7D32")); // أخضر
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            racine.setBackgroundColor(Color.parseColor("#C62828")); // أحمر
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // 3- العودة إلى MainActivity مع غلق كل النشاطات السابقة
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}