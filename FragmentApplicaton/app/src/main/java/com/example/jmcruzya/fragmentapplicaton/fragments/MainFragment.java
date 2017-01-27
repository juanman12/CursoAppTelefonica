package com.example.jmcruzya.fragmentapplicaton.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jmcruzya.fragmentapplicaton.MainActivity;
import com.example.jmcruzya.fragmentapplicaton.R;

/**
 * Created by jmcruzya on 26/01/2017.
 */

public class MainFragment extends Fragment
{

    private EditText num1,num2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment,container,false);

        num1 = (EditText)view.findViewById(R.id.number1);
        num2 = (EditText)view.findViewById(R.id.number2);
        Button botonSuma = (Button)view.findViewById(R.id.buttonSuma);
        botonSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1S = num1.getText().toString();
                String num2S = num2.getText().toString();
                sumar(num1S,num2S);
            }
        });


        return view;


    }

    private void sumar(String num1S, String num2S)
    {
          if(validateNum(num1S,num2S)){
              ((MainActivity)getActivity()).
                      replaceFragment(MainActivity.newInstance
                              (Integer.valueOf(num1S),Integer.valueOf(num2S)));
          }
        else{
              num2.requestFocus();
              num2.setError("Revise el contenido para seguir");
          }
    }

    private boolean validateNum(String a, String b)
    {
        String regex = "\\d+";
        return (!a.isEmpty() && !b.isEmpty() && (a.matches(regex) && b.matches(regex)));
    }


}
