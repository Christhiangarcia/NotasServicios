package com.example.chris.notasmultimedia;

/**
 * Created by chris on 28/10/2017.
 */

public class Nota {
    private int idNota;
    private String titulo,descripcion,fecha;

    public long getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "ID: "+this.idNota+"\n"+"Titulo: "+this.titulo+"\n"+"Descripcion: "+this.descripcion+"\n"+"Fecha: "+this.fecha;
    }
}
