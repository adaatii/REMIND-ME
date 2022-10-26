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
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class A_NewEvent extends AppCompatActivity implements Constants {

    private TextView tv_dateNewEvent, tv_timeNewEvent;
    private EditText titleNewEvent, descriptionNewEvent;
    private int year, month, day;
    Data dataInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        dataInstance = getIntent().getExtras().getParcelable("Data");

        tv_dateNewEvent = findViewById(R.id.et_dateNewEvent);
        tv_timeNewEvent = findViewById(R.id.et_timeNewEvent);
        titleNewEvent = findViewById(R.id.et_titleNewEvent);
        descriptionNewEvent = findViewById(R.id.et_descriptionNewEvent);

        tv_dateNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataPickerNewEvent();
            }
        });

        tv_timeNewEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerNewEvent();
            }
        });

    }

    public void alert(String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("OK", null);
        alertDialog.show();
    }


    public void dataPickerNewEvent() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(A_NewEvent.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                tv_dateNewEvent.setText(date);
            }
        }, year, month, day);
        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }

    public void timePickerNewEvent() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm"); // "hh:mm AA"
                tv_timeNewEvent.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, 12, 0, false);
        timePickerDialog.show();
        timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }

    public void createNewEvent(View view){
        String date = tv_dateNewEvent.getText().toString();
        String time = tv_timeNewEvent.getText().toString();
        String title = titleNewEvent.getText().toString();
        if (title.isEmpty() || date.isEmpty() || time.isEmpty()){
            //Title não deve estar vazio
            alert("Campos Obrigatórios", "Título, data e hora são campos obrigatórios");
        }else{
            Task newEvent = new Task(title, 0);
            newEvent.description = descriptionNewEvent.getText().toString();
            newEvent.createEvent(new String[] {date, time});
            dataInstance.getDataTask().add(newEvent);
            Intent it_aEvent = new Intent();
            it_aEvent.putExtra("NewEvent", dataInstance);
            setResult(RESULT_FIRST_USER, it_aEvent);
            finish();
        }
    }

    public void returnToEvent(View view){
        Intent it_aEvent = new Intent();
        setResult(RESULT_DESTROY, it_aEvent);
        finish();
    }

}