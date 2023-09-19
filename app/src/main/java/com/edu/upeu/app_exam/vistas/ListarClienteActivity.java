package com.edu.upeu.app_exam.vistas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.edu.upeu.app_exam.R;
import com.edu.upeu.app_exam.adapters.PersonaAdapter;
import com.edu.upeu.app_exam.interfaces.PersonaService;
import com.edu.upeu.app_exam.models.Persona;
import com.edu.upeu.app_exam.network.Apis;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ListarClienteActivity extends AppCompatActivity {

    private List<Persona> listaCliente;
    private RecyclerView recyclerView;
    private PersonaAdapter personaAdapter;

    FloatingActionButton add_cliente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cliente);

        recyclerView=findViewById(R.id.rvClientes);

        add_cliente=findViewById(R.id.add_cliente);



        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));

        listarClientes();

        personaAdapter = new PersonaAdapter(listaCliente, this);


        //btn agregar
        add_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListarClienteActivity.this, AddCliente.class);
                startActivity(intent);
            }
        });


    }

    public void listarClientes(){

        Call<List<Persona>> call = Apis.getcliente().create(PersonaService.class).getPersonas();

        call.enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                if (response.isSuccessful()){
                    listaCliente=response.body();

                    personaAdapter=new PersonaAdapter(listaCliente,getApplicationContext());

                    recyclerView.setAdapter(personaAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {

                Toast.makeText(ListarClienteActivity.this, "Error Conexion" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void deleteCliente(Persona personaAEliminar, int position){

        Call<List<Persona>> call = Apis.getcliente().create(PersonaService.class).getPersonas();

        call.enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                if (response.isSuccessful()) {


                    listaCliente.remove(position);
                    personaAdapter.notifyItemRemoved(position);

                    showToast("Persona eliminada exitosamente");

                } else {
                    showToast("Error al eliminar la persona. Código de estado: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {

                showToast("Error de conexión: " + t.getMessage());
            }
        });

    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



}