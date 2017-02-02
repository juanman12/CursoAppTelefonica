package com.example.jmcruzya.styleapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jmcruzya.styleapplication.fragments.ItemFragment;
import com.example.jmcruzya.styleapplication.utilities.ConnectionDetector;
import com.example.jmcruzya.styleapplication.utilities.MyPreference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemFragment.OnListFragmentInteractionListener {

    public ProgressDialog progressDialog;
    private MyPreference myPreference;
    private ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar , R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cerrando App");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.getProgress();

        myPreference = new MyPreference(this);
        connectionDetector = new ConnectionDetector(this);

        //incluir fragmento en content_main
        replaceFragment(new ItemFragment());
    }

    private void replaceFragment(ItemFragment fragment)
    {
        //Retomando la lección 2
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private void getData()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, "http://vesta.sersoluciones.com:9080/points/get_distance?lat=9999&lon=9999", null,
                        new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        //try {
                            //jsonArray = response.getJSONArray("features");
                        Log.d("MyData",response.toString());
                        //List<Point> pointArrayList = toList(response.toString(), Point.class);
                        Gson gson = new Gson();
                        ArrayList<Point> pointArrayList = gson.fromJson(response.toString(),
                               new TypeToken<ArrayList<Point>>() {}.getType());

                        for (Point p : pointArrayList){
                            Log.d("MyData",String.valueOf(p.getNombre()));
                        }
                        //)} catch (JSONException e) {
                            //e.printStackTrace();
                        //}
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        queue.add(jsObjRequest);

    }

    public static <T> List<T> toList(String json, Class clazz) {
        if (null == json) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Alerta")
                    .setIcon(R.drawable.ic_menu_manage)
                    .setMessage("¿Desea salir de la aplicación?")
                    .setCancelable(false)
                    //.setView() para layout propio
                    .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //pop-up rapido
                            Toast.makeText(MainActivity.this,"Cerrando App",Toast.LENGTH_LONG).show();
                            //No usar Theards
                            progressDialog.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    myPreference.setStateLogin(false);
                                    finish();
                                }
                            },3000);

                        }
                    })
                    //.setNeutralButton()
                    .setNegativeButton("NO",null);
            builder.create().show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Point item) {

    }
}
