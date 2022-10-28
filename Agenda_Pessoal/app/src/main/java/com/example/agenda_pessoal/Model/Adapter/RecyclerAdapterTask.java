package com.example.agenda_pessoal.Model.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class RecyclerAdapterTask extends RecyclerView.Adapter<RecyclerAdapterTask.TaskViewHolder> {

    private ArrayList<Task> task = new ArrayList<Task>();
    private Context context;

    //Mostra os eventos na RecyclerView
    public RecyclerAdapterTask(ArrayList<Task> task,  Context context) {
        this.context = context;
        this.task.addAll(task);
    }

    @NonNull
    @Override
    public RecyclerAdapterTask.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // define o card_desing que foi feito, determina qual desing sera mostrado

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_task_desing, parent, false); // primeiro parametro o card_desing, parente, false
        return new RecyclerAdapterTask.TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTask.TaskViewHolder holder, @SuppressLint("RecyclerView") int position) { // mostrara o data
        Task item = task.get(position);
        holder.bind(item);
        holder.cardViewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update de Evento;
            }
        });
    }

    @Override
    public int getItemCount() { //a quantidade de informação que será mostrada na tela
        return task.size();
    }

    public void reloadView(ArrayList<Task> task) {
        this.task.clear();
        //Pega somente dos Id's que já foram filtrados e ordenado usando o taskTree (coloca em this.task)
        this.task.addAll(task);
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTask, descriptionTask;
        private CardView cardViewTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTask = itemView.findViewById(R.id.tv_titleTask);
            descriptionTask = itemView.findViewById(R.id.tv_descriptionTask);
            cardViewTask = itemView.findViewById(R.id.card_viewTask);
        }

        public void bind(Task item){
            titleTask.setText(item.getTitle());
            descriptionTask.setText(item.description);
        }


    }
}
