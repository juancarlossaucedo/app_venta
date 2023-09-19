package com.edu.upeu.app_exam.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.edu.upeu.app_exam.R;
import com.edu.upeu.app_exam.models.Persona;
import com.edu.upeu.app_exam.vistas.ListarClienteActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder> {
    private List<Persona> listaPersona;
    private Context context;

    private ListarClienteActivity activity;
    public PersonaAdapter(List<Persona> listaPersona, Context context ) {
        this.listaPersona = listaPersona;
        this.context = context;
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Persona persona = listaPersona.get(position);

        // Asigna valores a las vistas en el diseño item_movie.xml
        holder.nombreTextView.setText(persona.getNombre());
        holder.apellidoTextView.setText(persona.getApellido());
        holder.emailTextView.setText(persona.getEmail());

        // Configura el oyente de clic para el botón de eliminar
        holder.btnDeleteCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Persona personaAEliminar = listaPersona.get(position);
                    activity.deleteCliente(personaAEliminar, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPersona.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nombreTextView;
        public TextView apellidoTextView;
        public TextView emailTextView;
        public FloatingActionButton btnDeleteCliente;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.iv_Nombre);
            apellidoTextView = itemView.findViewById(R.id.tv_apellido);
            emailTextView = itemView.findViewById(R.id.tv_email);
            btnDeleteCliente = itemView.findViewById(R.id.btnDeleteCliente);
        }
    }
}
