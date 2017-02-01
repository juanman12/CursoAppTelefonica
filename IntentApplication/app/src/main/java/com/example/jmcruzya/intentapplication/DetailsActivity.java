package com.example.jmcruzya.intentapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_OBJ = "obj" ;
    public static final  int WEB = 0 ;
    public static final  int TELEFONO = 1 ;
    private Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            contacto = (Contacto)extras.getSerializable(KEY_OBJ);
            TextView txtNombre = (TextView)findViewById(R.id.textNombre);
            TextView txtTelefono = (TextView)findViewById(R.id.textTelefono);
            TextView txtWeb = (TextView)findViewById(R.id.textWeb);

            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(String.valueOf(contacto.getTelefono()));
            txtWeb.setText(contacto.getWeb());

            //que vistas le quiero poner el evento
            txtTelefono.setOnClickListener(this);
            txtWeb.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.textTelefono:
                intent.putExtra("value",contacto.getTelefono());
                setResult(TELEFONO, intent);
                break;
            case R.id.textWeb:
                intent.putExtra("value",contacto.getWeb());
                setResult(WEB, intent);
                break;
        }
        finish();

    }
}
