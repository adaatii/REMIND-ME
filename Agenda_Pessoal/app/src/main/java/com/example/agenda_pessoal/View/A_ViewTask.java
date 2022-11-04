package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.R;

public class A_ViewTask extends AppCompatActivity implements Constants {
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

    public void openEditTask(View view){
        Intent it_aEditTask = new Intent(this, A_EditTask.class);
        it_aEditTask.putExtra("Data", dataInstance);
        it_aEditTask.putExtra("Position", position);
        startActivityForResult(it_aEditTask, EDIT_TASK_ACTIVITY_REQUEST_CODE);
    }

    public void changeTaskToEvent(View view) {
        Intent it_aTask = new Intent();
        it_aTask.putExtra("Data", dataInstance);
        it_aTask.putExtra("Position", position);
        setResult(RESULT_OK, it_aTask);
        finish();

    }

    public void returnViewTaskToTask(View view){
        Intent it_aTask = new Intent();
        it_aTask.putExtra("Data", dataInstance);
        setResult(RESULT_FIRST_USER, it_aTask);
        finish();
    }

    private void setInfoEvent() {
        Task task = dataInstance.getDataTask().get(position);
        tv_titleViewTask.setText(task.getTitle());
        tv_descriptionViewTask.setText(task.description);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", Integer.toString(resultCode));
        if (requestCode == EDIT_TASK_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_FIRST_USER) {
                Data dataSerialize = data.getExtras().getParcelable("EditedTask");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("EditedTask"));

                setInfoEvent();

            }
        }
    }
}