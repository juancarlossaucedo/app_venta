package com.edu.upeu.app_exam.parametros;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Clientes {

    public static Retrofit getCliente(String url){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit;
    }
}
