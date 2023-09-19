package com.edu.upeu.app_exam.vistas;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.edu.upeu.app_exam.R;
import com.edu.upeu.app_exam.dto.PersonaDto;
import com.edu.upeu.app_exam.interfaces.PersonaService;
import com.edu.upeu.app_exam.models.Persona;
import com.edu.upeu.app_exam.network.Apis;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCliente extends AppCompatActivity {

    EditText txtaddNombre, getTxtaddApellido, getTxtaddEmail;

    Button btnaddCliente;

    PersonaService personaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);

        txtaddNombre=findViewById(R.id.txtaddNombre);
        getTxtaddApellido=findViewById(R.id.txtaddApellido);
        getTxtaddEmail=findViewById(R.id.txtaddEmail);

        btnaddCliente=findViewById(R.id.btnAddClient);

        ///Nombre
        txtaddNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Apellido
        getTxtaddApellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Email
        getTxtaddEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnaddCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonaDto dto = new PersonaDto(txtaddNombre.getText().toString(),
                        getTxtaddApellido.getText().toString(),
                        getTxtaddEmail.getText().toString());

                createCliente(dto);
            }
        });


    }

    private void createCliente(PersonaDto dto) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apis.getcliente().baseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        personaService = retrofit.create(PersonaService.class);
        Call<Persona> call = personaService.createCliente(dto);

        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddCliente.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    Log.e("Response err: ", response.message());
                    return;
                }

                Persona persona = response.body();

                Intent intent = new Intent(AddCliente.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {

                Toast.makeText(AddCliente.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    private boolean buttonEnabled() {
        return txtaddNombre.getText().toString().trim().length() > 0;
    }
}