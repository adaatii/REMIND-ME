package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Event;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class A_EditTask extends AppCompatActivity implements Constants {
    Data dataInstance;
    Integer position;
    EditText et_titleEditTask,et_descriptionEditTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        dataInstance = getIntent().getExtras().getParcelable("Data");
        position = getIntent().getExtras().getInt("Position");

        et_titleEditTask = findViewById(R.id.et_titleEditTask);
        et_descriptionEditTask = findViewById(R.id.et_descriptionEditTask);

        setInfoTask();
    }

    public void alert(String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("OK", null);
        alertDialog.show();
    }

    public void updateTask(View view){
        String title = et_titleEditTask.getText().toString();
        String description = et_descriptionEditTask.getText().toString();

        if (title.isEmpty()){
            //Title não deve estar vazio
            alert("Campos Obrigatórios", "Preencha todos os campos Obrigatórios");
        }else {
            dataInstance.getDataTask().get(position).setTitle(title);
            dataInstance.getDataTask().get(position).description = description;

            Intent it_aViewTask = new Intent();
            it_aViewTask.putExtra("EditedTask", dataInstance);
            setResult(RESULT_FIRST_USER, it_aViewTask);
            finish();
        }
    }

    public void returnEditTaskToViewTask(View v){
        Intent it_aViewTask = new Intent();
        setResult(RESULT_DESTROY, it_aViewTask);
        finish();
    }

    private void setInfoTask() {
        Task task = dataInstance.getDataTask().get(position);
        et_titleEditTask.setText(task.getTitle());
        et_descriptionEditTask.setText(task.description);
    }
}