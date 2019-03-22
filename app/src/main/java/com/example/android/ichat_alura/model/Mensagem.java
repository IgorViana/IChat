package com.example.android.ichat_alura.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Mensagem implements Serializable {
    private final int id;
    @SerializedName("text")
    private final String texto;

    public Mensagem(int id, String texto) {
        this.id = id;
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public int getId() {
        return id;
    }
}
