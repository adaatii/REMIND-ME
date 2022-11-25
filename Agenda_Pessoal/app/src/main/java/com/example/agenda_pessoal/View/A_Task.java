package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.Model.Adapter.RecyclerAdapterEvent;
import com.example.agenda_pessoal.Model.Adapter.RecyclerAdapterTask;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.Model.Sort.PriorityTree;
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
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        // filterSwitch = (Switch) findViewById(R.id.filterSwitch);
        tv_Task = findViewById(R.id.tv_Task);
        dataInstance = getIntent().getExtras().getParcelable("Data");
        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        recyclerView = findViewById(R.id.recyclerView_Task);
        recyclerView.setLayoutManager(new LinearLayoutManager(A_Task.this)); // Como o RecyclerView será mostrado na tela

        Integer count = 0;
        ArrayList<Task> task = new ArrayList<>();
        ArrayList<Integer> sortedId = new ArrayList<>();
        //Percorre ArrayList Task, filtrando os finalizados
        for (Task item : dataInstance.getDataTask()) {
            if (!item.finished && !item.isEvent() && item.getOwner(dataInstance.log)) {
                task.add(item);
                sortedId.add(count);
            }
            count++;
        }
        adapterTask = new RecyclerAdapterTask(task, sortedId, A_Task.this);
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
            tv_Task.setVisibility(View.VISIBLE);
            tv_Task.setText(R.string.nao_ha_tarefas);
        } else {
            tv_Task.setVisibility(View.GONE);
        }
    }

    public void openNewTaskScreen(View view) {
        Intent it_newTask = new Intent(this, A_NewTask.class);
        it_newTask.putExtra("Data", dataInstance);
        startActivityForResult(it_newTask, NEW_TASK_ACTIVITY_REQUEST_CODE);
    }

    public void returnTaskToEvent(View view) {
        Intent it_aEvent = new Intent();
        it_aEvent.putExtra("Data", dataInstance);
        setResult(RESULT_OK, it_aEvent);
        finish();

    }

    public void prioritySort() {
        ArrayList<Task> taskPriority = dataInstance.getDataTask();
        PriorityTree priorityTree = new PriorityTree();
        //Percorre ArrayList Task, filtrando os finalizados
        for (int id = 0; id < taskPriority.size(); id++) {
            Task item = taskPriority.get(id);
            if (!item.finished && !item.isEvent() && item.getOwner(dataInstance.log)) {

                priorityTree.add(id, item.priority);
            }
        }
        adapterTask.reloadSort(dataInstance.getDataTask(), priorityTree.sort());
    }

    public void filter(View view) {
        if (check == 0) {
            check = 1;
        } else {
            check = 0;
        }

        if (check == 1) {
            prioritySort();
        } else {
            Integer count = 0;
            ArrayList<Task> task = new ArrayList<>();
            ArrayList<Integer> sortedId = new ArrayList<>();
            //Percorre ArrayList Task, filtrando os finalizados
            for (Task item : dataInstance.getDataTask()) {
                if (!item.finished && !item.isEvent() && item.getOwner(dataInstance.log)) {
                    task.add(item);
                    sortedId.add(count);
                }
                count++;
            }
            adapterTask.reloadView(task, sortedId);
        }
    }

    public void filterReturn() {
        if (check == 1) {
            prioritySort();
        } else {
            Integer count = 0;
            ArrayList<Task> task = new ArrayList<>();
            ArrayList<Integer> sortedId = new ArrayList<>();
            //Percorre ArrayList Task, filtrando os finalizados
            for (Task item : dataInstance.getDataTask()) {
                if (!item.finished && !item.isEvent() && item.getOwner(dataInstance.log)) {
                    task.add(item);
                    sortedId.add(count);
                }
                count++;
            }
            adapterTask.reloadView(task, sortedId);
        }
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
                filterReturn();

            }
        }
        if (requestCode == VIEW_TASK_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Data dataSerialize = data.getExtras().getParcelable("Data");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("Data"));
                Integer position = data.getExtras().getInt("Position");
                Intent it_editEvent = new Intent(this, A_EditEvent.class);
                it_editEvent.putExtra("Data", dataInstance);
                it_editEvent.putExtra("Position", position);
                startActivityForResult(it_editEvent, EDIT_EVENT_ACTIVITY_REQUEST_CODE);
            } else if (resultCode == RESULT_FIRST_USER) {
                Data dataSerialize = data.getExtras().getParcelable("Data");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("Data"));
                filterReturn();
            }
        }
        if (requestCode == EDIT_EVENT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_FIRST_USER) {
                Data dataSerialize = data.getExtras().getParcelable("EditedEvent");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("EditedEvent"));
                filterReturn();

            }
            if (resultCode == RESULT_OK) {
                Data dataSerialize = data.getExtras().getParcelable("EditedEvent");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("EditedEvent"));
                filterReturn();

            }
        }
    }
}

