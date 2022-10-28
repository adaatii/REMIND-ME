package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.Model.Adapter.RecyclerAdapterEvent;
import com.example.agenda_pessoal.Model.Adapter.RecyclerAdapterTask;
import com.example.agenda_pessoal.Model.Sort.TaskTree;
import com.example.agenda_pessoal.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class A_Task extends AppCompatActivity {
    Data dataInstance;
    private RecyclerView recyclerView;
    private RecyclerAdapterTask adapterTask;
    TextView tv_Task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        tv_Task = findViewById(R.id.tv_Task);
        dataInstance = getIntent().getExtras().getParcelable("Data");
        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        recyclerView = findViewById(R.id.recyclerView_Task);
        recyclerView.setLayoutManager(new LinearLayoutManager(A_Task.this)); // Como o RecyclerView será mostrado na tela

        ArrayList<Task> task = new ArrayList<>();
        //Percorre ArrayList Task, filtrando os finalizados
        for (Task item : dataInstance.getDataTask()) {
            if (!item.finished && !item.isEvent() && item.getOwner(dataInstance.log)){
                task.add(item);
            }
        }
        adapterTask = new RecyclerAdapterTask(task, A_Task.this);
        recyclerView.setAdapter(adapterTask);

        // Set a visibilidade do texto Não Há compromissos
        if (task.size() == 0) {
            tv_Task.setText(R.string.nao_ha_tarefas);
        } else {
            tv_Task.setText(R.string.tarefas);
        }
    }
}