package com.example.jmcruzya.applicationbmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;

public class MainActivity extends AppCompatActivity {

    //Eliminar error del tag
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText ePeso;
    private EditText eAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ePeso = (EditText) findViewById(R.id.editTextPeso);
        eAltura = (EditText) findViewById(R.id.editTextAltura);
    }

    public void calcularBMI(View view)
    {
        String peso = ePeso.getText().toString();
        String altura = eAltura.getText().toString();
        String regex = "\\d{1,3}";
        String msg = "Los campos de peso o altura están vacios";

        if(!(peso.isEmpty() && altura.isEmpty()))
        {
            //se crea regex en https://regex101.com/
            msg = "Valores no numericos";
            if(peso.matches(regex) && altura.matches(regex))
            {
                Log.e(TAG,msg);
                int alturaInt = Integer.parseInt(altura);
                int pesoInt = Integer.parseInt(peso);
                msg =  "El resultado es: " + (alturaInt*pesoInt);
            }
            ((TextView)findViewById(R.id.textResultado)).setText(msg);
        }
        else
        {
            Log.e(TAG,msg);
        }

    }
}
