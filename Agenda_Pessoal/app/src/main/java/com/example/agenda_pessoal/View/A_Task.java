package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.Model.Adapter.RecyclerAdapterEvent;
import com.example.agenda_pessoal.Model.Adapter.RecyclerAdapterTask;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.Model.Sort.TaskTree;
import com.example.agenda_pessoal.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class A_Task extends AppCompatActivity implements Constants {
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
            if (!item.finished && !item.isEvent() && item.getOwner(dataInstance.log)) {
                task.add(item);
            }
        }
        adapterTask = new RecyclerAdapterTask(task, A_Task.this);
        recyclerView.setAdapter(adapterTask);
        adapterTask.setListener(new RecyclerAdapterTask.itemActivityListener() {
            @Override
            public void onItemClick(int position) {
                Intent it_viewTask = new Intent(A_Task.this, A_ViewTask.class);
                it_viewTask.putExtra("Data", dataInstance);
                it_viewTask.putExtra("Position", position);
                startActivityForResult(it_viewTask, VIEW_TASK_ACTIVITY_REQUEST_CODE);
            }
        });
        // Set a visibilidade do texto Não Há compromissos
        if (task.size() == 0) {
            tv_Task.setText(R.string.nao_ha_tarefas);
        } else {
            tv_Task.setText(R.string.tarefas);
        }
    }

    public void openNewTaskScreen(View view) {
        Intent it_newTask = new Intent(this, A_NewTask.class);
        it_newTask.putExtra("Data", dataInstance);
        startActivityForResult(it_newTask, NEW_TASK_ACTIVITY_REQUEST_CODE);
    }

    public void returnTaskToEvent(View view){
        Intent it_aEvent = new Intent();
        it_aEvent.putExtra("Data", dataInstance);
        setResult(RESULT_OK, it_aEvent);
        finish();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", Integer.toString(resultCode));
        if (requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_FIRST_USER) {
                Data dataSerialize = data.getExtras().getParcelable("NewTask");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("NewTask"));

                ArrayList<Task> task = new ArrayList<>();
                //Percorre ArrayList Task, filtrando os finalizados
                for (Task item : dataInstance.getDataTask()) {
                    if (!item.finished && !item.isEvent() && item.getOwner(dataInstance.log)) {
                        task.add(item);
                    }
                }

                adapterTask.reloadView(task);
            }
        }
    }
}

