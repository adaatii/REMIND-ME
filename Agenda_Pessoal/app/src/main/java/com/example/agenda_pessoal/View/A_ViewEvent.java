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

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.Model.Sort.TaskTree;
import com.example.agenda_pessoal.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class A_ViewEvent extends AppCompatActivity implements Constants {
    Data dataInstance;
    Integer position;
    TextView tv_titleViewEvent, tv_descriptionViewEvent, tv_dateTimeViewEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        dataInstance = getIntent().getExtras().getParcelable("Data");
        position = getIntent().getExtras().getInt("Position");
        Log.d("TAG", position.toString());

        tv_titleViewEvent = findViewById(R.id.tv_titleViewEvent);
        tv_descriptionViewEvent = findViewById(R.id.tv_descriptionViewEvent);
        tv_dateTimeViewEvent = findViewById(R.id.tv_dateTimeViewEvent);

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
    public void openEditEvent(View view){
        Intent it_aEditEvent = new Intent(this, A_EditEvent.class);
        it_aEditEvent.putExtra("Data", dataInstance);
        it_aEditEvent.putExtra("Position", position);
        startActivityForResult(it_aEditEvent, EDIT_EVENT_ACTIVITY_REQUEST_CODE);
    }

    public void returnToEvent(View view) {
        Intent it_aEvent = new Intent();
        it_aEvent.putExtra("Data", dataInstance);
        setResult(RESULT_FIRST_USER, it_aEvent);
        finish();
    }

    public void deleteEvent(View view){
        optionAlert("Deletar Evento", "Deseja realmente deletar o evento ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataInstance.getDataTask().get(position).finished = true;
                Intent it_aEvent = new Intent();
                it_aEvent.putExtra("Data", dataInstance);
                setResult(RESULT_FIRST_USER, it_aEvent);
                finish();
            }
        }, null);

    }

    public void setFinishedEvent(View view){
        optionAlert("Finalizar Evento", "Deseja finalizar o evento ?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataInstance.getDataTask().get(position).finished = true;
                Intent it_aEvent = new Intent();
                it_aEvent.putExtra("Data", dataInstance);
                setResult(RESULT_FIRST_USER, it_aEvent);
                finish();
            }
        }, null);

    }

    private void setInfoEvent() {
        Task event = dataInstance.getDataTask().get(position);
        tv_titleViewEvent.setText(event.getTitle());
        tv_descriptionViewEvent.setText(event.description);
        tv_dateTimeViewEvent.setText(event.event.date[0] + " " + event.event.date[1]);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", Integer.toString(resultCode));
        if (requestCode == EDIT_EVENT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_FIRST_USER) {
                Data dataSerialize = data.getExtras().getParcelable("EditedEvent");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("EditedEvent"));

                setInfoEvent();

            }else if (resultCode == RESULT_OK) {
                Data dataSerialize = data.getExtras().getParcelable("EditedEvent");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("EditedEvent"));
                position = data.getExtras().getInt("checkTime");
                setInfoEvent();

            }
        }
    }
}