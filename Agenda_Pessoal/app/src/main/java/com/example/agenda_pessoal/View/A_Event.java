package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

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
        for (int id = 0; id < task.size(); id++) {
            Task item = task.get(id);
            if (item.isEvent() && item.getOwner(dataInstance.log) && !item.finished) {
                taskTree.add(id, item.event.date, localDate);
            }
        }
        adapterEvent = new RecyclerAdapterEvent(dataInstance.getDataTask(), taskTree.sort(), A_Event.this);
        recyclerView.setAdapter(adapterEvent);
        adapterEvent.setListener(new RecyclerAdapterEvent.itemActivityListener() {
            @Override
            public void onItemClick(int position) {
                Intent it_viewEvent = new Intent(A_Event.this, A_ViewEvent.class);
                it_viewEvent.putExtra("Data", dataInstance);
                it_viewEvent.putExtra("Position", position);
                startActivityForResult(it_viewEvent, VIEW_EVENT_ACTIVITY_REQUEST_CODE);
            }
        });

        // Set a visibilidade do texto Não Há compromissos
        if (taskTree.sort().size() > 0) {
            tv_event.setText(R.string.compromissos);
        } else {
            tv_event.setText(R.string.nao_ha_compromissos);
        }
    }

    public void openNewEventScreen(View v) {
        Intent it_aNewEvent = new Intent(this, A_NewEvent.class);
        it_aNewEvent.putExtra("Data", dataInstance);
        startActivityForResult(it_aNewEvent, NEW_EVENT_ACTIVITY_REQUEST_CODE);
    }

    public void openProfileScreen(View v){
        Intent it_aProfile = new Intent(this, A_Profile.class);
        it_aProfile.putExtra("Data", dataInstance);
        startActivityForResult(it_aProfile, PROFILE_ACTIVITY_REQUEST_CODE);
    }

    public void openTaskScreen(View v){
        Intent it_aTask = new Intent(this, A_Task.class);
        it_aTask.putExtra("Data", dataInstance);
        startActivityForResult(it_aTask, TASK_ACTIVITY_REQUEST_CODE);
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
                    if (item.isEvent() && item.getOwner(dataInstance.log)){
                        taskTree.add(i, item.event.date, date);
                    }
                }

                adapterEvent.reloadView(dataInstance.getDataTask(), taskTree.sort());
                // Set a visibilidade do texto Não Há compromissos
                if (taskTree.sort().size() == 0) {
                    tv_event.setText(R.string.nao_ha_compromissos);
                } else {
                    tv_event.setText(R.string.compromissos);
                }

            }
        }, year, month, day);
        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }
    public void logOutAlert(
            String title,
            String message,
            DialogInterface.OnClickListener accept,
            DialogInterface.OnClickListener refuse
    ) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("SIM", accept).setNegativeButton("NÃO", refuse);
        alertDialog.show();
    }
    public void returnToLogin(View view){
        logOutAlert("Logout", "Deseja realizar o Logout ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataInstance.log = null;
                Intent it_aLogin = new Intent();
                it_aLogin.putExtra("Data", dataInstance);
                setResult(RESULT_OK, it_aLogin);
                finish();
            }
        }, null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", Integer.toString(resultCode));
        if (requestCode == NEW_EVENT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_FIRST_USER) {
                Data dataSerialize = data.getExtras().getParcelable("NewEvent");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("NewEvent"));
                String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                tv_date.setText(localDate);

                ArrayList<Task> task = dataInstance.getDataTask();
                TaskTree taskTree = new TaskTree();
                // Ordenação do TaskData (dataInstance.getDataTask())
                for (int i = 0; i < task.size(); i++) {
                    Task item = task.get(i);
                    if (item.isEvent() && item.getOwner(dataInstance.log) && !item.finished) {
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
            }

        } else if (requestCode == PROFILE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK){
                Data dataSerialize = data.getExtras().getParcelable("Data");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("Data"));
            }
        } else if (requestCode == VIEW_EVENT_ACTIVITY_REQUEST_CODE){
            if (resultCode == RESULT_FIRST_USER){
                Data dataSerialize = data.getExtras().getParcelable("Data");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("Data"));

                String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                tv_date.setText(localDate);

                ArrayList<Task> task = dataInstance.getDataTask();
                TaskTree taskTree = new TaskTree();
                // Ordenação do TaskData (dataInstance.getDataTask())
                for (int i = 0; i < task.size(); i++) {
                    Task item = task.get(i);
                    if (item.isEvent() && item.getOwner(dataInstance.log) && !item.finished) {
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
            }
        } else if (requestCode == TASK_ACTIVITY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                Data dataSerialize = data.getExtras().getParcelable("Data");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("Data"));

                String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                tv_date.setText(localDate);

                ArrayList<Task> task = dataInstance.getDataTask();
                TaskTree taskTree = new TaskTree();
                // Ordenação do TaskData (dataInstance.getDataTask())
                for (int i = 0; i < task.size(); i++) {
                    Task item = task.get(i);
                    if (item.isEvent() && item.getOwner(dataInstance.log) && !item.finished) {
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
            }
        }
    }

}