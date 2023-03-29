package com.example.conversormoneda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.conversormoneda.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mv;
    private ActivityMainBinding binding;

    private EditText etDolares, etEuros, etResultado;
    private Button btnConvertir;
    private RadioButton rbDaE, rbEaD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        this.etDolares = binding.etDolares;
        this.etEuros = binding.etEuros;
        this.rbEaD = binding.rbEaD;
        this.rbDaE = binding.rbDaE;
        this.etResultado = binding.etResultado;
        this.btnConvertir = binding.btnConvertir;

        rbEaD.setChecked(true);
        etDolares.setEnabled(false);

        mv.getResultado().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double valorConversion) {
                etResultado.setText(String.valueOf(valorConversion));
            }
        });

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("onClick", "funcionando");

                etResultado.setText("");

                try {
                    if(rbDaE.isChecked()){
                        double dolares = Double.parseDouble(etDolares.getText().toString());
                        etEuros.setText("");
                        mv.convertir("dae",dolares); //dae = dolares a euros
                    }

                    if(rbEaD.isChecked()){
                        double euros = Double.parseDouble(etEuros.getText().toString());
                        etDolares.setText("");
                        mv.convertir("ead",euros); //ead = euros a dolares
                    }
                }catch (NumberFormatException ex){
                    Toast.makeText(MainActivity.this, "Ingrese un numero correcto", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Cuando elijo de Euros a Dolares
        rbEaD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etEuros.setEnabled(true);
                etDolares.setEnabled(false);

                Log.d("valor", etEuros.getText().toString());
                etEuros.setText(etDolares.getText().toString());
                etDolares.setText("");
                etDolares.setEnabled(false);
            }
        });

        //Cuando elijo de Dolares a Euros
        rbDaE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDolares.setEnabled(true);
                etEuros.setEnabled(false);

                Log.d("valor", etDolares.getText().toString());
                etDolares.setText(etEuros.getText().toString());
                etEuros.setText("");
            }
        });
    }


}