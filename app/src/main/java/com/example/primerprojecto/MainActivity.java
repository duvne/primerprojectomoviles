package com.example.primerprojecto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtn1, edtn2;

    TextView txtresultado;

    Button btncalcular, btnlimpiar;
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
        txtresultado = findViewById(R.id.txtresultado);
        btncalcular = findViewById(R.id.btncalcular);
        btnlimpiar = findViewById(R.id.btnlimpiar);

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

            private void calcular() {
                String n1 = edtn1.getText().toString();
                String n2 = edtn2.getText().toString();

                if (n1.isEmpty() || n2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "ingrese los datos", Toast.LENGTH_SHORT).show();
                } else {
                    int num1 = Integer.parseInt(n1);
                    int num2 = Integer.parseInt(n2);
                    int suma = num1 + num2;
                    txtresultado.setText(String.valueOf(suma));
                }
            }
        });
    }

    private void limpiar() {
        edtn1.setText("");
        edtn2.setText("");
        txtresultado.setText("0");
    }
}