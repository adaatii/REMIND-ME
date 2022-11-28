package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.agenda_pessoal.Controller_and_Model.Data;
import com.example.agenda_pessoal.Controller_and_Model.Task;
import com.example.agenda_pessoal.Sort_and_Adapters.Constants;
import com.example.agenda_pessoal.R;

public class A_EditTask extends AppCompatActivity implements Constants {
    Data dataInstance;
    Integer position;
    EditText et_titleEditTask,et_descriptionEditTask;
    RadioButton rbtn_hight_priority_edit_task, rbtn_medium_priority_edit_task, rbtn_low_priority_edit_task, rbtn_no_priority_edit_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        dataInstance = getIntent().getExtras().getParcelable("Data");
        position = getIntent().getExtras().getInt("Position");

        et_titleEditTask = findViewById(R.id.et_titleEditTask);
        et_descriptionEditTask = findViewById(R.id.et_descriptionEditTask);

        rbtn_hight_priority_edit_task = findViewById(R.id.rbtn_hight_priority_edit_task);
        rbtn_medium_priority_edit_task = findViewById(R.id.rbtn_medium_priority_edit_task);
        rbtn_low_priority_edit_task = findViewById(R.id.rbtn_low_priority_edit_task);
        rbtn_no_priority_edit_task = findViewById(R.id.rbtn_no_priority_edit_task);

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

        if (title.isEmpty() || !rbtn_hight_priority_edit_task.isChecked()
                && !rbtn_medium_priority_edit_task.isChecked()
                && !rbtn_low_priority_edit_task.isChecked()
                && !rbtn_no_priority_edit_task.isChecked()){
            //Title não deve estar vazio
            alert("Campos Obrigatórios", "Preencha todos os campos Obrigatórios");
        }else {
            Integer priority = 3;
            if (rbtn_hight_priority_edit_task.isChecked()) {
                priority = 0;
            } else if (rbtn_medium_priority_edit_task.isChecked()) {
                priority = 1;
            } else if (rbtn_low_priority_edit_task.isChecked()) {
                priority = 2;
            } else if (rbtn_no_priority_edit_task.isChecked()) {
                priority = 3;
            }
            dataInstance.getDataTask().get(position).setTitle(title);
            dataInstance.getDataTask().get(position).description = description;
            dataInstance.getDataTask().get(position).priority = priority;

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