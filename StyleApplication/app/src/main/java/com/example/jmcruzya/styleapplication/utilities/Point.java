package com.example.jmcruzya.styleapplication.utilities;

import java.io.Serializable;

/**
 * Created by jmcruzya on 02/02/2017.
 */

public class Point implements Serializable {
    private int id;
    private String nombre;
    private String direccion;

    public Point(int id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
