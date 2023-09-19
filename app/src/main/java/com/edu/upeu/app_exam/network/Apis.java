package com.edu.upeu.app_exam.network;

import com.edu.upeu.app_exam.interfaces.PersonaService;
import com.edu.upeu.app_exam.parametros.Clientes;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apis {

    private static Retrofit retrofit;


    public static Retrofit getcliente(){
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.10.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


}
