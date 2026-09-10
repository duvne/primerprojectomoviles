package com.example.primerprojecto;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        TextView txtResultadoFinal = findViewById(R.id.txtresultado);

        String resultadoRecibido = getIntent().getStringExtra("resultado_key");

        // Mostrarlo en el TextView
        if (resultadoRecibido != null) {
            txtResultadoFinal.setText( resultadoRecibido);
        }
    }
}