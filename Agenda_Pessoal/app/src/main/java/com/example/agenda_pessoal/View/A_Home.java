package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Model.Constants;
import com.example.agenda_pessoal.R;

public class A_Home extends AppCompatActivity implements Constants {
    Data dataInstance = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Importantes
        getWindow().setStatusBarColor(Color.rgb(0, 71, 179));
        Log.d("OpenSerialize", dataInstance.serialize());
    }

    public void openRegister(View v) {
        Intent it_aRegister = new Intent(this, A_Register.class);
        it_aRegister.putExtra("Data", dataInstance);
        startActivityForResult(it_aRegister, REGISTER_ACTIVITY_REQUEST_CODE);
    }

    public void abrirTelaLogin(View v) {
        Intent it_aLogin = new Intent(this, A_Login.class);
        it_aLogin.putExtra("Data", dataInstance);
        startActivityForResult(it_aLogin, LOGIN_ACTIVITY_REQUEST_CODE);
    }

    // request code - Identificação da proxima tela,
    // resultCode - feedback do que ocorreu na tela,
    // data - retorna a informação

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode", Integer.toString(resultCode));
        if (requestCode == REGISTER_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_FIRST_USER) {
                Data dataSerialize = data.getExtras().getParcelable("NewUser");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("NewUser"));
                Intent it_aLogin = new Intent(this, A_Login.class);
                it_aLogin.putExtra("Data", dataInstance);
                startActivityForResult(it_aLogin, LOGIN_ACTIVITY_REQUEST_CODE);
            } else if (resultCode == RESULT_OK) {
                Intent it_aLogin = new Intent(this, A_Login.class);
                it_aLogin.putExtra("Data", dataInstance);
                startActivityForResult(it_aLogin, LOGIN_ACTIVITY_REQUEST_CODE);
            }
        }
        if (requestCode == LOGIN_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_FIRST_USER) {
                Log.d("OpenSerialize", dataInstance.serialize());
            } else if (resultCode == RESULT_OK) {
                Intent it_aRegister = new Intent(this, A_Register.class);
                it_aRegister.putExtra("Data", dataInstance);
                startActivityForResult(it_aRegister, REGISTER_ACTIVITY_REQUEST_CODE);
            } else if(resultCode == RESULT_DESTROY){
                Data dataSerialize = data.getExtras().getParcelable("Data");
                Log.d("OpenSerialize", dataSerialize.serialize());
                dataInstance.Update(data.getExtras().getParcelable("Data"));
            }
        }

    }
}