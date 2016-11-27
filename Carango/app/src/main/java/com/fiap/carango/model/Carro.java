package com.fiap.carango.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rm31014 on 19/11/2016.
 */
public class Carro implements Parcelable {

    @SerializedName("nome")
    private String nome;

    @SerializedName("desc")
    private String desc;

    @SerializedName("foto")
    private String foto;


    public static final Creator<Carro> CREATOR = new Creator<Carro>() {
        @Override
        public Carro createFromParcel(Parcel in) {
            return new Carro(in);
        }
        @Override
        public Carro[] newArray(int size) {
            return new Carro[size];
        }
    };

    public Carro(Parcel in) {
        nome = in.readString();
        desc = in.readString();
        foto = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(nome);
        parcel.writeString(desc);
        parcel.writeString(foto);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }




}
