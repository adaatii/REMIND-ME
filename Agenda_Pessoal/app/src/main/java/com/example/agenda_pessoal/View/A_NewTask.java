package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class A_NewTask extends AppCompatActivity implements Constants {
    Data dataInstance;
    EditText et_titleNewTask,et_descriptionNewTask;
    private RadioButton rbtn_hight_priority_new_task, rbtn_medium_priority_new_task, rbtn_low_priority_new_task, rbtn_no_priority_new_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));


        dataInstance = getIntent().getExtras().getParcelable("Data");

        et_titleNewTask = findViewById(R.id.et_titleNewTask);
        et_descriptionNewTask = findViewById(R.id.et_descriptionNewTask);
        rbtn_hight_priority_new_task = findViewById(R.id.rbtn_hight_priority_new_task);
        rbtn_medium_priority_new_task = findViewById(R.id.rbtn_medium_priority_new_task);
        rbtn_low_priority_new_task = findViewById(R.id.rbtn_low_priority_new_task);
        rbtn_no_priority_new_task = findViewById(R.id.rbtn_no_priority_new_task);
    }

    public void alert(String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("OK", null);
        alertDialog.show();
    }

    public void createNewTask(View view){
        String title = et_titleNewTask.getText().toString();
        String description = et_descriptionNewTask.getText().toString();

        if (title.isEmpty()
                || !rbtn_hight_priority_new_task.isChecked()
                && !rbtn_medium_priority_new_task.isChecked()
                && !rbtn_low_priority_new_task.isChecked()
                && !rbtn_no_priority_new_task.isChecked()){
            //Title não deve estar vazio
            alert("Campos Obrigatórios", "Preencha todos os campos Obrigatórios");
        }else{
            Integer priority = 3;
            if (rbtn_hight_priority_new_task.isChecked()) {
                priority = 0;
            } else if (rbtn_medium_priority_new_task.isChecked()) {
                priority = 1;
            } else if (rbtn_low_priority_new_task.isChecked()) {
                priority = 2;
            } else if (rbtn_no_priority_new_task.isChecked()) {
                priority = 3;
            }
            Task newTask = new Task(title, dataInstance.log,priority);//RadioBTN
            newTask.description = description;
            dataInstance.getDataTask().add(newTask);
            Intent it_aTask = new Intent();
            it_aTask.putExtra("NewTask", dataInstance);
            setResult(RESULT_FIRST_USER, it_aTask);
            finish();
        }
    }
    public void returnNewTaskToTask(View view){
        Intent it_aTask = new Intent();
        setResult(RESULT_DESTROY, it_aTask);
        finish();
    }


}