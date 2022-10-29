package com.example.agenda_pessoal.Model.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.R;

import java.util.ArrayList;

public class RecyclerAdapterEvent extends RecyclerView.Adapter<RecyclerAdapterEvent.CompromissoViewHolder> {

    private ArrayList<Task> task = new ArrayList<Task>();
    private Context context;
    private ArrayList<Integer> sortedId = new ArrayList<>();
    private itemActivityListener listener;

    //Mostra os eventos na RecyclerView
    public RecyclerAdapterEvent(ArrayList<Task> task, ArrayList<Integer> id, Context context){
        this.context = context;
        //Pega somente dos Id's que já foram filtrados e ordenado usando o taskTree (coloca em this.task)
        for (Integer i: id) {
            this.task.add(task.get(i));
        }
        sortedId.addAll(id);
    }

    @NonNull
    @Override
    public CompromissoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // define o card_desing que foi feito, determina qual desing sera mostrado

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event_desing, parent, false); // primeiro parametro o card_desing, parente, false
        return new CompromissoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompromissoViewHolder holder, @SuppressLint("RecyclerView") int position){ // mostrara o data
        Task item = task.get(position);
        holder.bind(item);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(sortedId.get(position));

            }
        });
    }

    @Override
    public int getItemCount() { //a quantidade de informação que será mostrada na tela
        return task.size();
    }

    public void reloadView(ArrayList<Task> task, ArrayList<Integer> id){

        this.sortedId.clear();
        this.task.clear();
        //Pega somente dos Id's que já foram filtrados e ordenado usando o taskTree (coloca em this.task)
        for (Integer i: id) {
            this.task.add(task.get(i));
        }
        sortedId.addAll(id);
        notifyDataSetChanged();
    }

    public void setListener(itemActivityListener listener){this.listener = listener;}

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
            timeEvent.setText(item.event.date[1]);
        }
    }

    public interface itemActivityListener{
        void onItemClick(int position);
    }

}
