package com.example.agenda_pessoal.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_pessoal.R;

import java.util.ArrayList;

public class A_RecyclerAdapterCompromisso extends RecyclerView.Adapter<A_RecyclerAdapterCompromisso.CompromissoViewHolder> {

    private ArrayList<String> titulo;
    private ArrayList<String> descricao;
    private ArrayList<String> hora;    
    private Context context;

    public A_RecyclerAdapterCompromisso(ArrayList<String> titulo, ArrayList<String> descricao, ArrayList<String> hora, Context context) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.hora = hora;
        this.context = context;
    }

    @NonNull
    @Override
    public CompromissoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // define o card_desing que foi feito, determina qual desing sera mostrado

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_compromisso_desing, parent, false); // primeiro parametro o card_desing, parente, false
        return new CompromissoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompromissoViewHolder holder, int position){ // mostrara o data
        holder.textViewTituloCompromisso.setText(titulo.get(position));
        holder.textViewDescricaoCompromisso.setText(descricao.get(position));
        holder.textViewHoraCompromisso.setText(hora.get(position));
        holder.cardView.setOnClickListener(view -> {


        });
        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }

    @Override
    public int getItemCount() { //a quantidade de informação que será mostrada na tela
        return titulo.size();
    }

    public class CompromissoViewHolder extends RecyclerView.ViewHolder {
        
        private TextView textViewTituloCompromisso, textViewDescricaoCompromisso, textViewHoraCompromisso;
        private CardView cardView;
        
        public CompromissoViewHolder(@NonNull View itemView) {
            super(itemView);
            
            textViewTituloCompromisso = itemView.findViewById(R.id.tv_tituloCompromisso);
            textViewDescricaoCompromisso = itemView.findViewById(R.id.tv_descricaoCompromisso);
            textViewHoraCompromisso = itemView.findViewById(R.id.tv_horaCompromisso);
            cardView = itemView.findViewById(R.id.card_viewCompromisso);
            
        }
    }
}
