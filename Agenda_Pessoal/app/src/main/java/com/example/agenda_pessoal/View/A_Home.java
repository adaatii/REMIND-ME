package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.Controller.User;
import com.example.agenda_pessoal.R;

import java.util.Objects;

public class A_Home extends AppCompatActivity {
    Data dataInstance = new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Importantes
        getWindow().setStatusBarColor(Color.rgb(46, 113, 212));


        Log.d("OperSerialize" , dataInstance.serialize());


    }

    public void abrirCadastroUsuario(View v){
        Intent it_telaCadastroUsuario = new Intent(this, A_Register.class);
        startActivity(it_telaCadastroUsuario);
    }
    
    public void abrirTelaLogin(View v){
        Intent it_aLogin = new Intent(this, A_Login.class);
        it_aLogin.putExtra("Data", dataInstance);
        startActivity(it_aLogin);
    }
}