package com.example.jmcruzya.intentapplication;

import java.io.Serializable;

/**
 * Created by jmcruzya on 31/01/2017.
 */

public class Contacto implements Serializable
{
    private String nombre;
    private int telefono;
    private String web;

    public Contacto(String nombre, int telefono, String web) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.web = web;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
