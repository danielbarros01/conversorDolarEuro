package com.example.conversormoneda;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.text.DecimalFormat;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Double> resultado = new MutableLiveData<>();
    private double valorDolarEuro, valorEuroDolar; //Los valores son respecto al euro en contra el dolar.

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        this.valorEuroDolar = 1.08; //1 dolar = 1.08 euros
        this.valorDolarEuro = 0.92; //1 euro = 0.92 dolares
    }

    public void convertir(String tipoConversion, double moneda) {
        double res = 0;

        if (tipoConversion.equals("ead")) {
            res = moneda * valorEuroDolar;
        } else if (tipoConversion.equals("dae")) {
            res = moneda * valorDolarEuro;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double numeroRedondeado = Double.parseDouble(decimalFormat.format(res));

        resultado.setValue(numeroRedondeado);
    }


    //--------
    public LiveData<Double> getResultado() {
        if (resultado.getValue() == null) {
            this.resultado = new MutableLiveData<>();
        }

        return resultado;
    }
}
