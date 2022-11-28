package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.agenda_pessoal.Controller_and_Model.Data;
import com.example.agenda_pessoal.Controller_and_Model.Event;
import com.example.agenda_pessoal.Controller_and_Model.Task;
import com.example.agenda_pessoal.Sort_and_Adapters.Constants;
import com.example.agenda_pessoal.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class A_EditEvent extends AppCompatActivity implements Constants {
    Data dataInstance;
    Integer position;
    EditText et_titleEditEvent, et_descriptionEditEvent;
    TextView tv_dateEditEvent, tv_timeEditEvent, tv_edit_event;
    private int year, month, day;
    RadioButton rbtn_hight_priority_edit_event, rbtn_medium_priority_edit_event, rbtn_low_priority_edit_event, rbtn_no_priority_edit_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        dataInstance = getIntent().getExtras().getParcelable("Data");
        position = getIntent().getExtras().getInt("Position");

        et_titleEditEvent = findViewById(R.id.et_titleEditEvent);
        et_descriptionEditEvent = findViewById(R.id.et_descriptionEditEvent);
        tv_dateEditEvent = findViewById(R.id.et_dateEditEvent);
        tv_timeEditEvent = findViewById(R.id.et_timeEditEvent);
        tv_edit_event = findViewById(R.id.tv_edit_event);

        rbtn_hight_priority_edit_event = findViewById(R.id.rbtn_hight_priority_edit_event);
        rbtn_medium_priority_edit_event = findViewById(R.id.rbtn_medium_priority_edit_event);
        rbtn_low_priority_edit_event = findViewById(R.id.rbtn_low_priority_edit_event);
        rbtn_no_priority_edit_event = findViewById(R.id.rbtn_no_priority_edit_event);

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

    public void alert(String title, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("OK", null);
        alertDialog.show();
    }

    public void changeEventAlert(
            String title,
            String message,
            DialogInterface.OnClickListener accept,
            DialogInterface.OnClickListener refuse
    ) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(title).setMessage(message).setPositiveButton("SIM", accept).setNegativeButton("NÃO", refuse);
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

    public void updateEvent(View view) {
        String date = tv_dateEditEvent.getText().toString();
        String time = tv_timeEditEvent.getText().toString();
        String title = et_titleEditEvent.getText().toString();
        String description = et_descriptionEditEvent.getText().toString();

        if (title.isEmpty()
                || date.isEmpty()
                || time.isEmpty()
                || !rbtn_hight_priority_edit_event.isChecked()
                && !rbtn_medium_priority_edit_event.isChecked()
                && !rbtn_low_priority_edit_event.isChecked()
                && !rbtn_no_priority_edit_event.isChecked()) {
            //Title não deve estar vazio
            alert("Campos Obrigatórios", "Preencha todos os campos Obrigatórios");
        } else {
            //Quando faz a verificação se existe evento e faz a alteração, o evento é alterado porém o anterior já cadastrado continua visivel;
           Integer checkTime = dataInstance.checkEventTime(date, time);
            if (checkTime != null && !checkTime.equals(position)) {
                String titleEvent = dataInstance.getDataTask().get(checkTime).getTitle();
                Integer priorityEventint = dataInstance.getDataTask().get(checkTime).priority;
                String priorityEvent = " ";
                switch (priorityEventint) {
                    case 0:
                        priorityEvent = "Alta";
                        break;
                    case 1:
                        priorityEvent = "Média";
                        break;
                    case 2:
                        priorityEvent = "Baixa";
                        break;
                    case 3:
                        priorityEvent = "Nula";
                        break;
                    default:
                        priorityEvent = "Nula";
                        break;
                }
                changeEventAlert(
                        "Sobrescrever evento",
                        "O evento " + titleEvent + " de prioridade " + priorityEvent + " já ocupa esse horário deseja substituir?",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Integer priority = 3;
                                if (rbtn_hight_priority_edit_event.isChecked()) {
                                    priority = 0;
                                } else if (rbtn_medium_priority_edit_event.isChecked()) {
                                    priority = 1;
                                } else if (rbtn_low_priority_edit_event.isChecked()) {
                                    priority = 2;
                                } else if (rbtn_no_priority_edit_event.isChecked()) {
                                    priority = 3;
                                }
                                dataInstance.getDataTask().get(checkTime).setTitle(title);
                                dataInstance.getDataTask().get(checkTime).priority = priority;
                                dataInstance.getDataTask().get(checkTime).description = description;
                                dataInstance.getDataTask().get(checkTime).event.date = new String[]{date, time};
                                dataInstance.getDataTask().get(position).finished = true;

                                Intent it_aViewEvent = new Intent();
                                it_aViewEvent.putExtra("EditedEvent", dataInstance);
                                it_aViewEvent.putExtra("checkTime", checkTime);
                                setResult(RESULT_OK, it_aViewEvent);
                                finish();
                            }
                        }, null);
            }else{
            Integer priority = 3;
            if (rbtn_hight_priority_edit_event.isChecked()) {
                priority = 0;
            } else if (rbtn_medium_priority_edit_event.isChecked()) {
                priority = 1;
            } else if (rbtn_low_priority_edit_event.isChecked()) {
                priority = 2;
            } else if (rbtn_no_priority_edit_event.isChecked()) {
                priority = 3;
            }

            dataInstance.getDataTask().get(position).setTitle(title);
            dataInstance.getDataTask().get(position).priority = priority;
            dataInstance.getDataTask().get(position).description = description;
            dataInstance.getDataTask().get(position).event.date = new String[]{date, time};

            Intent it_aViewEvent = new Intent();
            it_aViewEvent.putExtra("EditedEvent", dataInstance);
            setResult(RESULT_FIRST_USER, it_aViewEvent);
            finish();
        }
        }
    }

    public void returnEditEventToViewEvent(View v) {
        Intent it_aViewEvent = new Intent();
        setResult(RESULT_DESTROY, it_aViewEvent);
        finish();
    }

    private void setInfoEvent() {
        Task event = dataInstance.getDataTask().get(position);
        et_titleEditEvent.setText(event.getTitle());
        et_descriptionEditEvent.setText(event.description);
        if (event.isEvent()) {
            tv_dateEditEvent.setText(event.event.date[0]);
            tv_timeEditEvent.setText(event.event.date[1]);
        } else {
            String localDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            Date time = new Date();
            String localTime = new SimpleDateFormat("HH:mm").format(time);
            dataInstance.getDataTask().get(position).event = new Event(new String[]{localDate, "00:00"});
            tv_dateEditEvent.setText(localDate);
            tv_timeEditEvent.setText(localTime);
            tv_edit_event.setText("Transformando\n em Evento");

        }
    }
}