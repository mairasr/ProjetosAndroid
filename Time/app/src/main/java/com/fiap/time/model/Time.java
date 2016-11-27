package com.fiap.time.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rm31014 on 19/11/2016.
 */
public class Time implements Parcelable {

    @SerializedName("nome")
    private String nome;

    @SerializedName("estado")
    private String estado;

    @SerializedName("escudo")
    private String escudo;

    @SerializedName("anofundacao")
    private String anoFundacao;

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }
        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };

    public Time(Parcel in) {
        nome = in.readString();
        estado = in.readString();
        escudo = in.readString();
        anoFundacao = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(nome);
        parcel.writeString(estado);
        parcel.writeString(escudo);
        parcel.writeString(anoFundacao);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEscudo() {
        return escudo;
    }

    public void setEscudo(String escudo) {
        this.escudo = escudo;
    }

    public String getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(String anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

}
