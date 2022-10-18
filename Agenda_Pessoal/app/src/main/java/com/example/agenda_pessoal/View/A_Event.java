package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.ArrayList;
import java.util.Calendar;

public class A_Event extends AppCompatActivity {

    private RecyclerView recyclerView;
    private A_RecyclerAdapterEvent adapterEvent;
    private Data dataInstance;
    private TextView tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        dataInstance = getIntent().getExtras().getParcelable("Data");

        tv_date = findViewById(R.id.tv_date);


        recyclerView = findViewById(R.id.recyclerView_Compromisso);
        recyclerView.setLayoutManager(new LinearLayoutManager(A_Event.this)); // Como o RecyclerView será mostrado na tela

        adapterEvent = new A_RecyclerAdapterEvent(dataInstance.getDataTask(), A_Event.this);
        recyclerView.setAdapter(adapterEvent);
    }

    public void datePicker(View v){
        /*MaterialDatePicker materialDatePicker =MaterialDatePicker.Builder.datePicker()
        .setTitleText("Selecione a data").build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                tv_date.setText(" " + materialDatePicker.getHeaderText());
            }
        });

        materialDatePicker.show(getSupportFragmentManager(), "TAG");*/
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day +"/"+month+"/"+year;
                tv_date.setText(date);
            }
        }, year, month, day);
        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);

    }
}