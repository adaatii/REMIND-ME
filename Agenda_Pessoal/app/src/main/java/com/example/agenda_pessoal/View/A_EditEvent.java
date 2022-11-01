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
import com.example.agenda_pessoal.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class A_EditEvent extends AppCompatActivity {
    Data dataInstance;
    Integer position;
    EditText et_titleEditEvent,et_descriptionEditEvent;
    TextView tv_dateEditEvent, tv_timeEditEvent;
    private int year, month, day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        dataInstance = getIntent().getExtras().getParcelable("Data");
        position = getIntent().getExtras().getInt("Position");

        et_titleEditEvent = findViewById(R.id.et_titleEditEvent);
        et_descriptionEditEvent = findViewById(R.id.et_descriptionEditEvent);
        tv_dateEditEvent = findViewById(R.id.et_dateEditEvent);
        tv_timeEditEvent = findViewById(R.id.et_timeEditEvent);

        setInfoEvent();

        tv_dateEditEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerEditEvent();
            }
        });

        tv_timeEditEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerEditEvent();
            }
        });
    }

    public void alert(String title, String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("OK", null);
        alertDialog.show();
    }

    public void datePickerEditEvent() {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(A_EditEvent.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                tv_dateEditEvent.setText(date);
            }
        }, year, month, day);
        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }

    public void timePickerEditEvent() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(0, 0, 0, hourOfDay, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm"); // "hh:mm AA"
                tv_timeEditEvent.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, 12, 0, false);
        timePickerDialog.show();
        timePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        timePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }

    public void updateEvent(View view){
        String date = tv_dateEditEvent.getText().toString();
        String time = tv_timeEditEvent.getText().toString();
        String title = et_titleEditEvent.getText().toString();
        String description = et_descriptionEditEvent.getText().toString();

        if (title.isEmpty() || date.isEmpty() || time.isEmpty()){
            //Title não deve estar vazio
            alert("Campos Obrigatórios", "Preencha todos os campos Obrigatórios");
        }else {
            Task upEvent = dataInstance.getDataTask().get(position);
            ;
            upEvent.setTitle(title);
            upEvent.description = description;
            upEvent.event.date = new String[]{date, time};

            Intent it_aViewEvent = new Intent();
            it_aViewEvent.putExtra("EditedEvent", dataInstance);
            setResult(RESULT_FIRST_USER, it_aViewEvent);
            finish();
        }
    }

    private void setInfoEvent() {
        Task event = dataInstance.getDataTask().get(position);
        et_titleEditEvent.setText(event.getTitle());
        et_descriptionEditEvent.setText(event.description);
        tv_dateEditEvent.setText(event.event.date[0]);
        tv_timeEditEvent.setText(event.event.date[1]);
    }
}