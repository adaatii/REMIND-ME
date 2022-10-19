package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class A_NewEvent extends AppCompatActivity {

    private TextView tv_dateNewEvent, tv_timeNewEvent;
    private int year, month, day;
    Data dataInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        dataInstance = getIntent().getExtras().getParcelable("Data");

        tv_dateNewEvent = findViewById(R.id.et_dateNewEvent);
        tv_timeNewEvent = findViewById(R.id.et_timeNewEvent);

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


}