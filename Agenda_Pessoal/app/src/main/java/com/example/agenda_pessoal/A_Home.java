package com.example.agenda_pessoal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class A_Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Importantes
        getWindow().setStatusBarColor(Color.rgb(46, 113, 212));
        Objects.requireNonNull(getSupportActionBar()).hide(); //esconde a action bar
    }

    public void abrirCadastroUsuario(View v){
        Intent it_telaCadastroUsuario = new Intent(this, A_Register.class);
        startActivity(it_telaCadastroUsuario);
    }
}