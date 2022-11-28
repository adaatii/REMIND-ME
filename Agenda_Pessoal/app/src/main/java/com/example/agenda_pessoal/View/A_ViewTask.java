package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;

import com.example.agenda_pessoal.Controller_and_Model.Data;
import com.example.agenda_pessoal.Controller_and_Model.Task;
import com.example.agenda_pessoal.Sort_and_Adapters.Constants;
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
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        tv_titleViewTask = findViewById(R.id.tv_titleViewTask);
        tv_descriptionViewTask = findViewById(R.id.tv_descriptionViewTask);

        setInfoEvent();

    }

    public void optionAlert(
            String title,
            String message,
            DialogInterface.OnClickListener accept,
            DialogInterface.OnClickListener refuse
    ) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("SIM", accept).setNegativeButton("NÃO", refuse);
        alertDialog.show();
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

    public void deleteTask(View view) {
        optionAlert("Deletar Evento", "Deseja realmente deletar o evento ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataInstance.getDataTask().get(position).finished = true;
                Intent it_aTask = new Intent();
                it_aTask.putExtra("Data", dataInstance);
                setResult(RESULT_FIRST_USER, it_aTask);
                finish();
            }
        }, null);

    }

    public  void finishTask(View view){
        dataInstance.getDataTask().get(position).priority = 4;
        Intent it_aTask = new Intent();
        it_aTask.putExtra("Data", dataInstance);
        setResult(RESULT_FIRST_USER, it_aTask);
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
        if (task.description.equals("")){
            tv_descriptionViewTask.setText("Não há descrição");
        }else{
            tv_descriptionViewTask.setText(task.description);
        }


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