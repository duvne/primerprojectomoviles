package com.example.primerprojecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;
    EditText edtn1, edtn2;
    TextView txtresultado;
    Button btncalcular, btnlimpiar;

    // Variable global para guardar la operación seleccionada
    String operacionSeleccionada = "suma";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtn1 = findViewById(R.id.edtn1);
        edtn2 = findViewById(R.id.edtn2);
        btncalcular = findViewById(R.id.btncalcular);
        btnlimpiar = findViewById(R.id.btnlimpiar);
        spinner = findViewById(R.id.spiner);

        String[] opciones = {"suma", "resta", "multiplicacion", "division"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                opciones
        );
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adaptador);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                operacionSeleccionada = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Sin selección
            }
        });

        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcular();
            }
        });
    }

    public void calcular() {
        String n1 = edtn1.getText().toString();
        String n2 = edtn2.getText().toString();

        if (n1.isEmpty() || n2.isEmpty()) {
            Toast.makeText(MainActivity.this, "Ingrese los datos", Toast.LENGTH_SHORT).show();
        } else {
            double num1 = Double.parseDouble(n1);
            double num2 = Double.parseDouble(n2);
            double resultado = 0;

            switch (operacionSeleccionada) {
                case "suma":
                    resultado = num1 + num2;
                    break;
                case "resta":
                    resultado = num1 - num2;
                    break;
                case "multiplicacion":
                    resultado = num1 * num2;
                    break;
                case "division":
                    if (num2 == 0) {
                        Toast.makeText(MainActivity.this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    resultado = num1 / num2;
                    break;
            }

            Intent intent = new Intent(MainActivity.this, com.example.primerprojecto.resultado.class);
            intent.putExtra("resultado_key", String.valueOf(resultado));
            startActivity(intent);
        }
    }

    private void limpiar() {
        edtn1.setText("");
        edtn2.setText("");
        txtresultado.setText("0");
    }
}