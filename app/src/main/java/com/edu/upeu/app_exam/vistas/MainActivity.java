package com.edu.upeu.app_exam.vistas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnCliente;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnCliente=findViewById(R.id.btn_Cliente);


        btnCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListarClienteActivity.class);
                startActivity(intent);

            }
        });



    }


}