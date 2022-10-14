package com.example.agenda_pessoal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.agenda_pessoal.Controller.Data;
import com.example.agenda_pessoal.R;

import java.util.Objects;

public class A_Login extends AppCompatActivity {
    Data dataInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataInstance = getIntent().getExtras().getParcelable("Data");
        Log.d("OperSerialize" , dataInstance.serialize());


        // Importantes
        getWindow().setStatusBarColor(Color.rgb(46, 113, 212));

    }

    public void abrirTela(View v){
        Intent it_tela = new Intent(this, A_Event.class);
        startActivity(it_tela);
    }
}