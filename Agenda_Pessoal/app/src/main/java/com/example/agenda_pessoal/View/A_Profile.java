package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.User;
import com.example.agenda_pessoal.R;

public class A_Profile extends AppCompatActivity {
    Data dataInstance;
    TextView tv_profileName, tv_profileEmail, tv_profilePhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));

        dataInstance = getIntent().getExtras().getParcelable("Data");
        Log.d("OpenSerialize", dataInstance.serialize());

        tv_profileName = findViewById(R.id.tv_profileName);
        tv_profileEmail = findViewById(R.id.tv_profileEmail);
        tv_profilePhone = findViewById(R.id.tv_profilePhone);

        setInfoProfile();
    }

    private void setInfoProfile() {
        User user = dataInstance.getDataUser().get(dataInstance.log);
        tv_profileName.setText(user.name);
        tv_profileEmail.setText(user.getEmail());
        tv_profilePhone.setText(user.phone);
    }

    public void returnProfileToEvent(View view) {
        Intent it_aEvent = new Intent();
        it_aEvent.putExtra("Data", dataInstance);
        setResult(RESULT_OK, it_aEvent);
        finish();

    }
}