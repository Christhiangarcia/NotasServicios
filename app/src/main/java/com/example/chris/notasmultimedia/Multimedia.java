package com.example.chris.notasmultimedia;

import android.net.Uri;

/**
 * Created by chris on 14/11/2017.
 */

public class Multimedia {
    private int idMultimedia,idNota,tipo;
    private String uri,tipoMultimedia;

    public int getIdMultimedia() {
        return idMultimedia;
    }

    public void setIdMultimedia(int idMultimedia) {
        this.idMultimedia = idMultimedia;
    }

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTipoMultimedia() { return tipoMultimedia; }

    public void setTipoMultimedia(String tipoMultimedia) { this.tipoMultimedia = tipoMultimedia; }

    @Override
    public String toString() {
        if(this.getTipo()==1){
            this.setTipoMultimedia("foto");
        }else if(this.getTipo()==2){
            this.setTipoMultimedia("video");
        }else
            this.setTipoMultimedia("audio");

        return "ID: "+this.getIdMultimedia()+"\n"+"Tipo: "+this.getTipoMultimedia()+"\n"+"Uri: "+this.getUri()+"\n"+"IdNota: "+this.getIdNota();
    }
}
