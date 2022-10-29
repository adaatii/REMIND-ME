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
import android.widget.TimePicker;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class A_NewTask extends AppCompatActivity {
    Data dataInstance;
    EditText et_titleNewTask,et_descriptionNewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);


        dataInstance = getIntent().getExtras().getParcelable("Data");

        et_titleNewTask = findViewById(R.id.et_titleNewTask);
        et_descriptionNewTask = findViewById(R.id.et_descriptionNewTask);
    }

    public void alert(String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("OK", null);
        alertDialog.show();
    }

    public void createNewTask(View view){
        String title = et_titleNewTask.getText().toString();
        String description = et_descriptionNewTask.getText().toString();

        if (title.isEmpty()){
            //Title não deve estar vazio
            alert("Campos Obrigatórios", "Preencha todos os campos Obrigatórios");
        }else{
            Task newTask = new Task(title, dataInstance.log);
            newTask.description = description;
            dataInstance.getDataTask().add(newTask);
            Intent it_aTask = new Intent();
            it_aTask.putExtra("NewTask", dataInstance);
            setResult(RESULT_FIRST_USER, it_aTask);
            finish();
        }

    }


}