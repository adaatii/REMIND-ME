package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.Model.Adapter.RecyclerAdapterEvent;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.Model.Sort.TaskTree;
import com.example.agenda_pessoal.R;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class A_Event extends AppCompatActivity implements Constants {

    private RecyclerView recyclerView;
    private RecyclerAdapterEvent adapterEvent;
    private Data dataInstance;
    private TextView tv_date, tv_event;
    private int year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        dataInstance = getIntent().getExtras().getParcelable("Data");

        tv_date = findViewById(R.id.tv_date);
        tv_event = findViewById(R.id.tv_Event);
        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        tv_date.setText(localDate);

        recyclerView = findViewById(R.id.recyclerView_Compromisso);
        recyclerView.setLayoutManager(new LinearLayoutManager(A_Event.this)); // Como o RecyclerView será mostrado na tela

        ArrayList<Task> task = dataInstance.getDataTask();
        TaskTree taskTree = new TaskTree();
        // Ordenação do TaskData (dataInstance.getDataTask())
        for (int i = 0; i < task.size(); i++) {
            Task item = task.get(i);
            if (item.isEvent()) {
                taskTree.add(i, item.event.date, localDate);
            }
        }

        // Set a visibilidade do texto Não Há compromissos
        if (taskTree.sort().size() > 0) {
            adapterEvent = new RecyclerAdapterEvent(dataInstance.getDataTask(), taskTree.sort(), A_Event.this);
            recyclerView.setAdapter(adapterEvent);
            tv_event.setText("Compromissos");
        } else {
            adapterEvent = new RecyclerAdapterEvent(dataInstance.getDataTask(), taskTree.sort(), A_Event.this);
            recyclerView.setAdapter(adapterEvent);
            tv_event.setText("Não há Compromissos");
        }
    }

    public void openNewEventScreen(View v) {
        Intent it_aNewEvent = new Intent(this, A_NewEvent.class);
        it_aNewEvent.putExtra("Data", dataInstance);
        startActivityForResult(it_aNewEvent, NEW_EVENT_ACTIVITY_REQUEST_CODE);
    }

    public void datePicker(View view) {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(A_Event.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                tv_date.setText(date);

                ArrayList<Task> task = dataInstance.getDataTask();
                TaskTree taskTree = new TaskTree();
                // Ordenação do TaskData (dataInstance.getDataTask())
                for (int i = 0; i < task.size(); i++) {
                    Task item = task.get(i);
                    if (item.isEvent()) {
                        taskTree.add(i, item.event.date, date);
                    }
                }

                adapterEvent.reloadView(dataInstance.getDataTask(), taskTree.sort());
                // Set a visibilidade do texto Não Há compromissos
                if (taskTree.sort().size() == 0) {
                    tv_event.setText("Não há Compromissos");
                } else {
                    tv_event.setText("Compromissos");
                }

            }
        }, year, month, day);
        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", Integer.toString(resultCode));

        String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        tv_date.setText(localDate);

        ArrayList<Task> task = dataInstance.getDataTask();
        TaskTree taskTree = new TaskTree();
        // Ordenação do TaskData (dataInstance.getDataTask())
        for (int i = 0; i < task.size(); i++) {
            Task item = task.get(i);
            if (item.isEvent()) {
                taskTree.add(i, item.event.date, localDate);
            }
        }


        adapterEvent.reloadView(dataInstance.getDataTask(), taskTree.sort());
        // Set a visibilidade do texto Não Há compromissos
        if (taskTree.sort().size() == 0) {
            tv_event.setText("Não há Compromissos");
        } else {
            tv_event.setText("Compromissos");
        }


        if (requestCode == NEW_EVENT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_FIRST_USER) {
                dataInstance.Update(data.getExtras().getParcelable("Data"));
            }

        }

    }

}