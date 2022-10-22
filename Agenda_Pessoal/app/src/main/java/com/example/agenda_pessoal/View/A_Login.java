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

        User loginUser = new User();

        if (loginUser.authenticateEmail(log_email)){
            boolean emailIsUsed = false;
            int currentUser = 0;
            for (int i = 0; i < dataInstance.getDataUser().size(); i++) {
                if (dataInstance.getDataUser().get(i).getEmail().equals(log_email)) {
                    emailIsUsed = true;
                    currentUser = i;
                }
            }

            boolean currect;
            currect = emailIsUsed && loginUser.validatePassword(dataInstance.getDataUser().get(currentUser).getPassword(), log_password);
            if (currect) {
                Intent it_aEvent = new Intent(this, A_Event.class);
                it_aEvent.putExtra("Data", dataInstance);
                startActivityForResult(it_aEvent, EVENT_ACTIVITY_REQUEST_CODE);
                //intent.putExtra("UserId", currentUser);
                finish();
            } else {
                // Esse email ou senha incorretos
                //alert(getString(R.string.informationIncorrect));
                Log.d("OperLog" , "Email ou senha incorretos");
                Log.d("OperSerialize" , dataInstance.serialize());
            }


        }else{
            // email invalido
            //alert(getString(R.string.invalidEmail));
            Log.d("OperLog" , "Email invalido");
            Log.d("OperSerialize" , dataInstance.serialize());
        }


    }

    public void returnToHome(View v){
        Intent it_aHome = new Intent();
        setResult(RESULT_DESTROY, it_aHome);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", Integer.toString(resultCode));
        if (requestCode == EVENT_ACTIVITY_REQUEST_CODE){
            if (resultCode == RESULT_FIRST_USER){
                Intent it_aHome = new Intent();
                it_aHome.putExtra("Data", dataInstance);
                setResult(RESULT_FIRST_USER, it_aHome);
                finish();
            }else if(resultCode == RESULT_OK){
                Intent it_aLogin = new Intent(this, A_Login.class);
                it_aLogin.putExtra("Data", dataInstance);
                startActivityForResult(it_aLogin, LOGIN_ACTIVITY_REQUEST_CODE);
            }
        }
    }
}