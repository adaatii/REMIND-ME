package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.R;

public class A_ViewTask extends AppCompatActivity {
    Data dataInstance;
    Integer position;
    TextView tv_titleViewTask,tv_descriptionViewTask;

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

    private void setInfoEvent() {
        Task task = dataInstance.getDataTask().get(position);
        tv_titleViewTask.setText(task.getTitle());
        tv_descriptionViewTask.setText(task.description);
    }
}