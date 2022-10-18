package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.User;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.R;

import java.util.Objects;

public class A_Login extends AppCompatActivity implements Constants {
    Data dataInstance;
    EditText id_login_email;
    EditText id_login_password;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataInstance = getIntent().getExtras().getParcelable("Data");
        Log.d("OpenSerialize" , dataInstance.serialize());
        // Importantes
        getWindow().setStatusBarColor(Color.rgb(46, 113, 212));

        id_login_email = findViewById(R.id.et_login_email);
        id_login_password = findViewById(R.id.et_login_password);

    }

    public void openEvent(View v){
        String log_email = id_login_email.getText().toString();
        String log_password = id_login_password.getText().toString();

        Intent it_aEvent = new Intent(this, A_Event.class);
        it_aEvent.putExtra("Data", dataInstance);
        startActivityForResult(it_aEvent, EVENT_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", Integer.toString(resultCode));
        if (requestCode == EVENT_ACTIVITY_REQUEST_CODE){
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