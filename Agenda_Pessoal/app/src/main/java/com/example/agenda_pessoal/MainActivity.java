package com.example.agenda_pessoal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirCadastroUsuario(View v){
        Intent it_telaCadastroUsuario = new Intent(this, TelaCadastroUsuario.class);
        startActivity(it_telaCadastroUsuario);
    }
}