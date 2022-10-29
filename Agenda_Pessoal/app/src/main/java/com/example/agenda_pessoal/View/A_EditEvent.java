package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.Task;
import com.example.agenda_pessoal.R;

public class A_EditEvent extends AppCompatActivity {
    Data dataInstance;
    Integer position;
    EditText et_titleEditEvent,et_descriptionEditEvent;
    TextView et_dateEditEvent, et_timeEditEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        dataInstance = getIntent().getExtras().getParcelable("Data");
        position = getIntent().getExtras().getInt("Position");

        et_titleEditEvent = findViewById(R.id.et_titleEditEvent);
        et_descriptionEditEvent = findViewById(R.id.et_descriptionEditEvent);
        et_dateEditEvent = findViewById(R.id.et_dateEditEvent);
        et_timeEditEvent = findViewById(R.id.et_timeEditEvent);

        setInfoEvent();
    }

    private void setInfoEvent() {
        Task event = dataInstance.getDataTask().get(position);
        et_titleEditEvent.setText(event.getTitle());
        et_descriptionEditEvent.setText(event.description);
        et_dateEditEvent.setText(event.event.date[0]);
        et_timeEditEvent.setText(event.event.date[1]);
    }
}