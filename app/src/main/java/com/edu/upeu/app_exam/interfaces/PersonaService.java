package com.edu.upeu.app_exam.interfaces;

import com.edu.upeu.app_exam.dto.PersonaDto;
import com.edu.upeu.app_exam.models.Persona;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PersonaService {

    @GET("clientes")
    Call<List<Persona>> getPersonas();

    @GET("clientes/{id}")
    Call<Persona> getOne(@Path("id") int id);
    @POST("clientes")
    Call<Persona> createCliente(@Body PersonaDto dto);

    @DELETE("clientes/{id}")
    Call<Persona>deleteCliente(@Path("id") int deleteIdCliente);

}
