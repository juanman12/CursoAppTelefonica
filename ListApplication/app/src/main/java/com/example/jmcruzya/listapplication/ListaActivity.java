package com.example.jmcruzya.listapplication;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * Created by jmcruzya on 26/01/2017.
 */

public class ListaActivity extends ListActivity
{
    //PA ra poner una lista siempre se debe usar un adapter
    String[] ciudades = {"Bogota", "Medellin", "Cali", "Pereira", "Bucaramanga", "Cartagena","Barranquilla","Pasto"};

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        ArrayAdapter<String> arreglo = new ArrayAdapter<String>(this,android.R.layout.
                simple_list_item_1,ciudades);
        setListAdapter(arreglo);
    }
}
