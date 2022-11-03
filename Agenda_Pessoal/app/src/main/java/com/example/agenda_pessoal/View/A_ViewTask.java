package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.R;

public class A_ViewTask extends AppCompatActivity {
    Data dataInstance;
    TextView tv_titleViewTask, tv_descriptionViewTask;
    Integer position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        dataInstance = getIntent().getExtras().getParcelable("Data");
        position = getIntent().getExtras().getInt("Position");

        tv_titleViewTask = findViewById(R.id.tv_titleViewTask);
        tv_descriptionViewTask = findViewById(R.id.tv_descriptionViewTask);

        setInfoEvent();

    }

    public void changeTaskToEvent(View view) {
        Intent it_aTask = new Intent();
        it_aTask.putExtra("Data", dataInstance);
        it_aTask.putExtra("Position", position);
        setResult(RESULT_OK, it_aTask);
        finish();

    }

    private void setInfoEvent() {
        Task task = dataInstance.getDataTask().get(position);
        tv_titleViewTask.setText(task.getTitle());
        tv_descriptionViewTask.setText(task.description);
    }
}