package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.R;


import java.util.ArrayList;
import java.util.Calendar;

public class A_Event extends AppCompatActivity implements Constants {

    private RecyclerView recyclerView;
    private A_RecyclerAdapterEvent adapterEvent;
    private Data dataInstance;
    private TextView tv_date;
    private int year, month, day;



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
    public void openNewEventScreen(View v) {
        Intent it_aNewEvent = new Intent(this, A_NewEvent.class);
        it_aNewEvent.putExtra("Data", dataInstance);
        startActivityForResult(it_aNewEvent, NEW_EVENT_ACTIVITY_REQUEST_CODE);
    }

    public void datePicker(View view){
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(A_Event.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = dayOfMonth+"/"+month+"/"+year;
                tv_date.setText(date);
            }
        }, year, month, day);
        dialog.show();
        dialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        dialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", Integer.toString(resultCode));
        if (requestCode == REGISTER_ACTIVITY_REQUEST_CODE){
            if (resultCode == RESULT_FIRST_USER){
                Data dataSerialize = data.getExtras().getParcelable("NewUser");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("NewUser"));
                Intent it_aLogin = new Intent(this, A_Login.class);
                it_aLogin.putExtra("Data", dataInstance);
                startActivityForResult(it_aLogin, LOGIN_ACTIVITY_REQUEST_CODE);
            }else if(resultCode == RESULT_OK){
                Intent it_aLogin = new Intent(this, A_Login.class);
                it_aLogin.putExtra("Data", dataInstance);
                startActivityForResult(it_aLogin, LOGIN_ACTIVITY_REQUEST_CODE);
            }
        }
    }

}