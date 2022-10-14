package com.example.agenda_pessoal.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.R;

import java.util.ArrayList;

public class A_RecyclerAdapterEvent extends RecyclerView.Adapter<A_RecyclerAdapterEvent.CompromissoViewHolder> {

    private ArrayList<Task> task = new ArrayList<Task>();
    private Context context;

    public A_RecyclerAdapterEvent(ArrayList<Task> task, Context context){
        this.context = context;
        for (int i = 0; i < task.size(); i++) {
            Task item = task.get(i);
            if (!item.isEvent()){
                this.task.add(item);
            }
        }
    }

    @NonNull
    @Override
    public CompromissoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // define o card_desing que foi feito, determina qual desing sera mostrado

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event_desing, parent, false); // primeiro parametro o card_desing, parente, false
        return new CompromissoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompromissoViewHolder holder, int position){ // mostrara o data
        Task item = task.get(position);
        holder.bind(item);
        holder.cardView.setOnClickListener(view -> {});

        /*holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
    }

    @Override
    public int getItemCount() { //a quantidade de informação que será mostrada na tela
        return task.size();
    }

    public class CompromissoViewHolder extends RecyclerView.ViewHolder {

        private TextView titleEvent, descriptionEvent, timeEvent;
        private CardView cardView;

        public CompromissoViewHolder(@NonNull View itemView) {
            super(itemView);

            titleEvent = itemView.findViewById(R.id.tv_tituloCompromisso);
            descriptionEvent = itemView.findViewById(R.id.tv_descricaoCompromisso);
            timeEvent = itemView.findViewById(R.id.tv_horaCompromisso);
            cardView = itemView.findViewById(R.id.card_viewCompromisso);

        }

        public void bind(Task item){
            titleEvent.setText(item.getTitle());
            descriptionEvent.setText(item.description);
            timeEvent.setText(" ");
        }


    }

}
